package bo.project.client;

import java.util.List;

import bo.project.editor.TrafficEditorFrame;
import bo.project.logic.Vehicle;

public class SimulationDrawer extends Thread {

	private TrafficEditorFrame editorFrame;
	private static int sleepTime = 50;
	
	public static void setSimulationSpeed(int speed) {
		sleepTime = Math.round(-2f*(float)speed + 201f);
	}
	
	
	
	public SimulationDrawer(TrafficEditorFrame frame) {
		this.editorFrame = frame;
	}
	
	@Override
	public void run() {
		
		List<Vehicle> vehicles;
		ViewSimulator simulator = editorFrame.getSimulator();
		
		while (simulator.isSimulationContinued()) {
			/*TODO
			 * remove old cars
			 */
			vehicles = simulator.getVehicles();
			for (Vehicle v : vehicles) {
				/*TODO
				 * draw car on position (v.getX(), v.getY())
				 */
				editorFrame.getDrawingArea().getParent().repaint();
			}
			try {
				/*TODO
				 * sleep time - speed of simulation
				 * should be possible to set by user
				 */
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
	}
	
}
