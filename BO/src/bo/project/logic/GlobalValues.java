package bo.project.logic;

public class GlobalValues {
	private static volatile GlobalValues instance = null;
	private int timeInterval = 1;	//czas przejechania 1 samochodu przez skrzy¿owanie
	private int totalTime = 60*60;
	private int totalWaitTime = 0;
	
	private GlobalValues(){
		//empty
	}
	
	public static GlobalValues getInstanceOf(){
		if(instance == null){
			synchronized (GlobalValues.class) {
                if (instance == null) {
                    instance = new GlobalValues();
                }
            }
		}
		return instance;
	}
	
	public int getTimeInterval(){
		return timeInterval;
	}
	
	public int getTotalTime(){
		return totalTime;
	}
	
	public int getTotalWaitTime(){
		return totalWaitTime;
	}
	
	public synchronized void increaseWaitTime(int timeToIncrease){
		totalWaitTime+=timeToIncrease;
	}
	
}
