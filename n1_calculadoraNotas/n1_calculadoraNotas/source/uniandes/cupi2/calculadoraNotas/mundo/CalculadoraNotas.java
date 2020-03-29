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
 * Representa el conjunto de notas del curso APO1, para un estudiante.
 */
public class CalculadoraNotas
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Representa el nivel 1
     */
    private Nivel n1; 

    /**
     * Representa el nivel 2
     */
    private Nivel n2; 
    
    /**
     * Representa el nivel 3
     */
    private Nivel n3; 
    /**
     * Representa el nivel 4
     */
    private Nivel n4; 
    /**
     * Representa el nivel 5
     */
    private Nivel n5; 
    /**
     * Representa el nivel 6
     */
    private Nivel n6; 
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva calculadora de notas. <br>
     * <b>post: </b> Se inicializaron todos los niveles según las reglas del curso .<br>
     */
    public CalculadoraNotas()
    {
       n1 = new Nivel(1, 0.01, 0.04, 0.05, "Problema Soluciones y Programas"); 
       n2 = new Nivel(2, 0.02, 0.04, 0.14, "Definición de situaciones y manejo de casos");
       n3 = new Nivel(3, 0.02, 0.05, 0.13, "Manejo de grupos de atributos");
       n4 = new Nivel(4, 0.02, 0.03, 0.07, "Definición y cumplimiento de responsabilidades");
       n5 = new Nivel(5, 0.02, 0.04, 0.07, "Construcción de la interfaz gráfica");
       n6 = new Nivel(6, 0.05, 0.06, 0.14, "Manejo de estructuras de dos dimensiones y persistencia");      
       
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nivel 1.
     * @return Nivel 1.
     */
    public Nivel darN1()
    {
       return n1; 
    }

    /**
     * Retorna el nivel 2.
     * @return Nivel 2.
     */
    public Nivel darN2( )
    {
        return n2; 
    }

    /**
     * Retorna el nivel 3.
     * @return Nivel 3.
     */
    public Nivel darN3( )
    {
        return n3; 
    }

    /**
     * Retorna el nivel 4.
     * @return Nivel 4.
     */
    public Nivel darN4( )
    {
       return n4; 
    }

    /**
     * Retorna el nivel 5.
     * @return Nivel 5.
     */
    public Nivel darN5( )
    {
        return n5; 
    }

    /**
     * Retorna el nivel 6.
     * @return Nivel 6.
     */
    public Nivel darN6( )
    {
       return n6; 
    }

    /**
     * Retorna el promedio de los ejercicios de todos los niveles. <br>
     * @return Nota promedio de los ejercicios.
     */
    public double darNotaPromedioEjercicios( )
    {
    	return (n1.darNotaEjercicio() + n2.darNotaEjercicio() + n3.darNotaEjercicio() + n4.darNotaEjercicio() + n5.darNotaEjercicio() + n6.darNotaEjercicio() )/6; 
    }

    /**
     * Retorna el promedio de los exámenes prácticos de todos los niveles. <br>
     * @return Promedio ponderado de los exámenes prácticos.
     */
    public double darNotaPromedioPracticos( )
    {
    	return (n1.darNotaPractico() + n2.darNotaPractico() + n3.darNotaPractico() + n4.darNotaPractico() + n5.darNotaPractico() + n6.darNotaPractico() )/6; 
    }

    /**
     * Retorna el promedio de los exámenes teóricos de todos los niveles. <br>
     * @return Promedio ponderado de los exámenes teóricos.
     */
    public double darNotaPromedioTeoricos( )
    {
    	return (n1.darNotaTeorico() + n2.darNotaTeorico() + n3.darNotaTeorico() + n4.darNotaTeorico() + n5.darNotaTeorico() + n6.darNotaTeorico() )/6; 
    }

    /**
     * Retorna la nota final del curso, teniendo en cuenta la nota de cada nivel. <br>
     * @return Nota definitiva del curso.
     */
    public double darNotaDefinitiva( )
    {
    	return n1.calcularNota() + n2.calcularNota() + n3.calcularNota() + n4.calcularNota() + n5.calcularNota() + n6.calcularNota(); 
    	
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Extensión 1.
     * @return respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Extensión 2.
     * @return respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}
