/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelTableros.java 622 2006-11-09 23:37:02Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 25/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.interfazCliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import uniandes.cupi2.batallaNaval.cliente.*;

/**
 * Este es el panel que es capaz de mostrar una representación de los tableros de juego actuales <br>
 * Este panel permite ver tanto el estado de las casillas locales como el estado de las casillas del oponente e interactuar con ellas.
 */
public class PanelTableros extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Color para las casillas que fueron atacadas pero que no le dieron a nada
     */
    private static final Color COLOR_VACIA = new Color( 0, 0, 0, 0 );

    /**
     * Color para las casillas que fueron atacadas pero que no le dieron a nada
     */
    private static final Color COLOR_ATACADA = new Color( 253, 191, 70 );

    /**
     * Color para las casillas donde fueron impactados barcos
     */
    private static final Color COLOR_IMPACTADA = new Color( 255, 0, 0 );

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazJugador principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es una matriz con los botones del tablero del jugador
     */
    private JButton[][] botonesJugador;

    /**
     * Es una matriz con los botones del tablero del oponente
     */
    private JButton[][] botonesOponente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel y lo inicializa
     * @param ventanaPrincipal Es una referencia a la clase principal de la interfaz
     */
    public PanelTableros( InterfazJugador ventanaPrincipal )
    {
        principal = ventanaPrincipal;
        inicializarPanel( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa todos los elementos del panel
     */
    private void inicializarPanel( )
    {
        botonesJugador = new JButton[9][9];
        botonesOponente = new JButton[9][9];

        JPanel panelJugador = new JPanel( new GridLayout( 9, 9 ) );
        JPanel panelOponente = new JPanel( new GridLayout( 9, 9 ) );

        panelJugador.setPreferredSize( new Dimension( 35 * 9, 35 * 9 ) );
        panelOponente.setPreferredSize( new Dimension( 35 * 9, 35 * 9 ) );

        panelJugador.setOpaque( false );
        panelOponente.setOpaque( false );

        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                JButton botonJugador = new JButton( );
                botonJugador.setEnabled( false );
                botonJugador.setOpaque( false );
                // botonJugador.setBackground( COLOR_VACIA );
                botonJugador.setIcon( new ImageIcon( "./data/vacia.png" ) );
                botonJugador.setBorder( new LineBorder( Color.BLACK ) );
                botonesJugador[ i ][ j ] = botonJugador;
                panelJugador.add( botonesJugador[ i ][ j ] );

                JButton botonOponente = new JButton( );
                botonOponente.setBackground( COLOR_VACIA );
                botonOponente.setBorder( new LineBorder( Color.BLACK ) );
                botonOponente.setIcon( new ImageIcon( "./data/vacia.png" ) );
                botonOponente.setOpaque( false );
                botonOponente.setFocusPainted( false );
                botonOponente.setActionCommand( i + ":" + j );
                botonOponente.addActionListener( this );
                botonesOponente[ i ][ j ] = botonOponente;
                panelOponente.add( botonesOponente[ i ][ j ] );
            }
        }

        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 1, 0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets( 15, 15, 5, 15 ), 10, 10 );
        add( panelJugador, gbc );

        gbc.gridx = 1;
        add( panelOponente, gbc );
    }

    /**
     * Este método se encarga de dejar el tablero en blanco para que se pueda iniciar un nuevo encuentro
     */
    public void reinicializarTablero( )
    {
        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                JButton botonJugador = botonesJugador[ i ][ j ];
                botonJugador.setOpaque( false );
                botonJugador.setIcon( new ImageIcon( "./data/vacia.png" ) );
                botonJugador.setBorder( new LineBorder( Color.BLACK ) );

                JButton botonOponente = botonesOponente[ i ][ j ];
                botonOponente.setEnabled( true );
                botonOponente.setBackground( COLOR_ATACADA );
                botonOponente.setIcon( new ImageIcon( "./data/vacia.png" ) );
                botonOponente.setOpaque( false );
            }
        }
    }

    /**
     * Este método actualiza la representación mostrada de los dos tableros
     * @param tableroJugador Las casillas del jugador local
     * @param tableroOponente Las casillas del oponente
     */
    public void actualizarTableros( TableroFlota tableroJugador, Tablero tableroOponente )
    {
        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                // Actualizar la casilla del tablero del jugador
                JButton botonJugador = botonesJugador[ i ][ j ];
                if( tableroJugador.darCasilla( i, j ).darEstado( ) == Casilla.IMPACTADA )
                {
                    botonJugador.setOpaque( true );
                    botonJugador.setBackground( COLOR_IMPACTADA );
                }
                else if( tableroJugador.darCasilla( i, j ).darEstado( ) == Casilla.OCUPADA )
                {
                    botonJugador.setOpaque( true );
                    botonJugador.setBackground( tableroJugador.darCasilla( i, j ).darBarco( ).darColor( ) );
                }
                else if( tableroJugador.darCasilla( i, j ).darEstado( ) == Casilla.ATACADA )
                {
                    botonJugador.setOpaque( true );
                    botonJugador.setBackground( COLOR_ATACADA );
                }

                // Actualizar la casilla del tablero del oponente
                JButton botonOponente = botonesOponente[ i ][ j ];
                if( tableroOponente.darCasilla( i, j ).darEstado( ) == Casilla.VACIA )
                    ;

                else if( tableroOponente.darCasilla( i, j ).darEstado( ) == Casilla.IMPACTADA )
                {
                    botonOponente.setIcon( null );
                    botonOponente.setBackground( COLOR_IMPACTADA );
                    botonOponente.setOpaque( true );
                    botonOponente.setEnabled( false );
                }

                else if( tableroOponente.darCasilla( i, j ).darEstado( ) == Casilla.ATACADA )
                {
                    botonOponente.setIcon( null );
                    botonOponente.setBackground( COLOR_ATACADA );
                    botonOponente.setOpaque( true );
                    botonOponente.setEnabled( false );
                }
            }
        }
    }

    /**
     * Este método se encarga de pintar el panel y sus componentes, haciendo que haya una imagen en el fondo<br>
     * @param g Es la superficie del panel
     */
    public void paintComponent( Graphics g )
    {
        ImageIcon icon = new ImageIcon( "./data/mapa.jpg" );
        g.drawImage( icon.getImage( ), 0, 0, null );

        setOpaque( false );
        super.paintComponent( g );
    }

    /**
     * Este es el método que se llama cuando se hace click sobre alguno de los botones del tablero del oponente
     * @param evento Es el evento del click sobre un botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        String[] coordenadas = comando.split( ":" );
        int fila = Integer.parseInt( coordenadas[ 0 ] );
        int columna = Integer.parseInt( coordenadas[ 1 ] );

        principal.jugar( fila, columna );
    }
}
