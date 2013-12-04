package bo.project.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Junction;
import bo.project.logic.Road;

public class DrawingArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	private List<Junction> closures;
	private List<Road> roads;
=======
	private List<Junction> junctions;
>>>>>>> cc790295e052d1561c5c3dfb363a09ac70597035
	private double offsetX;
	private double offsetY;
	// jakies tryby nie wiem co - mozna to jeszcze dalej wystawic:)
	private int mode = 0;
	
	public void incActualMode(){
		mode = (mode + 1) % 3;
	}
	
	public int getActualMode(){
		return mode;
	}
	
	public void moveTmpMap(Point offset){
		offsetX += offset.getX();
		offsetY += offset.getY();
	}
	
	public DrawingArea(List<Junction> junctions, List<Road> roads, int parentWidth, int parentHeigth){
		this.setSize(parentWidth-200, parentHeigth);
<<<<<<< HEAD
		this.closures = junctions;
		this.roads = roads;
=======
		this.junctions = junctions;
>>>>>>> cc790295e052d1561c5c3dfb363a09ac70597035
		offsetX = 0;
		offsetY = 0;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		 g.setClip(0, 0, 400, 400);
		 super.paintComponent(g);
	     
		 for(Junction closure : junctions){
			 double x = closure.getXCoordinate();
			 double y = closure.getYCoordinate();
			 List<Road> escapeRoads = closure.getEscapeRoads();

			 if(closure.getClass().equals(Generator.class)){
				 g.setColor(Color.red);
			 }
			 else if(closure.getClass().equals(Intersection.class)){
				 g.setColor(Color.black);
			 }
			 g.drawRect((int)(x-1 - offsetX), (int)(y-1 - offsetY), 3, 3);
			 
			 g.setColor(Color.yellow);
			 for(Road road : escapeRoads){
				 Junction destination = null;
				 
				 for(Junction end : junctions){
					 List<Road> entryRoads = end.getEntryRoads();
					 if(entryRoads.contains(road)){
						 destination = end;
						 break;
					 }
				 }

				 if(destination == null)
					 continue;
				 double dstX = destination.getXCoordinate();
				 double dstY = destination.getYCoordinate();
				 g.drawLine((int)(x-offsetX), (int)(y-offsetY), (int)(dstX-offsetX), (int)(dstY-offsetY));
			 }
		 }
   }

	public void addElement(Junction junction) {
		junctions.add(junction);
		 
	}
}
