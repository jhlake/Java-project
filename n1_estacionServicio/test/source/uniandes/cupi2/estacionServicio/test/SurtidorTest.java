/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
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
 * Clase usada para verificar que los m�todos de la clase Surtidor est�n correctamente implementados.
 */
public class SurtidorTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private Surtidor surtidor;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo surtidor vac�o.
     */
    private void setupEscenario1( )
    {
        surtidor = new Surtidor( );
        surtidor.inicializar( "Combustible1", 5000 );
    }

    /**
     * Prueba 1: verifica el m�todo constructor.<br>
     * <b> M�todos a probar: </b> <br>
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

        assertTrue( "No se crea con el costo del gal�n dado por par�metro.", surtidor.darPrecioGalon( ) == 5000 );
        assertTrue( "No inicializa el dinero recaudado en cero.", surtidor.darDineroRecaudado( ) == 0 );
        assertTrue( "No inicializa el n�mero de galones vendido en cero.", surtidor.darNumeroGalonesVendidos( ) == 0 );
        assertTrue( "No se crea con el tipo dado por par�metro.", surtidor.darTipoCombustible( ).equals( "Combustible1" ) );
    }

    /**
     * Prueba 2: verifica el m�todo registarVentaParticular.<br>
     * <b> M�todos a probar: </b> <br>
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
        assertTrue( "No aumenta el n�mero de galones vendido correctamente.", surtidor.darNumeroGalonesVendidos( ) == 5 );

        surtidor.registrarVentaParticular( 10000 );

        assertTrue( "No incrementa el dinero recaudado correctamente.", surtidor.darDineroRecaudado( ) == 35000 );
        assertTrue( "No aumenta el n�mero de galones vendido correctamente.", surtidor.darNumeroGalonesVendidos( ) == 7 );
    }

    /**
     * Prueba 3: verifica el m�todo registrarVentaServicioPublico.<br>
     * <b> M�todos a probar: </b> <br>
     * registrarVentaServicioPublico<br>
     * darDineroRecaudado<br>
     * darNumeroGalonesVendidos<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra una venta de servicio p�blico.<br>
     */
    public void testRegistrarVentaPublico( )
    {
        setupEscenario1( );

        double galonesVendidos = surtidor.registrarVentaServicioPublico( 25000 );

        double numGalones = ( double )25000 / 4750;
        assertTrue( "No incrementa el dinero recaudado correctamente.", surtidor.darDineroRecaudado( ) == 25000 );
        assertEquals( "El n�mero de galones vendidos es incorrecto.", numGalones, galonesVendidos );
        assertEquals( "No incrementa el n�mero de galones vendidos totales correctamente.", numGalones, surtidor.darNumeroGalonesVendidos( ) );

        galonesVendidos = surtidor.registrarVentaServicioPublico( 10000 );

        numGalones = ( double )10000 / 4750;
        double total = ( double )25000 / 4750 + numGalones;
        assertTrue( "No incrementa el dinero recaudado correctamente.", surtidor.darDineroRecaudado( ) == 35000 );
        assertEquals( "El n�mero de galones vendido es incorrecto.", numGalones, galonesVendidos );
        assertEquals( "No incrementa el n�mero de galones vendidos totales correctamente.", total, surtidor.darNumeroGalonesVendidos( ) );

    }

    private void assertEquals(String string, double numGalones,
			double galonesVendidos) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Prueba 4: verifica el m�todo reiniciar.<br>
     * <b> M�todos a probar: </b> <br>
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
        assertTrue( "No reinicia el n�mero de galones vendido en cero.", surtidor.darNumeroGalonesVendidos( ) == 0 );
    }
}
