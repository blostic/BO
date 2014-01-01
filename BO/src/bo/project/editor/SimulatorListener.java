package bo.project.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import bo.project.logic.Simulator;

public class SimulatorListener implements ActionListener {

	private TrafficEditorFrame trafficEditorFrame;
	
	public SimulatorListener(TrafficEditorFrame trafficEditorFrame) {
		this.trafficEditorFrame = trafficEditorFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Simulator simulator = trafficEditorFrame.getSimulator();
		trafficEditorFrame.getSimulator().runSimulation(new double[]{2},new double[]{2});
		this.trafficEditorFrame.getDrawingArea().paintComponents(this.trafficEditorFrame.getDrawingArea().getGraphics());
	}

}
