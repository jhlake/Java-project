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
 * Comando que escala la tortuga en el tablero.
 */
public class ComandoEscalar extends ComandoTransformacion
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante con el nombre del comando.
     */
    public static final String COMANDO = "escalar";
    
    /**
     * Valor para agrandar la escala de la tortuga.
     */
    public static final int AGRANDAR = 0;
    
    /**
     * Valor para reducir la escala de la tortuga.
     */
    public static final int REDUCIR = 1;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando para escalar la tortuga.<br>
     * <b>post:</b>Se han asignado la nueva escala, direcci�n y el nombre del comando.</br>
     * @param pValor Valor de la escala. pValor >= 1 && pValor <= 3.
     * @param pDireccion Direcci�n de la escala. pDireccion == 0 || pDireccion == 1.
     */
    public ComandoEscalar( double pValor, int pDireccion )
    {
        super( pValor, pDireccion );
        nombre = COMANDO;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Ejecuta el comando definido en la tortuga que viene por par�metro.<br>
     * <b>post:</b> Se ha actualizado el tama�o de la tortuga.
     * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
     * @param pG Tablero de edici�n. pG != null.
     */
    public void ejecutar( Tortuga pTortuga, Graphics2D pG )
    {
        double escala = valor;

        if( direccion == REDUCIR )
        {
            escala = 1 / escala;
        }
        pTortuga.modificarEscala( escala );

    }

}
