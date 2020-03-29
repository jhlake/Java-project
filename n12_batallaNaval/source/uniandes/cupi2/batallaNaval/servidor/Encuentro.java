/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Encuentro.java 2110 2010-11-23 15:32:12Z cm.rodriguez155 $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 23/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.servidor;

import java.io.*;
import java.net.*;
import java.sql.*;

import uniandes.cupi2.batallaNaval.cliente.*;

/**
 * Esta clase administra un encuentro y mantiene la comunicación entre los dos jugadores. Esta clase define <br> 
 * la parte fija de los mensajes del protocolo de comunicación.<br>
 * Cada encuentro funciona en un thread diferente, permitiendo que en el mismo servidor se lleven a cabo <br> 
 * varios encuentros a la vez.<br>
 * <b>inv:</b><br>
 * !finJuego => socketJugador1 != null <br>
 * !finJuego => out1 != null <br>
 * !finJuego => in1 != null <br>
 * !finJuego => socketJugador2 != null <br>
 * !finJuego => out2 != null <br>
 * !finJuego => in2 != null <br>
 * jugador1 != null <br>
 * jugador2 != null <br>
 */
public class Encuentro extends Thread
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Mensaje inicial de un jugador
     */
    public static final String JUGADOR = "JUGADOR";

    /**
     * Mensaje con el registro del jugador
     */
    public static final String INFO_JUGADOR = "INFO";

    /**
     * Mensaje para indicar que un jugador tiene el primer turno
     */
    public static final String PRIMER_TURNO = "1";

    /**
     * Mensaje para indicar que un jugador tiene el segundo turno
     */
    public static final String SEGUNDO_TURNO = "2";

    /**
     * Mensaje para enviar la información de un disparo
     */
    public static final String JUGADA = "JUGADA";

    /**
     * Mensaje para indicar que un disparo no le atinó a ningún barco
     */
    public static final String AGUA = "AGUA";

    /**
     * Mensaje para indicar que un disparo impactó un barco
     */
    public static final String IMPACTO = "IMPACTO";

    /**
     * Mensaje para indicar que un disparo hundió el último barco de un jugador
     */
    public static final String FIN_JUEGO = "FIN_JUEGO";

    /**
     * Mensaje para indicar quien fue el ganador del juego
     */
    public static final String GANADOR = "GANADOR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El canal usado para comunicarse con el jugador 1
     */
    private Socket socketJugador1;

    /**
     * El flujo de escritura conectado con el jugador 1
     */
    private PrintWriter out1;

    /**
     * El flujo de lectura conectado con el jugador 1
     */
    private BufferedReader in1;

    /**
     * El canal usado para comunicarse con el jugador 2
     */
    private Socket socketJugador2;

    /**
     * El flujo de escritura conectado con el jugador 2
     */
    private PrintWriter out2;

    /**
     * El flujo de lectura conectado con el jugador 2
     */
    private BufferedReader in2;

    /**
     * El objeto con la información sobre el jugador 1: visibilidad protegida, para facilitar las pruebas
     */
    protected JugadorRemoto jugador1;

    /**
     * El objeto con la información sobre el jugador 2: visibilidad protegida, para facilitar las pruebas
     */
    protected JugadorRemoto jugador2;

    /**
     * Indica que el encuentro debe terminar
     */
    private boolean finJuego;

    /**
     * Es el administrador que permite registrar el resultado del encuentro sobre la base de datos y consultar la información de los jugadores
     */
    private AdministradorResultados adminResultados;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Establece la comunicación con los dos jugadores y el encuentro queda listo para iniciar
     * @param canal1 El socket para comunicarse con el jugador 1 - cliente1 != null
     * @param canal2 El socket para comunicarse con el jugador 2 - cliente2 != null
     * @param administrador Es el objeto que permite consultar y registrar resultados sobre la base de datos - administrador != null
     * @throws IOException Se lanza esta excepción si hay problemas estableciendo la comunicación con los dos jugadores
     */
    public Encuentro( Socket canal1, Socket canal2, AdministradorResultados administrador ) throws IOException
    {
        adminResultados = administrador;

        out1 = new PrintWriter( canal1.getOutputStream( ), true );
        in1 = new BufferedReader( new InputStreamReader( canal1.getInputStream( ) ) );
        socketJugador1 = canal1;

        out2 = new PrintWriter( canal2.getOutputStream( ), true );
        in2 = new BufferedReader( new InputStreamReader( canal2.getInputStream( ) ) );
        socketJugador2 = canal2;

        finJuego = false;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el socket usado para comunicarse con el jugador 1
     * @return socketJugador1
     */
    public Socket darSocketJugador1( )
    {
        return socketJugador1;
    }

    /**
     * Retorna el socket usado para comunicarse con el jugador 2
     * @return socketJugador2
     */
    public Socket darSocketJugador2( )
    {
        return socketJugador2;
    }

    /**
     * Retorna el administrador de resultados en el que se registran los resultados del encuentro
     * @return adminResultados
     */
    public AdministradorResultados darAdministradorResultados( )
    {
        return adminResultados;
    }

    /**
     * Indica si el encuentro ya terminó.
     * @return Retorna true si el encuentro terminó. Retorna false en caso contrario.
     */
    public boolean encuentroTerminado( )
    {
        return finJuego;
    }

    /**
     * Obtiene la información de un jugador a partir del mensaje que envió cuando entró al encuentro
     * @param info El mensaje que fue enviado - info es de la forma "JUGADOR:<nombre>"
     * @return Retorna la información del jugador
     * @throws BatallaNavalException Se lanza esta excepción si hay problemas consultando a la base de datos o se recibe un mensaje con un formato inesperado
     */
    private RegistroJugador consultarJugador( String info ) throws BatallaNavalException
    {
        if( info.startsWith( JUGADOR ) )
        {
            String nombre = info.split( ":" )[ 1 ];
            try
            {
                RegistroJugador reg1 = adminResultados.consultarRegistroJugador( nombre );
                return reg1;
            }
            catch( SQLException e )
            {
                throw new BatallaNavalException( "Hubo un problema leyendo la información del jugador: " + e.getMessage( ) );
            }
        }
        else
        {
            throw new BatallaNavalException( "El mensaje no tiene el formato esperado" );
        }
    }

    /**
     * Envía la información registrada de un jugador usando uno de los streams que permiten la comunicación con los clientes
     * @param out El stream a través del cual se debe enviar la información - out es un stream abierto hacia uno de los jugadores
     * @param reg El registro que se va a transmitir - reg != null
     */
    private void enviarInformacion( PrintWriter out, RegistroJugador reg )
    {
        String cadena = INFO_JUGADOR + ":" + reg.darNombreJugador( ) + ":" + reg.darEncuentrosGanados( ) + ":" + reg.darEncuentrosPerdidos( );
        out.println( cadena );
    }

    /**
     * Inicia el encuentro y realiza todas las acciones necesarias mientras que este dure.<br>
     * El ciclo de vida de un encuentro tiene tres partes:<br>
     * 1. Iniciar el encuentro <br>
     * 2. Realizar el encuentro (permitir la comunicación entre los clientes)<br>
     * 3. Terminar el encuentro y enviar la información sobre el ganador
     */
    public void run( )
    {
        try
        {
            iniciarEncuentro( );

            // Iniciar el juego
            int atacante = 1;

            while( !finJuego )
            {
                procesarJugada( atacante );

                if( finJuego )
                {
                    terminarEncuentro( );
                }
                else
                {
                    atacante = ( atacante == 1 ) ? 2 : 1;
                }
            }
        }
        catch( Exception e )
        {
            finJuego = true;

            try
            {
                in1.close( );
                out1.close( );
                socketJugador1.close( );
            }
            catch( IOException e2 )
            {
                e2.printStackTrace( );
            }

            try
            {
                in2.close( );
                out2.close( );
                socketJugador2.close( );
            }
            catch( IOException e2 )
            {
                e2.printStackTrace( );
            }
        }
    }

    /**
     * Realiza las actividades necesarias para iniciar un encuentro: <br>
     * 1. Lee la información con los nombres de los jugadores <br>
     * 2. Consulta los registros de los jugadores <br>
     * 3. Envía a cada jugador tanto su información como la de su oponente <br>
     * 4. Le envía a cada jugador un indicador para que sepa si es su turno de jugar. Siempre inicia el juego el primer jugador que se conectó. <br>
     * @throws BatallaNavalException Se lanza esta excepción si hay problemas con el acceso a la base de datos
     * @throws IOException Se lanza esta excepción si hay problemas en la comunicación
     */
    protected void iniciarEncuentro( ) throws IOException, BatallaNavalException
    {
        // Leer la información sobre los jugadores
        String info1 = in1.readLine( );
        RegistroJugador reg1 = consultarJugador( info1 );
        jugador1 = new JugadorRemoto( reg1 );

        String info2 = in2.readLine( );
        RegistroJugador reg2 = consultarJugador( info2 );
        jugador2 = new JugadorRemoto( reg2 );

        // Enviar a cada jugador la información sobre su registro y el del oponente (en ese orden)
        enviarInformacion( out1, jugador1.darRegistroJugador( ) );
        enviarInformacion( out1, jugador2.darRegistroJugador( ) );

        enviarInformacion( out2, jugador2.darRegistroJugador( ) );
        enviarInformacion( out2, jugador1.darRegistroJugador( ) );

        // Enviar a cada jugador la información sobre en qué orden deben jugar: siempre empieza el jugador 1
        out1.println( PRIMER_TURNO );
        out2.println( SEGUNDO_TURNO );
    }

    /**
     * Realiza las actividades necesarias para terminar un encuentro: <br>
     * 1. Actualiza los registros de los jugadores en la base de datos <br>
     * 2. Envía un mensaje a los jugadores advirtiendo sobre el fin del juego y el nombre del ganador <br>
     * 3. Cierra las conexiones a los jugadores
     * @throws BatallaNavalException Se lanza esta excepción si hay problemas con el acceso a la base de datos
     * @throws IOException Se lanza esta excepción si hay problemas en la comunicación
     */
    private void terminarEncuentro( ) throws BatallaNavalException, IOException
    {
        // Actualizar el registro de los jugadores
        RegistroJugador ganador = null;
        RegistroJugador perdedor = null;
        if( jugador1.darPuntosPartida( ) > jugador2.darPuntosPartida( ) )
        {
            ganador = jugador1.darRegistroJugador( );
            perdedor = jugador2.darRegistroJugador( );
        }
        else
        {
            ganador = jugador2.darRegistroJugador( );
            perdedor = jugador1.darRegistroJugador( );
        }
        try
        {
            adminResultados.registrarVictoria( ganador.darNombreJugador( ) );
            adminResultados.registrarDerrota( perdedor.darNombreJugador( ) );
        }
        catch( SQLException e )
        {
            throw new BatallaNavalException( "Error actualizando la información en la base de datos: " + e.getMessage( ) );
        }
        // Enviar un mensaje indicando el fin del juego y el ganador
        String cadenaGanador = GANADOR + ":" + ganador.darNombreJugador( );
        out1.println( cadenaGanador );
        out2.println( cadenaGanador );

        // Cerrar los canales de los jugadores
        in1.close( );
        out1.close( );
        out2.close( );
        in2.close( );
        socketJugador1.close( );
        socketJugador2.close( );
    }

    /**
     * Este método se encarga de procesar una jugada completa del juego, recibiendo y enviando los mensajes, y además actualizando el puntaje del juego <br>
     * Si con esta jugada el encuentro debe terminar, entonces el atributo encuentroTerminado del encuentro se convierte en true
     * @param atacante El número del jugador que tiene el turno de atacar - atacante = 1 o atacante = 2
     * @throws BatallaNavalException Se lanza esta excepción si hay problemas con la información que llega
     * @throws IOException Se lanza esta excepción si hay problemas en la comunicación
     */
    private void procesarJugada( int atacante ) throws IOException, BatallaNavalException
    {
        PrintWriter atacanteOut = ( atacante == 1 ) ? out1 : out2;
        PrintWriter atacadoOut = ( atacante == 1 ) ? out2 : out1;

        BufferedReader atacanteIn = ( atacante == 1 ) ? in1 : in2;
        BufferedReader atacadoIn = ( atacante == 1 ) ? in2 : in1;

        // Leer la jugada del atacante que indica donde se va a hacer el ataque
        String lineaAtaque = atacanteIn.readLine( );

        if(lineaAtaque != null)
        {
        	if( !lineaAtaque.startsWith( JUGADA ) )
        		throw new BatallaNavalException( "Se esperaba una JUGADA pero se recibió " + lineaAtaque );
        	
        	// Reenviar el ataque al jugador atacado
        	atacadoOut.println( lineaAtaque );
        	
        	// Leer la información sobre el resultado del ataque que envía el jugador atacado
        	String lineaResultado = atacadoIn.readLine( );
        	
        	if( !lineaResultado.startsWith( AGUA ) && !lineaResultado.startsWith( IMPACTO ) && !lineaResultado.startsWith( FIN_JUEGO ) )
        		throw new BatallaNavalException( "Se esperaba el resultado de una JUGADA pero se recibió " + lineaResultado );
        	
        	// Revisar el resultado para saber si el encuentro termina y actualizar los puntajes
        	if( lineaResultado.startsWith( IMPACTO ) )
        	{
        		JugadorRemoto jugadorAtacante = ( atacante == 1 ) ? jugador1 : jugador2;
        		jugadorAtacante.aumentarPuntosEncuentro( 1 );
        	}
        	else if( lineaResultado.startsWith( FIN_JUEGO ) )
        	{
        		JugadorRemoto jugadorAtacante = ( atacante == 1 ) ? jugador1 : jugador2;
        		jugadorAtacante.aumentarPuntosEncuentro( 20 );
        		
        		finJuego = true;
        	}
        	
        	// Enviar el resultado del disparo al jugador atacante
        	atacanteOut.println( lineaResultado );
        }
        else
        	throw new BatallaNavalException( "Se esperaba una JUGADA pero se recibió una cadena nula" );
    }

    /**
     * Retorna una cadena con la información del encuentro con el siguiente formato:<br>
     * <jugador1> ( <puntos> ) - <jugador2> ( <puntos> )
     * @return cadena
     */
    public String toString( )
    {
        RegistroJugador j1 = jugador1.darRegistroJugador( );
        RegistroJugador j2 = jugador2.darRegistroJugador( );

        String cadena = j1.darNombreJugador( ) + "( " + jugador1.darPuntosPartida( ) + " ) - " + j2.darNombreJugador( ) + "( " + jugador2.darPuntosPartida( ) + " )";
        return cadena;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase
     * <b>inv:</b><br>
     * !finJuego => socketJugador1 != null <br>
     * !finJuego => out1 != null <br>
     * !finJuego => in1 != null <br>
     * !finJuego => socketJugador2 != null <br>
     * !finJuego => out2 != null <br>
     * !finJuego => in2 != null <br>
     * jugador1 != null <br>
     * jugador2 != null <br>
     */
    private void verificarInvariante( )
    {
        if( !finJuego )
        {
            assert socketJugador1 != null : "Canal inválido";
            assert out1 != null : "Flujo inválido";
            assert in1 != null : "Flujo inválido";
            assert socketJugador2 != null : "Canal inválido";
            assert out2 != null : "Flujo inválido";
            assert in2 != null : "Flujo inválido";
        }
        
        assert jugador1 != null : "Jugador nulo";
        assert jugador2 != null : "Jugador nulo";
    }
}
