package uniandes.cupi2.cupiMuseo.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel para calificar una obra.
 * @author jlake
 */
public class PanelCalificacion extends JPanel implements ActionListener
{
    /*
     * Constantes
     */
    /**
     * Constante que representa el comando de calificar con 0 estrellas.
     */
    public static final String CERO = "Cero";

    /**
     * Constante que representa el comando de calificar con 1 estrella.
     */
    public static final String UNO = "Uno";

    /**
     * Constante que representa el comando de calificar con 2 estrellas.
     */
    public static final String DOS = "Dos";

    /**
     * Constante que representa el comando de calificar con 3 estrellas.
     */
    public static final String TRES = "Tres";

    /**
     * Constante que representa el comando de calificar con 4 estrellas.
     */
    public static final String CUATRO = "Cuatro";

    /**
     * Constante que representa el comando de calificar con 5 estrellas.
     */
    public static final String CINCO = "Cinco";

    /*
     * Atributos
     */
    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiMuseo principal;

    /*
     * Atributos de la Interfaz
     */

    /**
     * Botón que califica una obra como 0 estrellas.
     */
    private JButton btnCero;

    /**
     * Botón que califica una obra como 1 estrella.
     */
    private JButton btnUno;

    /**
     * Botón que califica una obra como 2 estrellas.
     */
    private JButton btnDos;

    /**
     * Botón que califica una obra como 3 estrellas.
     */
    private JButton btnTres;

    /**
     * Botón que califica una obra como 4 estrellas.
     */
    private JButton btnCuatro;

    /**
     * Botón que califica una obra como 5 estrellas.
     */
    private JButton btnCinco;

    /*
     * Constructor
     */
    /**
     * Constructor del panel que permite calificar una obra. 
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelCalificacion(InterfazCupiMuseo pPrincipal)
    {
        //Inicializa el panel como tal
        principal = pPrincipal;
        setLayout(new GridLayout(11, 1));
        setPreferredSize( new Dimension (130, 400) );
        TitledBorder border = new TitledBorder("Califique la Obra");
        border.setTitleColor( Color.BLACK );
        setBorder(border);

        ImageIcon icon0 = new ImageIcon("data/imagenes/0_estrellas.png");
        btnCero = new JButton(  );
        btnCero.setIcon( icon0 );
        btnCero.setActionCommand( CERO );
        btnCero.addActionListener( this );
        add(btnCero);
        
        JLabel space0 = new JLabel("");
        add(space0);

        ImageIcon icon1 = new ImageIcon("data/imagenes/1_estrellas.png");
        btnUno = new JButton(  );
        btnUno.setIcon( icon1 );
        btnUno.setActionCommand( UNO );
        btnUno.addActionListener(this);
        add(btnUno);
        
        JLabel space1 = new JLabel("");
        add(space1);

        ImageIcon icon2 = new ImageIcon("data/imagenes/2_estrellas.png" );
        btnDos = new JButton( );
        btnDos.setIcon(icon2);
        btnDos.setActionCommand( DOS );
        btnDos.addActionListener( this );
        add(btnDos);
        
        JLabel space2 = new JLabel("");
        add(space2);

        ImageIcon icon3 = new ImageIcon("data/imagenes/3_estrellas.png");
        btnTres = new JButton(  );
        btnTres.setIcon( icon3 );
        btnTres.setActionCommand( TRES );
        btnTres.addActionListener( this );
        add(btnTres);
        
        JLabel space3 = new JLabel("");
        add(space3);

        ImageIcon icon4 = new ImageIcon("data/imagenes/4_estrellas.png");
        btnCuatro = new JButton();
        btnCuatro.setIcon( icon4 );
        btnCuatro.setActionCommand( CUATRO );
        btnCuatro.addActionListener( this );
        add(btnCuatro);
        
        JLabel space4 = new JLabel("");
        add(space4);

        ImageIcon icon5 = new ImageIcon("data/imagenes/5_estrellas.png");
        btnCinco = new JButton(  );
        btnCinco.setIcon( icon5 );
        btnCinco.setActionCommand( CINCO );
        btnCinco.addActionListener( this );
        add(btnCinco);

    }
    @Override
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        switch(command)
        {
            case CERO:
                principal.agregarCalificacion( 0 );
                principal.actualizarCalificacion( );
                break;
            case UNO:
                principal.agregarCalificacion( 1 );
                principal.actualizarCalificacion( );
                break;
            case DOS:
                principal.agregarCalificacion( 2 );
                principal.actualizarCalificacion( );
                break;
            case TRES:
                principal.agregarCalificacion( 3 );
                principal.actualizarCalificacion( );
                break;
            case CUATRO:
                principal.agregarCalificacion( 4 );
                principal.actualizarCalificacion( );
                break;
            case CINCO:
                principal.agregarCalificacion( 5 );
                principal.actualizarCalificacion( );
                break;
        }

    }

}
