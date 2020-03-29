package uniandes.cupi2.cupiEmail.servidor.mundo;

/**
 * Clase que representa un correo en el cupiEmail
 * @author jlake
 */
public class CorreoRemoto {

	/*
	 * Atributos
	 */
	/**
	 * Atributo que representa el login del remitente.
	 */
	private String loginRemitente;

	/**
	 * Atributo que representa el login del destinatario.
	 */
	private String loginDestinatario;

	/**
	 * Atributo que contiene la fecha de envio.
	 */
	private String fecha;

	/**
	 * Atributo que contiene el asunto del correo.
	 */
	private String asunto;

	/**
	 * Atributo que contiene el contenido del mensaje.
	 */
	private String mensaje;
	
	/**
	 * Atributo que contiene el estado del correo, si está o no leído.
	 */
	private String leido;


	/*
	 * Constructor
	 */
	/**
	 * Método que se encarga de inicializar el cliente.
	 * @param nLoginRemitente Login del remitente. nLoginRemitente != null && nLoginRemitente != "".
	 * @param nLoginDestinatario Login del destinatario. nLoginDestinatario != null && nLoginDestinatario != "".
	 * @param nFecha Fecha que se envió el correo. nFecha != null && nFecha != "".
	 * @param nAsunto Asunto del correo. nAsunto != null && nAsunto != "".
	 * @param nMensaje Contenido del correo. nMensaje != null && nAsunto != "".
	 * @param est Estado de conexión del cliente. est == N || est == S.
	 */
	public CorreoRemoto(String nLoginRemitente,  String nLoginDestinatario, String nFecha, String nAsunto, String nMensaje, String est)
	{
		loginRemitente = nLoginRemitente;
		loginDestinatario = nLoginDestinatario;
		fecha = nFecha;
		asunto = nAsunto;
		mensaje = nMensaje;
		leido = est;
	}


	//----------------------------------------
	// Métodos
	//----------------------------------------

	/**
	 * Devuelve el login del remitente.
	 * @return Devuelve el login del cliente remitente.
	 */
	public String darLoginRemitente()
	{
		return loginRemitente;
	}
	/**
	 * Devuelve el login del cliente destinatario.
	 * @return Devuelve el login del cliente destinatario.
	 */
	public String darLoginDestinatario()
	{
		return loginDestinatario;
	}

	/**
	 * Devuelve la fecha del correo.
	 * @return Devuelve la fecha en que se envió el correo.
	 */
	public String darFecha()
	{
		return fecha;
	}

	/**
	 * Devuelve el asunto del correo.
	 * @return Devuelve el asunto del correo específico.
	 */
	public String darAsunto()
	{
		return asunto;
	}

	/**
	 * Devuelve el contenido del mensaje.
	 * @return Devuelve el mensaje contenido.
	 */
	public String darMensaje()
	{
		return mensaje;
	}

	/**
	 * Devuelve el estado del correo.
	 * @return Devuelve el estado del correo.
	 */
	public String darLeido()
	{
		return leido;
	}

}
