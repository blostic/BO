package bo.project.editor;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawingAreaMouseListener implements MouseListener {

	private DrawingArea area;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
