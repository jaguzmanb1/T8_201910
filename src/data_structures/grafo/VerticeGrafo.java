/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VerticeGrafo.java,v 1.1 2008/04/19 03:58:41 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Feb 29, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package data_structures.grafo;

import data_structures.*;

/**
 * Representa un vértice del grafo que puede ser marcada y encapsula un elemento IVertice
 * @param <K> Tipo de identificador del vértice
 * @param <V> Tipo de datos del elemento del vértice
 */
public class VerticeGrafo<K, V extends IVertice<K>>
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Información contenida en el vertice
     */
    private V vertice;

    /**
     * Indica si el vértice está marcado
     */
    private boolean marcado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parametros.
     * @param vertice Información del vertice contenido
     */
    public VerticeGrafo( V vertice )
    {
        // Inicializar los atributos de la clase
        this.vertice = vertice;
        marcado = false;
    }

    // -----------------------------------------------------------------
    // Métodos consultores
    // -----------------------------------------------------------------

    /**
     * Retorna el <code>IVertice</code> encapsulado en el vértice
     * @return El <code>IVertice</code> encapsulado en el vértice
     */
    public V darInfoVertice( )
    {
        return vertice;
    }

    /**
     * Indica si el vértice está marcado
     * @return <code>true</code> si el vértice está marcado o <code>false</code> en caso contrario
     */
    public boolean estaMarcado( )
    {
        return marcado;
    }

    // -----------------------------------------------------------------
    // Métodos modificadores
    // -----------------------------------------------------------------

    /**
     * Prende la marca del vértice
     */
    public void marcar( )
    {
        marcado = true;
    }

    /**
     * Apaga la marca del vértice
     */
    public void desmarcar( )
    {
        marcado = false;
    }
}
