package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.*;

import uniandes.cupi2.lucesApagadas.mundo.*;

/**
 * Ventana principal de la aplicación.
 * @author jlake
 */
public class InterfazLucesApagadas extends JFrame
{

    /*
     * Constantes
     */

    /**
     * Constante que representa la ruta a la imagen de la ventana apagada.
     */
    public final static String RUTA_IMAGEN_VENTANA_APAGADA = "data/imagenes/ventana_apagada.png";

    /**
     * Constante que representa la ruta a la imagen de la ventana apagada con un fantasma.
     */
    public final static String RUTA_IMAGEN_VENTANA_APAGADA_FANTASMA = "data/imagenes/ventana_apagada_fantasma.png";

    /**
     * Constante que representa la ruta a la imagen de la ventana encendida.
     */
    public final static String RUTA_IMAGEN_VENTANA_ENCENDIDA = "data/imagenes/ventana_encendida.png";

    /**
     * Constante que representa la ruta a la imagen de la ventana encendida con un fantasma.
     */
    public final static String RUTA_IMAGEN_VENTANA_ENCENDIDA_FANTASMA = "data/imagenes/ventana_encendida_fantasma.png";

    /*
     * Atributos
     */

    /**
     * Atributo que relaciona la interfaz con el mundo
     */
    private LucesApagadas lucesApagadas;

    /**
     * Atributo que representa el estado inicial del juego.
     */
    private Properties data;

    /**
     * Panel que contiene la imagen del título.
     */
    private PanelImagen panelImagen;

    /**
     * Panel que contiene el tablero de juego.
     */
    private PanelTablero panelTablero;

    /**
     * Panel que contiene los botones de la aplicación.
     */
    private PanelExtension panelExtension;


    /*
     * Métodos
     */

    /*
     * Constructor
     */
    /**
     * Método constructor de la ventana principal.
     */
    public InterfazLucesApagadas()
    {
        lucesApagadas = new LucesApagadas();
        setLayout(new BorderLayout());
        setTitle("Luces Apagadas");
        setSize(new Dimension(820, 650));
        setResizable( false );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        panelImagen = new PanelImagen();
        add(panelImagen, BorderLayout.NORTH);

        panelExtension = new PanelExtension(this);
        add(panelExtension, BorderLayout.SOUTH);
    }

    /**
     * El método devuelve la posición en X del fantasma.
     * @return Un entero que representa la posición del fantasma en X en la matriz.
     */
    public int darPosicionActualXFantasma()
    {
        return lucesApagadas.darPosicionXFantasma( );
    }

    /**
     * El método devuelve la posición del Y del fantasma.
     * @return Un entero que representa la posición del fantasma en Y en la matriz.
     */
    public int darPosicionActualYFantasma()
    {
        return lucesApagadas.darPosicionYFantasma( );
    }

    /**
     * El método carga un juego nuevo, basado en el archivo que el usuario elija. <br>
     * <b> post: </b> El juego inicia, se ponen todos los botones. <br>
     * @throws Exception Si ocurre un error al cargar el archivo del tablero de juego.
     */
    public void cargarJuego() throws Exception
    {   
        //Archivo properties temporal por si se selecciona un archivo incorrecto no se borre el juego actual
        Properties temp = null;
        JFileChooser fc = new JFileChooser("data/");
        int returnVal = fc.showOpenDialog( this );
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            //Condición por si ya existe un juego antes, que lo borre, para que no queden 2 paneles.
            if(data !=null)
            {
                remove(panelTablero);
                temp = data;
            }
            //Agregar el panel nuevo.
            panelTablero = new PanelTablero(this);
            add(panelTablero, BorderLayout.CENTER);
            revalidate();
            data = new Properties();
            File file = fc.getSelectedFile( );
            FileInputStream fis = new FileInputStream(file);
            try
            {
                data.load( fis );
                fis.close( );
                lucesApagadas.inicializarTablero( data );
                panelTablero.inicializar( lucesApagadas );
            }
            catch(Exception ex)
            {
                //Vuelve a agregar el tablero que estaba antes ahí, para que no se borre el juego anterior.
                data = temp;
                panelTablero.inicializar( lucesApagadas );
                JOptionPane.showMessageDialog( null, "El archivo no tiene el formato esperado.", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( null, "Debe seleccionar un archivo de configuración para poder jugar.", "Luces Apagadas", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * El método presiona una ventana en la interfaz. <br>
     * <b> pre: </b> El juego ya debe estar cargado e inicializado. <br>
     * <b> post: </b> Se modifican la ventanas necesarias basado en qué ventana se undió. Se cambia la posición del fantasma. Luego, se revisa si todas las ventanas están apagadas. En dado caso que sí, se le informa al jugador que ha ganado la partida. <br>
     * @param x Coordenada en X de la ventana. x != null && x >= 0 && x < lucesApagadas.NUMERO_COLUMNAS.
     * @param y Coordenada en Y de la ventana. y != null && y >= 0 && x < lucesApagadas.NUMERO_FILAS.
     */
    public void presionarVentana(int x, int y)
    {
        lucesApagadas.presionarVentana( x, y );
        panelTablero.actualizar( lucesApagadas );
        panelTablero.cambiarPosicionFantasma( lucesApagadas.darVentanas( )[x][y], x, y );
        if(lucesApagadas.verificarLucesApagadas())
        {
            JOptionPane.showMessageDialog( null, "¡Felicitaciones! \n" + "Ganó el juego.", "Felicitaciones", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    /**
     * El método da las estadísticas del juego actuál. <br>
     * <b> post: </b> Se le informa al usuario de las estadísticas del juego en curso, si es que existe. Si no, se le avisa si ya ha terminado el juego, o si no existe un juego en curso. <br>
     */
    public void darEstadisticas()
    {
        
        if(data !=null)
        {
            if(lucesApagadas.verificarLucesApagadas( ))
            {
                JOptionPane.showMessageDialog( null, "El juego ha terminado, no hay ventanas encendidas.", "Estadisticas", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                JOptionPane.showMessageDialog( null, "Cantidad de ventanas encendidas: " + lucesApagadas.darCantidadVentanasEncendidas( ) + ". \n" + "La fila con más ventanas encendidas es: " + lucesApagadas.darFilaMasVentanasEncendidas( ) + ".", "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog( null, "No hay ningún juego en curso.", "Estadísticas", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reinicia el juego actual. <br>
     * <b> post: </b> El juego vuelve al estado original, antes de haber undido cualquier ventana. <br>
     */
    public void reinciarJuego()
    {
        if(data != null)
        {
            remove(panelTablero);
            panelTablero = new PanelTablero(this);
            add(panelTablero, BorderLayout.CENTER);
            revalidate();
            lucesApagadas.inicializarTablero( data );
            panelTablero.inicializar( lucesApagadas );
        }
        else
        {
            JOptionPane.showMessageDialog( null, "No hay ningún juego en curso.", "Reiniciar Juego", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     * Puntos de Extensión
     */ 

    /**
     * Método para la extensión 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = lucesApagadas.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = lucesApagadas.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /*
     * Main
     */

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param args Argumentos del programa. args != null.
     */
    public static void main( String[] args )
    {
        InterfazLucesApagadas interfaz = new InterfazLucesApagadas();
        interfaz.setVisible( true );

    }

}
