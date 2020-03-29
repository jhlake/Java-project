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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoEscalar;
import uniandes.cupi2.cupiLogo.mundo.TableroLogo;

/**
 * Clase usada para verificar que los m�todos de la clase ComandoEscalar est�n correctamente implementados.
 */
public class ComandoEscalarTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * ComandoEscalar donde se har�n las pruebas.
     */
    private ComandoEscalar comando;

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
     * <b> Escenario 1 :</b> Construye un nuevo ComandoEscalar.
     */
    private void setupEscenario1( )
    {
        comando = new ComandoEscalar( 2.0, ComandoEscalar.AGRANDAR );
        tablero = new TableroLogo( 100, 100 );

        JFrame frame = new JFrame( );
        frame.setVisible( true );
        graphics = (Graphics2D) frame.getGraphics();
    }

    /**
     * Prueba 1: Prueba el m�todo constructor de la clase ComandoEscalar. <br>
     * <b>M�todos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoEscalar.
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre del comando no es el correcto.", ComandoEscalar.COMANDO, comando.darNombre( ) );
    }

    /**
     * Prueba 2: Prueba el m�todo ejecutar de la clase. <br>
     * <b>M�todos a probar:</b> <br>
     * ejecutar<br>
     * darTortuga<br>
     * <b> Casos de prueba: <b><br>
     * 1. La escala de la tortuga cambi�.
     */
    public void testEjecutar( )
    {
        setupEscenario1( );
        comando.ejecutar( tablero.darTortuga( ), graphics );
        assertEquals( "El comando debi� modificar la escala de la tortuga.", tablero.darTortuga( ).darEscala( ), 2.0, 0.1 );
    }

    /**
     * Prueba 3: Prueba el m�todo guardar de la clase. <br>
     * <b>M�todos a probar:</b> <br>
     * guardar<br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando cargado corresponde al nombre del comando guardado.
     */
    public void testGuardarComando( )
    {
        setupEscenario1( );

        String nombreArchivo = "test/data/pruebaComandoEscalar.txt";
        File archivo = new File( nombreArchivo );

        PrintWriter pw;
        try
        {
            pw = new PrintWriter( archivo );
            comando.guardar( pw );
            pw.close( );

        }
        catch( FileNotFoundException e )
        {
            fail( "Fall� en la creaci�n y escritura del archivo." );
        }

        BufferedReader br;

        try
        {
            br = new BufferedReader( new FileReader( archivo ) );

            String linea = br.readLine( );
            if( linea != null )
            {
                String textoComando = comando.darNombre( ) + " 2.0" + " 0";
                assertEquals( "Debi� haber guardado el comando correctamente.", textoComando, linea );
            }
            else
            {
                fail( "El archivo est� vacio." );
            }
        }
        catch( FileNotFoundException e )
        {
            fail( "Fall� en  encontrar el archivo." );
        }
        catch( IOException e )
        {
            fail( "Fall� en la lectura del archivo" );
        }
        archivo.delete( );
    }
}