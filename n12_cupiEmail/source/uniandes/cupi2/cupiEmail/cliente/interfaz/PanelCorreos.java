/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiEmail
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiEmail.cliente.interfaz;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import uniandes.cupi2.cupiEmail.cliente.mundo.CorreoElectronico;

/**
 * Panel con la lista de correos
 */
public class PanelCorreos extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Panel para la lista de correos.
     */
    private JPanel panelListaCorreos;

    /**
     * Scroll para lista de correos.
     */
    private JScrollPane scpcorreos;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelCorreos( InterfazCliente pPrincipal )
    {
        principal = pPrincipal;
        scpcorreos = new JScrollPane( );
        panelListaCorreos = new JPanel( );
        setLayout( new GridLayout( 1, 0 ) );
        panelListaCorreos.setLayout( new BoxLayout( panelListaCorreos, BoxLayout.PAGE_AXIS ) );
        scpcorreos.setViewportView( panelListaCorreos );
        add( scpcorreos );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que actualiza la lista de correos.
     * @param pCorreos Lista de correos. pCorreos != null.
     */
    public void actualizar( ArrayList pCorreos )
    {
        panelListaCorreos.removeAll( );
        for( int i = 0; i < pCorreos.size( ); i++ )
        {
            CorreoElectronico correo = ( CorreoElectronico )pCorreos.get( i );
            PanelCorreo pCorreo = new PanelCorreo( principal, correo );
            panelListaCorreos.add( pCorreo );
            panelListaCorreos.add( new JSeparator( ) );
        }
        updateUI( );
    }
}