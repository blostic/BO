package bo.project.logic;

import java.util.ArrayList;

public class Intersection extends Junction{
	protected int greenLightTime;	//czas w sekundach œwiecenia zielonego œwiat³a dla ulicy pod indeksem 0
	protected int redLightTime;	//jw
	
	public Intersection(ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, 
			int greenLightTime, int redLightTime, int x, int y){
		super(entryRoads, awayRoads, x, y);
		this.greenLightTime=greenLightTime;
		this.redLightTime=redLightTime;
	}
	
	public void setGreenLightTime(int newTime){
		this.greenLightTime=newTime;
	}
	
	public void setRedLightTime(int newTime){
		this.redLightTime=newTime;
	}
	
	public int getGreenLightTime(){
		return greenLightTime;
	}
	
	public int getRedLightTime(){
		return redLightTime;
	}
	
	public void checkStatus(){
		//TODO
	}


	public void moveVehicles() {
		//TODO
	}
}
