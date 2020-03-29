/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Proyecto	Cupi2	(http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_calculadoraNotas
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.calculadoraNotas.mundo;

/**
 * Representa un nivel dentro del curso.
 */
public class Nivel
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Número del nivel.
	 */
	private int numero;

	/**
	 * Nota del ejercicio.
	 */
	private double notaEjercicio;

	/**
	 * Nota del examen práctico.
	 */
	private double notaPractico;

	/**
	 * Nota del examen teórico.
	 */
	private double notaTeorico;

	/**
	 * Porcentaje que representa el ejercicio sobre el curso completo.
	 */
	private double porcentajeEjercicio;

	/**
	 * Porcentaje que representa el examen práctico sobre el curso completo.
	 */
	private double porcentajePractico;

	/**
	 * Porcentaje que representa el examen teórico sobre el curso completo.
	 */
	private double porcentajeTeorico;

	/**
	 * Tema tratado en el nivel.
	 */
	private String tema;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea un nivel usando la información recibida por parámetro. <br>
	 * <b>post: </b> Se inicializaron los atributos: notaPractico, notaTeorico y notaEjercicio en 0. <br>
	 * Se inicializaron los atributos: numero, porcentajeEjercicio, porcentajePractico, porcentajeTeorico y tema con los valores recibidos por parámetro. <br>
	 * @param pNumero Número del nivel. 1 <= pNumero <=6
	 * @param pPorcentajeEjercicio Porcentaje que tiene el ejercicio sobre el curso. 0 < pPorcentajeEjercicio < 1.
	 * @param pPorcentajePractico Porcentaje que tiene el examen práctico sobre el curso. 0 < pPorcentajePractico < 1.
	 * @param pPorcentajeTeorico Porcentaje que tiene el examen teórico sobre el curso. 0 < pPorcentajeTeorico < 1.
	 * @param pTema Tema tratado en el nivel. pTema != null, pTema != "".
	 */
	public Nivel( int pNumero, double pPorcentajeEjercicio, double pPorcentajePractico, double pPorcentajeTeorico, String pTema )
	{
		numero = pNumero;
		notaEjercicio = 0;
		notaPractico = 0;
		notaTeorico = 0;        
		porcentajeEjercicio = pPorcentajeEjercicio; 
		porcentajePractico = pPorcentajePractico; 
		porcentajeTeorico = pPorcentajeTeorico;
		tema = pTema;         
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna el número del nivel.
	 * @return Número del nivel.
	 */
	public int darNumero( )
	{
		return numero;
	}

	/**
	 * Retorna la nota del ejercicio.
	 * @return Nota del ejercicio.
	 */
	public double darNotaEjercicio( )
	{
		return notaEjercicio;
	}

	/**
	 * Retorna la nota del examen práctico.
	 * @return Nota del examen práctico.
	 */
	public double darNotaPractico( )
	{
		return notaPractico; 
	}

	/**
	 * Retorna la nota del examen teórico.
	 * @return Nota del examen teórico.
	 */
	public double darNotaTeorico( )
	{
		return notaTeorico; 
	}

	/**
	 * Retorna el porcentaje del ejercicio sobre todo el curso.
	 * @return Porcentaje que tiene el ejercicio.
	 */
	public double darPorcentajeEjercicio( )
	{
		return porcentajeEjercicio; 
	}

	/**
	 * Retorna el porcentaje del examen práctico sobre todo el curso.
	 * @return Porcentaje que tiene el examen práctico.
	 */
	public double darPorcentajePractico( )
	{
		return porcentajePractico;
	}

	/**
	 * Retorna el porcentaje del examen teórico sobre todo el curso.
	 * @return Porcentaje que tiene el examen teórico.
	 */
	public double darPorcentajeTeorico( )
	{
		return porcentajeTeorico; 
	}

	/**
	 * Retorna el tema.
	 * @return Tema tratado en el nivel.
	 */
	public String darTema( )
	{
		return tema; 
	}

	/**
	 * Cambiar la nota del ejercicio por el valor recibido por parámetro. <br>
	 * <b> post: </b> Se modificó la nota del ejercicio.
	 * @param pNotaEjercicio Nueva nota del ejercicio.
	 */
	public void cambiarNotaEjercicio( double pNotaEjercicio )
	{
		notaEjercicio = pNotaEjercicio;  
	}

	/**
	 * Cambia la nota del examen práctico por el valor recibido por parámetro. <br>
	 * <b> post: </b> Se modificó la nota del examen práctico.
	 * @param pNotaPractico Nueva nota del examen práctico.
	 */
	public void cambiarNotaPractico( double pNotaPractico )
	{
		notaPractico = pNotaPractico; 
	}

	/**
	 * Cambia la nota del examen teórico por el valor recibido por parámetro. <br>
	 * <b> post: </b> Se modificó la nota del examen teórico.
	 * @param pNotaTeorico Nueva nota del examen teórico.
	 */
	public void cambiarNotaTeorico( double pNotaTeorico )
	{
		notaTeorico= pNotaTeorico;
	}

	/**
	 * Calcula el porcentaje total que representa el nivel sobre todo el curso. <br>
	 * @return Porcentaje de la nota del curso que representa el nivel.
	 */
	public double calcularPorcentajeTotal( )
	{
		return ( porcentajeEjercicio + porcentajePractico + porcentajeTeorico ); 
	}

	/**
	 * Calcula el promedio ponderado de las notas del nivel. <br>
	 * @return Nota del nivel .
	 */ 
	public double calcularNota( )
	{
		double notaNivel = ( notaEjercicio*porcentajeEjercicio + notaPractico*porcentajePractico + notaTeorico*porcentajeTeorico ) ;      
		return notaNivel;	   

	}

	/**
	 * Calcula la nota obtenida en el nivel, sobre 5.
	 * @return Nota obtenida en el nivel.
	 */
	public double calcularNotaSobreCinco( )
	{
		double nota = ( calcularNota( ) / calcularPorcentajeTotal( ) );
		return nota;
	}

}
