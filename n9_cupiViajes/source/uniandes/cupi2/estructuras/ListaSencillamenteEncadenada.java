package uniandes.cupi2.estructuras;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Clase que representa una lista sencillamente encadenada
 * @author alvar-go
 * @param <T>
 */
public class ListaSencillamenteEncadenada<T extends IdentificadoUnicamente> extends ListaEncadenadaAbstracta<T>
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construye una lista vacia
     * post: se ha inicializado el primer nodo en null
     */
    public ListaSencillamenteEncadenada()
    {
        primero = null;
    }
    
    /**
     * Se construye una nueva lista cuyo primer nodo  guardar� al elemento que llega por par�mentro
     * @param nPrimero el elemento a guardar en el primer nodo
     * @throws NullPointerException si el elemento recibido es nulo
     */
    public ListaSencillamenteEncadenada(T nPrimero)
    {
        if(nPrimero == null)
        {
            throw new NullPointerException();
        }
        primero = new NodoListaSencilla<T>( nPrimero );
    }

    /**
     * Agrega un elemento al final de la lista
     * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
     * @param elem el elemento que se desea agregar.
     * @return true en caso que se agregue el elemento o false en caso contrario. 
     * @throws NullPointerException si el elemento es nulo
     */
    public boolean add( T elem )throws NullPointerException
    {
        if(elem == null)
        {
            throw new NullPointerException( );
        }
        
        boolean agregado = false;
        if(primero == null)
        {
            primero = new NodoListaSencilla<T>( elem );
            agregado = true;
        }
        else
        {
            NodoListaSencilla<T> n = primero;
            boolean existe = false;
            while(  n.darSiguiente( ) != null && !existe)
            {
                if(n.darElemento( ).darIdentificador( ).equals( elem.darIdentificador( ) ))
                {
                    existe = true;
                }
                n = n.darSiguiente( );
            }
            if(!n.darElemento( ).darIdentificador( ).equals( elem.darIdentificador( ) ))
            {
                n.cambiarSiguiente( new NodoListaSencilla<T>( elem ) );
            }
            
        }
        
        return agregado;
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
        if(elem == null)
        {
            throw new NullPointerException( );
        }
        NodoListaSencilla<T> nuevo = new NodoListaSencilla<T>( elem );
        if(!contains( elem ))
        {
            
            if(pos == 0)
            {
                nuevo.cambiarSiguiente( primero );
                primero = nuevo;
            }
            else
            {
                NodoListaSencilla<T> n = primero;
                int posActual = 0;
                while( posActual < (pos-1) && n != null )
                {
                    posActual++;
                    n = n.darSiguiente( );
                }
                if(posActual != (pos-1))
                {
                    throw new IndexOutOfBoundsException( );
                }
                nuevo.cambiarSiguiente( n.darSiguiente( ) );
                n.cambiarSiguiente( nuevo );
            }
        }
        
    }

    
    public ListIterator<T> listIterator( )
    {
        throw new UnsupportedOperationException ();
    }

    
    public ListIterator<T> listIterator( int pos )
    {
        throw new UnsupportedOperationException ();
    }

    /**
     * Elimina el nodo que contiene al objeto que llega por par�metro
     * @param objeto el objeto que se desea eliminar. objeto != null
     * @return true en caso que exista el objeto y se pueda eliminar o false en caso contrario
     */
    public boolean remove( Object objeto )
    {
     // TODO Parte 3.A: Completar seg�n la documentaci�n
        boolean rem = false;
        NodoListaSencilla<T> ele = primero;
        if(ele == null)
            return false;
        if(ele.darElemento( ).equals( objeto ))
        {
            primero = ele.darSiguiente( );
            rem = true;
        }
        while(!rem)
        {
            NodoListaSencilla<T> sig = ele.darSiguiente( );
            if(sig == null)
                break;
            if(sig.darElemento( ).equals( objeto ))
            {
                ele.cambiarSiguiente( sig.darSiguiente( ) );
                rem = true;
            }
            ele = sig;
        }
        return rem;
       
    }

    /**
     * Elimina el nodo en la posici�n por par�metro
     * @param pos la posici�n que se desea eliminar
     * @return el elemento eliminado
     * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
     */
    public T remove( int pos )
    {
     // TODO Parte 3.A: Completar seg�n la documentaci�n
        if(pos <0)
            throw new ArrayIndexOutOfBoundsException( );
        int count = 0;
        boolean found = false;
        NodoListaSencilla<T> ele =primero;
        if(ele == null)
            return null;
        if(pos == 0)
        {
            primero = ele.darSiguiente( );
            return ele.darElemento( );
        }
        T obj = null;
        while(!found)
        {
            NodoListaSencilla<T> sig = ele.darSiguiente( );
            if(sig == null)
                throw new ArrayIndexOutOfBoundsException( );
            if(count +1 == pos) 
            {
                obj = sig.darElemento( );
                found = true;
                ele.cambiarSiguiente( sig.darSiguiente( ) );
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
     // TODO Parte 3.A: Completar seg�n la documentaci�n
        boolean change = false;
        NodoListaSencilla<T> ele =primero;
        if(ele == null)
            return false;
        NodoListaSencilla<T> sig = ele.darSiguiente( );
        if(!(coleccion.contains( ele )))
        {
            primero = sig;
            ele = sig;
            sig = sig.darSiguiente( );
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
     // TODO Parte 3.A: Completar seg�n la documentaci�n
        if(inicio < 0 || fin< inicio)
            throw new IndexOutOfBoundsException( );
        ListaSencillamenteEncadenada<T> ans = new ListaSencillamenteEncadenada<T>( );
        int count = 0;
        NodoListaSencilla<T> ele = primero;
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
            ele = ele.darSiguiente( );
        }
        return ans;
    }

}
