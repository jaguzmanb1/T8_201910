package lector;

import data_structures.grafo.IVertice;

public class Node implements IVertice<String>{

	//XML element id
	private String id;
	//XML element lon
	private double lon;
	//XML element lat
	private double lat;
	//XML element tag
	private double tag;



	public String darId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getlon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}

	public String toString() {
		return this.id + ":" + this.lat +  ":" + this.lon;
	}
	
}
