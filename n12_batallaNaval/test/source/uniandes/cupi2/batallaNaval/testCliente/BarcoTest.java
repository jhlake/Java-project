/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BarcoTest.java 646 2006-11-14 22:08:44Z da-romer $
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
 * Esta clase sirve para verificar los m�todos de la clase Barco
 */
public class BarcoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el barco sobre el que se har�n las pruebas
     */
    private Barco barco;

    /**
     * El color que tendr� el barco
     */
    private Color colorBarco;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo barco con valores conocidos
     */
    private void setupEscenario1( )
    {
        colorBarco = new Color( 150, 250, 135 );
        barco = new Barco( "Chalupa", 6, colorBarco );
    }

    /**
     * Verifica el constructor de la clase Barco. <br>
     * <b> M�todos a probar: </b> <br>
     * Barco. <br>
     * <b> Objetivo: </b> Probar que el constructor Barco() es capaz de crear correctamente un barco. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un barco y pedir su informaci�n, esta debe ser retornada de forma correcta.    
     */
    public void testBarco( )
    {
        setupEscenario1( );

        assertEquals( "El nombre del barco est� mal", "Chalupa", barco.darTipoBarco( ) );
        assertEquals( "El n�mero de puntos est� mal", 6, barco.darPuntos( ) );

        assertEquals( "El color rojo est� mal", colorBarco.getRed( ), barco.darColor( ).getRed( ) );
        assertEquals( "El color verde est� mal", colorBarco.getGreen( ), barco.darColor( ).getGreen( ) );
        assertEquals( "El color azul est� mal", colorBarco.getBlue( ), barco.darColor( ).getBlue( ) );
    }

    /**
     * Verifica el m�todo atacar. <br>
     * <b> M�todos a probar: </b> <br>
     * atacar. <br>
     * <b> Objetivo: </b> Probar que el m�todo atacar() es capaz de decrementar correctamente los puntos de un barco. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al atacar un barco, sus puntos deben decrementarse en 1.
     */
    public void testAtacar( )
    {
        setupEscenario1( );

        assertEquals( "El n�mero de puntos est� mal", 6, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El n�mero de puntos est� mal", 5, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El n�mero de puntos est� mal", 4, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El n�mero de puntos est� mal", 3, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El n�mero de puntos est� mal", 2, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El n�mero de puntos est� mal", 1, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El n�mero de puntos est� mal", 0, barco.darPuntos( ) );
    }

    /**
     * Verifica el m�todo estaHundido. <br>
     * <b> M�todos a probar: </b> <br>
     * estaHundido. <br>
     * <b> Objetivo: </b> Probar que el m�todo estaHundido() indique correctamente cuando el barco ha sido hundido. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al preguntar si un barco est� hundido, sabi�ndose que no lo esta, se debe retornar false. <br>
     * 2. Al preguntar si un barco est� hundido, sabi�ndose que lo esta, se debe retornar true.
     */
    public void testEstaHundido( )
    {
        setupEscenario1( );

        assertFalse( "El barco no debe estar hundido", barco.estaHundido( ) );
        barco.atacar( );
        assertFalse( "El barco no debe estar hundido", barco.estaHundido( ) );
        barco.atacar( );
        assertFalse( "El barco no debe estar hundido", barco.estaHundido( ) );
        barco.atacar( );
        assertFalse( "El barco no debe estar hundido", barco.estaHundido( ) );
        barco.atacar( );
        assertFalse( "El barco no debe estar hundido", barco.estaHundido( ) );
        barco.atacar( );
        assertFalse( "El barco no debe estar hundido", barco.estaHundido( ) );
        barco.atacar( );
        assertTrue( "El barco ya debe estar hundido", barco.estaHundido( ) );
    }
}
