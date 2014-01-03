package bo.project.editor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bo.project.editor.listeners.DrawingAreaMouseListener.DrawingAreaListenerCommand;

public class AddRoadButtonMouseListener implements ActionListener {

	private DrawingAreaMouseListener mouseListener;
	
	public AddRoadButtonMouseListener(DrawingAreaMouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(mouseListener == null)
			return;
		if (mouseListener.command != DrawingAreaListenerCommand.ROAD)
			mouseListener.command = DrawingAreaListenerCommand.ROAD;
		else
			mouseListener.command = DrawingAreaListenerCommand.NONE;
	}

}
