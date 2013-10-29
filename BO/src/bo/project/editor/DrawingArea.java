package bo.project.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import bo.project.logic.IJunction;

public class DrawingArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IJunction> junctions;
	
	public DrawingArea(List<IJunction> junctions){
		this.setSize(300, 300);
		this.junctions = junctions;
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE,2,true));
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		 g.setClip(0, 0, 100, 400);
	     //super.paintComponent(g);    
		 
		 g.setColor(Color.red);
		 for(IJunction junction : junctions){
			 int x = junction.getPositionX();
			 int y = junction.getPositionY();
			 g.drawRect(x-1, y-1, 3, 3);
		 }
   }
}
