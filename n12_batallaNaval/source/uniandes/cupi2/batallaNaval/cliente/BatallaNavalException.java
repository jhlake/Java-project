/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BatallaNavalException.java 588 2006-11-04 15:10:29Z jvillalo2 $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 23/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.cliente;

/**
 * Esta es una excepci�n que se lanza para indicar un problema en el juego
 */
public class BatallaNavalException extends Exception
{
    /**
     * Construye una nueva excepci�n de este tipo con el mensaje indicado
     * @param mensaje El mensaje que describe la causa de la excepci�n - mensaje != null
     */
    public BatallaNavalException( String mensaje )
    {
        super( mensaje );
    }
}
