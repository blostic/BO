package bo.project.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Intersection extends Closure{
	private ArrayList<Road> entranceRoads;  
	private HashMap<Road,Double> escapeRoads = new HashMap<Road,Double>();

	public Intersection(ArrayList<Road> entranceRoads,
			HashMap<Road, Double> escapeRoads) {
		super();
		this.entranceRoads = entranceRoads;
		this.escapeRoads = escapeRoads;
	}
	
	public void randomTurn(Vehicle vehicle){
		Random random = new Random();
		
	}
}
