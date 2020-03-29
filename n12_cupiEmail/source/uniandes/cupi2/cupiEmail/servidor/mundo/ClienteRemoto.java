package uniandes.cupi2.cupiEmail.servidor.mundo;

/**
 * Clase que representa la informaci�n de un usuario.
 * @author jlake
 */
public class ClienteRemoto {

	/*
	 * Atributos
	 */
	/**
	 * Atributo que representa el nombre del cliente.
	 */
	private String nombre;

	/**
	 * Atributo que representa los apellidos del cliente.
	 */
	private String apellido;

	/**
	 * Atributo que contiene el login del cliente.
	 */
	private String login;

	/**
	 * Atributo que contiene la contrase�a del cliente.
	 */
	private String contrasena;

	/**
	 * Atributo que contiene el estado del cliente.
	 */
	private String estado;


	/*
	 * Constructor
	 */
	/**
	 * M�todo que se encarga de inicializar el cliente.
	 * @param nLogin Login a agregar. nLogin != null && nLogin != "".
	 * @param nContrasena Contrase�a del cliente. nContrasena != null && nContrasena != "".
	 * @param nNombre Nombre del cliente. nNombre != null && nNombre != "".
	 * @param nApellido Apellidos del cliente. nApellido != null && nApellido != "".
	 * @param est Estado de conexi�n del cliente. est == N || est == S.
	 */
	public ClienteRemoto(String nLogin,  String nNombre, String nApellido, String nContrasena, String est)
	{
		login = nLogin;
		contrasena = nContrasena;
		nombre = nNombre;
		apellido = nApellido;
		estado = est;
	}


	//----------------------------------------
	// M�todos
	//----------------------------------------

	/**
	 * Devuelve el login del cliente.
	 * @return Devuelve el login del cliente espec�fico.
	 */
	public String darLogin()
	{
		return login;
	}

	/**
	 * Devuelve la contrase�a del cliente.
	 * @return Devuelve la contrase�a.
	 */
	public String darContrasena()
	{
		return contrasena;
	}

	/**
	 * Devuelve el nombre del cliente.
	 * @return Devuelve el nombre.
	 */
	public String darNombre()
	{
		return nombre;
	}

	/**
	 * Devuelve el apellido del cliente.
	 * @return Devuelve el apellido.
	 */
	public String darApellido()
	{
		return apellido;
	}

	/**
	 * Devuelve el estado del cliente.
	 * @return Devuelve el estado del cliente.
	 */
	public String darEstado()
	{
		return estado;
	}

}
