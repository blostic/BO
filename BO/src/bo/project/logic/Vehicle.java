package bo.project.logic;

public class Vehicle {
	private double waitingTime = 0;
	/**
	 * destination definiuje kierunek, w którym porusza się pojazd. Jest
	 * określony przez domkniecie w kierunku, którego porusza się samochód
	 * (nadawany jest pojazdowi na skrzyżowaniu)
	 */
	private Closure destination;
	/**
	 * Entry jest domknieciem, z którego startuje pojazd. Obecność pól
	 * destination i entry pozwala na ustalenie kierunku poruszania się pojazdu
	 */
	private Closure entry;

	private double xCoordinate;
	private double yCoordinate;

	private double xVelocity;
	private double yVelocity;
	/**
	 * Czas, który samochód spędził na aktualnej drodze. Pozwala nam to obliczyć
	 * jego współrzędne, potrzebne do narysowania
	 */
	private double roadTime;

	public Vehicle(Closure destination, Closure entry, Road road) {
		super();
		this.destination = destination;
		this.entry = entry;
		updateCoordinates();
	}

	public void updateRoad(Road road){
		double speed = road.getPermittedSpeed();
		double deltaX = destination.getxCoordinate() - entry.getxCoordinate();
		double deltaY = destination.getyCoordinate() - entry.getyCoordinate();
		this.xVelocity = deltaX / deltaY * speed;
		this.yVelocity = deltaY / deltaX * speed;
	}
	
	public void updateCoordinates() {
		this.xCoordinate = xVelocity * roadTime;
		this.yCoordinate = yVelocity * roadTime;
	}

	public double getWaitingTime() {
		return waitingTime;
	}

	public void incWaitingTime(double interval) {
		this.waitingTime += interval;
	}

	public Closure getdestination() {
		return destination;
	}

	public void setdestination(Closure destination,Road road) {
		this.entry = this.destination;
		this.destination = destination;
		
	}

	public double getxCoordinate() {
		return xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}
}
