package bo.project.logic;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class Road implements Serializable{
	/**
	 * 
	 */
	private int maximalNumberOfVehicles;
	private LinkedList<Vehicle> vehicles;
	private int minimalWaitTime;
	private int trafficIntensity; //natzenie ruchu - srednia ilosc samochodow wjezdzajacych na ulice w godzine
	private boolean greenLight;
	private double averageTime = 10; /*sredni czas przejechania z srednia predkoscia sredniej 
										   dlugosci samochodu*/
	private int startX, startY;
	private int endX, endY;
	
	public int getStartXCoordinate(){
		return startX;
	}
	public int getStartYCoordinate(){
		return startY;
	}
	public int getEndXCoordinate(){
		return endX;
	}
	public int getEndYCoordinate(){
		return endY;
	}
	
	public Road(int maximalNumberOfVehicles, int trafficIntensity){
		vehicles = new LinkedList<Vehicle>();
		this.maximalNumberOfVehicles=maximalNumberOfVehicles;
		minimalWaitTime=(int)(maximalNumberOfVehicles*averageTime);
		this.trafficIntensity=trafficIntensity;
		greenLight=false;
	}

	public void setStartCoordinates(int x, int y) {
		this.startX = x;
		this.startY = y;
	}
	
	public void setEndCoordinates(int x, int y) {
		this.endX = x;
		this.endY = y;
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
	 * zwraca pierwszy samochod ktory czeka na skrzyzowaniu
	 * jesli takiego nie ma - zwraca null
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
	 * przesuwa samochody na ulicy (zwieksza czas)
	 * sprawdza czy samochod stoi w kolejce przed skrzyzowaniem, jesli tak - oznacza go
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
	
	public void updateVehiclesPositions() {
		Vehicle v;
		for (int index=0; index<vehicles.size(); ++index) {
			v = vehicles.get(index);
			if (v.isWaiting()) {
				v.setX(Math.round(endX - index*(endX-startX)/(float)maximalNumberOfVehicles));
				v.setY(Math.round(endY - index*(endY-startY)/(float)maximalNumberOfVehicles));
			} else {
				v.setX(Math.round(startX + (endX - startX)*(float)v.getWaitTime()/minimalWaitTime));
				v.setY(Math.round(startY + (endY - startY)*(float)v.getWaitTime()/minimalWaitTime));
			}
		}
	}
	
	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}
	
	public void printState(){
		for(Vehicle vehicle: vehicles){
			System.out.print(vehicle.getWaitTime()+" "+vehicle.isWaiting()+"| ");
		}
		System.out.println();
	}
}
