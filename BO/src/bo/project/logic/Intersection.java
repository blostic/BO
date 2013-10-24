package bo.project.logic;

import java.util.ArrayList;
import java.util.Random;

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
	
	//ustalam które ulice maj¹ jakie œwiat³o w danym momencie
	public void checkStatus(int currentTime){
		if(currentTime%(greenLightTime+redLightTime)<=greenLightTime){
			for(int i=0;i<4;i+=2){
				entryRoads.get(i).setGreenLight();
				entryRoads.get(i+1).setRedLight();
			}			
		}
		else{
			for(int i=0;i<4;i+=2){
				entryRoads.get(i).setRedLight();
				entryRoads.get(i+1).setGreenLight();
			}
		}
	}

	//jakos sobie funkcyjka ustalajaca gdzie pojedzie nasz pojazd z danej ulicy wejsciowej
	private Road chooseRoad(Road entryRoad){
		Random random = new Random();
		int traffics[] = new int[3];
		int totalTraffic, tmp;
		totalTraffic=0;
		int entryRoadIndex=0;
		for(int i=0;i<4;++i){
			if(!entryRoad.equals(entryRoads.get(i))){
				traffics[i]=entryRoad.getTrafficIntensity();
				totalTraffic+=traffics[i];
			}
			else{
				entryRoadIndex=i;
				traffics[i]=0;
			}
		}
		tmp = random.nextInt(totalTraffic);
		for(int i=0;i<4;++i){
			if(entryRoadIndex!=i && tmp<awayRoads.get(i).getTrafficIntensity()){
				tmp=i;
				break;
			}
			else{
				tmp-=awayRoads.get(i).getTrafficIntensity();
			}
		}
		return awayRoads.get(tmp);
	}

	public void moveVehicles() {
		for(Road road: entryRoads){
			if(road.checkGreenLight()){
				Road awayRoad=chooseRoad(road);
				if(awayRoad.isFull()){
					Simulator.increaseWaitTime(Simulator.getTimeInterval()*road.getNumberOfWaiting());
				}
				else{
					awayRoad.addVehicle(road.getFirstWaitingVehicle());
				}
			}
			else{
				Simulator.increaseWaitTime(Simulator.getTimeInterval()*road.getNumberOfWaiting());
			}
		}
	}
}
