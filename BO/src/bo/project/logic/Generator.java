package bo.project.logic;

import java.util.ArrayList;

public class Generator extends Closure{
	
	public Generator(double xCoordinate, double yCoordinate,
			ArrayList<Road> escapeRoads) {
		super(xCoordinate, yCoordinate, escapeRoads);
	}

	private ArrayList<Road> escapeRoads;
	
	/**
	 * Performance definiuje jaka jest "wydajność" generatora. s
	 */
	
	private double performance;
	
	public double getPerformance() {
		return performance;
	}

	public void setPerformance(double performance) {
		this.performance = performance;
	}

	public Vehicle generate(){
		return null;
	}
	
	public void checkStatus(){
		
	}
	
}
