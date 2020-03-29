/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BatallaNavalTest.java 650 2006-11-16 00:59:12Z da-romer $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 21-feb-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testCliente;

import java.io.IOException;
import java.util.LinkedList;

import junit.framework.TestCase;
import uniandes.cupi2.batallaNaval.cliente.*;
import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase BatallaNaval est�n correctamente implementados
 */
public class BatallaNavalTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el cliente sobre el que se har�n las pruebas
     */
    private Jugador batallaNaval;

    /**
     * Es el objeto usado para simular un servidor de BatallaNaval.
     */
    private AyudantePruebasCliente ayudante;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo cliente de Batalla Naval sin conexi�n
     */
    private void setupEscenario1( )
    {
        batallaNaval = new Jugador( );
    }

    /**
     * Construye un nuevo cliente de Batalla Naval y lo conecta a una versi�n del servidor usada solamente para las pruebas.<br>
     * El cliente tendr� el primer turno de juego
     */
    private void setupEscenario2( )
    {
        try
        {
            ayudante = new AyudantePruebasCliente( true );
            ayudante.start( );

            batallaNaval = new Jugador( );
            batallaNaval.conectar( "barbarroja", "localhost", 9999 );
        }
        catch( BatallaNavalException e )
        {
            fail( "Hubo problemas conectando al servidor de pruebas: " + e.getMessage( ) );
            e.printStackTrace( );
        }
        catch( IOException e )
        {
            fail( "Hubo problemas creando el ayudante: " + e.getMessage( ) );
        }
    }

    /**
     * Construye un nuevo cliente de Batalla Naval y lo conecta a una versi�n del servidor usada solamente para las pruebas.<br>
     * El cliente tendr� el segundo turno de juego
     */
    private void setupEscenario3( )
    {
        try
        {
            ayudante = new AyudantePruebasCliente( false );
            ayudante.start( );

            batallaNaval = new Jugador( );
            batallaNaval.conectar( "barbarroja", "localhost", 9999 );
        }
        catch( BatallaNavalException e )
        {
            fail( "Hubo problemas conectando al servidor de pruebas: " + e.getMessage( ) );
            e.printStackTrace( );
        }
        catch( IOException e )
        {
            fail( "Hubo problemas creando el ayudante: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica el estado del juego al crear el cliente. <br>
     * <b> M�todos a probar: </b> <br>
     * Jugador, darEstadoJuego. <br>
     * <b> Objetivo: </b> Probar que el m�todo Jugador() cree el juego en el estado correcto. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un juego, su estado debe ser SIN_CONECTAR. <br>
     */
    public void testBatallaNaval( )
    {
        setupEscenario1( );

        assertEquals( "El estado del juego no es el esperado", Jugador.SIN_CONECTAR, batallaNaval.darEstadoJuego( ) );
    }

    /**
     * Verifica el m�todo conectar.<br>
     * Esta prueba establece una conexi�n a un servidor de prueba y verifica que la conexi�n se haya podido establecer y que el encuentro se haya iniciado correctamente.<br>
     * El cliente deber�a tener el primer turno de juego. <br>     
     * <b> Objetivo: </b> Verificar que los datos del cliente, cuando se inicia una conexi�n con el servidor, sean correctos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al iniciar la conexi�n con el servidor, el cliente s�lo debe habar enviado un mensaje con el formato <Encuentro.JUGADOR>:<nombre>. <br>
     * 2. Al iniciar la conexi�n con el servidor, los datos del cliente (direcci�n servidor, nombre jugador y puerto servidor) 
     *    deben corresponder a la informaci�n ingresada por el usuario. <br>
     * 3. Al iniciar la conexi�n con el servidor y la partida, el estado del cliente debe ser Jugador.ESPERANDO_LOCAL y <br> 
     *    el nombre del ganador debe ser "".<br>  
     * 4. Al iniciar la conexi�n con el servidor, el tablero del cliente deber�a tener los barcos. <br>
     * 5. Al iniciar la conexi�n con el servidor, el tablero del oponente deber�a estar vac�o. 
     */
    public void testConectar( )
    {
        setupEscenario2( );

        // Verificar los mensajes enviados por el cliente
        LinkedList mensajes = ayudante.darMensajes( );
        assertEquals( "El cliente deber�a haber mandado s�lo un mensaje", 1, mensajes.size( ) );
        String mensaje = ( String )mensajes.get( 0 );
        assertEquals( "El mensaje enviado no cumple el protocolo del juego", Encuentro.JUGADOR + ":barbarroja", mensaje );

        // Verificar los atributos del cliente
        assertEquals( "El estado del juego no es el esperado", Jugador.ESPERANDO_LOCAL, batallaNaval.darEstadoJuego( ) );
        assertEquals( "La direcci�n del servidor no es la correcta", "localhost", batallaNaval.darDireccionServidor( ) );
        assertEquals( "No deber�a haber un ganador en este momento", "", batallaNaval.darNombreGanador( ) );
        assertEquals( "El nombre del jugador no es el correcto", "barbarroja", batallaNaval.darNombreJugador( ) );
        assertEquals( "El puerto del servidor no es el correcto", 9999, batallaNaval.darPuertoServidor( ) );

        // Verificar los tableros:
        // - El tablero del oponente deber�a estar vac�o
        // - El tablero del jugador deber�a tener solamente los barcos iniciales
        int casillasBarcos = 0;
        Tablero tableros = batallaNaval.darTableroJuego( );
        Tablero ataque = batallaNaval.darTableroAtaque();
        for( int i = 0; i < 9; i++ )
        {
            for( int j = 0; j < 9; j++ )
            {
                Casilla casillaJugador = tableros.darCasilla( i, j );
                int estadoCasillaJugador = casillaJugador.darEstado( );
                boolean estadoCorrecto = estadoCasillaJugador == Casilla.VACIA || estadoCasillaJugador == Casilla.OCUPADA;
                assertTrue( "La casilla del jugador est� en un estado incorrecto: " + estadoCasillaJugador, estadoCorrecto );

                if( estadoCasillaJugador == Casilla.OCUPADA )
                    casillasBarcos++;

                Casilla casillaOponente = ataque.darCasilla( i, j );
                assertEquals( "La casilla del oponente est� en un estado incorrecto", Casilla.VACIA, casillaOponente.darEstado( ) );
            }
        }

        assertEquals( "El n�mero de barcos es incorrecto", 17, casillasBarcos );

        ayudante.detener( );
    }

    /**
     * Verifica el m�todo conectar nuevamente, pero el cliente deber�a estar esperando un oponente. <br>
     * <b> Objetivo: </b> Verificar que el estado del juego sea el correcto, despu�s de iniciar la conexi�n y no <br>
     * haber encontrado un oponente. <br> 
     * 1. Al iniciar la conexi�n con el servidor y no encontrar oponente, el estado del cliente debe ser Jugador.ESPERANDO_OPONENTE. 
     */
    public void testConectar2( )
    {
        setupEscenario3( );

        assertEquals( "El estado del juego no es el esperado", Jugador.ESPERANDO_OPONENTE, batallaNaval.darEstadoJuego( ) );

        ayudante.detener( );
    }

    /**
     * Verifica el m�todo enviarJugada.<br>
     * El disparo realizado deber�a dar en uno de los barcos del oponente.<br>
     * <b> Objetivo: </b> Verificar que al realizar un disparo se obtenga el resultado correcto. <br> 
     * 1. Al realizar un disparo, el estado del cliente debe quedar en Jugador.ESPERANDO_OPONENTE. <br> 
     * 2. Al realizar un disparo, se deben haber enviado dos mensajes: uno con el disparo y otro con el resultado del disparo. <br>
     * 3. Al realizar un disparo, el mensaje enviado por el usuario debe tener el formato <Encuentro.JUGADA>:<x>:<y>. <br>
     * 4. Al realizar un disparo, que se sabe que da en el blanco, el estado de la casilla sobre <br>
     *    el que se hizo el disparo debe ser Casilla.IMPACTADA. 
     */
    public void testEnviarJugada1( )
    {
        setupEscenario2( );

        try
        {
            // Hacer que el servidor simule una respuesta del oponente
            ayudante.enviarRespuesta( true, false );

            // Enviar la jugada y revisar el estado
            batallaNaval.enviarJugada( 4, 5 );
            assertEquals( "El estado del juego no es el esperado", Jugador.ESPERANDO_OPONENTE, batallaNaval.darEstadoJuego( ) );

            // Revisar el mensaje enviado
            LinkedList mensajes = ayudante.darMensajes( );
            assertEquals( "El cliente deber�a haber mandado s�lo dos mensajes", 2, mensajes.size( ) );
            String mensaje = ( String )mensajes.get( 1 );
            assertEquals( "El mensaje enviado no cumple el protocolo del juego", Encuentro.JUGADA + ":4:5", mensaje );

            // Verificar la casilla del tablero del oponente
            Tablero ataque = batallaNaval.darTableroAtaque( );
            Casilla casillaOponente = ataque.darCasilla( 4, 5 );
            assertEquals( "La casilla del oponente est� en un estado incorrecto", Casilla.IMPACTADA, casillaOponente.darEstado( ) );
        }
        catch( BatallaNavalException e )
        {
            fail( "No deber�a haber problemas realizando la jugada" );
        }

        ayudante.detener( );
    }

    /**
     * Verifica el m�todo enviarJugada.<br>
     * El disparo realizado deber�a caer al agua. <br>
     * <b> Objetivo: </b> Verificar que al realizar un disparo se obtenga el resultado correcto. <br> 
     * 1. Al realizar un disparo, el estado del cliente debe quedar en Jugador.ESPERANDO_OPONENTE. <br> 
     * 2. Al realizar un disparo, se deben haber enviado dos mensajes: uno con el disparo y otro con el resultado del disparo. <br>
     * 3. Al realizar un disparo, el mensaje enviado por el usuario debe tener el formato <Encuentro.JUGADA>:<x>:<y>. <br>
     * 4. Al realizar un disparo, que se sabe que cae al agua, el estado de la casilla sobre <br>
     *    el que se hizo el disparo debe ser Casilla.ATACADA.
     */
    public void testEnviarJugada2( )
    {
        setupEscenario2( );

        try
        {
            // Hacer que el servidor simule una respuesta del oponente
            ayudante.enviarRespuesta( false, false );

            // Enviar la jugada y revisar el estado
            batallaNaval.enviarJugada( 4, 5 );
            assertEquals( "El estado del juego no es el esperado", Jugador.ESPERANDO_OPONENTE, batallaNaval.darEstadoJuego( ) );

            // Revisar el mensaje enviado
            LinkedList mensajes = ayudante.darMensajes( );
            assertEquals( "El cliente deber�a haber mandado s�lo dos mensajes", 2, mensajes.size( ) );
            String mensaje = ( String )mensajes.get( 1 );
            assertEquals( "El mensaje enviado no cumple el protocolo del juego", Encuentro.JUGADA + ":4:5", mensaje );

            // Verificar la casilla del tablero del oponente
            Tablero ataque = batallaNaval.darTableroAtaque( );
            Casilla casillaOponente = ataque.darCasilla( 4, 5 );
            assertEquals( "La casilla del oponente est� en un estado incorrecto", Casilla.ATACADA, casillaOponente.darEstado( ) );
        }
        catch( BatallaNavalException e )
        {
            fail( "No deber�a haber problemas realizando la jugada" );
        }

        ayudante.detener( );
    }

    /**
     * Verifica el m�todo enviarJugada.<br>
     * El disparo realizado deber�a haber hundido el �ltimo barco oponente.<br>
     * <b> Objetivo: </b> Verificar que al realizar un disparo se obtenga el resultado correcto. <br> 
     * 1. Al realizar un disparo, el estado del cliente debe quedar en Jugador.ESPERANDO_OPONENTE. <br> 
     * 2. Al realizar un disparo, se deben haber enviado dos mensajes: uno con el disparo y otro con el resultado del disparo. <br>
     * 3. Al realizar un disparo, el mensaje enviado por el usuario debe tener el formato <Encuentro.JUGADA>:<x>:<y>. <br>
     * 4. Al terminar el juego, el nombre del ganador debe ser correcto y el estado del cliente debe ser Jugador.SIN_CONECTAR.
     */
    public void testEnviarJugada3( )
    {
        setupEscenario2( );

        try
        {
            // Hacer que el servidor simule una respuesta del oponente
            ayudante.enviarRespuesta( true, true );

            // Enviar la jugada y revisar el estado
            batallaNaval.enviarJugada( 4, 5 );
            assertEquals( "El estado del juego no es el esperado", Jugador.ESPERANDO_OPONENTE, batallaNaval.darEstadoJuego( ) );

            // Revisar el mensaje enviado
            LinkedList mensajes = ayudante.darMensajes( );
            assertEquals( "El cliente deber�a haber mandado s�lo dos mensajes", 2, mensajes.size( ) );
            String mensaje = ( String )mensajes.get( 1 );
            assertEquals( "El mensaje enviado no cumple el protocolo del juego", Encuentro.JUGADA + ":4:5", mensaje );

            // Terminar el juego
            batallaNaval.terminarEncuentro( );

            assertEquals( "El nombre del ganador es incorrecto", "barbarroja", batallaNaval.darNombreGanador( ) );
            assertEquals( "El estado del juego no es el esperado", Jugador.SIN_CONECTAR, batallaNaval.darEstadoJuego( ) );

        }
        catch( BatallaNavalException e )
        {
            fail( "No deber�a haber problemas realizando la jugada" );
        }

        ayudante.detener( );
    }

    /**
     * Verifica el m�todo esperarJugada.<br>
     * El disparo del oponente deber�a dar en uno de los barcos de este cliente. <br>
     * <b> Objetivo: </b> Verificar que al realizar un disparo se obtenga el resultado correcto. <br> 
     * 1. Al estar esperando una jugada, el estado del cliente debe quedar en Jugador.ESPERANDO_LOCAL. <br> 
     * 2. Al recibir un disparo, se deben haber enviado dos mensajes: uno con el disparo y otro con el resultado del disparo. <br>
     * 3. Al realizar un disparo, el mensaje enviado por el cliente que recibe el disparo (y que di� en el blanco) debe tener el formato <Encuentro.IMPACTO>:. <br>
     * 4. Al realizar un disparo, que se sabe que dio en el blanco, el estado de la casilla sobre <br>
     *    el que se hizo el disparo debe ser Casilla.IMPACTADA.
     */
    public void testEsperarJugada1( )
    {
        setupEscenario3( );

        try
        {
            // Hacer que el servidor simule un disparo del oponente
            int[] casillaBarco = buscarBarco( );
            ayudante.enviarJugada( casillaBarco[ 0 ], casillaBarco[ 1 ] );

            // Esperar una jugada y revisar el estado
            batallaNaval.esperarJugada( );
            assertEquals( "El estado del juego no es el esperado", Jugador.ESPERANDO_LOCAL, batallaNaval.darEstadoJuego( ) );
            Thread.sleep( 100 );

            // Revisar el mensaje enviado
            LinkedList mensajes = ayudante.darMensajes( );
            assertEquals( "El cliente deber�a haber mandado s�lo dos mensajes", 2, mensajes.size( ) );
            String mensaje = ( String )mensajes.get( 1 );
            assertTrue( "El mensaje enviado no cumple el protocolo del juego", mensaje.startsWith( Encuentro.IMPACTO + ":" ) );

            // Verificar la casilla del tablero del jugador
            Tablero tableros = batallaNaval.darTableroJuego( );
            Casilla casillaJugador = tableros.darCasilla( casillaBarco[ 0 ], casillaBarco[ 1 ] );
            assertEquals( "La casilla del jugador est� en un estado incorrecto", Casilla.IMPACTADA, casillaJugador.darEstado( ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a haber problemas realizando la jugada: " + e.getMessage( ) );
        }

        ayudante.detener( );
    }

    /**
     * Verifica el m�todo esperarJugada.<br>
     * El disparo del oponente no deber�a dar en ninguno de los barcos de este cliente. <br>
     * <b> Objetivo: </b> Verificar que al realizar un disparo se obtenga el resultado correcto. <br> 
     * 1. Al estar esperando una jugada, el estado del cliente debe quedar en Jugador.ESPERANDO_LOCAL. <br> 
     * 2. Al recibir un disparo, se deben haber enviado dos mensajes: uno con el disparo y otro con el resultado del disparo. <br>
     * 3. Al realizar un disparo, el mensaje enviado por el cliente que recibe el disparo (y que no dio en el blanco) debe tener el formato <Encuentro.AGUA>:. <br>
     * 4. Al realizar un disparo, que se sabe que dio en el agua, el estado de la casilla sobre <br>
     *    el que se hizo el disparo debe ser Casilla.ATACADA.
     */
    public void testEsperarJugada2( )
    {
        setupEscenario3( );

        try
        {
            // Hacer que el servidor simule un disparo del oponente
            int[] casillaSinBarco = buscarCasillaVacia( );
            ayudante.enviarJugada( casillaSinBarco[ 0 ], casillaSinBarco[ 1 ] );

            // Esperar una jugada y revisar el estado
            batallaNaval.esperarJugada( );
            assertEquals( "El estado del juego no es el esperado", Jugador.ESPERANDO_LOCAL, batallaNaval.darEstadoJuego( ) );
            Thread.sleep( 100 );

            // Revisar el mensaje enviado
            LinkedList mensajes = ayudante.darMensajes( );
            assertEquals( "El cliente deber�a haber mandado s�lo dos mensajes", 2, mensajes.size( ) );
            String mensaje = ( String )mensajes.get( 1 );
            assertTrue( "El mensaje enviado no cumple el protocolo del juego", mensaje.startsWith( Encuentro.AGUA ) );

            // Verificar la casilla del tablero del jugador
            Tablero tableros = batallaNaval.darTableroJuego( );
            Casilla casillaJugador = tableros.darCasilla( casillaSinBarco[ 0 ], casillaSinBarco[ 1 ] );
            assertEquals( "La casilla del jugador est� en un estado incorrecto", Casilla.ATACADA, casillaJugador.darEstado( ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a haber problemas realizando la jugada: " + e.getMessage( ) );
        }

        ayudante.detener( );
    }

    /**
     * Busca una casilla vac�a en el tablero del jugador local
     * @return Retorna una pareja de enteros <fila,columna> que indican una casilla vac�a
     */
    private int[] buscarCasillaVacia( )
    {
        int[] casillaSinBarco = null;

        Tablero tableros = batallaNaval.darTableroJuego( );
        for( int i = 0; i < 9 && casillaSinBarco == null; i++ )
        {
            for( int j = 0; j < 9 && casillaSinBarco == null; j++ )
            {
                Casilla casillaJugador = tableros.darCasilla( i, j );
                int estadoCasillaJugador = casillaJugador.darEstado( );

                if( estadoCasillaJugador == Casilla.VACIA )
                {
                    casillaSinBarco = new int[]{ i, j };
                }
            }
        }

        return casillaSinBarco;
    }

    /**
     * Busca una casilla ocupada en el tablero del jugador local
     * @return Retorna una pareja de enteros <fila,columna> que indican una casilla ocupada
     */
    private int[] buscarBarco( )
    {
        int[] casillaBarco = null;

        Tablero tableros = batallaNaval.darTableroJuego( );
        for( int i = 0; i < 9 && casillaBarco == null; i++ )
        {
            for( int j = 0; j < 9 && casillaBarco == null; j++ )
            {
                Casilla casillaJugador = tableros.darCasilla( i, j );
                int estadoCasillaJugador = casillaJugador.darEstado( );

                if( estadoCasillaJugador == Casilla.OCUPADA )
                {
                    casillaBarco = new int[]{ i, j };
                }
            }
        }

        return casillaBarco;
    }
}