package uniandes.cupi2.cupiEmail.servidor.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Clase que representa la lista de usuarios en el panel de usuarios.
 * @author jlake
 */
public class PanelLista extends JPanel implements ListSelectionListener{

	/*
	 * Atributos
	 */
	/**
	 * Referencia al panel de Usuarios.
	 */
	private PanelUsuarios principal;
	
	private JList listaUsuarios;
	
	private JScrollPane scrollUsuarios;
	/*
	 * Constructor
	 */
	/**
	 * Método que inicializa el panel con su padre.
	 * @param panelUsuarios Panel Padre
	 */
	public PanelLista(PanelUsuarios panelUsuarios) 
	{
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Usuarios"));
		principal = panelUsuarios;
		
		listaUsuarios = new JList( );
        listaUsuarios.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaUsuarios.addListSelectionListener( this );
        
        scrollUsuarios = new JScrollPane( listaUsuarios );
        scrollUsuarios.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollUsuarios.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollUsuarios.setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 3, 3 ), new LineBorder( Color.BLACK, 1 ) ) );
        add(scrollUsuarios, BorderLayout.CENTER);
	}

	/* 
	 * Métodos
	 */
	/**
	 * Método que se encarga de actualizar la información del cuadro de información al cambiar de usuario.
	 * @param event Evento que sucede al cambiar de índice en la lista.
	 */
	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (!event.getValueIsAdjusting()) 
		{
			principal.actualizarInformacion(listaUsuarios.getSelectedValue().toString());
		}
	}

	/**
	 * Método que actualiza la lista de reservas.
	 * @param logins Lista de logins de los usuarios. logins !=null
	 */
	public void actualizar(ArrayList<String> logins) {
		listaUsuarios.removeAll( );
        listaUsuarios.setListData(logins.toArray( ) );
        repaint();
	}

}
