package bo.project.logic;
import java.util.LinkedList;

public class Road {
	private String ID;
	private LinkedList<Vehicle> vehicles;
	private int maximalNumberOfVehicles;
	private int minimalWaitTime;
	private int trafficIntensity; //natê¿enie ruchu - œrednia iloœæ samochodów wje¿d¿aj¹cych na ulicê w godzinê
	private boolean greenLight;
	private double averageTime = 1; /*sredni czas przejechania z srednia predkoscia sredniej 
										   dlugosci samochodu*/
	
	
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
	
	public double getAverageTime(){
		return averageTime;
	}
	
	public void addVehicle(Vehicle vehicle){
		vehicles.addLast(vehicle);
	}
	
	/*
	 * uzywane tylko przy losowaniu na poczatku samochodow
	 */
	public void addVehicleFirst(Vehicle vehicle){
		vehicles.addFirst(vehicle);
	}
	
	public boolean isFull(){
		return vehicles.size()>=maximalNumberOfVehicles;
	}
	
	public boolean isEmpty(){
		return vehicles.isEmpty();
	}
	
	/*
	 * zwraca pierwszy samochód który czeka na skrzy¿owaniu
	 * jeœli takiego nie ma - zwraca null
	 */
	public Vehicle getFirstWaitingVehicle(){
		if(!vehicles.isEmpty()){
			if(vehicles.getFirst().isWaiting()){
				return vehicles.removeFirst();
			}
		}
		return null;
	}
	
	public int getNumberOfWaiting(){
		int numberOfWaiting = 0;
		for(Vehicle vehicle: vehicles){
			if(vehicle.isWaiting())
				++numberOfWaiting;
		}
		return numberOfWaiting;
	}
	
	/*
	 * przesuwa samochody na ulicy (zwiêksza czas)
	 * sprawdza czy samochód stoi w kolejce przed skrzy¿owaniem, jeœli tak - oznacza go
	 */
	public void moveVehiclesOnRoad(int timeInterval){
		Vehicle vehicle;
		for(int i=0;i<vehicles.size();++i){
			vehicle=vehicles.get(i);
			vehicle.increaseWaitTime(timeInterval);
			if(vehicle.getWaitTime()>=minimalWaitTime-i*averageTime){
				vehicle.markAsWaiting();
			}
		}
	}
	
	public void printState(){
		System.out.print(ID+" has "+vehicles.size()+" vehicles: ");
		for(Vehicle vehicle: vehicles){
			System.out.print(vehicle.getWaitTime()+" "+vehicle.isWaiting()+"| ");
		}
		System.out.println();
	}
}
