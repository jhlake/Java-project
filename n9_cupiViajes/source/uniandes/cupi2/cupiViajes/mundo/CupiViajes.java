/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiViajes
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiViajes.mundo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import uniandes.cupi2.cupiViajes.excepciones.ClienteTieneReservaException;
import uniandes.cupi2.estructuras.ListaDoblementeEncadenada;
import uniandes.cupi2.estructuras.ListaSencillamenteEncadenada;

/**
 * Clase que representa CupiViajes. <br>
 * <b> inv: </b> <br>
 * hoteles != null. <br>
 * reservas != null. <br>
 * No pueden existir dos reservas con el mismo cliente.
 */
public class CupiViajes implements Serializable
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Lista de hoteles disponibles en el sistema de viajes.
     */
    private List<Hotel> hoteles;

    /**
     * Lista de reservas.
     */
    private List<ReservaViaje> reservas;

    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Construye un nuevo sistema de viajes sin hoteles y sin reservas.<br>
     * <b> post: </b> Las listas de hoteles y de viajes han sido inicializadas.
     */
    public CupiViajes( )
    {
        hoteles = new ListaDoblementeEncadenada<Hotel>( );
        reservas = new ListaSencillamenteEncadenada<ReservaViaje>( );
        verificarInvariante( );
    }

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Retorna la lista de hoteles disponibles en el sistema.
     * @return Lista de hoteles disponibles.
     */
    public List<Hotel> darHoteles( )
    {
        return hoteles;
    }

    /**
     * Retorna la lista de reservas.
     * @return Lista de reservas.
     */
    public List<ReservaViaje> darReservas( )
    {
        return reservas;
    }

    /**
     * Agrega una nueva reserva a la lista si no existe una reserva con el cliente dado. <br>
     * <b> pre </b>: El hotel con el nombre dado ya existe.
     * @param pHotel Hotel que se va a reservar. pHotel != null.
     * @param pFechaLLegada fecha de llegada al hotel que se va a reservar. pDiaLlegada !=null.
     * @param pNombreCliente Nombre del cliente responsable de la reserva. pCliente != null && pCliente != "".
     * @param pCantidadAdultos Cantidad de adultos que van a viajar al hotel que se va a reservar. pCantidadAdultos >= 1 && pCantidadAdultos <= 6.
     * @param pCantidadNinios Cantidad de ni�os que van a viajar al hotel que se va a reservar. pCantidadNinios >= 0 && pCantidadNinios <= 4.
     * @param pNochesEstadia Cantidad de noches de estad�a en el hotel que se va a reservar. pNochesEstadia >= 1.
     * @param pAerolinea Aerol�nea que se va a reservar. pAerolinea != null
     * @return True si la reserva se agreg� correctamente. False de lo contrario.
     */
    public void agregarReserva( Hotel pHotel, Date pFechaLLegada, String pCedula, String pNombreCliente, int pCantidadAdultos, int pCantidadNinios, int pNochesEstadia, Aerolinea pAerolinea ) throws ClienteTieneReservaException
    {
        ReservaViaje buscado = buscarReserva( pCedula );
        if( buscado == null )
        {
            ReservaViaje reservaAAgregar = new ReservaViaje( pCedula, pNombreCliente, pCantidadAdultos, pCantidadNinios, pNochesEstadia, pAerolinea, pHotel, pFechaLLegada );
            reservas.add( reservaAAgregar );
            verificarInvariante( );
        }
        else
        {
           throw new ClienteTieneReservaException( "El cliente ya tiene una reserva", pCedula );
        }

    }
    
    
    /**
     * Agrega un nuevo hotel a la lista de hoteles. <br>
     * @param pNombre Nombre del hotel. pNombre != null && pNombre != "".
     * @param pCiudad Ciudad en la que se encuentra el hotel. pCiudad != null && pCiudad != "".
     * @param pEstrellas Cantidad de estrellas que tiene el hotel. pEstrellas >= 1 && pEstrellas <=5.
     * @param pCostoNoche Costo por noche en el hotel. pCostoNoche >= 1.
     * @param pRutaImagen Ruta de la imagen del hotel. pRutaImagen != null && pRutaImagen != "".
     */
    public void agregarHotel( String pNombre, String pCiudad, int pEstrellas, double pCostoNoche, String pRutaImagen )
    {
        Hotel hotel = new Hotel( pNombre, pCiudad, pEstrellas, pCostoNoche, pRutaImagen );
        hoteles.add( hotel );
    }
    
    /**
     * Busca los hoteles que est�n en la ciudad dada por par�metro.
     * @param pCiudad Ciudad  de los hoteles que se est�n buscando. pCiudad != null && pCiudad != "".
     * @return Lista con los hoteles que est�n en la ciudad dada.
     */
    public List<Hotel> buscarHotelesCiudad( String pCiudad )
    {
        // TODO Parte 4.A: completar usando iteradores
        ListaSencillamenteEncadenada<Hotel> ans = new ListaSencillamenteEncadenada<Hotel>( );
        Hotel temp = null;
        int size = hoteles.size( );
        for( int i = 0; i < size; i++ )
        {
            temp = hoteles.get( i );
            if( temp.darCiudad( ).equals( pCiudad ) )
            {
               ans.add( temp );
            }
        }
        return ans;
       
    }
    
    /**
     * Busca la reserva del cliente dado por par�metro. <br>
     * @param pCliente Cliente se la reserva que se va a buscar. pCliente != null && pCliente != "".
     * @return Reserva de viaje con el cliente dado, null en caso de no encontrarlo.
     */
    public ReservaViaje buscarReserva( String pCliente )
    {
        // TODO completar usando iteradores
        ReservaViaje reservaBuscada = null;
        boolean encontro = false;
        
        Iterator<ReservaViaje> i = reservas.iterator( );
        while( i.hasNext( ) && !encontro)
        {
            ReservaViaje temporal = i.next( );
            if( temporal.darCedula( ).equals( pCliente ) )
            {
                reservaBuscada = temporal;
                encontro = true;
            }
        }
        
        return reservaBuscada;
    }

    /**
     * Busca la reserva del cliente dado por par�metro utilizando una b�squeda binaria. <br>
     * <b> pre: </b> La lista de reservas se encuentra ordenada por clientes.
     * @param pNombreCliente Nombre del cliente de la reserva que se va a buscar. pNombreCliente != null && pNombreCliente != "".
     * @return Reserva de viaje del cliente dado, null en caso de no encontrarlo.
     */
    public ReservaViaje buscarReservaPorClienteBinario( String pNombreCliente )
    {
        ReservaViaje reservaBuscada = null;
        Comparator<ReservaViaje> comparador = new ComparadorReservaNombreCliente( );
        int inicio = 0;
        int fin = reservas.size( ) - 1;
        ReservaViaje reservaABuscar = new ReservaViaje( "", pNombreCliente, 1, 0, 1, Aerolinea.AVIANCA, new Hotel( "nombre", "ciudad", 1, 1, "imagen" ), new Date() );
        if( reservas.size( ) > 0 )
        {
            while( inicio <= fin && reservaBuscada == null )
            {
                int medio = ( inicio + fin ) / 2;
                ReservaViaje reserva = reservas.get( medio );
                int comparacion = comparador.compare( reserva, reservaABuscar );
                if( comparacion == 0 )
                {
                    reservaBuscada = reserva;
                }
                else if( comparacion > 0 )
                {
                    fin = medio - 1;
                }
                else
                {
                    inicio = medio + 1;
                }
            }
        }

        return reservaBuscada;
    }

    /**
     * Busca la primera reserva del viaje a la ciudad dada por par�metro.
     * @param pCiudad Ciudad del hotel de la reserva que se va a buscar. pCiudad != null && pCiudad != "";
     * @return Reserva del viaje a la ciudad dada por par�metro.
     */
    public ReservaViaje buscarReservaPorCiudad( String pCiudad )
    {
        // TODO Parte 4.A: completar usando iteradores
        ReservaViaje ans = null;
        boolean found = false;
        for(int i=0; !found; i++)
        {
            ReservaViaje temp = reservas.get( i );
            if(temp.darHotel( ).darCiudad( ).equals( pCiudad ))
            {
                ans = temp;
                found = true;
            }
        }
        return ans;
    }

    /**
     * Busca la reserva con la mayor cantidad de personas que van a viajar.
     * @return Reserva con la mayor cantidad de personas que van a viajar. <br>
     *         Si CupiViajes no tiene reservas, retorna null. Si existen varias reservas con la mayor cantidad de personas que van a viajar, se retorna la primera reserva
     *         encontrada.
     */
    public ReservaViaje buscarReservaMasPersonas( )
    {
        // TODO Parte 4.A: completar usando iteradores
        ReservaViaje buscado = null;
        Comparator<ReservaViaje> comparador = new CompardorReservaCantidadPersonas( );
        Iterator<ReservaViaje> i = reservas.iterator( );
        if( i.hasNext() )
        {
            ReservaViaje reservaMasPersonas = i.next();
            buscado = reservaMasPersonas;

            while(i.hasNext())
            {
                ReservaViaje reservaTemp = i.next();
                if( comparador.compare( reservaTemp, reservaMasPersonas ) == 1 )
                {
                    reservaMasPersonas = reservaTemp;
                    buscado = reservaMasPersonas;
                }
            }
        }

        return buscado;
       
    }

    /**
     * Busca la reserva con la menor cantidad de personas que van a viajar.
     * @return Reserva con la menor cantidad de personas que van a viajar. <br>
     *         Si CupiViajes no tiene reservas, retorna null. Si existen varias reservas con la menor cantidad de personas que van a viajar, se retorna la primera reserva
     *         encontrada.
     */
    public ReservaViaje buscarReservaMenosPersonas( )
    {
        // TODO Parte 4.A: completar usando iteradores
        ReservaViaje buscado = null;
        Comparator<ReservaViaje> comparador = new CompardorReservaCantidadPersonas( );
        Iterator<ReservaViaje> i = reservas.iterator( );
        if( i.hasNext() )
        {
            ReservaViaje reservaMenosPersonas = i.next();
            buscado = reservaMenosPersonas;

            while(i.hasNext())
            {
                ReservaViaje reservaTemp = i.next();
                if( comparador.compare( reservaTemp, reservaMenosPersonas ) == -1 )
                {
                    reservaMenosPersonas = reservaTemp;
                    buscado = reservaMenosPersonas;
                }
            }
        }

        return buscado;
       
       
    }

    /**
     * Busca las reservas con aerol�nea dada por par�metro.
     * @param pAerolinea Aerol�nea de las reservas que se est�n buscando. pAerolinea != "" && pAerolinea pertenece a {ReservaViaje.AVIANCA, ReservaViaje.LAN,
     *        ReservaViaje.VIVA_COLOMBIA, ReservaViaje.SATENA, ReservaViaje.JETBLUE, ReservaViaje.IBERIA, ReservaViaje.AIR_FRANCE}.
     * @return Lista de reservan con aerol�nea dada.
     */
    public List<ReservaViaje> buscarReservasAerolinea( Aerolinea pAerolinea )
    {
        // TODO Parte 4.A: completar usando iteradores
        ListaSencillamenteEncadenada<ReservaViaje> ans = new ListaSencillamenteEncadenada<ReservaViaje>( );
        ReservaViaje reservaTemp = null;
        Iterator<ReservaViaje> i = reservas.iterator( );
        while( i.hasNext())
        {
            reservaTemp = i.next( );
            if( reservaTemp.darAerolinea( ).equals( pAerolinea ) )
            {
                ans.add( reservaTemp );
            }
        }
        return ans;
       
    }
    /**
     * Cambia el estado de la reserva con cedula indicada
     * @param cedula la c�dula del cliente responsable de la reserva. Existe una reserva con la c�dula dada
     * @param estado el nuevo estado de la reserva. estado != null
     */
    public void cambiarEstadoReserva( String cedula, Estado estado )
    {
        buscarReserva( cedula ).cambiarEstado( estado );
        
    }

    // -------------------------------------------------------------
    // Invariante
    // -------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b> inv: </b> <br>
     * hoteles != null. <br>
     * reservas != null. <br>
     * No pueden existir dos reservas con el mismo cliente.
     */
    private void verificarInvariante( )
    {
        assert ( hoteles != null ) : "La lista de hoteles no puede ser nula.";
        assert ( reservas != null ) : "La lista de reservas no puede ser nula.";
        assert ( !buscarReservasMismoCliente( ) ) : "No pueden existir reservas con el mismo cliente.";
    }

    /**
     * Verifica si hay dos reservas con el mismo cliente.
     * @return True si hay dos reservas con el mismo cliente. False de lo contrario.
     */
    private boolean buscarReservasMismoCliente( )
    {
        boolean hay = false;
        for( int i = 0; i < reservas.size( ) && !hay; i++ )
        {
            ReservaViaje reservaI = ( ReservaViaje )reservas.get( i );
            for( int j = i + 1; j < reservas.size( ) && !hay; j++ )
            {
                ReservaViaje reservaJ = ( ReservaViaje )reservas.get( j );
                if( reservaI.darNombreCliente( ).equals( reservaJ.darNombreCliente( ) ) )
                {
                    hay = true;
                }
            }
        }
        return hay;
    }
    

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * M�todo para la extensi�n 2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }
}
