package bo.project.editor;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawingAreaMouseListener implements MouseListener {

	private DrawingArea area;
	private Point startPoint;
	public DrawingAreaMouseListener(DrawingArea area){
		this.area = area;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		area.setBackground(Color.white);
		area.setBackgroundStatic(Color.white);
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point endPoint = e.getPoint();
		endPoint.x = (int) (endPoint.getX() -startPoint.getX());
		endPoint.y = (int) (endPoint.getY() -startPoint.getY());
		
		area.moveMap(endPoint);

	}

}
