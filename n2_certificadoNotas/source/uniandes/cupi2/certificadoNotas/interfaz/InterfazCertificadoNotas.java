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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.certificadoNotas.mundo.CertificadoNotas;
import uniandes.cupi2.certificadoNotas.mundo.Materia;

/**
 * Ventana principal de la aplicación.
 */
public class InterfazCertificadoNotas extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private CertificadoNotas certificadoNotas;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel de la imagen del encabezado de la aplicación.
     */
    private PanelImagen panelImagen;

    /**
     * Panel para la materia 1.
     */
    private PanelMateria panelMateria1;

    /**
     * Panel para la materia 2.
     */
    private PanelMateria panelMateria2;

    /**
     * Panel para la materia 3.
     */
    private PanelMateria panelMateria3;

    /**
     * Panel para la materia 4.
     */
    private PanelMateria panelMateria4;

    /**
     * Panel de opciones.
     */
    private PanelOpciones panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa la ventana principal.<br>
     * <b>post: </b> Se inició la ventana principal.
     */
    public InterfazCertificadoNotas( )
    {
        certificadoNotas = new CertificadoNotas( );

        setTitle( "Certificado de notas" );
        setLayout( new BorderLayout( ) );
        setSize( 1000, 700 );
        setResizable( false );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        JPanel aux = new JPanel( );
        aux.setLayout( new GridLayout( 2, 2 ) );

        panelMateria1 = new PanelMateria( this, 1 );
        Materia actual = certificadoNotas.darMateria1( );
        panelMateria1.modificarMateria( actual.darNombre( ), actual.darDepartamento( ), actual.darNumeroCreditos( ) );
        aux.add( panelMateria1 );

        panelMateria2 = new PanelMateria( this, 2 );
        Materia actual2 = certificadoNotas.darMateria2( );
        panelMateria2.modificarMateria( actual2.darNombre( ), actual2.darDepartamento( ), actual2.darNumeroCreditos( ) );
        aux.add( panelMateria2 );

        panelMateria3 = new PanelMateria( this, 3 );
        Materia actual3 = certificadoNotas.darMateria3( );
        panelMateria3.modificarMateria( actual3.darNombre( ), actual3.darDepartamento( ), actual3.darNumeroCreditos( ) );
        aux.add( panelMateria3 );

        panelMateria4 = new PanelMateria( this, 4 );
        Materia actual4 = certificadoNotas.darMateria4( );
        panelMateria4.modificarMateria( actual4.darNombre( ), actual4.darDepartamento( ), actual4.darNumeroCreditos( ) );
        aux.add( panelMateria4 );

        add( aux, BorderLayout.CENTER );

        panelOpciones = new PanelOpciones( this );
        panelOpciones.setPreferredSize( new Dimension( 0, 90 ) );
        add( panelOpciones, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Muestra el número de materias en el departamento dado por parámetro.
     */
    public void darNumeroMateriasDepartamento( )
    {
        String[] opciones = new String[6];

        opciones[ 0 ] = Materia.INGENIERIA_DE_SISTEMAS;
        opciones[ 1 ] = Materia.INGENIERIA_INDUSTRIAL;
        opciones[ 2 ] = Materia.MATEMATICAS;
        opciones[ 3 ] = Materia.FISICA;
        opciones[ 4 ] = Materia.QUIMICA;
        opciones[ 5 ] = Materia.MICROBIOLOGIA;

        String departamento = ( String )JOptionPane.showInputDialog( this, "Departamentos:", "Número materias por departamento", JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[ 0 ] );
        if( departamento != null )
        {
            int cantidad = certificadoNotas.darNumeroMateriasDepartamento( departamento );
            JOptionPane.showMessageDialog( this, "Número de materias que pertenencen al programa de " + departamento + ": " + cantidad, "Materias por departamento", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    /**
     * Muestra las materias reprobadas.
     */
    public void darMateriasReprobadas( )
    {
        if( panelMateria1.darNota( ).equals( "" ) && panelMateria2.darNota( ).equals( "" ) && panelMateria3.darNota( ).equals( "" ) && panelMateria4.darNota( ).equals( "" ))
        {
            JOptionPane.showMessageDialog( this, "Todas las materias deben tener notas asignadas.", "Materias Reprobadas", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            String respuesta = certificadoNotas.darMateriasReprobadas( );
            
            if( !respuesta.contains( "," ) )
            {
                JOptionPane.showMessageDialog( this, respuesta, "Materias reprobadas", JOptionPane.INFORMATION_MESSAGE );
            }
            else
            {
                String materiasReprobadas = "Materias reprobadas:\n";
                
                if( materiasReprobadas.endsWith( "," ) )
                {
                    materiasReprobadas = materiasReprobadas.substring( 0, materiasReprobadas.length( ) - 1 );
                }
                
                String[] materias = respuesta.split( "," );
                
                for( int i = 0; i < materias.length; i++ )
                {
                    Materia materia = certificadoNotas.buscarMateria( materias[ i ].trim( ) );
                    
                    materiasReprobadas += "Materia: " + materia.darNombre( ) + " - Nota: " + materia.darNota( ) + "\n";
                }
                
                JOptionPane.showMessageDialog( this, materiasReprobadas.trim( ), "Materias reprobadas", JOptionPane.INFORMATION_MESSAGE );
            }            
        }
        
    }

    /**
     * Muestra un diálogo para modificar la materia con código dado por parámetro.
     * @param pNumeroMateria Número de la materia que va a ser modificada. pNumeroMateria > 0 && pNumeroMateria < 5.
     */
    public void mostrarDialogoModificarMateria( int pNumeroMateria )
    {
        DialogoModificarMateria dialogoModificarMateria = new DialogoModificarMateria( this, pNumeroMateria );
        dialogoModificarMateria.setVisible( true );
    }

    /**
     * Asigna una nota a la materia cuyo número es dado por parámetro
     * @param nNumeroMateria El número de la materia a la que se le quiere asignar la nota. nNumeroMateria == 1 || nNumeroMateria == 2 || nNumeroMateria == 3 || nNumeroMateria
     *        == 4
     */
    public void asignarNota( int nNumeroMateria )
    {
        String strNota = JOptionPane.showInputDialog( this, "Ingrese la nota de la materia:" );

        if( strNota != null )
        {
            try
            {
                double nota = Double.parseDouble( strNota );
                boolean respuesta = certificadoNotas.asignarNota( nNumeroMateria, nota );

                if( respuesta )
                {
                    if( nNumeroMateria == 1 )
                    {
                        panelMateria1.asignarNota( nota );
                    }
                    else if( nNumeroMateria == 2 )
                    {
                        panelMateria2.asignarNota( nota );
                    }
                    else if( nNumeroMateria == 3 )
                    {
                        panelMateria3.asignarNota( nota );
                    }
                    else if( nNumeroMateria == 4 )
                    {
                        panelMateria4.asignarNota( nota );
                    }

                    JOptionPane.showMessageDialog( this, "La nota fue asignada correctamente.", "Asignar nota", JOptionPane.INFORMATION_MESSAGE );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "No se pudo asignar la nota.", "Asignar nota", JOptionPane.INFORMATION_MESSAGE );
                }
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un valor numérico.", "Asignar nota", JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    /**
     * Genera el certificado de notas.
     */
    public void generarCertificado( )
    {
        String certificado = "";
        Materia materia1 = certificadoNotas.darMateria1( );
        Materia materia2 = certificadoNotas.darMateria2( );
        Materia materia3 = certificadoNotas.darMateria3( );
        Materia materia4 = certificadoNotas.darMateria4( );

        if( materia1.darNota( ) != -1 && materia2.darNota( ) != -1 && materia3.darNota( ) != -1 && materia4.darNota( ) != -1 )
        {
            certificado += "Materia: " + materia2.darNombre( ) + " - Créditos: " + materia2.darNumeroCreditos( ) + " - Nota: " + materia2.darNota( ) + "\n";
            certificado += "Materia: " + materia1.darNombre( ) + " - Créditos: " + materia1.darNumeroCreditos( ) + " - Nota: " + materia1.darNota( ) + "\n";
            certificado += "Materia: " + materia3.darNombre( ) + " - Créditos: " + materia3.darNumeroCreditos( ) + " - Nota: " + materia3.darNota( ) + "\n";
            certificado += "Materia: " + materia4.darNombre( ) + " - Créditos: " + materia4.darNumeroCreditos( ) + " - Nota: " + materia4.darNota( ) + "\n";
            certificado = "Certificado de notas:\n" + certificado;

            double promedio = certificadoNotas.calcularPromedio( );
            String estado = certificadoNotas.darEstadoEstudiante( );

            DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
            df.applyPattern( "###,###.##" );

            certificado += "Promedio: " + df.format( promedio ) + "\nEstado: " + estado;
        }
        else
        {
            certificado = "Todas las materias deben tener notas asignadas.";
        }

        JOptionPane.showMessageDialog( this, certificado, "Certificado", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Modifica la materia con el número dado por parámetro. <br>
     * <b> post: </b> La materia con el número dado se modificó.
     * @param pNumeroMateria Número de la materia que va a ser modificada. pNumeroMateria > 0 && pNumeroMateria < 5.
     * @param pNombreMateria Nuevo nombre de la materia. pNombreMateria != null && pNombreMateria != "".
     * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
     *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
     * @param pNumeroCreditos Número de créditos de la materia. pNumeroCredito > 0.
     */
    public void modificarMateria( int pNumeroMateria, String pNombreMateria, String pDepartamento, int pNumeroCreditos )
    {
        certificadoNotas.modificarMateria( pNumeroMateria, pNombreMateria, pDepartamento, pNumeroCreditos );
        if( pNumeroMateria == 1 )
        {
            panelMateria1.modificarMateria( pNombreMateria, pDepartamento, pNumeroCreditos );
        }
        else if( pNumeroMateria == 2 )
        {
            panelMateria2.modificarMateria( pNombreMateria, pDepartamento, pNumeroCreditos );
        }

        else if( pNumeroMateria == 3 )
        {
            panelMateria3.modificarMateria( pNombreMateria, pDepartamento, pNumeroCreditos );
        }

        else if( pNumeroMateria == 4 )
        {
            panelMateria4.modificarMateria( pNombreMateria, pDepartamento, pNumeroCreditos );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = certificadoNotas.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = certificadoNotas.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param pArgs Argumentos para la ejecución de la aplicación. En este caso no son necesarios.
     */
    public static void main( String[] args )
    {
        InterfazCertificadoNotas interfaz = new InterfazCertificadoNotas( );
        interfaz.setVisible( true );
    }
}