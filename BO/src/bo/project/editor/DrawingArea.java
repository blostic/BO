package bo.project.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import bo.project.logic.Closure;
import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Road;

public class DrawingArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Closure> closures;
	private List<Road> roads;
	private double offsetX;
	private double offsetY;
	
	public DrawingArea(List<Closure> junctions, List<Road> roads, int parentWidth, int parentHeigth){
		this.setSize(parentWidth-200, parentHeigth);
		this.closures = junctions;
		this.roads = roads;
		offsetX = 0;
		offsetY = 0;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		 g.setClip(0, 0, 400, 400);
	     super.paintComponent(g);    
		 
		 for(Closure closure : closures){
			 double x = closure.getxCoordinate();
			 double y = closure.getyCoordinate();
			 List<Road> escapeRoads = closure.getEscapeRoads();
			 g.setColor(Color.yellow);
			 for(Road road : escapeRoads){
				 Closure destination = road.getEnds();
				 if(destination == null)
					 continue;
				 double dstX = destination.getxCoordinate();
				 double dstY = destination.getyCoordinate();
				 g.drawLine((int)(x-offsetX), (int)(y-offsetY), (int)(dstX-offsetX), (int)(dstY-offsetY));
			 }
			 if(closure.getClass().equals(Generator.class)){
				 g.setColor(Color.red);
			 }
			 else if(closure.getClass().equals(Intersection.class)){
				 g.setColor(Color.black);
			 }
			 g.drawRect((int)(x-1 - offsetX), (int)(y-1 - offsetY), 3, 3);
		 }
		 
		 if(backGroundColor!=null)
			 g.drawRect(0, 0, 200, 100);
   }

	private Color backGroundColor;
	public void setBackgroundStatic(Color color) {
		// TODO Auto-generated method stub
		this.backGroundColor = color;
	}
}
