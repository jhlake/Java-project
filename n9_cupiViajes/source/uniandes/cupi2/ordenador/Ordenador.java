package uniandes.cupi2.ordenador;

import java.util.Comparator;
import java.util.List;

/**
 * Clase g�nerica que se encarga de ordenar colecciones de elementos de tipo T
 * @author alvar-go
 * @param <T> tipo de elementos que debe contener la colecci�n a ordenar
 */
public class Ordenador<T> 
{
    /**
     * Ordena la lista que llega por par�metro en el sentido que llega por par�metro usando el comparador que llega por par�metro
     * post: la lista se encuentra ordenada por los cirterios ingresados
     * @param ordenamiento el algoritmo de ordenamieto que se debe usar. ordenamiento pertenece a la enumeraci�n Ordenamiento
     * @param lista la lista que se desea ordenar. lista != null
     * @param ascendente indica si se debe ordenar de manera ascendente, de lo contrario se debe ordenar descendentemente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    public void ordenar(AlgoritmoOrdenamiento ordenamiento, List<T> lista, boolean ascendente, Comparator<T> comparador)
    {
        if(ordenamiento.equals(AlgoritmoOrdenamiento.BURBUJA))
        {
            ordenarBurbuja(lista, ascendente, comparador);
        }
        else if(ordenamiento.equals(AlgoritmoOrdenamiento.SELECCION))
        {
            ordenarSeleccion(lista, ascendente, comparador);
        }
        else if(ordenamiento.equals(AlgoritmoOrdenamiento.INSERCION))
        {
            ordenarInsercion(lista, ascendente, comparador);
        }
        else if(ordenamiento.equals(AlgoritmoOrdenamiento.SHAKER))
        {
            ordenarShaker(lista, ascendente, comparador);
        }
        else if(ordenamiento.equals(AlgoritmoOrdenamiento.GNOME))
        {
            ordenarGnome(lista, ascendente, comparador);
        }
    }

    /**
     * Ordena la lista usando el algoritmo de inscerci�n
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lista != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarInsercion(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
        //TODO Parte 4 Punto 1a Implemente seg�n la documentaci�n
        if(ascendnte)
        {
            for(int i = 1; i<lista.size( ); i++)
            {
                int posCh = i;
                int posMin = i;
                T min = lista.get( posMin );
                int j = i;
                while(j > 0 && comparador.compare(lista.get( j-1 ) , min ) > 0)
                {
                    lista.set( j, lista.get( j-1 ) );
                    j--;
                    posCh = j;
                }
                lista.set( posCh, min);
            }
        }
        else
        {
            for(int i = 1; i<lista.size( ); i++)
            {
                int posCh = i;
                int posMin = i;
                T min = lista.get( posMin );
                int j = i;
                while(j > 0 && comparador.compare(lista.get( j-1 ) , min ) < 0)
                {
                    lista.set( j, lista.get( j-1 ) );
                    j--;
                    posCh = j;
                }
                lista.set( posCh, min);
            }
        }

    }

    /**
     * Ordena la lista usando el algoritmo de selecci�n
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lista != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarSeleccion(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
        //TODO Parte 4 Punto 1b  Implemente seg�n la documentaci�n
        if(ascendnte)
        {
            for( int j = 0; j < lista.size( ); j++ )
            {
                int posicionMenor = j;
                T menor = lista.get( j );

                for( int i = j + 1; i < lista.size( ); i++ )
                {
                    T tPos = lista.get( i );

                    if( comparador.compare( tPos, menor ) < 0 )
                    {
                        menor = tPos;
                        posicionMenor = i;
                    }
                }

                if( posicionMenor != j )
                {
                    T temp = lista.get( j );
                    lista.set( j, menor );
                    lista.set( posicionMenor, temp );
                }
            }
        }
        else
        {
            for( int j = 0; j < lista.size( ); j++ )
            {
                int posicionMenor = j;
                T menor = lista.get( j );

                for( int i = j + 1; i < lista.size( ); i++ )
                {
                    T tPos = lista.get( i );

                    if( comparador.compare( tPos, menor ) > 0 )
                    {
                        menor = tPos;
                        posicionMenor = i;
                    }
                }

                if( posicionMenor != j )
                {
                    T temp = lista.get( j );
                    lista.set( j, menor );
                    lista.set( posicionMenor, temp );
                }
            }
        }

    }

    /**
     * Ordena la lista usando el algoritmo de burbuja
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lista != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarBurbuja(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
        //TODO Parte 4 Punto 1c  Implemente seg�n la documentaci�n
        if(ascendnte)
        {
            T temp = null;
            for(int i = 0; i<lista.size( )-1;i++)
            {
                for(int j = 1; j<lista.size( ) - i; j++)
                {
                    if(comparador.compare( lista.get( j-1 ), lista.get(j) ) > 0)
                    {
                        temp = lista.get(j);
                        lista.set( j, lista.get( j-1 ) );
                        lista.set(j-1, temp);
                    }
                }
            }
        }
        else
        {
            T temp = null;
            for(int i = 0; i<lista.size( )-1;i++)
            {
                for(int j = 1; j<lista.size( ) - i; j++)
                {
                    if(comparador.compare( lista.get( j-1 ), lista.get(j)) < 0)
                    {
                        temp = lista.get(j);
                        lista.set( j, lista.get( j-1 ) );
                        lista.set(j-1, temp);
                    }
                }
            }
        }

    }

    /**
     * Ordena la lista usando el algoritmo de shake (burbuja bidireccional)
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lista != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarShaker(List<T> lista,boolean ascendnte, Comparator<T> comparador)
    {
        //TODO Parte 4 Punto 1d  Implemente seg�n la documentaci�n
        if(ascendnte)
        {
            for (int i = 0; i < lista.size()/2; i++) 
            {
                boolean swapped = false;
                for (int j = i; j < lista.size() - i - 1; j++) 
                {
                    if (comparador.compare(lista.get(j), lista.get(j+1)) >0) 
                    {
                        T temp = lista.get( j );
                        lista.set( j, lista.get( j+1 ) );
                        lista.set( j+1, temp );
                        swapped = true;
                    }
                }
                for (int j = lista.size( ) - 2 - i; j > i; j--) 
                {
                    if (comparador.compare( lista.get( j ),lista.get( j-1 )) < 0) 
                    {
                        T temp = lista.get( j );
                        lista.set( j, lista.get( j-1 ) );
                        lista.set( j-1, temp );
                        swapped = true;

                    }
                }
                if(!swapped) break;
            }
        }
        else
        {
            for (int i = 0; i < lista.size()/2; i++) 
            {
                boolean swapped = false;
                for (int j = i; j < lista.size() - i - 1; j++) 
                {
                    if (comparador.compare(lista.get(j), lista.get(j+1)) <0) 
                    {
                        T temp = lista.get( j );
                        lista.set( j, lista.get( j+1 ) );
                        lista.set( j+1, temp );
                        swapped = true;
                    }
                }
                for (int j = lista.size( ) - 2 - i; j > i; j--) 
                {
                    if (comparador.compare( lista.get( j ),lista.get( j-1 )) > 0) 
                    {
                        T temp = lista.get( j );
                        lista.set( j, lista.get( j-1 ) );
                        lista.set( j-1, temp );
                        swapped = true;

                    }
                }
                if(!swapped) break;
            }
        }

    }


    /**
     * Ordena la lista usando el algoritmo de Gnome
     * post: la lista se encuentra ordenada
     * @param lista la lista que se desea ordenar. lista != null
     * @param ascendnte indica si se debe ordenar de mamenra ascendente, de lo contrario se ordenar� de manera descendente
     * @param comparador comparador de elementos tipo T que se usar� para ordenar la lista, define el criterio de orden. comparador != null.
     */
    private void ordenarGnome(List<T> lista,boolean ascendnte, Comparator<T> comparador) {
        //TODO Parte 4 Punto 1e  Implemente seg�n la documentaci�n
        if(ascendnte)
        {
            int i = 1;
            T temp = null;
            while(i<lista.size( ))
            {
                if(comparador.compare( lista.get( i ), lista.get( i-1 ) ) < 0)
                {
                    int marker = i;
                    temp = lista.get( i );
                    lista.set( i, lista.get( i-1 ) );
                    lista.set( i-1, temp);
                    i--;
                    if(i == 0)
                    {
                        i = marker;
                    }
                }
                else
                {
                    i++;
                }
            }
        }
        else
        {
            int i = 1;
            T temp = null;
            while(i<lista.size( ))
            {
                if(comparador.compare( lista.get( i ), lista.get( i-1 ) ) > 0)
                {
                    int marker = i;
                    temp = lista.get( i );
                    lista.set( i, lista.get( i-1 ) );
                    lista.set( i-1, temp);
                    i--;
                    if(i == 0)
                    {
                        i = marker;
                    }
                }
                else
                {
                    i++;
                }
            }
        }
    }
}
