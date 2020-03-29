/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ThreadConectar.java 640 2006-11-14 06:06:59Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 
 * Ejercicio: n12_batallaNaval 
 * Autor: Mario Sánchez - 27/02/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.batallaNaval.cliente;

import uniandes.cupi2.batallaNaval.interfazCliente.*;

/**
 * Esta clase implementa lo que se debe hacer en un hilo de ejecución cuando se quiere conectar al cliente con el servidor.
 */
public class ThreadConectar extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la referencia al juego
     */
    private Jugador jugador;

    /**
     * Es la referencia a la ventana principal de la aplicación
     */
    private InterfazJugador principal;

    /**
     * El nombre que utilizará el jugador
     */
    private String nombre;

    /**
     * La dirección para localizar al servidor
     */
    private String servidor;

    /**
     * El puerto a través del cual se realizará la conexión con el servidor
     */
    private int puerto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para conectarse al servidor
     * @param juego Es una referencia al juego - juego != null
     * @param interfaz Es una referencia a la ventana principal de la aplicación - interfaz != null
     * @param nombreJugador El nombre que utilizará el jugador - nombreJugador != null
     * @param direccionServidor La dirección para localizar al servidor - direccionServidor != null
     * @param puertoServidor El puerto a través del cual se realizará la conexión con el servidor - puertoServidor != null
     */
    public ThreadConectar( Jugador juego, InterfazJugador interfaz, String nombreJugador, String direccionServidor, int puertoServidor )
    {
        jugador = juego;
        principal = interfaz;
        nombre = nombreJugador;
        servidor = direccionServidor;
        puerto = puertoServidor;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que realiza la conexión con el servidor e incializa el tablero.<br>
     * Cuando se tiene la conexión y la información del tablero entonces se actualiza la interfaz.
     */
    public void run( )
    {
        try
        {
            jugador.conectar( nombre, servidor, puerto );

            principal.actualizarInterfaz( );

            if( jugador.darEstadoJuego( ) == Jugador.ESPERANDO_OPONENTE )
            {
                principal.esperarJugada( );
            }
        }
        catch( BatallaNavalException e )
        {
            e.printStackTrace( );
        }

    }
}
