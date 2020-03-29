/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DecoradorServidorBatallaNaval.java 651 2006-11-16 15:06:50Z da-romer $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 
 * Ejercicio: n12_batallaNaval 
 * Autor: Mario S�nchez - 5/04/2006 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.batallaNaval.testServidor;

import java.io.*;
import java.net.*;
import java.sql.*;

import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta clase sirve para enriquecer el comportamiento de la clase ServidorBatallaNaval para as� facilitar la construcci�n de las pruebas autom�ticas.
 */
public class DecoradorServidorBatallaNaval extends BatallaNaval
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Indica si ya se inici� un encuentro
     */
    private boolean encuentroIniciado;

    /**
     * El tiempo m�ximo (en milisegundos) que se va a esperar para que se cumplan las diferentes fases de la inicializaci�n de un encuentro
     */
    private long timeout;

    /**
     * En caso de falla, ac� se almacena un mensaje que explica el error
     */
    private String mensajeFalla;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del decorador
     * @param archivo El archivo de configuraci�n del servidor 
     * @param tiempoTimeout Tiempo de espera de las distintas fases de inicializaci�n
     * @throws Exception Se lanza esta excepci�n si hay problemas con el archivo de propiedades o hay problemas en la conexi�n a la base de datos
     * @throws SQLException Se lanza esta excepci�n si hay problemas conectando el almac�n a la base de datos.
     */
    public DecoradorServidorBatallaNaval( String archivo, long tiempoTimeout ) throws SQLException, Exception
    {
        super( archivo );

        encuentroIniciado = false;
        timeout = tiempoTimeout;
        mensajeFalla = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia un encuentro usando una instancia de la clase DecoradorEncuentro. <br>
     * Despu�s de que se intenta iniciar el encuentro, se espera hasta que el encuentro est� iniciado efectivamente, o hasta que haya un timeout. <br>
     * Si el timeout se cumple, entonces en el atributo falla queda un mensaje que explica el error.
     * @param nuevoEncuentro El nuevo encuentro a ser iniciado 
     */
    protected void iniciarEncuentro( Encuentro nuevoEncuentro )
    {
        try
        {
            Socket sJugador1 = nuevoEncuentro.darSocketJugador1( );
            Socket sJugador2 = nuevoEncuentro.darSocketJugador2( );
            AdministradorResultados adminResultados = nuevoEncuentro.darAdministradorResultados( );

            DecoradorEncuentro encuentroDecorado = new DecoradorEncuentro( sJugador1, sJugador2, adminResultados );

            super.iniciarEncuentro( encuentroDecorado );

            long tInicio = System.currentTimeMillis( );
            long tFinal = tInicio + timeout;

            while( !encuentroDecorado.estaIniciado( ) && System.currentTimeMillis( ) < tFinal )
            {
                try
                {
                    Thread.sleep( 100 );
                }
                catch( InterruptedException e1 )
                {
                }
            }

            if( !encuentroDecorado.estaIniciado( ) )
                mensajeFalla = "El encuentro no se inici� en el tiempo disponible";
            else
                encuentroIniciado = true;
        }
        catch( IOException e )
        {
            mensajeFalla = "Hubo un error iniciando el encuentro: " + e.getMessage( );
        }
    }

    /**
     * Este m�todo sirve para saber si ya se inici� un encuentro. <br>
     * Este m�todo espera hasta que el encuentro se inicie o hasta que se cumpla el timeout establecido.
     * @return Retorna true si se inici� un encuentro. Retorna false en caso contrario.
     */
    public boolean seInicioEncuentro( )
    {
        long tInicio = System.currentTimeMillis( );
        long tFinal = tInicio + timeout;

        while( !encuentroIniciado && mensajeFalla == null && System.currentTimeMillis( ) < tFinal )
        {
            try
            {
                Thread.sleep( 100 );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace( );
            }
        }

        return encuentroIniciado;
    }

    /**
     * Retorna el mensaje que explica porqu� hubo un fallo
     * @return Se retorn� el mensaje con la cuasa de la falla. Si no ha habido ninguna falla, se retorno null
     */
    public String darFallo( )
    {
        return mensajeFalla;
    }
}
