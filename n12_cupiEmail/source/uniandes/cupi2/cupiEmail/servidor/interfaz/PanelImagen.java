package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Clase que representa el panel que contiene la imagen del Servidor cupiEmail.
 * @author jlake
 */
public class PanelImagen extends JPanel{

	/*
	 * Constructor
	 */
	/**
     * Método que inicializa el panel
     */
    public PanelImagen()
    {
        setLayout( new GridLayout( ) );
        setPreferredSize( new Dimension( 706, 104 ) );
        JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/imagenes/servidor.png" );
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );
        setBackground( Color.white );
        setBorder( new LineBorder( Color.BLACK ) );
    }
}
