/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: RegistroJugador.java 588 2006-11-04 15:10:29Z jvillalo2 $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario S�nchez - 23/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.servidor;

import java.text.*;

/**
 * Esta clase mantiene la informaci�n del n�mero de de victorias y derrotas de un jugador<br>
 * <b>inv:</b><br>
 * nombreJugador != null<br>
 * encuentrosGanados >= 0<br>
 * encuentrosPerdidos >= 0<br>
 */
public class RegistroJugador
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nombre del jugador
     */
    private String nombre;

    /**
     * El n�mero de encuentros ganados hasta el momento
     */
    private int ganados;

    /**
     * El n�mero de encuentros perdidos hasta el momento
     */
    private int perdidos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo registro
     * @param nombreP El nombre del jugador - nombre != null
     * @param ganadosP El n�mero de encuentros ganados - ganados >= 0
     * @param perdidosP El n�mero de encuentros perdidos - perdidos >= 0
     */
    public RegistroJugador( String nombreP, int ganadosP, int perdidosP )
    {
        nombre = nombreP;
        ganados = ganadosP;
        perdidos = perdidosP;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del jugador
     * @return nombreJugador
     */
    public String darNombreJugador( )
    {
        return nombre;
    }

    /**
     * Retorna el n�mero de encuentros ganados por el jugador
     * @return encuentrosGanados
     */
    public int darEncuentrosGanados( )
    {
        return ganados;
    }

    /**
     * Retorna el n�mero de encuentros perdidos por el jugador
     * @return encuentrosPerdidos
     */
    public int darEncuentrosPerdidos( )
    {
        return perdidos;
    }

    /**
     * Retorna el porcentaje de efectividad del jugador. <br>
     * Si el jugador no ha terminado ning�n encuentro, la efectividad es 0.
     * @return encuentrosGanados * 100 / encuentrosTotales
     */
    public double darEfectividad( )
    {
        if( ganados + perdidos > 0 )
            return ( ( double )ganados * 100.0 / ( double ) ( ganados + perdidos ) );
        else
            return 0.0;
    }

    /**
     * Retorna una cadena con la informaci�n del registro
     * @return Retorna una cadena de la forma <nombre>: <ganados> ganados / <perdidos> perdidos ( <efectividad>% )
     */
    public String toString( )
    {
        DecimalFormat df = new DecimalFormat( "0.00" );
        String efectividad = df.format( darEfectividad( ) );
        return nombre + ": " + ganados + " ganados / " + perdidos + " perdidos (" + efectividad + "%)";
    }


    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv:</b><br>
     * nombreJugador != null<br>
     * encuentrosGanados >= 0<br>
     * encuentrosPerdidos >= 0<br>
     */
    private void verificarInvariante( )
    {
        assert ( nombre != null ) : "El nombre no puede ser null";
        assert ( ganados >= 0 ) : "El n�mero de encuentros ganados debe ser mayor o igual a 0";
        assert ( perdidos >= 0 ) : "El n�mero de encuentros perdidos debe ser mayor o igual a 0";
    }
}
