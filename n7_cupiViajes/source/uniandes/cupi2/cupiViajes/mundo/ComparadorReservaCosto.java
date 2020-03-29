package uniandes.cupi2.cupiViajes.mundo;


import java.util.Comparator;


public class ComparadorReservaCosto implements Comparator<ReservaViaje> 
{

    /**
     * Compara dos reservas por costo total de reserva.
     * @param r1 Reserva 1 para comparar. pReserva != null.
     * @param r2 Reserva 2 para comparar. pReserva != null.
     * @return Retorna 0 si las reservas tienen el mismo costo total. <br>
     *         Retorna -1 si la reserva r2 tiene MAYOR costo total que r1. <br>
     *         Retorna 1 si la reserva r2 tiene MENOR costo total que r1.
     */
    @Override
    public int compare( ReservaViaje r1, ReservaViaje r2 )
    {
        int diff = ( int ) (r1.darCostoTotal( )-r2.darCostoTotal( ));
        return diff==0?0:(diff>0?1:-1);
    }



}
