/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Jugador.java 644 2006-11-14 20:16:26Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 21-feb-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.cliente;

import java.io.*;
import java.net.*;
import java.util.*;

import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta es la clase que se encarga de actualizar los tableros, manejar las comunicaciones con el servidor y generar los mensajes con la información del juego para el jugador.<br>
 * Esta clase conoce la parte fija de los mensajes del protocolo de comunicación.<br>
 * <b>inv:</b><br>
 * estadoJuego pertenece a {SIN_CONECTAR, ESPERANDO_LOCAL, ESPERANDO_OPONENTE, ESPERANDO_RESPUESTA}<br>
 * estadoJuego = SIN_CONECTAR => juegoTerminado = true<br>
 * estadoJuego != SIN_CONECTAR => canal != null<br>
 * estadoJuego != SIN_CONECTAR => out != null<br>
 * estadoJuego != SIN_CONECTAR => in != null<br>
 * estadoJuego != SIN_CONECTAR => tableroFlota != null<br>
 * estadoJuego != SIN_CONECTAR => tableroAtaque != null<br>
 * estadoJuego != SIN_CONECTAR => servidor != null<br>
 * estadoJuego != SIN_CONECTAR => mensajesSinLeer != null<br>
 * estadoJuego != SIN_CONECTAR => nombreJugador != null <br>
 * estadoJuego != SIN_CONECTAR => puerto > 0
 */
public class Jugador
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que no se ha establecido la conexión con el servidor para jugar
     */
    public static final int SIN_CONECTAR = 0;

    /**
     * Indica que se está esperando que el jugador local realice una jugada
     */
    public static final int ESPERANDO_LOCAL = 1;

    /**
     * Indica que se está esperando a que el oponente realice una jugada
     */
    public static final int ESPERANDO_OPONENTE = 2;

    /**
     * Indica que se acaba de enviar la jugada del jugador local y se está esperando la respuesta del cliente
     */
    public static final int ESPERANDO_RESPUESTA = 3;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Este es el objeto que contiene el tablero con la flota del jugador
     */
    private TableroFlota tableroFlota;

    /**
     * Este es el objeto que contiene el tablero de ataque del jugador
     */
    private Tablero tableroAtaque;

    /**
     * Indica el estado actual del juego
     */
    private int estadoJuego;

    /**
     * Es un indicador para saber si un juego ya terminó
     */
    private boolean juegoTerminado;

    /**
     * El nombre del jugador local
     */
    private String nombreJugador;

    /**
     * La última dirección de servidor al que se conectó
     */
    private String servidor;

    /**
     * El último puerto usado para conectarse
     */
    private int puerto;

    /**
     * Es el nombre del ganador del último encuentro
     */
    private String nombreGanador;

    /**
     * Es el canal usado para comunicarse con el servidor
     */
    private Socket canal;

    /**
     * El flujo que envía los datos al servidor a través del socketServidor
     */
    private PrintWriter out;

    /**
     * Es el flujo de donde se leen los datos que llegan del servidor a través del socketServidor
     */
    private BufferedReader in;

    /**
     * Es una colección de mensajes que aún deben ser mostrados al jugador
     */
    private Collection mensajesSinLeer;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el juego de Batalla Naval
     */
    public Jugador( )
    {
        tableroFlota = null;
        tableroAtaque = null;
        mensajesSinLeer = null;
        nombreJugador = "Almirante Nelson";
        servidor = "localhost";
        puerto = 9999;
        estadoJuego = SIN_CONECTAR;
        juegoTerminado = true;
        nombreGanador = "";
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del jugador
     * @return nombreJugador
     */
    public String darNombreJugador( )
    {
        return nombreJugador;
    }

    /**
     * Retorna la dirección del servidor
     * @return servidor
     */
    public String darDireccionServidor( )
    {
        return servidor;
    }

    /**
     * Retorna el puerto usado para conectarse al servidor
     * @return puerto
     */
    public int darPuertoServidor( )
    {
        return puerto;
    }

    /**
     * Retorna el tablero con la flota del jugador
     * @return el tablero con la flota del jugador
     */
    public TableroFlota darTableroJuego( )
    {
        return tableroFlota;
    }

    /**
     * Retorna el tablero de ataque del jugador
     * @return el tablero de ataque del jugador
     */
    public Tablero darTableroAtaque( )
    {
        return tableroAtaque;
    }

    /**
     * Retorna el nombre del jugador que ganó el último encuentro
     * @return nombreGanador
     */
    public String darNombreGanador( )
    {
        return nombreGanador;
    }

    /**
     * Retorna una colección de mensajes que no han sido vistos por el jugador y limpia la lista de mensajes pendientes
     * @return mensajesSinLeer
     */
    public Collection darMensajesSinLeer( )
    {
        Collection temp = mensajesSinLeer;
        mensajesSinLeer = new LinkedList( );
        return temp;
    }

    /**
     * Retorna el estado actual del juego
     * @return estadoJuego
     */
    public int darEstadoJuego( )
    {
        return estadoJuego;
    }

    /**
     * Indica si el último encuentro jugado ya terminó.
     * @return juegoTerminado
     */
    public boolean juegoTerminado( )
    {
        return juegoTerminado;
    }

    /**
     * Establece una conexión con el servidor del juego y envía los datos del jugador para poder empezar un juego<br>
     * Este método termina cuando se consigue un oponente y se establece la conexión entre los dos jugadores.
     * @param nom El nombre del jugador local - nombre != null
     * @param dirServ La dirección usada para encontrar el servidor - direccionServidor != null
     * @param puertoServ El puerto usado para realizar la conexión - puertoServidor > 0
     * @throws BatallaNavalException Se lanza esta excepción si hay problemas estableciendo la comunicación
     */
    public void conectar( String nom, String dirServ, int puertoServ ) throws BatallaNavalException
    {
        nombreJugador = nom;
        servidor = dirServ;
        puerto = puertoServ;
        try
        {
            // Conectar al servidor
            canal = new Socket( dirServ, puertoServ );
            out = new PrintWriter( canal.getOutputStream( ), true );
            in = new BufferedReader( new InputStreamReader( canal.getInputStream( ) ) );
            // iniciar el encuentro
            iniciarEncuentro( );
        }
        catch( UnknownHostException e )
        {
            e.printStackTrace( );
            throw new BatallaNavalException( "No fue posible establecer una conexión al servidor. " + e.getMessage( ) );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
            throw new BatallaNavalException( "No fue posible establecer una conexión al servidor. " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    /**
     * Envía al servidor los mensajes necesarios para iniciar un encuentro y recibe la información del oponente y del turno
     * @throws IOException Se lanza esta excepción si hay un problema leyendo del canal
     */
    private void iniciarEncuentro( ) throws IOException
    {
        juegoTerminado = false;
        nombreGanador = "";

        // Preparar el tablero para el nuevo encuentro
        tableroFlota = new TableroFlota( );
        tableroAtaque = new Tablero( );
        tableroFlota.inicializarBarcosTablero( );

        // Inicializa la lista de mensajes
        mensajesSinLeer = new LinkedList( );

        // Enviar el nombre del jugador
        out.println( Encuentro.JUGADOR + ":" + nombreJugador );

        // Leer la información del jugador
        // INFO:<nombre>:<ganados>:<perdidos>
        String[] datosJugador = in.readLine( ).split( ":" );
        RegistroJugador regJugador = new RegistroJugador( datosJugador[ 1 ], Integer.parseInt( datosJugador[ 2 ] ), Integer.parseInt( datosJugador[ 3 ] ) );

        // Leer la información del oponente
        // INFO:<nombre>:<ganados>:<perdidos>
        String[] datosOponente = in.readLine( ).split( ":" );
        RegistroJugador regOponente = new RegistroJugador( datosOponente[ 1 ], Integer.parseInt( datosOponente[ 2 ] ), Integer.parseInt( datosOponente[ 3 ] ) );

        mensajesSinLeer.add( "Iniciando encuentro: " + regJugador.darNombreJugador( ) + " vs. " + regOponente.darNombreJugador( ) );
        mensajesSinLeer.add( regJugador.toString( ) );
        mensajesSinLeer.add( regOponente.toString( ) );

        // Leer la información del turno
        // 1 | 2
        String turno = in.readLine( );

        if( Encuentro.PRIMER_TURNO.equals( turno ) )
        {
            estadoJuego = ESPERANDO_LOCAL;
        }
        else
        {
            estadoJuego = ESPERANDO_OPONENTE;
        }
    }

    /**
     * Este método se encarga de esperar una jugada que envíe el otro jugador, actualizar el tablero y enviar la respuesta al servidor. <br>
     * Si el juego termina, este método debe cambiar el valor de juegoTerminado<br>
     * <b>pre:</b>estadoJugada = ESPERANDO_JUGADA_OPONENTE
     * @throws BatallaNavalException Se lanza esta excepción si hay problemas en la comunicación
     */
    public void esperarJugada( ) throws BatallaNavalException
    {
        try
        {
            // JUGADA:<fila>:<columna>
            String datosJugada[] = in.readLine( ).split( ":" );
            int fila = Integer.parseInt( datosJugada[ 1 ] );
            int columna = Integer.parseInt( datosJugada[ 2 ] );

            // Registrar el ataque a la casilla
            int resultadoAtaque = tableroFlota.atacarCasilla( fila, columna );
            if( resultadoAtaque == TableroFlota.RESULTADO_AGUA )
            {
                mensajesSinLeer.add( "El disparo del oponente terminó en el agua" );
                out.println( Encuentro.AGUA );
            }
            else if( resultadoAtaque == TableroFlota.RESULTADO_IMPACTO )
            {
                Barco barcoAtacado = tableroFlota.localizarBarco( fila, columna );
                mensajesSinLeer.add( "Malas Noticias: nos dieron en el " + barcoAtacado.darTipoBarco( ) + " y le quedan sólo " + barcoAtacado.darPuntos( ) + " puntos" );
                out.println( Encuentro.IMPACTO + ":" + barcoAtacado.darTipoBarco( ) + ":false" );
            }
            else if( resultadoAtaque == TableroFlota.RESULTADO_HUNDIMIENTO )
            {
                Barco barcoAtacado = tableroFlota.localizarBarco( fila, columna );

                if( !tableroFlota.hayBarcos( ) )
                {
                    // El encuentro terminó porque se acabaron los barcos
                    juegoTerminado = true;
                    mensajesSinLeer.add( "¡Nos han derrotado!" );
                    out.println( Encuentro.FIN_JUEGO );
                }
                else
                {
                    mensajesSinLeer.add( "Malas Noticias: el barco " + barcoAtacado.darTipoBarco( ) + " fue hundido!!!" );
                    out.println( Encuentro.IMPACTO + ":" + barcoAtacado.darTipoBarco( ) + ":true" );
                }
            }
            estadoJuego = ESPERANDO_LOCAL;
        }
        catch( IOException e )
        {
            throw new BatallaNavalException( "Se presentaron problemas con la conexión al servidor. " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    /**
     * Este método se encarga de realizar un ataque y procesar la respuesta del oponente <br>
     * <b>pre:</b>estadoJugada = ESPERANDO_JUGADA_LOCAL
     * @param fila La fila de la posición atacada - 0 <= fila < TablerosJuego.NUMERO_FILAS
     * @param columna La columna de la posición atacada - 0 <= columna < TablerosJuego.NUMERO_COLUMNAS
     * @throws BatallaNavalException Se lanza esta excepción si hay problemas en la comunicación
     */
    public void enviarJugada( int fila, int columna ) throws BatallaNavalException
    {
        try
        {
            // Enviar el mensaje
            out.println( Encuentro.JUGADA + ":" + fila + ":" + columna );
            estadoJuego = ESPERANDO_RESPUESTA;

            // Leer la respuesta enviada por el oponente
            String respuesta = in.readLine( );

            if( respuesta.startsWith( Encuentro.AGUA ) )
            {
                // AGUA
                tableroAtaque.marcarCasilla( fila, columna, Casilla.ATACADA );
                mensajesSinLeer.add( "Hemos fallado, capitán!" );
            }
            else if( respuesta.startsWith( Encuentro.IMPACTO ) )
            {
                // IMPACTO:<tipo barco>:<hundido>
                tableroAtaque.marcarCasilla( fila, columna, Casilla.IMPACTADA );
                String datosDisparo[] = respuesta.split( ":" );

                if( datosDisparo[ 2 ].equals( "true" ) )
                    mensajesSinLeer.add( "¡Excelente! El barco " + datosDisparo[ 1 ] + " fue hundido!" );
                else
                    mensajesSinLeer.add( "¡Buen disparo! Le dimos al " + datosDisparo[ 1 ] );
            }
            else if( respuesta.startsWith( Encuentro.FIN_JUEGO ) )
            {
                // FIN_JUEGO
                mensajesSinLeer.add( "¡Victoria! El último barco enemigo fue hundido!" );
                juegoTerminado = true;
            }
            estadoJuego = ESPERANDO_OPONENTE;
        }
        catch( IOException e )
        {
            throw new BatallaNavalException( "Se presentaron problemas con la conexión al servidor. " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    /**
     * Realiza las tareas necesarias para terminar el encuentro<br>
     * Se averigua el nombre del ganador, la conexión con el servidor se cierra y el estado del juego pasa a SIN_CONECTAR<br>
     * <b>pre:</b>juegoTerminado = true
     * @throws BatallaNavalException Se lanza esta excepción si hay problemas en la comunicación
     */
    public void terminarEncuentro( ) throws BatallaNavalException
    {
        try
        {
            // Leer el mensaje con el nombre del ganador
            // GANADOR:<nombre>
            String mensajeFin = in.readLine( );
            nombreGanador = mensajeFin.split( ":" )[ 1 ];
            estadoJuego = SIN_CONECTAR;

            // Cerrar la conexión al servidor
            out.close( );
            in.close( );
            canal.close( );

            out = null;
            in = null;
            canal = null;
        }
        catch( IOException e )
        {
            throw new BatallaNavalException( "Se presentaron problemas con la conexión al servidor. " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv</b><br>
     * estadoJuego pertenece a {SIN_CONECTAR, ESPERANDO_LOCAL, ESPERANDO_OPONENTE, ESPERANDO_RESPUESTA}<br>
     * estadoJuego = SIN_CONECTAR => juegoTerminado = true<br>
     * estadoJuego != SIN_CONECTAR => canal != null<br>
     * estadoJuego != SIN_CONECTAR => out != null<br>
     * estadoJuego != SIN_CONECTAR => in != null<br>
     * estadoJuego != SIN_CONECTAR => tableroFlota != null<br>
     * estadoJuego != SIN_CONECTAR => tableroAtaque != null<br>
     * estadoJuego != SIN_CONECTAR => servidor != null<br>
     * estadoJuego != SIN_CONECTAR => mensajesSinLeer != null<br>
     * estadoJuego != SIN_CONECTAR => nombreJugador != null <br>
     * estadoJuego != SIN_CONECTAR => puerto > 0
     */
    private void verificarInvariante( )
    {
        assert ( estadoJuego == SIN_CONECTAR || estadoJuego == ESPERANDO_LOCAL || estadoJuego == ESPERANDO_OPONENTE || estadoJuego == ESPERANDO_RESPUESTA ) : "El estado no es válido";
        if( estadoJuego == SIN_CONECTAR )
            assert juegoTerminado : "Valor inválido de atributo juegoTerminado";
        else
        {
            assert ( canal == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexión";
            assert ( out == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexión";
            assert ( in == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexión";
            assert ( tableroFlota != null ) : "El tablero de juego no puede ser null";
            assert ( tableroAtaque != null ) : "El tablero de ataque no puede ser null";
            assert ( servidor != null ) : "La dirección del servidor no puede ser null";
            assert ( mensajesSinLeer != null ) : "La lista de mensajes no puede ser null";
            assert ( nombreJugador != null ) : "El nombre del jugador no puede ser null";
            assert ( puerto > 0 ) : "El puerto debe ser mayor a 0";
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}