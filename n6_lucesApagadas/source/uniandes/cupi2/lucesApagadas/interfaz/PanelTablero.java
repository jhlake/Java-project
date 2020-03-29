package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import uniandes.cupi2.lucesApagadas.mundo.LucesApagadas;
import uniandes.cupi2.lucesApagadas.mundo.Ventana;

/**
 * Clase que representa el panel del tablero.
 * @author jlake
 */
public class PanelTablero extends JPanel implements ActionListener
{
    /*
     * Atributos
     */
    /**
     * Relaci�n con la clase principal de la interfaz.
     */
    private InterfazLucesApagadas principal;

    /*
     * Atributos de la interfaz
     */

    /**
     * Botones del tablero de juego.
     */
    private JButton[][] tableroDeJuegoGrafico;

    /*
     * Constructor
     */
    /**
     * M�todo que inicializa el tablero.
     * @param ventana Panel padre.
     */
    public PanelTablero(InterfazLucesApagadas ventana)
    {
        principal = ventana;
        setLayout(new GridLayout(LucesApagadas.NUMERO_FILAS, LucesApagadas.NUMERO_COLUMNAS));
        setBackground( Color.BLUE );
        tableroDeJuegoGrafico = new JButton[LucesApagadas.NUMERO_FILAS][LucesApagadas.NUMERO_COLUMNAS];
    }

    /**
     * El m�todo cambia al fantasma de posici�n a la posici�n que se undi� la ventana. <br>
     * <b> post: </b> El fantasma se posiciona en la �ltima ventana presionada. <br>
     * @param ventana La ventana espec�fica que se undi�. ventana != null
     * @param x La posici�n en X de la ventana presionada. x != null && x >= 0 && x < NUMERO_FILAS.
     * @param y La posici�n en Y de la ventnaa presionada. y != null && y >= 0 && y < NUMERO_COLUMNAS.
     */
    public void cambiarPosicionFantasma(Ventana ventana, int x, int y)
    {
        if(ventana.darEstado( ) == Ventana.ENCENDIDO)
        {
            ImageIcon icon = new ImageIcon(principal.RUTA_IMAGEN_VENTANA_ENCENDIDA_FANTASMA);
            tableroDeJuegoGrafico[x][y].setIcon( icon );
        }
        else
        {
            ImageIcon icon = new ImageIcon(principal.RUTA_IMAGEN_VENTANA_APAGADA_FANTASMA);
            tableroDeJuegoGrafico[x][y].setIcon(icon);
        }
    }

    /**
     * El m�todo actualiza el tablero del juego, asign�ndole la imagen de encedido o apagado a cada ventana, basado en la informaci�n dada por par�metro. <br>
     * <b> post: </b> Las ventanas quedan con la imagen correspondiente. <br>
     * @param lucesApagadas La informaci�n  de todas las ventanas en el mundo del problema, y sus estados. lucesApagadas != null
     */
    public void actualizar(LucesApagadas lucesApagadas)
    {
        for(int i= 0; i<LucesApagadas.NUMERO_FILAS; i++)
        {   
            for(int j = 0; j<LucesApagadas.NUMERO_COLUMNAS; j++)
            {
                if(lucesApagadas.darVentanas( )[i][j].darEstado( ) == Ventana.ENCENDIDO)
                {
                    ImageIcon icon = new ImageIcon(InterfazLucesApagadas.RUTA_IMAGEN_VENTANA_ENCENDIDA);
                    tableroDeJuegoGrafico[i][j].setIcon( icon );
                }
                else if(lucesApagadas.darVentanas( )[i][j].darEstado( ) == Ventana.APAGADO)
                {
                    ImageIcon icon = new ImageIcon(InterfazLucesApagadas.RUTA_IMAGEN_VENTANA_APAGADA);
                    tableroDeJuegoGrafico[i][j].setIcon( icon );
                }
            }
        }
    }

    /**
     * El m�todo inicializa los botones del tablero de juego, asign�ndole la imagen de encendido o apagado a cada ventana, basado en la informaci�n dada por par�metro. <br>
     * <b> post: </b> Las ventanas quedan con la imagen correspondiente. <br>
     * @param lucesApagadas La informaci�n de todas las ventanas en el mundo del problema, y sus estados. lucesApagadas != null
     */
    public void inicializar(LucesApagadas lucesApagadas)
    {
        for(int j = 0; j<lucesApagadas.NUMERO_FILAS;j++)
        {
            for(int i = 0; i<lucesApagadas.NUMERO_COLUMNAS;i++)
            {
                tableroDeJuegoGrafico[j][i] = new JButton();
                if(lucesApagadas.darVentanas( )[j][i].darEstado( ) == Ventana.ENCENDIDO)
                {
                    ImageIcon icon = new ImageIcon(InterfazLucesApagadas.RUTA_IMAGEN_VENTANA_ENCENDIDA);
                    tableroDeJuegoGrafico[j][i].setIcon( icon );
                    tableroDeJuegoGrafico[j][i].setActionCommand( i + "," +j);
                }
                else
                {
                    ImageIcon icon = new ImageIcon(InterfazLucesApagadas.RUTA_IMAGEN_VENTANA_APAGADA);
                    tableroDeJuegoGrafico[j][i].setIcon( icon );
                    tableroDeJuegoGrafico[j][i].setActionCommand(i + "," + j);
                }

                tableroDeJuegoGrafico[j][i].addActionListener( this );
                add(tableroDeJuegoGrafico[j][i]);
            }
        }
        revalidate();
    }

    /**
     * Manejo de los eventos de los botones.
     * @param e Acci�n que gener� el evento. e != null.
     */
    @Override
    public void actionPerformed( ActionEvent e )
    {
        for(int i = 0; i<LucesApagadas.NUMERO_COLUMNAS; i++)
        {
            for(int j = 0; j<LucesApagadas.NUMERO_FILAS;j++)
            {
                if(e.getSource( ) == tableroDeJuegoGrafico[i][j])
                {
                    principal.presionarVentana( i, j );
                }
            }
        }
        
        
    }
    
}
