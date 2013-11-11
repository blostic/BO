package bo.project.cuckooSwarming;

import java.util.Random;

public class Nest implements Comparable<Nest> {

	public Solution solution;
	public double energy;

	@Override
	public int compareTo(Nest o) {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	public static Nest generateRandomNest(int problemSize) {
		Nest nest = new Nest();
		Random rand = new Random();
		for (int i = 0; i < problemSize; i++) {
			nest.solution.greenLightsArray[i]= rand.nextDouble() * 10 ;
			nest.solution.redLightsArray[i] = rand.nextDouble() * 10;
		}
		return nest;
	}
}
