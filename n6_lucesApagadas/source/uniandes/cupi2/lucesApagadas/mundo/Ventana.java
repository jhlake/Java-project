package uniandes.cupi2.lucesApagadas.mundo;

/**
 * Clase que representa las ventanas individuales.
 * @author jlake
 */
public class Ventana
{
    /*
     * Constantes
     */
    /**
     * Constante que representa si la ventana est� apagada.
     */
    public final static int APAGADO = 0;

    /**
     * Constante que representa si la ventana est� encendida.
     */
    public final static int ENCENDIDO = 1;

    /*
     * Atributos
     */
    /**
     * Atributo que define el estado de la ventana.
     */
    private int estado;

    /*
     * M�todos
     */

    /*
     * Constructor
     */
    /**
     * M�todo que construye la ventana
     * @param nEstado Entero que representa el estado inicial de la ventana. nEstado == ENCEDIDO || nEstado == APAGADO
     */
    public Ventana(int nEstado)
    {
        estado = nEstado;
    }

    /**
     * M�todo que cambia el estado de la ventana de encendido a apagado, o vice-versa.
     */
    public void cambiarEstado()
    {
        if(estado==ENCENDIDO)
        {
            estado = APAGADO;
        }
        else
        {
            estado = ENCENDIDO;
        }
    }

    /**
     * M�todo que devuelve el estado actual de la ventana.
     * @return ENCEDIDO Si est� encendida, o APAGADO si est� apagada.
     */
    public int darEstado()
    {
        return estado;
    }
}
