package uniandes.cupi2.estructuras;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class ListaDoblementeEncadenada <T extends IdentificadoUnicamente> extends ListaEncadenadaAbstracta<T> implements List<T>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construye una lista vacia
     * post: se ha inicializado el primer nodo en null
     */
    public ListaDoblementeEncadenada()
    {
        primero = null;
    }
    
    /**
     * Se construye una nueva lista cuyo primer nodo  guardar� al elemento que llega por par�mentro
     * @param nPrimero el elemento a guardar en el primer nodo
     * @throws NullPointerException si el elemento recibido es nulo
     */
    public ListaDoblementeEncadenada(T nPrimero)
    {
        if(nPrimero == null)
        {
            throw new NullPointerException();
        }
        primero = new NodoListaDoble<T>( nPrimero );
    }

    /**
     * Agrega un elemento al final de la lista
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param elem el elemento que se desea agregar.
     * @return true en caso que se agregue el elemento o false en caso contrario. 
     * @throws NullPointerException si el elemento es nulo
     */
    public boolean add( T elem )
    {
        // TODO Parte 3.B: Completar seg�n la documentaci�n
        if(elem == null)
            throw new NullPointerException( );
        boolean added = false;
        NodoListaDoble<T> toAdd = new NodoListaDoble<T>( elem );
        if(primero == null)
        {
            primero = toAdd;
            added =true;
        }
        NodoListaDoble<T> ele = ( NodoListaDoble<T> )primero;
        while(!added)
        {
            ele = ( NodoListaDoble<T> )ele.darSiguiente( );
            if(ele.darElemento( ).darIdentificador( ).equals( elem.darIdentificador( ) ))
                break;
            else if(ele.darSiguiente( ) == null)
            {
                ele.cambiarSiguiente( toAdd );
                toAdd.cambiarAnterior( ele );
                added = true;
            }
        }
        return added;
    }

    
    /**
     * Agrega un elemento en la posici�n dada de la lista. Todos los elementos siguientes se desplazan
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param pos la posici�n donde se desea agregar. Si pos es igual al tama�o de la lista se agrega al final
     * @param elem el elemento que se desea agregar
     * @throws IndexOutOfBoundsException si el inidice es < 0 o > size()
     * @throws NullPointerException Si el elemento que se quiere agregar es null.
     */
    public void add( int pos, T elem )
    {
     // TODO Parte 3.B: Completar seg�n la documentaci�n
        if(elem == null)
            throw new NullPointerException( );
        NodoListaDoble<T> toAdd = new NodoListaDoble<T>( elem );
        if(!contains(elem))
        {
            if(pos==0)
            {
                toAdd.cambiarSiguiente( primero );
                primero = toAdd;
            }
            else
            {
                NodoListaDoble<T> n = ( NodoListaDoble<T> )primero;
                int posActual = 0;
                while( posActual < (pos-1) && n != null )
                {
                    posActual++;
                    n = ( NodoListaDoble<T> )n.darSiguiente( );
                }
                if(posActual != (pos-1))
                {
                    throw new IndexOutOfBoundsException( );
                }
                toAdd.cambiarSiguiente( n.darSiguiente( ) );
                n.cambiarSiguiente( toAdd );
                toAdd.cambiarAnterior( n );
            }
        }


    }

    
    /**
     * Devuelve un nuevo iterador de lista que inicie en el primer nodo
     * @return iterador
     */
    public ListIterator<T> listIterator( )
    {
        return new IteradorDeLista<T>( (NodoListaDoble<T>)primero );
    }

    /**
     * Devuelve un nuevo iterador de lista que inicie en nodo de la posici�n que llega por par�metro
     * @return iterador 
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public ListIterator<T> listIterator( int pos )
    {
        return new IteradorDeLista<T>( (NodoListaDoble<T>)(getNodo(pos)) );
    }

    /**
     * Elimina el nodo que contiene al objeto que llega por par�metro
     * @param objeto el objeto que se desea eliminar. objeto != null
     * @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
     */
    public boolean remove( Object objeto )
    {
     // TODO Completar seg�n la documentaci�n
        boolean rem = false;
        NodoListaDoble<T> ele = ( NodoListaDoble<T> )primero;
        if(ele == null)
            return false;
        if(ele.darElemento( ).equals( objeto ))
        {
            primero = ele.darSiguiente( );
            ele.cambiarAnterior( null );
            rem = true;
        }
        while(!rem)
        {
            NodoListaDoble<T> sig = ( NodoListaDoble<T> )ele.darSiguiente( );
            if(sig == null)
                break;
            NodoListaDoble<T> next = ( NodoListaDoble<T> )sig.darSiguiente( );
            if(sig.darElemento( ).equals( objeto ))
            {
                ele.cambiarSiguiente( next );
                next.cambiarAnterior( ele );
                rem = true;
            }
            ele = sig;
        }
        return rem;
       
    }

    /**
     * Elimina el elemento de la posici�n dada
     * @param pos la posici�n que se desea eliminar
     * @return el elemento eliminado o null en caso que no exista la posici�n que se desea eliminar
     */
    public T remove( int pos )
    {
     // TODO Parte 3.B: Completar seg�n la documentaci�n
        if(pos <0)
            throw new ArrayIndexOutOfBoundsException( );
        int count = 0;
        boolean found = false;
        NodoListaDoble<T> ele =( NodoListaDoble<T> )primero;
        if(ele == null)
            return null;
        if(pos == 0)
        {
            primero = (NodoListaDoble<T>)ele.darSiguiente( );
            return ele.darElemento( );
        }
        T obj = null;
        while(!found)
        {
            NodoListaDoble<T> sig = ( NodoListaDoble<T> )ele.darSiguiente( );
            if(sig == null)
                throw new ArrayIndexOutOfBoundsException( );
            NodoListaDoble<T> next = ( NodoListaDoble<T> )sig.darSiguiente( );
            if(count +1 == pos) 
            {
                obj = sig.darElemento( );
                found = true;
                ele.cambiarSiguiente( next );
                next.cambiarAnterior( ele );
            }
            ele = sig;
            count++;
        }
        return obj;
    }

    /**
     * Deja en la lista solo los elementos que est�n en la colecci�n que llega por par�metro
     * @param coleccion la colecci�n de elementos a mantener. coleccion != null
     * @return true en caso que se modifique (eliminaci�n) la lista o false en caso contrario
     */
    public boolean retainAll( Collection<?> coleccion )
    {
     // TODO Parte 3.B: Completar seg�n la documentaci�n
        boolean change = false;
        NodoListaDoble<T> ele =( NodoListaDoble<T> )primero;
        if(ele == null)
            return false;
        NodoListaDoble<T> sig = ( NodoListaDoble<T> )ele.darSiguiente( );
        if(!(coleccion.contains( ele )))
        {
            NodoListaDoble<T> next = ( NodoListaDoble<T> )sig.darSiguiente( );
            primero = sig;
            ele = sig;
            sig = next;
        }
        while(sig!=null)
        {
            if(!(coleccion.contains(ele.darElemento( ) )))
            {
                remove(ele.darElemento( ));
                change = true;
            }
        }
        return change;
    }

    /**
     * Crea una lista con los elementos de la lista entre las posiciones dadas
     * @param inicio la posici�n del primer elemento de la sublista. Se incluye en la sublista
     * @param fin la posici�n del �tlimo elemento de la sublista. Se excluye en la sublista
     * @return una lista con los elementos entre las posiciones dadas
     * @throws IndexOutOfBoundsException Si inicio < 0 o fin >= size() o fin < inicio
     */
    public List<T> subList( int inicio, int fin )
    {
     // TODO Parte 3.B: Completar seg�n la documentaci�n
        if(inicio < 0 || fin< inicio)
            throw new IndexOutOfBoundsException( );
        ListaDoblementeEncadenada<T> ans = new ListaDoblementeEncadenada<T>( );
        int count = 0;
        NodoListaDoble<T> ele = ( NodoListaDoble<T> )primero;
        if(ele == null)
            return null;
        while(count < fin )
        {
            if(ele==null)
                throw new IndexOutOfBoundsException( );
            if(count >= inicio)
            {
                ans.add( ele.darElemento( ) );
            }
            count++;
            ele = ( NodoListaDoble<T> )ele.darSiguiente( );
        }
        return ans;
    }
}
