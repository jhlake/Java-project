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

import junit.framework.TestCase;
import uniandes.cupi2.estacionServicio.mundo.EstacionServicio;

/**
 * Clase usada para verificar que los m�todos de la clase EstacionServicio est�n correctamente implementados.
 */
public class EstacionServicioTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private EstacionServicio estacionServicio;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva EstacionServicio vac�a.
     */
    private void setupEscenario1( )
    {
        estacionServicio = new EstacionServicio( );
        estacionServicio.inicializar( );
    }

    /**
     * Construye una nueva estaci�n de servicio con ventas.
     */
    private void setupEscenario2( )
    {
        estacionServicio = new EstacionServicio( );
        estacionServicio.inicializar( );

        estacionServicio.registrarVentaParticularSurtidor1( 7800 );
        estacionServicio.registrarVentaPublicoSurtidor2( 18050 );
        estacionServicio.registrarVentaParticularSurtidor3( 67000 );
    }

    /**
     * Prueba 1: verifica el m�todo constructor.<br>
     * <b> M�todos a probar: </b> <br>
     * EstacionServicio<br>
     * darSurtidor1<br>
     * darSurtidor2<br>
     * darSurtidor3<br>
     * <b> Caso de prueba 1: </b> <br>
     * Los valores de la EstacionServicio corresponden a los del m�todo inicializar.<br>
     */
    public void testInicializar( )
    {
        setupEscenario1( );

        assertNotNull( "No cre� el surtidor 1.", estacionServicio.darSurtidor1( ) );
        assertNotNull( "No cre� el surtidor 2.", estacionServicio.darSurtidor2( ) );
        assertNotNull( "No cre� el surtidor 3.", estacionServicio.darSurtidor3( ) );
        assertEquals( "El total de dinero recuadado no es el esperado.", 0.0 ,  estacionServicio.darTotalDineroRecaudado( ) );
        assertEquals( "El total de galones vendidos no es el esperado.", 0.0 ,  estacionServicio.darTotalGalonesVendidos( ) );

    }

    private void assertEquals(String string, double d,
			double darTotalDineroRecaudado) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Prueba 2: verifica el m�todo darTotalGalones.<br>
     * <b> M�todos a probar: </b> <br>
     * darTotalGalones<br>
     * <b> Caso de prueba 1: </b> <br>
     * La estaci�n de servicio posee galones vendidos.<br>
     */
    public void testDarTotalGalonesVendidos( )
    {
        setupEscenario2( );

        assertTrue( "No calcul� correctamente el n�mero total de galones vendidos.", estacionServicio.darTotalGalonesVendidos( ) == 13 );
    }

    /**
     * Prueba 3: verifica el m�todo darTotalDineroRecaudado.<br>
     * <b> M�todos a probar: </b> <br>
     * darTotalDineroRecaudado<br>
     * <b> Caso de prueba 1: </b> <br>
     * La estaci�n de servicio posee galones vendidos.<br>
     */
    public void testDarTotalDineroRecaudado( )
    {
        setupEscenario2( );

        assertEquals( "No calcul� correctamente el dinero total recaudado.", 92850.0, estacionServicio.darTotalDineroRecaudado( ) );
    }

    /**
     * Prueba 4: verifica el m�todo registrarVentaParticularSurtidor1.<br>
     * <b> M�todos a probar: </b> <br>
     * registrarVentaParticularSurtidor1<br>
     * darNumeroGalonesVendidos<br>
     * darDineroRecaudado<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra venta como particular del surtidor 1.<br>
     */
    public void testRegistrarVentaParticularSurtidor1( )
    {
        setupEscenario1( );

        estacionServicio.registrarVentaParticularSurtidor1( 15600 );

        assertEquals( "No registr� la venta correctamente.", 2.0, estacionServicio.darSurtidor1( ).darNumeroGalonesVendidos( ) );
        assertEquals( "No registr� la venta correctamente.", 15600.0, estacionServicio.darSurtidor1( ).darDineroRecaudado( ) );
    }

    /**
     * Prueba 5: verifica el m�todo registrarVentaParticularSurtidor2.<br>
     * <b> M�todos a probar: </b> <br>
     * registrarVentaParticularSurtidor2<br>
     * darNumeroGalonesVendidos<br>
     * darDineroRecaudado<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra venta como particular del surtidor 2.<br>
     */
    public void testRegistrarVentaParticularSurtidor2( )
    {
        setupEscenario1( );

        estacionServicio.registrarVentaParticularSurtidor2( 9500 );

        assertEquals( "No registr� la venta correctamente.", 1.0, estacionServicio.darSurtidor2( ).darNumeroGalonesVendidos( ) );
        assertEquals( "No registr� la venta correctamente.", 9500.0, estacionServicio.darSurtidor2( ).darDineroRecaudado( ) );
    }

    /**
     * Prueba 6: verifica el m�todo registrarVentaParticularSurtidor3.<br>
     * <b> M�todos a probar: </b> <br>
     * registrarVentaParticularSurtidor3<br>
     * darNumeroGalonesVendidos<br>
     * darDineroRecaudado<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra venta como particular del surtidor 3.<br>
     */
    public void testRegistrarVentaParticularSurtidor3( )
    {
        setupEscenario1( );

        estacionServicio.registrarVentaParticularSurtidor3( 20100 );

        assertEquals( "No registr� la venta correctamente.", 3.0, estacionServicio.darSurtidor3( ).darNumeroGalonesVendidos( ) );
        assertEquals( "No registr� la venta correctamente.", 20100.0, estacionServicio.darSurtidor3( ).darDineroRecaudado( ) );
    }

    /**
     * Prueba 7: verifica el m�todo registrarVentaPublicoSurtidor1.<br>
     * <b> M�todos a probar: </b> <br>
     * registrarVentaPublicoSurtidor1<br>
     * darNumeroGalonesVendidos<br>
     * darDineroRecaudado<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra venta como servicio p�blico del surtidor 1.<br>
     */
    public void testRegistrarVentaPublicoSurtidor1( )
    {
        setupEscenario1( );

        estacionServicio.registrarVentaPublicoSurtidor1( 18525 );

        assertEquals( "No registr� la venta correctamente.", 2.5, estacionServicio.darSurtidor1( ).darNumeroGalonesVendidos( ) );
        assertEquals( "No registr� la venta correctamente.", 18525.0, estacionServicio.darSurtidor1( ).darDineroRecaudado( ) );
    }

    /**
     * Prueba 8: verifica el m�todo registrarVentaPublicoSurtidor2.<br>
     * <b> M�todos a probar: </b> <br>
     * registrarVentaPublicoSurtidor2<br>
     * darNumeroGalonesVendidos<br>
     * darDineroRecaudado<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra venta como servicio p�blico del surtidor 2.<br>
     */
    public void testRegistrarVentaPublicoSurtidor2( )
    {
        setupEscenario1( );

        estacionServicio.registrarVentaPublicoSurtidor2( 9025 );

        assertEquals( "No registr� la venta correctamente.", 1.0, estacionServicio.darSurtidor2( ).darNumeroGalonesVendidos( ) );
        assertEquals( "No registr� la venta correctamente.", 9025.0, estacionServicio.darSurtidor2( ).darDineroRecaudado( ) );
    }

    /**
     * Prueba 9: verifica el m�todo registrarVentaPublicoSurtidor3.<br>
     * <b> M�todos a probar: </b> <br>
     * registrarVentaPublicoSurtidor3<br>
     * darNumeroGalonesVendidos<br>
     * darDineroRecaudado<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se registra venta como servicio p�blico del surtidor 3.<br>
     */
    public void testRegistrarVentaPublicoSurtidor3( )
    {
        setupEscenario1( );

        estacionServicio.registrarVentaPublicoSurtidor3( 63650 );

        assertEquals( "No registr� la venta correctamente.", 10.0, estacionServicio.darSurtidor3( ).darNumeroGalonesVendidos( ) );
        assertEquals( "No registr� la venta correctamente.", 63650.0, estacionServicio.darSurtidor3( ).darDineroRecaudado( ) );
    }
    /**
     * Prueba 10: verifica el m�todo reiniciar.<br>
     * <b> M�todos a probar: </b> <br>
     * reiniciar<br>
     * darDineroRecaudado<br>
     * darNumeroGalonesVendidos<br>
     * <b> Caso de prueba 1: </b> <br>
     * Se reinician los valores de la estaci�n de servicio.<br>
     */
    public void testReiniciar( )
    {
        setupEscenario2( );

        estacionServicio.reiniciar( );

        assertTrue( "No reinici� el dinero recaudado por ventas del surtidor 1.", estacionServicio.darSurtidor1( ).darDineroRecaudado( ) == 0 );
        assertTrue( "No reinici� el n�mero de galones vendidos del surtidor 1.", estacionServicio.darSurtidor1( ).darNumeroGalonesVendidos( ) == 0 );

        assertTrue( "No reinici� el dinero recaudado por ventas del surtidor 2.", estacionServicio.darSurtidor2( ).darDineroRecaudado( ) == 0 );
        assertTrue( "No reinici� el n�mero de galones vendidos del surtidor 2.", estacionServicio.darSurtidor2( ).darNumeroGalonesVendidos( ) == 0 );

        assertTrue( "No reinici� el dinero recaudado por ventas del surtidor 3.", estacionServicio.darSurtidor3( ).darDineroRecaudado( ) == 0 );
        assertTrue( "No reinici� el n�mero de galones vendidos del surtidor 3.", estacionServicio.darSurtidor3( ).darNumeroGalonesVendidos( ) == 0 );
        
        assertEquals( "El total de dinero recuadado no es el esperado.", 0.0 ,  estacionServicio.darTotalDineroRecaudado( ) );
        assertEquals( "El total de galones vendidos no es el esperado.", 0.0 ,  estacionServicio.darTotalGalonesVendidos( ) );
    }
}