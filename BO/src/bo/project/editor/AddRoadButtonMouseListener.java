package bo.project.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bo.project.editor.DrawingAreaMouseListener.DrawingAreaListenerCommand;

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
