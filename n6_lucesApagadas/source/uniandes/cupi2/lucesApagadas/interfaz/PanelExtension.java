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
 * Panel con los botones y la extensi�n.
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
     * Constante que representa el comando para mostrar las estad�sticas del juego actual.
     */
    public final static String ESTADISTICAS = "Estad�sticas";

    /**
     * Constante que representa el comando para ejecutar el m�todo 1 de la extensi�n.
     */
    public final static String OPCION_1 = "Opci�n 1";

    /**
     * Constante que representa el comando para ejecutar el m�todo 2 de la extensi�n.
     */
    public final static String OPCION_2 = "Opci�n 2";

    /*
     * Atributos
     */
    /**
     * Relaci�n con el panel principal de la aplicaci�n.
     */
    private InterfazLucesApagadas principal;

    /*
     * Atributos de la interfaz.
     */

    /**
     * Bot�n para cargar juego nuevo.
     */
    private JButton btnCargar;

    /**
     * Bot�n para reiniciar juego actual.
     */
    private JButton btnReiniciar;

    /**
     * Bot�n para arrojar las estad�sticas del juego actual.
     */
    private JButton btnEstadisticas;

    /**
     * Bot�n para ejecutar el m�todo 1 de la extensi�n.
     */
    private JButton btnOpcion1; 

    /**
     * Bot�n para ejecutar el m�todo 2 de la extensi�n.
     */
    private JButton btnOpcion2;

    /*
     * M�todos
     */

    /*
     * Constructor
     */
    /**
     * M�todo que construye el panel con los botones, y los inicializa.
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
     * @param e Acci�n que gener� el evento. e != null.
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
