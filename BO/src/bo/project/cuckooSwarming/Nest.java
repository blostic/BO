package bo.project.cuckooSwarming;

import java.util.Random;

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

	public static Nest generateRandomNest(int problemSize) {
		Nest nest = new Nest();
		Random rand = new Random();
		for (int i = 0; i < problemSize; i++) {
			nest.solution.greenLightsArray[i] = rand.nextDouble() * 100;
			nest.solution.redLightsArray[i] = rand.nextDouble() * 100;
		}
		nest.energy = Cuckoo.energy(nest.solution);
		return nest;
	}
}
