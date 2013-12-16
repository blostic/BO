package bo.project.server;

import java.io.IOException;

import bo.project.cuckooSwarming.Cuckoo;
import bo.project.cuckooSwarming.Solution;
import bo.project.logic.Simulator;
import bo.project.message.Message;
import bo.project.message.MessageType;

public class Main {
	
	public static final int SIMULATION_TIME_INTERVAL = 1;
	public static final int SIMULATION_TOTAL_TIME = 100;
	public static final int MAX_GENERATIONS = 100;
	
	public static void main(String[] args) throws IOException {
		
		MessageReceiver messageReceiver = new MessageReceiver(6060,15);
		messageReceiver.start();
		
		while (true) {
			Message request = messageReceiver.getRequest();
			Simulator simulator = new Simulator(request.getIntersections(), request.getGenerators(),
								SIMULATION_TIME_INTERVAL, SIMULATION_TOTAL_TIME);
			simulator.initializeVehiclesOnRoads();
			double redLightsArray[] = {30};
			double greenLightsArray[] = {30};
			
			simulator.runSimulation(redLightsArray,greenLightsArray);
			
			Cuckoo cuckoo = new Cuckoo(simulator);
			Solution solution = cuckoo.cuckooSearch(MAX_GENERATIONS);
			request.setResult(solution.greenLightsArray, solution.redLightsArray);
			request.setType(MessageType.SOL_RESPONSE);
			messageReceiver.addResponse(request);
		}
	}

}
