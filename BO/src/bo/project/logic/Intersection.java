package bo.project.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Intersection extends Closure {

	private ArrayList<Road> entranceRoads;
	private ArrayList<Double> escapeRoadProbability;
	
	public Intersection(double xCoordinate, double yCoordinate,
			ArrayList<Road> escapeRoads, ArrayList<Road> entranceRoads,
			ArrayList<Double> escapeRoadProbability) {
		super(xCoordinate, yCoordinate, escapeRoads);
		this.entranceRoads = entranceRoads;
		this.escapeRoadProbability = escapeRoadProbability;
		Iterator<Double> it = escapeRoadProbability.iterator();
		this.escapeRoadProbability = new ArrayList<Double>();
		double previos = 0;
		while (it.hasNext()) {
			previos += it.next();
			this.escapeRoadProbability.add(previos);
		}
	}

	/*
	 * randomTurn ma prawidlowy rozklad prawdopodobienstwa dzięki zastosowanie
	 * dystrybuanty
	 */

	public void randomTurn(Vehicle vehicle) {
		Double rand = new Random().nextDouble();
		int who = 0;
		for (double d : this.escapeRoadProbability) {
			System.out.println("d = " + d + " rand = " + rand);
			if (d > rand) {
				System.out.println(who);
				break;
			}
			who++;
		}
		Closure closure = this.escapeRoads.get(who).getEnds();

		if (closure.equals(this)) {
			vehicle.setdestination(this.escapeRoads.get(who).getEnds(),
					this.escapeRoads.get(who));
		} else {
			vehicle.setdestination(this.escapeRoads.get(who).getStart(),
					this.escapeRoads.get(who));
		}
	}
}
