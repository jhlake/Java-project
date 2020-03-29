/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Proyecto Cupi2
 * Ejercicio: n1_estacionServicio
 * Autor: Equipo Cupi2 2015.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.estacionServicio.test;

import uniandes.cupi2.estacionServicio.mundo.Surtidor;
import junit.framework.TestCase;

/**
 * Clase usada para verificar que los métodos de la clase Surtidor estén correctamente implementados.
 */
public class SurtidorTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se harán las pruebas.
     */
    private Surtidor surtidor;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo surtidor vacío.
     */
    private void setupEscenario1( )
    {
        surtidor = new Surtidor( );
        surtidor.inicializar( "Combustible1", 5000 );
    }

    /**
     * Prueba 1: verifica el método constructor.<br>
     * <b> Métodos a probar: </b> <br>
     * Surtidor<br>
     * darPrecioGalon<br>
     * darDineroRecaudado<br>
     * darNumeroGalonesVendidos<br>
     * darTipoCombustible <br>
     * <b> Caso de prueba 1:: </b> <br>
     * Los valores del surtidor corresponden a los del constructor.<br>
     */
    public void testInicializar( )
    {
        setupEscenario1( );

        assertTrue( "No se crea con el costo del galón dado por parámetro.", surtidor.darPrecioGalon( ) == 5000 );
        assertTrue( "No inicializa el dinero recaudado en cero.", surtidor.darDineroRecaudado( ) == 0 );
        assertTrue( "No inicializa el número de galones vendido en cero.", surtidor.darNumeroGalonesVendidos( ) == 0 );
        assertTrue( "No se crea con el tipo dado por parámetro.", surtidor.darTipoCombustible( ).equals( "Combustible1" ) );
    }

    /**
     * Prueba 2: verifica el método registarVentaParticular.<br>
     * <b> Métodos a probar: </b> <br>
     * registarVentaParticular<br>
     * darDineroRecaudado<br>
     * darNumeroGalonesVendidos<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra una venta particular.<br>
     */
    public void testRegistrarVentaParticular( )
    {
        setupEscenario1( );

        surtidor.registrarVentaParticular( 25000 );

        assertTrue( "No incrementa el dinero recaudado correctamente.", surtidor.darDineroRecaudado( ) == 25000 );
        assertTrue( "No aumenta el número de galones vendido correctamente.", surtidor.darNumeroGalonesVendidos( ) == 5 );

        surtidor.registrarVentaParticular( 10000 );

        assertTrue( "No incrementa el dinero recaudado correctamente.", surtidor.darDineroRecaudado( ) == 35000 );
        assertTrue( "No aumenta el número de galones vendido correctamente.", surtidor.darNumeroGalonesVendidos( ) == 7 );
    }

    /**
     * Prueba 3: verifica el método registrarVentaServicioPublico.<br>
     * <b> Métodos a probar: </b> <br>
     * registrarVentaServicioPublico<br>
     * darDineroRecaudado<br>
     * darNumeroGalonesVendidos<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra una venta de servicio público.<br>
     */
    public void testRegistrarVentaPublico( )
    {
        setupEscenario1( );

        double galonesVendidos = surtidor.registrarVentaServicioPublico( 25000 );

        double numGalones = ( double )25000 / 4750;
        assertTrue( "No incrementa el dinero recaudado correctamente.", surtidor.darDineroRecaudado( ) == 25000 );
        assertEquals( "El número de galones vendidos es incorrecto.", numGalones, galonesVendidos );
        assertEquals( "No incrementa el número de galones vendidos totales correctamente.", numGalones, surtidor.darNumeroGalonesVendidos( ) );

        galonesVendidos = surtidor.registrarVentaServicioPublico( 10000 );

        numGalones = ( double )10000 / 4750;
        double total = ( double )25000 / 4750 + numGalones;
        assertTrue( "No incrementa el dinero recaudado correctamente.", surtidor.darDineroRecaudado( ) == 35000 );
        assertEquals( "El número de galones vendido es incorrecto.", numGalones, galonesVendidos );
        assertEquals( "No incrementa el número de galones vendidos totales correctamente.", total, surtidor.darNumeroGalonesVendidos( ) );

    }

    private void assertEquals(String string, double numGalones,
			double galonesVendidos) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Prueba 4: verifica el método reiniciar.<br>
     * <b> Métodos a probar: </b> <br>
     * reiniciar<br>
     * registarVentaParticular<br>
     * darDineroRecaudado<br>
     * darNumeroGalonesVendidos<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se reinician los valores del surtidor.<br>
     */
    public void testReiniciar( )
    {
        setupEscenario1( );

        surtidor.registrarVentaParticular( 12 );
        surtidor.reiniciar( );

        assertTrue( "No reinicia el dinero recaudado en cero.", surtidor.darDineroRecaudado( ) == 0 );
        assertTrue( "No reinicia el número de galones vendido en cero.", surtidor.darNumeroGalonesVendidos( ) == 0 );
    }
}
