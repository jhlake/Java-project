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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import uniandes.cupi2.cupiLogo.mundo.ComandoCambiarColor;
import uniandes.cupi2.cupiLogo.mundo.ComandoCentrar;
import uniandes.cupi2.cupiLogo.mundo.ComandoModificarPintando;
import uniandes.cupi2.cupiLogo.mundo.ComandoReiniciar;

/**
 * Panel de manejo de comandos.
 */
public class PanelComandos extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constate que representa el comando deshacer.
     */
    private static final String DESHACER = "DESHACER";

    /**
     * Constate que representa el comando limpiar.
     */
    private static final String REINICIAR = "LIMPIAR";

    /**
     * Constate que representa el comando centrar.
     */
    private static final String CENTRAR = "CENTRAR";

    /**
     * Constate que representa el comando activar trayectoria.
     */
    private static final String ACTIVAR_TRAYECTORIA = "ACTIVAR_TRAYECTORIA";

    /**
     * Constate que representa el comando desactivar trayectoria.
     */
    private static final String DESACTIVAR_TRAYECTORIA = "DESACTIVAR_TRAYECTORIA";

    /**
     * Constate que representa el comando mover.
     */
    private static final String DESPLAZAR = "MOVER";

    /**
     * Constate que representa el comando girar.
     */
    private static final String GIRAR = "GIRAR";

    /**
     * Constate que representa el comando escalar.
     */
    private static final String ESCALAR = "ESCALAR";

    /**
     * Constate que representa el comando cambiar color.
     */
    private static final String CAMBIAR_COLOR = "CAMBIAR_COLOR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiLogo principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n Comando Deshacer.
     */
    private JButton btnComandoDeshacer;

    /**
     * Bot�n Comando Limpiar.
     */
    private JButton btnComandoReiniciar;

    /**
     * Bot�n Comando Limpiar.
     */
    private JButton btnComandoCentrar;

    /**
     * Bot�n Comando Cambiar Color.
     */
    private JButton btnComandoCambiarColor;

    /**
     * Bot�n Comando Activar Trayectoria.
     */
    private JButton btnComandoActivarTrayectoria;

    /**
     * Bot�n Comando Desactivar Trayectoria.
     */
    private JButton btnComandoDesactivarTrayectoria;

    /**
     * Bot�n Comando Mover.
     */
    private JButton btnComandoDesplazar;

    /**
     * Bot�n Comando Girar.
     */
    private JButton btnComandoGirar;

    /**
     * Bot�n Comando Escalar.
     */
    private JButton btnComandoEscalar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.<br>
     * <b> post: </b> Todos los elementos gr�ficos del panel de comandos fueron inicializados.
     * @param pPrincipal Ventana principal. pPrincipal != null.
     */
    public PanelComandos( InterfazCupiLogo pPrincipal )
    {
        principal = pPrincipal;

        setPreferredSize( new Dimension( 50, 90 ) );
        setLayout( new GridLayout( 9, 1, 3, 3 ) );

        
        // Bot�n desplazar
        btnComandoDesplazar = new JButton( new ImageIcon( "data/imagenes/desplazar.png" ) );
        btnComandoDesplazar.setToolTipText( "Desplazar" );
        btnComandoDesplazar.setActionCommand( DESPLAZAR );
        btnComandoDesplazar.addActionListener( this );
        add( btnComandoDesplazar );

        // Bot�n girar
        btnComandoGirar = new JButton( new ImageIcon( "data/imagenes/girar.png" ) );
        btnComandoGirar.setToolTipText( "Girar" );
        btnComandoGirar.setActionCommand( GIRAR );
        btnComandoGirar.addActionListener( this );
        add( btnComandoGirar );

        // Bot�n escalar
        btnComandoEscalar = new JButton( new ImageIcon( "data/imagenes/escalar.png" ) );
        btnComandoEscalar.setToolTipText( "Escalar" );
        btnComandoEscalar.setActionCommand( ESCALAR );
        btnComandoEscalar.addActionListener( this );
        add( btnComandoEscalar );

        // Bot�n centrar
        btnComandoCentrar = new JButton( new ImageIcon( "data/imagenes/centrar.png" ) );
        btnComandoCentrar.setToolTipText( "Centrar tortuga" );
        btnComandoCentrar.setActionCommand( CENTRAR );
        btnComandoCentrar.addActionListener( this );
        add( btnComandoCentrar );

        // Bot�n activar trayectoria
        btnComandoActivarTrayectoria = new JButton( new ImageIcon( "data/imagenes/estaPintando.png" ) );
        btnComandoActivarTrayectoria.setToolTipText( "Activar Trayectoria" );
        btnComandoActivarTrayectoria.setActionCommand( ACTIVAR_TRAYECTORIA );
        btnComandoActivarTrayectoria.addActionListener( this );
        add( btnComandoActivarTrayectoria );

        // Bot�n desactivar trayectoria
        btnComandoDesactivarTrayectoria = new JButton( new ImageIcon( "data/imagenes/noEstaPintando.png" ) );
        btnComandoDesactivarTrayectoria.setToolTipText( "Desactivar Trayectoria" );
        btnComandoDesactivarTrayectoria.setActionCommand( DESACTIVAR_TRAYECTORIA );
        btnComandoDesactivarTrayectoria.addActionListener( this );
        add( btnComandoDesactivarTrayectoria );

        // Bot�n deshacer
        btnComandoDeshacer = new JButton( new ImageIcon( "data/imagenes/deshacer.png" ) );
        btnComandoDeshacer.setToolTipText( "Deshacer" );
        btnComandoDeshacer.setActionCommand( DESHACER );
        btnComandoDeshacer.addActionListener( this );
        add( btnComandoDeshacer );

        // Bot�n limpiar
        btnComandoReiniciar = new JButton( new ImageIcon( "data/imagenes/reiniciar.png" ) );
        btnComandoReiniciar.setToolTipText( "Reiniciar" );
        btnComandoReiniciar.setActionCommand( REINICIAR );
        btnComandoReiniciar.addActionListener( this );
        add( btnComandoReiniciar );


        btnComandoCambiarColor = new JButton( " " );
        btnComandoCambiarColor.setToolTipText( "Cambiar color" );
        btnComandoCambiarColor.setActionCommand( CAMBIAR_COLOR );
        btnComandoCambiarColor.addActionListener( this );
        btnComandoCambiarColor.setBackground( Color.BLACK );
        add( btnComandoCambiarColor );
        
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
        String comandoSt = pEvento.getActionCommand( );
        if( DESHACER.equals( comandoSt ) )
        {
            principal.deshacer( );
        }
        if( REINICIAR.equals( comandoSt ) )
        {
            ComandoReiniciar comando = new ComandoReiniciar( );
            principal.agregarComando( comando );
        }
        if( CENTRAR.equals( comandoSt ) )
        {
            ComandoCentrar comando = new ComandoCentrar( );
            principal.agregarComando( comando );
        }
        if( ACTIVAR_TRAYECTORIA.equals( comandoSt ) )
        {
            ComandoModificarPintando comando = new ComandoModificarPintando( ComandoModificarPintando.PINTA );
            principal.agregarComando( comando );
        }
        if( DESACTIVAR_TRAYECTORIA.equals( comandoSt ))
        {
            ComandoModificarPintando comando = new ComandoModificarPintando( ComandoModificarPintando.NO_PINTA );
            principal.agregarComando( comando );
        }
        if( DESPLAZAR.equals( comandoSt ) )
        {
            DialogoDesplazar dialogoMover = new DialogoDesplazar( principal );
            dialogoMover.setVisible( true );
        }
        if( GIRAR.equals( comandoSt ) )
        {
            DialogoGirar dialogoGirar = new DialogoGirar( principal );
            dialogoGirar.setVisible( true );
        }
        if( ESCALAR.equals( comandoSt ) )
        {
            DialogoEscalar dialogoEscalar = new DialogoEscalar( principal );
            dialogoEscalar.setVisible( true );
        }
        if( CAMBIAR_COLOR.equals( comandoSt ) )
        {
            Color colorLinea = JColorChooser.showDialog( principal, "Color de la l�nea", Color.BLACK );
            if( colorLinea != null )
            {
                btnComandoCambiarColor.setBackground( colorLinea );
                ComandoCambiarColor comando = new ComandoCambiarColor( colorLinea.getRGB( ) );
                principal.agregarComando( comando );
            }
        }
    }
}
