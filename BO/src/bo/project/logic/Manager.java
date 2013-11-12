package bo.project.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Manager {
	private ArrayList<Road> roads = new ArrayList<Road>();
	private ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	private ArrayList<Generator> generators = new ArrayList<Generator>();
	public static double duration = 0.5;

	public Manager(ArrayList<Road> roads,
			ArrayList<Intersection> intersections,
			ArrayList<Generator> generators) {
		super();
		this.roads = roads;
		this.intersections = intersections;
		this.generators = generators;
	}

	public void updateIntersections() {
		Iterator<Intersection> interesectionIterator = intersections.iterator();
		while (interesectionIterator.hasNext()) {
			interesectionIterator.next().manageIntersection();
		}
	}

	public void updateVehiclesCoordinates() {
		/*
		 * Pojazdy, ktore w tym momencie nie sa przydzielone do zadnej drogi
		 * (uczestnicza w "skrecie") nie sa uwzgledniane w zmianie pozycji
		 */
		for (Road road : roads) {
			for (Vehicle vehicle : road.getVehicles()) {
				vehicle.updateCoordinates();
			}
		}
	}

	public void initializeIntersections() {
		for (Intersection intersection : this.intersections) {
			intersection.initialize();
		}
	}

	public void updateGenerators() {
		for (Generator generator : this.generators) {
			generator.generate();
		}
	}

	public void manageEverything() {
		this.initializeIntersections();
		while (true) {
			this.updateGenerators();
			this.updateIntersections();
			this.updateVehiclesCoordinates();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("przeszedlem petle\n");
		}
	}

	public static void main(String[] args) {
		Generator generator = new Generator(190, 190, 0.5);
		int[] time = { 3, 4 };

		Intersection intersection = new Intersection(200, 200,
				new ArrayList<Double>(Arrays.asList(0.2, 0.4, 0.2, 0.2)), time);
		Road roadA = new Road(1000, 2.2, generator, intersection);
		Road roadB = new Road(1000, 1.2, generator, intersection);
		ArrayList<Generator> generators = new ArrayList<Generator>();
		generators.add(generator);
		ArrayList<Intersection> intersections = new ArrayList<Intersection>();
		ArrayList<Road> roads = new ArrayList<Road>();
		intersections.add(intersection);
		roads.add(roadB);
		roads.add(roadA);

		Manager manager = new Manager(roads, intersections, generators);
		manager.manageEverything();
	}
}
