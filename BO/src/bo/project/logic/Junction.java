package bo.project.logic;

import java.util.ArrayList;
import java.util.Random;

public abstract class Junction {
	protected int ID;
	protected ArrayList<Road> entryRoads; // ulice entry i away pod tymi samymi
											// indeksami sa takie same
	protected ArrayList<Road> escapeRoads; // ulice z rowna reszta dzielenia
											// przez 2 sa ROWNOLEGLE
	protected int xCoordinate; // wspolrzedne
	protected int yCoordinate;

	public Junction(int ID, ArrayList<Road> entryRoads,
			ArrayList<Road> awayRoads, int xCoordinate, int yCoordinate) {
		this.ID = ID;
		this.entryRoads = entryRoads;
		this.escapeRoads = awayRoads;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	/*
	 * dodaje na ka�d� ulic� sta�� liczb� samochod�w, zale�n� od nat�enia ruchu
	 * dla uproszczenia dodaje je na pocz�tek skrzy�owania wszystkie jak to
	 * okarze si� problemem, mo�na b�dzie pokombinowa� co�
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

	public void printState() {
		for (Road road : entryRoads) {
			road.printState();
		}
	}

}
