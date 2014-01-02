package bo.project.client;

import java.util.List;

import bo.project.logic.Vehicle;

public class SimulationDrawer extends Thread {

	private ViewSimulator simulator;
	
	public SimulationDrawer(ViewSimulator simulator) {
		this.simulator = simulator;
	}
	
	@Override
	public void run() {
		
		List<Vehicle> vehicles;
		
		while (simulator.isSimulationContinued()) {
			/*TODO
			 * remove old cars
			 */
			vehicles = simulator.getVehicles();
			for (Vehicle v : vehicles) {
				/*TODO
				 * draw car on position (v.getX(), v.getY())
				 */
			}
			try {
				/*TODO
				 * sleep time - speed of simulation
				 * should be possible to set by user
				 */
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
	}
	
}
