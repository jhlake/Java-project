package uniandes.cupi2.cupiViajes.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiViajes.mundo.ComparadorHotelNombre;
import uniandes.cupi2.cupiViajes.mundo.Hotel;

/**
 * Clase prara probar el correcto funcionamiento del comaprador de hoteles por nombre
 * @author alvar-go
 *
 */
public class ComparadorHotelNombreTest extends TestCase
{

    /**
     * Hotel para realizar la prueba
     */
    private Hotel hotel1;

    /**
     * Hotel para realizar la prueba
     */
    private Hotel hotel2;

    private ComparadorHotelNombre comparador;

    /**
     * Escenario 1:Inicializa los hoteles en un estado conocido para poder realizar las pruebas
     * post: Se inicializan los hoteles con valores conocidos y el comparador
     */
    public void setUp()
    {
        //TODO Parte 1 Punto 1a completar seg�n la documentaci�n
        hotel1 = new Hotel("Anastasia", "Barranquilla", 4, 150000.00, "Imagen 1");
        hotel2 = new Hotel("Muerte Lenta", "Sabana Larga", 2, 50000.00,  "Imagen 2");
        comparador = new ComparadorHotelNombre( );

    }

    /**
     * Prueba 1: Se encarga de verificar el m�todo compare del comparador de hoteles.<br>
     * <b> M�todos a probar: </b> <br>
     * compare<br>
     * <b> Casos de prueba:</b><br>
     * 1. El hotel1 es igual al hotel1.<br>
     * 2. El hotel1 es menor al hotel2.<br>
     * 3. El hotel2 es mayor al hotel1.
     */
    public void testCompare()
    {
        //TODO Parte 1 Punto 1b completar seg�n la documentaci�n
        assertEquals("El hotel 1 debería ser igual al hotel 1.", 0, comparador.compare( hotel1, hotel1 ) );
        assertEquals("El hotel 1 debería ser menor que el hotel 2.", -1, comparador.compare( hotel1, hotel2 ));
        assertEquals("El hotel 2 debería ser mayor que el hotel 1.", 1, comparador.compare( hotel2, hotel1 ));
    }
}
