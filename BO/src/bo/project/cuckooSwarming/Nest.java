package bo.project.cuckooSwarming;

public class Nest implements Comparable<Nest>{

	public Object solution;
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
}
