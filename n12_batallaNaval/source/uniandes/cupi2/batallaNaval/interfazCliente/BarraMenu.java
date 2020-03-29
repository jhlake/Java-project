/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BarraMenu.java 641 2006-11-14 16:22:14Z da-romer $
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Esta es la clase donde está definido el menú de la aplicación
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Es el comando de la opción Iniciar Juego
     */
    private static final String INICIAR_JUEGO = "IniciarJuego";

    /**
     * Es el comando de la opción Salir
     */
    private static final String SALIR = "salir";

    /**
     * Es el comando de la opción 1
     */
    private static final String OPCION_1 = "Opcion1";

    /**
     * Es el comando de la opción 2
     */
    private static final String OPCION_2 = "Opcion2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz del cliente
     */
    private InterfazJugador principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el menú Inicio del juego
     */
    private JMenu menuInicio;

    /**
     * Es la opción Iniciar Juego del menú Inicio
     */
    private JMenuItem itemIniciarJuego;

    /**
     * Es la opción Salir del menú Inicio
     */
    private JMenuItem itemSalir;

    /**
     * Es el menú Extensiones del juego
     */
    private JMenu menuExtension;

    /**
     * Es la opción 1
     */
    private JMenuItem itemOpcion1;

    /**
     * Es la opción 2
     */
    private JMenuItem itemOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el menú para la aplicación de Batalla Naval
     * @param ventanaPrincipal Es una referencia a la ventana principal del cliente
     */
    public BarraMenu( InterfazJugador ventanaPrincipal )
    {
        principal = ventanaPrincipal;

        menuInicio = new JMenu( "Inicio" );
        add( menuInicio );

        itemIniciarJuego = new JMenuItem( "Iniciar Juego" );
        itemIniciarJuego.setActionCommand( INICIAR_JUEGO );
        itemIniciarJuego.addActionListener( this );
        menuInicio.add( itemIniciarJuego );

        itemSalir = new JMenuItem( "Salir" );
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menuInicio.add( itemSalir );

        menuExtension = new JMenu( "Extensión" );
        add( menuExtension );

        itemOpcion1 = new JMenuItem( "Opción 1" );
        itemOpcion1.setActionCommand( OPCION_1 );
        itemOpcion1.addActionListener( this );
        menuExtension.add( itemOpcion1 );

        itemOpcion2 = new JMenuItem( "Opción 2" );
        itemOpcion2.setActionCommand( OPCION_2 );
        itemOpcion2.addActionListener( this );
        menuExtension.add( itemOpcion2 );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ejecuta una acción según la opción del menú que haya sido seleccionada
     * @param evento El evento de click en una opción
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( SALIR.equals( comando ) )
        {
            principal.dispose( );
        }
        else if( INICIAR_JUEGO.equals( comando ) )
        {
            principal.iniciarConexion( );
        }
        else if( OPCION_1.equals( comando ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( comando ) )
        {
            principal.reqFuncOpcion2( );
        }
    }

}
