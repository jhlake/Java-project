/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogot� - Colombia)
 * Departamento de  Ingenier�a  de  Sistemas    y   Computaci�n
 * Licenciado   bajo    el  esquema Academic Free License versi�n 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_calculadoraNotas
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.calculadoraNotas.interfaz;

import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Contiene las notas definitivas, ubicado en la zona superior izquierda de la interfaz.
 */
@SuppressWarnings("serial")
public class PanelDefinitivas extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Muestra la nota promedio de los ejercicios
     */
    private JLabel lblPromedioEjercicios;

    /**
     * Muestra la nota promedio de los ex�menes pr�cticos
     */
    private JLabel lblPromedioPracticos;

    /**
     * Muestra la nota promedio de los ex�menes te�ricos
     */
    private JLabel lblPromedioTeoricos;

    /**
     * Muestra la nota definitiva del curso
     */
    private JLabel lblNotaDefinitiva;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con las notas totales del curso. <br>
     * <b>post:</b> Todos los componentes de tipo JLabel fueron inicializados.
     */
    public PanelDefinitivas( )
    {

        setLayout( new GridLayout( 4, 2 ) );

        TitledBorder brdTitulo = BorderFactory.createTitledBorder( "General" );
        CompoundBorder brdCompound = BorderFactory.createCompoundBorder( brdTitulo, new EmptyBorder( 10, 10, 20, 10 ) );
        setBorder( brdCompound );

        JLabel lblEtiquetaPromedioEjercicios = new JLabel( "Promedio ejercicios" );
        add( lblEtiquetaPromedioEjercicios );

        lblPromedioEjercicios = new JLabel( );
        add( lblPromedioEjercicios );

        JLabel lblEtiquetaPromedioPracticos = new JLabel( "Promedio pr�cticos" );
        add( lblEtiquetaPromedioPracticos );

        lblPromedioPracticos = new JLabel( );
        add( lblPromedioPracticos );

        JLabel lblEtiquetaPromedioTeoricos = new JLabel( "Promedio te�ricos" );
        add( lblEtiquetaPromedioTeoricos );

        lblPromedioTeoricos = new JLabel( );
        add( lblPromedioTeoricos );

        JLabel lblEtiquetaNotaDefinitiva = new JLabel( "Nota definitiva" );
        add( lblEtiquetaNotaDefinitiva );

        lblNotaDefinitiva = new JLabel( );
        add( lblNotaDefinitiva );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza los JLabel, con las notas que llegan por par�metro.
     * @param pNotaEjercicios Nota promedio de los ejercicios.
     * @param pNotaPracticos Nota promedio de los ex�menes pr�cticos.
     * @param pNotaTeoricos Nota promedio de los ex�menes te�ricos.
     * @param pNotaDefinitiva Nota definitiva del curso.
     */
    public void actualizar( double pNotaEjercicios, double pNotaPracticos, double pNotaTeoricos, double pNotaDefinitiva )
    {
        NumberFormat formato = new DecimalFormat( "#0.00" );
        lblPromedioEjercicios.setText( formato.format( pNotaEjercicios ) );
        lblPromedioPracticos.setText( formato.format( pNotaPracticos ) );
        lblPromedioTeoricos.setText( formato.format( pNotaTeoricos ) );
        lblNotaDefinitiva.setText( formato.format( pNotaDefinitiva ) );
    }

}
