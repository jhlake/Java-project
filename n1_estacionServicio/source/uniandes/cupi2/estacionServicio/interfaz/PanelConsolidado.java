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

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel que muestra la información consolidada de la estación de servicio.
 */
public class PanelConsolidado extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta del número de galones vendido.
     */
    private JLabel etiquetaNumGalones;

    /**
     * Etiqueta del dinero total recaudado.
     */
    private JLabel etiquetaDineroRecaudado;

    /**
     * Etiqueta del costo promedio del galón de acuerdo a las ventas realizadas.
     */
    private JLabel etiquetaPromedioGalon;

    /**
     * Campo de texto para el número de galones vendidos.
     */
    private JTextField txtNumGalones;

    /**
     * Campo de texto para el dinero total recaudado.
     */
    private JTextField txtDineroRecaudado;

    /**
     * Campo de texto para el costo promedio del galón de acuerdo a las ventas realizadas.
     */
    private JTextField txtPromedioGalon;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazEstacionServicio principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con la información consolidada de la estación de servicio.<br>
     * <b>post: </b> El panel quedó inicializado. <br> 
     * @param pPrincipal Referencia a la clase principal de la interfaz. pPrincipal != null.
     */
    public PanelConsolidado( InterfazEstacionServicio pPrincipal )
    {
        principal = pPrincipal;
        etiquetaDineroRecaudado = new JLabel( " Dinero total recaudado: " );
        etiquetaDineroRecaudado.setHorizontalAlignment( JLabel.LEFT );

        txtDineroRecaudado = new JTextField( " $ " );
        txtDineroRecaudado.setEditable( false );

        etiquetaNumGalones = new JLabel( " Galones vendidos: " );
        etiquetaNumGalones.setHorizontalAlignment( JLabel.LEFT );

        txtNumGalones = new JTextField( );
        txtNumGalones.setEditable( false );

        etiquetaPromedioGalon = new JLabel( " Costo promedio por galón: " );
        etiquetaPromedioGalon.setHorizontalAlignment( JLabel.LEFT );

        txtPromedioGalon = new JTextField( " $ 0" );
        txtPromedioGalon.setEditable( false );

        setBorder( BorderFactory.createTitledBorder( "Consolidado total" ) );
        setLayout( new GridLayout( 2, 3 ) );

        add( etiquetaNumGalones );
        add( etiquetaDineroRecaudado );
        add( etiquetaPromedioGalon );
        add( txtNumGalones );
        add( txtDineroRecaudado );
        add( txtPromedioGalon );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza los campos de interfaz con los consolidados de la estación.<br>
     * @param pTotalDineroRecaudado Dinero recaudado de los tres surtidores. pTotalDineroRecaudado > 0.
     * @param pTotalGalones Total de galones vendidos de los tres surtidores. pTotalGaones > 0.
     * @param pCostoPromedioGalon Costo promedio por galón. pCostoPromedioGalon > 0.
     */
    public void actualizar( double pTotalDineroRecaudado, double pTotalGalones, double pCostoPromedioGalon )
    {
        txtDineroRecaudado.setText( " $ " + principal.formatearValorReal( pTotalDineroRecaudado, 2 ) );
        txtNumGalones.setText( " " + principal.formatearValorReal( pTotalGalones, 2 ) );
        if( pTotalGalones > 0 )
        {
            txtPromedioGalon.setText( " $ " + principal.formatearValorReal( pCostoPromedioGalon, 2 ) );
        }
        else
        {
            txtPromedioGalon.setText( " $ 0" );
        }
    }
}
