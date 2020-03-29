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
 * Clase que representa un hilo de ejecuci�n cuando se quiere enviar un correo.
 */
public class ThreadEnviarCorreo extends Thread
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
     * Login de los destinatarios.
     */
    private String loginDestinatarios;
    
    /**
     * Asunto del correo.
     */
    private String asunto;
    
    /**
     * Mensaje del correo.
     */
    private String mensaje;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo hilo para enviar un correo.<br>
     * <b>post: </b> Se inicializaron el cliente y la interfaz principal con los valores dados por par�metro.
     * @param pCliente Clase principal del mundo con la informaci�n del cliente. pCliente != null.
     * @param pPrincipal Ventana principal de la aplicaci�n. pPrincipal != null.
     * @param pLoginDestinatarios Login de los destinatarios del correo. pLoginDestinatarios != null && pLoginDestinatarios != "".
     * @param pAsunto Asunto del correo. pAsunto != null && pAsunto != "".
     * @param pMensaje Mensaje del correo. pMensaje != null && pMensaje != "". 
     */
    public ThreadEnviarCorreo( ClienteCupiEmail pCliente, InterfazCliente pPrincipal, String pLoginDestinatarios, String pAsunto, String pMensaje )
    {
        cliente = pCliente;
        principal = pPrincipal;
        loginDestinatarios = pLoginDestinatarios;
        asunto = pAsunto;
        mensaje = pMensaje;
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que env�a el correo.<br>
     * <b>post: </b> Se envi� el correo.<br>
     */
    public void run( )
    {
        try
        {
            cliente.enviarCorreo( loginDestinatarios, asunto, mensaje );
            principal.actualizarEscribirCorreo( );
        }
        catch (Exception e)
        {
            principal.mostrarMensajeError( e.getMessage( ) );
        }
    }
}
