/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazBatallaNaval.java 2109 2010-11-23 14:38:36Z cm.rodriguez155 $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 21-feb-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.interfazServidor;

import java.awt.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;

import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta es la ventana principal del servidor de la batalla naval
 */
public class InterfazBatallaNaval extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del servidor
     */
    private BatallaNaval servidorBatallaNaval;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se muestran los registros de los jugadores
     */
    private PanelJugadores panelJugadores;

    /**
     * Es el panel donde se muestran los encuentros actuales
     */
    private PanelEncuentros panelEncuentros;

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicación<br>
     * @param servidor Es una referencia al servidor sobre el que funciona esta interfaz
     */
    public InterfazBatallaNaval( BatallaNaval servidor )
    {
        servidorBatallaNaval = servidor;
        inicializarVentana( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los elementos de la ventana principal
     */
    private void inicializarVentana( )
    {
        // Construye la forma
        setLayout( new GridBagLayout( ) );
        setSize( 531, 534 );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "Servidor Batalla Naval" );
        setLocationRelativeTo(null);

        // Creación de los paneles aquí
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 1, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        panelEncuentros = new PanelEncuentros( this );
        add( panelEncuentros, gbc );

        gbc = new GridBagConstraints( 0, 1, 1, 1, 1, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        panelJugadores = new PanelJugadores( this );
        add( panelJugadores, gbc );

        panelExtension = new PanelExtension( this );
        gbc = new GridBagConstraints( 0, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelExtension, gbc );
    }

    /**
     * Actualiza la lista de encuentros mostrada en el panelEncuentros
     */
    public void actualizarEncuentros( )
    {
        Collection encuentros = servidorBatallaNaval.darListaActualizadaEncuentros( );
        panelEncuentros.actualizarEncuentros( encuentros );
    }

    /**
     * Actualiza la lista de jugadores mostrada en el panelJugadores
     */
    public void actualizarJugadores( )
    {
        try
        {
            Collection jugadores = servidorBatallaNaval.darAdministradorResultados( ).consultarRegistrosJugadores( );
            panelJugadores.actualizarJugadores( jugadores );
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog( this, "Hubo un error consultando la lista de jugadores:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Cierra la ventana y la aplicación
     */
    public void dispose( )
    {
        super.dispose( );
        try
        {
            servidorBatallaNaval.darAdministradorResultados( ).desconectarBD( );
        }
        catch( SQLException e )
        {
            e.printStackTrace( );
        }
        System.exit( 0 );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = servidorBatallaNaval.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = servidorBatallaNaval.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }
    
    /**
     * Método para la extensión 3
     */
    public void reqFuncOpcion3( )
    {
        String resultado = servidorBatallaNaval.metodo3( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Parámetros de ejecución que no son usados
     */
    public static void main( String[] args )
    {
        try
        {
            String archivoPropiedades = "./data/servidor.properties";
            BatallaNaval servidorBatallaNaval = new BatallaNaval( archivoPropiedades );

            InterfazBatallaNaval interfaz = new InterfazBatallaNaval( servidorBatallaNaval );
            interfaz.setVisible( true );

            servidorBatallaNaval.recibirConexiones( );
        }
        catch( Exception e )
        {            
            e.printStackTrace( );
        }
    }

}