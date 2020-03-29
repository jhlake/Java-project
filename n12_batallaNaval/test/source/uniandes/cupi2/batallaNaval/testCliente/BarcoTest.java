/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BarcoTest.java 646 2006-11-14 22:08:44Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 2/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testCliente;

import java.awt.Color;

import junit.framework.TestCase;
import uniandes.cupi2.batallaNaval.cliente.*;

/**
 * Esta clase sirve para verificar los métodos de la clase Barco
 */
public class BarcoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el barco sobre el que se harán las pruebas
     */
    private Barco barco;

    /**
     * El color que tendrá el barco
     */
    private Color colorBarco;

    // -----------------------------------------------------------------
    // Métodos
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
     * <b> Métodos a probar: </b> <br>
     * Barco. <br>
     * <b> Objetivo: </b> Probar que el constructor Barco() es capaz de crear correctamente un barco. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un barco y pedir su información, esta debe ser retornada de forma correcta.    
     */
    public void testBarco( )
    {
        setupEscenario1( );

        assertEquals( "El nombre del barco está mal", "Chalupa", barco.darTipoBarco( ) );
        assertEquals( "El número de puntos está mal", 6, barco.darPuntos( ) );

        assertEquals( "El color rojo está mal", colorBarco.getRed( ), barco.darColor( ).getRed( ) );
        assertEquals( "El color verde está mal", colorBarco.getGreen( ), barco.darColor( ).getGreen( ) );
        assertEquals( "El color azul está mal", colorBarco.getBlue( ), barco.darColor( ).getBlue( ) );
    }

    /**
     * Verifica el método atacar. <br>
     * <b> Métodos a probar: </b> <br>
     * atacar. <br>
     * <b> Objetivo: </b> Probar que el método atacar() es capaz de decrementar correctamente los puntos de un barco. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al atacar un barco, sus puntos deben decrementarse en 1.
     */
    public void testAtacar( )
    {
        setupEscenario1( );

        assertEquals( "El número de puntos está mal", 6, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El número de puntos está mal", 5, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El número de puntos está mal", 4, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El número de puntos está mal", 3, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El número de puntos está mal", 2, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El número de puntos está mal", 1, barco.darPuntos( ) );
        barco.atacar( );
        assertEquals( "El número de puntos está mal", 0, barco.darPuntos( ) );
    }

    /**
     * Verifica el método estaHundido. <br>
     * <b> Métodos a probar: </b> <br>
     * estaHundido. <br>
     * <b> Objetivo: </b> Probar que el método estaHundido() indique correctamente cuando el barco ha sido hundido. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al preguntar si un barco está hundido, sabiéndose que no lo esta, se debe retornar false. <br>
     * 2. Al preguntar si un barco está hundido, sabiéndose que lo esta, se debe retornar true.
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
