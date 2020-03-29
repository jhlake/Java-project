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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Contiene los botones ubicados en la parte inferior de la ventana principal.
 */
@SuppressWarnings("serial")
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para ejecutar la acción del botón btnCalcularNota.
     */
    private static final String CALCULAR_NOTA = "Calcular nota";

    /**
     * Comando para ejecutar la acción del botón btnOpcion1.
     */
    private static final String OPCION1 = "Opción 1";

    /**
     * Comando para ejecutar la acción del botón btnOpcion2.
     */
    private static final String OPCION2 = "Opción 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCalculadoraNotas principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Botón que permite calcular las notas.
     */
    private JButton btnCalcularNota;

    /**
     * Botón para la opción 1.
     */
    private JButton btnOpcion1;

    /**
     * Botón para la opción 2.
     */
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con los botones <br>
     * <b>post:</b> Todos los botones fueron inicializados.
     * @param pInterfaz Interfaz principal de la aplicación. pInterfaz != null
     */
    public PanelOpciones( InterfazCalculadoraNotas pInterfaz )
    {
        TitledBorder b = BorderFactory.createTitledBorder( "Opciones" );
        setBorder( b );

        principal = pInterfaz;
        setLayout( new GridLayout( 1, 3 ) );

        btnCalcularNota = new JButton( CALCULAR_NOTA );
        btnOpcion1 = new JButton( OPCION1 );
        btnOpcion2 = new JButton( OPCION2 );

        btnCalcularNota.addActionListener( this );
        btnOpcion1.addActionListener( this );
        btnOpcion2.addActionListener( this );

        btnCalcularNota.setActionCommand( CALCULAR_NOTA );
        btnOpcion1.setActionCommand( OPCION1 );
        btnOpcion2.setActionCommand( OPCION2 );

        add( btnCalcularNota );
        add( btnOpcion1 );
        add( btnOpcion2 );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos del usuario.
     * @param pEvento Evento de usuario. pEvento != null.
     */
    @Override
    public void actionPerformed( ActionEvent pEvento )
    {
        if( pEvento.getActionCommand( ).equals( OPCION1 ) )
        {
        	Object[] options = {"Two",
    			"One"};
    			int n = JOptionPane.showOptionDialog(this,
    					"Who won?",
    					"Report win",
    					JOptionPane.YES_NO_OPTION,
    					JOptionPane.QUESTION_MESSAGE,
    					null,     //do not use a custom Icon
    					options,  //the titles of buttons
    					options[1]); //default button title
    			System.out.println(n);
        }
        else if( pEvento.getActionCommand( ).equals( OPCION2 ) )
        {
            principal.reqFuncOpcion2( );
        }
        else
        {
            principal.calcularNotas( );
        }
    }

}
