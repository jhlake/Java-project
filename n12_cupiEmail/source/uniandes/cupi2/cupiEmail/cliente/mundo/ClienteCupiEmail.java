/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiEmail
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiEmail.cliente.mundo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Clase que representa un cliente de CupiEmail.
 */
public class ClienteCupiEmail
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el separador de un comando.
     */
    public static final String SEPARADOR_COMANDO = ";;;";

    /**
     * Constante que representa el separador de los par�metros.
     */
    public static final String SEPARADOR_PARAMETROS = ":::";

    /**
     * Constante que representa el comando para crear un cuenta.
     */
    public static final String CREAR_CUENTA = "CREAR_CUENTA";

    /**
     * Constante que representa el comando para indicar que la cuenta fue creada.
     */
    public static final String CREAR_CUENTA_OK = "CREAR_CUENTA_OK";

    /**
     * Constante que representa el comando para iniciar sesi�n.
     */
    public final static String INICIAR_SESION = "INICIAR_SESION";

    /**
     * Constante que representa el comando para indicar que se ha iniciado sesi�n.
     */
    public final static String INICIAR_SESION_OK = "INICIAR_SESION_OK";

    /**
     * Constante que representa el comando para cerrar sesi�n.
     */
    public final static String CERRAR_SESION = "CERRAR_SESION";

    /**
     * Constante que representa el comando para indicar que se ha cerrado sesi�n.
     */
    public final static String CERRAR_SESION_OK = "CERRAR_SESION_OK";

    /**
     * Constante que representa el comando para marcar un correo como le�do,
     */
    public final static String MARCAR_COMO_LEIDO = "MARCAR_COMO_LEIDO";

    /**
     * Constante que representa el comando para indicar que un correo se ha marcado como le�do.
     */
    public final static String MARCAR_COMO_LEIDO_OK = "MARCAR_COMO_LEIDO_OK";

    /**
     * Constante que representa el comando para consultar los correos.
     */
    public final static String CONSULTAR_CORREOS = "CONSULTAR_CORREOS";

    /**
     * Constante que representa el comando para indicar la cantidad de correos.
     */
    private static final String CANTIDAD_CORREOS = "CANTIDAD_CORREOS";

    /**
     * Constante que representa el comando para indicar que se han consultado los correos.
     */
    public final static String CONSULTAR_CORREOS_OK = "CONSULTAR_CORREOS_OK";

    /**
     * Constante que representa el comando para indicar la informaci�n de un correo.
     */
    public final static String INFO_CORREO = "INFO_CORREO";

    /**
     * Constante que representa el comando para enviar un nuevo correo.
     */
    public final static String ENVIAR_CORREO = "ENVIAR_CORREO";

    /**
     * Constante que representa el comando para indicar que se ha enviado un correo.
     */
    public static final String ENVIAR_CORREO_OK = "ENVIAR_CORREO_OK";

    /**
     * Constante que representa un error en la comunicaci�n.
     */
    public final static String ERROR = "ERROR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Login del usuario.
     */
    private String loginUsuario;

    /**
     * Canal de comunicaci�n con el servidor.
     */
    private Socket canal;

    /**
     * Flujo que lee los datos que llegan del servidor a trav�s del socket.
     */
    private BufferedReader in;

    /**
     * Flujo que env�a los datos al servidor a trav�s del socket.
     */
    private PrintWriter out;

    /**
     * Lista de correos del cliente.
     */
    private ArrayList correosElectronicos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un cliente de CupiEmail.<br>
     * <b> post: </b> Se cre� la conexi�n con el servidor con el archivo de propiedades dado por par�metro.
     * @param pArchivoConfig Ruta del archivo con la informaci�n de configuraci�n del servidor. pArchivoConfig != null && pArchivoConfig != "".
     * @throws CupiEmailClienteException Si se presenta un error al conectarse con el servidor.
     */
    public ClienteCupiEmail( String pArchivoConfig ) throws CupiEmailClienteException
    {
        try
        {
            FileInputStream fis = new FileInputStream( pArchivoConfig );
            Properties p = new Properties( );
            p.load( fis );
            fis.close( );

            String dir = p.getProperty( "servidor.dirIp" );
            int puerto = Integer.parseInt( p.getProperty( "servidor.puerto" ) );
            canal = new Socket( dir, puerto );
            out = new PrintWriter( canal.getOutputStream( ), true );
            in = new BufferedReader( new InputStreamReader( canal.getInputStream( ) ) );
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( "Se presentaron problemas con la conexi�n al servidor. " + e.getMessage( ) );
        }
        correosElectronicos = new ArrayList( );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la sesi�n de usuario en el servidor.<br>
     * <b> post: </b> Inici� sesi�n en el servidor.
     * @param pLoginUsuario Login del usuario. pLoginUsuario != null && pLoginUsuario != "".
     * @param pContrasena Contrase�a del usuario. pContrasena != null && pContrasena != "".
     * @throws CupiEmailClienteException Si se presentan problemas al iniciar sesi�n.
     */
    public void iniciarSesion( String pLoginUsuario, String pContrasena ) throws CupiEmailClienteException
    {

        try
        {
            String mensaje = INICIAR_SESION + SEPARADOR_COMANDO + pLoginUsuario + SEPARADOR_PARAMETROS + pContrasena;
            out.println( mensaje );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( INICIAR_SESION_OK ) )
            {
                loginUsuario = pLoginUsuario;
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Crear una cuenta en el servidor.<br>
     * <b> post: </b> Se cre� la cuenta en el servidor.
     * @param pLogin Login del usuario. pLogin != null && pLogin != "".
     * @param pNombre Nombre del usuario. pNombre != null && pNombre != "".
     * @param pApellidos Apellidos del usuario. pApellidos != null && pApellidos != "".
     * @param pContrasena Contrase�a del usuario. pContrasena != null && pContrasena !="".
     * @throws CupiEmailClienteException Si hay problemas en crear la cuenta.
     */
    public void crearCuenta( String pLogin, String pNombre, String pApellidos, String pContrasena ) throws CupiEmailClienteException
    {
        try
        {
            String linea = CREAR_CUENTA + SEPARADOR_COMANDO + pLogin + SEPARADOR_PARAMETROS + pNombre + SEPARADOR_PARAMETROS + pApellidos + SEPARADOR_PARAMETROS + pContrasena;
            out.println( linea );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( CREAR_CUENTA_OK ) )
            {
                loginUsuario = pLogin;
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Cierra la sesi�n del usuario.<br>
     * <b> post: </b> Se cerr� sesi�n.
     * @throws CupiEmailClienteException Si hay problema al cerrar sesi�n.
     */
    public void cerrarSesion( ) throws CupiEmailClienteException
    {
        try
        {
            out.println( CERRAR_SESION );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( CERRAR_SESION_OK ) )
            {
                loginUsuario = null;
                correosElectronicos = new ArrayList( );
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Consulta la lista de correosElectronicos en el servidor y los agrega a correosElectronicos.<br>
     * <b> post: </b> Se actualiz� la lista de correos.
     * @throws CupiEmailClienteException Si hay problema al consultar los correos.
     */
    public void consultarCorreos( ) throws CupiEmailClienteException
    {
        try
        {
            out.println( CONSULTAR_CORREOS );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( CANTIDAD_CORREOS ) )
            {
                String cantidadStr = partesRespuesta[ 1 ];
                int cantidad = Integer.parseInt( cantidadStr );
                correosElectronicos = new ArrayList( );
                for( int i = 0; i < cantidad; i++ )
                {
                    String nuevoCorreo = in.readLine( );
                    String[] infoCorreo = nuevoCorreo.split( SEPARADOR_COMANDO );
                    if( !infoCorreo[ 0 ].equals( INFO_CORREO ) )
                    {
                        throw new CupiEmailClienteException( "Error al consultar los correos." );
                    }
                    else
                    {
                        String[] partesCorreo = infoCorreo[ 1 ].split( SEPARADOR_PARAMETROS );
                        boolean leido = false;
                        if( partesCorreo[ 4 ].equals( "S" ) )
                        {
                            leido = true;
                        }
                        CorreoElectronico correo = new CorreoElectronico( partesCorreo[ 0 ], partesCorreo[ 1 ], partesCorreo[ 2 ], partesCorreo[ 3 ], leido );
                        correosElectronicos.add( correo );
                    }
                }
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Env�a un correo.<br>
     * <b> post: Se envi� el correo.</b>
     * @param pLoginDestinatarios Logins de los destinatarios del correo. pLoginDestinatarios != null && pLoginDestinatarios != "".
     * @param pAsunto Asunto del correo. pAsunto != null && pAsunto != "".
     * @param pMensaje Mensaje del correo. pMensaje != null && pMensaje != "".
     * @throws CupiEmailClienteException Si hay problemas al escribir el correo.
     */
    public void enviarCorreo( String pLoginDestinatarios, String pAsunto, String pMensaje ) throws CupiEmailClienteException
    {
        try
        {
            String mensaje = ENVIAR_CORREO + SEPARADOR_COMANDO + pLoginDestinatarios + SEPARADOR_PARAMETROS + pAsunto + SEPARADOR_PARAMETROS + pMensaje;
            out.println( mensaje );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( ENVIAR_CORREO_OK ) )
            {
                // No pasa nada
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Marca el correo dado por par�metro como le�do.<br>
     * <b> post: </b> El correo dado por par�metro se marc� como le�do.
     * @param pCorreo Correo electr�nico que se desea marcar como le�do. pCorreo != null.
     * @throws CupiEmailClienteException Si hay problemas al marcar como le�do el correo.
     */
    public void marcarComoLeido( CorreoElectronico pCorreo ) throws CupiEmailClienteException
    {
        try
        {
            String mensaje = MARCAR_COMO_LEIDO + SEPARADOR_COMANDO + pCorreo.darFechaEnvio( ) + SEPARADOR_PARAMETROS + pCorreo.darAsunto( );
            out.println( mensaje );
            String respuesta = in.readLine( );
            String[] partesRespuesta = respuesta.split( SEPARADOR_COMANDO );
            if( partesRespuesta[ 0 ].equals( ERROR ) )
            {
                throw new CupiEmailClienteException( partesRespuesta[ 1 ] );
            }
            else if( partesRespuesta[ 0 ].equals( MARCAR_COMO_LEIDO_OK ) )
            {
                pCorreo.macarComoLeido( );
            }
        }
        catch( Exception e )
        {
            throw new CupiEmailClienteException( e.getMessage( ) );
        }
    }

    /**
     * Retorna el login del usuario.
     * @return Login del usuario.
     */
    public String darLogin( )
    {
        return loginUsuario;
    }

    /**
     * Retorna la lista de correos.
     * @return Lista de correos.
     */
    public ArrayList darCorreos( )
    {
        return correosElectronicos;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv: </b><br>
     * escuchaCambios != null <br>
     * canal != null <br>
     * correosElectronicos != null <br>
     */
    private void verificarInvariante( )
    {
        assert ( canal != null ) : "El canal no puede ser null";
        assert ( correosElectronicos != null ) : "La lista de correos no puede ser null";
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
}
