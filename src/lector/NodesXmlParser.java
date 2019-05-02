
package lector;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import data_structures.RedBlackBST;
import data_structures.grafo.ArcoYaExisteException;
import data_structures.grafo.GrafoNoDirigido;
import data_structures.grafo.VerticeGrafo;
import data_structures.grafo.VerticeNoExisteException;
import data_structures.grafo.VerticeYaExisteException;

public class NodesXmlParser
{
	public static void main(String[] args0) throws VerticeYaExisteException, VerticeNoExisteException, ArcoYaExisteException
	{
		//Create a empty link of Nodes initially
		RedBlackBST<String, Node> Nodes = new RedBlackBST();

		ArrayList<Way> ways = new ArrayList();

		ArrayList<arcs> arcos= new ArrayList();

		ArrayList<String> llaves = new ArrayList();

		GrafoNoDirigido<String, Node, arcs> grafo = new GrafoNoDirigido();
		try
		{
			//Create default handler instance
			NodeParserHandler handler = new NodeParserHandler();

			//Create parser from factory
			XMLReader parser = XMLReaderFactory.createXMLReader();

			//Register handler with parser
			parser.setContentHandler(handler);

			//Create an input source from the XML input stream
			File xmlFile = new File("./lector/data/Central-WashingtonDC-OpenStreetMap.xml");

			//parse the document
			InputSource source = new InputSource(new FileInputStream(xmlFile));
			parser.parse(source);

			//populate the parsed Nodes list in above created empty list; You can return from here also.
			Nodes = handler.getNodes();

			ways = handler.getWays();

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		//		for(int i=0; i<Nodes.size() ;i++)
		//		{
		//			System.out.println(Nodes.get(i));
		//		}
		//		
		//		for(int i=0; i<ways.size() ;i++)
		//		{
		//			System.out.println(ways.get(i));
		//		}



		//
		int xd=0;


		RedBlackBST<String, Node> temp = new RedBlackBST();
		for (int i=0;i<ways.size();i++){
			for (int j=0;j<((Way)ways.get(i)).getNd().size();j++){
				if(temp.size()==0||!temp.contains(((Way)ways.get(i)).getNd().get(j))){
					temp.put(((Way)ways.get(i)).getNd().get(j), Nodes.get(((Way)ways.get(i)).getNd().get(j)));
					llaves.add(((Way)ways.get(i)).getNd().get(j));
				}
				if(!(j+1>=((Way)ways.get(i)).getNd().size())){
					arcs esto = new arcs();
					esto.setInic(((Way)ways.get(i)).getNd().get(j));
					esto.setFinal(((Way)ways.get(i)).getNd().get(j+1));
					arcos.add(esto);
				}
			}
		}
		for(int x=0;x<llaves.size();x++){
			grafo.agregarVertice(temp.get(llaves.get(x)));
		}
		
		for(int y=0;y<arcos.size();y++){
			grafo.agregarArco(arcos.get(y).getInic(), arcos.get(y).getFinal(), arcos.get(y));
		}
		System.out.println("Cargo los vertices");
		System.out.println("La cantidad de vercites es de "+temp.size());
		System.out.println("La cantidad de arcos es de "+arcos.size());
	}
}
