/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_cupiMuseo.
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiMuseo.mundo;

import java.util.Properties;

/**
 * Clase que representa una Obra de Arte.
 */
public class ObraDeArte
{
    // -------------------------------------------------------------
    // Constantes
    // -------------------------------------------------------------

    /**
     * Constante que representa el tipo Pintura.
     */
    public static final String PINTURA = "Pintura";

    /**
     * Constante que representa el tipo Escultura.
     */
    public static final String ESCULTURA = "Escultura";

    /**
     * Constante que representa 5 estrellas.
     */
    public static final int CINCO_ESTRELLAS = 5;

    /**
     * Constante que representa 4 estrellas.
     */
    public static final int CUATRO_ESTRELLAS = 4;

    /**
     * Constante que representa 3 estrellas.
     */
    public static final int TRES_ESTRELLAS = 3;

    /**
     * Constante que representa 2 estrellas.
     */
    public static final int DOS_ESTRELLAS = 2;

    /**
     * Constante que representa 1 estrellas.
     */
    public static final int UNA_ESTRELLA = 1;

    /**
     * Constante que representa 0 estrellas.
     */
    public static final int CERO_ESTRELLAS = 0;

    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Nombre de la obra de arte.
     */
    private String nombreObra;

    /**
     * Nombre del artista autor de la obra de arte.
     */
    private String nombreArtista;

    /**
     * Tipo de la obra de arte.
     */
    private String tipo;

    /**
     * Suma total del puntaje dado por los visitantes a la obra de arte.
     */
    private int sumaPuntaje;

    /**
     * Cantidad de veces que la obra ha sido evaluada.
     */
    private int cantidadEvaluaciones;

    /**
     * Ruta de la imagen que contiene la obra de arte.
     */
    private String rutaImagen;

    /**
     * Estado de la obra, indica si está o no en remate.
     */
    private boolean estaRemate;

    // -------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------

    /**
     * Crea una nueva obra de arte cargando la información desde un archivo.<br>
     * <b>post:</b> Se creó la obra de arte con el numero de obra y el archivo de propiedades dado por parámetro.<br>
     * El atributo sumaPuntaje se inicializó en -1 y el atributo cantidadEvaluaciones en 0.
     * @param pNumeroObra El numero de la obra cargada. pNumeroObra >= 0.
     * @param pDatos El archivo properties donde se encuentran la información de la obra. pDatos != null.
     * @throws Exception Si hay algún error en el formato del archivo.
     */
    public ObraDeArte( int pNumeroObra, Properties pDatos ) throws Exception
    {
        try
        {
            sumaPuntaje = -1;
            cantidadEvaluaciones = 0;

            // Lee la información
            nombreObra = pDatos.getProperty( "museo.obra" + pNumeroObra + ".nombreObra" );
            nombreArtista = pDatos.getProperty( "museo.obra" + pNumeroObra + ".nombreArtista" );
            estaRemate = Boolean.parseBoolean( pDatos.getProperty( "museo.obra" + pNumeroObra + ".remate" ) );
            int intTipo = Integer.parseInt( pDatos.getProperty( "museo.obra" + pNumeroObra + ".tipo" ) );
            if( intTipo == 1 )
            {
                tipo = PINTURA;
            }
            else if( intTipo == 2 )
            {
                tipo = ESCULTURA;
            }
            else
            {
                throw new Exception( "Error en el formato del archivo" );
            }
            rutaImagen = "data/imagenes/" + pDatos.getProperty( "museo.obra" + pNumeroObra + ".imagen" );
        }
        catch( Exception e )
        {
            throw new Exception( "Error en el formato del archivo" );
        }

    }
    
    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Retorna la cantidad de veces que la obra ha sido evaluada.
     * @return Cantidad de evaluaciones de la obra.
     */
    public int darCantidadEvaluaciones( )
    {
        return cantidadEvaluaciones;
    }

    /**
     * Retorna el nombre de la obra de arte.
     * @return Nombre de la obra de arte.
     */
    public String darNombreObra( )
    {
        return nombreObra;
    }

    /**
     * Retorna el nombre del artista autor de la obra de arte.
     * @return Nombre del artista.
     */
    public String darNombreArtista( )
    {
        return nombreArtista;
    }

    /**
     * Retorna la ruta donde se encuentra la imagen de la obra de arte.
     * @return Ruta de la imagen.
     */
    public String darRutaImagen( )
    {
        return rutaImagen;
    }

    /**
     * Retorna la suma del puntaje.
     * @return Suma del puntaje.
     */
    public int darSumaPuntaje( )
    {
        return sumaPuntaje;
    }

    /**
     * Retorna el tipo de la obra de arte.<br>
     * <b>pre:</b> El atributo tipo ha sido inicializado.
     * @return Tipo de la obra de arte.
     */
    public String darTipo( )
    {
        return tipo;
    }

    /**
     * Retorna si la obra o no está en remate.
     * @return true si la obra está en remate o false de lo contrario.
     */
    public boolean estaEnRemate( )
    {
        return estaRemate;
    }

    /**
     * Agrega una nueva calificación de la obra de arte.<br>
     * <b> post: </b> La nueva calificación es sumada a la suma de puntajes y aumenta en 1 la cantidad de evaluaciones.
     * @param pNuevaCalificacion Nueva calificación de la obra. pCalificación pertenece a {CINCO_ESTRELLAS, CUATRO_ESTRELLAS, TRES_ESTRELLAS, DOS_ESTRELLAS,UNA_ESTRELLAS,
     *        CERO_ESTRELLAS}.
     */
    public void agregarCalificacion( int pNuevaCalificacion )
    {
        if( sumaPuntaje > 0 )
        {
            sumaPuntaje += pNuevaCalificacion;
        }
        else
        {
            sumaPuntaje = pNuevaCalificacion;
        }
        cantidadEvaluaciones++;
    }

    /**
     * Retorna el promedio de la puntaje dado por los visitantes.<br>
     * Si no tiene calificaciones retorna -1.<br>
     * <b>pre:</b> Los atributos cantidadEvaluaciones y sumaPuntaje han sido inicializados.
     * @return Promedio entero de la calificación dada por los visitantes, o -1 si aún no hay calificaciones registradas.
     */
    public int darPromedioCalificacion( )
    {
        int promedio = -1;
        double puntaje = 0;
        if( cantidadEvaluaciones != 0 )
        {
            puntaje = ( double )sumaPuntaje / cantidadEvaluaciones;
            puntaje = Math.round( puntaje ) / 1.0;
            promedio = ( int )puntaje;
        }
        return promedio;
    }
}