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
package uniandes.cupi2.certificadoNotas.test;

import uniandes.cupi2.certificadoNotas.mundo.Materia;
import junit.framework.TestCase;

/**
 * Clase usada para verificar que los m�todos de la clase Materia est�n correctamente implementados.
 */
public class MateriaTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas.
     */
    private Materia materia;

    // -------------------------------------------------------------
    // M�todos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Construye una nueva Materia.
     */
    public void setupEscenario1( )
    {
        materia = new Materia( "Nombre", Materia.MATEMATICAS, 3 );
    }

    /**
     * Prueba 1: Se encarga de verificar el m�todo constructor de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * Materia<br>
     * darNombre<br>
     * darDepartamento<br>
     * darNumeroCreditos<br>
     * darNota<br>
     * <b> Caso de prueba 1:</b><br>
     * Construye correctamente la materia, cada uno de los valores corresponde al esperado.<br>
     */
    public void testMateria( )
    {
        setupEscenario1( );

        assertEquals( "El nombre de la materia deber�a ser Nombre.", "Nombre", materia.darNombre( ) );
        assertEquals( "El departamento al que pertenece la materia deber�a ser Matem�ticas.", Materia.MATEMATICAS, materia.darDepartamento( ) );
        assertEquals( "El n�mero de cr�ditos de la materia deber�a ser 3.", 3, materia.darNumeroCreditos( ) );
        assertEquals( "La nota de la materia deber�a ser -1.", -1.0, materia.darNota( ), 0.0 );
    }

    /**
     * Prueba 2: Se encarga de verificar el m�todo asignarNota de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * asignarNota<br>
     * darNota<br>
     * <b> Caso de prueba 1:</b><br>
     * Asigna correctamente la nota a la materia.<br>
     */
    public void testAsignarNota( )
    {
        setupEscenario1( );

        materia.asignarNota( 4.5 );
        assertEquals( "La nota de la materia debe ser 4.5", 4.5, materia.darNota( ), 0.0 );
    }

    /**
     * Prueba 3: Se encarga de verificar el m�todo modificar de la clase.<br>
     * <b> M�todos a probar: </b> <br>
     * modificar<br>
     * darNombre<br>
     * darDepartamento<br>
     * darNumeroCreditos<br>
     * darNota<br>
     * <b> Caso de prueba 1:</b><br>
     * Modifica correctamente la materia, cada uno de los valores corresponde al esperado.<br>
     */
    public void testModificar( )
    {
        setupEscenario1( );

        materia.modificar( "Nombre2", Materia.FISICA, 1 );

        assertEquals( "El nombre de la materia deber�a ser Nombre2.", "Nombre2", materia.darNombre( ) );
        assertEquals( "El departamento al que pertenece la materia deber�a ser F�sica.", Materia.FISICA, materia.darDepartamento( ) );
        assertEquals( "El n�mero de cr�ditos de la materia deber�a ser 1.", 1, materia.darNumeroCreditos( ) );
        assertEquals( "La nota de la materia deber�a ser -1.", -1.0, materia.darNota( ), 0.0 );

    }
}