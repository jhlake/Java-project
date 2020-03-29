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

package uniandes.cupi2.estacionServicio.mundo;

/**
 * Clase que representa la estación de servicio.
 */
public class EstacionServicio
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Surtidor número 1.
     */
    private Surtidor surtidor1;

    /**
     * Surtidor número 2.
     */
    // TODO Parte1 PuntoA: Declarar el atributo para modelar el surtidor número 2.
    private Surtidor surtidor2;
    
    /**
     * Surtidor número 3.
     */
    // TODO Parte1 PuntoB: Declarar el atributo para modelar el surtidor número 3.
    private Surtidor surtidor3;
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los surtidores de combustible de la estación de servicio. <br>
     * <b>post: </b>Los tres surtidores se inicializaron con los valores tipo y precio por galón.<br>
     * Surtidor 1 - Tipo: Corriente, Precio galón: 7800. <br>
     * Surtidor 2 - Tipo: Extra, Precio galón: 9500. <br>
     * Surtidor 3 - Tipo: Diesel, Precio galón: 6700. La materia se inicializó con los valores nombre, departamento y número de créditos dados por parámetro.
     */
    public void inicializar( )
    {

        // Inicializar el surtidor 1.
        surtidor1 = new Surtidor( );
        surtidor1.inicializar( "Corriente", 7800 );
        // Inicializar el surtidor 2.
        // TODO Parte1 PuntoC: Crear e inicializar el surtidor número 2.
        surtidor2 = new Surtidor();
        surtidor2.inicializar("Extra", 9500);
        // Inicializar el surtidor 3.
        // TODO Parte1 PuntoD: Crear e inicializar el surtidor número 3.
        surtidor3 = new Surtidor();
        surtidor3.inicializar("Diesel", 6700);
    }

    /**
     * Retorna el surtidor 1 de la estación de servicio.<br>
     * @return Surtidor 1 de la estación de servicio.
     */
    public Surtidor darSurtidor1( )
    {
        return surtidor1;
    }

    /**
     * Retorna el surtidor 2 de la estación de servicio.<br>
     * @return Surtidor 2 de la estación de servicio.
     */
    public Surtidor darSurtidor2( )
    {
        // TODO Parte1 PuntoE: Completar el método según la documentación.
    	return surtidor2;
    }

    /**
     * Retorna el surtidor 3 de la estación de servicio.<br>
     * @return Surtidor 3 de la estación de servicio.
     */
    public Surtidor darSurtidor3( )
    {
        // TODO Parte1 PuntoF: Completar el método según la documentación.
    	return surtidor3;
    }

    /**
     * Retorna el total de galones vendidos por los tres surtidores.<br>
     * @return Total de galones vendidos de los tres surtidores.
     */
    public double darTotalGalonesVendidos( )
    {
        // TODO Parte1 PuntoG: Completar el método según la documentación.
        double galonesEnTotal = 0;
    	galonesEnTotal = Double.parseDouble(String.valueOf(surtidor1.darNumeroGalonesVendidos()))+Double.parseDouble(String.valueOf(surtidor2.darNumeroGalonesVendidos()))+Double.parseDouble(String.valueOf(surtidor3.darNumeroGalonesVendidos()));
    	return galonesEnTotal; 
   }

    /**
     * Retorna el dinero total recaudado por las ventas realizadas.<br>
     * @return Dinero recaudado de los tres surtidores.
     */
    public double darTotalDineroRecaudado( )
    {
        // TODO Parte1 PuntoH: Completar el método según la documentación.
        double dineroEnTotal = 0;
    	dineroEnTotal = Double.parseDouble(String.valueOf(surtidor1.darDineroRecaudado()))+Double.parseDouble(String.valueOf(surtidor2.darDineroRecaudado()))+Double.parseDouble(String.valueOf(surtidor3.darDineroRecaudado()));
    	return dineroEnTotal;  
    }

    /**
     * Retorna el costo promedio de un galón, según los galones vendidos. <br>
     * <b> pre: </b> La cantidad total de galones vendidos es mayor a cero.<br>
     * @return Costo promedio por galón.
     */
    public double darCostoPromedioGalon( )
    {
        double promedio = darTotalDineroRecaudado( ) / darTotalGalonesVendidos( );
        return promedio;
    }

    /**
     * Registra una nueva venta a vehículo particular del surtidor 1. <br>
     * <b>post: </b> Se registró la venta al surtidor 1.<br>
     * @param pDinero Cantidad de dinero. pDinero > 0.
     * @return Número de galones vendidos.
     */
    public double registrarVentaParticularSurtidor1( double pDinero )
    {
        return surtidor1.registrarVentaParticular( pDinero );
    }

    /**
     * Registra una nueva venta a vehículo particular del surtidor 2. <br>
     * <b>post: </b> Se registró la venta al surtidor 2.<br>
     * @param pDinero Cantidad de dinero. pDinero > 0.
     * @return Número de galones vendidos.
     */
    public double registrarVentaParticularSurtidor2( double pDinero )
    {
        // TODO Parte1 PuntoI: Completar el método según la documentación.
    	return surtidor2.registrarVentaParticular(pDinero);
    }

    /**
     * Registra una nueva venta a vehículo particular del surtidor 3. <br>
     * <b>post: </b> Se registró la venta al surtidor 3.<br>
     * @param pDinero Cantidad de dinero. pDinero > 0.
     * @return Número de galones vendidos.
     */
    public double registrarVentaParticularSurtidor3( double pDinero )
    {
        // TODO Parte1 PuntoJ: Completar el método según la documentación.
    	return surtidor3.registrarVentaParticular(pDinero);
    }

    /**
     * Registra una nueva venta a vehículo de servicio público del surtidor 1.<br>
     * <b>post: </b> Se registró la venta del surtidor 1.<br>
     * @param pDinero Cantidad de dinero. pDinero > 0.
     * @return Número de galones vendidos.
     */
    public double registrarVentaPublicoSurtidor1( double pDinero )
    {
        return surtidor1.registrarVentaServicioPublico( pDinero );
    }

    /**
     * Registra una nueva venta a vehículo de servicio público del surtidor 2.<br>
     * <b>post: </b> Se registró la venta del surtidor 2.<br>
     * @param pDinero Cantidad de dinero. pDinero > 0.
     * @return Número de galones vendidos.
     */
    public double registrarVentaPublicoSurtidor2( double pDinero )
    {
        // TODO Parte1 PuntoK: Completar el método según la documentación.
    	return surtidor2.registrarVentaServicioPublico(pDinero);
    }

    /**
     * Registra una nueva venta a vehículo de servicio público del surtidor 3.<br>
     * <b>post: </b> Se registró la venta del surtidor 3.<br>
     * @param pDinero Cantidad de dinero. pDinero > 0.
     * @return Número de galones vendidos.
     */
    public double registrarVentaPublicoSurtidor3( double pDinero )
    {
        // TODO Parte1 PuntoL: Completar el método según la documentación.
    	return surtidor3.registrarVentaServicioPublico(pDinero);
    }

    /**
     * Reinicia todos los surtidores.
     */
    public void reiniciar( )
    {
        surtidor1.reiniciar( );
        // TODO Parte1 PuntoM: Reiniciar el surtidor 2.
        surtidor2.reiniciar();
        // TODO Parte1 PuntoN: Reiniciar el surtidor 3.
        surtidor3.reiniciar();
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * Método para la extensión 2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }

}