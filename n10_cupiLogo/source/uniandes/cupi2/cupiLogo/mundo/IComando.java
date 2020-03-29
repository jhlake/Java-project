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

import java.awt.Graphics2D;
import java.io.PrintWriter;

/**
 * Interfaz que representa las instrucciones b�sicas que debe tener un comando.
 */
public interface IComando
{

    /**
     * Retorna el nombre del comando.
     * @return Nombre del comando.
     */
    public String darNombre( );

    /**
     * Ejecuta el comando definido en el tablero que viene por par�metro.
     * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
     * @param pG Tablero de edici�n. pG != null.
     */
    public void ejecutar( Tortuga pTortuga, Graphics2D pG );

    /**
     * Guarda el comando en un archivo que llega por par�metro.
     * @param pPw Flujo de escritura del archivo. pPw != null.
     */
    public void guardar( PrintWriter pPw );
}
