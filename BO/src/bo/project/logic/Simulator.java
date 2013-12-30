package bo.project.logic;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Simulator implements Serializable{
	protected ArrayList<Intersection> intersections;
	protected ArrayList<Generator> generators;
	protected int timeInterval;

	/*
	 * czas przejechania 1 samochodu przez skrzyzowanie
	 */

	protected int totalTime;
	protected static int totalWaitTime;

	/*
	 * Rozmiar problemu = liczba skrzyzowan;
	 */

	public int size;

	public Simulator(ArrayList<Intersection> intersections,
			ArrayList<Generator> generators, int simulationTimeInterval,
			int simulationTotalTime) {
		timeInterval = simulationTimeInterval;
		totalTime = simulationTotalTime;
		totalWaitTime = 0;
		this.intersections = intersections;
		this.generators = generators;
		this.size = intersections.size();
	}

	public void initializeVehiclesOnRoads() {
		for (Junction junction : intersections) {
			junction.addRandomVehicles(timeInterval);
		}
		for (Junction junction : generators) {
			junction.addRandomVehicles(timeInterval);
		}
	}

	public void setLights(double[] greenLights, double[] redLights) {
		for (int i = 0; i < intersections.size(); ++i) {
			intersections.get(i).setGreenLightTime(greenLights[i]);
			intersections.get(i).setRedLightTime(redLights[i]);
		}
	}


	public int runSimulation(double[] greenLights, double[] redLights) {
		this.setLights(greenLights, redLights);
		for (int currentTime = 0; currentTime < totalTime; currentTime += timeInterval) {
			for (Generator generator : generators) {
				generator.checkStatus(currentTime, timeInterval);
				generator.moveVehicles(timeInterval);
			}
			for (Intersection intersection : intersections) {
				intersection.checkStatus(currentTime);
				intersection.moveVehicles(timeInterval);
			}
			// printState()
			
		}
		return totalWaitTime;
	}


	public static void increaseWaitTime(int timeToIncrease) {
		totalWaitTime += timeToIncrease;
	}

	private void printState() {
		for (Generator junction : generators) {
			junction.printState();
		}
		for (Intersection junction : intersections) {
			junction.printState();
		}
		System.out.println();
	}
}
