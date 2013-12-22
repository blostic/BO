package bo.project.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Simulator;
import bo.project.logic.Vehicle;

public class ViewSimulator extends Simulator {

	private int currentTime = 0;

	public ViewSimulator(ArrayList<Intersection> intersections,
			ArrayList<Generator> generators, int simulationTimeInterval,
			double[] greenLights, double[] redLights) {
		super(intersections,generators,simulationTimeInterval,0);
		this.setLights(greenLights, redLights);	
	}

	private void iterateSimulation() {

		for (Generator generator : generators) {
			generator.checkStatus(currentTime, timeInterval);
			generator.moveVehicles(timeInterval);
		}
		for (Intersection intersection : intersections) {
			intersection.checkStatus(currentTime);
			intersection.moveVehicles(timeInterval);
		}
		currentTime += timeInterval;
	}


	@Override
	public int runSimulation(double[] greenLights, double[] redLights) {
		//		this.setLights(greenLights, redLights);
		//		//TODO: while (true)? while (flag)?
		//		for (int currentTime = 0; currentTime < totalTime; currentTime += timeInterval) {
		//			for (Generator generator : generators) {
		//				generator.checkStatus(currentTime, timeInterval);
		//				generator.moveVehicles(timeInterval);
		//			}
		//			for (Intersection intersection : intersections) {
		//				intersection.checkStatus(currentTime);
		//				intersection.moveVehicles(timeInterval);
		//			}
		//			
		//		}
		//		return totalWaitTime;
		return -1;
	}


	public List<Vehicle> getVehicles() {
		iterateSimulation();
		List<Vehicle> vehicles = new LinkedList<Vehicle>();
		for (Generator g : generators) {
			vehicles.addAll(g.getWaitingVehicles());
		}
		for (Intersection i : intersections) {
			vehicles.addAll(i.getWaitingVehicles());
		}
		return vehicles;
	}

	//	private void updateVehiclesOnView() {
	//		//TODO: View.removeAllVehicles();
	//		for (Generator g: generators) {
	//			for (Vehicle v : g.getWaitingVehicles()) {
	//				//TODO: View.draw(v);
	//			}
	//		}
	//		for (Intersection i: intersections) {
	//			for (Vehicle v : i.getWaitingVehicles()) {
	//				//TODO: View.draw(v);
	//			}
	//		}
	//	}
}
