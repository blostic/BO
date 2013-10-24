package bo.project.logic;

import java.util.ArrayDeque;

public class Road {
	protected ArrayDeque<Vehicle> Vehicles;
	protected int maximalNumberOfVehicles;
	protected int minimalWaitTime;
	protected int trafficIntensity; //natê¿enie ruchu - œrednia iloœæ samochodów wje¿d¿aj¹cych na ulicê w godzinê
	protected boolean greenLight;
	final static double averageTime = 0.5; /*sredni czas przejechania z srednia predkoscia sredniej 
										   dlugosci samochodu :D najs, nie? xD*/
	
	public Road(int maximalNumberOfVehicles, int trafficIntensity){
		Vehicles = new ArrayDeque<Vehicle>(maximalNumberOfVehicles);
		this.maximalNumberOfVehicles=maximalNumberOfVehicles;
		minimalWaitTime=(int)(maximalNumberOfVehicles*averageTime);
		this.trafficIntensity=trafficIntensity;
		greenLight=false;
	}

	public boolean checkGreenLight(){
		return greenLight;
	}
	
	public void setGreenLight(){
		greenLight=true;
	}
	
	public void setRedLight(){
		greenLight=false;
	}
	
	public int getTrafficIntensity(){
		return trafficIntensity;
	}
	
	public int getMaximalNumberOfVehicles(){
		return maximalNumberOfVehicles;
	}
	
	public int getMinimalWaitTime(){
		return minimalWaitTime;
	}
	
	public static double getAverageTime(){
		return averageTime;
	}
	
	//probujemy dodac pojazd, zwracamy czy sie udalo
	public boolean addVehicle(Vehicle vehicle){
		if(Vehicles.size()<maximalNumberOfVehicles){
			vehicle.resetWaitTime();
			Vehicles.addLast(vehicle);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isFull(){
		return Vehicles.size()<maximalNumberOfVehicles;
	}
	
	public Vehicle getFirstWaitingVehicle(){
		if(!Vehicles.isEmpty()){
			if(Vehicles.getFirst().getWaitTime()<minimalWaitTime){
				return Vehicles.removeFirst();
			}
		}
		return null;
	}
	
	public int getNumberOfWaiting(){
		int numberOfWaiting = 0;
		for(Vehicle el: Vehicles){
			if(el.getWaitTime()<minimalWaitTime)
				++numberOfWaiting;
		}
		return numberOfWaiting;
	}
}
