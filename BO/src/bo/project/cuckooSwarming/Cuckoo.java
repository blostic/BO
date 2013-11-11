package bo.project.cuckooSwarming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import bo.project.logic.Simulator;
import bo.project.cuckooSwarming.*;

public class Cuckoo {

	private Simulator simulator;

	/*
	 * Number of Intersection in simulating problem.
	 */
	private static ArrayList<Nest> population;
	private int simulationSize;

	/**
	 * Function generates initial solutions for prosimulator,blem. Solutions are
	 * generated using random numbers.
	 * 
	 * @param numberOfSolution
	 * @param problemSize
	 * @return A list of initial solutions.
	 */

	private ArrayList<Nest> generateInitialPopulationOfNests(
			int numberOfSolution, int problemSize) {
		ArrayList<Nest> solutions = new ArrayList<Nest>();
		solutions.add(Nest.generateRandomNest(simulator, problemSize));
		return solutions;
	}

	/**
	 * Cuckoo Constructor; 
	 * 
	 * @param simulator
	 */

	public Cuckoo(Simulator simulator) {
		this.simulator = simulator;
		this.simulationSize = simulator.size;
	}

	public Solution cuckooSearch(int MaxGenerations) {
		population = generateInitialPopulationOfNests(100, this.simulationSize);
		int t = 0;
		Solution bestSolution = null;
		while (t < MaxGenerations) {
			Solution solution = generateRandomLevySolution();
			Nest nest = randomNest(population);
			if (nest.energy - energy(this.simulator, solution) < 0) {
				nest.solution = solution;
			}
			replaceWorstFraction(population, 0.1);
			bestSolution = findBestSolution(population);
			t++;
		}
		return bestSolution;
	}
	
	/**
	 * 
	 * @param population
	 * @return
	 */
	
	private Solution findBestSolution(ArrayList<Nest> population) {
		Collections.sort(population);		
		if(population.size()>0){
			return population.get(population.size()-1).solution;
		}
		return null;
	}

	private void replaceWorstFraction(ArrayList<Nest> population,
			double fraction) {
		for (int i = 0; i < population.size()*fraction; i++) {
			population.remove(i);
			population.add(Nest.generateRandomNest(simulator,simulationSize));
		}	
	}

	/**
	 * To calculate that value of energy, I'm using function from Simulator called runSimulation.
	 * Solutions with lower energy are better.
	 * 
	 * @param simulator
	 * @param solution
	 * @return "energy" of particular solution.
	 */

	public static double energy(Simulator simulator, Solution solution) {
		return simulator.runSimulation(solution.greenLightsArray,
				solution.redLightsArray);
	}

	/**
	 * @param population
	 * @return Return random Nest from population
	 */
	
	private Nest randomNest(ArrayList<Nest> population) {
		Random rand = new Random();
		return population.get(rand.nextInt(population.size()));
	}

	private Solution generateRandomLevySolution() {
		// TODO Auto-generated method stub
		return null;
	}
}
