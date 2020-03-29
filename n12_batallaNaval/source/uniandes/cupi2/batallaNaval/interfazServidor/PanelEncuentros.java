/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelEncuentros.java 2109 2010-11-23 14:38:36Z cm.rodriguez155 $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 24/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.interfazServidor;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Es el panel donde se muestran los encuentros que hay actualmente en el servidor
 */
public class PanelEncuentros extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón Refrescar
     */
    private static final String REFRESCAR = "Refrescar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal de la aplicación del servidor
     */
    private InterfazBatallaNaval principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es la lista donde se muestran los encuentros
     */
    private JList listaEncuentros;

    /**
     * Es el botón que se usa para refrescar la lista de encuentros
     */
    private JButton botonRefrescar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel
     * @param ventanaPrincipal Es una referencia a la ventana principal del servidor
     */
    public PanelEncuentros( InterfazBatallaNaval ventanaPrincipal )
    {
        principal = ventanaPrincipal;
        inicializarPanel( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los elementos dentro del panel
     */
    private void inicializarPanel( )
    {
        setLayout( new BorderLayout( ) );
        setSize(new Dimension(500,200));
        
        JScrollPane scroll = new JScrollPane( );
        scroll.setPreferredSize(new Dimension(500, 150));
        listaEncuentros = new JList( );
        scroll.getViewport( ).add( listaEncuentros );
        add(scroll, BorderLayout.CENTER);  
        
        JPanel panelRefrescar= new JPanel(); 
        panelRefrescar.setLayout(new GridBagLayout());
        botonRefrescar = new JButton( "Refrescar" );
        botonRefrescar.addActionListener( this );
        botonRefrescar.setActionCommand( REFRESCAR );
                
        GridBagConstraints gbc= new GridBagConstraints(); 
        gbc.gridx=0;
        gbc.gridy=0;      
        gbc.fill= GridBagConstraints.BOTH;
        gbc.insets= new Insets(5,0,0,0);
        panelRefrescar.add( botonRefrescar, gbc );
        add(panelRefrescar, BorderLayout.SOUTH);
        setBorder( new TitledBorder( "Encuentros" ) );
    }

    /**
     * Actualiza la lista mostrada de encuentros
     * @param encuentros Es una colección con los encuentros que hay actualmente en curso
     */
    public void actualizarEncuentros( Collection encuentros )
    {
        listaEncuentros.setListData( encuentros.toArray( ) );
    }

    /**
     * Es el método llamado cuando se hace click sobre el botón refrescar
     * @param evento Es el evento de click sobre el botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( REFRESCAR.equals( comando ) )
        {
            principal.actualizarEncuentros( );
        }
    }

}
