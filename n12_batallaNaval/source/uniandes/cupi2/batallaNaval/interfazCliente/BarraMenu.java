/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BarraMenu.java 641 2006-11-14 16:22:14Z da-romer $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 24/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.interfazCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Esta es la clase donde est� definido el men� de la aplicaci�n
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Es el comando de la opci�n Iniciar Juego
     */
    private static final String INICIAR_JUEGO = "IniciarJuego";

    /**
     * Es el comando de la opci�n Salir
     */
    private static final String SALIR = "salir";

    /**
     * Es el comando de la opci�n 1
     */
    private static final String OPCION_1 = "Opcion1";

    /**
     * Es el comando de la opci�n 2
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
     * Es el men� Inicio del juego
     */
    private JMenu menuInicio;

    /**
     * Es la opci�n Iniciar Juego del men� Inicio
     */
    private JMenuItem itemIniciarJuego;

    /**
     * Es la opci�n Salir del men� Inicio
     */
    private JMenuItem itemSalir;

    /**
     * Es el men� Extensiones del juego
     */
    private JMenu menuExtension;

    /**
     * Es la opci�n 1
     */
    private JMenuItem itemOpcion1;

    /**
     * Es la opci�n 2
     */
    private JMenuItem itemOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el men� para la aplicaci�n de Batalla Naval
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

        menuExtension = new JMenu( "Extensi�n" );
        add( menuExtension );

        itemOpcion1 = new JMenuItem( "Opci�n 1" );
        itemOpcion1.setActionCommand( OPCION_1 );
        itemOpcion1.addActionListener( this );
        menuExtension.add( itemOpcion1 );

        itemOpcion2 = new JMenuItem( "Opci�n 2" );
        itemOpcion2.setActionCommand( OPCION_2 );
        itemOpcion2.addActionListener( this );
        menuExtension.add( itemOpcion2 );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Ejecuta una acci�n seg�n la opci�n del men� que haya sido seleccionada
     * @param evento El evento de click en una opci�n
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
