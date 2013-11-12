package bo.project.logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Closure {
	private double xCoordinate;
	private double yCoordinate;

	protected ArrayList<Road> entranceRoads;
	protected ArrayList<Road> escapeRoads;
	protected ArrayList<Vehicle> participants;

	public Closure(double xCoordinate, double yCoordinate) {
		super();
		this.entranceRoads = new ArrayList<Road>();
		this.escapeRoads = new ArrayList<Road>();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public void addEntranceRoads(Road road) {
		this.entranceRoads.add(road);
	}

	public void addEscapeRoads(Road road) {
		this.escapeRoads.add(road);
	}

	/*
	 * Sprawdzam czy pojazd jest na skrzyzowaniu
	 */

	public boolean ifParticipant(Vehicle vehicle) {
		return Math.sqrt((vehicle.getxCoordinate() - this.getxCoordinate())
				* (vehicle.getxCoordinate() - this.getxCoordinate())
				+ (vehicle.getxCoordinate() - this.getyCoordinate())
				* (vehicle.getxCoordinate() - this.getyCoordinate())) < 1;
	}

	public ArrayList<Vehicle> findParticipants() {
		this.participants = new ArrayList<Vehicle>();
		for (Road road : entranceRoads) {
			ArrayList<Vehicle> vehicleParticipants = new ArrayList<Vehicle>();
			for (Vehicle vehicle : road.getVehicles()) {
				if (ifParticipant(vehicle)) {
					vehicleParticipants.add(vehicle);
				}

			}
			for (Vehicle vehicle : vehicleParticipants) {
				participants.add(vehicle);
				road.deleteVehicle(vehicle);
			}
		}
		return participants;
	}

	public double getxCoordinate() {
		return xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Closure) {
			Closure clos = (Closure) obj;
			if (this.xCoordinate == clos.xCoordinate
					&& this.yCoordinate == clos.yCoordinate) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public List<Road> getEscapeRoads() {
		
		return this.escapeRoads;
	}
	
	public List<Road> getEntraceRoads() {
		return this.entranceRoads;
	}

}
