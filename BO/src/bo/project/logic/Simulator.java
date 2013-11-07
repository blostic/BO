package bo.project.logic;

import java.util.ArrayList;

public class Simulator {	
	private ArrayList<Intersection> intersections;
	private ArrayList<Generator> generators;
	private int timeInterval;	//czas przejechania 1 samochodu przez skrzy¿owanie
	private int totalTime;
	private static int totalWaitTime;
	
	public Simulator(ArrayList<Intersection> intersections, ArrayList<Generator> generators, int simulationTimeInterval, int simulationTotalTime){
			timeInterval=simulationTimeInterval;
			totalTime=simulationTotalTime;
			totalWaitTime=0;
			this.intersections=intersections;
			this.generators=generators;
	} 
	
	public void initializeVehiclesOnRoads(){
		for(Junction junction: intersections){
			junction.addRandomVehicles(timeInterval);
		}
		for(Junction junction: generators){
			junction.addRandomVehicles(timeInterval);
		}
	}
	
	public void setLights(int[] greenLights, int[] redLights){
		for(int i=0;i<intersections.size();++i){
			intersections.get(i).setGreenLightTime(greenLights[i]);
			intersections.get(i).setRedLightTime(redLights[i]);
		}
	}
	
	public int runSimulation(){
		for(int currentTime=0;currentTime<totalTime;currentTime+=timeInterval){
			for(Generator generator: generators){
				generator.checkStatus(currentTime, timeInterval);
				generator.moveVehicles(timeInterval);
			}
			for(Intersection intersection: intersections){
				intersection.checkStatus(currentTime);
				intersection.moveVehicles(timeInterval);
			}
			//printState();
		}
		return totalWaitTime;
	}
	
	public static void increaseWaitTime(int timeToIncrease){
		totalWaitTime+=timeToIncrease;
	}
	
	private void printState(){
		for(Generator junction: generators){
			junction.printState();
		}
		for(Intersection junction: intersections){
			junction.printState();
		}
		System.out.println();
	}
}
