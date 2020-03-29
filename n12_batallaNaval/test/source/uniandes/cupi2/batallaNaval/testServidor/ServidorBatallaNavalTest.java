/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ServidorBatallaNavalTest.java 2110 2010-11-23 15:32:12Z cm.rodriguez155 $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 2/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testServidor;

import java.io.*;
import java.net.*;
import java.util.*;

import junit.framework.*;
import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta es la clase usada para verificar los m�todos de la clase ServidorBatallaNaval.
 */
public class ServidorBatallaNavalTest extends TestCase
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Es el tiempo m�ximo que se va a esperar a que se inicien los encuentros
     */
    private static final long TIMEOUT = 1000;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private DecoradorServidorBatallaNaval servidor;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo servidor
     */
    private void setupEscenario1( )
    {
        try
        {
            servidor = new DecoradorServidorBatallaNaval( "./test/data/servidor.properties", TIMEOUT );
        }
        catch( Exception e )
        {
            fail( "No deber�a haber problemas construyendo el servidor" );
        }
    }

    /**
     * Este es el m�todo que se llama al terminar la ejecuci�n de cada prueba
     */
    protected void tearDown( ) throws Exception
    {   
        servidor.darAdministradorResultados( ).desconectarBD( );
    }

    /**
     * Verifica que cuando se construya el servidor la conexi�n con la base de datos se establezca correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * DecoradorServidorBatallaNaval. <br>
     * <b> Objetivo: </b> Probar que al iniciar el servidor, la conexi�n con la base de datos haya sido establecida. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear el servidor, el administrado de resultados no deber�a ser null.
     */
    public void testServidorBatallaNaval( )
    {
        setupEscenario1( );

        AdministradorResultados admin = servidor.darAdministradorResultados( );
        assertNotNull( "El administrador de resultados no puede ser null", admin );
    }

    /**
     * Verifica el m�todo darListaActualizadaEncuentros. <br>
     * Usando la clase AyudantePruebasServidor para construir un Thread aparte en el que un Servidor espera conexiones entrantes, desde esta clase de pruebas se establecen
     * conexiones al servidor para construir encuentros. <br>}
     *  <b> M�todos a probar: </b> <br>
     * darListaActualizadaEncuentros. <br>
     * <b> Objetivo: </b> Probar que m�todo darlistaActualizadaEncuentros, retorne los encuentros que realmente existen. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la lista de encuentros, �sta s�lo debe contener los encuentros que se est�n ejecutando en el momento.     
     */
    public void testDarListaActualizadaEncuentros( )
    {
        setupEscenario1( );

        AyudantePruebasServidor ayudante = new AyudantePruebasServidor( servidor );
        ayudante.start( );
        try
        {
            // Establecer las conexiones al servidor
            Socket s1 = new Socket( "localhost", 9999 );
            PrintWriter pw1 = new PrintWriter( s1.getOutputStream( ), true );

            Socket s2 = new Socket( "localhost", 9999 );
            PrintWriter pw2 = new PrintWriter( s2.getOutputStream( ), true );

            // Enviar la informaci�n inicial necesaria para que se inicie un encuentro
            pw1.println( Encuentro.JUGADOR + ":jugadorA" );
            pw2.println( Encuentro.JUGADOR + ":jugadorB" );

            // Esperar a que el encuentro se haya iniciado
            servidor.seInicioEncuentro( );

            // Verificar que el encuentro se haya creado
            Collection encuentros = servidor.darListaActualizadaEncuentros( );
            assertTrue( "No se cre� el encuentro", encuentros.size( ) == 1 );
            Encuentro e1 = ( Encuentro )encuentros.toArray( )[ 0 ];
            String strE1 = e1.toString( );
            assertTrue( "En el encuentro no aparecen los nombres de los jugadores", strE1.indexOf( "jugadorA" ) != -1 && strE1.indexOf( "jugadorB" ) != -1 );
        
            ayudante.detener( );     
        }
        catch( IOException e )
        {	
            ayudante.detener( );
            fail( "No deber�a fallar: " + e.getMessage( ) );
        } 
    }
}
