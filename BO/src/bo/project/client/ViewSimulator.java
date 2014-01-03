package bo.project.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import bo.project.editor.TrafficEditorFrame;
import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Simulator;
import bo.project.logic.Vehicle;

@SuppressWarnings("serial")
public class ViewSimulator extends Simulator {

	private int currentTime = 0;
	private boolean continueSimulation = true;
	transient private TrafficEditorFrame editorFrame;

	
	public TrafficEditorFrame getEditorFrame() {
		return editorFrame;
	}

	public void setEditorFrame(TrafficEditorFrame editorFrame) {
		this.editorFrame = editorFrame;
	}

	public ViewSimulator(ArrayList<Intersection> intersections,
			ArrayList<Generator> generators, int simulationTimeInterval,
			TrafficEditorFrame editorFrame) {
		super(intersections,generators,simulationTimeInterval,0);
		this.editorFrame = editorFrame;
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


	public int runSimulation() {
		continueSimulation = true;
			
		new SimulationDrawer(this.editorFrame).start();
			
		return 0;
	}
	
	public boolean isSimulationContinued() {
		return this.continueSimulation;
	}

	public void stopSimulation() {
		this.continueSimulation = false;
	}
	
	public List<Vehicle> getVehicles() {
		iterateSimulation();
		List<Vehicle> vehicles = new LinkedList<Vehicle>();
		for (Generator g : generators) {
			vehicles.addAll(g.getEntryVehicles());
		}
		for (Intersection i : intersections) {
			vehicles.addAll(i.getEntryVehicles());
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
