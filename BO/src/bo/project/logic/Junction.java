package bo.project.logic;

import java.util.ArrayList;
import java.util.Random;

public abstract class Junction implements IJunction {
	protected ArrayList<Road> entryRoads;	//ulice entry i away pod tymi samymi indeksami sa takie same
	protected ArrayList<Road> awayRoads;	//ulice z rowna reszta dzielenia przez 2 sa ROWNOLEGLE
	protected int x;	//wspolrzedne
	protected int y;
	
	public Junction(ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y){
		this.entryRoads=entryRoads;
		this.awayRoads=awayRoads;
		this.x=x;
		this.y=y;
	}
	
	//losuje sobie ileœ samochodów, nie wa¿ne ile na ka¿dej drodze bo w koñcu siê samo ustabilizuje
	//póŸniej je ustawiam jakoœ w czasie na tej drodze
	public void addRandomVehicles() {
		for(Road road: entryRoads){
			Random random = new Random();
			int lastWaitTime = 0;
			for(int i=random.nextInt(road.getMaximalNumberOfVehicles());i>0;--i){
				road.addVehicle(new Vehicle(lastWaitTime = random.nextInt((int)((road.getMinimalWaitTime()-Road.getAverageTime()*i-lastWaitTime)*(1/Road.getAverageTime())))));
			}
		}
	}
	
	
	
}
