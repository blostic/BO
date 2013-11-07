package bo.project.logic;

import java.util.ArrayList;
import java.util.Random;

public class Generator extends Junction{
	private boolean generateFlag;
	Random random;
	
	public Generator(int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y){
		super(ID, entryRoads,awayRoads, x, y);
		generateFlag=false;
		random = new Random();
	}
	
	/*
	 * losuje czy powinienem dodawac nowe pojazdy
	 * 
	 * dziwny warunek w ifie: timeInterval - czas przejazdu jednego samochodu
	 * trafficIntensity/3600 - ilosc samochodow na sekunde wiec powinno to nam dac
	 *  ilosc samochodow w czasie przejazdu 1 samochodu, max 1 :)
	 */
	public void checkStatus(int currentTime, int timeInterval){
		for(Road road: escapeRoads){
			if(random.nextDouble()<=(road.getTrafficIntensity()*timeInterval)/(3600.0)){
				generateFlag=true;
			}
			else{
				generateFlag=false;
			}
		}
	}

	/*
	 * Usuwam z modelu wszystkie samochody, które dojecha³y na jego koniec 
	 * Dodaje nowe samochody, jeœli jest taka potrzeba
	 */
	public void moveVehicles(int timeInterval) {
		for(Road road: entryRoads){
			road.getFirstWaitingVehicle(); //pobieramy pierwszy czekajacy na wyjazd i go od razu gubimy :)
			road.moveVehiclesOnRoad(timeInterval);	//przesuwam pozostale
		}
		if(generateFlag){
			for(Road road: escapeRoads){
				if(!road.isFull()){
					road.addVehicle(new Vehicle());
				}
			}
		}
	}
	
	
}
