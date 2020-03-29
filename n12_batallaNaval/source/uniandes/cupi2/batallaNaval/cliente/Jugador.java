/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Jugador.java 644 2006-11-14 20:16:26Z da-romer $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 21-feb-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.cliente;

import java.io.*;
import java.net.*;
import java.util.*;

import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta es la clase que se encarga de actualizar los tableros, manejar las comunicaciones con el servidor y generar los mensajes con la informaci�n del juego para el jugador.<br>
 * Esta clase conoce la parte fija de los mensajes del protocolo de comunicaci�n.<br>
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
     * Indica que no se ha establecido la conexi�n con el servidor para jugar
     */
    public static final int SIN_CONECTAR = 0;

    /**
     * Indica que se est� esperando que el jugador local realice una jugada
     */
    public static final int ESPERANDO_LOCAL = 1;

    /**
     * Indica que se est� esperando a que el oponente realice una jugada
     */
    public static final int ESPERANDO_OPONENTE = 2;

    /**
     * Indica que se acaba de enviar la jugada del jugador local y se est� esperando la respuesta del cliente
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
     * Es un indicador para saber si un juego ya termin�
     */
    private boolean juegoTerminado;

    /**
     * El nombre del jugador local
     */
    private String nombreJugador;

    /**
     * La �ltima direcci�n de servidor al que se conect�
     */
    private String servidor;

    /**
     * El �ltimo puerto usado para conectarse
     */
    private int puerto;

    /**
     * Es el nombre del ganador del �ltimo encuentro
     */
    private String nombreGanador;

    /**
     * Es el canal usado para comunicarse con el servidor
     */
    private Socket canal;

    /**
     * El flujo que env�a los datos al servidor a trav�s del socketServidor
     */
    private PrintWriter out;

    /**
     * Es el flujo de donde se leen los datos que llegan del servidor a trav�s del socketServidor
     */
    private BufferedReader in;

    /**
     * Es una colecci�n de mensajes que a�n deben ser mostrados al jugador
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
    // M�todos
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
     * Retorna la direcci�n del servidor
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
     * Retorna el nombre del jugador que gan� el �ltimo encuentro
     * @return nombreGanador
     */
    public String darNombreGanador( )
    {
        return nombreGanador;
    }

    /**
     * Retorna una colecci�n de mensajes que no han sido vistos por el jugador y limpia la lista de mensajes pendientes
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
     * Indica si el �ltimo encuentro jugado ya termin�.
     * @return juegoTerminado
     */
    public boolean juegoTerminado( )
    {
        return juegoTerminado;
    }

    /**
     * Establece una conexi�n con el servidor del juego y env�a los datos del jugador para poder empezar un juego<br>
     * Este m�todo termina cuando se consigue un oponente y se establece la conexi�n entre los dos jugadores.
     * @param nom El nombre del jugador local - nombre != null
     * @param dirServ La direcci�n usada para encontrar el servidor - direccionServidor != null
     * @param puertoServ El puerto usado para realizar la conexi�n - puertoServidor > 0
     * @throws BatallaNavalException Se lanza esta excepci�n si hay problemas estableciendo la comunicaci�n
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
            throw new BatallaNavalException( "No fue posible establecer una conexi�n al servidor. " + e.getMessage( ) );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
            throw new BatallaNavalException( "No fue posible establecer una conexi�n al servidor. " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    /**
     * Env�a al servidor los mensajes necesarios para iniciar un encuentro y recibe la informaci�n del oponente y del turno
     * @throws IOException Se lanza esta excepci�n si hay un problema leyendo del canal
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

        // Leer la informaci�n del jugador
        // INFO:<nombre>:<ganados>:<perdidos>
        String[] datosJugador = in.readLine( ).split( ":" );
        RegistroJugador regJugador = new RegistroJugador( datosJugador[ 1 ], Integer.parseInt( datosJugador[ 2 ] ), Integer.parseInt( datosJugador[ 3 ] ) );

        // Leer la informaci�n del oponente
        // INFO:<nombre>:<ganados>:<perdidos>
        String[] datosOponente = in.readLine( ).split( ":" );
        RegistroJugador regOponente = new RegistroJugador( datosOponente[ 1 ], Integer.parseInt( datosOponente[ 2 ] ), Integer.parseInt( datosOponente[ 3 ] ) );

        mensajesSinLeer.add( "Iniciando encuentro: " + regJugador.darNombreJugador( ) + " vs. " + regOponente.darNombreJugador( ) );
        mensajesSinLeer.add( regJugador.toString( ) );
        mensajesSinLeer.add( regOponente.toString( ) );

        // Leer la informaci�n del turno
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
     * Este m�todo se encarga de esperar una jugada que env�e el otro jugador, actualizar el tablero y enviar la respuesta al servidor. <br>
     * Si el juego termina, este m�todo debe cambiar el valor de juegoTerminado<br>
     * <b>pre:</b>estadoJugada = ESPERANDO_JUGADA_OPONENTE
     * @throws BatallaNavalException Se lanza esta excepci�n si hay problemas en la comunicaci�n
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
                mensajesSinLeer.add( "El disparo del oponente termin� en el agua" );
                out.println( Encuentro.AGUA );
            }
            else if( resultadoAtaque == TableroFlota.RESULTADO_IMPACTO )
            {
                Barco barcoAtacado = tableroFlota.localizarBarco( fila, columna );
                mensajesSinLeer.add( "Malas Noticias: nos dieron en el " + barcoAtacado.darTipoBarco( ) + " y le quedan s�lo " + barcoAtacado.darPuntos( ) + " puntos" );
                out.println( Encuentro.IMPACTO + ":" + barcoAtacado.darTipoBarco( ) + ":false" );
            }
            else if( resultadoAtaque == TableroFlota.RESULTADO_HUNDIMIENTO )
            {
                Barco barcoAtacado = tableroFlota.localizarBarco( fila, columna );

                if( !tableroFlota.hayBarcos( ) )
                {
                    // El encuentro termin� porque se acabaron los barcos
                    juegoTerminado = true;
                    mensajesSinLeer.add( "�Nos han derrotado!" );
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
            throw new BatallaNavalException( "Se presentaron problemas con la conexi�n al servidor. " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    /**
     * Este m�todo se encarga de realizar un ataque y procesar la respuesta del oponente <br>
     * <b>pre:</b>estadoJugada = ESPERANDO_JUGADA_LOCAL
     * @param fila La fila de la posici�n atacada - 0 <= fila < TablerosJuego.NUMERO_FILAS
     * @param columna La columna de la posici�n atacada - 0 <= columna < TablerosJuego.NUMERO_COLUMNAS
     * @throws BatallaNavalException Se lanza esta excepci�n si hay problemas en la comunicaci�n
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
                mensajesSinLeer.add( "Hemos fallado, capit�n!" );
            }
            else if( respuesta.startsWith( Encuentro.IMPACTO ) )
            {
                // IMPACTO:<tipo barco>:<hundido>
                tableroAtaque.marcarCasilla( fila, columna, Casilla.IMPACTADA );
                String datosDisparo[] = respuesta.split( ":" );

                if( datosDisparo[ 2 ].equals( "true" ) )
                    mensajesSinLeer.add( "�Excelente! El barco " + datosDisparo[ 1 ] + " fue hundido!" );
                else
                    mensajesSinLeer.add( "�Buen disparo! Le dimos al " + datosDisparo[ 1 ] );
            }
            else if( respuesta.startsWith( Encuentro.FIN_JUEGO ) )
            {
                // FIN_JUEGO
                mensajesSinLeer.add( "�Victoria! El �ltimo barco enemigo fue hundido!" );
                juegoTerminado = true;
            }
            estadoJuego = ESPERANDO_OPONENTE;
        }
        catch( IOException e )
        {
            throw new BatallaNavalException( "Se presentaron problemas con la conexi�n al servidor. " + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    /**
     * Realiza las tareas necesarias para terminar el encuentro<br>
     * Se averigua el nombre del ganador, la conexi�n con el servidor se cierra y el estado del juego pasa a SIN_CONECTAR<br>
     * <b>pre:</b>juegoTerminado = true
     * @throws BatallaNavalException Se lanza esta excepci�n si hay problemas en la comunicaci�n
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

            // Cerrar la conexi�n al servidor
            out.close( );
            in.close( );
            canal.close( );

            out = null;
            in = null;
            canal = null;
        }
        catch( IOException e )
        {
            throw new BatallaNavalException( "Se presentaron problemas con la conexi�n al servidor. " + e.getMessage( ) );
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
        assert ( estadoJuego == SIN_CONECTAR || estadoJuego == ESPERANDO_LOCAL || estadoJuego == ESPERANDO_OPONENTE || estadoJuego == ESPERANDO_RESPUESTA ) : "El estado no es v�lido";
        if( estadoJuego == SIN_CONECTAR )
            assert juegoTerminado : "Valor inv�lido de atributo juegoTerminado";
        else
        {
            assert ( canal == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexi�n";
            assert ( out == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexi�n";
            assert ( in == null ) : "Si el estado es SIN_CONECTAR, entonces no hay conexi�n";
            assert ( tableroFlota != null ) : "El tablero de juego no puede ser null";
            assert ( tableroAtaque != null ) : "El tablero de ataque no puede ser null";
            assert ( servidor != null ) : "La direcci�n del servidor no puede ser null";
            assert ( mensajesSinLeer != null ) : "La lista de mensajes no puede ser null";
            assert ( nombreJugador != null ) : "El nombre del jugador no puede ser null";
            assert ( puerto > 0 ) : "El puerto debe ser mayor a 0";
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}