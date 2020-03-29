/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiEmail
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiEmail.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Dialogo para iniciar sesi�n.
 */
public class DialogoIniciarSesion extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando de la opci�n iniciar sesi�n
     */
    private static final String INICIAR_SESION = "INICIAR_SESION";

    /**
     * Comando de la opci�n salir
     */
    private static final String CANCELAR = "SALIR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n iniciar sesi�n.
     */
    private JButton btnIniciarSesion;

    /**
     * Bot�n cancelar.
     */
    private JButton btnCancelar;

    /**
     * Label imagen.
     */
    private JLabel lblImagen;

    /**
     * Label contrase�a.
     */
    private JLabel lblContrasena;

    /**
     * Label usuario.
     */
    private JLabel lblUsuario;

    /**
     * Texto contrase�a.
     */
    private JPasswordField txtContrasena;

    /**
     * Texto usuario.
     */
    private JTextField txtUsuario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo iniciar sesi�n
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public DialogoIniciarSesion( InterfazCliente pPrincipal )
    {
        super( pPrincipal, true );
        setLayout( new BorderLayout( ) );
        principal = pPrincipal;
        setSize( 230, 270 );
        setLocationRelativeTo( null );
        setTitle( "Iniciar sesi�n" );
        lblImagen = new JLabel( );
        JPanel panelDatos = new JPanel( );
        lblUsuario = new JLabel( );
        txtUsuario = new JTextField( );
        lblContrasena = new JLabel( );
        txtContrasena = new JPasswordField( );
        JPanel panelBotones = new JPanel( );
        btnIniciarSesion = new JButton( );
        btnCancelar = new JButton( );

        lblImagen.setHorizontalAlignment( SwingConstants.CENTER );
        lblImagen.setIcon( new ImageIcon( "data/imagenes/inicio_sesion.png" ) );
        add( lblImagen, BorderLayout.NORTH );

        panelDatos.setLayout( new java.awt.GridLayout( 5, 0 ) );

        lblUsuario.setText( "Usuario" );
        panelDatos.add( lblUsuario );
        panelDatos.add( txtUsuario );

        lblContrasena.setText( "Contrase�a" );
        panelDatos.add( lblContrasena );
        panelDatos.add( txtContrasena );

        panelBotones.setLayout( new java.awt.GridLayout( 1, 2 ) );

        btnIniciarSesion.setText( "Iniciar sesi�n" );
        btnIniciarSesion.setActionCommand( INICIAR_SESION );
        btnIniciarSesion.addActionListener( this );
        panelBotones.add( btnIniciarSesion );

        btnCancelar.setText( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelBotones.add( btnCancelar );

        panelDatos.add( panelBotones );

        add( panelDatos, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( INICIAR_SESION ) )
        {
            if( txtUsuario.getText( ) != null && !txtUsuario.getText( ).isEmpty( ) )
            {
                if( txtContrasena.getText( ) == null || txtContrasena.getText( ).isEmpty( ) )
                {
                    JOptionPane.showMessageDialog( this, "Por favor, ingrese su contrase�a", "Error Contrase�a", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    principal.iniciarSesion( txtUsuario.getText( ), txtContrasena.getText( ) );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Por favor, ingrese el nombre de usuario", "Error Usuario", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }

    }
}
