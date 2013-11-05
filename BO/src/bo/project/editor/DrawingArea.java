package bo.project.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import bo.project.logic.IJunction;
import bo.project.logic.Junction;
import bo.project.logic.Road;

public class DrawingArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Junction> junctions;
	private List<Road> roads;
	
	public DrawingArea(List<Junction> junctions, List<Road> roads){
		this.setSize(300, 300);
		this.junctions = junctions;
		this.roads = roads;
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE,2,true));
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		 g.setClip(0, 0, 100, 400);
	     //super.paintComponent(g);    
		 
		 for(Junction junction : junctions){
			 int x = junction.getPositionX();
			 int y = junction.getPositionY();
			List<Road> escapeRoads = junction.getEscapeRoads();
			 g.setColor(Color.yellow);
			 for(Road road : escapeRoads){
				 Junction dstJunction = getDestinationJuniction(road);
				 if(dstJunction == null)
					 continue;
				 int dstX = dstJunction.getPositionX();
				 int dstY = dstJunction.getPositionY();
				 g.drawLine(x, y, dstX, dstY);
			 }
			 g.setColor(Color.red);
			 g.drawRect(x-1, y-1, 3, 3);
		 }
   }
	
	private Junction getDestinationJuniction(Road road){
		for(Junction junction : junctions){
			List<Road> enterRoads = junction.getEnterRoads();
			for(Road tmpRoad : enterRoads){
				if(tmpRoad.equals(road)){
					return junction;
				}
			}
		}
		return null;
	}
}
