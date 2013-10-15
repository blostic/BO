package bo.project.cuckooSwarming;
import java.util.ArrayList;
import java.util.Collections;

public class Cuckoo {

	private ArrayList<Nest>  generateInitialPopulationOfNests(int n) {
		return null;
	}

	public Solution cuckooSearch(int MaxGenerations) {
		ArrayList<Nest> population = generateInitialPopulationOfNests(4);
		int t = 0;
		Solution bestSolution = null;
		while (t < MaxGenerations) {
			Solution solution = generateRandomLevySolution();
			Nest nest = randomNest(population);
			if(nest.energy - energy(solution)<0){
				nest.solution = solution;
			}
			Collections.sort(population);
			replaceWorstFraction(population,0.01);
			bestSolution = findBestSolution(population);
			t++;
		}
		return bestSolution;
	}
	
	private Solution findBestSolution(ArrayList<Nest> population) {
		// TODO Auto-generated method stub
		return null;
	}

	private void replaceWorstFraction(ArrayList<Nest> population, double fraction) {
		// TODO Auto-generated method stub
		
	}

	private double energy(Solution solution) {
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
