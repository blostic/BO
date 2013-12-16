package bo.project.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Road;

public class Message implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private int id;
        private MessageType type;
        
        private ArrayList<Intersection> intersections;
        private ArrayList<Generator> generators;
        private ArrayList<Road> roads;
        
        private int simulationTimeInterval;
        private double[] greenLights, redLights;
        
        public void setTrafficData(ArrayList<Intersection> intersections, ArrayList<Generator> generators, 
        		ArrayList<Road> roads, int simulationTimeInterval) {
        	this.intersections = intersections;
        	this.generators = generators;
        	this.roads = roads;
        	this.simulationTimeInterval = simulationTimeInterval;
        }
        
        public void setResult(double[] greenLights, double[] redLights) {
        	this.greenLights = greenLights;
        	this.redLights = redLights;
        }
        
        public double[] getGreenLights() {
        	return this.greenLights;
        }
        
        public double[] getRedLights() {
        	return this.redLights;
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
        
        public ArrayList<Intersection> getIntersections() {
        	return this.intersections;
        }
        
        public ArrayList<Generator> getGenerators() {
        	return this.generators;
        }
        
        public ArrayList<Road> getRoads() {
        	return this.roads;
        }
        
        public int getSimulationTimeInterval() {
        	return this.simulationTimeInterval;
        }

  
}