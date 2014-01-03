package bo.project.editor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bo.project.editor.listeners.DrawingAreaMouseListener.DrawingAreaListenerCommand;

public class AddJunctionButtonMouseListener implements ActionListener {

	private DrawingAreaMouseListener mouseListener;
	
	public AddJunctionButtonMouseListener(DrawingAreaMouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(mouseListener == null)
			return;
		if (mouseListener.command != DrawingAreaListenerCommand.INTERSECTION)
			mouseListener.command = DrawingAreaListenerCommand.INTERSECTION;
		else
			mouseListener.command = DrawingAreaListenerCommand.NONE;
	}

}
