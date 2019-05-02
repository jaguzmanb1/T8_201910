package lector;

import java.util.ArrayList;

public class Way {

	//XML element id
	private int id;
//	//XML element lon
	private ArrayList<String> nd;
	
	private boolean highway;

	public Way()
	{
		nd = new ArrayList();
	}
	public int getId() {
		return id;	
		}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getHighway() {
		return highway;	
		}
	
	public void setHighway(boolean id) {
		this.highway = id;
	}

	public void setNd(ArrayList<String> val) {
		nd = val;
	}
	public ArrayList<String> getNd() {
		return nd;
	}

	public String toString() {
		
		String rta;
		rta = this.id + ": \n" + nd.size();
		for(int i=0; i< nd.size();i++){
		rta += "   " + nd.get(i);
	}
		return rta;
	}
}
