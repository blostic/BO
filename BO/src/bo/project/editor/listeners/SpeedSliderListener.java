package bo.project.editor.listeners;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bo.project.client.SimulationDrawer;

public class SpeedSliderListener implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			SimulationDrawer.setSimulationSpeed((int)source.getValue());
		}
		
		
	}


}
