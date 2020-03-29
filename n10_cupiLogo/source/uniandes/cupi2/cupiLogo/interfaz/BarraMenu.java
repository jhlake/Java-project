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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 * Barra que contiene los men�s de la aplicaci�n.
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante que representa el comando Nuevo.
     */
    private static final String NUEVO = "Nuevo";

    /**
     * Constante que representa el comando Abrir.
     */
    private static final String ABRIR = "Abrir";

    /**
     * Constante que representa el comando Guardar.
     */
    private static final String GUARDAR = "Guardar";
    
    /**
     * Constante que representa el comando Guardar como.
     */
    private static final String GUARDAR_COMO = "GuardarComo";

    /**
     * Constante que representa el comando Salir.
     */
    private static final String SALIR = "Salir";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCupiLogo principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * El men� Archivo.
     */
    private JMenu menuArchivo;

    /**
     * La opci�n Nuevo del men� Archivo.
     */
    private JMenuItem itemNuevo;

    /**
     * La opci�n Abrir del men� Archivo.
     */
    private JMenuItem itemAbrir;

    /**
     * La opci�n Guardar del men� Archivo.
     */
    //TODO Parte 5 Punto A: Declarar la opci�n del menu guardar.
    private JMenuItem itemGuardar;

    /**
     * La opci�n Guardar como del men� Archivo.
     */
    //TODO Parte 5 Punto A: Declarar la opci�n del menu guardar como.
    private JMenuItem itemGuardarComo;

    /**
     * La opci�n Salir del men� Archivo.
     */
    //TODO Parte 5 Punto A: Declarar la opci�n del menu salir.
    private JMenuItem itemSalir;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la barra de men�.<br>
     * <b> post: </b> Todos los elementos gr�ficos del men� fueron inicializados.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     */
    public BarraMenu( InterfazCupiLogo pPrincipal )
    {
        principal = pPrincipal;

        menuArchivo = new JMenu( "Archivo" );
        add( menuArchivo );

        itemNuevo = new JMenuItem( "Nuevo" );
        itemNuevo.setActionCommand( NUEVO );
        itemNuevo.addActionListener( this );
        menuArchivo.add( itemNuevo );

        itemAbrir = new JMenuItem( "Abrir" );
        itemAbrir.setActionCommand( ABRIR );
        itemAbrir.addActionListener( this );
        menuArchivo.add( itemAbrir );

        //TODO Parte 5 Punto D: Completar el m�todo constructor para incluir la opciones guardar, guardar como y salir.
        //No olvide poner un separador entre guardar como y salir.
        
        itemGuardar = new JMenuItem( "Guardar" );
        itemGuardar.setActionCommand( GUARDAR );
        itemGuardar.addActionListener( this );
        menuArchivo.add( itemGuardar );
        
        itemGuardarComo = new JMenuItem( "Guardar Como" );
        itemGuardarComo.setActionCommand( GUARDAR_COMO );
        itemGuardarComo.addActionListener( this );
        menuArchivo.add( itemGuardarComo );
        
        menuArchivo.add(new JSeparator());
        
        itemSalir = new JMenuItem( "Salir" );
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menuArchivo.add( itemSalir );
        
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los men�s.
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( NUEVO.equals( comando ) )
        {
            principal.nuevo( );
        }
        else if( ABRIR.equals( comando ) )
        {
            principal.abrir( );
        }
        //TODO Parte 5 Punto E: Complete este m�todo para que incluya las acciones de los �tems de men� creados.
        else if( GUARDAR.equals(comando))
        {
        	principal.guardar();
        }
        else if( GUARDAR_COMO.equals(comando))
        {
        	principal.guardarComo();
        }
        else if(SALIR.equals(comando))
        {
        	principal.dispose();
        }
    }

}