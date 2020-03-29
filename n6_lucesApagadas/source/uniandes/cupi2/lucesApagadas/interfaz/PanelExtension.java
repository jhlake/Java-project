package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel con los botones y la extensión.
 * @author jlake
 */
public class PanelExtension extends JPanel implements ActionListener
{
    /*
     * Constantes
     */

    /**
     * Constante que representa el comando de cargar un nuevo juego.
     */
    public final static String CARGAR = "Cargar";

    /**
     * Constante que representa el comando de reinciar el juego actual.
     */
    public final static String REINICIAR = "Reiniciar";

    /**
     * Constante que representa el comando para mostrar las estadísticas del juego actual.
     */
    public final static String ESTADISTICAS = "Estadísticas";

    /**
     * Constante que representa el comando para ejecutar el método 1 de la extensión.
     */
    public final static String OPCION_1 = "Opción 1";

    /**
     * Constante que representa el comando para ejecutar el método 2 de la extensión.
     */
    public final static String OPCION_2 = "Opción 2";

    /*
     * Atributos
     */
    /**
     * Relación con el panel principal de la aplicación.
     */
    private InterfazLucesApagadas principal;

    /*
     * Atributos de la interfaz.
     */

    /**
     * Botón para cargar juego nuevo.
     */
    private JButton btnCargar;

    /**
     * Botón para reiniciar juego actual.
     */
    private JButton btnReiniciar;

    /**
     * Botón para arrojar las estadísticas del juego actual.
     */
    private JButton btnEstadisticas;

    /**
     * Botón para ejecutar el método 1 de la extensión.
     */
    private JButton btnOpcion1; 

    /**
     * Botón para ejecutar el método 2 de la extensión.
     */
    private JButton btnOpcion2;

    /*
     * Métodos
     */

    /*
     * Constructor
     */
    /**
     * Método que construye el panel con los botones, y los inicializa.
     * @param ventana Componente padre.
     */
    public PanelExtension(InterfazLucesApagadas ventana)
    {
        principal = ventana;
        TitledBorder border = new TitledBorder("Opciones");
        setBorder( border );
        setSize(new Dimension(820, 70));
        setLayout(new GridLayout(1, 5));

        btnCargar = new JButton(CARGAR);
        btnCargar.setActionCommand( CARGAR );
        btnCargar.addActionListener( this );
        add(btnCargar);

        btnReiniciar = new JButton(REINICIAR);
        btnReiniciar.setActionCommand(REINICIAR);
        btnReiniciar.addActionListener( this );
        add(btnReiniciar);

        btnEstadisticas = new JButton(ESTADISTICAS);
        btnEstadisticas.setActionCommand( ESTADISTICAS );
        btnEstadisticas.addActionListener( this );
        add(btnEstadisticas);

        btnOpcion1 = new JButton(OPCION_1);
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add(btnOpcion1);

        btnOpcion2 = new JButton(OPCION_2);
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add(btnOpcion2);

    }

    /**
     * Manejo de los eventos de los botones.
     * @param e Acción que generó el evento. e != null.
     */
    @Override
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        switch(command)
        {
            case CARGAR:
                try
                {
                    principal.cargarJuego( );
                }
                catch( Exception ex )
                {
                    JOptionPane.showMessageDialog( null, ex.getMessage( ) );
                    ex.printStackTrace();
                }
                break;
            case REINICIAR:
                principal.reinciarJuego( );
                break;
            case ESTADISTICAS:
                principal.darEstadisticas( );
                break;
            case OPCION_1:
                principal.reqFuncOpcion1( );
                break;
            case OPCION_2:
                principal.reqFuncOpcion2( );
                break;
        }

    }
}
