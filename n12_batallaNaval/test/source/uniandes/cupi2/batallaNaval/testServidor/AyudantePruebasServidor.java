/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AyudantePruebasServidor.java 651 2006-11-16 15:06:50Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 5/04/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testServidor;

import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta clase es usada por las pruebas de la clase ServidorBatallaNaval.<br>
 * Cuando se inicia un Thread con esta clase, esta se encarga de hacer que el servidor quede listo para recibir conexiones.
 */
public class AyudantePruebasServidor extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el servidor sobre el que se realizan las pruebas
     */
    private BatallaNaval servidor;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el ayudante para las pruebas
     * @param servidorBN El servidor que será probado
     */
    public AyudantePruebasServidor( BatallaNaval servidorBN )
    {
        servidor = servidorBN;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia al servidor dejándolo listo para recibir conexiones
     */
    public void run( )
    {
        while( true )
        {
            servidor.recibirConexiones( );
        }
    }

    /**
     * Detiene el servidor
     */
    public void detener( )
    {
        interrupt( );
    }
}
