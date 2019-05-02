/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: LlaveInvalidaException.java,v 1.3 2008/10/11 22:09:05 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package data_structures.grafo;

/**
 * Excepción generada cuando se trata de agregar un elemento con una llave null
 */
public class LlaveInvalidaException extends RuntimeException
{
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serialización 
	 */
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
    /**
     * Constructor con mensaje
     * @param mensaje Mensaje de error
     */
    public LlaveInvalidaException( String mensaje )
    {
        super( mensaje );
    }

}
