/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_certificadoNotas
 * Autor: EquipoCupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.certificadoNotas.interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import uniandes.cupi2.certificadoNotas.mundo.Materia;

/**
 * Di�logo para modificar una materia.
 */
public class DialogoModificarMateria extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el comando para aceptar.
     */
    private final static String ACEPTAR = "Aceptar";

    /**
     * Constante que representa el comando para cancelar.
     */
    private final static String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCertificadoNotas principal;

    /**
     * N�mero de la materia.
     */
    private int numeroMateria;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Campo de texto para ingresar el nombre de la materia.
     */
    private JTextField txtNombreMateria;

    /**
     * Lista de selecci�n para seleccionar el nombre del departamento al que pertenece la materia.
     */
    private JComboBox comboDepartamento;

    /**
     * Lista de selecci�n para asignar los cr�ditos de la materia.
     */
    private JComboBox comboNumeroCreditos;

    /**
     * Bot�n para aceptar.
     */
    private JButton btnAceptar;

    /**
     * Bot�n para cancelar.
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el di�logo. <br>
     * <b>post: </b> Construy� el di�logo.<br>
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     * @param pNumeroMateria N�mero de la materia. pNumeromateria > 0 && pNumeroMateria < 5.
     */
    public DialogoModificarMateria( InterfazCertificadoNotas pPrincipal, int pNumeroMateria )
    {
        principal = pPrincipal;
        numeroMateria = pNumeroMateria;

        setTitle( "Modificar materia" );
        setSize( 320, 160 );
        setResizable( false );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setModal( true );
        setLocationRelativeTo( principal );
        setLayout( new FlowLayout( ) );

        JLabel lblNombreMateria = new JLabel( "Nombre materia:" );
        lblNombreMateria.setPreferredSize( new Dimension( 150, 25 ) );
        add( lblNombreMateria );

        txtNombreMateria = new JTextField( );
        txtNombreMateria.setPreferredSize( new Dimension( 150, 25 ) );
        add( txtNombreMateria );

        JLabel lblDepartamento = new JLabel( "Departamento:" );
        lblDepartamento.setPreferredSize( new Dimension( 150, 25 ) );
        add( lblDepartamento );

        comboDepartamento = new JComboBox( );
        comboDepartamento.setPreferredSize( new Dimension( 150, 25 ) );
        comboDepartamento.addItem( Materia.MATEMATICAS );
        comboDepartamento.addItem( Materia.INGENIERIA_DE_SISTEMAS );
        comboDepartamento.addItem( Materia.INGENIERIA_INDUSTRIAL );
        comboDepartamento.addItem( Materia.MICROBIOLOGIA );
        comboDepartamento.addItem( Materia.FISICA );
        comboDepartamento.addItem( Materia.QUIMICA );
        add( comboDepartamento );

        JLabel lblNumeroCreditos = new JLabel( "N�mero cr�ditos" );
        lblNumeroCreditos.setPreferredSize( new Dimension( 150, 25 ) );
        add( lblNumeroCreditos );

        comboNumeroCreditos = new JComboBox( );
        comboNumeroCreditos.setPreferredSize( new Dimension( 150, 25 ) );
        comboNumeroCreditos.addItem( 1 );
        comboNumeroCreditos.addItem( 2 );
        comboNumeroCreditos.addItem( 3 );
        comboNumeroCreditos.addItem( 4 );
        comboNumeroCreditos.addItem( 5 );
        add( comboNumeroCreditos );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setPreferredSize( new Dimension( 150, 25 ) );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        add( btnAceptar );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setPreferredSize( new Dimension( 150, 25 ) );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        add( btnCancelar );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( ACEPTAR ) )
        {
            if( !txtNombreMateria.getText( ).trim( ).equals( "" ) )
            {
                String nombre = txtNombreMateria.getText( );
                String departamento = ( String )comboDepartamento.getSelectedItem( );
                int creditos = ( Integer )comboNumeroCreditos.getSelectedItem( );
                principal.modificarMateria( numeroMateria, nombre, departamento, creditos );
                dispose( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar el nombre de la materia", "Modificar materia", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        else if( comando.equals( CANCELAR ) )
        {
            dispose( );
        }
    }
}