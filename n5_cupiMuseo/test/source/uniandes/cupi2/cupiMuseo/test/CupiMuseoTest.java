/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_cupiMuseo.
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiMuseo.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMuseo.mundo.CupiMuseo;
import uniandes.cupi2.cupiMuseo.mundo.ObraDeArte;

/**
 * Clase usada para verificar la correcta implementaci�n de la clase CupiMuseo.
 */
public class CupiMuseoTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * CupiMuseo donde se har�n las pruebas
     */
    private CupiMuseo cupiMuseo;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Escenario 1. Construye un nuevo CupiMuseo con la informaci�n contenida en un archivo por defecto.
     */
    public void setupEscenario1( )
    {
        try
        {
            cupiMuseo = new CupiMuseo( );
        }
        catch( Exception e )
        {
            fail( "No deber�a generarse ninguna excepci�n." );
        }
    }

    /**
     * Prueba 1: Prueba el m�todo constructor <br>
     * <b>M�todos a probar: </b> <br>
     * cupiMuseo <br>
     * darCantidadObras <br>
     * darObraSiguiente <br>
     * darObraActual<br>
     * <b>Casos de prueba : </b> <br>
     * 1. Verifica que en todas las posiciones del arreglo de obras exista una obra y que esta sea la correcta.
     */
    public void testCupiMuseo( )
    {
        setupEscenario1( );
        assertEquals( "El n�mero de obras no es correcto.", 9, cupiMuseo.darCantidadObras( ) );
        try
        {
            Properties pr = new Properties( );
            FileInputStream file = new FileInputStream( "./data/obrasDeArte.data" );
            pr.load( file );
            int i = 1;
            ObraDeArte actual = cupiMuseo.darObraActual( );
            String tipo;
            while( i < 9 )
            {
                tipo = (Integer.parseInt(pr.getProperty( "museo.obra" + i + ".tipo" )))==1?ObraDeArte.PINTURA:ObraDeArte.ESCULTURA;
                assertNotNull( "La obra no deber�a ser nula.", actual);
                assertEquals( "Error cargando el museo, el nombre de la obra " + i + " no corresponde al correcto.",pr.getProperty( "museo.obra" + i + ".nombreObra" ), actual.darNombreObra( ) );
                assertEquals( "Error cargando el museo, el nombre del artista de la obra " + i + " no corresponde al correcto.",pr.getProperty( "museo.obra" + i + ".nombreArtista" ), actual.darNombreArtista( ) );
                assertEquals( "Error cargando el museo, el tipo de la obra " + i + " no corresponde al correcto.",tipo, actual.darTipo( ) );
                assertEquals( "Error cargando el museo, la imagen de la obra " + i + " no corresponde al correcto.","data/imagenes/" + pr.getProperty( "museo.obra" + i + ".imagen" ), actual.darRutaImagen( ) );
                assertEquals( "Error cargando el museo, el estado de la obra " + i + " no corresponde al correcto.",Boolean.parseBoolean(pr.getProperty( "museo.obra" + i + ".remate" )), actual.estaEnRemate( ) );
                i++;
                actual = cupiMuseo.darObraSiguiente( );
            }
        }
        catch( Exception e )
        {
            fail( "No se deber�a generar el error: " + e.getMessage( ) + "." );
        }
    }
    
    /**
     * Prueba 2: Prueba el m�todo darObraActual <br>
     * <b>M�todos a probar: </b> <br>
     * darObraActual <br>
     * darObraSiguiente<br>
     * <b>Casos de prueba : </b> <br>
     * 1. Verifica que la obra actual sea la correcta en dos consultas diferentes.
     */
    public void testDarObraActual( )
    {
        setupEscenario1( );
        try
        {
            ObraDeArte obraActual = cupiMuseo.darObraActual( );
            assertEquals( "El nombre de la obra actual no es correcto. ", "Gato del Raval", obraActual.darNombreObra( ) );
            assertEquals( "El nombre del artista de la obra actual no es correcto. ", "Fernando Botero", obraActual.darNombreArtista( ) );
            assertEquals( "El tipo de la obra actual no es correcta. ", ObraDeArte.ESCULTURA, obraActual.darTipo( ) );
            assertEquals( "La ruta de la imagen de la obra actual no es correcta. ", "data/imagenes/gato_del_raval.png", obraActual.darRutaImagen( ) );
            assertEquals( "El estado de la obra actual no es correcto. ", false, obraActual.estaEnRemate( ) );
            
            cupiMuseo.darObraSiguiente( );
            cupiMuseo.darObraSiguiente( );
            cupiMuseo.darObraSiguiente( );
            cupiMuseo.darObraSiguiente( );
            
            obraActual = cupiMuseo.darObraActual( );
            assertEquals( "El nombre de la obra actual no es correcto. ", "�ltima Cena", obraActual.darNombreObra( ) );
            assertEquals( "El nombre del artista de la obra actual no es correcto. ", "Leonardo Da Vinci", obraActual.darNombreArtista( ) );
            assertEquals( "El tipo de la obra actual no es correcta. ", ObraDeArte.PINTURA, obraActual.darTipo( ) );
            assertEquals( "La ruta de la imagen de la obra actual no es correcta. ", "data/imagenes/ultima_cena.png", obraActual.darRutaImagen( ) );
            assertEquals( "El estado de la obra actual no es correcto. ", false, obraActual.estaEnRemate( ) );
            
      
            
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n." );
        }
    }

    
    /**
     * Prueba 3: Prueba el m�todo darObraSiguiente <br>
     * <b>M�todos a probar: </b> <br>
     * darObraSiguiente <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual no es la �ltima obra.
     */
    public void testDarObraSiguiente1( )
    {
        setupEscenario1( );
        ObraDeArte obraSiguiente;
        try
        {
            obraSiguiente = cupiMuseo.darObraSiguiente( );
            assertEquals( "El nombre de la obra siguiente no es correcto. ", "Los M�sicos", obraSiguiente.darNombreObra( ) );
            assertEquals( "El nombre del artista de la obra siguiente no es correcto. ", "Fernando Botero", obraSiguiente.darNombreArtista( ) );
            assertEquals( "El tipo de la obra siguiente no es correcta. ", ObraDeArte.PINTURA, obraSiguiente.darTipo( ) );
            assertEquals( "La ruta de la imagen de la obra siguiente no es correcta. ", "data/imagenes/los_musicos.png", obraSiguiente.darRutaImagen( ) );
            assertEquals( "El estado de la obra siguiente no es correcto. ", false, obraSiguiente.estaEnRemate( ) );

     
            cupiMuseo.darObraSiguiente( );
            cupiMuseo.darObraSiguiente( );
            obraSiguiente = cupiMuseo.darObraSiguiente( );
            assertEquals( "El nombre de la obra siguiente no es correcto. ", "�ltima Cena", obraSiguiente.darNombreObra( ) );
            assertEquals( "El nombre del artista de la obra siguiente no es correcto. ", "Leonardo Da Vinci", obraSiguiente.darNombreArtista( ) );
            assertEquals( "El tipo de la obra siguiente no es correcta. ", ObraDeArte.PINTURA, obraSiguiente.darTipo( ) );
            assertEquals( "La ruta de la imagen de la obra siguiente no es correcta. ", "data/imagenes/ultima_cena.png", obraSiguiente.darRutaImagen( ) );
            assertEquals( "El estado de la obra siguiente no es correcto. ", false, obraSiguiente.estaEnRemate( ) );
            
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n." );
        }

    }

    
    /**
     * Prueba 4: Prueba el m�todo darObraSiguiente <br>
     * <b>M�todos a probar: </b> <br>
     * darObraSiguiente <br>
     * darUltimaObra <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual es la �ltima obra.
     */
    public void testDarObraSiguiente2( )
    {
        setupEscenario1( );
        try
        {
            ObraDeArte obraActual = cupiMuseo.darUltimaObra( );
            obraActual = cupiMuseo.darObraSiguiente( );
            fail( "Deber�a generar excepci�n pues est� en la �ltima obra." );
        }
        catch( Exception e )
        {
            // Debe pasar por aqu�
        }
    }
    
    /**
     * Prueba 5: Prueba el m�todo darObraAnterior <br>
     * <b>M�todos a probar: </b> <br>
     *  darObraAnterior <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual es la primera obra.
     */
    public void testDarObraAnterior1( )
    {
        setupEscenario1( );

        try
        {
            cupiMuseo.darObraAnterior( );
            fail( "Deber�a generar excepci�n pues est� en la primera obra." );
        }
        catch( Exception e )
        {
            // Debe pasar por aqu�
        }
    }

    /**
     * Prueba 6: Prueba el m�todo darObraAnterior <br>
     * <b>M�todos a probar: </b> <br>
     *  darObraAnterior <br>
     *  darUltimaObra <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual no es la primera obra.
     */
    public void testDarObraAnterior2( )
    {
        setupEscenario1( );

        try
        {
            ObraDeArte obraAnterior = cupiMuseo.darUltimaObra( );
            obraAnterior = cupiMuseo.darObraAnterior( );
            obraAnterior = cupiMuseo.darObraAnterior( );
            assertEquals( "El nombre de la obra anterior no es correcto. ", "El nacimiento de Venus", obraAnterior.darNombreObra( ) );
            assertEquals( "El nombre del artista de la obra anterior no es correcto. ", "Sandro Botticelli", obraAnterior.darNombreArtista( ) );
            assertEquals( "El tipo de la obra anterior no es correcta. ", ObraDeArte.PINTURA, obraAnterior.darTipo( ) );
            assertEquals( "La ruta de la imagen de la obra anterior no es correcta. ", "data/imagenes/el_nacimiento_de_Venus.png", obraAnterior.darRutaImagen( ) );
            assertEquals( "El estado de la obra anterior no es correcto. ", false, obraAnterior.estaEnRemate( ) );

            
            cupiMuseo.darObraAnterior( );
            obraAnterior = cupiMuseo.darObraAnterior( );
            assertEquals( "El nombre de la obra anterior no es correcto. ", "�ltima Cena", obraAnterior.darNombreObra( ) );
            assertEquals( "El nombre del artista de la obra anterior no es correcto. ", "Leonardo Da Vinci", obraAnterior.darNombreArtista( ) );
            assertEquals( "El tipo de la obra anterior no es correcta. ", ObraDeArte.PINTURA, obraAnterior.darTipo( ) );
            assertEquals( "La ruta de la imagen de la obra anterior no es correcta. ", "data/imagenes/ultima_cena.png", obraAnterior.darRutaImagen( ) );
            assertEquals( "El estado de la obra anterior no es correcto. ", false, obraAnterior.estaEnRemate( ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n." );
        }
    }

    /**
     * Prueba 7: Prueba el m�todo darPrimeraObra <br>
     * <b>M�todos a probar: </b> <br>
     *  darPrimeraObra <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual es la primera obra.
     */
    public void testDarPrimeraObra1( )
    {
        setupEscenario1( );

        try
        {
            cupiMuseo.darPrimeraObra( );
            fail( "Deber�a generar excepci�n pues est� en la primera obra." );
        }
        catch( Exception e )
        {
            // Debe pasar por aqu�
        }
    }

   
    /**
     * Prueba 8: Prueba el m�todo darPrimeraObra <br>
     * <b>M�todos a probar: </b> <br>
     *  darPrimeraObra <br>
     *  darObraSiguiente<br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual no es la primera obra.
     */
    public void testDarPrimeraObra2( )
    {
        setupEscenario1( );
         
        try
        {
            ObraDeArte obraPrimera = cupiMuseo.darObraSiguiente( );
            obraPrimera = cupiMuseo.darObraSiguiente( );
            obraPrimera = cupiMuseo.darPrimeraObra( );
            assertEquals( "El nombre de la primera obra no es correcto. ", "Gato del Raval", obraPrimera.darNombreObra( ) );
            assertEquals( "El nombre del artista de la primera obra no es correcto. ", "Fernando Botero", obraPrimera.darNombreArtista( ) );
            assertEquals( "El tipo de la primera obra no es correcta. ", ObraDeArte.ESCULTURA, obraPrimera.darTipo( ) );
            assertEquals( "La ruta de la imagen de la primera obra no es correcta. ", "data/imagenes/gato_del_raval.png", obraPrimera.darRutaImagen( ) );
            assertEquals( "El estado de la primera obra no es correcto. ", false, obraPrimera.estaEnRemate( ) );

        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n." );
        }
    }

    
    /**
     * Prueba 9: Prueba el m�todo darUltimaObra <br>
     * <b>M�todos a probar: </b> <br>
     *  darUltimaObra <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual es la �ltima obra.
     */
    public void testDarUltimaObra1( )
    {
        setupEscenario1( );

        try
        {
            cupiMuseo.darUltimaObra( );
            cupiMuseo.darUltimaObra( );
            fail("Deber�a generar Excepci�n dado que est� en la �ltima obra.");
        }
        catch( Exception e )
        {
            // Debe pasar por aqu�
        }
    }
    
    /**
     * Prueba 10: Prueba el m�todo darUltimaObra <br>
     * <b>M�todos a probar: </b> <br>
     *  darUltimaObra <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual no es la �ltima obra.
     */
    public void testDarUltimaObra2( )
    {
        setupEscenario1( );
        ObraDeArte obraUltima;
        try
        {
            obraUltima = cupiMuseo.darUltimaObra( );
            assertEquals( "El nombre de la �ltima obra no es correcto. ", "Sposalizio della Vergine", obraUltima.darNombreObra( ) );
            assertEquals( "El nombre del artista de la �ltima obra no es correcto. ", "Rafael Sanzio", obraUltima.darNombreArtista( ) );
            assertEquals( "El tipo de la �ltima obra no es correcta. ", ObraDeArte.PINTURA, obraUltima.darTipo( ) );
            assertEquals( "La ruta de la imagen de la �ltima obra no es correcta. ", "data/imagenes/los_desposorios_de_la_Virgen.png", obraUltima.darRutaImagen( ) );
            assertEquals( "El estado de la �ltima obra no es correcto. ", true, obraUltima.estaEnRemate( ) );

       
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }

    
    /**
     * Prueba 11: Prueba el m�todo darObraPorNombre <br>
     * <b>M�todos a probar: </b> <br>
     *  darObraPorNombre <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra buscada existe. 
     */
    public void testDarObraPorNombre1( )
    {
        setupEscenario1( );
        try
        {
            ObraDeArte obraVenus = cupiMuseo.darObraPorNombre( "El nacimiento de Venus" );
            assertEquals( "El nombre de la obra buscada no es correcto. ", "El nacimiento de Venus", obraVenus.darNombreObra( ) );
            assertEquals( "El nombre del artista de la obra buscada no es correcto. ", "Sandro Botticelli", obraVenus.darNombreArtista( ) );
            assertEquals( "El tipo de la obra buscada no es correcta. ", ObraDeArte.PINTURA, obraVenus.darTipo( ) );
            assertEquals( "La ruta de la imagen de la obra buscada no es correcta. ", "data/imagenes/el_nacimiento_de_Venus.png", obraVenus.darRutaImagen( ) );
            assertEquals( "El estado de la obra buscada no es correcto. ", false, obraVenus.estaEnRemate( ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n" );
        }

    }

   
    /**
     * Prueba 12: Prueba el m�todo darObraPorNombre <br>
     * <b>M�todos a probar: </b> <br>
     *  darObraPorNombre <br>
     *  darNombreArtista <br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra buscada no existe.  
     */
    public void testDarObraPorNombre2( )
    {
        setupEscenario1( );
        
        try
        {
            cupiMuseo.darObraPorNombre( "Monalisa" );
            fail( "Deber�a generar excepci�n" );
        }
        catch( Exception e )
        {
            //Deber�a pasar por aqu�
        }
    }
    
    /**
     * Prueba 13: Prueba el m�todo darObraPorNombre <br>
     * <b>M�todos a probar: </b> <br>
     *  calificarObraActual <br>
     *  darPromedioCalificacion <br>
     *  darObraSiguiente<br>
     *  darObraAnterior<br>
     * <b>Casos de prueba : </b> <br>
     * 1. La obra actual no tiene calificaciones.
     * 2. La obra actual se califica.
     * 3. La obra actual cambia y la nueva no tiene calificaciones.
     * 4. Se retorna a la obra anterior y se verifica su calificaci�n.
     */
    public void testCalificarObraActual( )
    {
        setupEscenario1( );
        try
        {
            //Sin calificaciones.
            assertEquals("La obra no debe estar calificada, suma de puntaje incorrecta.", -1, cupiMuseo.darObraActual( ).darSumaPuntaje( ) );
            assertEquals("La obra no debe estar calificada, cantidad de calificaciones incorrecta.", 0, cupiMuseo.darObraActual( ).darCantidadEvaluaciones( ));
            assertEquals("La obra no debe estar calificada, promedio incorrecto", -1, cupiMuseo.darObraActual( ).darPromedioCalificacion( ) );
            cupiMuseo.calificarObraActual( 5 );
            //Calificaci�n valida.
            assertEquals("No se calific� correctamente, la suma de puntaje no es correcta.",5 ,  cupiMuseo.darObraActual( ).darSumaPuntaje( ));
            assertEquals("No se calific� correctamente, la cantidad de evaluacioens no es correcta.", 1, cupiMuseo.darObraActual( ).darCantidadEvaluaciones( ));
            assertEquals("No se calific� correctamente, el promedio no es correcto.", 5 ,cupiMuseo.darObraActual( ).darPromedioCalificacion( ) );
            cupiMuseo.calificarObraActual( 5 );
            //Calificaci�n valida.
            assertEquals("No se calific� correctamente, la suma de puntaje no es correcta.", 10, cupiMuseo.darObraActual( ).darSumaPuntaje( ));
            assertEquals("No se calific� correctamente, la cantidad de evaluacioens no es correcta.", 2, cupiMuseo.darObraActual( ).darCantidadEvaluaciones( ) );
            assertEquals("No se calific� correctamente, el promedio no es correcto.", 5, cupiMuseo.darObraActual( ).darPromedioCalificacion( ) );
            cupiMuseo.calificarObraActual( 2 );
            //Calificaci�n valida.
            assertEquals("No se calific� correctamente, la suma de puntaje no es correcta.", 12, cupiMuseo.darObraActual( ).darSumaPuntaje( ) );
            assertEquals("No se calific� correctamente, la cantidad de evaluacioens no es correcta.", 3,cupiMuseo.darObraActual( ).darCantidadEvaluaciones( ));
            assertEquals("No se calific� correctamente, el promedio no es correcto.", 4, cupiMuseo.darObraActual( ).darPromedioCalificacion( ));
            //Se desplaza a una nueva obra
            cupiMuseo.darObraSiguiente( );
            //Sin calificaciones.
            assertEquals("La obra no debe estar calificada, suma de puntaje incorrecta.", -1, cupiMuseo.darObraActual( ).darSumaPuntaje( ));
            assertEquals("La obra no debe estar calificada, cantidad de calificaciones incorrecta.", 0, cupiMuseo.darObraActual( ).darCantidadEvaluaciones( ));
            assertEquals("La obra no debe estar calificada, promedio incorrecto", -1, cupiMuseo.darObraActual( ).darPromedioCalificacion( ));
            //Se regresa a la obra calificada
            cupiMuseo.darObraAnterior( );
            //Calificaci�n valida.
            assertEquals("No se almacena la calificaci�n correctamente, la suma de puntaje no es correcta.", 12, cupiMuseo.darObraActual( ).darSumaPuntaje( ));
            assertEquals("No se almacena la calificaci�n correctamente, la cantidad de evaluacioens no es correcta.", 3, cupiMuseo.darObraActual( ).darCantidadEvaluaciones( ));
            assertEquals("No se almacena la calificaci�n correctamente, el promedio no es correcto.", 4, cupiMuseo.darObraActual( ).darPromedioCalificacion( ));
            
            
            
        }
        catch( Exception e )
        {
            fail( "No deber�a generar excepci�n" );
        }
    }
}