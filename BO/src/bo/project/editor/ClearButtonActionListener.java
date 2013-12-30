package bo.project.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Road;
import bo.project.logic.Simulator;

public class ClearButtonActionListener implements ActionListener {

	private Simulator _simulator;
	private DrawingArea _area;
	
	public ClearButtonActionListener(Simulator simulator, DrawingArea area){
		this._simulator = simulator;
		this._area = area;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(_simulator == null)
			return;
		
		clearVehicles();
		clearRoads();
		clearJunctions();
		_area.getParent().repaint();
	}
	
	private void clearJunctions() {
		_simulator.setIntersections(new ArrayList<Intersection>());
		_simulator.setGenerators(new ArrayList<Generator>());
	}

	private void clearRoads() {
		List<Intersection> intersections = _simulator.getIntersections();
		for(Intersection inter : intersections){
			inter.getEscapeRoads().clear();
			inter.getEntryRoads().clear();
		}
		
		List<Generator> generators = _simulator.getGenerators();
		for(Generator inter : generators){
			inter.getEscapeRoads().clear();
			inter.getEntryRoads().clear();
		}
	}

	private void clearVehicles(){
		List<Intersection> intersections = _simulator.getIntersections();
		for(Intersection inter : intersections){
			for(Road r : inter.getEscapeRoads()){
				r.getVehicles().clear();
			}
		}
		
		List<Generator> generators = _simulator.getGenerators();
		for(Generator inter : generators){
			for(Road r : inter.getEscapeRoads()){
				r.getVehicles().clear();
			}
		}
	}

}
