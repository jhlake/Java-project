package uniandes.cupi2.cupiMuseo.interfaz;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Panel con la imagen del banner.
 * @author jlake
 */
public class PanelBanner extends JPanel
{
    /*
     * Constructor
     */
    /**
     * Coloca la imagen del encabezado de la aplicación.
     */
    public PanelBanner( )
    {
        setLayout( new GridLayout(1,1) );
        setPreferredSize( new Dimension( 755, 160 ));
        JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/imagenes/titulo.jpg" );

        setBorder( new LineBorder( Color.BLACK ) );
        // La agrega a la etiqueta
        imagen = new JLabel( );
        imagen.setIcon( icono );
        add( imagen );


    }
}
