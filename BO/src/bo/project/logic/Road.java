package bo.project.logic;

import java.util.LinkedList;

public class Road {
	protected String ID;
	protected LinkedList<Vehicle> vehicles;
	protected int maximalNumberOfVehicles;
	protected int minimalWaitTime;
	protected int trafficIntensity; //natê¿enie ruchu - œrednia iloœæ samochodów wje¿d¿aj¹cych na ulicê w godzinê
	protected boolean greenLight;
	final static double averageTime = 1; /*sredni czas przejechania z srednia predkoscia sredniej 
										   dlugosci samochodu :D najs, nie? xD*/
	
	public Road(String ID, int maximalNumberOfVehicles, int trafficIntensity){
		this.ID=ID;
		vehicles = new LinkedList<Vehicle>();
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
	
	public void addVehicle(Vehicle vehicle){
		vehicles.addLast(vehicle);
	}
	
	public void addVehicleFirst(Vehicle vehicle){
		vehicles.addFirst(vehicle);
	}
	
	public boolean isFull(){
		return vehicles.size()>=maximalNumberOfVehicles;
	}
	
	public boolean isEmpty(){
		return vehicles.isEmpty();
	}
	
	public Vehicle getFirstWaitingVehicle(){
		if(!vehicles.isEmpty()){
			if(vehicles.getFirst().getWaitTime()>=minimalWaitTime){
				return vehicles.removeFirst();
			}
		}
		return null;
	}
	
	public int getNumberOfWaiting(){
		int numberOfWaiting = 0;
		for(int i=0;i<vehicles.size();++i){
			if(vehicles.get(i).getWaitTime()>=minimalWaitTime-i*averageTime)
				++numberOfWaiting;
		}
		return numberOfWaiting;
	}
	
	public void moveVehiclesOnRoad(){
		for(Vehicle vehicle: vehicles){
			vehicle.increaseWaitTime(Simulator.getTimeInterval());
		}
	}
	
	public void printState(){
		System.out.print(ID+" has "+vehicles.size()+" ,"+greenLight+"\n");
		for(Vehicle vehicle: vehicles){
			System.out.print(vehicle.getWaitTime()+" ");
		}
		System.out.print("\n");
		System.out.print(getNumberOfWaiting()+"\n");
	}
}
