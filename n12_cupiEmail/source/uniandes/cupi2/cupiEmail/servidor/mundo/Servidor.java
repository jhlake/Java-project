package uniandes.cupi2.cupiEmail.servidor.mundo;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;


/**
 * Clase que representa el servidor principal del CupiEmail.
 * @author jlake
 */
public class Servidor {

	//-------------------------------------------------------
	// Atributos
	//-------------------------------------------------------
	/**
	 * EL canal por el cual se comunica el servidor.
	 */
	private ServerSocket svSocket;

	/**
	 * Archivo que contiene las propiedades del servidor.
	 */
	private Properties config;

	/**
	 * Administrador de la base de datos.
	 */
	private AdminDB admindb;


	//-------------------------------------------------------
	// Constructor
	//-------------------------------------------------------

	/**
	 * Método que inicializa la base de datos, su administrador, y también el servidor.
	 * @param archivo Archivo con las propiedades de la base de datos. archivo != null && archivo != "".
	 */
	public Servidor(String archivo)
	{
		try {
			cargarConfiguracion(archivo);
			admindb = new AdminDB(config);
			admindb.connectToDB();
			admindb.inicializarTablaUsuarios();
			admindb.inicializarTablaCorreos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que devuelve el administrador de la base de datos.
	 * @return Retorna el administrador de la base de datos.
	 */
	public AdminDB darAdministrador()
	{
		return admindb;
	}

	/**
	 * Carga la configuración a partir de un archivo de propiedades
	 * @param archivo El archivo de propiedades que contiene la configuración que requiere el servidor - archivo != null y el archivo debe contener la propiedad
	 *        "servidor.puerto" y las propiedades que requiere el administrador de resultados
	 * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
	 */
	private void cargarConfiguracion( String archivo ) throws Exception
	{
		FileInputStream fis = new FileInputStream( archivo );
		config = new Properties( );
		config.load( fis );
		fis.close( );
	}

	/**
	 * Intenta crear e iniciar un nuevo encuentro con el jugador que se acaba de conectar. <br>
	 * @param socketCliente El canal que permite la comunicación con el nuevo cliente - socket != null
	 */
	synchronized private void atender( Socket socketCliente ) throws IOException
	{
		Atender nuevo = new Atender( socketCliente, admindb );
		nuevo.start();
	}

	/**
	 * Método que se encarga de recibir todas las conexiones de usuarios y remitirlos a un socket específico.
	 */
	public void recibirConexiones()
	{
		try
		{
			svSocket = new ServerSocket(9999);
			while(true)
			{
				Socket socNuevo= svSocket.accept();
				atender(socNuevo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				svSocket.close( );
			}
			catch( IOException e )
			{
				e.printStackTrace( );
			}
		}
	}

	/**
	 * Método que devuelve un entero, que representa la cantidad de correos que tiene el usuario.
	 * @param username Login del usuario que se quiere buscar. username != null && username != "".
	 * @return Entero que representa la cantidad de correos que tiene el usuario.
	 */
	public int darCantidadCorreosUsuario(String username)
	{
		return Integer.parseInt(admindb.darCantidadCorreosUsuario(username));
	}

	/**
	 * Método que devuelve la cantidad de correos sin leer que tiene un usuario.
	 * @param username Login del usuario que se quiere buscar. username != null && username != "".
	 * @return Devuelve un entero que representa la cantidad de correos sin leer que tiene un usuario.
	 */
	public int darCantidadCorreosSinLeerUsuario(String username)
	{
			return Integer.parseInt(admindb.darCantidadCorreosSinLeerUsuario(username));
	}
	// -----------------------------------------------------------------
	// Puntos de Extensión
	// -----------------------------------------------------------------

	/**
	 * Método para la extensión 1
	 * @return respuesta1
	 */
	public String metodo1( )
	{
		return "Respuesta 1";
	}

	/**
	 * Método para la extensión 2
	 * @return respuesta2
	 */
	public String metodo2( )
	{
		return "Respuesta 2";
	}
}
