package bo.project.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private MessageType type;
	
	private List<JunctionData> junctions = new ArrayList<JunctionData>();
	private int simulationTimeInterval, simulationTotalTime;
	
	class RoadData {
		public String ID;
		int maxNumberOfVehicles, trafficIntensity;
		public RoadData(String ID, int maxNumberOfVehicles, int trafficIntensity) {
			this.ID = ID; this.maxNumberOfVehicles = maxNumberOfVehicles; this.trafficIntensity = trafficIntensity;
		}
	}
	
	class JunctionData {
		public int ID,x,y;
		public List<RoadData> entryRoads = new ArrayList<RoadData>(), 
						  awayRoads = new ArrayList<RoadData>();
		public boolean isIntersection;
		public JunctionData(int ID, int x, int y, boolean isIntersection) {
			this.ID = ID; this.x = x; this.y = y; this.isIntersection = isIntersection;
		}
	}
	
	public void addIntersection(int ID, int x, int y) {
		junctions.add(new JunctionData(ID,x,y,true));
	}
	
	public void addGenerator(int ID, int x, int y) {
		junctions.add(new JunctionData(ID, x, y, false));
	}
	
	public void addRoadToJunction(int junctionID, String roadID, boolean isEntryRoad, int maxNumberOfVehicles, int trafficIntensity) {
		for (JunctionData junction : junctions) {
			if (junction.ID == junctionID) {
				if (isEntryRoad) {
					junction.entryRoads.add(new RoadData(roadID, maxNumberOfVehicles, trafficIntensity));
				} else {
					junction.awayRoads.add(new RoadData(roadID, maxNumberOfVehicles, trafficIntensity));
				}
			}
		}
	}
	
	public List<JunctionData> getJunctions() {
		return junctions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public int getSimulationTimeInterval() {
		return simulationTimeInterval;
	}

	public void setSimulationTimeInterval(int simulationTimeInterval) {
		this.simulationTimeInterval = simulationTimeInterval;
	}

	public int getSimulationTotalTime() {
		return simulationTotalTime;
	}

	public void setSimulationTotalTime(int simulationTotalTime) {
		this.simulationTotalTime = simulationTotalTime;
	}
	
}
