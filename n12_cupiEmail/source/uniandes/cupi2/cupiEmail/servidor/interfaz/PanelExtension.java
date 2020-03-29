package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Clase que representa el panel de la extensión en el servidor cupiEmail.
 * @author jlake
 */
public class PanelExtension extends JPanel implements ActionListener{

	/*
	 * Constantes
	 */
	/**
	 * Constante que representa la opción 1 de la extensión.
	 */
	public final static String OPCIONUNO = "Opción 1";
	/**
	 * Constante que representa la opción 2 de la extensión.
	 */
	public final static String OPCIONDOS = "Opción 2";

	/*
	 * Atributos
	 */
	/**
	 * Atributo que representa la clase principal de la interfaz.
	 */
	private InterfazServidor principal;
	
	/*
	 * Atributos de la interfaz
	 */
	
	/**
	 * Atributo que representa el botón de la opción 1.
	 */
	private JButton botonOpcionUno;
	
	/**
	 * Atributo que representa el botón de la opción 2.
	 */
	private JButton botonOpcionDos;

	/*
	 * Constructor
	 */

	/**
     * Método que construye el panel con los botones, y los inicializa.
     * @param pPrincipal Componente padre. pPrincipal !=null
     */
	public PanelExtension(InterfazServidor pPrincipal)
	{
		principal = pPrincipal;

		setLayout(new GridLayout(1, 2));
		setBorder(new TitledBorder("Opciones"));

		botonOpcionUno = new JButton(OPCIONUNO);
		botonOpcionUno.setActionCommand(OPCIONUNO);
		botonOpcionUno.addActionListener(this);
		add(botonOpcionUno);

		botonOpcionDos = new JButton(OPCIONDOS);
		botonOpcionDos.setActionCommand(OPCIONDOS);
		botonOpcionDos.addActionListener(this);
		add(botonOpcionDos);


	}

	/*
	 * Métodos
	 */
	/**
     * Manejo de los eventos de los botones.
     * @param e Acción que generó el evento. e != null.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand( );
		switch(command)
		{
		case OPCIONUNO:
			principal.reqFuncOpcion1( );
			break;
		case OPCIONDOS:
			principal.reqFuncOpcion2( );
			break;

		}
	}
}
