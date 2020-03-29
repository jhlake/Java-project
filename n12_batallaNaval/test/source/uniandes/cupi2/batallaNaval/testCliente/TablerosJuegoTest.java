/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: TablerosJuegoTest.java 651 2006-11-16 15:06:50Z da-romer $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 2/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testCliente;

import junit.framework.*;
import uniandes.cupi2.batallaNaval.cliente.*;

/**
 * Esta es la clase usada para verificar los m�todos de la clase TableroFlota y Tablero
 */
public class TablerosJuegoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una de las clases donde se har�n las pruebas
     */
    private TableroFlota tablero;
    
    /**
     * Es una de las clases donde se har�n las pruebas
     */
    private Tablero ataque;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo tablero vac�o
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
     * <b> M�todos a probar: </b> <br>
     * Tablero. <br>
     * <b> Objetivo: </b> Probar que el m�todo Tablero() cree correctamente el tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un tablero, sus dimensiones deben ser de 9x9. <br>
     * 2. Al crear un tablero, sus casillas deben estar vac�as.
     */
    public void testTablerosJuego( )
    {
        setupEscenario1( );

        Casilla[][] tJugador = tablero.darTablero( );

        assertEquals( "El tama�o del tablero del jugador est� equivocado", 9, tJugador.length );

        for( int i = 0; i < 9; i++ )
        {
            assertEquals( "El tama�o del tablero del jugador est� equivocado", 9, tJugador[ i ].length );

            for( int j = 0; j < 9; j++ )
            {
                assertEquals( "Las casillas deber�an estar vac�as", Casilla.VACIA, tJugador[ i ][ j ].darEstado( ) );
            }
        }

        assertFalse( "No deber�a haber barcos inicialmente", tablero.hayBarcos( ) );
    }

    /**
     * Verifica el m�todo atacarCasilla. <br>
     * <b> M�todos a probar: </b> <br>
     * atacarCasilla. <br>
     * <b> Objetivo: </b> Probar que el m�todo atacarCasilla() cambie correctamente el estado de las casillas del tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al atacar una casilla vac�a, el resultado del ataque debe ser TableroFlota.RESULTADO_AGUA. <br>
     * 2. Al atacar una casilla ocupada, el resultado del ataque debe ser TableroFlota.RESULTADO_IMPACTO <br>
     *    � TableroFlota.RESULTADO_HUNDIMIENTO. <br>
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
                    assertTrue( "El n�mero de puntos del barco no disminuy� como deber�a", puntosAntes == puntosDespues + 1 );
                }
            }
        }
    }
    /**
     * Verifica que el m�todo darBarcoCasilla retorne el mismo barco que se encuentra en la casilla indicada.<br>
     * <b> M�todos a probar: </b> <br>
     * darBarcoCasilla, localizarBarco. <br>
     * <b> Objetivo: </b> Probar que el m�todo darBarcoCasilla() retorne correctamente el barco que se encuentra en la casilla. <br>
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
                assertSame( "Si hay barco, deber�a ser el mismo", tablero.localizarBarco( i, j ), tJugador[ i ][ j ].darBarco( ) );
            }
        }
    }

    /**
     * Verifica el m�todo darCasillaJugador. <br>
     * <b> M�todos a probar: </b> <br>
     * darCasillaJugador. <br>
     * <b> Objetivo: </b> Probar que el m�todo darCasilla() retorne correctamente la casilla pedida. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la casilla en la posici�n (i,j), se debe obtener efectivamente tal casilla. <br>    
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
                assertSame( "La casilla retornada deber�a ser la misma", tJugador[ i ][ j ], tablero.darCasilla( i, j ) );
            }
        }
    }

    /**
     * Verifica el m�todo hayBarcos en un tablero vac�o. <br>
     * <b> M�todos a probar: </b> <br>
     * hayBarcos. <br>
     * <b> Objetivo: </b> Probar que el m�todo hayBarcos() indique correctamente cuando hay barcos en el tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al preguntar si hay barcos en un tablero vac�o, se debe obtener false. <br>    
     */
    public void testHayBarcos1( )
    {
        setupEscenario1( );

        assertFalse( "No deber�a haber barcos", tablero.hayBarcos( ) );
    }

    /**
     * Verifica el m�todo hayBarcos en un tablero que no est� vac�o.<br>
     * <b> M�todos a probar: </b> <br>
     * hayBarcos. <br>
     * <b> Objetivo: </b> Probar que el m�todo hayBarcos() indique correctamente cuando hay barcos en el tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al preguntar si hay barcos en un tablero no vac�o, se debe obtener true. <br>    
     */
    public void testHayBarcos2( )
    {
        setupEscenario2( );

        assertTrue( "Deber�a haber barcos", tablero.hayBarcos( ) );
    }

    /**
     * Verifica el m�todo hayBarcos en un tablero que no est� vac�o en el que los barcos se van eliminando. <br>
     * <b> M�todos a probar: </b> <br>
     * hayBarcos. <br>
     * <b> Objetivo: </b> Probar que el m�todo hayBarcos() indique correctamente cuando hay barcos en el tablero. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al preguntar si hay barcos en un tablero vac�o, se debe obtener false. <br>    
     * 2. Al preguntar si hay barcos en un tablero no vac�o, se debe obtener true. <br>
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
                    assertTrue( "Deber�a haber barcos", tablero.hayBarcos( ) );
                else
                    assertFalse( "Ya no deber�a haber barcos", tablero.hayBarcos( ) );
            }
        }
    }

    /**
     * Verifica el m�todo marcarCasilla. <br>
     * <b> M�todos a probar: </b> <br>
     * marcarCasilla. <br>
     * <b> Objetivo: </b> Probar que el m�todo marcarCasilla() marque correctamente una casilla. <br>
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
                assertEquals( "El estado de la casilla no cambi�", Casilla.ATACADA, ataque.darCasilla( i, j ).darEstado( ) );

                ataque.marcarCasilla( i, j, Casilla.IMPACTADA );
                assertEquals( "El estado de la casilla no cambi�", Casilla.IMPACTADA, ataque.darCasilla( i, j ).darEstado( ) );

                ataque.marcarCasilla( i, j, Casilla.OCUPADA );
                assertEquals( "El estado de la casilla no cambi�", Casilla.OCUPADA, ataque.darCasilla( i, j ).darEstado( ) );

                ataque.marcarCasilla( i, j, Casilla.VACIA );
                assertEquals( "El estado de la casilla no cambi�", Casilla.VACIA, ataque.darCasilla( i, j ).darEstado( ) );
            }
        }
    }
}
