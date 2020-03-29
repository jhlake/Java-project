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

/**
 * Clase que representa un usuario en el sistema.
 */
public class Usuario
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Login de usuario.
     */
    private String login;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellidos del usuario.
     */
    private String apellidos;

    /**
     * Contrase�a del usuario.
     */
    private String contrasena;

    /**
     * Total de correos.
     */
    private int totalCorreos;

    /**
     * Total de correos sin leer.
     */
    private int totalCorreosSinLeer;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo usuario con los valores dados por par�metro.<br>
     * <b> post: </b> El login, nombre, apellidos, contrase�a, total de mensaje y total de mensajes sin leer se inicializaron con los valores dados por par�metro.
     * @param pLogin Login del usuario. pLogin != null && pLogin != "".
     * @param pNombre Nombre del usuario. pNombre != null && pNombre != "".
     * @param pApellidos Apellidos del usuario. pApellidos != null && pApellidos != "".
     * @param pContrasena Contrase�a del usuario. pContrasena != null && pContrasena != "".
     * @param pTotalCorreos Total de correos. pTotalCorreos >= 0.
     * @param pTotalCorreosSinLeer Total de correos sin leer. pTotalCorreosSinLeer >= 0.
     */
    public Usuario( String pLogin, String pNombre, String pApellidos, String pContrasena, int pTotalCorreos, int pTotalCorreosSinLeer )
    {
        login = pLogin;
        nombre = pNombre;
        apellidos = pApellidos;
        contrasena = pContrasena;
        totalCorreos = pTotalCorreos;
        totalCorreosSinLeer = pTotalCorreosSinLeer;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el login del usuario.
     * @return Login del usuario.
     */
    public String darLogin( )
    {
        return login;
    }

    /**
     * Retorna el nombre del usuario.
     * @return Nombre del usuario.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna los apellidos del usuario.
     * @return Apellidos del usuario.
     */
    public String darApellidos( )
    {
        return apellidos;
    }

    /**
     * Retorna la contrase�a del usuario.
     * @return Contrase�a del usuario.
     */
    public String darContrasena( )
    {
        return contrasena;
    }

    /**
     * Retorna el total de correos del usuario.
     * @return Total de correos del usuario.
     */
    public int darTotalCorreos( )
    {
        return totalCorreos;
    }

    /**
     * Retorna el total de correos sin leer del usuario.
     * @return Total de correos sin leer del usuario.
     */
    public int darTotalMensajesSinLeer( )
    {
        return totalCorreosSinLeer;
    }

    /**
     * Representaci�n en cadena de caracteres del usuario.
     * @return Login del usuario.
     */
    public String toString( )
    {
        return login;
    }

}