package bo.project.logic;

import java.util.ArrayList;
import java.util.Random;

public class Intersection extends Junction{
	protected int greenLightTime;	//czas w sekundach �wiecenia zielonego �wiat�a dla ulicy pod indeksem 0
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
	 * ustalam kt�re ulice maj� jakie �wiat�o w danym momencie
	 * zwracam true je�li maj� �wiat�a zielone drogi o parzystym modulo 2
	 */
	public boolean checkStatus(int currentTime){
		if(currentTime%(greenLightTime+redLightTime)<greenLightTime){
			for(int i=0;i<4;i+=2){
				entryRoads.get(i).setGreenLight();
				entryRoads.get(i+1).setRedLight();
			}	
			return true;
		}
		else{
			for(int i=0;i<4;i+=2){
				entryRoads.get(i).setRedLight();
				entryRoads.get(i+1).setGreenLight();
			}
			return false;
		}
	}

	/*
	 * funkcja losuj�ca drog� w kt�r� pojedzie samoch�d
	 * 
	 * mam 4 natezenia ruchu na ulicach i wiem, z jakiej ulicy wyjezdza samochod
	 * zapamietuje sobie w talicy traffics natezenia pozostalych, dla tej na ktorej jestem daje 0
	 * zapamietuje tez sume natezen pozostalych i indeks drogi na ktorej jestem
	 * pozniej losuje liczbe z przedzialu 0..sumy natezen-1
	 * przechodze po tablicy, sprawdzam czy wylosowana liczba jest mniejsza od wartosci w polu
	 * jak tak - mam wylosowana droge
	 * jak nie - zmniejszam wylosowana wartosc o natezenie drogi
	 * 
	 * czyli np jak mam traffics = [1, 0, 3, 4]
	 * wylosowalem z 0-7 powiedzmy 2, tmp = 2
	 * czy 2 mniejsze od 1? - nie, tmp = 1
	 * czy 1 mniejsze od 0? (ulica na ktorej jestem, nie moge wylosowac) - nie, tmp = 1
	 * czy 1 mniejsze od 3? - tak, wylosowalem 3 ulice
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
			road.moveVehiclesOnRoad(timeInterval); //aktualizuje czasy przejazd�w samochod�w, kt�re pozostaj� na drodze
		}
	}
}
