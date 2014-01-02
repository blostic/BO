package bo.project.editor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bo.project.editor.TrafficEditorFrame;

public class StopButtonMouseListener implements ActionListener {

	private TrafficEditorFrame editorFrame;
	
	public StopButtonMouseListener(TrafficEditorFrame trafficEditorFrame) {
		this.editorFrame = trafficEditorFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		editorFrame.getDrawingArea().getSimulator().stopSimulation();
	}

}
