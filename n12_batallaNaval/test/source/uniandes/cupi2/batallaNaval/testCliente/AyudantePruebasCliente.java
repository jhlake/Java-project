/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AyudantePruebasCliente.java 636 2006-11-14 04:17:07Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 5/04/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testCliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;

import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta clase es usada por las pruebas de la clase BatallaNaval.<br>
 * Cuando se inicia un Thread con esta clase, esta se encarga de esperar la conexión de un cliente y simular el inicio de un encuentro con este
 */
public class AyudantePruebasCliente extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El socket para recibir las conexiones de los clientes
     */
    private ServerSocket socketServidor;

    /**
     * El socket con la conexión hacia el cliente
     */
    private Socket socketCliente;

    /**
     * Stream usado para enviar información al cliente
     */
    private PrintWriter out;

    /**
     * Stream usado para leer la información enviada por el cliente
     */
    private BufferedReader in;

    /**
     * Es una lista donde se almacenan los mensajes enviados por el cliente
     */
    private LinkedList mensajesCliente;

    /**
     * Indica si el cliente debe tener el primer turno o no
     */
    private boolean primerTurno;

    /**
     * Es la respuesta que se prepara para responder a los ataques del jugador
     */
    private String respuestaPreparada;

    // -----------------------------------------------------------------
    // Cosntructores
    // -----------------------------------------------------------------

    /**
     * Construye el ayudante y espera la conexión del cliente
     * @param tienePrimerTurno Indica si el cliente debe tener el primer turno de juego
     * @throws IOException Se lanza esta excepción si hay problemas creando el socket para aceptar conexiones
     */
    public AyudantePruebasCliente( boolean tienePrimerTurno ) throws IOException
    {
        primerTurno = tienePrimerTurno;
        socketServidor = new ServerSocket( 9999 );
        mensajesCliente = new LinkedList( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * En un Thread separado del principal, esta clase espera la conexión de un cliente y simula el comportamiento del servidor
     */
    public void run( )
    {
        try
        {
            socketCliente = socketServidor.accept( );
            socketServidor.close( );
            out = new PrintWriter( socketCliente.getOutputStream( ), true );
            in = new BufferedReader( new InputStreamReader( socketCliente.getInputStream( ) ) );
            String infoCliente = in.readLine( );
            mensajesCliente.add( infoCliente );

            String nombreJugador = infoCliente.substring( infoCliente.indexOf( ":" ) + 1 );

            out.println( Encuentro.INFO_JUGADOR + ":" + nombreJugador + ":10:5" );
            out.println( Encuentro.INFO_JUGADOR + ":oponente:5:10" );

            if( primerTurno )
            {
                out.println( Encuentro.PRIMER_TURNO );
            }
            else
            {
                out.println( Encuentro.SEGUNDO_TURNO );
            }

            esperarJugada( );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Hace que se espere un mensaje del cliente en un thread separado del principal
     * @throws IOException Se lanza esta excepción si hay problemas en la comunicación
     */
    private void esperarJugada( ) throws IOException
    {
        try
        {
            String jugada = in.readLine( );
            mensajesCliente.add( jugada );
            out.println( respuestaPreparada );
        }
        catch( SocketException se )
        {

        }
    }

    /**
     * Envía una jugada al cliente conectado
     * @param fila Fila de la jugada
     * @param columna Columna de la jugada
     * @throws IOException Se lanza esta excepción si hay problemas en la comunicación
     */
    public void enviarJugada( int fila, int columna ) throws IOException
    {
        out.println( Encuentro.JUGADA + ":" + fila + ":" + columna );
    }

    /**
     * Prepara la respuesta para la jugada del cliente
     * @param impacto Indica si el disparo debe ser respondido como un impacto
     * @param finJuego Indica si el disparo debe ser respondido con una señal de final del juego
     */
    public void enviarRespuesta( boolean impacto, boolean finJuego )
    {
        if( !impacto )
        {
            respuestaPreparada = Encuentro.AGUA;
        }
        else if( impacto && !finJuego )
        {
            respuestaPreparada = Encuentro.IMPACTO + ":fragata:true";
        }
        else
            respuestaPreparada = Encuentro.FIN_JUEGO + "\n" + Encuentro.GANADOR + ":barbarroja";
    }

    /**
     * Retorna los mensajes enviados por el cliente
     * @return mensajesCliente
     */
    public LinkedList darMensajes( )
    {
        return mensajesCliente;
    }

    /**
     * Detiene el ayudante del servidor y cierra la conexión con el cliente
     */
    public void detener( )
    {
        try
        {
            socketCliente.close( );
            socketServidor.close( );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
        this.interrupt( );
    }
}
