package bo.project.message;

import java.io.Serializable;
import java.util.ArrayList;

import bo.project.logic.Generator;
import bo.project.logic.Intersection;

/**
 * Class used in client - server communication. 
 * It contains all traffic data needed for simulation 
 * and simulation results.
 * 
 * @author Marcin
 */
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private MessageType type;

	private ArrayList<Intersection> intersections;
	private ArrayList<Generator> generators;

	private int simulationTimeInterval;
	private double[] greenLights, redLights;

	/**
	 * Constructor
	 * 
	 * @param intersections 
	 * @param generators
	 * @param simulationTimeInterval
	 */
	public void setTrafficData(ArrayList<Intersection> intersections, ArrayList<Generator> generators, 
			 int simulationTimeInterval) {
		this.intersections = intersections;
		this.generators = generators;
		this.simulationTimeInterval = simulationTimeInterval;
	}

	/**
	 * Sets simulation result
	 * 
	 * @param greenLights	array of green light times for consecutive intersections
	 * @param redLights		array of red light times for consecutive intersections.
	 */
	public void setResult(double[] greenLights, double[] redLights) {
		this.greenLights = greenLights;
		this.redLights = redLights;
	}
	
	public double[] getGreenLights() {
		return this.greenLights;
	}

	public double[] getRedLights() {
		return this.redLights;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public ArrayList<Intersection> getIntersections() {
		return this.intersections;
	}

	public ArrayList<Generator> getGenerators() {
		return this.generators;
	}

	public int getSimulationTimeInterval() {
		return this.simulationTimeInterval;
	}


}