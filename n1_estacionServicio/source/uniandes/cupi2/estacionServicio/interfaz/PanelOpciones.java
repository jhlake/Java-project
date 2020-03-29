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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de opciones.
 */
public class PanelOpciones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para la opci�n 1.
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando para la opci�n 2.
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando para reiniciar valores.
     */
    private static final String REINCIAR = "Reiniciar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazEstacionServicio principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para la opci�n 1.
     */
    private JButton btnOpcion1;

    /**
     * Bot�n para la opci�n 2.
     */
    private JButton btnOpcion2;

    /**
     * Bot�n para reiniciar.
     */
    private JButton btnReiniciar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.<br>
     * @param pPrincipal Ventana principal. pPrincipal != null.
     */
    public PanelOpciones( InterfazEstacionServicio pPrincipal )
    {
        principal = pPrincipal;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 3 ) );

        // Bot�n reiniciar.
        btnReiniciar = new JButton( REINCIAR );
        btnReiniciar.setActionCommand( REINCIAR );
        btnReiniciar.addActionListener( this );
        add( btnReiniciar );

        // Bot�n opci�n 1.
        btnOpcion1 = new JButton( "Opci�n 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Bot�n opci�n 2.
        btnOpcion2 = new JButton( "Opci�n 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones. <br>
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        if( OPCION_1.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( REINCIAR.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reiniciar( );
        }
    }

}
