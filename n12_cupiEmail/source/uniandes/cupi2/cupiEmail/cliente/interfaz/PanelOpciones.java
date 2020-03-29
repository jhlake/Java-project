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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel con las opciones de la interfaz.
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de la opción actualizar los correos.
     */
    private static final String ACTUALIZAR_CORREOS = "ACTUALIZAR_CORREOS";

    /**
     * Comando de la opción escribir correo.
     */
    private static final String ESCRIBIR_CORREOS = "ESCRIBIR_CORREOS";

    /**
     * Comando Opción 1.
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2.
     */
    private static final String OPCION_2 = "OPCION_2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     *Referencia a la clase principal de la interfaz del cliente.
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón actualizar
     */
    private JButton btnActualizar;

    /**
     * Botón escribir correo.
     */
    private JButton btnEscribirCorreo;

    /**
     * Botón opción 1.
     */
    private JButton btnOpcion1;

    /**
     * Botón opción 2.
     */
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel de opciones.
     * @param pPrincipal Referencia a la ventana principal. pPrincipal != null.
     */
    public PanelOpciones( InterfazCliente pPrincipal )
    {
        principal = pPrincipal;
        GridBagConstraints gridBagConstraints;
        btnEscribirCorreo = new JButton( );
        btnActualizar = new JButton( );
        btnOpcion1 = new JButton( );
        btnOpcion2 = new JButton( );

        setBorder( BorderFactory.createTitledBorder( "Opciones" ) );
        setLayout( new GridBagLayout( ) );

        btnEscribirCorreo.setText( "Escribir correo" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        btnEscribirCorreo.addActionListener( this );
        btnEscribirCorreo.setActionCommand( ESCRIBIR_CORREOS );
        add( btnEscribirCorreo, gridBagConstraints );

        gridBagConstraints.gridy = 1;
        btnActualizar.setText( "Refrescar" );
        btnActualizar.setActionCommand( ACTUALIZAR_CORREOS );
        btnActualizar.addActionListener( this );
        add( btnActualizar, gridBagConstraints );

        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 2 ) );

        btnOpcion1.setText( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        panelBotones.add( btnOpcion1 );

        btnOpcion2.setText( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        panelBotones.add( btnOpcion2 );

        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;

        add( panelBotones, gridBagConstraints );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( ACTUALIZAR_CORREOS ) )
        {
            principal.consultarCorreos( );
        }
        else if( command.equals( ESCRIBIR_CORREOS ) )
        {
            principal.escribirCorreoNuevo( );
        }
        if( OPCION_1.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
    }
}