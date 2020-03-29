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

import java.awt.Graphics2D;

import javax.swing.JFrame;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoReiniciar;
import uniandes.cupi2.cupiLogo.mundo.TableroLogo;

/**
 * Clase usada para verificar que los m�todos de la clase ComandoReiniciar est�n correctamente implementados.
 */
public class ComandoReiniciarTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * ComandoReiniciar donde se har�n las pruebas.
     */
    private ComandoReiniciar comando;

    /**
     * TableroLogo donde se har�n las pruebas.
     */
    private TableroLogo tablero;
    
    /**
     * Gr�ficas donde se har�n las pruebas.
     */
    private Graphics2D graphics;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * <b> Escenario 1 :</b> Construye un nuevo ComandoReiniciar.
     */
    private void setupEscenario1( )
    {
        comando = new ComandoReiniciar();
        tablero = new TableroLogo( 100, 100 );
        JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = ( Graphics2D ) frame.getGraphics( );
    }

    /**
     * Prueba 1: Prueba el m�todo constructor de la clase ComandoReiniciar. <br>
     * <b>M�todos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoReiniciar.
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre del comando no es el correcto.", ComandoReiniciar.COMANDO, comando.darNombre( ) );
    }

    /**
     * Prueba 2: Prueba el m�todo ejecutar de la clase. <br>
     * <b>M�todos a probar:</b> <br>
     * ejecutar<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. La tortuga se encuentra en el centro del tablero despues de reiniciar y debe tener estado en pintando.
     */
    public void testEjecutar( )
    {
        setupEscenario1( );

        tablero.darTortuga( ).modificarXActual( 0 );
        tablero.darTortuga( ).modificarYActual( 0 );
        tablero.darTortuga( ).modificarPintando( false );

        comando.ejecutar( tablero.darTortuga( ), graphics );
        assertEquals( "El comando debi� modificar la posici�n de la tortuga en X.", tablero.darTortuga( ).darXActual( ), 50 );
        assertEquals( "El comando debi� modificar la posici�n de la tortuga en Y.", tablero.darTortuga( ).darYActual( ), 50 );
        assertTrue( "El comando debi� modificar el estado de la tortuga.", tablero.darTortuga( ).estaPintando( ) );
    }
}