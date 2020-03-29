/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CasillaTest.java 650 2006-11-16 00:59:12Z da-romer $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 2/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testCliente;

import java.awt.Color;

import junit.framework.TestCase;
import uniandes.cupi2.batallaNaval.cliente.*;

/**
 * Esta es la clase usada para verificar los m�todos de la clase Casilla
 */
public class CasillaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la casilla donde se har�n las pruebas
     */
    private Casilla casilla;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una casilla vac�a
     */
    private void setupEscenario1( )
    {
        casilla = new Casilla( );
    }

    /**
     * Construye una casilla vac�a con un barco
     */
    private void setupEscenario2( )
    {
        casilla = new Casilla( );
        Barco barco = new Barco( "Chalupa", 6, Color.BLACK );
        casilla.cambiarBarco( barco );
    }

    /**
     * Verifica los m�todos analizadores de la clase Casilla, para una casilla vac�a. <br>
     * <b> M�todos a probar: </b> <br>
     * darEstado, darBarco. <br>
     * <b> Objetivo: </b> Verificar que los m�todos analizadores de la casilla retornen correctamente su informaci�n. <br> 
     * 1. Se sabe que el estado de la casilla es Casilla.VACIA. Al pedir el estado de la casilla debe obtener este valor. <br>
     * 2. Se sabe que la casilla no contiene barcos. Al pedir el barco de la casilla se debe obtener null.           
     */
    public void testDatosCasilla1( )
    {
        setupEscenario1( );

        assertEquals( "El estado de la casilla no est� bien", Casilla.VACIA, casilla.darEstado( ) );
        assertNull( "No deber�a haber un barco en la casilla", casilla.darBarco( ) );
    }

    /**
     * Verifica el m�todo atacarCasilla sobre una casilla vac�a. <br>
     * <b> M�todos a probar: </b> <br>
     * darEstado, atacarCasilla. <br>
     * <b> Objetivo: </b> Verificar el m�todo atacarCasilla() cambie el estado de la casilla de forma correcta. <br> 
     * 1. Se sabe que el estado de la casilla es Casilla.VACIA. Al pedir el estado de la casilla debe obtener ese valor. <br>
     * 2. Al atacar una casilla sin barcos y pedir su estado este debe ser Casilla.ATACADA.
     */
    public void testAtacarCasilla1( )
    {
        setupEscenario1( );

        assertEquals( "El estado de la casilla no est� bien", Casilla.VACIA, casilla.darEstado( ) );

        casilla.atacarCasilla( );
        assertEquals( "El estado de la casilla no est� bien", Casilla.ATACADA, casilla.darEstado( ) );
    }

    /**
     * Verifica el m�todo atacarCasilla sobre una casilla que tiene un barco. <br>
     * <b> M�todos a probar: </b> <br>
     * darEstado, atacarCasilla. <br>
     * <b> Objetivo: </b> Verificar el m�todo atacarCasilla() cambie el estado de la casilla de forma correcta. <br> 
     * 1. Se sabe que el estado de la casilla es Casilla.OCUPADA. Al pedir el estado de la casilla debe obtener ese valor. <br>
     * 2. Al atacar una casilla ocupada y pedir su estado este debe ser Casilla.IMPACTADA.
     */
    public void testAtacarCasilla2( )
    {
        setupEscenario2( );

        assertEquals( "El estado de la casilla no est� bien", Casilla.OCUPADA, casilla.darEstado( ) );
        int puntosBarco = casilla.darBarco( ).darPuntos( );

        casilla.atacarCasilla( );
        assertEquals( "El estado de la casilla no est� bien", Casilla.IMPACTADA, casilla.darEstado( ) );
        int puntosBarco2 = casilla.darBarco( ).darPuntos( );

        assertTrue( "Los puntos del barco deber�an haber disminu�do", puntosBarco == puntosBarco2 + 1 );
    }

    /**
     * Verifica el m�todo marcar. <br>
     * <b> M�todos a probar: </b> <br>
     * darEstado, marcar. <br>
     * <b> Objetivo: </b> Verificar el m�todo marcar() cambie el estado de la casilla de forma correcta. <br> 
     * 1. Al cambiar el estado de una casilla por X, al pedirlo se debe obtener X.
     */
    public void testMarcar( )
    {
        setupEscenario1( );

        assertEquals( "El estado de la casilla no est� bien", Casilla.VACIA, casilla.darEstado( ) );

        casilla.marcar( Casilla.OCUPADA );
        assertEquals( "El estado de la casilla no est� bien", Casilla.OCUPADA, casilla.darEstado( ) );

        casilla.marcar( Casilla.ATACADA );
        assertEquals( "El estado de la casilla no est� bien", Casilla.ATACADA, casilla.darEstado( ) );

        casilla.marcar( Casilla.IMPACTADA );
        assertEquals( "El estado de la casilla no est� bien", Casilla.IMPACTADA, casilla.darEstado( ) );

        casilla.marcar( Casilla.VACIA );
        assertEquals( "El estado de la casilla no est� bien", Casilla.VACIA, casilla.darEstado( ) );
    }

    /**
     * Verifica el m�todo setBarco
     * <b> M�todos a probar: </b> <br>
     * darEstado, cambiarBarco. <br>
     * <b> Objetivo: </b> Verificar que el m�todo cambiarBarco() cambie correctamente el barco de la casilla. <br> 
     * 1. Se sabe que el estado de la casilla es Casilla.VACIA. Al pedir el estado de la casilla debe obtener ese valor. <br>
     * 2. Al definir el barco de una casilla y pedir su estado, se debe obtener Casilla.OCUPADA.<br>
     * 3. Al definir el barco de una casilla en null y pedir su estado, se debe obtener Casilla.VACIA.<br>
     */
    public void testCambiarBarco( )
    {
        setupEscenario1( );

        assertEquals( "El estado de la casilla no est� bien", Casilla.VACIA, casilla.darEstado( ) );

        Barco barco = new Barco( "Chalupa", 6, Color.BLACK );
        casilla.cambiarBarco( barco );
        assertEquals( "El estado de la casilla no est� bien", Casilla.OCUPADA, casilla.darEstado( ) );

        casilla.cambiarBarco( null );
        assertEquals( "El estado de la casilla no est� bien", Casilla.VACIA, casilla.darEstado( ) );
    }
}
