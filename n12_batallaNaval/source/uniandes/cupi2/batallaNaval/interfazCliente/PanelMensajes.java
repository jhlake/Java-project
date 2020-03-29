/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelMensajes.java 622 2006-11-09 23:37:02Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 24/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.interfazCliente;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * Este es el panel donde se muestran los mensajes que describen lo que va pasando en el juego
 */
public class PanelMensajes extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el área de texto donde se muestran los mensajes
     */
    private JTextArea txtArea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel de mensajes
     */
    public PanelMensajes( )
    {
        setBorder( new TitledBorder( "Registro de Mensajes" ) );
        setLayout( new BorderLayout( ) );

        JScrollPane scroll = new JScrollPane( );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        txtArea = new JTextArea( 6, 10 );
        txtArea.setWrapStyleWord( true );
        txtArea.setLineWrap( true );
        txtArea.setEditable( false );

        scroll.setOpaque( false );
        scroll.getViewport( ).add( txtArea );
        add( scroll );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega todos los mensajes de la colección al campo de texto, uno por uno
     * @param mensajes Es una lista de mensajes que deben mostrarse
     */
    public void agregarMensajes( Collection mensajes )
    {
        Iterator iter = mensajes.iterator( );
        while( iter.hasNext( ) )
        {
            String mensaje = ( String )iter.next( );
            txtArea.append( mensaje + "\n" );
            txtArea.setCaretPosition( txtArea.getText( ).length( ) );
        }
    }

    /**
     * Este método se encarga de pintar el panel y sus componentes, haciendo que haya una imagen en el fondo<br>
     * @param g Es la superficie del panel
     */
    public void paintComponent( Graphics g )
    {
        ImageIcon icon = new ImageIcon( "./data/abajo.jpg" );
        g.drawImage( icon.getImage( ), 0, 0, null );
        setOpaque( false );
        super.paintComponent( g );
    }
}
