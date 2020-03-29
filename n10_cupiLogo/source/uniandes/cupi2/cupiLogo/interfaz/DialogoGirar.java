/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiLogo
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiLogo.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiLogo.mundo.ComandoGirar;

/**
 * Di�logo para agregar el comando Girar.
 */
public class DialogoGirar extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------s

    /**
     * Constante para la serializaci�n.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante que representa el comando para ejecutar el comando.
     */
    private final static String EJECUTAR = "Ejecutar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiLogo principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para ejecutar el comando.
     */
    private JButton btnEjecutar;

    /**
     * Combobox con la direcci�n del movimiento.
     */
    private JComboBox cbDireccion;

    /**
     * Campo de texto con el �ngulo a girar.
     */
    private JTextField txtAngulo;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del di�logo para el comando Girar.<br>
     * <b> post: </b> Todos los elementos gr�ficos del di�logo fueron inicializados.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public DialogoGirar( InterfazCupiLogo pPrincipal )
    {
        principal = pPrincipal;

        setTitle( "Girar" );
        setSize( new Dimension( 350, 150 ) );
        setResizable( true );
        setLocationRelativeTo( principal );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );

        JPanel panel = new JPanel( );
        panel.setLayout( new GridLayout( 3, 2, 5, 5 ) );
        panel.setBorder( new TitledBorder( "Agregar nuevo giro:" ) );

        panel.add( new JLabel( "Angulo a girar:" ) );
        txtAngulo = new JTextField( );
        panel.add( txtAngulo );

        panel.add( new JLabel( "Direcci�n:" ) );
        cbDireccion = new JComboBox( );
        cbDireccion.addItem( "Horario" );
        cbDireccion.addItem( "Anti-horario" );
        panel.add( cbDireccion );

        panel.add( new JLabel( ) );
        btnEjecutar = new JButton( EJECUTAR );
        btnEjecutar.setActionCommand( EJECUTAR );
        btnEjecutar.addActionListener( this );
        panel.add( btnEjecutar );

        add( panel );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {

        if( pEvento.getActionCommand( ).equals( EJECUTAR ) )
        {
            boolean puede = true;
            double angulo = 0;
            int direccion = cbDireccion.getSelectedIndex( );
            try
            {
                angulo = Double.parseDouble( txtAngulo.getText( ) );
            }
            catch( Exception e2 )
            {
                puede = false;
                JOptionPane.showMessageDialog( principal, "Debe agregar un valor num�rico.", "Girar", JOptionPane.ERROR_MESSAGE );
            }
            if( !principal.verificarGiro( angulo, direccion ) )
            {
                puede = false;
                JOptionPane.showMessageDialog( principal, "El valor introducido debe estar entre 0 y 360.", "Girar", JOptionPane.ERROR_MESSAGE );
            }

            if( puede )
            {
                ComandoGirar comando = new ComandoGirar( angulo, direccion );
                principal.agregarComando( comando );
                this.dispose( );
            }
        }

    }

}
