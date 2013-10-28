package bo.project.logic;

import java.util.Random;

public class Generator extends Closure {

	public Generator(double xCoordinate, double yCoordinate,
			double performance) {
		super(xCoordinate, yCoordinate);
		this.performance = (performance > 1.0) ? 1.0 : performance;
	}

	/**
	 * Performance definiuje jaka jest "wydajność" generatora (samochod/sekunda)
	 * - max 1
	 */

	private double performance;

	public double getPerformance() {
		return performance;
	}

	public void setPerformance(double performance) {
		this.performance = performance;
	}

	public Vehicle generate() {
		Random rand = new Random();
		System.out.println(escapeRoads.size());
		if (rand.nextDouble() < performance) {
			Road road = escapeRoads.get(rand.nextInt(escapeRoads.size()));
			if (road.getEnds().equals(this)) {
				return new Vehicle(road.getStart(), this, road);
			} else {
				return new Vehicle(road.getEnds(), this, road);
			}
		}
		return null;
	}

}
