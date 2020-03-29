/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ThreadEnviarJugada.java 640 2006-11-14 06:06:59Z da-romer $ 
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
 * Esta clase implementa lo que se debe hacer en un hilo de ejecuci�n cuando se quiere enviar una jugada
 */
public class ThreadEnviarJugada extends Thread
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

    /**
     * Es la fila de la casilla donde se va a realizar el disparo
     */
    private int fila;

    /**
     * Es la columna de la casilla donde se va a realizar el disparo
     */
    private int columna;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constuye el nuevo hilo y lo deja listo para enviar la jugada
     * @param juego Es una referencia al juego - juego != null
     * @param interfaz Es una referencia a la ventana principal de la aplicaci�n - interfaz != null
     * @param filaJugada Es la fila de la casilla donde se va a realizar el disparo - 0 <= filaJugada < TablerosJuego.NUMERO_FILAS
     * @param columnaJugada Es la columna de la casilla donde se va a realizar el disparo - - 0 <= columnaJugada < TablerosJuego.NUMERO_COLUMNAS
     */
    public ThreadEnviarJugada( Jugador juego, InterfazJugador interfaz, int filaJugada, int columnaJugada )
    {
        super( );

        jugador = juego;
        principal = interfaz;
        fila = filaJugada;
        columna = columnaJugada;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que realiza el env�o del mensaje y espera la respuesta. <br>
     * Cuando se tiene informaci�n sobre el resultado de la jugada entonces se actualiza la interfaz.<br>
     * Si el juego debe terminar entonces muestra quien fue el ganador y termina el encuentro y la conexi�n al servidor.
     */
    public void run( )
    {
        try
        {
            jugador.enviarJugada( fila, columna );
            principal.actualizarInterfaz( );

            if( jugador.juegoTerminado( ) )
            {
                jugador.terminarEncuentro( );
                principal.actualizarInterfaz( );
                principal.mostrarGanador( );
            }
            else
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
