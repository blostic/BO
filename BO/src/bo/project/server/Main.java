package bo.project.server;

import java.io.IOException;

import bo.project.logic.Simulator;
import bo.project.message.Message;
import bo.project.message.MessageType;

public class Main {
	
	public static final int simulationTimeInterval = 1;
	public static final int simulationTotalTime = 100;
	
	public static void main(String[] args) throws IOException {
		
		MessageReceiver messageReceiver = new MessageReceiver(6060,15);
		messageReceiver.start();
		
		while (true) {
			Message request = messageReceiver.getRequest();
			Simulator simulator = new Simulator(request.getIntersections(), request.getGenerators(),
								simulationTimeInterval, simulationTotalTime);
			simulator.initializeVehiclesOnRoads();
			double redLightsArray[] = {30};
			double greenLightsArray[] = {30};
			
			simulator.runSimulation(redLightsArray,greenLightsArray);
			
			request.setResult(simulator.getGreenLights(), simulator.getRedLights());
			request.setType(MessageType.SOL_RESPONSE);
			messageReceiver.addResponse(request);
		}
	}

}
