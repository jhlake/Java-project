/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AdministradorResultadosTest.java 651 2006-11-16 15:06:50Z da-romer $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_batallaNaval
 * Autor: Mario Sánchez - 23/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.batallaNaval.testServidor;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.batallaNaval.servidor.*;

/**
 * Esta es la clase usada para verificar que los métodos de la clase AdministradorResultados estén correctamente implementados
 */
public class AdministradorResultadosTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private AdministradorResultados adminResultados;

    /**
     * Es el conjunto de propiedades para configurar las pruebas
     */
    private Properties configuracion;

    /**
     * Es la conexión usada para las pruebas
     */
    private Connection conexionPruebas;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa la base de datos y construye un nuevo AdministradorResultados conectado a esta base de datos
     */
    private void setupEscenario1( )
    {
        adminResultados = null;
        File directorioData = new File( "./test/data" );
        System.setProperty( "derby.system.home", directorioData.getAbsolutePath( ) );
        configuracion = new Properties( );
        configuracion.setProperty( "admin.db.url", "jdbc:derby:testAdmin;create=true" );
        configuracion.setProperty( "admin.test.url", "jdbc:derby:testAdmin" );
        configuracion.setProperty( "admin.db.driver", "org.apache.derby.jdbc.EmbeddedDriver" );
        configuracion.setProperty( "admin.db.shutdown", "jdbc:derby:;shutdown=true" );
        configuracion.setProperty( "admin.db.path", "./test/data" );

        // Conectar a la base de datos
        try
        {
            String driver = configuracion.getProperty( "admin.db.driver" );
            Class.forName( driver ).newInstance( );
            String url = configuracion.getProperty( "admin.db.url" );
            conexionPruebas = DriverManager.getConnection( url );
        }
        catch( Exception e )
        {
            fail( "Falló la conexión a la base de datos" );
        }

        try
        {
            // Crear la tabla de resultados si es necesario
            crearTablas( );
        }
        catch( SQLException e1 )
        {
            fail( "No se pudo crear la tabla" );
        }

        try
        {
            // Limpia todos los datos existentes e inserta datos iniciales para las pruebas
            inicializarTabla( );
        }
        catch( SQLException e2 )
        {
            fail( "No se pudo crear la tabla" );
        }

        // Construir el administrador
        adminResultados = new AdministradorResultados( configuracion );
        try
        {
            adminResultados.conectarABD( );
        }
        catch( Exception e3 )
        {
            fail( "No se pudo conectar el administrador a la BD" );
        }
    }

    /**
     * Crea las tablas necesarias para el administrador de resultados
     * @throws SQLException Se lanza esta excepción si hay problemas creando la tabla
     */
    private void crearTablas( ) throws SQLException
    {
        Statement s = conexionPruebas.createStatement( );

        boolean crearTabla = false;
        try
        {
            // Verificar si ya existe la tabla resultados
            s.executeQuery( "SELECT * FROM resultados" );
        }
        catch( SQLException se )
        {
            crearTabla = true;
        }

        if( crearTabla )
        {
            s.execute( "CREATE TABLE resultados (nombre varchar(32), ganados int, perdidos int, PRIMARY KEY (nombre))" );
        }
        s.close( );
    }

    /**
     * Limpia la tabla de resultados e inserta valores iniciales para las pruebas
     * @throws SQLException Se lanza esta excepción si hay problemas inicializando la tabla
     */
    private void inicializarTabla( ) throws SQLException
    {
        Statement s = conexionPruebas.createStatement( );

        // Limpiar la tabla resultados
        s.executeUpdate( "DELETE FROM resultados" );

        // Insertar los datos iniciales
        s.executeUpdate( "INSERT INTO resultados (nombre, ganados, perdidos) VALUES ('Alicia', 1,2)" );
        s.executeUpdate( "INSERT INTO resultados (nombre, ganados, perdidos) VALUES ('Beto', 2,1)" );
        s.executeUpdate( "INSERT INTO resultados (nombre, ganados, perdidos) VALUES ('Carlos', 1,2)" );
        s.executeUpdate( "INSERT INTO resultados (nombre, ganados, perdidos) VALUES ('Daniel', 2,1)" );
    }

    /**
     * Este método, que se llama después de cada prueba, se encarga de detener el administrador y desconectarlo de la base de datos
     * @throws Exception Se lanza esta excepción si hay problemas en la desconexión
     */
    protected void tearDown( ) throws Exception
    {
        // Desconectar el administrador de la base de datos
        try
        {
            if( adminResultados != null )
            {
                adminResultados.desconectarBD( );
            }
        }
        catch( Exception npe )
        {
            fail( "No se debería lanzar una excepción desconectando" );
        }
    }

    /**
     * Verifica el método consultarRegistrosJugadores revisando que los datos retornado correspondan a los insertados. <br>
     * <b> Métodos a probar: </b> <br>
     * consultarRegistrosJugadores. <br>
     * <b> Objetivo: </b> Probar que el método consultarRegistrosJugaores() recupere correctamente los jugadores que han sido insertados. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir la lista de jugadores, se deben obtener cada uno de los jugadores que ha sido insertado previamente.         
     */
    public void testConsultarRegistrosJugadores( )
    {
        // Configuración básica
        setupEscenario1( );

        // Consultar los Jugadores
        try
        {
            Collection jugadores = adminResultados.consultarRegistrosJugadores( );
            assertEquals( "El número de jugadores no es correcto", 4, jugadores.size( ) );

            String nom0, nom1, nom2, nom3;
            Iterator iter = jugadores.iterator( );

            RegistroJugador j = ( RegistroJugador )iter.next( );
            nom0 = j.darNombreJugador( );
            assertTrue( "Se retornó un jugador equivocado", j.darNombreJugador( ).equals( "Alicia" ) || j.darNombreJugador( ).equals( "Beto" ) || j.darNombreJugador( ).equals( "Carlos" ) || j.darNombreJugador( ).equals( "Daniel" ) );

            j = ( RegistroJugador )iter.next( );
            nom1 = j.darNombreJugador( );
            assertTrue( "Se retornó un jugador equivocado", j.darNombreJugador( ).equals( "Alicia" ) || j.darNombreJugador( ).equals( "Beto" ) || j.darNombreJugador( ).equals( "Carlos" ) || j.darNombreJugador( ).equals( "Daniel" ) );

            j = ( RegistroJugador )iter.next( );
            nom2 = j.darNombreJugador( );
            assertTrue( "Se retornó un jugador equivocado", j.darNombreJugador( ).equals( "Alicia" ) || j.darNombreJugador( ).equals( "Beto" ) || j.darNombreJugador( ).equals( "Carlos" ) || j.darNombreJugador( ).equals( "Daniel" ) );

            j = ( RegistroJugador )iter.next( );
            nom3 = j.darNombreJugador( );
            assertTrue( "Se retornó un jugador equivocado", j.darNombreJugador( ).equals( "Alicia" ) || j.darNombreJugador( ).equals( "Beto" ) || j.darNombreJugador( ).equals( "Carlos" ) || j.darNombreJugador( ).equals( "Daniel" ) );

            assertFalse( "Se listó dos veces el mismo jugador", nom0.equals( nom1 ) || nom0.equals( nom2 ) || nom0.equals( nom3 ) || nom1.equals( nom2 ) || nom1.equals( nom3 ) || nom2.equals( nom3 ) );
        }
        catch( Exception e )
        {
            fail( "No se debería lanzar una excepción" );
        }
    }

    /**
     * Verifica el método consultarRegistroJugador para el caso en el que se quiere consultar la información de un<br> 
     * jugador que ya existía en la base de datos. <br>
     * <b> Métodos a probar: </b> <br>
     * consultarRegistroJugador. <br>
     * <b> Objetivo: </b> Probar que el método consultarRegistroJugador() recupere correctamente el registro de un jugador insertado<br>
     * previamente. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el registro de un jugador, su información debe corresponder a la insertada originalmente.
     * 
     */
    public void testConsultarRegistroJugadorViejo( )
    {
        // Configuración básica
        setupEscenario1( );

        // Consultar un jugador viejo
        try
        {
            RegistroJugador jugador = adminResultados.consultarRegistroJugador( "Alicia" );
            assertEquals( "El nombre del jugador es incorrecto", "Alicia", jugador.darNombreJugador( ) );
            assertEquals( "El número de encuentros ganados es incorrecto", 1, jugador.darEncuentrosGanados( ) );
            assertEquals( "El número de encuentros perdidos es incorrecto", 2, jugador.darEncuentrosPerdidos( ) );
        }
        catch( Exception e )
        {
            fail( "No se debería lanzar una excepción" );
        }
    }

    /**
     * Verifica el método consultarRegistroJugador para el caso en el que se quiere consultar la información <br> 
     * de un jugador que no existe en la base de datos.<br>
     * En este caso el método consultarRegistroJugador debería crear un nuevo registro con valores en 0. <br>
     * <b> Métodos a probar: </b> <br>
     * consultarRegistroJugador. <br>
     * <b> Objetivo: </b> Probar que el método consultarRegistroJugador() cree un jugador cuando éste no se encuentra registrado<br>
     * en la base de datos. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al pedir el registro de un jugador que no existe, éste debe ser creado en la base de datos con los valores en cero.<br>
     * 2. Al registrar una victoria del nuevo jugador, sólo debe cambiar su número de encuentros ganados. 
     * 
     */
    public void testConsultarRegistroJugadorNuevo( )
    {
        // Configuración básica
        setupEscenario1( );

        // Consultar un jugador nuevo
        try
        {
            RegistroJugador jugador = adminResultados.consultarRegistroJugador( "Edgar" );
            assertEquals( "El nombre del jugador es incorrecto", "Edgar", jugador.darNombreJugador( ) );
            assertEquals( "El número de encuentros ganados es incorrecto", 0, jugador.darEncuentrosGanados( ) );
            assertEquals( "El número de encuentros perdidos es incorrecto", 0, jugador.darEncuentrosPerdidos( ) );

            // Modificar el jugador
            adminResultados.registrarVictoria( "Edgar" );

            // Consultar nuevamente el jugador para verificar que se creó un registro
            jugador = adminResultados.consultarRegistroJugador( "Edgar" );
            assertEquals( "El nombre del jugador es incorrecto", "Edgar", jugador.darNombreJugador( ) );
            assertEquals( "El número de encuentros ganados es incorrecto", 1, jugador.darEncuentrosGanados( ) );
            assertEquals( "El número de encuentros perdidos es incorrecto", 0, jugador.darEncuentrosPerdidos( ) );
        }
        catch( Exception e )
        {
            fail( "No se debería lanzar una excepción" );
        }
    }

    /**
     * Verifica el método registrarVictoria. <br>
     * <b> Métodos a probar: </b> <br>
     * registrarVictoria. <br>
     * <b> Objetivo: </b> Probar que el método registrarVictoria() modifique correctamente el número<br>
     * de encuentros ganados de un jugador. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al registrar la victoria de un jugador, su número de encuentros ganados se debe incrementar en uno.
     */
    public void testRegistrarVictoria( )
    {
        // Configuración básica
        setupEscenario1( );

        try
        {
            // Modificar el jugador
            adminResultados.registrarVictoria( "Alicia" );

            // Consultar el jugador para verificar que se modificó el registro
            RegistroJugador jugador = adminResultados.consultarRegistroJugador( "Alicia" );
            assertEquals( "El nombre del jugador es incorrecto", "Alicia", jugador.darNombreJugador( ) );
            assertEquals( "El número de encuentros ganados es incorrecto", 2, jugador.darEncuentrosGanados( ) );
            assertEquals( "El número de encuentros perdidos es incorrecto", 2, jugador.darEncuentrosPerdidos( ) );
        }
        catch( Exception e )
        {
            fail( "No se debería lanzar una excepción" );
        }
    }

    /**
     * Verifica el método registrarDerrota. <br>
     * <b> Métodos a probar: </b> <br>
     * registrarDerrota. <br>
     * <b> Objetivo: </b> Probar que el método registrarDerrota() recupere correctamente el número
     * de registro perdidos de un jugador. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al registrar la derrota de un jugador, su número de encuentros perdidos se debe incrementar en uno.
     */
    public void testRegistrarDerrota( )
    {
        // Configuración básica
        setupEscenario1( );

        try
        {
            // Modificar el jugador
            adminResultados.registrarDerrota( "Alicia" );

            // Consultar el jugador para verificar que se modificó el registro
            RegistroJugador jugador = adminResultados.consultarRegistroJugador( "Alicia" );
            assertEquals( "El nombre del jugador es incorrecto", "Alicia", jugador.darNombreJugador( ) );
            assertEquals( "El número de encuentros ganados es incorrecto", 1, jugador.darEncuentrosGanados( ) );
            assertEquals( "El número de encuentros perdidos es incorrecto", 3, jugador.darEncuentrosPerdidos( ) );
        }
        catch( Exception e )
        {
            fail( "No se debería lanzar una excepción" );
        }
    }

}