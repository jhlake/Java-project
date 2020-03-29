/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DecoradorEncuentro.java 590 2006-11-04 22:15:59Z jvillalo2 $ 
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

import java.io.IOException;
import java.net.Socket;

import uniandes.cupi2.batallaNaval.cliente.*;
import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta clase extiende el comportamiento de la clase encuentro para facilitar las pruebas de la clase ServidorBatallaNaval
 */
public class DecoradorEncuentro extends Encuentro
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Indica si el encuentro ya se inici�
     */
    private boolean encuentroIniciado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo encuentro
     * @param cliente1 El socket que permite la comunicaci�n con el cliente 1
     * @param cliente2 El socket que permite la comunicaci�n con el cliente 2
     * @param administrador El administrador de resultados usado
     * @throws IOException Se lanza esta excepci�n si hay problemas en la comunicaci�n
     */
    public DecoradorEncuentro( Socket cliente1, Socket cliente2, AdministradorResultados administrador ) throws IOException
    {
        super( cliente1, cliente2, administrador );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia un encuentro y cambia el valor del atributo encuentroIniciado
     * @throws IOException Se lanza esta excepci�n si hay problemas en la comunicaci�n
     * @throws BatallaNavalException Se lanza esta excepci�n si hay problemas en la comunicaci�n
     */
    protected void iniciarEncuentro( ) throws IOException, BatallaNavalException
    {
        super.iniciarEncuentro( );
        encuentroIniciado = true;
    }

    /**
     * Indica si el encuentro ya se inici�
     * @return encuentroIniciado
     */
    public boolean estaIniciado( )
    {
        return encuentroIniciado;
    }

    /**
     * Retorna el nombre del jugador 1
     * @return nombre del jugador 1
     */
    public String darNombreJugador1( )
    {
        return jugador1.darRegistroJugador( ).darNombreJugador( );
    }

    /**
     * Retorna el nombre del jugador 2
     * @return nombre del jugador 2
     */
    public String darNombreJugador2( )
    {
        return jugador2.darRegistroJugador( ).darNombreJugador( );
    }

}
