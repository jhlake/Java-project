/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_certificadoNotas
 * Autor: EquipoCupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.certificadoNotas.mundo;

/**
 * Clase que representa una materia del certificado de notas.
 */
public class Materia
{
	// -------------------------------------------------------------
	// Constantes
	// -------------------------------------------------------------

	/**
	 * Constante que representa el departamento de Ingeniería de Sistemas.
	 */
	public final static String INGENIERIA_DE_SISTEMAS = "Ingeniería de Sistemas";

	/**
	 * Constante que representa el departamento de Ingeniería Industrial.
	 */
	public final static String INGENIERIA_INDUSTRIAL = "Ingeniería Industrial";

	/**
	 * Constante que representa el departamento de Matemáticas.
	 */
	public final static String MATEMATICAS = "Matemáticas";

	/**
	 * Constante que representa el departamento de Química.
	 */
	// TODO Parte1 PuntoA: Declare la constante QUIMICA con valor inicial Química.
	public final static String QUIMICA = "Química";
	/**
	 * Constante que representa el departamento de Física.
	 */
	// TODO Parte1 PuntoB: Declare la constante FISICA con valor inicial Física.
	public final static String FISICA = "Física";
	/**
	 * Constante que representa el departamento de Microbiología.
	 */
	// TODO Parte1 PuntoC: Declare la constante MICROBIOLOGIA con valor inicial Microbiología.
	public final static String MICROBIOLOGIA = "Microbiología";
	// -------------------------------------------------------------
	// Atributos
	// -------------------------------------------------------------

	/**
	 * Nombre de la materia.
	 */
	private String nombre;

	/**
	 * Nombre del departamento que ofrece la materia.
	 */
	// TODO Parte1 PuntoD: Declare el atributo departamento.
	private String departamento;

	/**
	 * Número de créditos de la materia.
	 */
	private int numeroCreditos;

	/**
	 * Nota obtenida en la materia.
	 */
	// TODO Parte1 PuntoE: Declare el atributo nota.
	private double nota;
	// -------------------------------------------------------------
	// Constructores
	// -------------------------------------------------------------

	/**
	 * Crea la materia con los datos que llegan por parámetro.<br>
	 * <b>post: </b> La materia se inicializó con los valores nombre, departamento y número de créditos dados por parámetro. El valor de la nota queda inicializado en -1.
	 * @param pNombre Nombre de la materia. pNombre != null && pNombre != "".
	 * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
	 *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
	 * @param pNumeroCreditos Número de créditos de la materia. pNumeroCredito > 0.
	 */
	public Materia( String pNombre, String pDepartamento, int pNumeroCreditos )
	{
		// TODO Parte1 PuntoF: Complete el método constructor según la documentación.

		nombre = pNombre;
		departamento = pDepartamento;
		numeroCreditos = pNumeroCreditos;
		nota = -1;
	}

	// -------------------------------------------------------------
	// Métodos
	// -------------------------------------------------------------

	/**
	 * Retorna el nombre de la materia.
	 * @return Nombre de la materia.
	 */
	public String darNombre( )
	{
		return nombre;
	}

	/**
	 * Retorna el nombre del departamento que ofrece la materia.
	 * @return Nombre del departamento que ofrece la materia.
	 */
	public String darDepartamento( )
	{
		//TODO Parte1 PuntoG: Complete el método según la documentación.
		return departamento;
	}

	/**
	 * Retorna el número de créditos de la materia.
	 * @return Número de créditos de la materia.
	 */
	public int darNumeroCreditos( )
	{
		return numeroCreditos;
	}

	/**
	 * Retorna la nota obtenida en la materia.
	 * @return Nota obtenida en la materia.
	 */
	public double darNota( )
	{
		//TODO Parte1 PuntoH: Complete el método según la documentación.
		return nota;
	}

	/**
	 * Asigna la nota con el valor dado por parámetro.<br>
	 * <b>post: </b> Se asignó el valor a la nota.
	 * @param pNota Valor de la nota obtenida. pNota >= 1.5 && pNota <=5.
	 */
	public void asignarNota( double pNota )
	{
		//TODO Parte1 PuntoI: Complete el método según la documentación.
		nota = pNota;
	}

	/**
	 * Modifica la materia con los valores dados por parámetro.<br>
	 * <b> post: </b> La materia dada cambió de nombre, departamento y número de créditos. La nota tiene valor -1.
	 * @param pNombre Nuevo nombre de la materia. pNombre != null && pNombre != "".
	 * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
	 *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
	 * @param pNumeroCreditos Número de créditos de la materia. pNumeroCredito > 0.
	 */
	public void modificar( String pNombre, String pDepartamento, int pNumeroCreditos )
	{
		//TODO Parte1 PuntoJ: Complete el método según la documentación.
		nombre = pNombre;
		departamento = pDepartamento;
		numeroCreditos = pNumeroCreditos;
		nota = -1;
	}
}