package bo.project.logic;

import java.util.ArrayList;
import java.util.Random;

public abstract class Junction{
	protected int ID;
	protected ArrayList<Road> entryRoads;	//ulice entry i away pod tymi samymi indeksami sa takie same
	protected ArrayList<Road> escapeRoads;	//ulice z rowna reszta dzielenia przez 2 sa ROWNOLEGLE
	protected int x;	//wspolrzedne
	protected int y;
	
	public Junction(int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y){
		this.ID=ID;
		this.entryRoads=entryRoads;
		this.escapeRoads=awayRoads;
		this.x=x;
		this.y=y;
	}
	
	/*
	 * dodaje na ka¿d¹ ulicê sta³¹ liczbê samochodów, zale¿n¹ od natê¿enia ruchu
	 * dla uproszczenia dodaje je na pocz¹tek skrzy¿owania wszystkie
	 * jak to okarze siê problemem, mo¿na bêdzie pokombinowaæ coœ
	 */
	public void addRandomVehicles(int timeInterval) {
		for(Road road: entryRoads){
			for(int i=0;i<((double)(road.getTrafficIntensity()*timeInterval)/3600.0)*road.getMaximalNumberOfVehicles();++i){
				Vehicle vehicle = new Vehicle(road.getMinimalWaitTime());
				vehicle.markAsWaiting();
				road.addVehicle(vehicle);
			}
		}
	}
	
	public void printState(){
		for(Road road: entryRoads){
			road.printState();
		}
	}
	
}
