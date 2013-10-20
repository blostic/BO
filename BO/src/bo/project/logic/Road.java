package bo.project.logic;

import java.util.ArrayList;

public class Road {
	private ArrayList<Vehicle> vehicles;
	private int maxCapacity;
	private double length;
	private double permittedSpeed;
	private Closure start;
	private Closure ends;
	
	public Closure getStart() {
		return start;
	}

	public void setStart(Closure start) {
		this.start = start;
	}
	public Closure getEnds() {
		return ends;
	}

	public void setEnds(Closure ends) {
		this.ends = ends;
	}

	public Road(int maxCapacity, double length, double permittedSpeed,
			Closure start, Closure ends) {
		super();
		this.maxCapacity = maxCapacity;
		this.length = length;
		this.permittedSpeed = permittedSpeed;
		this.start = start;
		this.ends = ends;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public double getLength() {
		return length;
	}

	public double getPermittedSpeed() {
		return permittedSpeed;
	}

	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
	}

}
