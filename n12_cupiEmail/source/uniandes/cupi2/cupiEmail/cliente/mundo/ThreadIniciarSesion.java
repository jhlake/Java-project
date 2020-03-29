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

import uniandes.cupi2.cupiEmail.cliente.interfaz.InterfazCliente;

/**
 * Clase que representa un hilo de ejecuci�n cuando se quiere iniciar sesi�n.
 */
public class ThreadIniciarSesion extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cliente de CupiEmail.
     */
    private ClienteCupiEmail cliente;
    
    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazCliente principal;
    
    /**
     * Login ingresado por el usuario.
     */
    private String login;
    
    /**
     * Contrasena ingresada por el usuario.
     */
    private String contrasena;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Crea un nuevo hilo para iniciar sesi�n en el servidor.<br>
     * <b>post: </b> Se inicializaron el cliente, la interfaz principal, el login y la contrase�a con los valores dados por par�metro.
     * @param pCliente Clase principal del mundo con la informaci�n del cliente. pCliente != null.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     * @param pLogin Login de usuario. pLogin != null && pLogin != "".
     * @param pContrasena La contrase�a. pContrasena != null && pContrasena != "".
     */
    public ThreadIniciarSesion (ClienteCupiEmail pCliente, InterfazCliente pPrincipal, String pLogin, String pContrasena)
    {
        cliente = pCliente;
        principal = pPrincipal;
        login = pLogin;
        contrasena = pContrasena;
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que realiza el inicio de sesi�n.<br>
     * <b>post: </b> Se inici� sesi�n en el servidor.<br>
     */
    public void run( )
    {
        try
        {
            cliente.iniciarSesion( login, contrasena );
            principal.actualizarIniciarSesion( );
        }
        catch (Exception e)
        {
            principal.mostrarMensajeError( e.getMessage( ) );
        }
    }
}
