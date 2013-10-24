package bo.project.logic;

import java.util.ArrayList;
import java.util.Random;

public class Generator extends Junction{
	private boolean generateFlag;
	Random random;
	
	public Generator(ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y){
		super(entryRoads,awayRoads, x, y);
		generateFlag=false;
		random = new Random();
	}
	
	//dziwny warunek w ifie: timeInterval - czas przejazdu jednego samochodu
	//						trafficIntensity/3600 - ilosc samochodow na sekunde
	//wiec powinno to nam dac ilosc samochodow w czasie przejazdu 1 samochodu, max 1 :)
	public void checkStatus(int currentTime){
		for(Road road: entryRoads){
			if(random.nextDouble()<=(road.getTrafficIntensity()*Simulator.getTimeInterval())/(3600)){
				generateFlag=true;
			}
			else{
				generateFlag=false;
			}
		}
	}


	public void moveVehicles() {
		for(Road road: awayRoads){
			road.getFirstWaitingVehicle(); //pobieramy pierwszy czekajacy na wyjazd i go od razu gubimy :)
		}
		if(generateFlag){
			for(Road road: entryRoads){
				road.addVehicle(new Vehicle());
			}
		}
	}
	
	
}
