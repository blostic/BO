package bo.project.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Road;
import bo.project.logic.Simulator;
import bo.project.logic.Vehicle;

@SuppressWarnings("serial")
public class DrawingArea extends JPanel {

	/**
	 * 
	 */

	private Simulator simulator;
	private List<Road> _roadsToDraw;
	private List<Vehicle> _vehicles;

	public Simulator getSimulator() {
		return simulator;
	}

	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
		
		this.repaint();
	}

	private double offsetX;
	private double offsetY;
	// jakies tryby nie wiem co - mozna to jeszcze dalej wystawic:)
	private int mode = 0;

	public void incActualMode() {
		mode = (mode + 1) % 3;
	}

	public int getActualMode() {
		return mode;
	}

	public void moveTmpMap(Point offset) {
		offsetX += offset.getX();
		offsetY += offset.getY();
	}

	public DrawingArea(Simulator simulator, int parentWidth, int parentHeigth) {
		this.setSize(parentWidth - 200, parentHeigth);
		this.simulator = simulator;
		offsetX = 0;
		offsetY = 0;
	}

	private void paintGenerators(List<Generator> generators, Graphics g){
		if(generators == null)
			return;
		
		g.setColor(Color.red);
		for(Generator generator : generators){
			double x = generator.getXCoordinate();
			double y = generator.getYCoordinate();
			
			g.drawRect((int) (x - 1 - offsetX), (int) (y - 1 - offsetY), 3, 3);

		}
	}
	
	private void paintIntersections(List<Intersection> intersections, Graphics g){
		if(intersections == null)
			return;
		
		g.setColor(Color.black);
		for(Intersection intersection : intersections){
			double x = intersection.getXCoordinate();
			double y = intersection.getYCoordinate();
			
			g.drawRect((int) (x - 1 - offsetX), (int) (y - 1 - offsetY), 3, 3);
			_roadsToDraw.addAll(intersection.getEntryRoads());
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		g.setClip(0, 0, 1700, 1000);
		super.paintComponent(g);
		if(simulator == null)
			return;
		
		prepareToPaint();
		paintGenerators(simulator.getGenerators(),g);
		paintIntersections(simulator.getIntersections(),g);
		paintRoads(g);
		paintVehicles(g);
	}

	private void paintVehicles(Graphics g) {
		for (Vehicle veh : _vehicles) {
			g.drawRect((int) (veh.getX() - offsetX),
					(int) (veh.getY() - offsetY), 3, 3);
		}
	}

	private void paintRoads(Graphics g) {
		g.setColor(Color.yellow);
		for(Road road : _roadsToDraw){
			int startX = road.getStartXCoordinate();
			int startY = road.getStartYCoordinate();
			int endX = road.getEndXCoordinate();
			int endY = road.getEndYCoordinate();
			
			g.drawLine((int) (startX - offsetX), (int) (startY - offsetY),
					(int) (endX - offsetX), (int) (endY - offsetY));
			_vehicles.addAll(road.getVehicles());
		}
		
	}

	private void prepareToPaint() {
		if(_roadsToDraw == null)
			_roadsToDraw = new ArrayList<Road>();
		else
			_roadsToDraw.clear();
		if(_vehicles == null)
			_vehicles = new ArrayList<Vehicle>();
		else
			_vehicles.clear();
	}

	public Point getRealPosition(Point endPoint) {
		endPoint.x = (int) (endPoint.x + offsetX);
		endPoint.y = (int) (endPoint.y + offsetY);

		return endPoint;
	}
}
