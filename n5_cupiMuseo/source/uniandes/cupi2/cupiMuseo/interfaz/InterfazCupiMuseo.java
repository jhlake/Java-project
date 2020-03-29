package uniandes.cupi2.cupiMuseo.interfaz;

import java.awt.*;

import javax.swing.*;

import uniandes.cupi2.cupiMuseo.mundo.*;

/**
 * Ventana principal de la aplicaci�n.
 * @author jlake
 */
public class InterfazCupiMuseo extends JFrame
{
    /*
     * Atributos
     */

    /**
     * Clase principal del mundo.
     */
    private CupiMuseo cupiMuseo;

    /*
     * Atributos de la interfaz
     */
    /**
     * Panel que contiene el banner.
     */
    private PanelBanner panelBanner;

    /**
     * Panel que contiene la informaci�n de la obra.
     */
    private PanelInformacion panelInformacion;

    /**
     * Panel que permite calificar las obras.
     */
    private PanelCalificacion panelCalificacion;

    /**
     * Panel de opciones adicionales.
     */
    private PanelOpciones panelOpciones;

    /*
     * M�todos
     */

    /**
     * Construye una nueva interfaz. <br>

     */
    public InterfazCupiMuseo() 
    {
        try
        {
            cupiMuseo = new CupiMuseo();
            setTitle("CupiMuseo");
            setSize(760, 700);
            setResizable(false);
            setDefaultCloseOperation( EXIT_ON_CLOSE );
            setLocationRelativeTo( null );
            setLayout( new BorderLayout( ) );

            panelBanner = new PanelBanner();
            add(panelBanner, BorderLayout.NORTH);

            panelInformacion = new PanelInformacion(this);
            add(panelInformacion, BorderLayout.WEST);

            panelCalificacion = new PanelCalificacion(this);
            add(panelCalificacion, BorderLayout.EAST);

            panelOpciones = new PanelOpciones(this);
            add(panelOpciones, BorderLayout.SOUTH);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ) );
        }

    }

    /**
     * Este m�todo devuelve la obra en la que est� actu�lmente el usuario.
     * @return El m�todo devuelve la obra actual. Null si no hay obra seleccionada.
     */
    public ObraDeArte darObraActual()
    {
        try
        {
            return cupiMuseo.darObraActual( );
        }
        catch(Exception e)
        {
            return null;
        }
    }
    /**
     * Se busca la obra anterior, actualiza la informaci�n y autom�ticamente pone la informaci�n en la pantalla principal.
     */
    public void darObraAnterior()
    {
        try
        {
            ObraDeArte obra = cupiMuseo.darObraAnterior( );
            panelInformacion.actualizar( obra.darNombreObra( ), obra.darNombreArtista( ), obra.darTipo( ), obra.darRutaImagen( ), obra.estaEnRemate( ) );

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( null, "Ya se encuentra en la primera obra." );
        }
    }
    /**
     * Se busca la obra siguiente, actualiza la informaci�n, y autom�ticamente pone la informaci�n en la pantalla principal.
     */
    public void darObraSiguiente()
    {
        try
        {
            ObraDeArte obra = cupiMuseo.darObraSiguiente( );
            panelInformacion.actualizar( obra.darNombreObra( ), obra.darNombreArtista( ), obra.darTipo( ), obra.darRutaImagen( ), obra.estaEnRemate( ) );

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( null, "Ya se encuentra en la �ltima obra." );
        }
    }
    /**
     * Se busca una obra con nombre dado por par�metro, y se actualiza la informaci�n en la pantalla principal con esa obra.
     * @param nNombre Nombre de la obra a buscar. nNombre != null && nNombre != "".
     */
    public void darObraPorNombre(String nNombre)
    {

        try
        {
            ObraDeArte obra = cupiMuseo.darObraPorNombre( nNombre );
            panelInformacion.actualizar( obra.darNombreObra( ), obra.darNombreArtista( ), obra.darTipo( ), obra.darRutaImagen( ), obra.estaEnRemate( ) );

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ) );
        }

    }

    /**
     * El m�todo salta immediatamente a la primera obra.
     */
    public void darPrimeraObra()
    {
        try
        {
            ObraDeArte obra = cupiMuseo.darPrimeraObra( );
            panelInformacion.actualizar( obra.darNombreObra( ), obra.darNombreArtista( ), obra.darTipo( ), obra.darRutaImagen( ), obra.estaEnRemate( ) );

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ) );
        }
    }

    /**
     * El m�todo salta immediatamente a la �ltima obra.
     */
    public void darUltimaObra()
    {
        try
        {
            ObraDeArte obra = cupiMuseo.darUltimaObra( );
            panelInformacion.actualizar( obra.darNombreObra( ), obra.darNombreArtista( ), obra.darTipo( ), obra.darRutaImagen( ), obra.estaEnRemate( ) );

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ) );
        }
    }

    /**
     * Agrega una calificaci�n a la obra actual basado en qu� bot�n se undi� en la interfaz.
     * @param nCalificacion Entero que dice cu�l fue la calificaci�n dada por el usuario. nCalificacion != null && nCalificacion >= -1 && nCalificacoin <= 5.
     */
    public void agregarCalificacion(int nCalificacion)
    {
        try
        {
            cupiMuseo.darObraActual( ).agregarCalificacion( nCalificacion );
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ) );
        }
    }

    /**
     * El m�todo actualiza la calificaci�n de la obra actual.
     */
    public void actualizarCalificacion()
    {
        panelInformacion.actualizarCalificacion( );
    }
    
    public void irObraPosicion(int index)
    {
        try
        {
        ObraDeArte obra = cupiMuseo.darObraIndice( index );
        panelInformacion.actualizar( obra.darNombreObra( ), obra.darNombreArtista( ), obra.darTipo( ), obra.darRutaImagen( ), obra.estaEnRemate( ) );
        }
        catch(Exception exc)
        {
            JOptionPane.showMessageDialog( null, exc.getMessage( ) );
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
        String resultado = cupiMuseo.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cupiMuseo.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /*
     * Main
     */
    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
     * @param args Argumentos para la ejecuci�n de la aplicaci�n. En este caso no son necesarios.
     */
    public static void main( String[] args )
    {
        try
        {
            InterfazCupiMuseo interfaz = new InterfazCupiMuseo( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );

        }



    }




}
