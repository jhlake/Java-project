/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazJugador.java 602 2006-11-06 17:16:51Z jvillalo2 $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 21-feb-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.interfazCliente;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.batallaNaval.cliente.*;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazJugador extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Jugador batallaNaval;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es la barra con el men� de opciones del juego
     */
    private BarraMenu barraMenu;

    /**
     * Es el panel donde se muestran los mensajes del juego
     */
    private PanelMensajes panelMensajes;

    /**
     * Es el panel donde se muestran los tableros para jugar
     */
    private PanelTableros panelTableros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz para un cliente del juego
     */
    public InterfazJugador( )
    {
        batallaNaval = new Jugador( );
        inicializarVentana( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa los componentes de la interfaz
     */
    private void inicializarVentana( )
    {
        // Construye la forma
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 819, 612 );
        setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Batalla Naval: desconectado...." );

        // Inicializar el men� de opciones
        barraMenu = new BarraMenu( this );
        setJMenuBar( barraMenu );

        // Construir los paneles

        panelTableros = new PanelTableros( this );
        getContentPane( ).add( panelTableros, BorderLayout.CENTER );

        panelMensajes = new PanelMensajes( );
        getContentPane( ).add( panelMensajes, BorderLayout.SOUTH );
    }

    /**
     * Este m�todo se encarga de mostrar un di�logo para pedir la informaci�n necesaria para iniciar un juego nuevo de Batalla Naval.
     */
    public void iniciarConexion( )
    {
        DialogoConectar dialogo = new DialogoConectar( this, batallaNaval.darNombreJugador( ), batallaNaval.darDireccionServidor( ), batallaNaval.darPuertoServidor( ) );
        dialogo.setVisible( true );
    }

    /**
     * Intenta realizar una conexi�n.<br>
     * El proceso de conexi�n al servidor se hace en un hilo aparte usando la clase ThreadConectar.
     * @param dialogo Es el di�logo que fue usado para recibir los datos
     * @param nombre El nombre del jugador
     * @param direccion Direcci�n donde se encuentra el servidor
     * @param puerto Puerto usado para la conexi�n
     */
    public void conectar( DialogoConectar dialogo, String nombre, String direccion, int puerto )
    {
        dialogo.dispose( );
        Thread t = new ThreadConectar( batallaNaval, this, nombre, direccion, puerto );
        t.start( );
    }

    /**
     * Este m�todo se usa cuando se debe esperar la jugada del oponente. <br>
     * Despu�s de que este mensaje sea recibido y respondido se debe actualizar la lista de mensajes y la representaci�n de los tableros. Adem�s se debe verificar si la
     * partida termin� y en caso afirmativo consultar el ganador.<br>
     * Las tareas relacionadas con esperar una jugada del oponente se hacen en un hilo aparte que no bloquea la aplicaci�n usando la clase ThreadEsperarJugada.
     */
    public void esperarJugada( )
    {
        if( batallaNaval.darEstadoJuego( ) == Jugador.ESPERANDO_OPONENTE )
        {
            Thread t = new ThreadEsperarJugada( batallaNaval, this );
            t.start( );
        }
    }

    /**
     * Este m�todo se encarga de realizar una jugada. <br>
     * Despu�s de que se env�a el ataque se debe verificar si el juego termin�, y en caso negativo se debe esperar la jugada del oponente<br>
     * Las tareas relacionadas con enviar una jugada y esperar la respuesta del oponente se hacen en un hilo aparte que no bloquea la aplicaci�n usando la clase
     * ThreadEnviarJugada.
     * @param fila La fila donde se va a hacer el ataque
     * @param columna La columna donde se va a hacer el ataque
     */
    public void jugar( int fila, int columna )
    {
        if( batallaNaval.darEstadoJuego( ) == Jugador.ESPERANDO_LOCAL )
        {
            Thread t = new ThreadEnviarJugada( batallaNaval, this, fila, columna );
            t.start( );
        }
    }

    /**
     * Este m�todo se encarga de consultar cual fue el jugador que gan� el encuentro y avisarle al usuario
     */
    public void mostrarGanador( )
    {
        JOptionPane.showMessageDialog( this, "El ganador del encuentro fue " + batallaNaval.darNombreGanador( ).toUpperCase( ), "Fin del Juego", JOptionPane.INFORMATION_MESSAGE );
        panelTableros.reinicializarTablero( );
        validate( );
    }

    /**
     * Actualiza la interfaz del usuario: <br>
     * a) actualiza el estado de los tableros mostrados <br>
     * b) actualiza el t�tulo de la ventana para que indique el estado actual
     */
    public void actualizarInterfaz( )
    {
        panelMensajes.agregarMensajes( batallaNaval.darMensajesSinLeer( ) );
        panelTableros.actualizarTableros( batallaNaval.darTableroJuego( ), batallaNaval.darTableroAtaque( ) );

        if( batallaNaval.darEstadoJuego( ) == Jugador.SIN_CONECTAR )
            setTitle( "Batalla Naval: sin conexi�n" );
        else if( batallaNaval.darEstadoJuego( ) == Jugador.ESPERANDO_LOCAL )
            setTitle( "Batalla Naval: jugando" );
        else if( batallaNaval.darEstadoJuego( ) == Jugador.ESPERANDO_OPONENTE )
            setTitle( "Batalla Naval: esperando jugada" );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = batallaNaval.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = batallaNaval.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Par�metros de ejecuci�n que no son usados
     */
    public static void main( String[] args )
    {

        InterfazJugador interfaz = new InterfazJugador( );
        interfaz.setVisible( true );
    }

}