/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogotá - Colombia)
 * Departamento de  Ingeniería  de  Sistemas    y   Computación
 * Licenciado   bajo    el  esquema Academic Free License versión 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_calculadoraNotas
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.calculadoraNotas.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.calculadoraNotas.mundo.Nivel;

/**
 * Clase usada para verificar que los métodos de la clase Nivel estén correctamente implementados.
 */
public class NivelTest
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nivel en el cual se harán las pruebas
     */
    private Nivel nivel;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------

    /**
     * Crea un Nivel con los porcentajes correspondientes a N1 y todas las notas inicializadas en 0.
     */
    @Before
    public void setupEscenario1( )
    {
        nivel = new Nivel( 1, 0.01, 0.04, 0.05, "Tema del nivel" );
    }

    /**
     * Escenario que asigna 5.0 al examen teórico, examen práctico y al ejercicio.
     */
    public void setupEscenario2( )
    {
        nivel.cambiarNotaEjercicio( 5 );
        nivel.cambiarNotaPractico( 5 );
        nivel.cambiarNotaTeorico( 5 );
    }

    /**
     * Escenario que asigna 3.2 al examen teórico, 2.7 al examen práctico y 1.2 al ejercicio.
     */
    public void setupEscenario3( )
    {
        nivel.cambiarNotaEjercicio( 3.2 );
        nivel.cambiarNotaPractico( 2.7 );
        nivel.cambiarNotaTeorico( 1.2 );
    }

    // -----------------------------------------------------------------
    // Métodos de prueba
    // -----------------------------------------------------------------
    /**
     * <b>Prueba 1</b> : verifica el método constructor Nivel.<br>
     * <b>Métodos a probar: </b><br>
     * Nivel<br>
     * darNumero<br>
     * darNotaEjercicio<br>
     * darNotaPractico<br>
     * darNotaTeorico<br>
     * darPorcentajeEjercicio<br>
     * darPorcentajePractico<br>
     * darPorcentajeTeorico<br>
     * darTema<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el nivel correctamente.
     */
    @Test
    public void testNivel( )
    {
        // Caso de prueba 1
        assertEquals( "El nivel asignado no corresponde.", 1, nivel.darNumero( ) );
        assertEquals( "El porcentaje del ejercicio no corresponde.", 0.01, nivel.darPorcentajeEjercicio( ), 0 );
        assertEquals( "El porcentaje del examen práctico no corresponde.", 0.04, nivel.darPorcentajePractico( ), 0 );
        assertEquals( "El porcentaje del examen teórico no corresponde.", 0.05, nivel.darPorcentajeTeorico( ), 0 );
        assertEquals( "El tema no corresponde.", "Tema del nivel", nivel.darTema( ) );
        assertEquals( "La nota inicial del ejercicio no corresponde.", 0, nivel.darNotaEjercicio( ), 0 );
        assertEquals( "La nota inicial del examen práctico no corresponde.", 0, nivel.darNotaPractico( ), 0 );
        assertEquals( "La nota inicial del examen teórico no corresponde.", 0, nivel.darNotaTeorico( ), 0 );

    }

    /**
     * <b>Prueba 2</b> : verifica el método calcularPorcentajeTotal.<br>
     * <b>Métodos a probar: </b><br>
     * calcularPorcentajeTotal<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se calcula correctamente el porcentaje total del nivel.
     */
    @Test
    public void testCalcularPorcentajeTotal( )
    {
        // Caso de prueba 1
        assertEquals( "El porcentaje total del nivel no corresponde.", 0.1, nivel.calcularPorcentajeTotal( ), 0 );
    }

    /**
     * <b>Prueba 3</b> : verifica el método calcularNota.<br>
     * <b>Métodos a probar: </b><br>
     * calcularNota<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se calcula correctamente la nota del nivel, con todas las notas en 0.<br>
     * 2. Se calcula correctamente la nota del del nivel, con todas las notas en 5.<br>
     * 3. Se calcula correctamente la nota del nivel, con 3.2 en el examen teórico, 2.7 en el examen práctico y 1.2 en el ejercicio.
     */
    @Test
    public void testCalcularNota( )
    {
        // Caso de prueba 1
        assertEquals( "La nota del nivel no corresponde.", 0, nivel.calcularNota( ), 0 );

        // Caso de prueba 2
        setupEscenario2( );
        assertEquals( "La nota del nivel no corresponde.", 0.5, nivel.calcularNota( ), 0.1 );

        // Caso de prueba 3
        setupEscenario3( );
        assertEquals( "La nota del nivel no corresponde.", 0.2, nivel.calcularNota( ), 0.1 );
    }

    /**
     * <b>Prueba 4</b> : verifica el método calcularNotaSobreCinco.<br>
     * <b>Métodos a probar: </b><br>
     * calcularNotaSobreCinco<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se calcula correctamente la nota del nivel sobre cinco, con todas las notas en 0.<br>
     * 2. Se calcula correctamente la nota del del nivel sobre cinco, con todas las notas en 5.<br>
     * 3. Se calcula correctamente la nota del nivel sobre cinco, con 3.2 en el examen teórico, 2.7 en el examen práctico y 1.2 en el ejercicio.
     */
    @Test
    public void testCalcularNotaSobreCinco( )
    {
        // Caso de prueba 1
        assertEquals( "La nota del nivel no corresponde.", 0, nivel.calcularNotaSobreCinco( ), 0 );

        // Caso de prueba 2
        setupEscenario2( );
        assertEquals( "La nota del nivel no corresponde.", 5, nivel.calcularNotaSobreCinco( ), 0.1 );

        // Caso de prueba 3
        setupEscenario3( );
        assertEquals( "La nota del nivel no corresponde.", 2, nivel.calcularNotaSobreCinco( ), 0.1 );
    }
}
