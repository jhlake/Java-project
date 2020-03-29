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
package uniandes.cupi2.estacionServicio.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Panel donde se muestra la informaci�n del surtidor de combustible.
 */
public class PanelSurtidor extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de registrar venta.
     */
    private static final String REGISTRAR_VENTA = "Registrar venta";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazEstacionServicio principal;

    /**
     * N�mero del surtidor.
     */
    private int numSurtidor;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta del tipo de combustible.
     */
    private JLabel etiquetaTipoCombustible;

    /**
     * Etiqueta del costo del gal�n de combustible.
     */
    private JLabel etiquetaCostoGalon;

    /**
     * Etiqueta del n�mero de galones vendidos.
     */
    private JLabel etiquetaNumGalones;

    /**
     * Etiqueta del dinero total recaudado por ventas.
     */
    private JLabel etiquetaDineroRecaudado;

    /**
     * Campo de texto con el tipo de combustible.
     */
    private JTextField txtTipoCombustible;

    /**
     * Campo de texto con el costo gal�n de combustible.
     */
    private JTextField txtCostoGalon;

    /**
     * Campo de texto con el n�mero de galones de combustible.
     */
    private JTextField txtNumGalonese;

    /**
     * Campo de texto con el dinero recaudado.
     */
    private JTextField txtDineroRecaudado;

    /**
     * Bot�n para registrar venta.
     */
    private JButton botonRegistrarVenta;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con la informaci�n de los surtidores.<br>
     * @param pPrincipal Ventana principal. pPrincipal != null.
     * @param pSurtidor N�mero del Surtidor. pSurtidor > 0.
     */
    public PanelSurtidor( InterfazEstacionServicio pPrincipal, int pSurtidor )
    {
        principal = pPrincipal;
        numSurtidor = pSurtidor;

        JPanel info = new JPanel( );
        info.setLayout( new GridLayout( 4, 2 ) );

        botonRegistrarVenta = new JButton( REGISTRAR_VENTA );
        botonRegistrarVenta.setPreferredSize( new Dimension( 160, 20 ) );
        botonRegistrarVenta.setActionCommand( REGISTRAR_VENTA );
        botonRegistrarVenta.addActionListener( this );

        etiquetaTipoCombustible = new JLabel( " Tipo: " );
        etiquetaTipoCombustible.setHorizontalAlignment( JLabel.LEFT );

        txtTipoCombustible = new JTextField( );
        txtTipoCombustible.setEditable( false );

        etiquetaCostoGalon = new JLabel( " Costo por gal�n: $ " );
        etiquetaCostoGalon.setHorizontalAlignment( JLabel.LEFT );

        txtCostoGalon = new JTextField( );
        txtCostoGalon.setEditable( false );

        etiquetaNumGalones = new JLabel( " Galones vendidos: " );
        etiquetaNumGalones.setHorizontalAlignment( JLabel.LEFT );

        txtNumGalonese = new JTextField( );
        txtNumGalonese.setEditable( false );

        etiquetaDineroRecaudado = new JLabel( " Dinero recaudado: $ " );
        etiquetaDineroRecaudado.setHorizontalAlignment( JLabel.LEFT );

        txtDineroRecaudado = new JTextField( );
        txtDineroRecaudado.setEditable( false );

        info.add( etiquetaTipoCombustible );
        info.add( txtTipoCombustible );
        info.add( etiquetaCostoGalon );
        info.add( txtCostoGalon );
        info.add( etiquetaNumGalones );
        info.add( txtNumGalonese );
        info.add( etiquetaDineroRecaudado );
        info.add( txtDineroRecaudado );
        

        setLayout( new BorderLayout( ) );
        setBorder( BorderFactory.createTitledBorder( "Surtidor " + numSurtidor ) );

        JLabel imagen = new JLabel( );
        imagen.setHorizontalAlignment( JLabel.CENTER );
        ImageIcon icono = new ImageIcon( "data/imagenes/surtidor.png" );
        imagen.setIcon( icono );
        add( imagen, BorderLayout.NORTH );

        add( info, BorderLayout.CENTER );
        add( botonRegistrarVenta,BorderLayout.SOUTH );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza los campos de la interfaz con la informaci�n del surtidor.<br>
     * @param pTipoCombustible Tipo de combustible. pTipoCombustible != null & pTipoCombustible != "".
     * @param pPrecioGalon Precio del gal�n. pPrecioGalon > 0.
     * @param pNumeroGalonesVendidos N�mero de galones vendidos por el surtidor. pNumeroGalonesVendidos >= 0.
     * @param pDineroRecaudado Dinero total recaudado. pDineroRecaudado >= 0.
     */
    public void actualizar( String pTipoCombustible, double pPrecioGalon, double pNumeroGalonesVendidos, double pDineroRecaudado )
    {
        txtTipoCombustible.setText( pTipoCombustible );
        txtCostoGalon.setText( pPrecioGalon + "" );
        txtNumGalonese.setText( principal.formatearValorReal( pNumeroGalonesVendidos, 2 ) );
        txtDineroRecaudado.setText( principal.formatearValorReal( pDineroRecaudado, 2 ) );
    }

    /**
     * Manejo de los eventos de los botones.<br>
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( REGISTRAR_VENTA ) )
        {
            principal.registrarVenta( numSurtidor );
        }

    }
}
