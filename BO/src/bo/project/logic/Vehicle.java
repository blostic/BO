package bo.project.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Vehicle implements Serializable{
	private int waitTime;
	private boolean isWaiting;
	private int x,y;
	
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

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}
