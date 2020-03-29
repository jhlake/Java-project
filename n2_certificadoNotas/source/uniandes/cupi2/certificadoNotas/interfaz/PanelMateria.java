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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.certificadoNotas.mundo.Materia;

/**
 * Panel donde se muestra la información de una materia.
 */
public class PanelMateria extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para asignar una nota.
     */
    private final static String ASIGNAR_NOTA = "Asignar nota";

    /**
     * Constante que representa el comando para modificar una materia.
     */
    private final static String MODIFICAR_MATERIA = "Modificar materia";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCertificadoNotas principal;

    /**
     * Número de la materia.
     */
    private int numeroMateria;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta con la imagen de la materia.
     */
    private JLabel lblImagen;

    /**
     * Campo de texto con el nombre de la materia.
     */
    private JTextField txtNombreMateria;

    /**
     * Campo de texto con el nombre del departamento al que pertenece la materia.
     */
    private JTextField txtNombreDepartamento;

    /**
     * Campo de texto con el número de créditos de la materia.
     */
    private JTextField txtNumeroCreditos;

    /**
     * Campo de texto con la nota de la materia.
     */
    private JTextField txtNota;

    /**
     * Botón para asignar una nota a la materia.
     */
    private JButton btnAsignarNota;

    /**
     * Botón para editar la materia.
     */
    private JButton btnEditarMateria;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel de una materia dado su número por parámetro.<br>
     * <b>post: </b> Construyó el panel con la información de la materia. <br>
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     * @param pNumeroMateria Número de la materia. pNumeroMateria > 0 && pNumeroMateria < 5.
     */
    public PanelMateria( InterfazCertificadoNotas pPrincipal, int pNumeroMateria )
    {
        principal = pPrincipal;
        numeroMateria = pNumeroMateria;

        setBorder( new TitledBorder( "Materia " + numeroMateria ) );
        setLayout( new GridBagLayout( ) );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.insets = new Insets( 3, 3, 3, 3 );
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblImagen = new JLabel( new ImageIcon( "./data/imagenes/sinAsignar.png" ) );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        add( lblImagen, gbc );

        JLabel labNombreMateria = new JLabel( "Nombre materia:" );
        gbc.gridx = 1;
        gbc.gridheight = 1;
        add( labNombreMateria, gbc );

        txtNombreMateria = new JTextField( );
        txtNombreMateria.setPreferredSize( new Dimension( 185, 20 ) );
        txtNombreMateria.setForeground( Color.BLUE );
        txtNombreMateria.setEditable( false );
        gbc.gridx = 2;
        gbc.gridwidth = 3;

        add( txtNombreMateria, gbc );
        gbc.gridwidth = 1;

        JLabel labNombrePrograma = new JLabel( "Departamento:" );
        gbc.gridy = 1;
        gbc.gridx = 1;
        add( labNombrePrograma, gbc );

        txtNombreDepartamento = new JTextField( );
        txtNombreDepartamento.setPreferredSize( new Dimension( 185, 20 ) );
        txtNombreDepartamento.setForeground( Color.BLUE );
        txtNombreDepartamento.setEditable( false );
        gbc.gridx = 2;
        gbc.gridwidth = 3;

        add( txtNombreDepartamento, gbc );
        gbc.gridwidth = 1;

        JLabel labNumeroCreditos = new JLabel( "Número créditos:" );
        gbc.gridy = 2;
        gbc.gridx = 1;
        add( labNumeroCreditos, gbc );

        txtNumeroCreditos = new JTextField( );
        txtNumeroCreditos.setPreferredSize( new Dimension( 185, 20 ) );
        txtNumeroCreditos.setForeground( Color.BLUE );
        txtNumeroCreditos.setEditable( false );
        gbc.gridx = 2;
        gbc.gridwidth = 3;

        add( txtNumeroCreditos, gbc );
        gbc.gridwidth = 1;

        JLabel labNota = new JLabel( "Nota:" );
        gbc.gridy = 3;
        gbc.gridx = 1;
        add( labNota, gbc );

        txtNota = new JTextField( );
        txtNota.setPreferredSize( new Dimension( 185, 20 ) );
        txtNota.setForeground( Color.BLUE );
        txtNota.setEditable( false );
        gbc.gridx = 2;
        gbc.gridwidth = 3;

        add( txtNota, gbc );
        gbc.gridwidth = 1;

        btnAsignarNota = new JButton( "Asignar nota" );
        btnAsignarNota.setEnabled( false );
        btnAsignarNota.setActionCommand( ASIGNAR_NOTA );
        btnAsignarNota.addActionListener( this );
        gbc.gridy = 4;
        gbc.gridx = 1;

        gbc.gridwidth = 5;
        add( btnAsignarNota, gbc );

        btnEditarMateria = new JButton( "Modificar materia" );
        btnEditarMateria.setActionCommand( MODIFICAR_MATERIA );
        btnEditarMateria.addActionListener( this );
        gbc.gridy = 5;
        add( btnEditarMateria, gbc );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la ruta a la imagen del departamento dado el nombre del departamento.
     * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
     *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
     * @return Ruta a la imagen del departamento.
     */
    public String darRutaImagen( String pDepartamento )
    {
        String rta = "./data/imagenes/sinAsignar.png";

        if( pDepartamento.equals( Materia.MATEMATICAS ) )
        {
            rta = "./data/imagenes/matematicas.png";
        }
        else if( pDepartamento.equals( Materia.INGENIERIA_DE_SISTEMAS ) )
        {
            rta = "./data/imagenes/sistemas.png";
        }
        else if( pDepartamento.equals( Materia.INGENIERIA_INDUSTRIAL ) )
        {
            rta = "./data/imagenes/industrial.png";
        }
        else if( pDepartamento.equals( Materia.MICROBIOLOGIA ) )
        {
            rta = "./data/imagenes/microbiologia.png";
        }
        else if( pDepartamento.equals( Materia.FISICA ) )
        {
            rta = "./data/imagenes/fisica.png";
        }
        else
        {
            rta = "./data/imagenes/quimica.png";
        }

        return rta;
    }

    /**
     * Asigna la nota dada por parámetro.
     * @param pNota La nota de la materia. pNota >= 1.5 && pNota <= 5.0
     */
    public void asignarNota( double pNota )
    {
        txtNota.setText( pNota + "" );
    }

    /**
     * Retorna el valor que se encuentra en el campo de texto de la nota de la materia.
     * @return el valor en el campo de texto.
     */
    public String darNota( )
    {
        return txtNota.getText( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza los campos de la materia con la información dada por parámetro.
     * @param pNombreMateria Nuevo nombre de la materia. pNombreMateria != null && pNombreMateria != "".
     * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
     *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
     * @param pNumeroCreditos Número de créditos de la materia. pNumeroCredito > 0.
     */
    public void modificarMateria( String pNombreMateria, String pDepartamento, int pNumeroCreditos )
    {
        txtNombreMateria.setText( pNombreMateria );
        txtNombreDepartamento.setText( pDepartamento );
        txtNumeroCreditos.setText( pNumeroCreditos + "" );
        txtNota.setText( "" );
        lblImagen.setIcon( new ImageIcon( darRutaImagen( pDepartamento ) ) );
        btnAsignarNota.setEnabled( true );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( ASIGNAR_NOTA ) )
        {
            principal.asignarNota( numeroMateria );
        }
        else if( comando.equals( MODIFICAR_MATERIA ) )
        {
            principal.mostrarDialogoModificarMateria( numeroMateria );
        }
    }
}
