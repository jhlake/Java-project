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
package uniandes.cupi2.certificadoNotas.test;

import uniandes.cupi2.certificadoNotas.mundo.CertificadoNotas;
import uniandes.cupi2.certificadoNotas.mundo.Materia;
import junit.framework.TestCase;

/**
 * Clase usada para verificar que los métodos de la clase CertificadoNotas están correctamente implementados.
 */
public class CertificadoNotasTest extends TestCase
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Clase donde se harán las pruebas.
     */
    private CertificadoNotas certificadoNotas;

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Escenario 1: Construye un nuevo CertificadoNotas sin notas.
     */
    public void setupEscenario1( )
    {
        certificadoNotas = new CertificadoNotas( );
    }

    /**
     * Escenario 2: Construye un nuevo CertificadoNotas con notas en 2 materias.
     */
    public void setupEscenario2( )
    {
        certificadoNotas = new CertificadoNotas( );
        certificadoNotas.modificarMateria( 2, "Introducción ISIS", Materia.INGENIERIA_DE_SISTEMAS, 3 );
        certificadoNotas.asignarNota( 1, 3 );
        certificadoNotas.asignarNota( 2, 5 );
    }

    /**
     * Escenario 3: Construye un nuevo CertificadoNotas con notas en 4 materias.
     */
    public void setupEscenario3( )
    {
        certificadoNotas = new CertificadoNotas( );
        certificadoNotas.asignarNota( 1, 3 );
        certificadoNotas.asignarNota( 2, 5 );
        certificadoNotas.asignarNota( 3, 4 );
        certificadoNotas.asignarNota( 4, 5 );
    }

    /**
     * Prueba 1: Se encarga de verificar el método constructor de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * CertificadoNotas<br>
     * darMateria1<br>
     * darMateria2<br>
     * darMateria3<br>
     * darMateria4<br>
     * <b> Caso de prueba 1:</b><br>
     * Construye correctamente las materias, cada uno de los valores corresponde al esperado.<br>
     */
    public void testCertificadoNotas( )
    {
        setupEscenario1( );

        assertNotNull( "La materia 1 no debería ser nula.", certificadoNotas.darMateria1( ) );
        assertNotNull( "La materia 2 no debería ser nula.", certificadoNotas.darMateria2( ) );
        assertNotNull( "La materia 3 no debería ser nula.", certificadoNotas.darMateria3( ) );
        assertNotNull( "La materia 4 no debería ser nula.", certificadoNotas.darMateria4( ) );

        assertEquals( "El nombre de la materia 1 no corresponde.", "APO1", certificadoNotas.darMateria1( ).darNombre( ) );
        assertEquals( "El nombre de la materia 2 no corresponde.", "Cálculo Diferencial", certificadoNotas.darMateria2( ).darNombre( ) );
        assertEquals( "El nombre de la materia 3 no corresponde.", "Biología Celular", certificadoNotas.darMateria3( ).darNombre( ) );
        assertEquals( "El nombre de la materia 4 no corresponde.", "Laboratorio Química", certificadoNotas.darMateria4( ).darNombre( ) );

        assertEquals( "El departamento de la materia 1 no corresponde.", Materia.INGENIERIA_DE_SISTEMAS, certificadoNotas.darMateria1( ).darDepartamento( ) );
        assertEquals( "El departamento de la materia 2 no corresponde.", Materia.MATEMATICAS, certificadoNotas.darMateria2( ).darDepartamento( ) );
        assertEquals( "El departamento de la materia 3 no corresponde.", Materia.MICROBIOLOGIA, certificadoNotas.darMateria3( ).darDepartamento( ) );
        assertEquals( "El departamento de la materia 4 no corresponde.", Materia.QUIMICA, certificadoNotas.darMateria4( ).darDepartamento( ) );

        assertEquals( "El número de créditos de la materia 1 no corresponde.", 3, certificadoNotas.darMateria1( ).darNumeroCreditos( ) );
        assertEquals( "El número de créditos de la materia 2 no corresponde.", 3, certificadoNotas.darMateria2( ).darNumeroCreditos( ) );
        assertEquals( "El número de créditos de la materia 3 no corresponde.", 3, certificadoNotas.darMateria3( ).darNumeroCreditos( ) );
        assertEquals( "El número de créditos de la materia 4 no corresponde.", 1, certificadoNotas.darMateria4( ).darNumeroCreditos( ) );
    }

    /**
     * Prueba 2: Se encarga de verificar el método darNumeroMateriasDepartamento de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * darNumeroMateriasDepartamento<br>
     * <b> Casos de prueba:</b><br>
     * 1. El departamento no tiene materias.<br>
     * 2. El departamento tiene 1 materia.<br>
     * 3. El departamento tiene 2 materias.<br>
     */
    public void testDarNumeroMateriasDepartamento( )
    {
        setupEscenario2( );

        // 1
        assertEquals( "El resultado debería ser 0.", 0, certificadoNotas.darNumeroMateriasDepartamento( Materia.INGENIERIA_INDUSTRIAL ) );

        // 2
        assertEquals( "El resultado debería ser 1.", 1, certificadoNotas.darNumeroMateriasDepartamento( Materia.QUIMICA ) );

        // 3
        assertEquals( "El resultado debería ser 2.", 2, certificadoNotas.darNumeroMateriasDepartamento( Materia.INGENIERIA_DE_SISTEMAS ) );
    }

    /**
     * Prueba 3: Se encarga de verificar el método darMateriasReprobadas de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * darMateriasReprobadas<br>
     * <b> Casos de prueba:</b><br>
     * 1. Las materias tienen notas y 1 está reprobada.<br>
     * 2. Las materias tienen notas y están aprobadas.<br>
     */
    public void testDarMateriasReprobadas( )
    {
        setupEscenario2( );

        // 1
        certificadoNotas.asignarNota( 3, 2 );
        certificadoNotas.asignarNota( 4, 5 );
        assertTrue( "El mensaje debería incluir Biología Celular.", certificadoNotas.darMateriasReprobadas( ).contains( "Biología Celular" ) );

        // 2
        certificadoNotas.asignarNota( 3, 5 );
        assertEquals( "El mensaje debería ser: El estudiante aprobó todas las materias.", "El estudiante aprobó todas las materias.", certificadoNotas.darMateriasReprobadas( ) );
    }

    /**
     * Prueba 4: Se encarga de verificar el método darEstadoEstudiante de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * darEstadoEstudiante<br>
     * asignarNota<br>
     * <b> Casos de prueba:</b><br>
     * 1. El estudiante tiene matrícula condicional.<br>
     * 2. El estudiante está becado.<br>
     * 3. El estado del estudiante es normal.<br>
     */
    public void testDarEstadoEstudiante( )
    {
        setupEscenario1( );

        // 1
        certificadoNotas.asignarNota( 1, 2 );
        certificadoNotas.asignarNota( 2, 2 );
        certificadoNotas.asignarNota( 3, 2 );
        certificadoNotas.asignarNota( 4, 2 );
        assertEquals( "El estado debería ser matrícula condicional.", CertificadoNotas.MATRICULA_CONDICIONAL, certificadoNotas.darEstadoEstudiante( ) );

        // 2
        certificadoNotas.asignarNota( 1, 5 );
        certificadoNotas.asignarNota( 2, 5 );
        certificadoNotas.asignarNota( 3, 5 );
        certificadoNotas.asignarNota( 4, 4.5 );
        assertEquals( "El estado debería ser becado.", CertificadoNotas.BECADO, certificadoNotas.darEstadoEstudiante( ) );

        // 3
        certificadoNotas.asignarNota( 1, 3 );
        certificadoNotas.asignarNota( 2, 3 );
        certificadoNotas.asignarNota( 3, 4 );
        certificadoNotas.asignarNota( 4, 4.5 );
        assertEquals( "El estado debería ser normal.", CertificadoNotas.NORMAL, certificadoNotas.darEstadoEstudiante( ) );
    }

    /**
     * Prueba 5: Se encarga de verificar el método buscarMateria de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * buscarMateria<br>
     * <b> Casos de prueba:</b><br>
     * 1. Encuentra la materia.<br>
     * 1. No encuentra la materia.<br>
     */
    public void testBuscarMateria( )
    {
        setupEscenario1( );
        // 1
        assertNotNull( "La materia deberías existir.", certificadoNotas.buscarMateria( "APO1" ) );
        assertEquals( "El departamento no coincide.", Materia.INGENIERIA_DE_SISTEMAS, certificadoNotas.buscarMateria( "APO1" ).darDepartamento( ) );
        assertEquals( "El número de créditos no coincide.", 3, certificadoNotas.buscarMateria( "APO1" ).darNumeroCreditos( ) );

        // 2
        assertNull( "La materia no debería existir.", certificadoNotas.buscarMateria( "inexistente" ) );
    }

    /**
     * Prueba 6: Se encarga de verificar el método calcularPromedio de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * calcularPromedio<br>
     * asignarNota<br>
     * <b> Casos de prueba:</b><br>
     * 1. Cuando no hay notas.<br>
     * 2. Cuando no todas las materias tienen nota.<br>
     * 3. Cuando todas las materias tienen nota.<br>
     */
    public void testCalcularPromedio( )
    {
        setupEscenario1( );

        // 1
        assertEquals( "El promedio debería ser -1.0", -1.0, certificadoNotas.calcularPromedio( ), 0.0 );

        // 2
        certificadoNotas.asignarNota( 1, 3 );
        certificadoNotas.asignarNota( 4, 5 );
        assertEquals( "El promedio debería ser 3.5", 3.5, certificadoNotas.calcularPromedio( ), 0.0 );

        // 3
        certificadoNotas.asignarNota( 2, 4 );
        certificadoNotas.asignarNota( 3, 5 );
        assertEquals( "El promedio debería ser 4.1", 4.1, certificadoNotas.calcularPromedio( ), 0.0 );
    }

    /**
     * Prueba 7: Se encarga de verificar el método asignarNota de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * asignarNota<br>
     * <b> Casos de prueba:</b><br>
     * 1. La nota que se quiere asignar está por debajo del rango permitido.<br>
     * 2. La nota que se quiere asignar está por encima del rango permitido.<br>
     * 3. La nota que se quiere asignar está dentro del rango permitido.<br>
     */
    public void testAsignarNota( )
    {
        setupEscenario1( );

        // 1
        assertFalse( "No debió asignar la nota a la materia 1 porque está por debajo del rango permitido.", certificadoNotas.asignarNota( 1, 1.0 ) );

        // 2
        assertFalse( "No debió asignar la nota a la materia 2 porque está por encima del rango permitido.", certificadoNotas.asignarNota( 2, 5.5 ) );

        // 3
        assertTrue( "Debió asignar la nota a la materia 3 porque está dentro del rango permitido.", certificadoNotas.asignarNota( 3, 4.5 ) );
    }

    /**
     * Prueba 8: Se encarga de verificar el método modificarMateria de la clase.<br>
     * <b> Métodos a probar: </b> <br>
     * modificarMateria<br>
     * darMateria<br>
     * <b> Casos de prueba:</b><br>
     * 1. La materia se modifica y su nota queda en -1.0.<br>
     */
    public void testModificarMateria( )
    {
        setupEscenario3( );
        certificadoNotas.modificarMateria( 4, "Física 1", Materia.FISICA, 4 );
        assertEquals( "El nombre no corresponde.", "Física 1", certificadoNotas.darMateria4( ).darNombre( ) );
        assertEquals( "El departamento no corresponde.", Materia.FISICA, certificadoNotas.darMateria4( ).darDepartamento( ) );
        assertEquals( "El número de créditos no corresponde.", 4, certificadoNotas.darMateria4( ).darNumeroCreditos( ) );
        assertEquals( "La nota no corresponde.", -1.0, certificadoNotas.darMateria4( ).darNota( ), 0.0 );
    }
}