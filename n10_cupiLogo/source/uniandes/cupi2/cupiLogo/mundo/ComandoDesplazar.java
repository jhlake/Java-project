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
 * Comando que desplaza la tortuga en el tablero.
 */
public class ComandoDesplazar extends ComandoTransformacion
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante con el nombre del comando.
     */
    public static final String COMANDO = "desplazar";
    
    /**
     * Valor para el desplazamiento hacia adelante.
     */
    public static final int ADELANTE = 0;
    
    /**
     * Valor para el desplazamiento hacia atr�s.
     */
    public static final int ATRAS = 1;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el comando desplazar.</br>
     * <b>post:</b>Se ha asignado la distancia a desplazar, direcci�n y el nombre del comando.
     * @param pValor Distancia del desplazamiento la tortuga. pValor > 0.
     * @param pDireccion Direcci�n del desplazamiento. pDireccion == ADELANTE || pDireccion == ATRAS.
     */
    public ComandoDesplazar( double pValor, int pDireccion )
    {
        super( pValor, pDireccion );
        nombre = COMANDO;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Ejecuta el comando definido en el tablero que viene por par�metro.<br>
     * <b>post:</b> Se ha actualizado la posici�n de la tortuga.
     * @param pTortuga Tortuga sobre la cual se ejecutan los comandos. pTortuga != null.
     * @param pG Tablero de edici�n. pG != null.
     */
    public void ejecutar( Tortuga pTortuga, Graphics2D pG )
    {
        // TODO Parte 2 Punto A: implemente el m�todo seg�n la documentaci�n dada.
    	double radians = (Math.PI * pTortuga.darOrientacion())/180;
    	int xNew = 0; int yNew = 0;
    	switch(direccion)
    	{
    	case ADELANTE:
    		xNew = (pTortuga.darXActual() + (int)(Math.cos(radians)*valor));
    		yNew = (pTortuga.darYActual() + (int)(Math.cos(radians)*valor));
    		break;
    	case ATRAS:
    		xNew = (pTortuga.darXActual() - (int)(Math.cos(radians)*valor));
    		yNew = (pTortuga.darYActual() - (int)(Math.cos(radians)*valor));
    		break;
    	}
    	if(pTortuga.estaPintando())
    		pG.drawLine(pTortuga.darXActual(), pTortuga.darYActual(), xNew, yNew);
    	pTortuga.modificarXActual(xNew);
    	pTortuga.modificarYActual(yNew);
    }

}
