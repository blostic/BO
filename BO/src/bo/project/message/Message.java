package bo.project.message;

import java.io.Serializable;
import java.util.List;
import bo.project.logic.*;

public class Message implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private int id;
        private MessageType type;
        
        private List<Intersection> intersections;
        private List<Generator> generators;
        private List<Road> roads;
        
        private int simulationTimeInterval;
        private double[] greenLights, redLights;
        
        public void setTrafficData(List<Intersection> intersections, List<Generator> generators, 
        		List<Road> roads, int simulationTimeInterval) {
        	this.intersections = intersections;
        	this.generators = generators;
        	this.roads = roads;
        	this.simulationTimeInterval = simulationTimeInterval;
        }
        
        public void setResult(double[] greenLights, double[] redLights) {
        	this.greenLights = greenLights;
        	this.redLights = redLights;
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
        
        public List<Intersection> getIntersections() {
        	return this.intersections;
        }
        
        public List<Generator> getGenerators() {
        	return this.generators;
        }
        
        public List<Road> getRoads() {
        	return this.roads;
        }
        
        public int getSimulationTimeInterval() {
        	return this.simulationTimeInterval;
        }

  
}