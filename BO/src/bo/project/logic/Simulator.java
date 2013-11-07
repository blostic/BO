package bo.project.logic;

import java.util.ArrayList;

public class Simulator {	
	private ArrayList<Junction> junctions;
	private int timeInterval;	//czas przejechania 1 samochodu przez skrzy¿owanie
	private int totalTime;
	private static int totalWaitTime;
	
	public Simulator(ArrayList<Junction> junctions, int simulationTimeInterval, int simulationTotalTime){
			timeInterval=simulationTimeInterval;
			totalTime=simulationTotalTime;
			totalWaitTime=0;
			this.junctions=junctions;
	} 
	
	public void initializeVehiclesOnRoads(){
		for(Junction junction: junctions){
			junction.addRandomVehicles(timeInterval);
		}
	}
	
	public int runSimulation(){
		for(int currentTime=0;currentTime<totalTime;currentTime+=timeInterval){
			for(Junction junction: junctions){
				junction.checkStatus(currentTime, timeInterval);
				junction.moveVehicles(timeInterval);
			}
			printState();
		}
		return totalWaitTime;
	}
	
	public static void increaseWaitTime(int timeToIncrease){
		totalWaitTime+=timeToIncrease;
	}
	
	private void printState(){
		for(Junction junction: junctions){
			junction.printState();
		}
		System.out.println();
	}
}
