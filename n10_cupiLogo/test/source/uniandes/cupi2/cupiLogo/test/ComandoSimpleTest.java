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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.TestCase;
import uniandes.cupi2.cupiLogo.mundo.ComandoReiniciar;
import uniandes.cupi2.cupiLogo.mundo.ComandoSimple;

/**
 * Clase usada para verificar que los m�todos de la clase ComandoSimple est�n correctamente implementados.
 */
public class ComandoSimpleTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Comando donde se har�n las pruebas.
     */
    private ComandoSimple comando;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * <b> Escenario 1 :</b> Construye un nuevo ComandoSimple.
     */
    private void setupEscenario1( )
    {
        comando = new ComandoReiniciar( );
    }

    /**
     * Prueba 1: Prueba el m�todo constructor de la clase ComandoSimple. <br>
     * <b>M�todos a probar:</b> <br>
     * darNombre<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando corresponde al de la constante de ComandoReiniciar.
     */
    public void testComandoSimple( )
    {
        setupEscenario1( );
        assertEquals( "El nombre debi� inicializase correctamente.", ComandoReiniciar.COMANDO, comando.darNombre( ) );
    }

    /**
     * Prueba 2: Prueba el m�todo guardar de la clase. <br>
     * <b>M�todos a probar:</b> <br>
     * darNombre<br>
     * guardar<br>
     * <b> Casos de prueba: <b><br>
     * 1. El nombre del comando cargado corresponde al nombre del comando guardado.
     */
    public void testGuardar( )
    {
        setupEscenario1( );

        String nombreArchivo = "test/data/pruebaComandoSimple.txt";
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
                assertEquals( "Debi� haber guardado el comando simple correctamente.", comando.darNombre( ), linea );
            }
            else
            {
                fail( "El archivo est� vac�o." );
            }
        }
        catch( FileNotFoundException e )
        {
            fail( "Fall� en  encontrar el archivo." );
        }
        catch( IOException e )
        {
            fail( "Fall� en la lectura del archivo." );
        }
    }
}