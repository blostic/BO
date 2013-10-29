package bo.project.logic;

public interface IJunction {
	public void checkStatus(int currentTime);
	public void moveVehicles();
	public int getPositionX();
	public int getPositionY();
}
