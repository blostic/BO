package bo.project.logic;

import java.util.ArrayList;

public class Road {

	/*
	 * Potrzebne przy skrzyzowaniu aby nie iterowac po wszystkich pojazdach
	 */

	private ArrayList<Vehicle> vehicles;

	private Color color;
	private int maxCapacity;
	private double permittedSpeed;
	private Closure start;
	private Closure ends;

	/**
	 * Skoro rozrozniamy drogi wchodzace na skrzyzowanie i wychodzace ze
	 * skrzyzowania nie ma sensu przetrzymywac informacji o kolorze w
	 * skrzyzowaniach. To drooga bedzie posiadala kolor oraz metody
	 * odpowiedzialne za zmiane koloru (jakkolwiek beda one wywolywane z poziomu
	 * skrzyzowan). Po podjechaniu do skrzyzowania (sprawdzenie odleglosci od
	 * skrzyz.) pojazd nie bedzie zmienial swojego polozenia do momentu zmiany
	 * koloru drogi na zielny
	 */

	public Road(int maxCapacity, double permittedSpeed, Closure start,
			Closure ends) {
		super();
		this.maxCapacity = maxCapacity;
		this.permittedSpeed = permittedSpeed;
		this.start = start;
		this.ends = ends;
		this.ends.addEntranceRoads(this);
		this.start.addEscapeRoads(this);
		this.vehicles = new ArrayList<Vehicle>();
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

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

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public Color getColor() {
		return color;
	}

	public void changeColor() {
		if (this.color.equals(Color.RED)) {
			this.color = Color.GREEN;
		} else {
			this.color = Color.RED;
		}
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getPermittedSpeed() {
		return permittedSpeed;
	}

	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
	}

	public void deleteVehicle(Vehicle vehicle) {
		this.vehicles.remove(vehicle);
	}
}
