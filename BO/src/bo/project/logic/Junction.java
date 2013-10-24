package bo.project.logic;

import java.util.ArrayList;

public abstract class Junction implements IJunction {
	protected ArrayList<Road> entryRoads;	//ulice entry i away pod tymi samymi indeksami sa takie same
	protected ArrayList<Road> awayRoads;	//ulice z rowna reszta dzielenia przez 2 sa ROWNOLEGLE
	protected int x;	//wspolrzedne
	protected int y;
	
	public Junction(ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y){
		this.entryRoads=entryRoads;
		this.awayRoads=awayRoads;
		this.x=x;
		this.y=y;
	}
	
	
	
}
