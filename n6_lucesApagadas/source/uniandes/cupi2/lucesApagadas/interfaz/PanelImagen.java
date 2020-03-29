package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Clase que representa el panel de la imagen superior del programa.
 * @author jlake
 */
public class PanelImagen extends JPanel
{
    /*
     * Constructor
     */

    /**
     * Método que inicializa el panel
     */
    public PanelImagen()
    {
        setLayout( new GridLayout( ) );
        setPreferredSize( new Dimension( 820, 185 ) );
        JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/imagenes/titulo.jpg" );
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );
        setBackground( Color.blue );
        setBorder( new LineBorder( Color.BLACK ) );
    }

}
