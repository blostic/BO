package bo.project.logic;

public class Vehicle {
	private int waitTime;
	private boolean isWaiting;
	
	public Vehicle(){
		this.waitTime=0;
		this.isWaiting=false;
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
	
	public void markAsWaiting(){
		isWaiting=true;
	}
	
	public void resetWaiting(){
		isWaiting=false;
	}
	
	public boolean isWaiting(){
		return isWaiting;
	}
}
