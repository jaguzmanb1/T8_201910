/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - 9-May-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package data_structures.grafo;

import java.io.Serializable;

import data_structures.*;



/**
 * Lista doblemente encadenada con cabeza y cola
 * @param <T> Tipo de datos a almacenar en la lista
 */
public class ListaEncadenada<T> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cabeza de la lista encadenada
     */
    private NodoLista<T> primero;

    /**
     * Último elemento de la lista encadenada
     */
    private NodoLista<T> ultimo;

    /**
     * Número de elementos de la lista
     */
    private int numElems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la lista vacía. <br>
     * <b>post: </b> Se construyó una lista vacía.
     */
    public ListaEncadenada( )
    {
        primero = null;
        ultimo = null;
        numElems = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Busca un elemento en la lista encadenada. <br>
     * <b>post: </b> Se retornó el elemento o null si no existe.
     * @param modelo Modelo del elemento a buscar
     * @return Elemento en la lista, null si no existe
     */
    public T buscar( T modelo )
    {
        for( NodoLista<T> p = primero; p != null; p = p.darSiguiente( ) )
        {
            if( p.darElemento( ).equals( modelo ) )
            {
                return p.darElemento( );
            }
        }
        return null;
    }

    /**
     * Retorna la longitud (cantidad de elementos) de la lista encadenada. <br>
     * <b>post: </b> Se retornó la longitud de la lista.
     * @return Longitud de la lista. 0 en caso que no tenga elementos.
     */
    public int darLongitud( )
    {
        return numElems;
    }

    /**
     * Retorna el primer nodo de la lista. <br>
     * <b>post: </b> Se retornó el primer nodo de la lista.
     * @return Primer nodo de la lista. Null en caso que la lista sea vacía.
     */
    public NodoLista<T> darPrimero( )
    {
        return primero;
    }

    /**
     * Retorna el último nodo de la lista. <br>
     * <b>post: </b> Se retornó el último nodo de la lista.
     * @return Último nodo de la lista. Null en caso que la lista sea vacía.
     */
    public NodoLista<T> darUltimo( )
    {
        return ultimo;
    }

    /**
     * Inserta el elemento en la cabeza (inicio) de la lista. <br>
     * <b>post: </b> Se insertó el elemento en la cabeza de la lista. La cantidad de elementos aumenta <br>
     * En caso que la lista no sea vacía, el nuevo elemento queda como el primero. Si la lista es vacía el nuevo elemento es el primero y el último<br>
     * @param elemento Elemento a insertar
     */
    public void insertarCabeza( T elemento )
    {
        NodoLista<T> nodo = new NodoLista<T>( elemento );
        if( primero == null )
        {
            primero = nodo;
            ultimo = nodo;
        }
        else
        {
            primero.insertarAntes( nodo );
            primero = nodo;
        }
        numElems++;
    }

    /**
     * Inserta el elemento en la cola (final) de la lista. <br>
     * <b>post: </b> Se insertó el elemento en la cola de la lista. La cantidad de elementos aumenta <br>
     * En caso que la lista no sea vacía, el nuevo elemento queda como el último. Si la lista es vacía el nuevo elemento es el primero y el último<br>
     * @param elemento Elemento a insertar<br>
     */
    public void insertarCola( T elemento )
    {
        NodoLista<T> nodo = new NodoLista<T>( elemento );
        if( primero == null )
        {
            primero = nodo;
            ultimo = nodo;
        }
        else
        {
            ultimo.insertarDespues( nodo );
            ultimo = nodo;
        }
        numElems++;
    }

    /**
     * Elimina el nodo de la lista encadenada. <br>
     * <b>post: </b> Se eliminó el nodo especificado de la lista. La cantidad de elementos de la lista se reduce.<br>
     * @param nodo Nodo a ser eliminado de la lista<br>
     * @throws NoExisteException El nodo especificado no pertenece a la lista<br>
     */
    public void eliminarNodo( NodoLista<T> nodo ) throws NoExisteException
    {
        if( buscar( nodo.darElemento( ) ) != null )
        {
            throw new NoExisteException( "El nodo especificado no pertenece a la lista" );
        }
        if( primero == nodo )
        {
            primero = nodo.desconectarPrimero( );
            if( ultimo == nodo )
            {
                ultimo = null;
            }
        }
        else
        {
            if( ultimo == nodo )
            {
                ultimo = nodo.darAnterior( );
            }
            nodo.desconectarNodo( );
        }
    }

    /**
     * Elimina el primer nodo (cabeza) de la lista y devuelve el elemento que contenía. <br>
     * <b>post: </b> Se eliminó el primer nodo de la lista. Se retornó el elemento contenido en el nodo eliminado. La cantidad de elementos se reduce en uno. Si la lista no
     * tiene nodos se retorna null.<br>
     * @return Elemento del nodo eliminado<br>
     */
    public T eliminarPrimero( )
    {
        //
        // Si no tiene
        if( primero == null )
        {
            return null;
        }
        else
        {
            //
            // Elimina el primer elemento
            try
            {
                return eliminar( primero.darElemento( ) );
            }
            catch( NoExisteException e )
            {
                //
                // Esto nunca debería pasar
                return null;
            }
        }
    }

    /**
     * Elimina el último nodo y devuelve el elemento que contenía. <br>
     * <b>post: </b> Se eliminó el último nodo de la lista. Se retornó el elemento contenido en el nodo eliminado. La cantidad de elementos se reduce en uno. Si la lista no
     * tiene nodos se retorna null.
     * @return Elemento del nodo eliminado
     */
    public T eliminarUltimo( )
    {
        //
        // Si no tiene
        if( ultimo == null )
        {
            return null;
        }
        else
        {
            //
            // Elimina el último elemento
            try
            {
                return eliminar( ultimo.darElemento( ) );
            }
            catch( NoExisteException e )
            {
                //
                // Esto nunca debería pasar
                return null;
            }
        }
    }

    /**
     * Elimina el elemento especificado de la lista encadenada. <br>
     * <b>post: </b> Se eliminó el elemento especificado de la lista. La cantidad de elementos se reduce en uno<br>
     * @param elemento Elemento a eliminar<br>
     * @return Elemento eliminado<br>
     * @throws NoExisteException Excepción que es lanzada si el elemento especificado no existe<br>
     */
    public T eliminar( T elemento ) throws NoExisteException
    {
        T valor = null;
        if( primero == null )
        {
            throw new NoExisteException( "Elemento no existe" );
        }
        else if( primero.darElemento( ).equals( elemento ) )
        {
            if( primero.equals( ultimo ) )
            {
                ultimo = null;
            }
            valor = primero.darElemento( );
            primero = primero.desconectarPrimero( );
            numElems--;
            return valor;
        }
        else
        {
            for( NodoLista<T> p = primero.darSiguiente( ); p != null; p = p.darSiguiente( ) )
            {
                if( p.darElemento( ).equals( elemento ) )
                {
                    if( p.equals( ultimo ) )
                    {
                        ultimo = p.darAnterior( );
                    }
                    valor = p.darElemento( );
                    p.desconectarNodo( );
                    numElems--;
                    return valor;
                }
            }
            throw new NoExisteException( "Elemento no existe" );
        }
    }

    /**
     * Inserta el elemento en la posición especificada. <br>
     * <b>post: </b> Se insertó el elemento en la posición especificada.<br>
     * @param elemento Elemento a insertar<br>
     * @param pos La posición en la que se va a insertar el elemento<br>
     * @throws IndiceFueraDeRangoException Excepción lanzada cuando se quiere agregar el elemento en una posición invalida<br>
     */
    public void insertar( T elemento, int pos )
    {
        NodoLista<T> nodo = new NodoLista<T>( elemento );
        if( ( pos >= numElems ) || pos < 0 )
        {
            throw new IndiceFueraDeRangoException( pos );
        }
        else
        {
            NodoLista<T> aux = primero;

            for( int cont = 0; cont < pos - 1; cont++ )
            {
                aux = aux.darSiguiente( );
            }
            aux.insertarDespues( nodo );
            numElems++;
        }
    }

    /**
     * Retorna el elemento en la posición especificada. <br>
     * <b>post: </b>Se retornó el elemento que se encuentra en la posición especificada.<br>
     * @param pos La posición del elemento a retornar<br>
     * @return Elemento que se encuentra en la posición especificada<br>
     * @throws IndiceFueraDeRangoException Excepción lanzada cuando se trata de retornar un elemento cuya posición es invalida.
     */
    public T dar( int pos )
    {
        if( pos >= numElems || pos < 0 )
        {
            throw new IndiceFueraDeRangoException( pos );
        }
        else
        {
            NodoLista<T> aux = primero;

            for( int cont = 0; cont < pos; cont++ )
            {
                aux = aux.darSiguiente( );
            }

            return aux.darElemento( );
        }
    }

    /**
     * Elimina el elemento en la posición especificada. <br>
     * <b>post: </b> Se eliminó el elemento en la posición especificada.<br>
     * @param pos Posición del elemento a eliminar<br>
     * @return Elemento eliminado<br>
     * @throws IndiceFueraDeRangoException Excepción lanzada cuando se trata de retornar un elemento cuya posición es invalida.<br>
     */
    public T eliminar( int pos )
    {
        T valor = null;

        if( ( pos >= numElems ) || pos < 0 )
        {
            throw new IndiceFueraDeRangoException( pos );
        }
        else if( pos == 0 )
        {
            if( primero.equals( ultimo ) )
            {
                ultimo = null;
            }
            valor = primero.darElemento( );
            primero = primero.desconectarPrimero( );
            numElems--;
            return valor;
        }
        else
        {

            NodoLista<T> p = primero.darSiguiente( );
            for( int cont = 1; cont < pos; cont++ )
            {
                p = p.darSiguiente( );
            }

            if( p.equals( ultimo ) )
            {
                ultimo = p.darAnterior( );
            }
            valor = p.darElemento( );
            p.desconectarNodo( );
            numElems--;
            return valor;
        }
    }

    /**
     * Evalúa si la lista contiene el elemento que se pasa por parámetro<br>
     * @param modelo Modelo del elemento a buscar.<br>
     * @return True en caso que el elemento dado exista en la lista, false de lo contrario.<br>
     */
    public boolean contiene( T modelo )
    {
        return buscar( modelo ) != null;
    }

    /**
     * Elimina todos los elementos de la lista.<br>
     * <b>post: </b> La lista ahora es vacía. primero = null, ultimo=null, numElems = 0<br>
     */
    public void vaciar( )
    {
        primero = null;
        ultimo = null;
        numElems = 0;
    }

    /**
     * Devuelve un iterador con los elementos de la lista encadenada. <br>
     * <b>post:</b> Se retornó iterador con los elementos de la lista.<br>
     * @return Iterador con los elementos de la lista, puede ser vacío pero no null.<br>
     */
    public Iterador<T> darIterador( )
    {
        IteradorSimple<T> respuesta = new IteradorSimple<T>( numElems );
        NodoLista<T> iterador = primero;
        while( iterador != null )
        {
            try
            {
                respuesta.agregar( iterador.darElemento( ) );
                iterador = iterador.darSiguiente( );
            }
            catch( IteradorException e )
            {
                // Nunca debería ocurrir esta excepción
            }
        }
        return respuesta;
    }

    /**
     * Convierte la lista a un String. <br>
     * <b>post: </b> Se retornó la representación en String de la lista. El String tiene el formato "[numeroElementos]: e1<->e2<->e3..<->en", donde e1, e2, ..., en son los
     * elementos que contiene la lista y numeroElementos su longitud.
     * @return La representación en String de la lista
     */
    @Override
    public String toString( )
    {
        String resp = "ida: [" + numElems + "]:";
        for( NodoLista<T> p = primero; p != null; p = p.darSiguiente( ) )
        {
            resp += p.darElemento( ).toString( ) + "<->";
        }
        resp += "\r\nvuelta:[" + numElems + "]:";
        for( NodoLista<T> p = ultimo; p != null; p = p.darAnterior( ) )
        {
            resp += p.darElemento( ).toString( ) + "<->";
        }
        return resp;
    }
    
    /**
     * Retorna un iterador sin memoria sobre la lista
     * @return Iterador sin memoria sobre la lista
     */
    public IteradorSinMemoria<T> darIteradorSinMemoria()
    {
    	IteradorSinMemoria<T> it = new IteradorSinMemoria<T>(primero);
    	return it;    	
    }
}
