/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupiMuseo
 * Autor: Vanessa Pérez Romanello - 13-sep-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiMuseo.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMuseo.mundo.ObraDeArte;

/**
 * Clase usada para verificar la correcta implementación de la clase ObraDeArte.
 */
public class ObraDeArteTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * ObraDeArte donde se harán las pruebas.
     */
    private ObraDeArte obraDeArte;

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Escenario 1. Construye una nueva ObraDeArte con la información contenida en un archivo.
     */
    public void setupEscenario1( )
    {
        try
        {
            Properties persistencia = new Properties( );
            FileInputStream fis = new FileInputStream( new File( "./test/data/obrasDeArteTest.data" ) );
            persistencia.load( fis );
            fis.close( );
            obraDeArte = new ObraDeArte( 4, persistencia );
        }
        catch( Exception e )
        {
            fail( "No debería generarse ninguna excepción." );
        }

    }

    /**
     * Prueba 1: Prueba el método constructor <br>
     * <b>Métodos a probar: </b> <br>
     * ObraDeArte <br>
     * darSumaPuntaje <br>
     * darCantidadEvaluaciones <br>
     * darNombreObra <br>
     * darNombreArtista <br>
     * darTipo <br>
     * darImagen <br>
     * <b>Casos de prueba : </b> <br>
     * 1. Creación de una obra de arte.
     */
    public void testObraDeArte( )
    {
        setupEscenario1( );
        assertEquals( "No se inicializó correctamente la sumas del puntaje de la obra.", -1, obraDeArte.darSumaPuntaje( ) );
        assertEquals( "No se inicializó correctamente el número de evaluaciones de la obra.", 0, obraDeArte.darCantidadEvaluaciones( ) );
        assertEquals( "No se inicializó correctamente el nombre de la obra.", "Balsa Muisca", obraDeArte.darNombreObra( ) );
        assertEquals( "No se inicializó correctamente el nombre del artista de la obra.", "Cultura Muisca", obraDeArte.darNombreArtista( ) );
        assertEquals( "No se inicializó correctamente el tipo de la obra.", ObraDeArte.ESCULTURA, obraDeArte.darTipo( ) );
        assertEquals( "No se inicializó correctamente la ruta de la imagen de la obra.", "data/imagenes/balsa_muisca.png", obraDeArte.darRutaImagen( ) );
        assertEquals( "No se inicializó correctamente el estado de la obra.", true, obraDeArte.estaEnRemate( ) );
    }

    /**
     * Prueba 2: Prueba el método agregarCalificacion <br>
     * <b>Métodos a probar: </b> <br>
     * agregarCalificacion <br>
     * darSumaPuntaje <br>
     * darCantidadEvaluaciones <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra no ha sido calificada.<br>
     * 2. La obra se califica. 
     */
    public void testAgregarCalificacion( )
    {
        setupEscenario1( );
        assertEquals( "No sumó el puntaje correctamente.", -1, obraDeArte.darSumaPuntaje( ) );
        assertEquals( "No cambió la cantidad de evaluaciones correctamente.", 0, obraDeArte.darCantidadEvaluaciones( ) );
        obraDeArte.agregarCalificacion( 5 );
        assertEquals( "No sumó el puntaje correctamente.", 5, obraDeArte.darSumaPuntaje( ) );
        assertEquals( "No cambió la cantidad de evaluaciones correctamente.", 1, obraDeArte.darCantidadEvaluaciones( ) );
        obraDeArte.agregarCalificacion( 2 );
        assertEquals( "No sumó el puntaje correctamente.", 7, obraDeArte.darSumaPuntaje( ) );
        assertEquals( "No cambió la cantidad de evaluaciones correctamente.", 2, obraDeArte.darCantidadEvaluaciones( ) );
    }

    /**
     * Prueba 3: Prueba el método darPromedioCalificacion <br>
     * <b>Métodos a probar: </b> <br>
     * agregarCalificacion <br>
     * darPromedioCalificacion <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra no ha sido calificada.<br>
     * 2. La obra se califica. 
     */
    public void testDarPromedioCalificacion( )
    {
        setupEscenario1( );

        assertEquals( "El promedio de calificación no es correcto.", -1, obraDeArte.darPromedioCalificacion( ) );
        obraDeArte.agregarCalificacion( 5 );
        assertEquals( "El promedio de calificación no es correcto.", 5, obraDeArte.darPromedioCalificacion( ) );
        obraDeArte.agregarCalificacion( 0 );
        assertEquals( "El promedio de calificación no es correcto.", 3, obraDeArte.darPromedioCalificacion( ) );
        obraDeArte.agregarCalificacion( 1 );
        assertEquals( "El promedio de calificación no es correcto.", 2, obraDeArte.darPromedioCalificacion( ) );
        obraDeArte.agregarCalificacion( 4 );
        assertEquals( "El promedio de calificación no es correcto.", 3, obraDeArte.darPromedioCalificacion( ) );
    }
}