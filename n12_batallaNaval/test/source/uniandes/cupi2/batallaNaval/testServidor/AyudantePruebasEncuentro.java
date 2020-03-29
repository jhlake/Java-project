/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AyudantePruebasEncuentro.java 590 2006-11-04 22:15:59Z jvillalo2 $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 7/05/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Esta clase es usada para poder esperar una conexi�n a un socket local en un thread diferente al principal
 */
public class AyudantePruebasEncuentro extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el socket con la conexi�n que se establece
     */
    private Socket socket;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Crea un socket que espera una conexi�n
     */
    public void run( )
    {
        try
        {
            ServerSocket ssocket = new ServerSocket( 8888 );
            socket = ssocket.accept( );
            ssocket.close( );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Retorna el socket conectado
     * @return socket
     */
    public Socket darSocket( )
    {
        return socket;
    }

}
