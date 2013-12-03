package bo.project.editor;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import bo.project.logic.Generator;
import bo.project.logic.Road;

public class DrawingAreaMouseListener implements MouseListener,MouseMotionListener {

	private DrawingArea area;
	private Point startPoint;
	private Point lastPoint;
	private int mode;
	public DrawingAreaMouseListener(DrawingArea area){
		this.area = area;
		mode = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON3){
			mode = (mode + 1) % 3;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();
		mode = 1;
		lastPoint = e.getPoint();
	}

	private void addGeneratorAt(MouseEvent e){
		
		Point endPoint = e.getPoint();
		
		//int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y
		ArrayList<Road> entryRoads = new ArrayList<Road>();
		ArrayList<Road> awayRoads = new ArrayList<Road>();
		Generator generator = new Generator(6,entryRoads, awayRoads,endPoint.x,endPoint.y);
		area.addElement(generator);
		endPoint.x = (int) (endPoint.getX() -startPoint.getX());
		endPoint.y = (int) (endPoint.getY() -startPoint.getY());
	}
	
	private void addIntersectionAt(MouseEvent e){
		Point endPoint = e.getPoint();
		
		//int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y
		ArrayList<Road> entryRoads = new ArrayList<Road>();
		ArrayList<Road> awayRoads = new ArrayList<Road>();
		Generator generator = new Generator(6,entryRoads, awayRoads,endPoint.x,endPoint.y);
		area.addElement(generator);
		endPoint.x = (int) (endPoint.getX() -startPoint.getX());
		endPoint.y = (int) (endPoint.getY() -startPoint.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			return;
		}
		
		int areaMode = area.getActualMode();
		switch(areaMode){
			case 0:
				
				break;
			case 1:
				addGeneratorAt(e);
				break;
			case 2:
				addIntersectionAt(e);
				break;
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
        mode = 0;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
			Point punkt = arg0.getPoint();
			Point punkt2 = arg0.getPoint();
			punkt.x = (int) (punkt.getX() - lastPoint.getX());
			punkt.y = (int) (punkt.getY() - lastPoint.getY());
			area.moveTmpMap(punkt);
			lastPoint = punkt2;
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}
