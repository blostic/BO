package bo.project.editor.listeners;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bo.project.editor.DrawingArea;
import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Junction;
import bo.project.logic.Road;

public class DrawingAreaMouseListener implements MouseListener,
		MouseMotionListener {

	public enum DrawingAreaListenerCommand {
		NONE, GENERATOR, INTERSECTION, ROAD
	};

	private DrawingArea area;
	// private Point startPoint;
	private Junction startElement;
	public DrawingAreaListenerCommand command;
	private Point lastPoint;

	public DrawingAreaMouseListener(DrawingArea area) {
		this.area = area;
		command = DrawingAreaListenerCommand.NONE;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// startPoint = e.getPoint();
		lastPoint = e.getPoint();
	}

	private void addGeneratorAt(MouseEvent e) {

		Point endPoint = e.getPoint();

		ArrayList<Road> entryRoads = new ArrayList<Road>();
		ArrayList<Road> awayRoads = new ArrayList<Road>();
		Point offset = area.getRealPosition(endPoint);
		Generator generator = new Generator(entryRoads, awayRoads, offset.x,
				offset.y);
		area.getSimulator().getGenerators().add(generator);
	}

	private void addIntersectionAt(MouseEvent e) {
		Point endPoint = e.getPoint();

		ArrayList<Road> entryRoads = new ArrayList<Road>();
		ArrayList<Road> awayRoads = new ArrayList<Road>();
		Point offset = area.getRealPosition(endPoint);
		Intersection intersection = new Intersection(entryRoads, awayRoads, 10,
				20, offset.x, offset.y);
		area.getSimulator().getIntersections().add(intersection);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			area.incActualMode();
			return;
		}
		switch (command) {
		case GENERATOR: {
			addGeneratorAt(e);
			break;
		}
		case INTERSECTION: {
			addIntersectionAt(e);
			break;
		}
		case ROAD: {
			addRoad(e);
			break;
		}
		default:

			break;
		}

		area.repaint(new Rectangle(0, 0, area.getWidth(), area.getHeight()));
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
		ArrayList<Junction> junctions = new ArrayList<Junction>();
		junctions.addAll(area.getSimulator().getGenerators());
		junctions.addAll(area.getSimulator().getIntersections());
		
		if (startElement == null) {
			for (Junction junction : junctions) {
				if (checkIfIntersect(junction,offset)) {
					startElement = junction;
					break;
				}
			}
		} else {
			for (Junction junction : junctions) {
				if (checkIfIntersect(junction,offset)) {
					Junction endElement = junction;
					if (endElement == startElement)
						return;

					int vehicles;
					try{
						vehicles = Integer.parseInt(JOptionPane.showInputDialog(area, "Podaj maksymalną ilosc samochodow"));
					}catch (NumberFormatException ex){
						JOptionPane.showMessageDialog(area, "Nie podano liczby samochodow!");
						return;
					}
					
					int trafficIntensity;
					try{
						trafficIntensity = Integer.parseInt(JOptionPane.showInputDialog(area, "Podaj intensywnosc zatloczenia"));
					}catch (NumberFormatException ex){
						JOptionPane.showMessageDialog(area, "Nie podano intensywnosci zatloczenia");
						return;
					}
					
					Road road = new Road(vehicles, trafficIntensity);
					Road secondRoad = new Road(vehicles, trafficIntensity);
					
					int x_1 = startElement.getXCoordinate();
					int x_2 = endElement.getXCoordinate();
					int y_1 = startElement.getYCoordinate();
					int y_2 = endElement.getXCoordinate();

					int x_delta = 0;
					int y_delta = 2;
					
					if (Math.abs(y_1 - y_2)/Math.abs(x_1 - x_2) > 1 ){
						x_delta = 2;
						y_delta = 0;
					}

					road.setStartCoordinates(startElement.getXCoordinate()-x_delta, startElement.getYCoordinate()-y_delta);
					road.setEndCoordinates(endElement.getXCoordinate()-x_delta, endElement.getYCoordinate()-y_delta);
					
					secondRoad.setEndCoordinates(startElement.getXCoordinate()+x_delta, startElement.getYCoordinate()+y_delta);
					secondRoad.setStartCoordinates(endElement.getXCoordinate()+x_delta, endElement.getYCoordinate()+y_delta);
					
					endElement.getEntryRoads().add(road);
					endElement.getEscapeRoads().add(secondRoad);
		
					startElement.getEscapeRoads().add(road);					
					startElement.getEntryRoads().add(secondRoad);
					
					startElement = null;
					break;
				}
			}
		}
	}

	private Boolean checkIfIntersect(Junction junction, Point offset){
		return junction.getXCoordinate() - 15 <= offset.getX()
				&& junction.getXCoordinate() + 15 >= offset.getX()
				&& junction.getYCoordinate() - 15 <= offset.getY()
				&& junction.getYCoordinate() + 15 >= offset.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		int areaMode = area.getActualMode();
		if (areaMode == 0) {
			Point punkt = arg0.getPoint();
			Point punkt2 = arg0.getPoint();
			punkt.x = -(int) (punkt.getX() - lastPoint.getX());
			punkt.y = -(int) (punkt.getY() - lastPoint.getY());
			area.moveTmpMap(punkt);
			lastPoint = punkt2;
		}

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

}
