/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * $Id: EntradaTabla.java,v 1.11 2008/09/30 16:06:59 alf-mora Exp $
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Daniel Romero- Mayo 21, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package data_structures.grafo;

import java.io.Serializable;

/**
 * Representa una entrada en la tabla de hashing, que asocia un valor con una llave. Contiene una referencia al siguiente elemento del bucket al que pertenece
 * @param <L> Tipo de las llaves asociada con los elementos
 * @param <V> Tipo de los elementos guardados en la tabla
 */
public class EntradaTabla<L, V> implements Serializable
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
     * Elemento de la entrada
     */
    private V elemento;

    /**
     * Llave asociada con el elemento de la entrada
     */
    private L llave;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la entrada con el elemento y su llave asociada. <br>
     * <b>post: </b> Se constuyó una entrada de la tabla con la llave y elementos asociados.
     * @param tLlave la llave asociada con el elemento. Diferente de null.
     * @param tElemento El elemento de la entrada
     */
    public EntradaTabla( L tLlave, V tElemento )
    {
        elemento = tElemento;
        llave = tLlave;
    }

    /**
     * Constructor de la entrada con la llave especificada. <br>
     * <b>post: </b> Se constuyó una entrada de la tabla con la llave especificada, elemento= null.
     * @param tLlave la llave de la entrada. Diferente de null
     */
    public EntradaTabla( L tLlave )
    {
        elemento = null;
        llave = tLlave;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento de la entrada. <br>
     * <b>post: </b> Se retornó el elemento de la entrada.<br>
     * @return Elemento de la entrada.<br>
     */
    public V darElemento( )
    {
        return elemento;
    }

    /**
     * Retorna la llave de la entrada. <br>
     * <b>post: </b> Se retornó la llave de la entrada.<br>
     * @return Llave de la entrada. Diferente de null<br>
     */
    public L darLlave( )
    {
        return llave;
    }

    /**
     * Cambia el elemento de la entrada. <br>
     * <b>post: </b> Se cambió el elemento de la entrada.<br>
     * @param Nuevo elemento de la entrada<br>
     */
    public void cambiarElemento( V elem )
    {
        elemento = elem;
    }

    /**
     * Convierte la entrada a un String. <br>
     * <b>post: </b> Se retornó la representación en String de la entrada. El String tiene el formato "[Llave: l Elemento: E]", donde l es la llave de la entrada y E su
     * elemento.
     * @return La representación en String de la entrada
     */
    @Override
    public String toString( )
    {
        return "[Llave: " + llave.toString( ) + " Elemento: " + elemento.toString( ) + "]";
    }

    /**
     * Indica si la entrada especificada es igual a la actual. <br>
     * <b>post: </b> Se retornó true si la entrada es igual a la actual o false de lo contrario. Dos entradas son iguales si sus llaves correspondientes lo son.
     * @return True si la entrada es igual a la actual o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals( Object o )
    {
        boolean igual = false;

        if( o instanceof EntradaTabla )
        {
            EntradaTabla<L, V> entrada = ( EntradaTabla )o;
            igual = llave.equals( entrada.darLlave( ) );

        }
        return igual;
    }

}
