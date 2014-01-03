package bo.project.editor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bo.project.editor.listeners.DrawingAreaMouseListener.DrawingAreaListenerCommand;

public class AddGeneratorButtonMouseListener implements ActionListener {

	private DrawingAreaMouseListener mouseListener;
	

	public AddGeneratorButtonMouseListener(
			DrawingAreaMouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(mouseListener == null)
			return;
		
		if (mouseListener.command != DrawingAreaListenerCommand.GENERATOR)
			mouseListener.command = DrawingAreaListenerCommand.GENERATOR;
		else
			mouseListener.command = DrawingAreaListenerCommand.NONE;
	}

}
