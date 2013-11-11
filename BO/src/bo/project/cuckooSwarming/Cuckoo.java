package bo.project.cuckooSwarming;

import java.util.ArrayList;
import java.util.Collections;

import bo.project.logic.Simulator;

public class Cuckoo {

	private Simulator simulator;
	
	/*
	 * Number of Intersection in simulating problem.
	 */
	
	private int simulationSize;
	
	/**
	 * Function generates initial solutions for problem. Solutions are generated
	 * using random numbers.
	 * @param numberOfSolution
	 * @param problemSize
	 * @return A list of initial solutions.
	 */
	
	private ArrayList<Nest> generateInitialPopulationOfNests(
			int numberOfSolution, int problemSize) {
		ArrayList<Nest> solutions = new ArrayList<Nest>();
		solutions.add(Nest.generateRandomNest(problemSize));
		return solutions;
	}

	/**
	 * Cuckoo Constructor; 
	 * @param simulator 
	 */
	
	public Cuckoo(Simulator simulator) {
		this.simulator = simulator;
		this.simulationSize = simulator.size;
	}

	public Solution cuckooSearch(int MaxGenerations) {
		ArrayList<Nest> population = generateInitialPopulationOfNests(10,this.simulationSize);
		int t = 0;
		Solution bestSolution = null;
		while (t < MaxGenerations) {
			Solution solution = generateRandomLevySolution();
			Nest nest = randomNest(population);
			if (nest.energy - energy(solution) < 0) {
				nest.solution = solution;
			}
			Collections.sort(population);
			replaceWorstFraction(population, 0.01);
			bestSolution = findBestSolution(population);
			t++;
		}
		return bestSolution;
	}

	private Solution findBestSolution(ArrayList<Nest> population) {
		// TODO Auto-generated method stub
		return null;
	}

	private void replaceWorstFraction(ArrayList<Nest> population,
			double fraction) {
		
	}

	public static double energy(Solution solution) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Nest randomNest(ArrayList<Nest> population) {
		// TODO Auto-generated method stub
		return null;
	}

	private Solution generateRandomLevySolution() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

	}
}
