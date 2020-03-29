/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Barco.java 622 2006-11-09 23:37:02Z da-romer $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 24/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.cliente;

import java.awt.*;

/**
 * Esta clase representa un barco que se encuentra en el tablero<br>
 * <b>inv:</b><br>
 * nombreBarco != null<br>
 * puntosRestantes >= 0<br>
 * colorBarco != null
 */
public class Barco
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el tipo de barco
     */
    private String tipoBarco;

    /**
     * Es el n�mero de impactos que hacen falta para hundir el barco
     */
    private int puntosRestantes;

    /**
     * El color que debe ser usado para pintar el barco
     */
    private Color colorBarco;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo barco con los par�metros indicados
     * @param tipo El tipo del barco - tipo != null
     * @param puntos El n�mero de impactos requeridos para hundir el barco - puntos > 0
     * @param color El color que ser� usado para pintar el barco - color != null
     */
    public Barco( String tipo, int puntos, Color color )
    {
        tipoBarco = tipo;
        puntosRestantes = puntos;
        colorBarco = color;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el tipo del barco
     * @return El tipo del barco
     */
    public String darTipoBarco( )
    {
        return tipoBarco;
    }

    /**
     * Disminuye el n�mero de puntos que le quedan al barco<br>
     * <b>pre:</b>puntosRestantes > 0
     */
    public void atacar( )
    {
        puntosRestantes--;
        verificarInvariante( );
    }

    /**
     * Retorna el n�mero de puntos que tiene el barco
     * @return El n�mero de puntos restantes
     */
    public int darPuntos( )
    {
        return puntosRestantes;
    }

    /**
     * Indica si el barco fue hundido
     * @return Retorna true si al barco no le quedan puntos de impacto o false en caso contrario
     */
    public boolean estaHundido( )
    {
        return puntosRestantes == 0;
    }

    /**
     * Retorna el color que debe ser usado para pintar el barco
     * @return El color que debe ser usado para pintar el barco
     */
    public Color darColor( )
    {
        return colorBarco;
    }
    
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b><br>
     * tipoBarco != null<br>
     * puntosRestantes >= 0<br>
     * colorBarco != null
     */
    private void verificarInvariante( )
    {
        assert ( tipoBarco != null ) : "El tipo del barco no puede ser null";
        assert ( puntosRestantes >= 0 ) : "Los puntos restantes no pueden ser inferiores a 0";
        assert ( colorBarco != null ) : "El color del barco no puede ser null";
    }
}
