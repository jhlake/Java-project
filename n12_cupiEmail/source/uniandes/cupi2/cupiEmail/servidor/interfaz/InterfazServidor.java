package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import uniandes.cupi2.cupiEmail.servidor.mundo.*;

/**
 * Clase que representa la interfaz principal del Servidor cupiEmail.
 * @author jlake
 */
public class InterfazServidor extends JFrame{
	
	/*
	 * Atributos de la interfaz
	 */
	/**
	 * Panel de la imagen del Cupi Email.
	 */
	private PanelImagen panelImagen;
	
	/**
	 * Panel con los botones de la extensi�n.
	 */
	private PanelExtension panelExtension;
	
	/**
	 * Panel con la lista de usuarios y sus botones pertinentes.
	 */
	private PanelUsuarios panelUsuarios;
	
	/**
	 * Panel que contiene la informaci�n de un usuario.
	 */
	private PanelInformacion panelInformacion;
	
	/*
	 * Atributos
	 */
	/**
	 * Relaci�n entre la interfaz y el servidor.
	 */
	private Servidor servidor;

	/*
	 * Constructor
	 */
	/**
	 * M�todo que construye todos los elementos de la interfaz.
	 * @param server Servidor cupiEmail
	 */
	public InterfazServidor(Servidor server) 
	{
		servidor = server;
		setLayout(new BorderLayout());
		setTitle("Servidor Cupi EMail");
		setSize(706, 700);
        setResizable(false);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        
        panelImagen = new PanelImagen();
        add(panelImagen, BorderLayout.NORTH);
        
        panelExtension = new PanelExtension(this);
        add(panelExtension, BorderLayout.SOUTH);
        
        panelInformacion = new PanelInformacion(this);
        add(panelInformacion, BorderLayout.EAST);
        
        panelUsuarios = new PanelUsuarios(this);
        add(panelUsuarios, BorderLayout.CENTER); 
	}

	/*
	 * M�todos
	 */
	
	/**
	 * M�todo que devuelve el login de todos los usuarios.
	 * @return ArrayList con el login de todos los usuarios.
	 */
	public ArrayList<String> darLoginsUsuarios()
	{
		return servidor.darAdministrador().darTodosUsuarios();
	}

	/**
	 * M�todo que devuelve el login de los usuarios conectados.
	 * @return ArrayList con el login de los usuarios conectados.
	 */
	public ArrayList<String> darLoginsUsuariosConectados() 
	{
		return servidor.darAdministrador().darUsuariosEstado("s");
	}

	/**
	 * M�todo que actualiza la informaci�n en el panel de informaci�n, busc�ndola en la base de datos.
	 * @param username Usuario del cual se quiere actualizar la informaci�n.
	 */
	public void actualizarInformacion(String username) 
	{
		try {
			ClienteRemoto rem = servidor.darAdministrador().darUsuario(username);
			panelInformacion.actualizarDatos(username, rem.darNombre(), rem.darApellido(), servidor.darCantidadCorreosUsuario(username), servidor.darCantidadCorreosSinLeerUsuario(username));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Main
	 */
	/**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param args Argumentos para la ejecuci�n de la aplicaci�n. En este caso no son necesarios.
     */
	public static void main(String[] args) {
		try
		{
			Servidor s = new Servidor("data/servidor.properties");
			InterfazServidor ventana = new InterfazServidor(s);
			ventana.setVisible(true);
			s.recibirConexiones();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	/*
     * Puntos de Extensi�n
     */ 

    /**
     * M�todo para la extensi�n 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = servidor.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = servidor.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }


}
