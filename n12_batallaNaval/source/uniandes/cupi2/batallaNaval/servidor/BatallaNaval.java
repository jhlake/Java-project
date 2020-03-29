/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BatallaNaval.java 641 2006-11-14 16:22:14Z da-romer $
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

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

/**
 * El Servidor Batalla Naval es el que se encarga de recibir conexiones de los clientes y organizar los encuentros. <br>
 * <b>inv:</b><br>
 * receptor!= null <br>
 * config!=null <br>
 * adminResultados!=null <br>
 * encuentros!=null <br>
 */
public class BatallaNaval
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el punto por el cual los clientes solicitan conexiones
     */
    private ServerSocket receptor;

    /**
     * Es el conjunto de propiedades que contienen la configuraci�n de la aplicaci�n
     */
    private Properties config;

    /**
     * Es el administrador que permite registrar los resultados sobre la base de datos
     */
    private AdministradorResultados adminResultados;

    /**
     * Este es el canal utilizado para establecer una comunicaci�n con un jugador que est� en espera de un oponente. <br>
     * Si no hay jugadores en espera, este canal debe ser null.
     */
    private Socket socketJugadorEnEspera;

    /**
     * Es una colecci�n con los encuentros que se est�n llevando a cabo en este momento
     */
    protected Collection encuentros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicia el servidor y deja listo el administrador de resultados
     * @param archivo El archivo de propiedades que tiene la configuraci�n del servidor - archivo != null
     * @throws Exception Se lanza esta excepci�n si hay problemas con el archivo de propiedades o hay problemas en la conexi�n a la base de datos
     * @throws SQLException Se lanza esta excepci�n si hay problemas conectando el almac�n a la base de datos.
     */
    public BatallaNaval( String archivo ) throws SQLException, Exception
    {
        encuentros = new Vector( );

        cargarConfiguracion( archivo );

        adminResultados = new AdministradorResultados( config );
        adminResultados.conectarABD( );
        adminResultados.inicializarTabla( );
       verificarInvariante(); 
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Carga la configuraci�n a partir de un archivo de propiedades
     * @param archivo El archivo de propiedades que contiene la configuraci�n que requiere el servidor - archivo != null y el archivo debe contener la propiedad
     *        "servidor.puerto" y las propiedades que requiere el administrador de resultados
     * @throws Exception Se lanza esta excepci�n si hay problemas cargando el archivo de propiedades
     */
    private void cargarConfiguracion( String archivo ) throws Exception
    {
        FileInputStream fis = new FileInputStream( archivo );
        config = new Properties( );
        config.load( fis );
        fis.close( );
    }

    /**
     * Retorna al administrador de resultados
     * @return adminResultados;
     */
    public AdministradorResultados darAdministradorResultados( )
    {
        return adminResultados;
    }

    /**
     * Retorna una colecci�n actualizada con los encuentros que se est�n desarrollando actualmente y no han terminado.<br>
     * Si hab�a encuentros en la lista que ya hab�an terminado deben ser eliminados.
     * @return colecci�n de encuentros
     */
    public Collection darListaActualizadaEncuentros( )
    {
        Collection listaActualizada = new Vector( );

        // Armar la lista actualizada con los encuentros que no han terminado
        Iterator iter = encuentros.iterator( );
        while( iter.hasNext( ) )
        {
            Encuentro e = ( Encuentro )iter.next( );
            if( !e.encuentroTerminado( ) )
                listaActualizada.add( e );
        }

        // Reemplazar la lista antigua con la lista actualizada
        encuentros = listaActualizada;

        return encuentros;
    }

    /**
     * Este m�todo se encarga de recibir todas las conexiones entrantes y crear los encuentros cuando fuera necesario.
     */
    public void recibirConexiones( )
    {
        String aux = config.getProperty( "servidor.puerto" );
        int puerto = Integer.parseInt( aux );
        try
        {
            receptor = new ServerSocket( puerto );

            while( true )
            {
                // Esperar una nueva conexi�n
                Socket socketNuevoCliente = receptor.accept( );

                // Intentar iniciar un encuentro con el nuevo cliente
                crearEncuentro( socketNuevoCliente );
            }
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
        finally
        {
            try
            {
                receptor.close( );
            }
            catch( IOException e )
            {
                e.printStackTrace( );
            }
        }
    }

    /**
     * Intenta crear e iniciar un nuevo encuentro con el jugador que se acaba de conectar. <br>
     * Si no se tiene ya un oponente, entonces el jugador queda en espera de que otra persona se conecte.
     * @param socketNuevoCliente El canal que permite la comunicaci�n con el nuevo cliente - socket != null
     * @throws IOException Se lanza esta excepci�n si se presentan problemas de comunicaci�n
     */
    synchronized private void crearEncuentro( Socket socketNuevoCliente ) throws IOException
    {
        if( socketJugadorEnEspera == null )
        {
            // No hay un oponente a�n, as� que hay que dejarlo en espera
            socketJugadorEnEspera = socketNuevoCliente;
        }
        else
        {
            // Ya se tiene un oponente as� que se puede empezar una partida
            Socket jug1 = socketJugadorEnEspera;
            Socket jug2 = socketNuevoCliente;

            socketJugadorEnEspera = null;

            try
            {
                Encuentro nuevo = new Encuentro( jug1, jug2, adminResultados );
                iniciarEncuentro( nuevo );
            }
            catch( IOException e )
            {
                try
                {
                    jug1.close( );
                }
                catch( IOException e1 )
                {
                    e.printStackTrace( );
                }
                try
                {
                    jug2.close( );
                }
                catch( IOException e2 )
                {
                    e.printStackTrace( );
                }

                // Mostrar la excepci�n original
                e.printStackTrace( );
            }
        }
        
        verificarInvariante();
    }

    /**
     * Agrega el encuentro a la lista de encuentros en curso y lo inicia
     * @param nuevoEncuentro Un encuentro que no ha sido inicializado ni agregado a la lista de encuentros - nuevoEncuentro != null
     */
    protected void iniciarEncuentro( Encuentro nuevoEncuentro )
    {
        encuentros.add( nuevoEncuentro );
        nuevoEncuentro.start( );
    }
    
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b><br>
     * receptor!= null <br>
     * config!=null <br>
     * adminResultados!=null <br>
     * encuentros!=null <br>
     */
    private void verificarInvariante( )
    {        
        assert receptor != null : "Canal inv�lido";
        assert config != null : "Conjunto de propiedades inv�lidas";
        assert adminResultados != null : "El administrador de resultados no deber�a ser null";
        assert encuentros != null : "La lista de encuentros no deber�a ser null";            
    }
    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
    
    /**
     * M�todo para la extensi�n3
     * @return respuesta3
     */
    public String metodo3( )
    {
        return "Respuesta 3";
    }

}
