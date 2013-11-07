package bo.project.logic;

import java.util.ArrayList;
import java.util.Random;

public class Intersection extends Junction{
	protected int greenLightTime;	//czas w sekundach œwiecenia zielonego œwiat³a dla ulicy pod indeksem 0
	protected int redLightTime;	//jw
	
	public Intersection(int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, 
			int greenLightTime, int redLightTime, int x, int y){
		super(ID, entryRoads, awayRoads, x, y);
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
	/*
	 * ustalam które ulice maj¹ jakie œwiat³o w danym momencie
	 */
	public void checkStatus(int currentTime, int timeInterval){
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

	/*
	 * funkcja losuj¹ca drogê w któr¹ pojedzie samochód
	 */
	private Road chooseRoad(Road entryRoad){
		Random random = new Random();
		int traffics[] = new int[4];
		int totalTraffic, tmp;
		totalTraffic=0;
		int entryRoadIndex=0;
		for(int i=0;i<4;++i){	//zliczam natezenie sumaryczne i zapisuje sobie natezenia drog, dajac za ta droge 0
			if(!entryRoad.equals(entryRoads.get(i))){
				traffics[i]=escapeRoads.get(i).getTrafficIntensity();
				totalTraffic+=traffics[i];
			}
			else{
				entryRoadIndex=i;
				traffics[i]=0;
			}
		}
		tmp = random.nextInt(totalTraffic);
		for(int i=0;i<4;++i){
			if(entryRoadIndex!=i && tmp<traffics[i]){
				tmp=i;
				break;
			}
			else{
				tmp-=traffics[i];
			}
		}
		return escapeRoads.get(tmp);
	}

	public void moveVehicles(int timeInterval) {
		for(Road road: entryRoads){
			if(road.checkGreenLight() && !road.isEmpty()){
				Road awayRoad=chooseRoad(road);
				if(awayRoad.isFull()){
					Simulator.increaseWaitTime(timeInterval*road.getNumberOfWaiting());
				}
				else{
					Vehicle first = road.getFirstWaitingVehicle();
					if(first!=null){		//nie ma samochodu ktory juz dojechal do skrzyzowania - nie robie nic, jest -przenosze
						first.resetWaitTime();
						first.resetWaiting();
						awayRoad.addVehicle(first);
					}
				}
			}
			else{
				Simulator.increaseWaitTime(timeInterval*road.getNumberOfWaiting());
			}
			road.moveVehiclesOnRoad(timeInterval); //aktualizuje czasy przejazdów samochodów, które pozostaj¹ na drodze
		}
	}
}
