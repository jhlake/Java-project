package uniandes.cupi2.cupiMuseo.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel con la información de una obra.
 * @author jlake
 */

public class PanelInformacion extends JPanel implements ActionListener
{
    /*
     * Constantes
     */

    /**
     * Constante que representa el comando para ir a la primera obra.
     */
    private static final String PRIMERA = "Primera";

    /**
     * Constante que representa el comando para ir a la última obra.
     */
    private final static String ULTIMA = "Última";

    /**
     * Constante que representa el comando para buscar la obra anterior.
     */
    private final static String OBRA_ANTERIOR = "<<";

    /**
     * Constante que representa el comando para buscar la obra siguiente.
     */
    private final static String OBRA_SIGUIENTE = ">>";
    /*
     * Atributos
     */

    /**
     * Ventana principal de la aplicación
     */

    private InterfazCupiMuseo principal;

    /* 
     * Atributos de la interfaz
     */

    /**
     * Etiqueta para el nombre de la obra.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta para el nombre del artista.
     */
    private JLabel lblArtista;

    /**
     * Etiqueta para el tipo de la obra.
     */
    private JLabel lblTipo;

    /**
     * Etiqueta para la calificación.
     */
    private JLabel lblCalificacion;

    /**
     * Etiqueta para el checkbox de remate o no.
     */
    private JLabel lblRemate;

    /**
     * Etiqueta para la imagen de la obra
     */
    private JLabel lblImg;

    /**
     * Checkbox para el remate
     */
    private JCheckBox chbRemate;

    /**
     * Etiqueta donde se muestra el nombre de la obra.
     */
    private JLabel lblNombreFetch;

    /**
     * Etiqueta donde se muestra el nombre del artista de la obra.
     */
    private JLabel lblArtistaFetch;

    /**
     * Etiqueta donde se muestra el tipo del artista.
     */
    private JLabel lblTipoFetch;

    /**
     * Imagen de la calificación
     */
    private JLabel lblCalificacionFetch;

    /**
     * Botón para ir a la primera obra.
     */
    private JButton btnPrimera;

    /**
     * Botón para ir a la última obra.
     */
    private JButton btnUltima;

    /**
     * Botón para ir a la obra siguiente.
     */
    private JButton btnSiguiente;

    /**
     * Botón para ir a la obra anterior.
     */
    private JButton btnAnterior;

    /*
     * Constructor
     */
    /**
     * Constructor del panel de la información de la obra. 
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelInformacion( InterfazCupiMuseo pPrincipal)
    {
        //Inicializa el panel como tal
        principal = pPrincipal;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(630, 0));
        TitledBorder border = new TitledBorder("Información");
        border.setTitleColor( Color.BLACK );
        setBorder(border);

        //Inicializa la parte de la imagen
        JPanel imag = new JPanel();
        imag.setLayout( new GridLayout(1,1) );
        imag.setPreferredSize( new Dimension(260, 0) );
        lblImg = new JLabel();
        imag.add(lblImg);
        add( imag, BorderLayout.WEST);

        //Inicializa la parte de la información
        JPanel info = new JPanel();
        info.setLayout( new GridLayout(5, 2) );
        info.setPreferredSize( new Dimension(350, 320) );
        LineBorder borderLabels = new LineBorder( Color.GRAY);

        lblNombre = new JLabel("Nombre de la obra");
        info.add( lblNombre );

        lblNombreFetch = new JLabel("");
        lblNombreFetch.setBorder(borderLabels);
        info.add( lblNombreFetch );

        lblArtista = new JLabel( "Nombre del artista" );
        info.add( lblArtista );

        lblArtistaFetch = new JLabel("");
        lblArtistaFetch.setBorder( borderLabels );
        info.add( lblArtistaFetch );

        lblTipo = new JLabel( "Tipo de obra" );
        info.add( lblTipo );

        lblTipoFetch = new JLabel("");
        lblTipoFetch.setBorder( borderLabels );
        info.add( lblTipoFetch );

        lblCalificacion = new JLabel("Calificación de la obra");
        info.add( lblCalificacion );

        lblCalificacionFetch = new JLabel("");
        info.add( lblCalificacionFetch );

        lblRemate = new JLabel("Está en remate");
        info.add( lblRemate );

        chbRemate = new JCheckBox();
        info.add( chbRemate );

        add( info , BorderLayout.EAST );

        //Inicializa el navegador de obras  
        JPanel navegador = new JPanel();
        navegador.setLayout( new GridLayout(1,4) );
        navegador.setPreferredSize( new Dimension(650, 50) );
        TitledBorder borderNavegador = new TitledBorder("Navegación");
        borderNavegador.setTitleColor( Color.BLACK );
        navegador.setBorder( borderNavegador );

        btnPrimera = new JButton( "Ver primera obra" );
        btnPrimera.setActionCommand( PRIMERA );
        btnPrimera.addActionListener( this );
        navegador.add( btnPrimera );

        btnAnterior = new JButton( OBRA_ANTERIOR );
        btnAnterior.setActionCommand( OBRA_ANTERIOR );
        btnAnterior.addActionListener( this );
        navegador.add( btnAnterior );

        btnSiguiente = new JButton( OBRA_SIGUIENTE );
        btnSiguiente.setActionCommand( OBRA_SIGUIENTE );
        btnSiguiente.addActionListener( this );
        navegador.add( btnSiguiente );

        btnUltima = new JButton("Ver última obra");
        btnUltima.setActionCommand( ULTIMA );
        btnUltima.addActionListener( this );
        navegador.add( btnUltima );     

        //Agrega el navegador al panel
        add(navegador, BorderLayout.SOUTH);
        actualizar(principal.darObraActual().darNombreObra( ), principal.darObraActual( ).darNombreArtista( ), principal.darObraActual( ).darTipo( ), principal.darObraActual( ).darRutaImagen( ), principal.darObraActual( ).estaEnRemate( ));


    }
    /**
     * Actualiza la información de la obra por la nueva, basado en la información pasada por parámetro.
     * @param nNombre Nombre de la obra nueva. nNombre !=null && nNombre es un nombre válido.
     * @param nArtista Nombre del artista nuevo. nArtista != null && nArtista es un artista válido.
     * @param nTipo Tipo de la obra nueva. nTipo != null && nTipo es un tipo válido.
     * @param nImg URL de la imagen nueva. nImg !=null && nImg es un URL de una imagen válida.
     * @param estadoRemate Booleano que indica la condición de la imagen, si está o no en remate. 
     */
    public void actualizar(String nNombre, String nArtista, String nTipo, String nImg, boolean estadoRemate)
    {
        lblNombreFetch.setText(nNombre);
        lblArtistaFetch.setText( nArtista );
        lblTipoFetch.setText( nTipo );
        ImageIcon img = new ImageIcon( nImg );
        lblImg.setIcon( img );
        chbRemate.setSelected( estadoRemate );
        lblCalificacionFetch.setText("");
        lblCalificacionFetch.setIcon( null );
        actualizarCalificacion();
    }

    /**
     * Este método actualiza la calificacion apenas el usuario unde algún botón de calificación, así no tiene que cambiar de obra para que se actualize.
     */
    public void actualizarCalificacion()
    {
        switch(principal.darObraActual( ).darPromedioCalificacion( ))
        {
            case -1:
                lblCalificacionFetch.setText( "Aún no hay calificación." );
                break;
            case 0:
                lblCalificacionFetch.setText("");
                ImageIcon icon0 = new ImageIcon("data/imagenes/0_estrellas.png");
                lblCalificacionFetch.setIcon(icon0);
                break;
            case 1:
                lblCalificacionFetch.setText("");
                ImageIcon icon1 = new ImageIcon("data/imagenes/1_estrellas.png");
                lblCalificacionFetch.setIcon(icon1);
                break;
            case 2:
                lblCalificacionFetch.setText("");
                ImageIcon icon2 = new ImageIcon("data/imagenes/2_estrellas.png");
                lblCalificacionFetch.setIcon(icon2);
                break;
            case 3:
                lblCalificacionFetch.setText("");
                ImageIcon icon3 = new ImageIcon("data/imagenes/3_estrellas.png");
                lblCalificacionFetch.setIcon(icon3);
                break;
            case 4:
                lblCalificacionFetch.setText("");
                ImageIcon icon4 = new ImageIcon("data/imagenes/4_estrellas.png");
                lblCalificacionFetch.setIcon(icon4);
                break;
            case 5:
                lblCalificacionFetch.setText("");
                ImageIcon icon5 = new ImageIcon("data/imagenes/5_estrellas.png");
                lblCalificacionFetch.setIcon(icon5);
                break;
        }
    }
    @Override
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        switch(command)
        {
            case PRIMERA:
                principal.darPrimeraObra( );
                break;
            case OBRA_ANTERIOR:
                principal.darObraAnterior( );
                break;
            case OBRA_SIGUIENTE:
                principal.darObraSiguiente( );
                break;
            case ULTIMA:
                principal.darUltimaObra( );
                break;
        }

    }
}
