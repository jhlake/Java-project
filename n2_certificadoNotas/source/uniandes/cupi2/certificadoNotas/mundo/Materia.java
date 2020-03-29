/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
	 * Constante que representa el departamento de Ingenier�a de Sistemas.
	 */
	public final static String INGENIERIA_DE_SISTEMAS = "Ingenier�a de Sistemas";

	/**
	 * Constante que representa el departamento de Ingenier�a Industrial.
	 */
	public final static String INGENIERIA_INDUSTRIAL = "Ingenier�a Industrial";

	/**
	 * Constante que representa el departamento de Matem�ticas.
	 */
	public final static String MATEMATICAS = "Matem�ticas";

	/**
	 * Constante que representa el departamento de Qu�mica.
	 */
	// TODO Parte1 PuntoA: Declare la constante QUIMICA con valor inicial Qu�mica.
	public final static String QUIMICA = "Qu�mica";
	/**
	 * Constante que representa el departamento de F�sica.
	 */
	// TODO Parte1 PuntoB: Declare la constante FISICA con valor inicial F�sica.
	public final static String FISICA = "F�sica";
	/**
	 * Constante que representa el departamento de Microbiolog�a.
	 */
	// TODO Parte1 PuntoC: Declare la constante MICROBIOLOGIA con valor inicial Microbiolog�a.
	public final static String MICROBIOLOGIA = "Microbiolog�a";
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
	 * N�mero de cr�ditos de la materia.
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
	 * Crea la materia con los datos que llegan por par�metro.<br>
	 * <b>post: </b> La materia se inicializ� con los valores nombre, departamento y n�mero de cr�ditos dados por par�metro. El valor de la nota queda inicializado en -1.
	 * @param pNombre Nombre de la materia. pNombre != null && pNombre != "".
	 * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
	 *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
	 * @param pNumeroCreditos N�mero de cr�ditos de la materia. pNumeroCredito > 0.
	 */
	public Materia( String pNombre, String pDepartamento, int pNumeroCreditos )
	{
		// TODO Parte1 PuntoF: Complete el m�todo constructor seg�n la documentaci�n.

		nombre = pNombre;
		departamento = pDepartamento;
		numeroCreditos = pNumeroCreditos;
		nota = -1;
	}

	// -------------------------------------------------------------
	// M�todos
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
		//TODO Parte1 PuntoG: Complete el m�todo seg�n la documentaci�n.
		return departamento;
	}

	/**
	 * Retorna el n�mero de cr�ditos de la materia.
	 * @return N�mero de cr�ditos de la materia.
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
		//TODO Parte1 PuntoH: Complete el m�todo seg�n la documentaci�n.
		return nota;
	}

	/**
	 * Asigna la nota con el valor dado por par�metro.<br>
	 * <b>post: </b> Se asign� el valor a la nota.
	 * @param pNota Valor de la nota obtenida. pNota >= 1.5 && pNota <=5.
	 */
	public void asignarNota( double pNota )
	{
		//TODO Parte1 PuntoI: Complete el m�todo seg�n la documentaci�n.
		nota = pNota;
	}

	/**
	 * Modifica la materia con los valores dados por par�metro.<br>
	 * <b> post: </b> La materia dada cambi� de nombre, departamento y n�mero de cr�ditos. La nota tiene valor -1.
	 * @param pNombre Nuevo nombre de la materia. pNombre != null && pNombre != "".
	 * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
	 *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
	 * @param pNumeroCreditos N�mero de cr�ditos de la materia. pNumeroCredito > 0.
	 */
	public void modificar( String pNombre, String pDepartamento, int pNumeroCreditos )
	{
		//TODO Parte1 PuntoJ: Complete el m�todo seg�n la documentaci�n.
		nombre = pNombre;
		departamento = pDepartamento;
		numeroCreditos = pNumeroCreditos;
		nota = -1;
	}
}