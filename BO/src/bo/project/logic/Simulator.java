package bo.project.logic;

import java.util.ArrayList;

/*
 * generalnie ta klasa wyszla mi magiczna, to jest sam symulator
 * pseudo singleton, zeby nie zrobic dwoch
 * dzieci junction sobie pobieraja z niej wartosci statycznie jak potrzebuja
 * to pewnie bardzo zle rozwiazanie, ale tak wyszlo w praniu, jak chcecie poprawcie :D
 */

public class Simulator {
	private static volatile Simulator instance = null;
	
	private ArrayList<Junction> junctions;
	private static int timeInterval;	//czas przejechania 1 samochodu przez skrzy¿owanie
	private static int totalTime;
	private static int totalWaitTime;
	
	private Simulator(ArrayList<Junction> junctions){
		this.junctions=junctions;
	}
	
	public static Simulator initialize(ArrayList<Junction> junctions, int simulationTimeInterval, int simulationTotalTime){
		if(instance==null){
			timeInterval=simulationTimeInterval;
			totalTime=simulationTotalTime;
			totalWaitTime=0;
			instance=new Simulator(junctions);
			return instance;
		}
		else{
			return null;
		}
	} 
	
	public void initializeVehiclesOnRoads(){
		for(Junction junction: junctions){
			junction.addRandomVehicles();
		}
		printState();
	}
	
	public int runSimulation(){
		for(int currentTime=0;currentTime<totalTime;currentTime+=timeInterval){
			for(Junction junction: junctions){
				junction.checkStatus(currentTime);
				junction.moveVehicles();
			}
			printState();
		}
		return totalWaitTime;
	}
	
	private void printState(){
		for(Junction junction: junctions){
			junction.printState();
		}
		System.out.print("\n\n");
	}
	
	public static int getTimeInterval(){
		return timeInterval;
	}
	
	public static int getTotalTime(){
		return totalTime;
	}
	
	public static int getTotalWaitTime(){
		return totalWaitTime;
	}
	
	public static void increaseWaitTime(int timeToIncrease){
		totalWaitTime+=timeToIncrease;
	}
}
