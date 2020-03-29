package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Clase que representa la información de un usuario de CupiEmail.
 * @author jlake
 */
public class PanelInformacion extends JPanel {

	/*
	 * Atributos
	 */
	/**
	 * Atributo que representa la clase principal del servidor cupiEmail.
	 */
	private InterfazServidor principal;

	/*
	 * Atributos de la interfaz
	 */
	/**
	 * Etiqueta para el login del cliente
	 */
	private JLabel lblLogin;

	/**
	 * Etiqueta para el nombre del cliente.
	 */
	private JLabel lblNombre;

	/**
	 * Etiqueta para el apellido del cliente.
	 */
	private JLabel lblApellido;

	/**
	 * Etiqueta para los correos totales.
	 */
	private JLabel lblCorreosTot;

	/**
	 * Etiequeta para los correos no leidos.
	 */
	private JLabel lblCorreosNoLeidosTot;

	/**
	 * Etiqueta que se actualiza con el login de los clientes.
	 */
	private JLabel ansLogin;

	/**
	 * Etiqueta que se actualiza con el nombre de los clientes.
	 */
	private JLabel ansNombre;

	/**
	 * Etiqueta que se actualiza con el apellido de los clientes.
	 */
	private JLabel ansApellido;

	/**
	 * Etiqueta que se actualiza con el total de correos de los clientes.
	 */
	private JLabel ansCorreosTot;

	/**
	 * Etiqueta que se actualiza con el total de correos no leidos de los clientes.
	 */
	private JLabel ansCorreosNoLeidosTot;


	/*
	 * Constructor
	 */
	/**
	 * Método constructor del panel. Pone por defecto los valores predeterminados.
	 * @param pPrincpal Panel padre.
	 */
	public PanelInformacion(InterfazServidor pPrincpal)
	{
		setLayout(new GridLayout(5, 2));
		setBorder(new TitledBorder("Información del Usuario"));

		LineBorder borderLabels = new LineBorder( Color.GRAY);

		lblLogin = new JLabel("Login");
		add(lblLogin);

		ansLogin = new JLabel("");
		ansLogin.setBorder(borderLabels);
		add(ansLogin);

		lblNombre = new JLabel("Nombres");
		add(lblNombre);

		ansNombre = new JLabel("");
		ansNombre.setBorder(borderLabels);
		add(ansNombre);

		lblApellido = new JLabel("Apellidos");
		add(lblApellido);

		ansApellido = new JLabel("");
		ansApellido.setBorder(borderLabels);
		add(ansApellido);

		lblCorreosTot = new JLabel("Total correos");
		add(lblCorreosTot);

		ansCorreosTot = new JLabel("");
		ansCorreosTot.setBorder(borderLabels);
		add(ansCorreosTot);

		lblCorreosNoLeidosTot = new JLabel("Total correos sin leer");
		add(lblCorreosNoLeidosTot);

		ansCorreosNoLeidosTot = new JLabel("");
		ansCorreosNoLeidosTot.setBorder(borderLabels);
		add(ansCorreosNoLeidosTot);

	}

	/*
	 * Métodos
	 */
	/**
	 * Método que actualiza la información del usuario seleccionado.
	 * @param login Login del usuario seleccionado. login != null && login != "".
	 * @param nombres Nombres del usuario seleccionado. nombres != null && nombres != "".
	 * @param apellidos Apellidos del usuario seleccionado. apellidos != null && apellidos != "".
	 * @param correosTot Número de correos que tiene el usuario en su bandeja. correos Tot >= 0.
	 * @param correosSinLeerTot Número de correos sin leer que tiene el usuario en su bandeja. correosSinLeerTot >= 0
	 */
	public void actualizarDatos(String login, String nombres, String apellidos, int correosTot, int correosSinLeerTot)
	{
		ansLogin.setText(login);
		ansNombre.setText(nombres);
		ansApellido.setText(apellidos);
		ansCorreosTot.setText(""+ correosTot);
		ansCorreosNoLeidosTot.setText("" + correosSinLeerTot);
		repaint();
	}
}
