package bo.project.logic;

import java.util.ArrayList;


public abstract class Closure {
	private double xCoordinate;
	private double yCoordinate;
	public ArrayList<Road> escapeRoads;
	
	
	public Closure(double xCoordinate, double yCoordinate,
			ArrayList<Road> escapeRoads) {
		super();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.escapeRoads = escapeRoads;
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

}
