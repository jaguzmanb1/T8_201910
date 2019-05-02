/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IteradorSinMemoria.java,v 1.4 2008/11/04 03:14:12 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Alfredo Morales  - 22-oct-2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package data_structures.grafo;

import data_structures.*;

/**
 * Implementación de un iterador sin memoria. A través de este tipo de iterador se pueden recorrer los elementos de la estructura de datos sobre la que se encuentra asociado,
 * manteniendo en memoria únicamente el nodo actual sobre el que se encuentra el iterador.
 * @param <T> Tipo de datos sobre los que se itera
 */
public class IteradorSinMemoria<T> implements Iterador<T>
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
     * Nodo actual
     */
    INodo<T> nodoActual;

    /**
     * Primer nodo
     */
    INodo<T> primero;

    /**
     * Último nodo
     */
    INodo<T> ultimo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la clase<br>
     * <b>pos:</b> El nodo actual y primero se han inicializado con el nodo dado por parámetro
     * @param nNodo Es el nodo actual del iterador
     */
    public IteradorSinMemoria( INodo<T> nNodo )
    {
        nodoActual = nNodo;
        primero = nNodo;
        ultimo = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.collections.iterador.Iterador#darAnterior()
     */
    public T darAnterior( )
    {
        T elemento = null;

        if( hayAnterior( ) )
        {
            if( nodoActual != null )
            {
                nodoActual = nodoActual.darAnterior( );
                elemento = nodoActual.darElemento( );
            }
            else if( ultimo != null )
            {
                nodoActual = ultimo;
                elemento = nodoActual.darElemento( );
            }

        }

        return elemento;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.collections.iterador.Iterador#darSiguiente()
     */
    public T darSiguiente( )
    {
        T elemento = null;

        if( haySiguiente( ) )
        {
            elemento = nodoActual.darElemento( );
            if( nodoActual.darSiguiente( ) == null )
                ultimo = nodoActual;
            nodoActual = nodoActual.darSiguiente( );
        }

        return elemento;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.collections.iterador.Iterador#hayAnterior()
     */
    public boolean hayAnterior( )
    {
        boolean hayAnterior = false;

        if( nodoActual != null )
            hayAnterior = nodoActual.darAnterior( ) != null;
        else if( ultimo != null )
            hayAnterior = true;

        return hayAnterior;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.collections.iterador.Iterador#haySiguiente()
     */
    public boolean haySiguiente( )
    {
        return nodoActual != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uniandes.cupi2.collections.iterador.Iterador#reiniciar()
     */
    public void reiniciar( )
    {
        nodoActual = primero;
        ultimo = null;
    }
}
