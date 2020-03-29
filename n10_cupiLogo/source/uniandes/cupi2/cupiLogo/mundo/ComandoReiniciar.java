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

/**
 * Comando que reinicia el tablero de edici�n y ubica a la tortuga en el centro del tablero con orientaci�n 0.
 */
public class ComandoReiniciar extends ComandoSimple
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante con el nombre del comando.
     */
    public static final String COMANDO = "reiniciar";

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando para reiniciar el tablero.</br>
     * <b>post:</b>El nombre del comando se ha asignado.
     */
    public ComandoReiniciar( )
    {
        nombre = COMANDO;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Ejecuta el comando definido en la tortuga que viene por par�metro.<br>
     * <b>post:</b>El tablero no tiene nada pintado y la tortuga est� en el centro con orientaci�n 0.
     * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
     * @param pG Tablero de edici�n. pG != null.
     */
    public void ejecutar( Tortuga pTortuga, Graphics2D pG )
    {
        int ancho = pTortuga.darXInicial( )*2;
        int alto = pTortuga.darYInicial( )*2;
        
        pG.clearRect( 0, 0, ancho, alto );

        pTortuga.reiniciarTortuga( );
    }
}
