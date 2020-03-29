package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Clase que contiene los botones para cambiar entre listas y tambi�n la lista de los usuarios como tal.
 * @author jlake
 */
public class PanelUsuarios extends JPanel implements ActionListener{


	/*
	 * Constantes
	 */
	/**
	 * Representa la acci�n de ver a todos los usuarios.
	 */
	public final static String VER_TODOS = "Ver todos los usuarios";

	/**
	 * Representa la acci�n de ver s�lo los usuarios conectados.
	 */
	public final static String VER_CONECTADOS = "Ver solo usuarios conectados";

	/*
	 * Atributos
	 */
	/**
	 * Referencia al panel principal del servidor cupiEmail.
	 */
	private InterfazServidor principal;

	/*
	 * Atributos de la interfaz
	 */
	/**
	 * Bot�n que muestra todos los usuarios.
	 */
	private JButton botonTodos;

	/**
	 * Bot�n que muestra todos los usuarios conectados.
	 */
	private JButton botonConectados;

	/**
	 * Lista de usuarios mostrados en un JList.
	 */
	private PanelLista panelLista;

	/*
	 * Constructor
	 */
	/**
	 * M�todo que inicializa el panel de usuarios y la lista de usuarios.
	 * @param pPrincipal Panel padre
	 */
	public PanelUsuarios(InterfazServidor pPrincipal)
	{
		principal = pPrincipal;
		setLayout(new BorderLayout());

		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(1, 2));

		botonTodos = new JButton(VER_TODOS);
		botonTodos.setActionCommand(VER_TODOS);
		botonTodos.addActionListener(this);
		botones.add(botonTodos);

		botonConectados = new JButton(VER_CONECTADOS);
		botonConectados.setActionCommand(VER_CONECTADOS);
		botonConectados.addActionListener(this);
		botones.add(botonConectados);

		add(botones, BorderLayout.NORTH);

		panelLista = new PanelLista(this);
		add(panelLista, BorderLayout.CENTER);
	}

	/*
	 * M�todos
	 */
	/**
	 * M�todo que actualiza la lista de usuarios para mostrarlos todos.
	 */
	public void verTodosUsuarios()
	{
			ArrayList<String> ans = principal.darLoginsUsuarios();
			panelLista.actualizar(ans);
	}

	/**
	 * M�todo que actualiza la lista de usuarios para mostrar s�lo aquellos conectados.
	 */
	public void verSoloUsuariosConectados()
	{
			ArrayList<String> ans = principal.darLoginsUsuariosConectados();
			panelLista.actualizar(ans);
	}

	/**
	 * M�todo que actualiza la informaci�n en el panel de usuarios.
	 * @param username Usuario seleccionado en la lista de Usuarios. username != nulll && es un usuario v�lido.
	 */
	public void actualizarInformacion(String username) 
	{
		principal.actualizarInformacion(username);

	}

	/**
	 * Manejo de los eventos de los botones.
	 * @param e Acci�n que gener� el evento. e != null.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand( );
		switch(command)
		{
		case VER_TODOS:
			verTodosUsuarios();
			break;
		case VER_CONECTADOS:
			verSoloUsuariosConectados();
			break;
		}
	}
}
