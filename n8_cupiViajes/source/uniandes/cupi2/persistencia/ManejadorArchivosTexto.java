package uniandes.cupi2.persistencia;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import uniandes.cupi2.cupiViajes.excepciones.PersistenciaException;
import uniandes.cupi2.cupiViajes.mundo.CupiViajes;
import uniandes.cupi2.cupiViajes.mundo.ReservaViaje;


/**
 * Clase que permite interpretar el contenido del archivo CSV de puestos
 * @author alvar-go
 *
 */
public class ManejadorArchivosTexto {

    /**
     * Separador de columnas de archivos CSV
     */
    public final static String SEPARADOR = ";";

    public final static String LINEA_PUNTEADA = "==============================================================";

    /**
     * Importa la informaci�n del archivo que llega por par�metro en el sistema que llega por par�metro
     * post:	Se agregaron los hoteles del archivo al sistema
     * @param ruta la ruta donde se encuentra el archivo. ruta != null
     * @param cv el sistema donde se deben cargar los datos del archivo. cv != null
     * @throws PersistenciaException Se lanza en caso que no se pueda leer el archivo
     */
    public static void importarCSV(String ruta, CupiViajes cv) throws PersistenciaException
    {
        //TODO PArte 3.A Lea el archivo csv y cargue (agregue) los hoteles en el sistema de cupivaijes que llega por par�metro
        //		Si el archivo tiene formato incorrecto o Si no se puede abrir o leer el archivo lanza una PersistenciaException

        File csv = new File(ruta);
        try
        {
            if(!csv.canExecute( ) || !csv.canRead( ))
            {
                throw new PersistenciaException(csv.getName(), "El archivo no puede abrirse o no se puede leer.");
            }
            BufferedReader in = new BufferedReader(new FileReader(csv));
            for( String x = in.readLine( ); x!= null; x = in.readLine( ))
            {
                String[] temp = new String[5];
                temp = x.split(SEPARADOR);
                cv.agregarHotel( temp[0],temp[1] , Integer.parseInt(temp[2]), Double.parseDouble( temp[3] ), temp[4] );
            }
            in.close( );
        }
        catch(Exception e)
        {
            throw new PersistenciaException(csv.getName( ), "El archivo no tiene el formato esperado.");
        }


    }

    /**
     * Crea un reporte de texto con las reservas realizadas hasta el moemnto.
     * @param ruta. La ruta donde se desea guardar el reporte. ruta != null
     * @param cv el sistema cupiviajes del que se desea generar el reporte. cv != null
     * @throws PersistenciaException Se lanza en caso de que se genere alg�n error creando el reporte
     */
    public static void generarReporte(String ruta, CupiViajes cv) throws PersistenciaException
    {
        //TODO Parte 3.D Complete el m�todo seg�n la documentaci�n y el formato de archivo presentado en la descripic�n
        File file = new File(ruta);
        if(!file.exists( ))
        {
            try
            {
                file.createNewFile( );
            }
            catch(Exception e)
            {
                throw new PersistenciaException( ruta, "Ocurrió un error al crear el archivo de reporte." );
            }
        }
        try
        {
            PrintWriter pw = new PrintWriter( file );
            ArrayList<ReservaViaje> reservas = cv.darReservas( );
            int size = reservas.size( );
            pw.write( "Reporte reservas hechas \n" );
            pw.write( "Fecha: " + Calendar.getInstance( ).getTime( ) + "\n" );
            pw.write( LINEA_PUNTEADA + "\n" );
            for(int i= 0; i<size; i++)
            {
                ReservaViaje  act = reservas.get( i );
                int numPers = act.darCantidadAdultos( ) + act.darCantidadNinios( );
                pw.write( "Cédula del cliente: " + act.darCedula( ) + "\n");
                pw.write( "Nombre del cliente: " + act.darNombreCliente( ) + "\n");
                pw.write( "Total personas en el viaje: " + numPers + "\n");
                pw.write( "Noches de estadía: " + act.darCantidadNochesEstadia( )+ "\n" );
                pw.write( "Aerolínea: " + act.darAerolinea( ) + "\n" );
                pw.write( "Nome del hotel: " + act.darHotel( ).darNombre( ) + "\n" );
                pw.write( "Fecha de llegada: "  + act.darFechaLlegada( ) + "\n" );
                pw.write( "Costo total de la reserva: " + act.darCostoTotal( ) + "\n" );
                pw.write( LINEA_PUNTEADA + "\n");
            }
            pw.write( "=================================================" );
            pw.close( );
        }
        catch(Exception e )
        {
            throw new PersistenciaException( ruta, "Ocurrió un error al escribir el archivo de reporte." );
        }



    }
}
