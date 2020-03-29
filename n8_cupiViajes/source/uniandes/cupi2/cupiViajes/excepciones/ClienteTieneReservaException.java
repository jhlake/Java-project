package uniandes.cupi2.cupiViajes.excepciones;

public class ClienteTieneReservaException extends Exception
{

    private boolean exists;

    private String cedula;

    public ClienteTieneReservaException(boolean ee, String nCedula)
    {
        exists = ee;
        cedula = nCedula;
    }

    public boolean hayRepeticion()
    {
        return exists;
    }

    public String darCedula()
    {
        return cedula;
    }
}
