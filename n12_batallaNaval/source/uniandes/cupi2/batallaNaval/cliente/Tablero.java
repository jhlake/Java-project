/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Tablero.java 613 2006-11-09 16:47:38Z da-romer $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 
 * Ejercicio: n12_batallaNaval 
 * Autor: Mario Sánchez - 1/03/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.batallaNaval.cliente;


/**
 * Esta clase representa un tablero de juego<br>
 * <b>inv:</b><br>
 * tablero es una matriz de 9 * 9 casillas y ninguna es null<br>
 */
public class Tablero
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Son las casillas que constituyen el tablero de juego
     */
    protected Casilla[][] tablero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el tablero, dejando todas las casillas vacías.<br>
     */
    public Tablero( )
    {
        tablero = new Casilla[9][9];
        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                tablero[ i ][ j ] = new Casilla( );
            }
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el contenido del tablero (este método solo se debe llamar desde las pruebas)
     * @return contenido del tablero
     */
    public Casilla[][] darTablero( )
    {
        return tablero;
    }

    /**
     * Retorna una casilla del tablero
     * @param fila La fila donde está la casilla - 0 <= fila < 9
     * @param columna La columna donde está la casilla - 0 <= columna < 9
     * @return Retorna la casilla indicada en el tablero
     */
    public Casilla darCasilla( int fila, int columna )
    {
        return tablero[ fila ][ columna ];
    }

    /**
     * Marca una casilla en el tablero del oponente con el estado indicado
     * @param fila La fila donde se encuentra la casilla - 0 <= fila < 9
     * @param columna La columna donde se encuentra la casilla - 0 <= columna < 9
     * @param nuevoEstado El nuevo estado que tendrá la casilla marcada - nuevoEstado está en {Casilla.VACIA, Casilla.OCUPADA, Casilla.ATACADA, Casilla.IMPACTADA}
     */
    public void marcarCasilla( int fila, int columna, int nuevoEstado )
    {
        tablero[ fila ][ columna ].marcar( nuevoEstado );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Este método se encarga de verificar el invariante de la clase TablerosJuego<br>
     * <b>inv:</b><br>
     * tablero es una matriz de 9 * 9 casillas y ninguna es null<br>
     */
    private void verificarInvariante( )
    {
        assert tablero != null && tablero.length == 9 : "Tablero inválido";
        for( int i = 0; i < 9; i++ )
        {
            assert tablero[ i ] != null && tablero[ i ].length == 9 : "Tablero inválido";
            for( int j = 0; j < 9; j++ )
            {
                assert tablero[ i ][ j ] != null : "Casilla nula";
            }
        }
    }
}
