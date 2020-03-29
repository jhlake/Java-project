package uniandes.cupi2.cupiMuseo.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Esta clase representa al panel de opciones de abajo de la interfaz.
 * @author jlake
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    /*
     * Constantes
     */
    /**
     * Constante que representa el comando para buscar obra por nombre.
     */
    public final static String BUSCAR = "Buscar por nombre";

    /**
     * Constante que representa el comando para accionar el método 1 de la extensión.
     */
    private final static String OPCIONUNO = "Opción 1";

    /**
     * Constante que representa el comando para accionar el método 2 de la extensión.
     */
    private final static String OPCIONDOS = "Opción 2";
    
    private final static String BUSCAR_POSICION = "Buscar posición";

    /*
     * Atributos
     */
    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupiMuseo principal;

    /*
     * Atributos de la interfaz.
     */

    /**
     * Botón que permite al usuario buscar por nommbre.
     */
    private JButton nameSearch;

    /**
     * Botón para accionar el método de extensión 1.
     */
    private JButton opcion1;

    /**
     * Botón para accionar el método de extensión 2.
     */
    private JButton opcion2;
    
    private JButton btnPos;
    
    private JTextField txtPosicion;

    /**
     * Método constructor del panel de opciones.
     * @param pPrincipal La ventana principal de la aplicación. Principal !=null.
     */
    public PanelOpciones(InterfazCupiMuseo pPrincipal)
    {
        principal = pPrincipal;
        setLayout(new GridLayout(2,2));
        setSize(new Dimension (800, 0));
        TitledBorder border = new TitledBorder("Opciones");
        border.setTitleColor( Color.BLACK );
        setBorder(border);

        //Inicializar los botones y agregarlos.
        nameSearch = new JButton(BUSCAR);
        nameSearch.setActionCommand( BUSCAR );
        nameSearch.addActionListener( this );
        add(nameSearch );

       /* opcion1 = new JButton (OPCIONUNO);
        opcion1.setActionCommand( OPCIONUNO );
        opcion1.addActionListener( this );
        add(opcion1);

        opcion2 = new JButton(OPCIONDOS);
        opcion2.setActionCommand( OPCIONDOS );
        opcion2.addActionListener( this );
        add(opcion2);
        */
        
        btnPos = new JButton(BUSCAR_POSICION);
        btnPos.setActionCommand( BUSCAR_POSICION );
        btnPos.addActionListener(this);
        add(btnPos);
        
        JLabel emp = new JLabel();
        add(emp);
        
        txtPosicion = new JTextField("Introduce la posición de la obra");
        add(txtPosicion);
        
        
        
    }
    
    public boolean isNumber() 
    {
        if(txtPosicion.getText( ).equals( "" ))
        {
            JOptionPane.showMessageDialog( null, "La cadena está vacía." );
        }
        try
        {
        Double d = Double.parseDouble(txtPosicion.getText( ));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( null, "El texto no es numérico." );
        }
        return true;
    }

    @Override
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        switch(command)
        {
            case BUSCAR:
                String nNombre = JOptionPane.showInputDialog( null, "Nombre de la Obra" );
                principal.darObraPorNombre( nNombre );
                break;
            case OPCIONUNO:
                principal.reqFuncOpcion1( );
                break;
            case OPCIONDOS:
                principal.reqFuncOpcion2( );
                break;
            case BUSCAR_POSICION:
                if(isNumber())
                {
                    principal.irObraPosicion( Integer.parseInt(txtPosicion.getText( )));
                }
                break;
        }

    }

}
