package bo.project.logic;

import java.util.ArrayList;

public abstract class Junction implements IJunction {
	protected ArrayList<Street> entryRoads;	//ulice entry i away pod tymi samymi indeksami sa takie same
	protected ArrayList<Street> awayRoads;	//ulice z rowna reszta dzielenia przez 2 sa ROWNOLEGLE
	protected ArrayList<Integer> traffic;	//natê¿enie ruchu - œrednia iloœæ samochodów wje¿d¿aj¹cych na ulicê w godzinê
	protected int greenLightTime;	//czas w sekundach œwiecenia zielonego œwiat³a dla ulicy pod indeksem 0
	protected int redLightTime;	//jw
	protected int x;	//wspolrzedne
	protected int y;
	
	public Junction(ArrayList<Street> entryRoads, ArrayList<Street> awayRoads, 
			ArrayList<Integer> traffic, int greenLightTime, int redLightTime, int x, int y){
		this.entryRoads=entryRoads;
		this.awayRoads=awayRoads;
		this.traffic=traffic;
		this.greenLightTime=greenLightTime;
		this.redLightTime=redLightTime;
		this.x=x;
		this.y=y;
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
	
}
