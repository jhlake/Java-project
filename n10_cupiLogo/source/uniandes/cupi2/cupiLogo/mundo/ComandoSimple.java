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
 * Representa un comando simple.
 */
public abstract class ComandoSimple extends Comando
{

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un comando simple.
     */
    public ComandoSimple( )
    {
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Guarda el comando a trav�s del flujo de escritura que llega por par�metro.<br>
     * <b>post:</b> Se ha guardado el nombre del comando.
     * @param pPw Flujo de escritura del archivo. pPw != null.
     */
    public void guardar( PrintWriter pPw )
    {
        pPw.println( nombre );
    }
}
