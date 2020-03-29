package uniandes.cupi2.cupiEmail.servidor.mundo;


import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Clase que representa el administrador de la base de datos.
 * @author jlake
 */
public class AdminDB {

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Conexi�n a la base de datos
	 */
	private Connection conexion;

	/**
	 * Conjunto de propiedades que contienen la configuraci�n de la aplicaci�n
	 */
	private Properties config;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el administrador de resultados y lo deja listo para conectarse a la base de datos
	 * @param propiedades Las propiedades para la configuraci�n del administrador - Debe tener las propiedades "admin.db.path", "admin.db.driver", "admin.db.url" y
	 *        "admin.db.shutdown"
	 */
	public AdminDB( Properties propiedades )
	{
		config = propiedades;

		// Establecer la ruta donde va a estar la base de datos.
		// Derby utiliza la propiedad del sistema derby.system.home para saber donde est�n los datos
		File data = new File( config.getProperty( "admin.db.path" ) );
		System.setProperty( "derby.system.home", data.getAbsolutePath( ) );

	}

	/**
	 * Conecta el administrador a la base de datos
	 * @throws SQLException Se lanza esta excepci�n si hay problemas realizando la operaci�n
	 * @throws Exception Se lanza esta excepci�n si hay problemas con los controladores
	 */
	public void connectToDB( ) throws SQLException, Exception
	{
		String driver = config.getProperty( "admin.db.driver" );
		Class.forName( driver ).newInstance( );

		String url = config.getProperty( "admin.db.url" );
		conexion = DriverManager.getConnection( url );
	}

	/**
	 * Desconecta el administrador de la base de datos y la detiene
	 * @throws SQLException Se lanza esta excepci�n si hay problemas realizando la operaci�n
	 */
	public void desconectarBD( ) throws SQLException
	{ 
		conexion.close( );
		String down = config.getProperty( "admin.db.shutdown" );
		try
		{
			DriverManager.getConnection( down );
		}
		catch( SQLException e )
		{
			// Al bajar la base de datos se produce siempre una excepci�n
		}
	}

	/**
	 * El m�todo busca entre los usuarios del cupiEmail, y los devuelve en una lista.
	 * @return ArrayList con los usuarios encontrados en la base de datos.
	 */
	public ArrayList<String> darTodosUsuarios()
	{
		ArrayList<String> clients = new ArrayList<String>();
		try
		{
			Statement st = conexion.createStatement();
			String sql = "SELECT login FROM Usuarios";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				clients.add(rs.getString("login"));
			}
			rs.close();st.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return clients;
	}
	/**
	 * El m�todo busca el usuario con el login dado por par�metro, y lo retorna si existe.
	 * @param us El login del usuario que se quiere buscar. us != null.
	 * @return Devuelve el usuario si se encontr�, null si no.
	 * @throws SQLException Si hay un fallo mientras se busca el usuario.
	 */
	public ClienteRemoto darUsuario(String us) throws SQLException
	{
		Statement st = conexion.createStatement();
		String sql = "SELECT * FROM Usuarios WHERE login = '" + us + "'";
		ResultSet rs = st.executeQuery(sql);
		ClienteRemoto ans = null;
		while(rs.next())
		{
			ans = new ClienteRemoto(us, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		}
		rs.close();st.close();
		return ans;
	}

	/**
	 * El m�todo busca entre los usuarios de cupiEmail cu�les est�n conectados o desconectados, y los retorna en una lista.
	 * @param conectado Estado que se quiere buscar. conectado == N || conectado == S.
	 * @return Devuelve una ArrayList con los usuarios conectado o desconectados.
	 */
	public ArrayList<String> darUsuariosEstado(String conectado)
	{
		ArrayList<String> ans = new ArrayList<String>();
		try 
		{
			Statement st = conexion.createStatement();
			String sql = "SELECT login FROM Usuarios WHERE conectado = '" + conectado +"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				ans.add(rs.getString(1));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ans;

	}
	/**
	 * Crea la tabla para los usuarios. Si la tabla ya estaba creada entonces no hace nada. <br>
	 * @throws SQLException Se lanza esta excepci�n si hay problemas creando la tabla
	 */
	public void inicializarTablaUsuarios( ) throws SQLException
	{
		Statement s = conexion.createStatement( );
		boolean crearTabla = false;
		try
		{
			// Verificar si ya existe la tabla resultados
			s.executeQuery( "SELECT * FROM Usuarios WHERE 1=2" );
		}
		catch( SQLException se )
		{
			// La excepci�n se lanza si la tabla resultados no existe
			crearTabla = true;
		}

		// Se crea una nueva tabla vac�a
		if( crearTabla )
		{
			s.execute( "CREATE TABLE Usuarios (login varchar(32), nombres varchar(50), apellidos varchar(50), contrasena varchar(32), conectado varchar(1), PRIMARY KEY (login))" );
		}

		s.close( );
	}
	/**
	 * Crea la tabla que guarda los correos. Si la tabla ya estaba creada entonces no hace nada. <br>
	 * @throws SQLException Se lanza esta excepci�n si hay problemas creando la tabla
	 */
	public void inicializarTablaCorreos( ) throws SQLException
	{
		Statement s = conexion.createStatement( );
		boolean crearTabla = false;
		try
		{
			// Verificar si ya existe la tabla resultados
			s.executeQuery( "SELECT * FROM Correos WHERE 1=2" );
		}
		catch( SQLException se )
		{
			// La excepci�n se lanza si la tabla resultados no existe
			crearTabla = true;
		}

		// Se crea una nueva tabla vac�a
		if( crearTabla )
		{
			s.execute("CREATE TABLE Correos(login_remitente varchar(32), login_destinatario varchar(32), fecha_envio varchar(20), asunto varchar(140), mensaje varchar(512), leido varchar(1), PRIMARY KEY (login_destinatario, fecha_envio, asunto ))");
		}

		s.close( );
	}

	/**
	 * El m�todo inicia sesi�n al usuario si la contrase�a es correcta.
	 * @param user Usuario que quiere conectarse. pass != null.
	 * @param pass Contrase�a a verificar. pass != null.
	 * @return Devuelve la respuesta pertinente si se logr� o no conectar.
	 */
	public String iniciarSesion(String user, String pass)
	{
		try
		{
			Statement st = conexion.createStatement();
			String ori = "SELECT contrasena FROM Usuarios WHERE login = '"+ user +"'";
			Statement p = conexion.createStatement();
			ResultSet rs = p.executeQuery(ori);
			if(!rs.next())
			{
				throw new Exception();
			}
			else if(rs.getString(1).equals(pass))
			{
				String sql = "UPDATE Usuarios SET conectado = 'S' WHERE login = '" + user + "'";
				st.execute(sql);
			}
			else
				throw new Exception("Contrase�a o login incorrecto.");
			rs.close(); p.close();
			st.close();
			return Atender.INICIAR_SESION_OK;
		}
		catch(Exception e)
		{
			return Atender.ERROR + Atender.SEPARADOR_COMANDO + e.getMessage();
		}
	}

	/**
	 * El m�todo cierra la sesi�n de un usuario.
	 * @param darLogin login del usuario que quiere cerrar sesi�n. darLogin != null.
	 * @return Devuelve una respuesta exitosa si logr� cerrar sesi�n, o no exitosa en caso contrario.
	 */
	public String cerrarSesion(String darLogin) {
		try
		{
			Statement st = conexion.createStatement();
			String sql = "UPDATE Usuarios SET conectado='n' WHERE login = '" + darLogin + "'";
			st.executeUpdate(sql);
			return Atender.CERRAR_SESION_OK;
		}
		catch(Exception e)
		{
			return Atender.ERROR + e.getMessage();
		}
	}

	/**
	 * M�todo que se encanrga de crear la cuenta del usuario. <br>
	 * <b> Pos: </b> Se cre� la cuenta del usuario si no exist�a antes, y se marc� como usuario siendo atendido actu�lmente. <br>
	 * @param username Usuario con el cual se quiere crear la cuenta. username != null
	 * @param nombres Nombre del usuario que crea la cuenta. nombres != null && nombres != "".
	 * @param apellidos Apellidos del usuario que crea la cuenta. apellidos != null && apellidos != "".
	 * @param contrasena Constrase�a para �ste usuario. contrasena != null && contrasena != "".
	 * @return Devuelve la respuesta pertinente, si se creo o no la cuenta.
	 */
	public String crearCuenta(String username, String nombres, String apellidos, String contrasena) {
		try
		{
			Statement st = conexion.createStatement();
			String search = "SELECT * FROM Usuarios WHERE login = '" + username + "'";
			ResultSet rs = st.executeQuery(search);
			if(rs.next())
			{
				rs.close();st.close();
				throw new Exception("El usuario ya existe.");
			}
			else
			{
				String sql = "INSERT INTO Usuarios VALUES('" + username + "', '" + nombres + "', '" + apellidos + "', '" + contrasena +"', 'n')";
				st.executeUpdate(sql);
			}
			rs.close();st.close();
			return Atender.CREAR_CUENTA_OK;
		}
		catch(Exception e)
		{
			return Atender.ERROR + Atender.SEPARADOR_COMANDO +  e.getMessage();
		}
	}

	/** 
	 * M�todo que se encarga de marcar como le�do un correo.
	 * @param fecha Fecha en la cual se envi� el correo. fecha != null && fecha != "".
	 * @param asunto Asunto del mensaje que se quiere marcar como le�do. asunto != null.
	 * @return Devuelve el mensaje pertinente.
	 */
	public String marcarLeido(String fecha, String asunto) {
		try
		{
			Statement st = conexion.createStatement();
			String sql = "UPDATE Correos SET leido = 'S' WHERE fecha_envio = '" + fecha + "' AND asunto = '" + asunto + "'";
			st.executeUpdate(sql);
			st.close();
			return Atender.MARCAR_COMO_LEIDO_OK;
		}
		catch(Exception e)
		{
			return Atender.ERROR +Atender.SEPARADOR_COMANDO + e.getMessage();
		}
	}

	/**
	 * M�todo que se encarga de enviar un mensaje a los destinatarios pasados por par�metros.
	 * @param destinatarios login de los destinatarios de los mensajes, separados por ",". destinatarios != null && destinatarios != "".
	 * @param mensaje Mensaje que se quiere enviar. mensaje != null && mensaje != "".
	 * @param asunto Asunto con el cual se quiere enviar el mensaje. asunto != null && asunto != "". 
	 * @param remitente Login de la persona que env�a el mensaje. remitente != null && remitente != "".
	 * @return Devuelve mensaje exitoso si a todos los destinatarios se le envio. Si a alguno falla, devuelve el usuario de los que fallaron separado por , y precedido por el comando de error.
	 */
	public String send(String destinatarios, String mensaje, String asunto, String remitente) {
		String[] rec = destinatarios.split(",");
		String failures = "";
		int countfails = 0;
		for(int i = 0; i < rec.length; i++)
		{
			try
			{
//				Statement st = conexion.createStatement();
//				String test = "SELECT nombres FROM Usuarios WHERE username ='" + rec[i]+ "'";
//				ResultSet rs = st.executeQuery(test);
//				if(!rs.next())
//				{
//					throw new Exception("falla");
//				}
//				rs.close(); st.close();
				Statement p = conexion.createStatement();
				String sql = "INSERT INTO Correos VALUES('" + remitente + "', '" + rec[i] + "', '" + Calendar.getInstance().getTimeInMillis() + "', '" + asunto + "', '" + mensaje + "', 'N')";
				p.execute(sql);
				p.close();
			}
			catch(Exception e)
			{
				if(e.getMessage().equals("falla"))
				{
					countfails++;
					failures= failures +","+rec[i];
				}
			}
		}
		String ans = "";
		if(countfails == 0)
			ans = Atender.ENVIAR_CORREO_OK;
		else
			ans = Atender.ERROR + Atender.SEPARADOR_COMANDO + "Error registrando el correo para: " + failures;
		return ans;
	}

	/**
	 * Devuelve la cantidad de correos que tiene una persona en su cuenta. Si falla, devuelve un mensaje de error.
	 * @param username Usuario de la persona que se quiere buscar. username != null && username != "".
	 * @return N�mero que representa la cantidad de correos que tiene la persona en su bandeja, o un mensaje de error si falla.
	 */
	public String darCantidadCorreosUsuario(String username) 
	{
		int count = 0;
		try
		{
			Statement st = conexion.createStatement();
			String sql = "SELECT * FROM Correos WHERE login_destinatario = '" + username + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				count++;
			}
			rs.close();st.close();
		}
		catch(Exception e)
		{
			return Atender.ERROR + Atender.SEPARADOR_COMANDO +"Error consultando la lista de correos.";
		}
		return count +"";
	}

	/**
	 * M�todo que devuelve los correos de un usuario como tal.
	 * @param username Login del usuario que se quiere buscar.
	 * @param number N�mero del correo
	 * @return ResultSet con los correos del usuario.
	 */
	public String darCorreosUsuario(String username, int number) 
	{
		int count = 0;
		String ans = "";
		try
		{
			Statement st = conexion.createStatement();
			String sql = "SELECT login_remitente, fecha_envio, asunto, mensaje, leido FROM Correos WHERE login_destinatario = '" + username + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				if(count==number)
				{
					ans = Atender.INFO_CORREOS + Atender.SEPARADOR_COMANDO + rs.getString(1) + Atender.SEPARADOR_PARAMETRO + rs.getString(2) + Atender.SEPARADOR_PARAMETRO + rs.getString(3) + Atender.SEPARADOR_PARAMETRO + rs.getString(4) + Atender.SEPARADOR_PARAMETRO + rs.getString(5);
				}
				count++;
			}
			return ans;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * M�todo que devuelve la cantidad de correos sin leer que tiene el usuario. Devuelve un error si falla.
	 * @param username Login del usuario que se quiere buscar.
	 * @return N�mero que representa la cantidad de correos sin leer que tiene el usuario, o un mensaje de error si falla.
	 */
	public String darCantidadCorreosSinLeerUsuario(String username) 
	{
		int count = 0;
		try
		{
			Statement st = conexion.createStatement();
			String sql = "SELECT fecha_envio, asunto, leido FROM Correos WHERE login_destinatario = '" + username + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				if(rs.getString(3).equals("N"))
				{
					count++;
				}
			}
			rs.close();
			st.close();
			return count +"";
		}
		catch(Exception e)
		{
			return Atender.ERROR + Atender.SEPARADOR_COMANDO +"Error consultando la lista de correos.";
		}
	
	}

}
