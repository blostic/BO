package bo.project.logic;

import java.util.ArrayList;

public class Intersection extends Junction{
	public Intersection(ArrayList<Street> entryRoads, ArrayList<Street> awayRoads, 
			ArrayList<Integer> traffic, int greenLightTime, int redLightTime, int x, int y){
		super(entryRoads,awayRoads,traffic, greenLightTime, redLightTime, x, y);
	}
	
	public void checkStatus(){
		//TODO
	}


	public void moveVehicles() {
		//TODO
	}
}
