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
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel con la informaci�n del usuario.
 */
public class PanelInfoUsuario extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de la opci�n cerrar sesi�n.
     */
    private static final String CERRAR_SESION = "CERRAR_SESION";

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
     * Bot�n cerrar sesi�n.
     */
    private JButton btnCerrarSesion;

    /**
     * Label imagen.
     */
    private JLabel lblImagen;

    /**
     * Label bienvenido.
     */
    private JLabel lblBienvenido;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public PanelInfoUsuario( InterfazCliente pPrincipal )
    {
        principal = pPrincipal;
        lblBienvenido = new JLabel( );
        btnCerrarSesion = new JButton( );
        lblImagen = new JLabel( );
        lblImagen.setIcon( new ImageIcon( "data/imagenes/cliente.png" ) );

        setBackground( new Color( 255, 255, 255 ) );
        setLayout( new BorderLayout( ) );

        lblBienvenido.setText( "<html> Bienvenid@: <p> usuario  </html>" );
        add( lblBienvenido, BorderLayout.WEST );

        btnCerrarSesion.setText( "Cerrar Sesi�n" );
        btnCerrarSesion.addActionListener( this );
        btnCerrarSesion.setActionCommand( CERRAR_SESION );
        add( btnCerrarSesion, BorderLayout.EAST );

        add( lblImagen, BorderLayout.NORTH );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo para actualizar la interfaz con la informaci�n de un usuario.
     * @param pLogin Login del usuario. pLogin != null && pLogin != "".
     */
    public void actualizarUsuario( String pLogin )
    {
        lblBienvenido.setText( "<html> Bienvenid@: <p> " + pLogin + "  </html>" );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( CERRAR_SESION ) )
        {
            principal.cerrarSesion( );
        }
    }
}