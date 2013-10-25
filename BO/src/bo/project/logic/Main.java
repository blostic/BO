package bo.project.logic;

import java.util.ArrayList;

public class Main {
	public static void main(String args[]){
		ArrayList<Junction> junctions = new ArrayList<Junction>();
		
		ArrayList<Road> eRG1 = new ArrayList<Road>();
		eRG1.add(new Road("1-0",10, 1200));
		ArrayList<Road> aRG1 = new ArrayList<Road>();
		aRG1.add(new Road("0-1",10, 1200));
		junctions.add(new Generator(1, eRG1, aRG1, 0, 0));
		
		ArrayList<Road> eRG2 = new ArrayList<Road>();
		eRG2.add(new Road("2-0",10, 1200));
		ArrayList<Road> aRG2 = new ArrayList<Road>();
		aRG2.add(new Road("0-2",10, 1200));
		junctions.add(new Generator(2, eRG2, aRG2, 0, 0));
		
		ArrayList<Road> eRG3 = new ArrayList<Road>();
		eRG3.add(new Road("3-0",10, 1200));
		ArrayList<Road> aRG3 = new ArrayList<Road>();
		aRG3.add(new Road("0-3",10, 1200));
		junctions.add(new Generator(3, eRG3, aRG3, 0, 0));
		
		ArrayList<Road> eRG4 = new ArrayList<Road>();
		eRG4.add(new Road("4-0",10, 1200));
		ArrayList<Road> aRG4 = new ArrayList<Road>();
		aRG4.add(new Road("0-4",10, 1200));
		junctions.add(new Generator(4, eRG4, aRG4, 0, 0));
		
		ArrayList<Road> eRI0 = new ArrayList<Road>();
		eRI0.add(aRG1.get(0));
		eRI0.add(aRG2.get(0));
		eRI0.add(aRG3.get(0));
		eRI0.add(aRG4.get(0));
		
		ArrayList<Road> aRI0 = new ArrayList<Road>();
		aRI0.add(eRG1.get(0));
		aRI0.add(eRG2.get(0));
		aRI0.add(eRG3.get(0));
		aRI0.add(eRG4.get(0));
		
		junctions.add(new Intersection(0, eRI0, aRI0, 30, 30, 0, 0));
		
		Simulator simulator = Simulator.initialize(junctions, 1, 2);
		simulator.initializeVehiclesOnRoads();
		System.out.print(simulator.runSimulation());
	}
}
