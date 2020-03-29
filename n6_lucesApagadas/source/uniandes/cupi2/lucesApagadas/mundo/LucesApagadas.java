package uniandes.cupi2.lucesApagadas.mundo;

import java.util.Properties;

/**
 * Clase que representa el mundo del juego Luces Apagadas.
 * @author jlake
 */
public class LucesApagadas
{

    /*
     * Constantes
     */
    /**
     * Constante que representa el número de filas que hay en un tablero.
     */
    public final static int NUMERO_FILAS = 7;

    /**
     * Constante que representa el número de columnas que hay en un tablero.
     */
    public final static int NUMERO_COLUMNAS = 7;

    /*
     * Atributos
     */
    /**
     * Matriz con todas las ventanas del juego.
     */
    private Ventana[][] ventanas;

    /**
     * Atributo que representa la posición del fantasma en X.
     */
    private int posXFantasma;

    /**
     * Atributo que representa la posición del fantasma en Y.
     */
    private int posYFantasma;

    /*
     * Métodos
     */

    /*
     * Constructor
     */
    /**
     * Método constructor del mundo.
     */
    public LucesApagadas()
    {
        ventanas = new Ventana[NUMERO_FILAS][NUMERO_COLUMNAS];
    }

    /**
     * El método da la posición del fantasma en X.
     * @return Un entero que es la posición en X del fantasma en el vector.
     */
    public int darPosicionXFantasma()
    {
        return posXFantasma;
    }
    /**
     * El método da la posición del fantasma en Y.
     * @return Un entero que es la posición en Y del fantasma en el vector.
     */
    public int darPosicionYFantasma()
    {
        return posYFantasma;
    }

    /**
     * El método retorna la matriz de ventanas. <br> 
     * <b> pre:  </b> La matriz de ventanas debe estar inicializada. <br>
     * @return La matriz de ventanas.
     */
    public Ventana[][] darVentanas()
    {
        return ventanas;
    }

    /**
     * Este método oprime la ventana en la posición dada por parámetro. <br>
     * <b> pre: </b> La matriz de ventanas debe estar inicializada. <br>
     * <b> post: </b> La ventana cambió de encendida a apagada o vice-versa. Las ventanas inmediatamente arriba, abajo, y a los lados también cambiaron de estado. <br>
     * @param x Columna en la que está la ventana a ser oprimida. x != null && x >= 0 && x < NUMERO_FILAS. 
     * @param y Fila en la que está la ventana a ser oprimida. y != null && y >= 0 && y < NUMERO_COLUMNAS.
     */
    public void presionarVentana(int x, int y)
    {
        if(x != 0)
        {
            ventanas[x-1][y].cambiarEstado( );
        }
        if(x != (NUMERO_FILAS - 1))
        {
            ventanas[x+1][y].cambiarEstado( );
        }
        if(y != 0)
        {
            ventanas[x][y-1].cambiarEstado( );
        }
        if(y != (NUMERO_COLUMNAS -1))
        {
            ventanas[x][y+1].cambiarEstado( );
        }
        ventanas[x][y].cambiarEstado( );
        posXFantasma = x;
        posYFantasma = y;
    }

    /**
     * El método devuelve cuál fila tiene más ventanas encendidas. <br>
     * <b> pre: </b> La matriz de ventanas está inicializada. <br>
     * @return Entero que representa la fila con más ventanas encendidas. Si hay 2 filas con la misma cantidad, devuelve la primera.
     */
    public int darFilaMasVentanasEncendidas()
    {
        int mas = -1;
        int comp = 0;
        for(int i = 0; i<NUMERO_FILAS; i++)
        {
            int comp2 =0;
            for(int j = 0; j < NUMERO_COLUMNAS; j++)
            {
                if(ventanas[i][j].darEstado( ) == Ventana.ENCENDIDO)
                {
                    comp2++;
                }
            }
            if(comp < comp2)
            {
                comp = comp2;
                mas = i;
            }
        }
        return mas;
    }

    /**
     * El método devuelve la cantidad de ventanas encendidas en total. <br>
     * <b> pre: </b> La matriz de ventanas está inicializada <br>
     * @return Un entero que es la cantidad de ventanas encendidas.
     */
    public int darCantidadVentanasEncendidas()
    {
        int count = 0;
        for(int i = 0; i < NUMERO_FILAS; i++)
        {
            for(int j = 0; j < NUMERO_COLUMNAS; j++)
            {
                if(ventanas[i][j].darEstado( ) == 1)
                {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * El método verifica si todas las luces están apagadas.
     * @return True si todas están apagdas y el usuario ha ganado el juego. False si aún hay luces encendidas.
     */
    public boolean verificarLucesApagadas()
    {
        boolean ans = false;
        if(darCantidadVentanasEncendidas() == 0)
        {
           ans= true;
        }
        return ans;
    }


    /**
     * El método inicializa el tablero basado en el archivo properties que recibe como parámetro.
     * @param data Archivo que contiene el estado inicial de las ventanas. data != null.
     */
    public void inicializarTablero(Properties data)
    {
        for(int i = 0; i<NUMERO_FILAS; i++)
        {
            String value = data.getProperty( "cupiCastillo.fila" + (i+1));
            for(int j = 0; j < NUMERO_COLUMNAS; j++)
            {
                if(value.charAt(j) == '0')
                {
                    ventanas[i][j] = new Ventana(Ventana.APAGADO);
                }
                else
                {
                    ventanas[i][j] = new Ventana(Ventana.ENCENDIDO);
                }

            }
        }
    }


    /*
     * Métodos de la extensión
     */

    /**
     * Método para la extensión 1.
     * @return respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * Método para la extensión 2.
     * @return respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }
}
