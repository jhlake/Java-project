/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: TablerosJuegoTest.java 651 2006-11-16 15:06:50Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 2/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testCliente;

import junit.framework.*;
import uniandes.cupi2.batallaNaval.cliente.*;

/**
 * Esta es la clase usada para verificar los métodos de la clase TableroFlota y Tablero
 */
public class TablerosJuegoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una de las clases donde se harán las pruebas
     */
    private TableroFlota tablero;
    
    /**
     * Es una de las clases donde se harán las pruebas
     */
    private Tablero ataque;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo tablero vacío
     */
    private void setupEscenario1( )
    {
        tablero = new TableroFlota( );
        ataque = new Tablero( );
    }

    /**
     * Construye un nuevo tablero y lo inicializa con barcos
     */
    private void setupEscenario2( )
    {
        tablero = new TableroFlota( );
        ataque = new Tablero( );
        tablero.inicializarBarcosTablero( );
    }

    /**
     * Verifica el constructor. <br>
     * <b> Métodos a probar: </b> <br>
     * Tablero. <br>
     * <b> Objetivo: </b> Probar que el método Tablero() cree correctamente el tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un tablero, sus dimensiones deben ser de 9x9. <br>
     * 2. Al crear un tablero, sus casillas deben estar vacías.
     */
    public void testTablerosJuego( )
    {
        setupEscenario1( );

        Casilla[][] tJugador = tablero.darTablero( );

        assertEquals( "El tamaño del tablero del jugador está equivocado", 9, tJugador.length );

        for( int i = 0; i < 9; i++ )
        {
            assertEquals( "El tamaño del tablero del jugador está equivocado", 9, tJugador[ i ].length );

            for( int j = 0; j < 9; j++ )
            {
                assertEquals( "Las casillas deberían estar vacías", Casilla.VACIA, tJugador[ i ][ j ].darEstado( ) );
            }
        }

        assertFalse( "No debería haber barcos inicialmente", tablero.hayBarcos( ) );
    }

    /**
     * Verifica el método atacarCasilla. <br>
     * <b> Métodos a probar: </b> <br>
     * atacarCasilla. <br>
     * <b> Objetivo: </b> Probar que el método atacarCasilla() cambie correctamente el estado de las casillas del tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al atacar una casilla vacía, el resultado del ataque debe ser TableroFlota.RESULTADO_AGUA. <br>
     * 2. Al atacar una casilla ocupada, el resultado del ataque debe ser TableroFlota.RESULTADO_IMPACTO <br>
     *    ó TableroFlota.RESULTADO_HUNDIMIENTO. <br>
     */
    public void testAtacarCasilla( )
    {
        setupEscenario2( );
        Casilla[][] tJugador = tablero.darTablero( );

        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                Casilla casilla = tJugador[ i ][ j ];

                if( casilla.darEstado( ) == Casilla.VACIA )
                {
                    assertEquals( "El resultado del ataque fue equivocado", TableroFlota.RESULTADO_AGUA, tablero.atacarCasilla( i, j ) );
                }
                else if( casilla.darEstado( ) == Casilla.OCUPADA )
                {
                    Barco b = casilla.darBarco( );
                    int puntosAntes = b.darPuntos( );

                    if( puntosAntes > 1 )
                        assertEquals( "El resultado del ataque fue equivocado", TableroFlota.RESULTADO_IMPACTO, tablero.atacarCasilla( i, j ) );
                    else
                        assertEquals( "El resultado del ataque fue equivocado", TableroFlota.RESULTADO_HUNDIMIENTO, tablero.atacarCasilla( i, j ) );

                    int puntosDespues = b.darPuntos( );
                    assertTrue( "El número de puntos del barco no disminuyó como debería", puntosAntes == puntosDespues + 1 );
                }
            }
        }
    }
    /**
     * Verifica que el método darBarcoCasilla retorne el mismo barco que se encuentra en la casilla indicada.<br>
     * <b> Métodos a probar: </b> <br>
     * darBarcoCasilla, localizarBarco. <br>
     * <b> Objetivo: </b> Probar que el método darBarcoCasilla() retorne correctamente el barco que se encuentra en la casilla. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el barco de una casilla es X. Al pedir el barco de la casilla debe ser X. <br>             
     */
    public void testDarBarcoCasilla( )
    {
        setupEscenario2( );

        Casilla[][] tJugador = tablero.darTablero( );

        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                assertSame( "Si hay barco, debería ser el mismo", tablero.localizarBarco( i, j ), tJugador[ i ][ j ].darBarco( ) );
            }
        }
    }

    /**
     * Verifica el método darCasillaJugador. <br>
     * <b> Métodos a probar: </b> <br>
     * darCasillaJugador. <br>
     * <b> Objetivo: </b> Probar que el método darCasilla() retorne correctamente la casilla pedida. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la casilla en la posición (i,j), se debe obtener efectivamente tal casilla. <br>    
     * 
     */
    public void testDarCasilla( )
    {
        setupEscenario2( );

        Casilla[][] tJugador = tablero.darTablero( );

        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                assertSame( "La casilla retornada debería ser la misma", tJugador[ i ][ j ], tablero.darCasilla( i, j ) );
            }
        }
    }

    /**
     * Verifica el método hayBarcos en un tablero vacío. <br>
     * <b> Métodos a probar: </b> <br>
     * hayBarcos. <br>
     * <b> Objetivo: </b> Probar que el método hayBarcos() indique correctamente cuando hay barcos en el tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al preguntar si hay barcos en un tablero vacío, se debe obtener false. <br>    
     */
    public void testHayBarcos1( )
    {
        setupEscenario1( );

        assertFalse( "No debería haber barcos", tablero.hayBarcos( ) );
    }

    /**
     * Verifica el método hayBarcos en un tablero que no está vacío.<br>
     * <b> Métodos a probar: </b> <br>
     * hayBarcos. <br>
     * <b> Objetivo: </b> Probar que el método hayBarcos() indique correctamente cuando hay barcos en el tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al preguntar si hay barcos en un tablero no vacío, se debe obtener true. <br>    
     */
    public void testHayBarcos2( )
    {
        setupEscenario2( );

        assertTrue( "Debería haber barcos", tablero.hayBarcos( ) );
    }

    /**
     * Verifica el método hayBarcos en un tablero que no está vacío en el que los barcos se van eliminando. <br>
     * <b> Métodos a probar: </b> <br>
     * hayBarcos. <br>
     * <b> Objetivo: </b> Probar que el método hayBarcos() indique correctamente cuando hay barcos en el tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al preguntar si hay barcos en un tablero vacío, se debe obtener false. <br>    
     * 2. Al preguntar si hay barcos en un tablero no vacío, se debe obtener true. <br>
     */
    public void testHayBarcos3( )
    {
        setupEscenario2( );

        int numeroBarcosRestantes = 5;

        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                int resultado = tablero.atacarCasilla( i, j );
                if( resultado == TableroFlota.RESULTADO_HUNDIMIENTO )
                    numeroBarcosRestantes--;

                if( numeroBarcosRestantes > 0 )
                    assertTrue( "Debería haber barcos", tablero.hayBarcos( ) );
                else
                    assertFalse( "Ya no debería haber barcos", tablero.hayBarcos( ) );
            }
        }
    }

    /**
     * Verifica el método marcarCasilla. <br>
     * <b> Métodos a probar: </b> <br>
     * marcarCasilla. <br>
     * <b> Objetivo: </b> Probar que el método marcarCasilla() marque correctamente una casilla. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al marcar una casilla con un estado X, al pedir su estado se debe obtener X.         
     */
    public void testMarcarCasilla( )
    {
        setupEscenario2( );

        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                ataque.marcarCasilla( i, j, Casilla.ATACADA );
                assertEquals( "El estado de la casilla no cambió", Casilla.ATACADA, ataque.darCasilla( i, j ).darEstado( ) );

                ataque.marcarCasilla( i, j, Casilla.IMPACTADA );
                assertEquals( "El estado de la casilla no cambió", Casilla.IMPACTADA, ataque.darCasilla( i, j ).darEstado( ) );

                ataque.marcarCasilla( i, j, Casilla.OCUPADA );
                assertEquals( "El estado de la casilla no cambió", Casilla.OCUPADA, ataque.darCasilla( i, j ).darEstado( ) );

                ataque.marcarCasilla( i, j, Casilla.VACIA );
                assertEquals( "El estado de la casilla no cambió", Casilla.VACIA, ataque.darCasilla( i, j ).darEstado( ) );
            }
        }
    }
}
