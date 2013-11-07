package bo.project.logic;

public interface IJunction {
	public void checkStatus(int currentTime, int timeInterval);
	public void moveVehicles(int timeInterval);
}
