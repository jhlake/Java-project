package uniandes.cupi2.cupiViajes.excepciones;

/**
 * Maneja los errores que se generan al leer o escribir archivos
 * @author alvar-go
 *
 */
public class PersistenciaException extends Exception
{
    private String nombre;

    private String msg;

    public PersistenciaException(String nNombre, String nMsg)
    {
        nombre = nNombre;
        msg = nMsg;
    }

    public String darNombreArchivo()
    {
        return nombre;
    }

    public String getMessage()
    {
        return msg;
    }
}
