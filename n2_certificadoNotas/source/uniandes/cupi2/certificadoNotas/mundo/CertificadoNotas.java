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
 * Clase que representa el certificado de notas para un estudiante.
 */
public class CertificadoNotas
{
	// -------------------------------------------------------------
	// Constantes
	// -------------------------------------------------------------

	/**
	 * Constante que representa la nota mínima que debe obtener un estudiante para aprobar una materia.
	 */
	public final static double NOTA_MINIMA_PARA_APROBAR = 3.0;

	/**
	 * Constante que representa la nota mínima que un estudiante puede obtener en una materia.
	 */
	//TODO Parte2 PuntoA: Declare la constante NOTA_MINIMA con valor inicial 1.5
	public final static double NOTA_MINIMA = 1.5;

	/**
	 * Constante que representa la nota máxima que un estudiante puede obtener en una materia.
	 */
	//TODO Parte2 PuntoB: Declare la constante NOTA_MAXIMA con valor inicial 5.0
	public final static double NOTA_MAXIMA = 5.0;
	/**
	 * Constante que representa el promedio mínimo que el estudiante debe obtener para no obtener una matrícula condicional.
	 */
	public final static double PROMEDIO_MINIMO_NO_MATRICULA_CONDICIONAL = 3.25;

	/**
	 * Constante que representa el promedio mínimo que el estudiante debe obtener para ganarse una beca.
	 */
	public final static double PROMEDIO_MINIMO_BECA = 4.85;

	/**
	 * Constante que representa el estado de estudiante becado.
	 */
	public final static String BECADO = "Becado";

	/**
	 * Constante que representa el estado de estudiante normal.
	 */
	public final static String NORMAL = "Normal";

	/**
	 * Constante que representa el estado de estudiante con matrícula condicional.
	 */
	public final static String MATRICULA_CONDICIONAL = "Matrícula Condicional";

	// -------------------------------------------------------------
	// Atributos
	// -------------------------------------------------------------

	/**
	 * Materia número 1.
	 */
	private Materia materia1;

	/**
	 * Materia número 2.
	 */
	private Materia materia2;

	/**
	 * Materia número 3.
	 */
	//TODO Parte2 PuntoC: Declare el atributo materia3.
	private Materia materia3;

	/**
	 * Materia número 4.
	 */
	//TODO Parte2 PuntoD: Declare el atributo materia4.
	private Materia materia4;

	// -------------------------------------------------------------
	// Constructores
	// -------------------------------------------------------------

	/**
	 * Construye un certificado de materias sin notas.<br>
	 * <b> post: </b> Las 4 materias quedaron inicializadas con los siguientes valores:<br>
	 * Materia 1 - Nombre: APO1, Departamento: Ingeniería de Sistemas, Créditos: 3.<br>
	 * Materia 2 - Nombre: Cálculo Diferencial, Departamento: Matemáticas, Créditos: 3.<br>
	 * Materia 3 - Nombre: Biología Celular, Departamento: Microbiología, Créditos: 3.<br>
	 * Materia 4 - Nombre: Laboratorio Química, Departamento: Química, Créditos: 1.
	 */
	public CertificadoNotas( )
	{
		//TODO Parte2 PuntoE: Complete el método constructor con la documentación dada.
		materia1 = new Materia("APO1", Materia.INGENIERIA_DE_SISTEMAS, 3);
		materia2 = new Materia("Cálculo Diferencial", Materia.MATEMATICAS, 3);
		materia3 = new Materia("Biología Celular", Materia.MICROBIOLOGIA, 3);
		materia4 = new Materia("Laboratorio Química", Materia.QUIMICA, 1);
	}

	// -------------------------------------------------------------
	// Métodos
	// -------------------------------------------------------------

	/**
	 * Retorna la materia 1.
	 * @return Materia 1.
	 */
	public Materia darMateria1( )
	{
		return materia1;
	}

	/**
	 * Retorna la materia 2.
	 * @return Materia 2.
	 */
	public Materia darMateria2( )
	{
		return materia2;
	}

	/**
	 * Retorna la materia 3.
	 * @return Materia 3.
	 */
	public Materia darMateria3( )
	{
		return materia3;
	}

	/**
	 * Retorna la materia 4.
	 * @return Materia 4.
	 */
	public Materia darMateria4( )
	{
		return materia4;
	}

	/**
	 * Retorna el número de materias que pertenecen al departamento dado por parámetro.
	 * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
	 *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
	 * @return Número de materias que pertenecen al departamento.
	 */
	public int darNumeroMateriasDepartamento( String pDepartamento )
	{
		//TODO Parte2 PuntoF: Complete el método con la documentación dada.
		int departamentoscheck = 0;

		if(pDepartamento == materia1.darDepartamento())
		{
			departamentoscheck++;
		}
		if(pDepartamento == materia2.darDepartamento())
		{
			departamentoscheck++;
		}
		if(pDepartamento == materia3.darDepartamento())
		{
			departamentoscheck++;
		}
		if(pDepartamento == materia4.darDepartamento())
		{
			departamentoscheck++;
		}
		return departamentoscheck;
	}

	/**
	 * Retorna un mensaje que indica cuáles fueron las materias reprobadas por el estudiante.<br>
	 * @return Cadena de texto con los nombres de las materias reprobadas separadas por coma.<br>
	 *         Si el estudiante aprobó todas las materias, retorna el siguiente mensaje: El estudiante aprobó todas las materias.
	 */
	public String darMateriasReprobadas( )
	{
		//TODO Parte2 PuntoG: Complete el método con la documentación dada.
		if(materia1.darNota() < NOTA_MINIMA_PARA_APROBAR || materia2.darNota() < NOTA_MINIMA_PARA_APROBAR || materia3.darNota() < NOTA_MINIMA_PARA_APROBAR || materia4.darNota() < NOTA_MINIMA_PARA_APROBAR)
		{
			String nm1 = "";
			String nm2 = "";
			String nm3 = "";
			String nm4 = "";
			if(materia1.darNota() < NOTA_MINIMA_PARA_APROBAR)
			{
				nm1 =  materia1.darNombre() + ". ";
			}
			if (materia2.darNota() < NOTA_MINIMA_PARA_APROBAR)
			{
				nm2 =  materia2.darNombre()+ ". ";
			}
			if(materia3.darNota() < NOTA_MINIMA_PARA_APROBAR)
			{
				nm3 =  materia3.darNombre()+ ". ";
			}
			if (materia4.darNota() < NOTA_MINIMA_PARA_APROBAR)
			{
				nm4 = materia4.darNombre()+ ". ";
			}
			String rbp = nm1 + nm2 + nm3 + nm4;
			return rbp;
		}
		else 
		{
			return "El estudiante aprobó todas las materias.";
		}
	}

	/**
	 * Retorna el estado del estudiante.<br>
	 * Si su promedio es inferior a PROMEDIO_MINIMO_NO_MATRICULA_CONDICIONAL su estado queda en matrícula condicional.<br>
	 * Si su promedio es superior o igual a PROMEDIO_MINIMO_BECA su estado queda en beca.<br>
	 * En los otros casos su estado es normal.
	 * @return En caso de estar en matrícula condicional debe retornar la constante MATRICULA_CONDICIONAL.<br>
	 *         En caso de estar becado debe retornar la constante BECADO.<br>
	 *         En otro caso retorna la constante NORMAL.
	 */
	public String darEstadoEstudiante( )
	{
		//TODO Parte2 PuntoJ: Complete el método con la documentación dada.
		double promedioMaterias= calcularPromedio();
		String estado = "";
		if(promedioMaterias < PROMEDIO_MINIMO_NO_MATRICULA_CONDICIONAL)
		{
			estado = MATRICULA_CONDICIONAL;
		}
		else
		{
			if(promedioMaterias >= PROMEDIO_MINIMO_BECA)
			{
				estado = BECADO;
			}
			else
			{
				estado = NORMAL;
			}
		}
		return estado;
	}

	/**
	 * Busca una materia con el nombre dado por parámetro.
	 * @param pNombre Nombre de la materia. pNombre != null && pNombre != "".
	 * @return Materia que tiene el nombre dado por parámetro, null en caso de no encontrarla.
	 */
	public Materia buscarMateria( String pNombre )
	{
		//TODO Parte2 PuntoH: Complete el método con la documentación dada.
		if(materia1.darNombre() == pNombre)
		{
			return materia1;
		}
		if(materia2.darNombre() == pNombre)
		{
			return materia2;
		}
		if(materia3.darNombre() == pNombre)
		{
			return materia3;
		}
		if(materia4.darNombre() == pNombre)
		{
			return materia4;
		}
		return null;
	}

	/**
	 * Calcula el promedio ponderado de las materias con notas asignadas.<br>
	 * Para calcular el promedio es necesario sumar el producto de la nota de cada materia por el número de créditos de dicha materia y dividir por el total de créditos.
	 * @return Promedio ponderado de las materias. Si ninguna materia tiene nota asignada retorna -1.
	 */
	public double calcularPromedio( )
	{
		//TODO Parte2 PuntoI: Complete el método con la documentación dada.
		double prom1, prom2, prom3, prom4;
		double totalCreditos = 0.0;
		double promPonderado = 0.0;
		if (materia1.darNota() == -1 && materia2.darNota() == -1 && materia3.darNota() == -1 && materia4.darNota() == -1)
		{
			return -1;
		}
		else
		{
			prom1 = (materia1.darNota() * materia1.darNumeroCreditos());
			prom2 = (materia2.darNota() * materia2.darNumeroCreditos());
			prom3 = (materia3.darNota() * materia3.darNumeroCreditos());
			prom4 = (materia4.darNota() * materia4.darNumeroCreditos());
			if(materia1.darNota()!= -1)
			{
				totalCreditos+=materia1.darNumeroCreditos();
				promPonderado += prom1;
			}
			if(materia2.darNota()!= -1)
			{
				totalCreditos+=materia2.darNumeroCreditos();
				promPonderado += prom2;
			}
			if(materia3.darNota()!= -1)
			{
				totalCreditos+=materia3.darNumeroCreditos();
				promPonderado += prom3;
			}if(materia4.darNota()!= -1)
			{
				totalCreditos+=materia4.darNumeroCreditos();
				promPonderado += prom4;
			}
			promPonderado = promPonderado/ totalCreditos;
			return promPonderado;
		}
	}

	/**
	 * Asigna una nota dada a la materia que tiene el número dado por parámetro.<br>
	 * @param pNumeroMateria Número de la materia a la que va a ser asignada la nota. pNumeroMateria > 0 && pNumeroMateria < 5.
	 * @param pNota Nota de la materia.
	 * @return True si se pudo asignar la nota a la materia. False si pNota no está dentro del rango de notas.
	 */
	public boolean asignarNota( int pNumeroMateria, double pNota )
	{
		boolean pudoAsignarNota = false;

		if( pNota >= NOTA_MINIMA && pNota <= NOTA_MAXIMA )
		{
			if( pNumeroMateria == 1 )
			{
				materia1.asignarNota( pNota );
				pudoAsignarNota = true;
			}
			else if( pNumeroMateria == 2 )
			{
				materia2.asignarNota( pNota );
				pudoAsignarNota = true;
			}
			else if( pNumeroMateria == 3 )
			{
				materia3.asignarNota( pNota );
				pudoAsignarNota = true;
			}
			else if( pNumeroMateria == 4 )
			{
				materia4.asignarNota( pNota );
				pudoAsignarNota = true;
			}
		}

		return pudoAsignarNota;
	}

	/**
	 * Modifica la materia que tiene el número por parámetro.<br>
	 * <b> post: </b> La materia dada cambió de nombre, departamento y número de créditos.
	 * @param pNumeroMateria Número de la materia que va a ser modificada. pNumeroMateria > 0 && pNumeroMateria < 5.
	 * @param pNombreMateria Nuevo nombre de la materia. pNombreMateria != null && pNombreMateria != "".
	 * @param pDepartamento Nombre del departamento que ofrece la materia. pDepartamento != null && pDepartamento != "" && (pDepartamento == INGENIERIA_DE_SISTEMAS ||
	 *        pDepartamento == INGENIERIA_INDUSTRIAL || pDepartamento == MATEMATICAS || pDepartamento == QUIMICA || pDepartamento == FISICA || pDepartamento == MICROBIOLOGIA).
	 * @param pNumeroCreditos Número de créditos de la materia. pNumeroCredito > 0.
	 */
	public void modificarMateria( int pNumeroMateria, String pNombreMateria, String pDepartamento, int pNumeroCreditos )
	{
		if( pNumeroMateria == 1 )
		{
			materia1.modificar( pNombreMateria, pDepartamento, pNumeroCreditos );
		}
		else if( pNumeroMateria == 2 )
		{
			materia2.modificar( pNombreMateria, pDepartamento, pNumeroCreditos );
		}
		else if( pNumeroMateria == 3 )
		{
			materia3.modificar( pNombreMateria, pDepartamento, pNumeroCreditos );
		}
		else if( pNumeroMateria == 4 )
		{
			materia4.modificar( pNombreMateria, pDepartamento, pNumeroCreditos );
		}
	}

	// -------------------------------------------------------------
	// Puntos de extensión
	// -------------------------------------------------------------

	/**
	 * Método para la extensión 1.
	 * @return Respuesta 1.
	 */
	public String metodo1( )
	{
		return "Respuesta 1.";
	}

	/**
	 * Método para la extensión 2.
	 * @return Respuesta 2.
	 */
	public String metodo2( )
	{
		return "Respuesta 2.";
	}
}