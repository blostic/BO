package bo.project.editor;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Junction;
import bo.project.logic.Road;

public class DrawingAreaMouseListener implements MouseListener,MouseMotionListener {

	public enum DrawingAreaListenerCommand {NONE, GENERATOR, INTERSECTION, ROAD};
	
	private DrawingArea area;
//	private Point startPoint;
	private Junction startElement;
	public DrawingAreaListenerCommand command;
	private Point lastPoint;
	public DrawingAreaMouseListener(DrawingArea area){
		this.area = area;
		command = DrawingAreaListenerCommand.NONE;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		area.setBackground(Color.white);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {
	//	startPoint = e.getPoint();
		lastPoint = e.getPoint();
	}

	private void addGeneratorAt(MouseEvent e){
		
		Point endPoint = e.getPoint();
		
		//int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y
		ArrayList<Road> entryRoads = new ArrayList<Road>();
		ArrayList<Road> awayRoads = new ArrayList<Road>();
		Point offset = area.getRealPosition(endPoint);
		Generator generator = new Generator(6,entryRoads, awayRoads,offset.x,offset.y);
		area.addElement(generator);
	}
	
	private void addIntersectionAt(MouseEvent e){
		Point endPoint = e.getPoint();
		
		//int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y
		ArrayList<Road> entryRoads = new ArrayList<Road>();
		ArrayList<Road> awayRoads = new ArrayList<Road>();
		Point offset = area.getRealPosition(endPoint);
		Intersection generator = new Intersection(7,entryRoads, awayRoads,10,20,offset.x,offset.y);
		area.addElement(generator);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			area.incActualMode();
			return;
		}


		switch(command){
			
			case GENERATOR:{
				addGeneratorAt(e);
				break;
			}
			case INTERSECTION:{
				addIntersectionAt(e);
				break;
			}
			case ROAD:{
				addRoad(e);
				break;
			}
			default:
				
				break;
		}
		
		
		
		area.repaint(new Rectangle(0,0,area.getWidth(), area.getHeight()));
		area.invalidate();
        area.revalidate();
        area.repaint();
       
        Container parent = area.getParent();
        parent.invalidate();
        parent.revalidate();
        parent.repaint();
	}

	private void addRoad(MouseEvent e) {
		Point offset = area.getRealPosition(e.getPoint());
		if(startElement == null)
		{
			List<Junction> junctions = area.getJunctions();
			for(Junction junction : junctions){
				if(junction.getXCoordinate() -3 <= offset.getX() && junction.getXCoordinate() + 3 >= offset.getX()
						&& junction.getYCoordinate() -3 <= offset.getY() && junction.getYCoordinate() + 3 >= offset.getY()){
					startElement = junction;
					break;
				}
			}
			return;
		}
		else{
			List<Junction> junctions = area.getJunctions();
			for(Junction junction : junctions){
				if(junction.getXCoordinate() -3 <= offset.getX() && junction.getXCoordinate() + 3 >= offset.getX()
						&& junction.getYCoordinate() -3 <= offset.getY() && junction.getYCoordinate() + 3 >= offset.getY()){
					Junction endElement = junction;
					if(endElement == startElement)
						return;
					
					Road road = new Road("road_int",10,10);
					List<Road> roads = endElement.getEntryRoads();
					roads.add(road);
					roads = startElement.getEscapeRoads();
					roads.add(road);
					startElement = null;
					break;
				}
			}
			return;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		int areaMode = area.getActualMode();
		if(areaMode == 0){
			Point punkt = arg0.getPoint();
			Point punkt2 = arg0.getPoint();
			punkt.x = (int) (punkt.getX() - lastPoint.getX());
			punkt.y = (int) (punkt.getY() - lastPoint.getY());
			area.moveTmpMap(punkt);
			lastPoint = punkt2;
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
