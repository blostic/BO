package bo.project.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Intersection extends Closure {

	private ArrayList<Double> escapeRoadProbability;
	/*
	 * Time okresla czas przez ktory drogi 1 i 3 maja kolor zielony (komorka 0 )
	 * drogi 2,4 maja kolor zielony (komorka 1)
	 */

	private int[] time;

	/*
	 * Clock jest odliczany w dół, po przejsiu do zera ustawiany jest na wartosc
	 * time2 lub time2, w zaleznosci od stanu current
	 */

	private int clock;
	private int curent = 0;

	public Intersection(double xCoordinate, double yCoordinate,
			ArrayList<Double> escapeRoadProbability, int[] time) {

		super(xCoordinate, yCoordinate);
		this.escapeRoadProbability = escapeRoadProbability;
		Iterator<Double> it = escapeRoadProbability.iterator();
		this.escapeRoadProbability = new ArrayList<Double>();
		double previos = 0;
		while (it.hasNext()) {
			previos += it.next();
			this.escapeRoadProbability.add(previos);
		}
		this.time = time;
		this.clock = time[0];
	}

	public void initialize(){
		/*
		 * zainicjowanie kolorkow
		 */
		int foo=0;
		for(Road road: this.entranceRoads){
			if(foo%2==0){
				road.setColor(Color.GREEN);				
			}else{
				road.setColor(Color.RED);
			}
			foo++;
		}
	}	
	/*
	 * Funkcja odpowiedzialna za zarządzanie skrzyzowaniem
	 */

	public void manageIntersection() {
		this.findParticipants();
		for (Vehicle vehicle : this.participants) {
			for (Road road : this.entranceRoads) {
				if (road.getColor().equals(Color.GREEN)) {
					this.randomTurn(vehicle);
				}
			}
		}
		this.clock--;
		if (clock == 0) {
			this.curent = (this.curent++) % 2;
			this.clock = time[this.curent];
			this.changeTrafficLights();
		}
	}

	private void changeTrafficLights() {
		for (Road road : this.entranceRoads) {
			road.changeColor();
		}
	}

	/*
	 * randomTurn ma prawidlowy rozklad prawdopodobienstwa dzięki zastosowanie
	 * dystrybuanty
	 */

	public void randomTurn(Vehicle vehicle) {
		Double rand = new Random().nextDouble();
		int who = 0;
		for (double d : this.escapeRoadProbability) {
			System.out.println("d = " + d + " rand = " + rand);
			if (d > rand) {
				System.out.println(who);
				break;
			}
			who++;
		}
		Closure closure = this.escapeRoads.get(who).getEnds();

		if (closure.equals(this)) {
			vehicle.setdestination(this.escapeRoads.get(who).getEnds(),
					this.escapeRoads.get(who));
		} else {
			vehicle.setdestination(this.escapeRoads.get(who).getStart(),
					this.escapeRoads.get(who));
		}
	}
}
