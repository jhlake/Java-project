/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiLogo
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.cupiLogo.mundo;

import java.io.PrintWriter;

/**
 * Comando que modifica la tortuga.
 */
public abstract class ComandoTransformacion extends Comando
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Valor de la transformaci�n.
     */
    protected double valor;

    /**
     * Direccion de la transformaci�n.
     */
    protected int direccion;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando transformaci�n.<br>
     * <b>post:</b> Se ha asignado el valor y direcci�n del comando.
     * @param pValor Valor de la transformaci�n. pValor > 0.
     * @param pDireccion Direcci�n de la transformaci�n. pDireccion == 0 || pDireccion == 1.
     */
    public ComandoTransformacion( double pValor, int pDireccion )
    {
        valor = pValor;
        direccion = pDireccion;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Guarda el comando a trav�s del flujo de escritura que llega por par�metro.<br>
     * <b>post:</b> Se ha guardado el nombre, valor y direcci�n del comando separado por espacios.
     * @param pPw Flujo de escritura del archivo. pPw != null.
     */
    public void guardar( PrintWriter pPw )
    {
        pPw.println( nombre + " " + valor + " " + direccion );
    }
}
