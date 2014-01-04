package bo.project.cuckooSwarming;

public class Solution {
	double redLightsArray[];
	double greenLightsArray[];
	
	public Solution(double[] redLightsArray, double[] greenLightsArray) {
		super();
		this.redLightsArray = redLightsArray;
		this.greenLightsArray = greenLightsArray;
	}
	
	public double[] getRedLightsArray() {
		return redLightsArray;
	}
	public void setRedLightsArray(double[] redLightsArray) {
		this.redLightsArray = redLightsArray;
	}
	public double[] getGreenLightsArray() {
		return greenLightsArray;
	}
	public void setGreenLightsArray(double[] greenLightsArray) {
		this.greenLightsArray = greenLightsArray;
	}
}
