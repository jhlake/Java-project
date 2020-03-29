/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ThreadEsperarJugada.java 644 2006-11-14 20:16:26Z da-romer $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 
 * Ejercicio: n12_batallaNaval 
 * Autor: Mario S�nchez - 27/02/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.batallaNaval.cliente;

import uniandes.cupi2.batallaNaval.interfazCliente.*;

/**
 * Esta clase implementa lo que se debe hacer en un hilo de ejecuci�n cuando se quiere esperar la jugada del oponente.
 */
public class ThreadEsperarJugada extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la referencia al juego
     */
    private Jugador jugador;

    /**
     * Es la referencia a la ventana principal de la aplicaci�n
     */
    private InterfazJugador principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para esperar la jugada
     * @param juego Es una referencia al juego - juego != null
     * @param interfaz Es una referencia a la ventana principal de la aplicaci�n - interfaz != null
     */
    public ThreadEsperarJugada( Jugador juego, InterfazJugador interfaz )
    {
        super( );
        jugador = juego;
        principal = interfaz;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que espera la jugada del oponente. <br>
     * Cuando se tiene informaci�n sobre el disparo del oponente entonces se actualiza la interfaz.<br>
     * Si el juego debe terminar entonces muestra quien fue el ganador y termina el encuentro y la conexi�n al servidor.
     */
    public void run( )
    {
        try
        {
            jugador.esperarJugada( );
            principal.actualizarInterfaz( );

            if( jugador.juegoTerminado( ) )
            {
                jugador.terminarEncuentro( );
                principal.actualizarInterfaz( );
                principal.mostrarGanador( );
            }
        }
        catch( BatallaNavalException e )
        {
            e.printStackTrace( );
        }
    }
}
