/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Proyecto	Cupi2	(http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_calculadoraNotas
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.calculadoraNotas.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.calculadoraNotas.mundo.CalculadoraNotas;

/**
 * Clase principal de la interfaz
 */
@SuppressWarnings("serial")
public class InterfazCalculadoraNotas extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Asociación a la clase principal del mundo.
     */
    private CalculadoraNotas mundo;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Contiene el Banner.
     */
    private PanelImagen panelImagen;

    /**
     * Contiene las notas definitivas, ubicado en la zona superior izquierda de la interfaz.
     */
    private PanelDefinitivas panelDefinitivas;

    /**
     * Contiene el diagrama de pie, ubicado en la zona superior derecha de la interfaz.
     */
    private PanelPorcentajes panelPorcentajes;

    /**
     * Panel de botones ubicado en la zona inferior de la interfaz
     */
    private PanelOpciones panelOpciones;

    /**
     * Contiene los campos de texto y la definitiva correspondientes al nivel 1.
     */
    private PanelNivel panelN1;
    /**
     * Contiene los campos de texto y la definitiva correspondientes al nivel 2.
     */
    private PanelNivel panelN2;

    /**
     * Contiene los campos de texto y la definitiva correspondientes al nivel 3.
     */
    private PanelNivel panelN3;

    /**
     * Contiene los campos de texto y la definitiva correspondientes al nivel 4.
     */
    private PanelNivel panelN4;

    /**
     * Contiene los campos de texto y la definitiva correspondientes al nivel 5.
     */
    private PanelNivel panelN5;

    /**
     * Contiene los campos de texto y la definitiva correspondientes al nivel 6.
     */
    private PanelNivel panelN6;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazCalculadoraNotas( )
    {
        setTitle( "Calculadora notas" );
        setSize( 680, 670 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );

        mundo = new CalculadoraNotas( );

        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelDefinitivas = new PanelDefinitivas( );

        panelPorcentajes = new PanelPorcentajes( mundo.darN1( ).calcularPorcentajeTotal( ), mundo.darN2( ).calcularPorcentajeTotal( ), mundo.darN3( ).calcularPorcentajeTotal( ), mundo.darN4( ).calcularPorcentajeTotal( ), mundo.darN5( )
                .calcularPorcentajeTotal( ), mundo.darN6( ).calcularPorcentajeTotal( ) );

        JPanel panelSuperior = new JPanel( new BorderLayout( ) );
        panelSuperior.add( panelDefinitivas, BorderLayout.CENTER );
        panelSuperior.add( panelPorcentajes, BorderLayout.EAST );

        panelN1 = new PanelNivel( mundo.darN1( ), new Color( 255, 255, 153 ) );
        panelN2 = new PanelNivel( mundo.darN2( ), new Color( 204, 255, 204 ) );
        panelN3 = new PanelNivel( mundo.darN3( ), new Color( 204, 236, 255 ) );
        panelN4 = new PanelNivel( mundo.darN4( ), new Color( 153, 204, 255 ) );
        panelN5 = new PanelNivel( mundo.darN5( ), new Color( 255, 153, 204 ) );
        panelN6 = new PanelNivel( mundo.darN6( ), new Color( 250, 191, 143 ) );

        JPanel panelNiveles = new JPanel( );
        panelNiveles.setLayout( new GridLayout( 2, 3, 5, 5 ) );
        panelNiveles.add( panelN1 );
        panelNiveles.add( panelN2 );
        panelNiveles.add( panelN3 );
        panelNiveles.add( panelN4 );
        panelNiveles.add( panelN5 );
        panelNiveles.add( panelN6 );

        JPanel panelCentro = new JPanel( new BorderLayout( ) );
        panelCentro.add( panelNiveles, BorderLayout.SOUTH );
        panelCentro.add( panelSuperior, BorderLayout.CENTER );
        add( panelCentro, BorderLayout.CENTER );

        panelOpciones = new PanelOpciones( this );
        add( panelOpciones, BorderLayout.SOUTH );

        panelDefinitivas.actualizar( mundo.darNotaPromedioEjercicios( ), mundo.darNotaPromedioPracticos( ), mundo.darNotaPromedioTeoricos( ), mundo.darNotaDefinitiva( ) );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Pide a cada uno de los paneles de nivel que registre y actualice su nota. <br>
     * Captura la excepción que estos pueden generar y la muestra al usuario
     */
    public void calcularNotas( )
    {

        try
        {
            panelN1.actualizarNotas( mundo.darN1( ) );
            panelN2.actualizarNotas( mundo.darN2( ) );
            panelN3.actualizarNotas( mundo.darN3( ) );
            panelN4.actualizarNotas( mundo.darN4( ) );
            panelN5.actualizarNotas( mundo.darN5( ) );
            panelN6.actualizarNotas( mundo.darN6( ) );

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Cambiar nota", JOptionPane.ERROR_MESSAGE );
        }
        panelDefinitivas.actualizar( mundo.darNotaPromedioEjercicios( ), mundo.darNotaPromedioPracticos( ), mundo.darNotaPromedioTeoricos( ), mundo.darNotaDefinitiva( ) );
    }

    // -----------------------------------------------------------------
    // Puntos de extensión
    // -----------------------------------------------------------------

    /**
     * Extensión 1
     */
    public void reqFuncOpcion1( )
    {

        String resultado = mundo.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta 1", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extensión 2
     */
    public void reqFuncOpcion2( )
    {

        String resultado = mundo.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta 2", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo opcional de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        InterfazCalculadoraNotas i = new InterfazCalculadoraNotas( );
        i.setVisible( true );
        
    }
}
