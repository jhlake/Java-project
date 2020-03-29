package uniandes.cupi2.cupiEmail.servidor.mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que representa los hilos en donde se atienden a múltiples clientes.
 * @author jlake
 */
public class Atender extends Thread{


	/*
	 * Constantes
	 */
	/**
	 * Constante que representa el separador de comandos.
	 */
	public static final String SEPARADOR_COMANDO = ";;;";

	/**
	 * Constante que representa el separador de parámetros.
	 */
	public static final String SEPARADOR_PARAMETRO = ":::";

	/**
	 * Constante que representa el comando de iniciar sesión.
	 */
	public static final String INICIAR_SESION = "INICIAR_SESION";

	/**
	 * Constante que representa el comando de responder al inicio de sesión.
	 */
	public static final String INICIAR_SESION_OK = "INICIAR_SESION_OK";

	/**
	 * Constante que representa el comando de error.
	 */
	public static final String ERROR = "ERROR";

	/**
	 * Constante que representa el comando de Crear Cuenta.
	 */
	public static final String CREAR_CUENTA = "CREAR_CUENTA";

	/**
	 * Constante que representa el comando de responder a crear cuenta.
	 */
	public static final String CREAR_CUENTA_OK = "CREAR_CUENTA_OK";

	/**
	 * Constante que representa el comando de cerrar sesión.
	 */
	public static final String CERRAR_SESION = "CERRAR_SESION";

	/**
	 * Constante que representa el comando de responder a cerrar sesión.
	 */
	public static final String CERRAR_SESION_OK = "CERRAR_SESION_OK";

	/**
	 * Constante que representa el comando de Marcar como leído un correo.
	 */
	public static final String MARCAR_COMO_LEIDO = "MARCAR_COMO_LEIDO";

	/**
	 * Constante que representa el comando de responder a Marcar como leído.
	 */
	public static final String MARCAR_COMO_LEIDO_OK = "MARCAR_COMO_LEIDO_OK";

	/**
	 * Constante que representa el comando de consultar correos.
	 */
	public static final String CONSULTAR_CORREOS = "CONSULTAR_CORREOS";

	/**
	 * Constante que representa el comando de responder a consultar correos.
	 */
	public static final String CONSULTAR_CORREOS_OK = "CONSULTAR_CORREOS_OK";

	/**
	 * Constante que representa el comando de responder con la información de un correo.
	 */
	public static final String INFO_CORREOS = "INFO_CORREOS";

	/**
	 * Constante que representa el comando de responder con la cantidad de correos de un usuario.
	 */
	public static final String CANTIDAD_CORREOS = "CANTIDAD_CORREOS";

	/**
	 * Constante que representa el comando de enviar un correo.
	 */
	public static final String ENVIAR_CORREO = "ENVIAR_CORREO";

	/**
	 * Constante que representa el comando de responder a enviar un correo.
	 */
	public static final String ENVIAR_CORREO_OK = "ENVIAR_CORREO_OK";

	/*
	 * Atributos
	 */
	/**
	 * El canal usado para comunicarse con el cliente
	 */
	private Socket socketCliente;

	/**
	 * El flujo de escritura conectado con el cliente
	 */
	private PrintWriter outC;

	/**
	 * El flujo de lectura conectado con el cliente
	 */
	private BufferedReader inC;

	/**
	 *  El cliente al cual este hilo en particular está atendiendo.
	 */
	protected ClienteRemoto cliente;

	/**
	 * Estado del cliente, conectado o no.
	 */
	private boolean logged;

	/**
	 * Es el administrador sobre la base de datos.
	 */
	private AdminDB admindb;

	/*
	 * Constructor
	 */
	/**
	 * Método que se encarga de crear el hilo.
	 * @param canal Canal por el cual se comunica el cliente y el servidor. canal != null
	 * @param admin Administrador de la base de datos. admin != null.
	 * @throws IOException Si sucede algún error en el canal de entrada o salida.
	 */
	public Atender(Socket canal, AdminDB admin) throws IOException
	{
		admindb = admin;

		outC = new PrintWriter( canal.getOutputStream( ), true );
		inC = new BufferedReader( new InputStreamReader( canal.getInputStream( ) ) );
		socketCliente = canal;
		logged = true;
	}

	/**
	 * Devuelve el canal por el cual se comunica el cliente y el servidor.
	 * @return Retorna el socket de conexión.
	 */
	public Socket darSocketCliente()
	{
		return socketCliente;
	}

	/**
	 * Devuelve el administrador de la base de datos.
	 * @return Retorna el administrador de la base de datos.
	 */
	public AdminDB darAdmin()
	{
		return admindb;
	}

	/**
	 * Devuelve si el usuario está o no conectado en este momento.
	 * @return True si el usuario está conectado, o false en caso contrario.
	 */
	public boolean estaConectado()
	{
		return logged;
	}


	/**
	 * Inicia la conección con el usuario. <br>
	 * El ciclo de vida de la conexión tiene 2 etapas: <br>
	 * 1. Esperar una entrada por parte del usuario, de las varias opciones que tiene. <br>
	 * 2. Desconectarse. <br>
	 */
	public void run()
	{

		try
		{
			while(logged)
			{
				String[] data = inC.readLine().split(SEPARADOR_COMANDO);
				switch(data[0])
				{
				case INICIAR_SESION:
					String[] parIni = data[1].split(SEPARADOR_PARAMETRO);
					outC.println(iniciarSesion(parIni[0], parIni[1]));
					break;
				case CERRAR_SESION:
					outC.println(cerrarSesion());
					break;
				case CREAR_CUENTA:
					String[] parCrear = data[1].split(SEPARADOR_PARAMETRO);
					outC.println(crearCuenta(parCrear[0], parCrear[1], parCrear[2], parCrear[3]));
					break;
				case MARCAR_COMO_LEIDO:
					String[] parMarcar = data[1].split(SEPARADOR_PARAMETRO);
					outC.println(marcarLeido(parMarcar[0], parMarcar[1]));
					break;
				case CONSULTAR_CORREOS:
					consultarCorreos();
					break;
				case ENVIAR_CORREO:
					String[] parEnviar = data[1].split(SEPARADOR_PARAMETRO);
					outC.println(send(parEnviar[0], parEnviar[1], parEnviar[2]));
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite consultar los correos del cliente.
	 */
	private void consultarCorreos() 
	{
		String numeroCorreos = admindb.darCantidadCorreosUsuario(cliente.darLogin());
		if(numeroCorreos.startsWith(ERROR))
		{
			outC.println(numeroCorreos);
		}
		else
		{
			outC.println(CANTIDAD_CORREOS+SEPARADOR_COMANDO + numeroCorreos);
			for(int i = 0; i < Integer.parseInt(numeroCorreos); i++)
			{
				outC.println(admindb.darCorreosUsuario(cliente.darLogin(), i));	
			}
			outC.println(CONSULTAR_CORREOS_OK);
		}
	}
	/**
	 * Método que permite enviar al cliente un correo.
	 * @param destinatarios Personas a las cuales se les está enviando el correo, separados por ",".
	 * @param mensaje Mensaje que se le está enviando a éstas personas.
	 * @param asunto Asunto del mensaje en cuestión.
	 * @return Devuelve una respuesta que se le envía al cliente, correspondiente a lo sucedido.
	 */
	private String send(String destinatarios, String mensaje, String asunto) 
	{
		String ans = admindb.send(destinatarios, mensaje, asunto, cliente.darLogin());
		return ans;
	}
	/**
	 * Método que permite marcar como leído un mensaje.
	 * @param fecha Fecha en la cual se envió el mensaje.
	 * @param asunto Asunto del mensaje.
	 * @return Devuelve la respuesta pertinente.
	 */
	private String marcarLeido(String fecha, String asunto) {
		String ans = admindb.marcarLeido(fecha, asunto);
		return ans;
	}

	/**
	 * Método que se encanrga de crear la cuenta del usuario. <br>
	 * <b> Pos: </b> Se creó la cuenta del usuario si no existía antes, y se marcó como usuario siendo atendido actuálmente. <br>
	 * @param username Usuario con el cual se quiere crear la cuenta.
	 * @param nombres Nombre del usuario que crea la cuenta.
	 * @param apellidos Apellidos del usuario que crea la cuenta.
	 * @param contrasena Constraseña para éste usuario.
	 * @return Devuelve la respuesta pertinente, si se creo o no la cuenta.
	 * @throws SQLException Si falla la creación de la cuenta en algún punto.
	 */
	private String crearCuenta(String username, String nombres, String apellidos, String contrasena) throws SQLException {
		String ans = admindb.crearCuenta(username, nombres, apellidos, contrasena);
		cliente = admindb.darUsuario(username);
		return ans;
	}

	/**
	 * Método que cierra sesión del usuario. <br>
	 * <b> Pos: </b> Se desconectó al usuario del servidor. 
	 * @return Devuelve el mensaje pertinente.
	 */
	private String cerrarSesion() {
		logged =false;
		String ans= admindb.cerrarSesion(cliente.darLogin());
		return ans;
	}
	/**
	 * Método que inicia la sesión del usuario. <br>
	 * <b> Pos: </b> El usuario queda conectado y asignado a éste hilo.
	 * @param username Usuario que se quiere conectar.
	 * @param pass Contraseña a verificar.
	 * @return Devuelve mensaje existoso si se logró conectar, o no exitoso en caso contrario.
	 * @throws SQLException Si sucede algún error mientras se está conectando al usuario.
	 */
	private String iniciarSesion(String username, String pass) throws SQLException {
		String ans = admindb.iniciarSesion(username, pass);
		if(!ans.startsWith(ERROR))
		{
			cliente = admindb.darUsuario(username);	
		}
		return ans;

	}
}