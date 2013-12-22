package bo.project.server;

import bo.project.cuckooSwarming.Cuckoo;
import bo.project.cuckooSwarming.Solution;
import bo.project.logic.Simulator;
import bo.project.message.Message;
import bo.project.message.MessageType;

/**
 * Class used to manage and compute solutions.
 * 
 * @author Marcin
 *
 */
public class RequestHandler extends Thread {

	private MessageReceiver messageReceiver;
	private boolean isAlive = true;

	/**
	 * Constructor
	 * 
	 * @param messageReceiver		client message handler
	 */
	public RequestHandler(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	/**
	 * Safe thread stop
	 */
	public void close() {
		this.isAlive = false;
	}

	@Override
	public void run() {

		while (this.isAlive) {
			Message request = messageReceiver.getRequest();
			Main.info("Processing request " + request.getId() + "...");

			/* Computing solution */
			Simulator simulator = new Simulator(request.getIntersections(), request.getGenerators(),
					Main.SIMULATION_TIME_INTERVAL, Main.SIMULATION_TOTAL_TIME);
			simulator.initializeVehiclesOnRoads();
			double redLightsArray[] = {30};
			double greenLightsArray[] = {30};

			simulator.runSimulation(redLightsArray,greenLightsArray);

			Cuckoo cuckoo = new Cuckoo(simulator);
			Solution solution = cuckoo.cuckooSearch(Main.MAX_GENERATIONS);
			
			Main.info("Request processed.");
			
			/* Adding result to response queue */
			request.setResult(solution.greenLightsArray, solution.redLightsArray);
			request.setType(MessageType.SOL_RESPONSE);
			messageReceiver.addResponse(request);
			Main.info("Response queue updated.");    
		}

	}

}
