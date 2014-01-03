package bo.project.cuckooSwarming;

import java.util.Random;

import bo.project.logic.Simulator;

public class Nest implements Comparable<Nest> {

	public Solution solution;
	public Double energy;

	@Override
	public int compareTo(Nest o) {
		return (this.energy).compareTo(o.energy);
	}

	@Override
	public boolean equals(Object obj) {
		return solution.redLightsArray.equals(((Solution) obj).redLightsArray)
				&& solution.greenLightsArray
						.equals(((Solution) obj).greenLightsArray);
	}

	@Override
	public int hashCode() {
		Double tmp = 0.0;
		for (int i = 0; i < solution.greenLightsArray.length; i++) {
			tmp += solution.greenLightsArray[i] * i
					+ solution.redLightsArray[i] * i;
		}
		return tmp.intValue();
	}

	public static Nest generateRandomNest(Simulator simulator, int problemSize  ) {
		Nest nest = new Nest();
		Random rand = new Random();
		double redLightsArray[] = new double[problemSize];
		double greenLightsArray[] = new double[problemSize];
		for (int i = 0; i < problemSize; i++) {
			greenLightsArray[i] = rand.nextDouble() * 100;
			redLightsArray[i] = rand.nextDouble() * 100;
		}
		nest.solution = new Solution(redLightsArray, greenLightsArray);
		nest.energy = Cuckoo.energy(simulator,nest.solution);
		return nest;
	}
}
