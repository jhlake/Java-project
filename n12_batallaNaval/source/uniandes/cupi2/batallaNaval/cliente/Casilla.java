/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Casilla.java 644 2006-11-14 20:16:26Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 24/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.cliente;

/**
 * Es una casilla dentro del tablero de juego<br>
 * <b>inv:</b><br>
 * estadoCasilla pertenece a { OCUPADA, IMPACTADA, VACIA, ATACADA }
 */
public class Casilla
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que la casilla está vacía
     */
    public static final int VACIA = 0;

    /**
     * Indica que la casilla está ocupada pero no se han realizado disparos ahí
     */
    public static final int OCUPADA = 1;

    /**
     * Indica que la casilla está vacía, y ya fue atacada
     */
    public static final int ATACADA = 2;

    /**
     * Indica que la casilla fue atacada y un barco que se encuentra allí fue impactado
     */
    public static final int IMPACTADA = 3;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el estado actual de la casilla
     */
    private int estadoCasilla;

    /**
     * Es un barco que ocupa la casilla. Si la casilla está vacía, entonces es null.
     */
    private Barco barcoCasilla;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva casilla vacía
     */
    public Casilla( )
    {
        estadoCasilla = VACIA;
        barcoCasilla = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el estado actual de la casilla
     * @return estadoCasilla
     */
    public int darEstado( )
    {
        return estadoCasilla;
    }

    /**
     * Registra un ataque a la casilla. <br>
     * Si la casilla estaba VACIA, entonces ahora queda ATACADA. <br>
     * Si la casilla estaba OCUPADA, entonces queda IMPACTADA y el disparo se registra sobre el barco.
     */
    public void atacarCasilla( )
    {
        if( estadoCasilla == VACIA )
        {
            estadoCasilla = ATACADA;
        }
        else if( estadoCasilla == OCUPADA )
        {
            estadoCasilla = IMPACTADA;
            barcoCasilla.atacar( );
        }
        verificarInvariante( );
    }

    /**
     * Cambia el estado de la casilla. <br>
     * Este método es usado para manipular el tablero de ataque.
     * @param nuevoEstado El nuevo estado de la casilla - nuevoEstado está en {VACIA, OCUPADA, ATACADA, IMPACTADA}
     */
    public void marcar( int nuevoEstado )
    {
        estadoCasilla = nuevoEstado;
        verificarInvariante( );
    }

    /**
     * Establece cuál es el barco que se encuentra en la casilla
     * @param barco Es el barco que va a quedar en la casilla
     */
    public void cambiarBarco( Barco barco )
    {
        barcoCasilla = barco;
        estadoCasilla = barcoCasilla != null ? OCUPADA : VACIA;
        verificarInvariante( );
    }

    /**
     * Retorna el barco que se encuentra en la casilla
     * @return El barco que se encuentra en la casilla
     */
    public Barco darBarco( )
    {
        return barcoCasilla;
    }
    
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv:</b><br>
     * estadoCasilla pertenece a { OCUPADA, IMPACTADA, VACIA, ATACADA }
     */
    private void verificarInvariante( )
    {
        assert ( estadoCasilla == VACIA || estadoCasilla == ATACADA || estadoCasilla == OCUPADA || estadoCasilla == IMPACTADA ) : "El estado de la casilla no es válido";
    }
}
