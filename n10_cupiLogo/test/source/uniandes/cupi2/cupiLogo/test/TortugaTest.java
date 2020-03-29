/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiLogo
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cupiLogo.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.Tortuga;

/**
 * Clase usada para verificar que los m�todos de la clase Tortuga est�n correctamente implementados..
 */
public class TortugaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tortuga donde se har�n las pruebas.
     */
    private Tortuga tortuga;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * <b> Escenario 1 :</b> Construye una Tortuga.
     */
    private void setupEscenario1( )
    {
        tortuga = new Tortuga( 100, 150, 200, 1 );
    }
    
    /**
     * Prueba 1: Prueba los m�todos modificadores de la clase Tortuga. <br>
     * <b>M�todos a probar:</b> <br>
     * Tortuga<br>
     * darXActual<br>
     * darYActual<br>
     * darOrientacion<br>
     * estaPintando<br>
     * <b> Casos de prueba: <b><br>
     * 1. La tortuga fue creada y todos los valores de los atributos corresponden.
     */
    public void testTortuga( )
    {
        setupEscenario1( );

        assertEquals( "La coordenada x debi� modificarse correctamente.", 100, tortuga.darXActual( ), 0.01 );
        assertEquals( "La coordenada y debi� modificarse correctamente.", 150, tortuga.darYActual( ), 0.01 );
        assertEquals( "La orientaci�n debi� modificarse correctamente.", 200, tortuga.darOrientacion( ), 0.01 );
        assertEquals( "El estado del l�piz debi� modificarse correctamente.", true, tortuga.estaPintando( ) );
    }
}