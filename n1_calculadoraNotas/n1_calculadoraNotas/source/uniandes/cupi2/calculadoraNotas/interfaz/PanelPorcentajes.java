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

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler.AnnotationType;
import org.knowm.xchart.style.Styler.ChartTheme;

/**
 * Contiene el diagrama de pie, ubicado en la zona superior derecha de la interfaz.
 */
@SuppressWarnings("serial")
public class PanelPorcentajes extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Diagrama de pastel
     */
    private PieChart diagrama;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con el diagrama de pie. <b>post:</b> El diagrama de tipo PieChart fue inicializado.
     * @param pPorcentajeN1 Porcentaje total que vale el nivel 1.
     * @param pPorcentajeN2 Porcentaje total que vale el nivel 2.
     * @param pPorcentajeN3 Porcentaje total que vale el nivel 3.
     * @param pPorcentajeN4 Porcentaje total que vale el nivel 4.
     * @param pPorcentajeN5 Porcentaje total que vale el nivel 5.
     * @param pPorcentajeN6 Porcentaje total que vale el nivel 6.
     */
    public PanelPorcentajes( double pPorcentajeN1, double pPorcentajeN2, double pPorcentajeN3, double pPorcentajeN4, double pPorcentajeN5, double pPorcentajeN6 )
    {

        TitledBorder brdTitulo = BorderFactory.createTitledBorder( "Porcentaje de cada nivel" );
        setBorder( brdTitulo );

        setLayout( new BorderLayout( ) );
        diagrama = new PieChartBuilder( ).width( 400 ).height( 235 ).title( "Porcentaje de cada nivel" ).theme( ChartTheme.GGPlot2 ).build( );
        diagrama.getStyler( ).setLegendVisible( true );
        diagrama.getStyler( ).setAnnotationType( AnnotationType.Percentage );
        diagrama.getStyler( ).setAnnotationDistance( 1.40 );
        diagrama.getStyler( ).setPlotContentSize( .65 );
        diagrama.getStyler( ).setStartAngleInDegrees( 90 );
        diagrama.getStyler( ).setChartTitleVisible( false );

        diagrama.getStyler( ).setChartBackgroundColor( getBackground( ) );
        diagrama.getStyler( ).setLegendBackgroundColor( getBackground( ) );
        diagrama.getStyler( ).setLegendBorderColor( getBackground( ) );
        diagrama.getStyler( ).setLegendPadding( 1 );

        // Series
        diagrama.addSeries( "Nivel 1", pPorcentajeN1 ).setFillColor( new Color( 255, 255, 153 ) );
        diagrama.addSeries( "Nivel 2", pPorcentajeN2 ).setFillColor( new Color( 204, 255, 204 ) );
        diagrama.addSeries( "Nivel 3", pPorcentajeN3 ).setFillColor( new Color( 204, 236, 255 ) );
        diagrama.addSeries( "Nivel 4", pPorcentajeN4 ).setFillColor( new Color( 153, 204, 255 ) );
        diagrama.addSeries( "Nivel 5", pPorcentajeN5 ).setFillColor( new Color( 255, 153, 204 ) );
        diagrama.addSeries( "Nivel 6", pPorcentajeN6 ).setFillColor( new Color( 250, 191, 143 ) );

        JPanel chartPanel = new XChartPanel<PieChart>( diagrama );
        add( chartPanel, BorderLayout.CENTER );

    }

}
