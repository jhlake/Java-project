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
package uniandes.cupi2.estacionServicio.mundo;

/**
 * Clase que representa un surtidor de combustible.
 */
public class Surtidor
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tipo de combustible.
     */
    private String tipo;

    /**
     * Precio del gal�n de combustible.
     */
    private double precioGalon;

    /**
     * N�mero de galones de combustible vendidos.
     */
    // TODO Parte2 PuntoA: Declarar el atributo para modelar el n�mero de galones de combustible vendidos.
    private double galonesVendidos;
    /**
     * Dinero total recaudado por las ventas de combustible.
     */
    // TODO Parte2 PuntoB: Declarar el atributo para modelar el dinero total recaudado por las ventas de combustible. .
    private double totalDinero;
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el surtidor de combustible. <br>
     * <b>post: </b> El surtidor se inicializ� con n�mero de galones vendidos y dinero recaudado en cero.<br>
     * @param pTipo Tipo de combustible. pTipo != null && pTipo != "" && (pTipo == Corriente || pTipo == Extra || pTipo == Diesel).
     * @param pPrecioGalon Precio de un gal�n de combustible.
     */
    public void inicializar( String pTipo, double pPrecioGalon )
    {
        tipo = pTipo;
        precioGalon = pPrecioGalon;
        // TODO Parte2 PuntoC: Completar el m�todo seg�n la documentaci�n. El n�mero de galones vendidos y el dinero recaudado deben inicializarse en cero.
        galonesVendidos = 0;
        totalDinero = 0;
    }

    /**
     * Retorna el precio del gal�n de combustible.
     * @return Precio del gal�n.
     */
    public double darPrecioGalon( )
    {
        return precioGalon;
    }

    /**
     * Retorna el tipo de combustible ofrecido por el surtidor.
     * @return Tipo de combustible.
     */
    public String darTipoCombustible( )
    {
        return tipo;
    }

    /**
     * Retorna el dinero total recaudado por las ventas realizadas.
     * @return Dinero total recaudado.
     */
    public double darDineroRecaudado( )
    {
     // TODO Parte2 PuntoD: Completar el m�todo seg�n la documentaci�n.
    	return totalDinero;
    }

    /**
     * Retorna el n�mero de galones vendidos.
     * @return N�mero de galones vendidos por el surtidor.
     */
    public double darNumeroGalonesVendidos( )
    {
     // TODO Parte2 PuntoE: Completar el m�todo seg�n la documentaci�n.
    	
    	return galonesVendidos;
    }

    /**
     * Reinicia el surtidor.<br>
     * <b>post: </b> El n�mero de galones vendidos y el dinero recaudado quedaron en cero.
     */
    public void reiniciar( )
    {
     // TODO Parte2 PuntoF: Completar el m�todo seg�n la documentaci�n.
    	totalDinero = 0;
    	galonesVendidos = 0;
    }

    /**
     * Registra una nueva venta de combustible para un veh�culo particular. <br>
     * <b>post: </b> Se increment� numeroGalones de acuerdo al dinero de carga y se aument� el dinero recaudado seg�n el dinero que entra como par�metro. <br>
     * @param pDinero Cantidad de dinero a registrar. pDinero > 0.
     * @return N�mero de galones vendidos.
     */
    public double registrarVentaParticular( double pDinero )
    {

        double numGalones = pDinero / precioGalon;

     // TODO Parte2 PuntoG: Completar el m�todo seg�n la documentaci�n.
        
        totalDinero = totalDinero + pDinero;
        galonesVendidos = numGalones + galonesVendidos;

        return numGalones;
    }

    /**
     * Registra una nueva venta de combustible para un veh�culo de servicio p�blico <br>
     * <b>post: </b> Se increment� numeroGalones de acuerdo al dinero de carga y se aument� el dinero recaudado seg�n el dinero que entra como par�metro. <br>
     * @param pDinero Cantidad de dinero a registrar. pDinero > 0.
     * @return N�mero de galones vendidos.
     */
    public double registrarVentaServicioPublico( double pDinero )
    {

        double galonDescuento = precioGalon * 0.95;

        double numGalones = pDinero / galonDescuento;

     // TODO Parte2 PuntoH: Completar el m�todo seg�n la documentaci�n.
        totalDinero = totalDinero + pDinero;
        galonesVendidos = numGalones        +         galonesVendidos;

        return numGalones;
    }

}
