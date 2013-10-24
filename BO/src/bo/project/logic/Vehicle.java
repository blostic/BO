package bo.project.logic;

public class Vehicle {
	protected int waitTime;
	
	public Vehicle(){
		this.waitTime=0;
	}
	
	public Vehicle(int waitTime){
		this.waitTime=waitTime;
	}
	
	public int getWaitTime(){
		return waitTime;
	}
	
	public void increaseWaitTime(int timeToIncrease){
		waitTime+=timeToIncrease;
	}
	
	public void resetWaitTime(){
		waitTime=0;
	}
}
