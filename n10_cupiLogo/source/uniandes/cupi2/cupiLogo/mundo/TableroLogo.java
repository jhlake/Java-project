/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiLogo
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiLogo.mundo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Representa al tablero del logo.</br>
 * <b>inv:</b></br>
 * comandos != null</br>
 * tortuga != null </br>
 * ancho >= 0</br>
 * alto >= 0</br>
 */
public class TableroLogo
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tortuga del tablero.
     */
    private Tortuga tortuga;

    /**
     * Altura del tablero.
     */
    private int alto;

    /**
     * Ancho del tablero.
     */
    private int ancho;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Lista de comandos del tablero.
     */
    // TODO Parte 3 Punto A: Declare el atributo comandos como una lista.
    public List<IComando> comandos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el tablero.<br>
     * <b>post:</b>El ancho y alto del tablero se han asignado. <br>
     * Se ha inicializado la tortuga en el centro del tablero con orientación 0 y escala 1. <br> 
     * La lista de comandos ha sido inicializada.
     * @param pAncho Ancho del tablero. nAncho > 0.
     * @param pAlto Alto del tablero. nAlto > 0.
     */
    public TableroLogo( int pAncho, int pAlto )
    {
        ancho = pAncho;
        alto = pAlto;

        tortuga = new Tortuga( ancho/2, alto/2, 0, 1 );

        // TODO Parte 3 Punto B: Inicialice la lista de comandos.
        comandos = new ArrayList<IComando>();

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la tortuga del tablero.
     * @return Tortuga del tablero.
     */
    public Tortuga darTortuga( )
    {
        return tortuga;
    }

    /**
     * Retorna el alto del tablero.
     * @return Alto del tablero.
     */
    public int darAlto( )
    {
        return alto;
    }

    /**
     * Retorna el ancho del tablero.
     * @return Ancho del tablero.
     */
    public int darAncho( )
    {
        return ancho;
    }

    /**
     * Retorna la lista de comandos que hay en el tablero.
     * @return Lista de comandos.
     */
    public List darComandos( )
    {
        // TODO Parte 3 Punto C: Complete el método según la documentación dada.
    	return comandos;
    }

    /**
     * Agrega un comando a la lista de comandos.<br>
     * <b> pre: </b>La lista de comandos está inicializada.<br>
     * <b> post: </b>Se ha agregado el comando a la lista de comandos.
     * @param pComando Nuevo comando de la lista. pComando != null.
     */
    public void agregarComando( IComando pComando )
    {
        // TODO Parte 3 Punto D: Complete el método según la documentación dada.
    	comandos.add(pComando);
        verificarInvariante( );
    }

    /**
     * Ejecuta todos los comandos de la lista.<br>
     * <b> pre: </b>La lista de comandos está inicializada.
     * @param pG Tablero de edición. pG != null.
     */
    public void ejecutarComandos( Graphics2D pG )
    {

        // TODO Parte 3 Punto E: Complete el método según la documentación dada.
    	for(int i = 0; i< comandos.size(); i++)
    	{
    		comandos.get(i).ejecutar(tortuga, pG);
    	}
        verificarInvariante( );
    }

    /**
     * Elimina el último elemento de la lista de comandos.<br>
     * <b> pre: </b>La lista de comandos está inicializada.<br>
     * @return True si se eliminó un comando, false en caso contrario.
     */
    public boolean eliminarUltimoComando( )
    {
        // TODO Parte 3 Punto F: Complete el método según la documentación dada.
    	boolean del = false;
    	int i = 0;
    	while(i+1<comandos.size())
    	{
    		i++;
    	}
    	if(!comandos.isEmpty())
    	{
    		comandos.remove(i);
    		del = true;
    	}
    	return del;
    }

    /**
     * Limpia la lista de comandos, eliminando todos los comandos de la lista.<br>
     * <b>post:</b> Se ha limpiado el tablero y la tortuga se ubica en el centro.<br>
     * La lista de comandos está vacía.<br>
     */
    public void limpiarComandos( )
    {
        // TODO Parte 3 Punto G: Complete el método según la documentación dada.
    	comandos.clear();
    	tortuga.reiniciarTortuga();
    }

    /**
     * Verifica si el próximo movimiento se encuentra dentro del tablero.
     * @param pValor Valor de desplazamiento. pValor >= 0.
     * @param pDireccion Dirección del desplazamiento. pDireccion == 0 || pDireccion == 1.
     * @return True si el movimiento se encuentra dentro del tablero, false en caso contrario.
     */
    public boolean verificarMovimiento( double pValor, int pDireccion )
    {
        boolean puede = true;
        double distancia = pValor;

        if( pDireccion == 1 )
        {
            distancia *= -1;
        }

        double grados = tortuga.darOrientacion( );
        int xActual = tortuga.darXActual( );
        int yActual = tortuga.darYActual( );

        double radianes = Math.PI * grados / 180;
        int xNuevo = ( int ) ( xActual + distancia * Math.cos( radianes ) );
        int yNuevo = ( int ) ( yActual + distancia * Math.sin( radianes ) );
        if( ( xNuevo < 0 || ancho < xNuevo ) || ( yNuevo < 0 || alto < yNuevo ) )
        {
            puede = false;
        }

        return puede;
    }

    /**
     * Verifica si el giro encuentra dentro del rango definido.
     * @param pValor Valor del giro. pValor >= 0.
     * @param pDireccion Dirección del giro. pDireccion == 0 || pDireccion == 1.
     * @return True si el giro se encuentra dentro del rango, false en caso contrario.
     */
    public boolean verificarGiro( double pValor, int pDireccion )
    {
        boolean puede = true;

        if( pValor < 0 || pValor > 360 )
        {
            puede = false;
        }

        return puede;
    }

    /**
     * Verifica si la escala se encuentra dentro del rango definido.
     * @param pValor Valor de la escala. pValor >= 0.
     * @param pDireccion Dirección de la escala. pDireccion == 0 || pDireccion == 1.
     * @return True si la escala se encuentra dentro del rango, false en caso contrario.
     */
    public boolean verificarEscala( double pValor, int pDireccion )
    {
        boolean puede = true;

        if( pValor < 1 || pValor > 3 )
        {
            puede = false;
        }

        return puede;
    }

    /**
     * Guarda la lista de comandos en el archivo dado.<br>
     * <b>pre:</b> La lista de comandos ha sido inicializada<br>
     * <b>post:</b> Se ha guardado el archivo con la lista de comandos.
     * @param pArchivo Archivo donde se van a guardar los comandos. pArchivo != null.
     * @throws PersistenciaException En caso de no encontrar el archivo.
     */
    public void guardarComandos( File pArchivo ) throws PersistenciaException
    {
        // TODO Parte 3 Punto H: Complete el método según la documentación dada.
    	try
    	{
    		PrintWriter pw = new PrintWriter(pArchivo);
    		pw.println(comandos.size());
    		Iterator iterator = comandos.iterator();
    		while (iterator.hasNext())
    		{
    			IComando command = (IComando) iterator.next();
    			command.guardar(pw);
    		}
    		pw.close();
    		
    	}
    	catch(Exception e)
    	{
    		throw new PersistenciaException("No se pudo guardar el archivo.");
    	}
    	
        verificarInvariante( );
    }

    /**
     * Carga la lista de comandos de un archivo.<br>
     * <b>post:</b>Se han cargado los comandos, y la tortuga está en la posición inicial.<br>
     * <b>post:</b>Se ha cargado la lista de comandos.
     * @param pArchivo Archivo con la lista de comandos. pArchivo != null.
     * @throws PersistenciaException En caso de no encontrar el archivo.
     * @throws PersistenciaException En caso de error al leer el archivo.
     * @throws PersistenciaException En caso de que el archivo no cumpla con el formato debido.
     */
    public void cargarComandos( File pArchivo ) throws PersistenciaException
    {

        // TODO Parte 3 Punto I: Complete el método según la documentación dada.
    	if(!pArchivo.exists())
    		throw new PersistenciaException("No se encontró el archivo.");
    	else
    	{
    		try
    		{
    			BufferedReader bf = new BufferedReader(new FileReader(pArchivo));
    			int comN = Integer.parseInt(bf.readLine());
    			for(int i = 0; i < comN; i++)
    			{
    				String act = bf.readLine();
    				String data[] = act.split(" ");
    				switch(data[0])
    				{
    				case ComandoCentrar.COMANDO:
    					comandos.add(new ComandoCentrar());
    					break;
    				case ComandoReiniciar.COMANDO:
    					comandos.add(new ComandoReiniciar());
    					break;
    				case ComandoCambiarColor.COMANDO:
    					comandos.add(new ComandoCambiarColor(Integer.parseInt(data[1])));
    					break;
    				case ComandoModificarPintando.COMANDO:
    					comandos.add(new ComandoModificarPintando(Integer.parseInt(data[1])));
    					break;
    				case ComandoEscalar.COMANDO:
    					comandos.add(new ComandoEscalar(Double.parseDouble(data[1]), Integer.parseInt(data[2])));
    					break;
    				case ComandoDesplazar.COMANDO:
    					comandos.add(new ComandoDesplazar(Double.parseDouble(data[1]), Integer.parseInt(data[2])));
    					break;
    				case ComandoGirar.COMANDO:
    					comandos.add(new ComandoGirar(Double.parseDouble(data[1]), Integer.parseInt(data[2])));
    					break;
    				}
    			}
    			bf.close();
    			tortuga.reiniciarTortuga();
    		}
    		catch(Exception e)
    		{
    			throw new PersistenciaException("Ha ocurrido un error al leer el archivo");
    		}
    	}
    	
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase.<br>
     * comandos != null.</br>
     * tortuga != null.</br>
     * ancho >= 0.</br>
     * alto >= 0.</br>
     */
    private void verificarInvariante( )
    {
        assert comandos != null : "La lista debería haber sido inicializada.";
        assert tortuga != null : "La tortuga debería haber sido inicializada.";
        assert ancho >= 0 : "El ancho del tablero debe ser positivo.";
        assert alto >= 0 : "La altura del tablero debe ser positivo.";

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return Respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión 2.
     * @return Respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}