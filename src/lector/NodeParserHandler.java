package lector;
import java.util.ArrayList;
import java.util.Stack;



import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import data_structures.RedBlackBST;

public class NodeParserHandler extends DefaultHandler
{
	//This is the list which shall be populated while parsing the XML.
	private RedBlackBST<String, Node> NodeList = new RedBlackBST();
	
	private ArrayList<Way> wayList = new ArrayList();

	//As we read any XML element we will push that in this stack
	private Stack elementStack = new Stack();

	//As we complete one Node block in XML, we will push the Node instance in NodeList
	private Stack objectStack = new Stack();
	
	private Stack objectWayStack = new Stack();
	
	private ArrayList nd = new ArrayList();
	
	private int hw = 0;

	public void startDocument() throws SAXException
	{
		//System.out.println("start of the document   : ");
	}

	public void endDocument() throws SAXException
	{
		//System.out.println("end of the document document     : ");
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		//Push it in element stack
		this.elementStack.push(qName);
		
		Way way = new Way();


		//If this is start of 'Node' element then prepare a new Node instance and push it in object stack
		if ("node".equals(qName))
		{
			//New Node instance
			Node Node = new Node();

			//Set all required attributes in any XML element here itself
			if(attributes != null && attributes.getLength() > 1)
			{
				Node.setId((attributes.getValue(0)));

				Node.setLat(Double.parseDouble(attributes.getValue(1)));

				Node.setLon(Double.parseDouble(attributes.getValue(2)));


			}
			this.objectStack.push(Node);
		}
		
		
		else if ("way".equals(qName))
		{
			//New Node instance
			way = new Way();

			//Set all required attributes in any XML element here itself
			if(attributes != null && attributes.getLength() > 0)
			{
				way.setId(Integer.parseInt(attributes.getValue(0)));
			}
			this.objectWayStack.push(way);  
			
			hw = 1;
		}
		
		else if ("nd".equals(qName))
		{
			nd.add(attributes.getValue(0));
		}
		
		else if ("tag".equals(qName))
		{
			if(attributes != null && attributes.getLength() > 0 && hw == 1)
			{
				String hww = attributes.getValue(0);
				
				if(hww.compareToIgnoreCase("highway")==0)
				{
					hw = 2;
				}
			}
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		//Remove last added  element
		this.elementStack.pop();

		//Node instance has been constructed so pop it from object stack and push in NodeList
		if ("node".equals(qName))
		{
			Node object = (Node) this.objectStack.pop();
			this.NodeList.put(object.darId(), object);
		}
		if ("way".equals(qName))
		{
			Way object = (Way) this.objectWayStack.pop();
			object.setNd(nd);
			nd = new ArrayList();
			if(hw == 2){
			hw = 0;
			this.wayList.add(object);
			
			}
		}
	}

	/**
	 * This will be called everytime parser encounter a value node
	 * */
	public void characters(char[] ch, int start, int length) throws SAXException
	{
	}

	/**
	 * Utility method for getting the current element in processing
	 * */
	private String currentElement()
	{
		return (String) this.elementStack.peek();
	}

	//Accessor for NodeList object
	public RedBlackBST<String, Node> getNodes()
	{
		return NodeList;
	}
	
	public ArrayList getWays()
	{
		return wayList;
	}
}
