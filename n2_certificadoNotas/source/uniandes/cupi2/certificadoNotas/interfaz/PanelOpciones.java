/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_certificadoNotas
 * Autor: EquipoCupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.certificadoNotas.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de acciones.
 */
public class PanelOpciones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para ver el número de materias por programa.
     */
    private final static String MATERIAS_POR_DEPARTAMENTO = "Materias por departamento";

    /**
     * Constante que representa el comando para ver las materias reprobadas.
     */
    private final static String MATERIAS_REPROBADAS = "Materias reprobadas";

    /**
     * Constante que representa el comando para generar el certificado de notas.
     */
    private final static String GENERAR_CERTIFICADO = "Generar certificado";

    /**
     * Constante que representa el comando para la opción 1.
     */
    private final static String OPCION_1 = "Opción 1";

    /**
     * Constante que representa el comando para la opción 2.
     */
    private final static String OPCION_2 = "Opción 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCertificadoNotas principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón para ver el número de materias por departamento.
     */
    private JButton btnMateriasPorDepartamento;

    /**
     * Botón para ver las materias reprobadas.
     */
    private JButton btnMateriasReprobadas;

    /**
     * Botón para generar el certificado de notas.
     */
    private JButton btnGenerarCertificado;

    /**
     * Botón para la opción 1.
     */
    private JButton butOpcion1;

    /**
     * Botón para la opción 2.
     */
    private JButton butOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel de manejo de acciones con una referencia a la ventana principal de la aplicación. <br>
     * <b>post: </b> Construyó el diálogo.<br>
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelOpciones( InterfazCertificadoNotas pPrincipal )
    {
        principal = pPrincipal;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 2, 1 ) );

        JPanel panelSuperior = new JPanel( );
        panelSuperior.setLayout( new GridLayout( 1, 3 ) );

        btnMateriasPorDepartamento = new JButton( "Materias por departamento" );
        btnMateriasPorDepartamento.setPreferredSize( new Dimension( 289, 26 ) );
        btnMateriasPorDepartamento.setActionCommand( MATERIAS_POR_DEPARTAMENTO );
        btnMateriasPorDepartamento.addActionListener( this );
        panelSuperior.add( btnMateriasPorDepartamento );

        btnMateriasReprobadas = new JButton( "Materias reprobadas" );
        btnMateriasReprobadas.setPreferredSize( new Dimension( 289, 26 ) );
        btnMateriasReprobadas.setActionCommand( MATERIAS_REPROBADAS );
        btnMateriasReprobadas.addActionListener( this );
        panelSuperior.add( btnMateriasReprobadas );

        btnGenerarCertificado = new JButton( "Generar certificado" );
        btnGenerarCertificado.setPreferredSize( new Dimension( 289, 26 ) );
        btnGenerarCertificado.setActionCommand( GENERAR_CERTIFICADO );
        btnGenerarCertificado.addActionListener( this );
        panelSuperior.add( btnGenerarCertificado );

        JPanel panelInferior = new JPanel( );
        panelInferior.setLayout( new GridLayout( 1, 2 ) );

        butOpcion1 = new JButton( "Opción 1" );
        butOpcion1.setPreferredSize( new Dimension( 289, 26 ) );
        butOpcion1.setActionCommand( OPCION_1 );
        butOpcion1.addActionListener( this );
        panelInferior.add( butOpcion1 );

        butOpcion2 = new JButton( "Opción 2" );
        butOpcion2.setPreferredSize( new Dimension( 289, 26 ) );
        butOpcion2.setActionCommand( OPCION_2 );
        butOpcion2.addActionListener( this );
        panelInferior.add( butOpcion2 );

        add( panelSuperior );
        add( panelInferior );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( MATERIAS_POR_DEPARTAMENTO ) )
        {
            principal.darNumeroMateriasDepartamento( );
        }
        else if( comando.equals( MATERIAS_REPROBADAS ) )
        {
            principal.darMateriasReprobadas( );
        }
        else if( comando.equals( GENERAR_CERTIFICADO ) )
        {
            principal.generarCertificado( );
        }
        else if( comando.equals( OPCION_1 ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( comando.equals( OPCION_2 ) )
        {
            principal.reqFuncOpcion2( );
        }
    }
}
