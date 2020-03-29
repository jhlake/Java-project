/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogot� - Colombia)
 * Departamento de  Ingenier�a  de  Sistemas    y   Computaci�n
 * Licenciado   bajo    el  esquema Academic Free License versi�n 2.1
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

import uniandes.cupi2.calculadoraNotas.mundo.CalculadoraNotas;
import uniandes.cupi2.calculadoraNotas.mundo.Nivel;

/**
 * Clase usada para verificar que los m�todos de la clase CalculadoraNotas est�n correctamente implementados.
 */
public class CalculadoraNotasTest
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Calculadora de notas en la que se realizar�n las pruebas.
     */
    private CalculadoraNotas calculadoraNotas;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea una instancia de la clase calculadoraNotas. Este m�todo se ejecuta antes de cada m�todo de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        calculadoraNotas = new CalculadoraNotas( );
    }

    /**
     * Crea una calculadora de notas, asignando en 5 todas las notas de todos los niveles.
     */
    public void setupEscenario2( )
    {
        Nivel nivel1 = calculadoraNotas.darN1( );
        Nivel nivel2 = calculadoraNotas.darN2( );
        Nivel nivel3 = calculadoraNotas.darN3( );
        Nivel nivel4 = calculadoraNotas.darN4( );
        Nivel nivel5 = calculadoraNotas.darN5( );
        Nivel nivel6 = calculadoraNotas.darN6( );

        nivel1.cambiarNotaEjercicio( 5 );
        nivel2.cambiarNotaEjercicio( 5 );
        nivel3.cambiarNotaEjercicio( 5 );
        nivel4.cambiarNotaEjercicio( 5 );
        nivel5.cambiarNotaEjercicio( 5 );
        nivel6.cambiarNotaEjercicio( 5 );

        nivel1.cambiarNotaPractico( 5 );
        nivel2.cambiarNotaPractico( 5 );
        nivel3.cambiarNotaPractico( 5 );
        nivel4.cambiarNotaPractico( 5 );
        nivel5.cambiarNotaPractico( 5 );
        nivel6.cambiarNotaPractico( 5 );

        nivel1.cambiarNotaTeorico( 5 );
        nivel2.cambiarNotaTeorico( 5 );
        nivel3.cambiarNotaTeorico( 5 );
        nivel4.cambiarNotaTeorico( 5 );
        nivel5.cambiarNotaTeorico( 5 );
        nivel6.cambiarNotaTeorico( 5 );
    }

    /**
     * Crea una calculadora de notas, asignando en 3 todas las notas de todos los niveles.
     */
    public void setupEscenario3( )
    {
        Nivel nivel1 = calculadoraNotas.darN1( );
        Nivel nivel2 = calculadoraNotas.darN2( );
        Nivel nivel3 = calculadoraNotas.darN3( );
        Nivel nivel4 = calculadoraNotas.darN4( );
        Nivel nivel5 = calculadoraNotas.darN5( );
        Nivel nivel6 = calculadoraNotas.darN6( );

        nivel1.cambiarNotaEjercicio( 3 );
        nivel2.cambiarNotaEjercicio( 3 );
        nivel3.cambiarNotaEjercicio( 3 );
        nivel4.cambiarNotaEjercicio( 3 );
        nivel5.cambiarNotaEjercicio( 3 );
        nivel6.cambiarNotaEjercicio( 3 );

        nivel1.cambiarNotaPractico( 3 );
        nivel2.cambiarNotaPractico( 3 );
        nivel3.cambiarNotaPractico( 3 );
        nivel4.cambiarNotaPractico( 3 );
        nivel5.cambiarNotaPractico( 3 );
        nivel6.cambiarNotaPractico( 3 );

        nivel1.cambiarNotaTeorico( 3 );
        nivel2.cambiarNotaTeorico( 3 );
        nivel3.cambiarNotaTeorico( 3 );
        nivel4.cambiarNotaTeorico( 3 );
        nivel5.cambiarNotaTeorico( 3 );
        nivel6.cambiarNotaTeorico( 3 );
    }

    /**
     * Crea una calculadora de notas, asignando notas diferentes a ejercicios, pr�cticos y te�ricos de los diferentes niveles.
     */
    public void setupEscenario4( )
    {
        Nivel nivel1 = calculadoraNotas.darN1( );
        Nivel nivel2 = calculadoraNotas.darN2( );
        Nivel nivel3 = calculadoraNotas.darN3( );
        Nivel nivel4 = calculadoraNotas.darN4( );
        Nivel nivel5 = calculadoraNotas.darN5( );
        Nivel nivel6 = calculadoraNotas.darN6( );

        nivel1.cambiarNotaEjercicio( 4.8 );
        nivel2.cambiarNotaEjercicio( 4.5 );
        nivel3.cambiarNotaEjercicio( 4.3 );
        nivel4.cambiarNotaEjercicio( 3.4 );
        nivel5.cambiarNotaEjercicio( 2.6 );
        nivel6.cambiarNotaEjercicio( 1.8 );

        nivel1.cambiarNotaPractico( 2.9 );
        nivel2.cambiarNotaPractico( 3.7 );
        nivel3.cambiarNotaPractico( 2.5 );
        nivel4.cambiarNotaPractico( 5.0 );
        nivel5.cambiarNotaPractico( 1.6 );
        nivel6.cambiarNotaPractico( 2.1 );

        nivel1.cambiarNotaTeorico( 2.7 );
        nivel2.cambiarNotaTeorico( 4.4 );
        nivel3.cambiarNotaTeorico( 2.8 );
        nivel4.cambiarNotaTeorico( 2.6 );
        nivel5.cambiarNotaTeorico( 1.8 );
        nivel6.cambiarNotaTeorico( 4.6 );
    }

    // -----------------------------------------------------------------
    // M�todos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el m�todo CalculadoraNotas.<br>
     * <b>M�todos a probar:</b><br>
     * CalculadoraNotas<br>
     * darN1<br>
     * darN2<br>
     * darN3<br>
     * darN4<br>
     * darN5<br>
     * darN6<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea la calculadoraNotas correctamente, y todos los niveles fueron inicializados correctamente.
     */
    @Test
    public void testCalculadoraNotas( )
    {
        Nivel nivel1 = calculadoraNotas.darN1( );
        Nivel nivel2 = calculadoraNotas.darN2( );
        Nivel nivel3 = calculadoraNotas.darN3( );
        Nivel nivel4 = calculadoraNotas.darN4( );
        Nivel nivel5 = calculadoraNotas.darN5( );
        Nivel nivel6 = calculadoraNotas.darN6( );

        assertNotNull( "No se inicializ� N1 correctamente.", nivel1 );
        assertNotNull( "No se inicializ� N2 correctamente.", nivel2 );
        assertNotNull( "No se inicializ� N3 correctamente.", nivel3 );
        assertNotNull( "No se inicializ� N4 correctamente.", nivel4 );
        assertNotNull( "No se inicializ� N5 correctamente.", nivel5 );
        assertNotNull( "No se inicializ� N6 correctamente.", nivel6 );

        assertEquals( "El n�mero de N1 no es correcto", 1, nivel1.darNumero( ), 0 );
        assertEquals( "El n�mero de N2 no es correcto", 2, nivel2.darNumero( ), 0 );
        assertEquals( "El n�mero de N3 no es correcto", 3, nivel3.darNumero( ), 0 );
        assertEquals( "El n�mero de N4 no es correcto", 4, nivel4.darNumero( ), 0 );
        assertEquals( "El n�mero de N5 no es correcto", 5, nivel5.darNumero( ), 0 );
        assertEquals( "El n�mero de N6 no es correcto", 6, nivel6.darNumero( ), 0 );

        assertEquals( "El porcentaje del ejercicio asignado a N1 no es correcto", 0.01, nivel1.darPorcentajeEjercicio( ), 0 );
        assertEquals( "El porcentaje del ejercicio asignado a N2 no es correcto", 0.02, nivel2.darPorcentajeEjercicio( ), 0 );
        assertEquals( "El porcentaje del ejercicio asignado a N3 no es correcto", 0.02, nivel3.darPorcentajeEjercicio( ), 0 );
        assertEquals( "El porcentaje del ejercicio asignado a N4 no es correcto", 0.02, nivel4.darPorcentajeEjercicio( ), 0 );
        assertEquals( "El porcentaje del ejercicio asignado a N5 no es correcto", 0.02, nivel5.darPorcentajeEjercicio( ), 0 );
        assertEquals( "El porcentaje del ejercicio asignado a N6 no es correcto", 0.05, nivel6.darPorcentajeEjercicio( ), 0 );

        assertEquals( "El porcentaje del examen pr�ctico asignado a N1 no es correcto", 0.04, nivel1.darPorcentajePractico( ), 0 );
        assertEquals( "El porcentaje del examen pr�ctico asignado a N2 no es correcto", 0.04, nivel2.darPorcentajePractico( ), 0 );
        assertEquals( "El porcentaje del examen pr�ctico asignado a N3 no es correcto", 0.05, nivel3.darPorcentajePractico( ), 0 );
        assertEquals( "El porcentaje del examen pr�ctico asignado a N4 no es correcto", 0.03, nivel4.darPorcentajePractico( ), 0 );
        assertEquals( "El porcentaje del examen pr�ctico asignado a N5 no es correcto", 0.04, nivel5.darPorcentajePractico( ), 0 );
        assertEquals( "El porcentaje del examen pr�ctico asignado a N6 no es correcto", 0.06, nivel6.darPorcentajePractico( ), 0 );

        assertEquals( "El porcentaje del examen te�rico asignado a N1 no es correcto", 0.05, nivel1.darPorcentajeTeorico( ), 0 );
        assertEquals( "El porcentaje del examen te�rico asignado a N2 no es correcto", 0.14, nivel2.darPorcentajeTeorico( ), 0 );
        assertEquals( "El porcentaje del examen te�rico asignado a N3 no es correcto", 0.13, nivel3.darPorcentajeTeorico( ), 0 );
        assertEquals( "El porcentaje del examen te�rico asignado a N4 no es correcto", 0.07, nivel4.darPorcentajeTeorico( ), 0 );
        assertEquals( "El porcentaje del examen te�rico asignado a N5 no es correcto", 0.07, nivel5.darPorcentajeTeorico( ), 0 );
        assertEquals( "El porcentaje del examen te�rico asignado a N6 no es correcto", 0.14, nivel6.darPorcentajeTeorico( ), 0 );

    }

    /**
     * <b>Prueba 2:</b> Verifica el m�todo darNotaPromedioEjercicios.<br>
     * <b>M�todos a probar:</b><br>
     * darNotaPromedioEjercicios<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se calcula correctamente el promedio de ejercicios con todas las notas asignadas en 0.<br>
     * 2. Se calcula correctamente el promedio de ejercicios con todas las notas asignadas en 5.<br>
     * 3. Se calcula correctamente el promedio de ejercicios con todas las notas asignadas en 3.<br>
     * 4. Se calcula correctamente el promedio de ejercicios con diferentes notas.<br>
     */
    @Test
    public void testDarNotaPromedioEjercicios( )
    {
        // Caso de prueba 1
        assertEquals( "La nota promedio de los ejercicios no es correcta", 0, calculadoraNotas.darNotaPromedioEjercicios( ), 0.1 );

        // Caso de prueba 2
        setupEscenario2( );
        assertEquals( "La nota promedio de los ejercicios no es correcta", 5, calculadoraNotas.darNotaPromedioEjercicios( ), 0.1 );

        // Caso de prueba 3
        setupEscenario3( );
        assertEquals( "La nota promedio de los ejercicios no es correcta", 3, calculadoraNotas.darNotaPromedioEjercicios( ), 0.1 );

        // Caso de prueba 4
        setupEscenario4( );
        assertEquals( "La nota promedio de los ejercicios no es correcta", 3.56, calculadoraNotas.darNotaPromedioEjercicios( ), 0.1 );

    }

    /**
     * <b>Prueba 3:</b> Verifica el m�todo darNotaPromedioPracticos.<br>
     * <b>M�todos a probar:</b><br>
     * darNotaPromedioPracticos<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se calcula correctamente el promedio de ex�menes pr�cticos con todas las notas asignadas en 0.<br>
     * 2. Se calcula correctamente el promedio de ex�menes pr�cticos con todas las notas asignadas en 5.<br>
     * 3. Se calcula correctamente el promedio de ex�menes pr�cticos con todas las notas asignadas en 3.<br>
     * 4. Se calcula correctamente el promedio de ex�menes pr�cticos con diferentes notas.<br>
     */
    @Test
    public void testDarNotaPromedioPracticos( )
    {
        // Caso de prueba 1
        assertEquals( "La nota promedio de examenes pr�cticos no es correcta", 0, calculadoraNotas.darNotaPromedioPracticos( ), 0.1 );

        // Caso de prueba 2
        setupEscenario2( );
        assertEquals( "La nota promedio de examenes pr�cticos no es correcta", 5, calculadoraNotas.darNotaPromedioPracticos( ), 0.1 );

        // Caso de prueba 3
        setupEscenario3( );
        assertEquals( "La nota promedio de examenes pr�cticos no es correcta", 3, calculadoraNotas.darNotaPromedioPracticos( ), 0.1 );

        // Caso de prueba 4
        setupEscenario4( );
        assertEquals( "La nota promedio de examenes pr�cticos no es correcta", 2.96, calculadoraNotas.darNotaPromedioPracticos( ), 0.1 );

    }

    /**
     * <b>Prueba 4:</b> Verifica el m�todo darNotaPromedioTeoricos.<br>
     * <b>M�todos a probar:</b><br>
     * darNotaPromedioTeoricos<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se calcula correctamente el promedio de ex�menes te�ricos con todas las notas asignadas en 0.<br>
     * 2. Se calcula correctamente el promedio de ex�menes te�ricos con todas las notas asignadas en 5.<br>
     * 3. Se calcula correctamente el promedio de ex�menes te�ricos con todas las notas asignadas en 3.<br>
     * 4. Se calcula correctamente el promedio de ex�menes te�ricos con diferentes notas.<br>
     */
    @Test
    public void testDarNotaPromedioTeoricos( )
    {
        // Caso de prueba 1
        assertEquals( "La nota promedio de examenes te�ricos no es correcta", 0, calculadoraNotas.darNotaPromedioTeoricos( ), 0.1 );

        // Caso de prueba 2
        setupEscenario2( );
        assertEquals( "La nota promedio de examenes te�ricos no es correcta", 5, calculadoraNotas.darNotaPromedioTeoricos( ), 0.1 );

        // Caso de prueba 3
        setupEscenario3( );
        assertEquals( "La nota promedio de examenes te�ricos no es correcta", 3, calculadoraNotas.darNotaPromedioTeoricos( ), 0.1 );

        // Caso de prueba 4
        setupEscenario4( );
        assertEquals( "La nota promedio de examenes te�ricos no es correcta", 3.15, calculadoraNotas.darNotaPromedioTeoricos( ), 0.1 );

    }

    /**
     * <b>Prueba 5:</b> Verifica el m�todo darNotaDefinitiva.<br>
     * <b>M�todos a probar:</b><br>
     * darNotaDefinitiva<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se calcula correctamente la nota definitiva con todas las notas asignadas en 0.<br>
     * 2. Se calcula correctamente la nota definitiva con todas las notas asignadas en 5.<br>
     * 3. Se calcula correctamente la nota definitiva con todas las notas asignadas en 3.<br>
     * 4. Se calcula correctamente la nota definitiva con diferentes notas.<br>
     */
    @Test
    public void testDarNotaDefinitiva( )
    {
        // Caso de prueba 1
        assertEquals( "La nota definitiva calculada no es correcta", 0, calculadoraNotas.darNotaDefinitiva( ), 0.1 );

        // Caso de prueba 2
        setupEscenario2( );
        assertEquals( "La nota definitiva calculada no es correcta", 5, calculadoraNotas.darNotaDefinitiva( ), 0.1 );

        // Caso de prueba 3
        setupEscenario3( );
        assertEquals( "La nota definitiva calculada no es correcta", 3, calculadoraNotas.darNotaDefinitiva( ), 0.1 );

        // Casp de prueba 4
        setupEscenario4( );
        assertEquals( "La nota definitiva calculada no es correcta", 3.2, calculadoraNotas.darNotaDefinitiva( ), 0.1 );

    }

}
