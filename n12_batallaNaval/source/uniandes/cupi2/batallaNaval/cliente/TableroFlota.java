/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: TableroFlota.java 638 2006-11-14 05:27:49Z da-romer $ 
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

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Esta clase representa un tablero de juego con la flota de barcos del jugador<br>
 * <b>inv:</b><br>
 * barcos != null<br>
 * todos los barcos están en el tablero
 */
public class TableroFlota extends Tablero
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que un barco está horizontal
     */
    private static final int HORIZONTAL = 0;

    /**
     * Indica que un barco está vertical
     */
    private static final int VERTICAL = 1;

    /**
     * Indica que el blanco de un disparo no fue alcanzado
     */
    public static final int RESULTADO_AGUA = 0;

    /**
     * Indica que el blanco de un disparo fue alcanzado pero no fue hundido
     */
    public static final int RESULTADO_IMPACTO = 1;

    /**
     * Indica que el blanco de un disparo fue alcanzado y hundido
     */
    public static final int RESULTADO_HUNDIMIENTO = 2;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una lista con los barcos que sobreviven en el tablero de juego
     */
    private List barcos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el tablero de juego sin barcos<br>
     */
    public TableroFlota( )
    {
        barcos = new LinkedList( );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el barco que se encuentra en una casilla del tablero del jugador.
     * @param fila La fila de la casilla - 0 <= fila < NUMERO_FILAS
     * @param columna La columna de la casilla - 0 <= columna < NUMERO_COLUMNAS
     * @return Retorna el barco que se encuentra en la casilla. Si en la casilla no hay barco retorna null
     */
    public Barco localizarBarco( int fila, int columna )
    {
        return tablero[ fila ][ columna ].darBarco( );
    }

    /**
     * Indica si quedan barcos en el tablero del jugador
     * @return Retorna true si quedan barcos en el tablero
     */
    public boolean hayBarcos( )
    {
        return barcos.size( ) != 0;
    }

    /**
     * Inicializa los barcos sobre el tablero del jugador<br>
     * Se dejan 5 barcos en el tablero: <br>
     * Portaviones - Resistencia 5 <br>
     * Destructor - Resistencia 4 <br>
     * Fragata - Resistencia 3 <br>
     * Fragata - Resistencia 3 <br>
     * Submarino - Resistencia 2
     */
    public void inicializarBarcosTablero( )
    {
        barcos = new LinkedList( );
        desplegarBarco( new Barco( "Portaviones", 5, new Color( 80, 80, 80 ) ) );
        desplegarBarco( new Barco( "Destructor", 4, new Color( 100, 70, 70 ) ) );
        desplegarBarco( new Barco( "Fragata", 3, new Color( 70, 70, 100 ) ) );
        desplegarBarco( new Barco( "Fragata", 3, new Color( 70, 100, 70 ) ) );
        desplegarBarco( new Barco( "Submarino", 2, new Color( 50, 100, 100 ) ) );
        verificarInvariante( );
    }

    /**
     * Este método se encarga de actualizar el estado de las casillas del jugador cuando se recibe un disparo
     * @param fila La fila de la casilla - 0 <= fila < NUMERO_FILAS
     * @param columna La columna de la casilla - 0 <= columna < NUMERO_COLUMNAS
     * @return Retorna una constante que indica el resultado del ataque.<br>
     *         RESULTADO_AGUA indica que el ataque no le dio a nada<br>
     *         RESULTADO_IMPACTO indica que el ataque le dio a un barco, pero no lo hundió<br>
     *         RESULTADO_HUNDIMIENTO indica que el ataque le dio a un barco y lo hundió
     */
    public int atacarCasilla( int fila, int columna )
    {
        int resultado = 0;
        Casilla casilla = tablero[ fila ][ columna ];
        casilla.atacarCasilla( );
        if( casilla.darEstado( ) == Casilla.IMPACTADA )
        {
            // Si hubo un impacto hay que revisar si el barco se hundió y eliminarlo si es necesario
            Barco barcoAtacado = casilla.darBarco( );

            if( barcoAtacado.estaHundido( ) )
            {
                barcos.remove( barcoAtacado );
                resultado = RESULTADO_HUNDIMIENTO;
            }
            else
            {
                resultado = RESULTADO_IMPACTO;
            }
        }
        else
        {
            resultado = RESULTADO_AGUA;
        }
        verificarInvariante( );
        return resultado;
    }

    // -----------------------------------------------------------------
    // Métodos privados
    // -----------------------------------------------------------------

    /**
     * Busca un sitio del tablero donde quepa el barco y lo deja allí
     * @param barco El barco que debe ser agregado - barco != null
     */
    private void desplegarBarco( Barco barco )
    {
        int orientacion = 0, fila = 0, columna = 0;

        // Buscar una posición vacía donde quepa el barco
        boolean posicionEncontrada = false;
        while( !posicionEncontrada )
        {
            // Escoger una orientación
            orientacion = ( Math.random( ) < 0.5 ) ? HORIZONTAL : VERTICAL;

            // Escoger una fila y una columna
            fila = ( int ) ( Math.random( ) * 9 );
            columna = ( int ) ( Math.random( ) * 9 );

            if( fila == 9 )
                fila--;
            if( columna == 9 )
                fila--;

            // Verificar si el barco puede ubicarse en esa posición
            posicionEncontrada = verificarBarcoCabe( fila, columna, orientacion, barco.darPuntos( ) );
        }
        // Colocar el barco en la posición encontrada
        colocarBarco( fila, columna, orientacion, barco );
    }

    /**
     * Verifica si un barco cabe en la posición indicada
     * @param fila La fila donde estará la punta superior izquierda del barco - 0 <= fila < NUMERO_FILAS
     * @param columna La columna donde estará la punta superior izquierda del barco - 0 <= columna < NUMERO_COLUMNAS
     * @param orientacion La orientación del barco (HORIZONTAL o VERTICAL)
     * @param largo La longitud del barco - largo > 0
     * @return Retorna true si el barco puede colocarse en la posición indicada (cabe dentro del tablero y no queda sobre una posición ya ocupada)
     */
    private boolean verificarBarcoCabe( int fila, int columna, int orientacion, int largo )
    {
        // Verificar que el barco quepa en el tablero
        if( orientacion == HORIZONTAL && columna + largo >= 9 )
            return false;

        if( orientacion == VERTICAL && fila + largo >= 9 )
            return false;

        // Verificar que ninguna de las posiciones que ocupará el barco esté ocupada
        boolean hayCasillaOcupada = false;
        int[][] casillasBarco = calcularCasillasBarco( fila, columna, orientacion, largo );
        for( int i = 0; i < largo && !hayCasillaOcupada; i++ )
        {
            int filaBarco = casillasBarco[ i ][ 0 ];
            int columnaBarco = casillasBarco[ i ][ 1 ];

            Casilla c = tablero[ filaBarco ][ columnaBarco ];
            if( c.darEstado( ) != Casilla.VACIA )
                hayCasillaOcupada = true;
        }
        return !hayCasillaOcupada;
    }

    /**
     * Coloca el barco dentro del tablero en la posición indicada
     * @param fila La fila donde estará la punta del barco - 0 <= fila < NUMERO_FILAS
     * @param columna La columna donde estará la punta del barco - 0 <= columna < NUMERO_COLUMNAS
     * @param orientacion La orientación (HORIZONTAL o VERTICAL)
     * @param barco El barco que debe quedar en el tablero - barco != null
     */
    private void colocarBarco( int fila, int columna, int orientacion, Barco barco )
    {
        int[][] casillasBarco = calcularCasillasBarco( fila, columna, orientacion, barco.darPuntos( ) );
        for( int i = 0; i < barco.darPuntos( ); i++ )
        {
            int filaBarco = casillasBarco[ i ][ 0 ];
            int columnaBarco = casillasBarco[ i ][ 1 ];

            Casilla c = tablero[ filaBarco ][ columnaBarco ];
            c.cambiarBarco( barco );
        }
        barcos.add( barco );
    }

    /**
     * Calcula cuales serán las casillas que ocupará el barco dada su posición en el tablero
     * @param fila La fila donde estará la punta superior izquierda del barco - 0 <= fila < NUMERO_FILAS
     * @param columna La columna donde estará la punta superior izquierda del barco - 0 <= columna < NUMERO_COLUMNAS
     * @param orientacion La orientación (HORIZONTAL o VERTICAL)
     * @param largo La longitud del barco - largo > 0
     * @return Retorna un arreglo con las coordenadas de las casillas que ocupará el barco. <br>
     *         En cada posición del arreglo retornado hay una pareja de enteros "fila, columna"
     */
    private int[][] calcularCasillasBarco( int fila, int columna, int orientacion, int largo )
    {
        int deltaFilas = ( orientacion == HORIZONTAL ) ? 0 : 1;
        int deltaColumnas = ( orientacion == HORIZONTAL ) ? 1 : 0;

        int[][] casillasOcupadas = new int[largo][2];
        for( int i = 0; i < largo; i++ )
        {
            casillasOcupadas[ i ][ 0 ] = fila + i * deltaFilas;
            casillasOcupadas[ i ][ 1 ] = columna + i * deltaColumnas;
        }
        return casillasOcupadas;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Este método se encarga de verificar el invariante de la clase TablerosJuego<br>
     * <b>inv:</b><br>
     * barcos != null<br>
     * todos los barcos están en el tablero
     */
    private void verificarInvariante( )
    {        
        assert barcos != null : "Debe haber una lista de barcos";        
        // Verificar que los barcos de la lista sean los mismos de las casillas
        for( int k = 0; k < barcos.size( ); k++ )
        {
            Barco buscado = ( Barco )barcos.get( k );
            boolean encontreBarco = false;

            for( int i = 0; i < 9 && !encontreBarco; i++ )
            {
                for( int j = 0; j < 9 && !encontreBarco; j++ )
                {
                    if( tablero[ i ][ j ].darBarco( ) == buscado )
                    {
                        encontreBarco = true;
                    }
                }
            }
            assert ( encontreBarco ) : "No se encontró un barco dentro de las casillas";
        }
    }
}
