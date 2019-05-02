/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: GrafoNoDirigido.java,v 1.4 2008/12/11 16:33:20 alf-mora Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo G�mez - Feb 29, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package data_structures.grafo;

import java.io.Serializable;
import data_structures.*;


/**
 * Representa un grafo no dirigido
 * 
 * @param <K> Tipo del identificador de un v�rtice
 * @param <V> Tipo de datos del elemento del v�rtice
 * @param <A> Tipo de datos del elemento del arco
 */
public class GrafoNoDirigido<K, V extends IVertice<K>, A extends IArco> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serializaci�n
	 */
	private static final long serialVersionUID = 1L;	

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Matiz de adyacencia encargada de mantener los arcos del grafo no dirigido
     */
    private MatrizAdyacencia<K, V, A> matrizAdyacencia;

    /**
     * Vertices del grafo no dirigido.
     */
    private Lista<VerticeGrafo<K, V>> vertices;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor del grafo no dirigido 
     */
    public GrafoNoDirigido( )
    {
        // Inicializar los atributos del grafo no dirigido
        matrizAdyacencia = new MatrizAdyacencia<K, V, A>( );
        vertices = new Lista<VerticeGrafo<K, V>>( );
    }

    // -----------------------------------------------------------------
    // M�todos modificadores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo v�rtice en el grafo
     * @param elemento Elemento del v�rtice
     * @throws VerticeYaExisteException Si el v�rtice que se quiere agregar ya existe
     */
    public void agregarVertice( V elemento ) throws VerticeYaExisteException
    {
        // Verificar que el v�rtice no est� registrado en el grafo
        try
        {
            darVertice( elemento.darId( ) );
            System.out.println(elemento.darId());
            System.out.println(darVertice(elemento.darId()).toString());
		    throw new VerticeYaExisteException( "El v�rtice ya existe", elemento.darId( ) );
        }
        catch( VerticeNoExisteException e )
        {
            // Agregar el v�rtice a la lista de vertices
            vertices.agregar( new VerticeGrafo<K, V>( elemento ) );

            // Registrar el v�rtice en la matriz de adyacencia
            matrizAdyacencia.agregarVertice( elemento.darId( ) );
        }
    }

    /**
     * Elimina el v�rtice identificado con el Identificador especificado
     * @param idVertice Identificador del v�rtice
     * @throws VerticeNoExisteException suando el v�rtice especificado no existe
     */
    public void eliminarVertice( K idVertice ) throws VerticeNoExisteException
    {
        // Eliminar el vertice de la matriz de adyacencia
        matrizAdyacencia.eliminarVertice( idVertice );

        // Eliminar el vertice de la lista de vertices
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            V vert = vertices.darElemento( i ).darInfoVertice( );
            if( vert.darId( ).equals( idVertice ) )
            {
                vertices.eliminar( i );
                return;
            }
        }
    }

    /**
     * Agrega un nuevo arco al grafo
     * @param idVerticeOrigen Identificador del v�rtice desde donde sale el arco
     * @param idVerticeDestino Identificador del v�rtice hasta donde llega el arco
     * @param infoArco Elemento del arco
     * @throws VerticeNoExisteException Si alguno de los v�rtices especificados no existe
     * @throws ArcoYaExisteException Si ya existe un arco entre esos dos v�rtices
     */
    public void agregarArco( K idVerticeOrigen, K idVerticeDestino, A infoArco ) throws VerticeNoExisteException, ArcoYaExisteException
    {
        matrizAdyacencia.agregarArco( infoArco, idVerticeOrigen, idVerticeDestino );
    }

    /**
     * Elimina el arco que existe entre dos v�rtices
     * @param idVerticeOrigen Identificador del v�rtice desde donde sale el arco
     * @param idVerticeDestino Identificador del v�rtice hasta donde llega el arco
     * @throws VerticeNoExisteException Cuando el v�rtice de salida no existe
     * @throws ArcoNoExisteException Cuando el arco no existe
     */
    public void eliminarArco( K idV1, K idV2 ) throws VerticeNoExisteException, ArcoNoExisteException
    {
        matrizAdyacencia.eliminarArco( idV1, idV2 );

    }

    // -----------------------------------------------------------------
    // M�todos consultoress
    // -----------------------------------------------------------------

    /**
     * Devuelve el v�rtice identificado con el identificador especificado
     * @param idVertice Identificador del v�rtice
     * @return V�rtice buscado
     * @throws VerticeNoExisteException Excepci�n generada cuando el v�rtice buscado no existe en el grafo
     */
    public V darVertice( K idVertice ) throws VerticeNoExisteException
    {
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            V vert = vertices.darElemento( i ).darInfoVertice( );
            if( vert.darId( ).equals( idVertice ) )
                return vert;
        }
        throw new VerticeNoExisteException( "El v�rtice buscado no existe", idVertice );
    }

    /**
     * Indica si el v�rtice con el identificador dado existe en el grafo
     * @param idVertice Identificador del v�rtice
     * @return <code>true</code> si el v�rtice con el identificador dado existe o <code>false</code> en caso contrario
     */
    public boolean existeVertice( K idVertice )
    {
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            V vert = vertices.darElemento( i ).darInfoVertice( );
            if( vert.darId( ).equals( idVertice ) )
                return true;
        }
        return false;
    }

    /**
     * Retorna los v�rtices del grafo.
     * @return Los v�rtices del grafo.
     */
    public Lista<V> darVertices( )
    {
        // Crear la lista
        Lista<V> vs = new Lista<V>( );

        // Recorrer los vertices y poblar la lista
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            vs.agregar( vertices.darElemento( i ).darInfoVertice( ) );
        }

        // Retornar la lista
        return vs;
    }

    /**
     * Devuelve el orden del grafo.
     * </p>
     * El orden de un grafo se define con el n�mero de v�rtices que tiene este
     * @return Orden del grafo
     */
    public int darOrden( )
    {
        return vertices.darLongitud( );
    }

    /**
     * Indica si existe un arco entre los v�rtices ingresados por parametros
     * @param idV1 id del primer v�rtice
     * @param idV2 id del segundo v�rtice
     * @return <code>true</code> si existe un arco entre los v�rtices ingresado o <code>false</code> en caso contrario.
     * @throws VerticeNoExisteException si alguno de los v�rtices ingresados por parametros no existe en el grafo
     */
    public boolean existeArco( K v1, K v2 ) throws VerticeNoExisteException
    {
        return matrizAdyacencia.existeArco( v1, v2 );
    }

    /**
     * Retorna el arco entre los v�rtices ingresados por parametros
     * @param idV1 id del primer v�rtice
     * @param idV2 id del segundo v�rtice
     * @return El arco entre los v�rtices ingresados por parametros
     * @throws VerticeNoExisteException si alguno de los v�rtices ingresados por parametros no existe en el grafo
     * @throws ArcoNoExisteException si no existe un arco entre esos v�rtices
     */
    public A darArco( K v1, K v2 ) throws VerticeNoExisteException, ArcoNoExisteException
    {
        return matrizAdyacencia.darArco( v1, v2 );
    }

    /**
     * Retorna el n�mero de arcos que tiene el grafo
     * @return el n�mero de arcos que tiene el grafo
     */
    public int darNArcos( )
    {
        return matrizAdyacencia.darNArcos( );
    }

    /**
     * Devuelve todos los arcos del grafo
     * @return Arcos del grafo
     */
    public Lista<A> darArcos( )
    {
        return matrizAdyacencia.darArcos( );
    }

    /**
     * El peso de un grafo es la suma de los pesos de todos sus arcos
     */
    public int darPeso( )
    {
        return matrizAdyacencia.darPeso( );
    }

    /**
     * Devuelve los id de los v�rtice sucedores a un v�rtice ingresado por par�metro
     * @param idVertice Identificador del v�rtice
     * @return Los id de los v�rtice sucedores a un v�rtice ingresado por par�metro
     * @throws VerticeNoExisteException Si el v�rtice especificado no existe
     */
    public Lista<V> darSucesores( K idVertice ) throws VerticeNoExisteException
    {
        // Crear la lista
        Lista<V> sucesores = new Lista<V>( );

        // Recorrer todos los vertices verificando si son adyacentes y poblar la lista
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            V vertice2 = vertices.darElemento( i ).darInfoVertice( );
            if( matrizAdyacencia.existeArco( idVertice, vertice2.darId( ) ) )
                sucesores.agregar( vertice2 );
        }

        // Retornar la lista
        return sucesores;
    }

    /**
     * Verifica si existe un camino entre los dos v�rtices especificados
     * @param idVerticeOrigen V�rtice de origen
     * @param idVerticeDestino V�rtice de destino
     * @return <code>true</code> si hay camino entre los dos v�rtices especificados o <code>false</code> de lo contrario
     * @throws VerticeNoExisteException Si no existe alguno de los dos v�rtices dados
     */
    public boolean hayCamino( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        reiniciarMarcasArcos( );

        return hayCaminoRecursivo( idVerticeOrigen, idVerticeDestino );
    }

    /**
     * Retorna el camino m�s corto (de menor longitud) entre el par de v�rtices especificados
     * @param idVerticeOrigen V�rtice en el que inicia el camino
     * @param idVerticeDestino V�rtice en el que termina el camino
     * @return El camino m�s corto entre el par de v�rtices especificados
     * @throws VerticeNoExisteException Si alguno de los dos v�rtices no existe
     */
    public Camino<K, V, A> darCaminoMasCorto( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        reiniciarMarcasVertices( );
        reiniciarMarcasArcos( );

        return darCaminoMasCortoRecursivo( idVerticeOrigen, idVerticeDestino );
    }

    /**
     * Retorna el camino m�s barato (de menor costo) entre el par de v�rtices especificados
     * @param idVerticeOrigen V�rtice en el que inicia el camino
     * @param idVerticeDestino V�rtice en el que termina el camino
     * @return El camino m�s barato entre el par de v�rtices especificados
     * @throws VerticeNoExisteException Si alguno de los dos v�rtices no existe
     */
    public Camino<K, V, A> darCaminoMasBarato( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        reiniciarMarcasVertices( );
        reiniciarMarcasArcos( );

        return darCaminoMasBaratoRecursivo( idVerticeOrigen, idVerticeDestino );
    }

    /**
     * Indica si hay un ciclo en el grafo que pase por el v�rtice especificado
     * @param idVertice El identificador del v�rtice
     * @return <code>true</code> si existe el ciclo o <code>false</code> en caso contrario
     * @throws VerticeNoExisteException Si el v�rtice especificado no existe
     */
    public boolean hayCiclo( K idVertice ) throws VerticeNoExisteException
    {
        reiniciarMarcasArcos( );
        return hayCaminoRecursivo( idVertice, idVertice );
    }

    /**
     * Indica si en el grafo no hay ciclos
     * @return <code>true</code> si en el grafo no hay ciclos o <code>false</code> en caso contrario
     */
    public boolean esAciclico( )
    {
        // Recorrer los v�rtices del grafo
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            try
            {
                // Si encuentro un v�rtice por el que pase un ciclo retorno true
                if( hayCiclo( vertices.darElemento( i ).darInfoVertice( ).darId( ) ) )
                    return true;
            }
            catch( VerticeNoExisteException e )
            {
                // Esto no va a suceder
            }
        }
        // No hay ciclos desde ning�n v�rtice, as� que el grafo es aciclico
        return false;
    }

    /**
     * Indica si el grafo es completo. Un grafo es completo si existe un arco entre cualquier pareja de v�rtices en el grafo.s
     * @return <code>true</code> si el grafo es completo o <code>false</code> en caso contrario
     */
    public boolean esCompleto( )
    {
        try
        {
            for( int i = 0; i < vertices.darLongitud( ); i++ )
                for( int j = i + 1; j < vertices.darLongitud( ); j++ )
                    if( !matrizAdyacencia.existeArco( vertices.darElemento( i ).darInfoVertice( ).darId( ), vertices.darElemento( j ).darInfoVertice( ).darId( ) ) )
                        return false;
        }
        catch( VerticeNoExisteException e )
        {
            // esto nunva a suceder
        }

        return true;
    }

    /**
     * Indica si el grafo es conexo
     * @return <code>true</code> si el grafo es conexo o <code>false</code> en caso contrario
     */
    public boolean esConexo( )
    {
        reiniciarMarcasVertices( );

        if( vertices.darLongitud( ) != 0 )
        {
            try
            {
                // Recuperar cualquier v�rtice del grafo
                K idVert = vertices.darElemento( 0 ).darInfoVertice( ).darId( );

                // Marcar a profundidad
                marcarSucesoresAProfundidad( idVert );

                // Buscar elementos no marcados
                for( int i = 0; i < vertices.darLongitud( ); i++ )
                {
                    K idVertice = vertices.darElemento( i ).darInfoVertice( ).darId( );
                    if( !estaMarcadoVertice( idVertice ) )
                        return false;
                }
                return true;
            }
            catch( VerticeNoExisteException e )
            {
                // esto no deber�a suceder
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    /**
     * Calcula y retorna el �rbol recubridor m�nimo de un grafo no dirigido utilizando el algoritmo de Prim.
     * @param idOrigen V�rtice a partir del cual se quiere calcular el �rbol recubridor m�nimo.
     * @return El �rbol recubridor m�nimo calculado a partir del v�rtice ingresado por par�metro.
     * @throws VerticeNoExisteException Si el v�rtice a partir del cual se quiere calcular el �rbol no existe.
     */
    public GrafoNoDirigido<K, V, A> prim( K idOrigen ) throws VerticeNoExisteException

    {
        GrafoNoDirigido<K, V, A> prim = new GrafoNoDirigido<K, V, A>( );
        reiniciarMarcasVertices( );

        try
        {
            prim.agregarVertice( darVertice( idOrigen ) );
            marcarVertice( idOrigen );

            while( prim.darOrden( ) != darOrden( ) )
            {
                K menorVertice = null;
                K origenCamino = null;

                int menorPeso = darPeso( );

                //Por cada v�rtice marcado
                for( int ver = 0; ver < prim.vertices.darLongitud( ); ver++ )
                {
                    V verticePrim = prim.vertices.darElemento( ver ).darInfoVertice( );
                    Lista<V> sucesores = darSucesores( verticePrim.darId( ) );

                    //Por cada sucesor
                    for( int suc = 0; suc < sucesores.darLongitud( ); suc++ )
                    {
                        V sucesor = sucesores.darElemento( suc );
                        if( !estaMarcadoVertice( sucesor.darId( ) ) )
                        {
                            int peso = darArco( verticePrim.darId( ), sucesor.darId( ) ).darPeso( );
                            if(peso <= menorPeso)
                            {
                                menorVertice = sucesor.darId( );
                                menorPeso = peso;
                                origenCamino = verticePrim.darId( );
                            }
                        }
                    }
                }
                prim.agregarVertice( darVertice( menorVertice ) );
                prim.agregarArco( origenCamino, menorVertice, darArco( origenCamino, menorVertice ) );
                marcarVertice( menorVertice );
            }
        }
        catch( VerticeYaExisteException e )
        {
            // Esto no deber�a suceder
        }
        catch( ArcoNoExisteException e )

        {
            // Esto no deber�a suceder
        }
        catch( ArcoYaExisteException e )
        {
            // Esto no deber�a suceder
        }
        
        return prim;
    }

    /**
     * Calcula y retorna el �rbol recubridor m�nimo de un grafo no dirigido utilizando el algoritmo de Kruskal.
     * @return El �rbol recubridor m�nimo del grafo.
     */
    public GrafoNoDirigido<K, V, A> kruskal( )
    {
        GrafoNoDirigido<K, V, A> kruskal = new GrafoNoDirigido<K, V, A>( );
        reiniciarMarcasVertices( );
        reiniciarMarcasArcos( );

        boolean termino = false;
        while( !termino )
        {
            // Encontrar el arco no marcado de menor peso
            Lista<ArcoMatriz<K, A>> arcos = matrizAdyacencia.darArcosMatriz( );
            ArcoMatriz<K, A> menorArco = null;
            try
            {
                for( int iA = 0; iA < arcos.darLongitud( ); iA++ )
                {
                    ArcoMatriz<K, A> arco = arcos.darElemento( iA );
                    K v1 = arco.darVertice1( );
                    K v2 = arco.darVertice2( );

                    if( !estaMarcadoArco( arco.darVertice1( ), arco.darVertice2( ) ) && !kruskal.hayCamino( v1, v2 ) && ( menorArco == null || menorArco.darArco( ).darPeso( ) > arco.darArco( ).darPeso( ) ) )
                        menorArco = arco;

                }
                if( menorArco == null )
                    termino = true;
                else
                {
                    try
                    {
                        marcarArco( menorArco.darVertice1( ), menorArco.darVertice2( ) );
                        
                        if( !kruskal.existeVertice( menorArco.darVertice1( ) ) )
                            kruskal.agregarVertice( darVertice( menorArco.darVertice1( ) ) );
                        if( !kruskal.existeVertice( menorArco.darVertice2( ) ) )
                            kruskal.agregarVertice( darVertice( menorArco.darVertice2( ) ) );
                        
                        kruskal.agregarArco( menorArco.darVertice1( ), menorArco.darVertice2( ), menorArco.darArco( ) );
                    }
                    catch( VerticeYaExisteException e )
                    {
                        // esto no deber�a suceder
                    }
                    catch( ArcoYaExisteException e )
                    {
                        // esto no deber�a suceder
                    }

                }
            }
            catch( VerticeNoExisteException e )
            {
                // esto no deber�a suceder
            }
            catch( ArcoNoExisteException e )
            {
                // esto no deber�a suceder
            }
        }

        return kruskal;
    }
    // -----------------------------------------------------------------
    // M�todos privados
    // -----------------------------------------------------------------

    /**
     * Borra las marcas de todos los v�rtices del grafo
     */
    private void reiniciarMarcasVertices( )
    {
        // Elimina todas las marcas presentes en los v�rtices del grafo
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            vertices.darElemento( i ).desmarcar( );
        }
    }

    /**
     * Borra las marcas de todos los arcos del grafo
     */
    private void reiniciarMarcasArcos( )
    {
        matrizAdyacencia.reiniciarMarcas( );
    }

    /**
     * Marca un v�rtice
     * @param idVertice Id del v�rtice que se quiere marcar
     * @throws VerticeNoExisteException Si el v�rtice que se quiere marcar no existe
     */
    private void marcarVertice( K idVertice ) throws VerticeNoExisteException
    {
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            VerticeGrafo<K, V> vert = vertices.darElemento( i );
            if( vert.darInfoVertice( ).darId( ).equals( idVertice ) )
            {
                vert.marcar( );
                return;
            }
        }
        throw new VerticeNoExisteException( "El v�rtice buscado no existe", idVertice );
    }

    /**
     * Marca un arco
     * @param v1 primer v�rtice conectado por el arco
     * @param v2 segundo v�rtice conectado por el arco
     * @throws VerticeNoExisteException Si alguno de los v�rtices seleccionados no existe
     * @throws ArcoNoExisteException Si no existe ning�n arco entre los v�rtices seleccionados
     */
    private void marcarArco( K v1, K v2 ) throws VerticeNoExisteException, ArcoNoExisteException
    {
        matrizAdyacencia.marcarArco( v1, v2 );
    }

    /**
     * Desmarca un v�rtice
     * @param idVertice Id del v�rtice que se quiere desmarcar
     * @throws VerticeNoExisteException Si el v�rtice que se quiere desmarcar no existe
     */
    private void desmarcarVertice( K idVertice ) throws VerticeNoExisteException
    {
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            VerticeGrafo<K, V> vert = vertices.darElemento( i );
            if( vert.darInfoVertice( ).darId( ).equals( idVertice ) )
            {
                vert.desmarcar( );
                return;
            }
        }
        throw new VerticeNoExisteException( "El v�rtice buscado no existe", idVertice );
    }


    /**
     * Verifica si un v�rtice est� marcado
     * @param idVertice Id del v�rtice a consultar
     * @return <code>true</code> si el v�rtice seleccionado est� marcado o <code>false</code> en caso contrario
     * @throws VerticeNoExisteException Si el v�rtice seleccionado no existe
     */
    private boolean estaMarcadoVertice( K idVertice ) throws VerticeNoExisteException
    {
        for( int i = 0; i < vertices.darLongitud( ); i++ )
        {
            VerticeGrafo<K, V> vert = vertices.darElemento( i );
            if( vert.darInfoVertice( ).darId( ).equals( idVertice ) )
            {
                return vert.estaMarcado( );
            }
        }
        throw new VerticeNoExisteException( "El v�rtice buscado no existe", idVertice );
    }

    /**
     * Verifica si un arco est� marcado
     * @param v1 primer v�rtice conectado por el arco
     * @param v2 segundo v�rtice conectado por el arco
     * @return <code>true</code> si el arco que conecta a los v�rtices seleccionados est� marcado o <code>false</code> en caso contrario
     * @throws VerticeNoExisteException Si alguno de los v�rtices seleccionados no existe
     * @throws ArcoNoExisteException Si no existe ning�n arco entre los v�rtices seleccionados
     */
    private boolean estaMarcadoArco( K v1, K v2 ) throws VerticeNoExisteException, ArcoNoExisteException
    {
        return matrizAdyacencia.estaMarcado( v1, v2 );
    }

    /**
     * Cacula, de forma recursiva, el camino m�s corto entre dos v�rtices.
     * @param v1 id del v�rtice origen del camino
     * @param v2 id del v�rtice destino del camino
     * @return El camino m�s corto entre dos v�rtices.
     * @throws VerticeNoExisteException Si alguno de los v�rtices seleccionados no existe
     */
    private Camino<K, V, A> darCaminoMasCortoRecursivo( K v1, K v2 ) throws VerticeNoExisteException
    {
        try
        {
            if( matrizAdyacencia.existeArco( v1, v2 ) && !estaMarcadoArco( v1, v2 ) )
            {
                Camino<K, V, A> camino = new Camino<K, V, A>( darVertice( v1 ) );

                camino.agregarArcoFinal( darVertice( v2 ), matrizAdyacencia.darArco( v1, v2 ) );

                return camino;
            }
            else
            {
                marcarVertice( v1 );
                Lista<V> sucesores = darSucesores( v1 );
                Camino<K, V, A> camino = null;
                V sucesorCorto = null;
                for( int i = 0; i < sucesores.darLongitud( ); i++ )
                {
                    V vert = sucesores.darElemento( i );
                    if( !estaMarcadoVertice( vert.darId( ) ) )
                    {
                        marcarArco( v1, vert.darId( ) );
                        Camino<K, V, A> camAux = darCaminoMasCortoRecursivo( vert.darId( ), v2 );
                        if( camAux != null && ( camino == null || camAux.darLongitud( ) < camino.darLongitud( ) ) )
                        {
                            camino = camAux;
                            sucesorCorto = vert;
                        }
                    }
                }
                desmarcarVertice( v1 );

                if( camino == null )
                    return null;
                else
                {
                    camino.agregarArcoComienzo( darVertice( v1 ), matrizAdyacencia.darArco( v1, sucesorCorto.darId( ) ) );
                    return camino;
                }
            }
        }
        catch( ArcoNoExisteException e )
        {
            // Esto no deber�a suceder
            return null;
        }

    }
    /**
     * Cacula, de forma recursiva, el camino m�s corto entre dos v�rtices.
     * @param v1 id del v�rtice origen del camino
     * @param v2 id del v�rtice destino del camino
     * @return El camino m�s corto entre dos v�rtices.
     * @throws VerticeNoExisteException Si alguno de los v�rtices seleccionados no existe
     */
    private Camino<K, V, A> darCaminoMasBaratoRecursivo( K v1, K v2 ) throws VerticeNoExisteException
    {
        try
        {
            if( matrizAdyacencia.existeArco( v1, v2 ) && !estaMarcadoArco( v1, v2 ) )
            {
                Camino<K, V, A> camino = new Camino<K, V, A>( darVertice( v1 ) );

                camino.agregarArcoFinal( darVertice( v2 ), matrizAdyacencia.darArco( v1, v2 ) );

                return camino;
            }
            else
            {
                marcarVertice( v1 );
                Lista<V> sucesores = darSucesores( v1 );
                Camino<K, V, A> camino = null;
                V sucesorBarato = null;
                A arcoBarato = null;
                for( int i = 0; i < sucesores.darLongitud( ); i++ )
                {
                    V vert = sucesores.darElemento( i );

                    if( !estaMarcadoVertice( vert.darId( ) ) )
                    {
                        marcarArco( v1, vert.darId( ) );
                        Camino<K, V, A> camAux = darCaminoMasBaratoRecursivo( vert.darId( ), v2 );
                        if( camAux != null && ( camino == null || camAux.darCosto( ) + darArco( v1, vert.darId( ) ).darPeso( ) < camino.darCosto( ) + arcoBarato.darPeso( ) ) )
                        {
                            camino = camAux;
                            sucesorBarato = vert;
                            arcoBarato = darArco( v1, vert.darId( ) );
                        }
                    }
                }
                desmarcarVertice( v1 );

                if( camino == null )
                    return null;
                else
                {
                    camino.agregarArcoComienzo( darVertice( v1 ), matrizAdyacencia.darArco( v1, sucesorBarato.darId( ) ) );
                    return camino;
                }
            }
        }
        catch( ArcoNoExisteException e )
        {
            // Esto no deber�a suceder
            return null;
        }
    }

    /**
     * Busca recursivamente la existencia de camino entre 2 v�rtices tomando en cuenta que los arcos por los que ya se pas� est�n marcados.
     * @param vertActual v�rtice a partir del cual se quiere encontrar el camino
     * @param vertCierre v�rtice al cual hay que llegar
     * @return <code>true</code> si encontro un camino desde <code>vertActual</code> hasta <code>vertCierre</code> sin pasar por arcos marcados o <code>false</code> en
     *         caso contrario
     * @throws VerticeNoExisteException Si alguno de los v�rtices seleccionados no existe
     */
    private boolean hayCaminoRecursivo( K vertActual, K vertCierre ) throws VerticeNoExisteException
    {
        // Recuperar los sucesores del vertice actual
        Lista<V> sucesores = darSucesores( vertActual );

        for( int i = 0; i < sucesores.darLongitud( ); i++ )
        {
            try
            {
                V sucesor = sucesores.darElemento( i );

                // Solo tomo en cuenta los arcos no marcados
                if( !estaMarcadoArco( vertActual, sucesor.darId( ) ) )
                {
                    // Si alguno de los sucesores es el v�rtice de cierro del ciclo, y el arco no est� marcado, encontramos el ciclo
                    if( sucesor.darId( ).equals( vertCierre ) )
                        return true;
                    // Si no es as�, sigo buscando el ciclo recursivamente usando los arcos no marcados y voy marcando los arcos por los que paso
                    else
                    {
                        marcarArco( vertActual, sucesor.darId( ) );
                        if( hayCaminoRecursivo( sucesor.darId( ), vertCierre ) )
                            return true;
                    }
                }
            }
            catch( ArcoNoExisteException e )
            {
                // Esto no deberia suceder
            }
        }
        // Si ninguno de mis sucesores puede cerrar el ciclo es porque este no existe
        return false;
    }

    /**
     * Marca a todos los v�rtices a los que se pueda llegar desde un v�rtice dado.
     * @param idVertice Id del v�rtice a partir del cual se quiere hacer la marcaci�n.
     * @throws VerticeNoExisteException Si el v�rtice a partir del cual se quiere hacer la marcaci�n no existe.
     */
    private void marcarSucesoresAProfundidad( K idVertice ) throws VerticeNoExisteException
    {
        marcarVertice( idVertice );

        Lista<V> sucesores = darSucesores( idVertice );
        for( int i = 0; i < sucesores.darLongitud( ); i++ )
        {
            K idSucesor = sucesores.darElemento( i ).darId( );
            if( !estaMarcadoVertice( idSucesor ) )
            {
                marcarSucesoresAProfundidad( idSucesor );
            }
        }

    }

}
