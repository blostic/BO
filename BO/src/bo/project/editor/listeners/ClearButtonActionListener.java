package bo.project.editor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import bo.project.editor.TrafficEditorFrame;
import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Road;

public class ClearButtonActionListener implements ActionListener {

	private TrafficEditorFrame frame;
	public ClearButtonActionListener(TrafficEditorFrame frame){
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(frame.getSimulator() == null)
			return;
		
		clearVehicles();
		clearRoads();
		clearJunctions();
		frame.getDrawingArea().getParent().repaint();
	}
	
	private void clearJunctions() {
		frame.getSimulator().setIntersections(new ArrayList<Intersection>());
		frame.getSimulator().setGenerators(new ArrayList<Generator>());
	}

	private void clearRoads() {
		List<Intersection> intersections = frame.getSimulator().getIntersections();
		for(Intersection inter : intersections){
			inter.getEscapeRoads().clear();
			inter.getEntryRoads().clear();
		}
		
		List<Generator> generators = frame.getSimulator().getGenerators();
		for(Generator inter : generators){
			inter.getEscapeRoads().clear();
			inter.getEntryRoads().clear();
		}
	}

	private void clearVehicles(){
		List<Intersection> intersections = frame.getSimulator().getIntersections();
		for(Intersection inter : intersections){
			for(Road r : inter.getEscapeRoads()){
				r.getVehicles().clear();
			}
		}
		
		List<Generator> generators = frame.getSimulator().getGenerators();
		for(Generator inter : generators){
			for(Road r : inter.getEscapeRoads()){
				r.getVehicles().clear();
			}
		}
	}

}
