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
import uniandes.cupi2.cupiLogo.mundo.ComandoModificarPintando;
import uniandes.cupi2.cupiLogo.mundo.TableroLogo;

/**
 * Clase usada para verificar que los m�todos de la clase ComandoActivarTrayectoria est�n correctamente implementados.
 */
public class ComandoModificarPintandoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * ComandoActivarTrayectoria donde se har�n las pruebas.
     */
    private ComandoModificarPintando comando;

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
     * <b> Escenario 1 :</b> Construye un nuevo Comando para Activar la Trayectoria que activa la trayectoria.
     */
    private void setupEscenario1( )
    {
        comando = new ComandoModificarPintando( ComandoModificarPintando.PINTA );
        tablero = new TableroLogo( 100, 100 );
        JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = (Graphics2D) frame.getGraphics();
    }

    /**
     * <b> Escenario 2 :</b> Construye un nuevo ComandoActivarTrayectoria que desactiva la trayectoria.
     */
    private void setupEscenario2( )
    {
        comando = new ComandoModificarPintando( ComandoModificarPintando.NO_PINTA );
        tablero = new TableroLogo( 100, 100 );
        JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = (Graphics2D) frame.getGraphics();
    }

    /**
     * Prueba 1: Prueba el m�todo constructor de la clase ComandoActivarTrayectoria. <br>
     * <b>M�todos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoActivarTrayectoria.
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre del comando no es el correcto.", ComandoModificarPintando.COMANDO, comando.darNombre( ) );
    }

    /**
     * Prueba 2: Prueba el m�todo ejecutar de la clase. <br>
     * <b>M�todos a probar:</b> <br>
     * ejecutar<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. La tortuga no se encuentra pintando la trayectoria..
     */
    public void testEjecutar1( )
    {
        setupEscenario1( );

        tablero.darTortuga( ).modificarPintando( false );

        comando.ejecutar( tablero.darTortuga( ), graphics );
        assertTrue( "El comando debi� modificar el estado de la tortuga", tablero.darTortuga( ).estaPintando( ) );
    }

    /**
     * Prueba 3: Prueba el m�todo ejecutar de la clase. <br>
     * <b>M�todos a probar:</b> <br>
     * ejecutar<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. La tortuga se encuentra pintando la trayectoria.
     */
    public void testEjecutar2( )
    {
        setupEscenario2( );

        tablero.darTortuga( ).modificarPintando( true );

        comando.ejecutar( tablero.darTortuga( ), graphics );
        assertFalse( "El comando debi� modificar el estado de la tortuga", tablero.darTortuga( ).estaPintando( ) );
    }
}