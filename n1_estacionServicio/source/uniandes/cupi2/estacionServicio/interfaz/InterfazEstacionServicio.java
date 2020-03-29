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

package uniandes.cupi2.estacionServicio.interfaz;

import java.awt.*;
import java.text.*;
import javax.swing.*;

import uniandes.cupi2.estacionServicio.mundo.EstacionServicio;

/**
 * Ventana principal de la aplicación.
 */
public class InterfazEstacionServicio extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private EstacionServicio estacionServicio;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las opciones.
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel con la imagen del encabezado.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con la información consolidada de ventas.
     */
    private PanelConsolidado panelConsolidado;

    /**
     * Panel con la información del surtidor 1.
     */
    private PanelSurtidor panelSurtidor1;

    /**
     * Panel con la información del surtidor 2.
     */
    private PanelSurtidor panelSurtidor2;

    /**
     * Panel con la información del surtidor 3.
     */
    private PanelSurtidor panelSurtidor3;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana principal. <br>
     */
    public InterfazEstacionServicio( )
    {
        // Crea la clase principal.
        estacionServicio = new EstacionServicio( );
        estacionServicio.inicializar( );

        // Construye la forma.
        setLayout( new BorderLayout( ) );
        setSize( 800, 600 );
        setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( " Estación de servicio " );

        // Creación de los paneles.
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelOpciones = new PanelOpciones( this );
        add( panelOpciones, BorderLayout.SOUTH );

        JPanel centro = new JPanel( );
        centro.setLayout( new BorderLayout( ) );

        JPanel surtidores = new JPanel( );
        surtidores.setLayout( new GridLayout( 1, 3 ) );

        panelConsolidado = new PanelConsolidado( this );
        centro.add( panelConsolidado, BorderLayout.SOUTH );

        panelSurtidor1 = new PanelSurtidor( this, 1 );
        surtidores.add( panelSurtidor1 );

        panelSurtidor2 = new PanelSurtidor( this, 2 );
        surtidores.add( panelSurtidor2 );

        panelSurtidor3 = new PanelSurtidor( this, 3 );
        surtidores.add( panelSurtidor3 );

        centro.add( surtidores, BorderLayout.CENTER );
        add( centro, BorderLayout.CENTER );

        // Centrar la ventana.
        setLocationRelativeTo( null );

        // Actualizar los valores de la estación y surtidores.
        actualizar( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Reinicia las ventas de la estación.
     */
    public void reiniciar( )
    {
        estacionServicio.reiniciar( );
        actualizar( );
    }

    /**
     * Registra la venta de combustible.<br>
     * @param pNumSurtidor Número del surtidor al que se le asocia la venta.
     */
    public void registrarVenta( int pNumSurtidor )
    {
        String dineroS = JOptionPane.showInputDialog( this, "Ingrese el valor de la venta", "Registrar venta " + pNumSurtidor + ".", JOptionPane.INFORMATION_MESSAGE );
        try
        {
            if( dineroS != null )
            {
                double dinero = Double.parseDouble( dineroS );

                if( dinero > 0 )
                {
                    Object[] tipoV = { "Particular", "Servicio público" };
                    String tipo = ( String )JOptionPane.showInputDialog( this, "Tipo de vehículo:", "Tipo", JOptionPane.QUESTION_MESSAGE, null, tipoV, "Particular" );

                    if( tipo != null )
                    {
                        double numGalones = 0;
                        if( tipo.equals( "Particular" ) )
                        {
                            if( pNumSurtidor == 1 )
                            {
                                numGalones = estacionServicio.registrarVentaParticularSurtidor1( dinero );
                            }
                            else if( pNumSurtidor == 2 )
                            {
                                numGalones = estacionServicio.registrarVentaParticularSurtidor2( dinero );
                            }
                            else if( pNumSurtidor == 3 )
                            {
                                numGalones = estacionServicio.registrarVentaParticularSurtidor3( dinero );
                            }
                        }
                        else
                        {
                            if( pNumSurtidor == 1 )
                            {
                                numGalones = estacionServicio.registrarVentaPublicoSurtidor1( dinero );
                            }
                            else if( pNumSurtidor == 2 )
                            {
                                numGalones = estacionServicio.registrarVentaPublicoSurtidor2( dinero );
                            }
                            else if( pNumSurtidor == 3 )
                            {
                                numGalones = estacionServicio.registrarVentaPublicoSurtidor3( dinero );
                            }
                        }
                        actualizar( );
                        JOptionPane.showMessageDialog( this, formatearValorReal( numGalones, 23 ) + " galones vendidos", "Registrar venta", JOptionPane.INFORMATION_MESSAGE );
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog( this, "La cantidad de dinero de la venta debe ser mayor a cero.", "Registrar venta", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "Ingrese valores numéricos.", "Registrar venta", JOptionPane.ERROR_MESSAGE );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza los campos de la interfaz con la información de la estación de servicio y sus surtidores.
     */
    private void actualizar( )
    {
        panelConsolidado.actualizar( estacionServicio.darTotalDineroRecaudado( ), estacionServicio.darTotalGalonesVendidos( ), estacionServicio.darCostoPromedioGalon( ) );
        panelSurtidor1.actualizar( estacionServicio.darSurtidor1( ).darTipoCombustible( ), estacionServicio.darSurtidor1( ).darPrecioGalon( ), estacionServicio.darSurtidor1( ).darNumeroGalonesVendidos( ), estacionServicio.darSurtidor1( )
                .darDineroRecaudado( ) );
        panelSurtidor2.actualizar( estacionServicio.darSurtidor2( ).darTipoCombustible( ), estacionServicio.darSurtidor2( ).darPrecioGalon( ), estacionServicio.darSurtidor2( ).darNumeroGalonesVendidos( ), estacionServicio.darSurtidor2( )
                .darDineroRecaudado( ) );
        panelSurtidor3.actualizar( estacionServicio.darSurtidor3( ).darTipoCombustible( ), estacionServicio.darSurtidor3( ).darPrecioGalon( ), estacionServicio.darSurtidor3( ).darNumeroGalonesVendidos( ), estacionServicio.darSurtidor3( )
                .darDineroRecaudado( ) );
    }

    /**
     * Formatea un valor numérico real para presentar en la interfaz. <br>
     * @param pValor Valor numérico a ser formateado.
     * @param pNumDigitos Número de decimales deseados.
     * @return Cadena con el valor formateado con puntos y signos.
     */
    public String formatearValorReal( double pValor, int pNumDigitos )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( " ###,###.##" );
        df.setMinimumFractionDigits( 0 );
        return df.format( pValor );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = estacionServicio.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = estacionServicio.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.<br>
     * @param pArgs Argumentos para la ejecución de la aplicación. En este caso no son necesarios.
     */
    public static void main( String[] pArgs )
    {
        InterfazEstacionServicio interfaz = new InterfazEstacionServicio( );
        interfaz.setVisible( true );
    }

}