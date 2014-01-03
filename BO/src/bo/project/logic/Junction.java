package bo.project.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public abstract class Junction implements Serializable {
	/**
	 * 
	 */

	protected ArrayList<Road> entryRoads; // ulice entry i away pod tymi samymi
											// indeksami sa takie same
	protected ArrayList<Road> escapeRoads; // ulice z rowna reszta dzielenia
											// przez 2 sa ROWNOLEGLE
	protected int xCoordinate; // wspolrzedne
	protected int yCoordinate;

	public Junction(ArrayList<Road> entryRoads,
			ArrayList<Road> awayRoads, int xCoordinate, int yCoordinate) {
		this.entryRoads = entryRoads;
		this.escapeRoads = awayRoads;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		//for (Road r : this.entryRoads) {
		//	r.setEndCoordinates(xCoordinate, yCoordinate);
		//}
		//for (Road r : this.escapeRoads) {
		//	r.setStartCoordinates(xCoordinate, yCoordinate);
		//}
	}
	
	public int getXCoordinate(){
		return xCoordinate;
	}
	
	public List<Road> getEscapeRoads(){
		return escapeRoads;
	}
	
	public List<Road> getEntryRoads(){
		return entryRoads;
	}
	public int getYCoordinate(){
		return yCoordinate;
	}

	/*
	 * dodaje na kazda ulice stala liczbe½ samochodow, zaleznie od nateenia ruchu
	 * dla uproszczenia dodaje je na poczatek skrzyzowania wszystkie jak to
	 * okarze sie problemem, mozna bedzie pokombinowac cos
	 */
	
	public void addRandomVehicles(int timeInterval) {
		for (Road road : entryRoads) {
			for (int i = 0; i < ((double) (road.getTrafficIntensity() * timeInterval) / 3600.0)
					* road.getMaximalNumberOfVehicles(); ++i) {
				Vehicle vehicle = new Vehicle(road.getMinimalWaitTime());
				vehicle.markAsWaiting();
				road.addVehicle(vehicle);
			}
		}
	}
	
	public List<Vehicle> getEntryVehicles() {
		List<Vehicle> entryVehicles = new LinkedList<Vehicle>();
		for (Road r : entryRoads) {
			r.updateVehiclesPositions();
			entryVehicles.addAll(r.getVehicles());
		}
		return entryVehicles;
	}

	public void printState() {
		System.out.println("eR:"+entryRoads.size()+"aR:"+escapeRoads.size());
		for (Road road : entryRoads) {
			road.printState();
		}
	}

}
