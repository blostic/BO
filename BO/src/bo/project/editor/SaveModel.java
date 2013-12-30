package bo.project.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;

/**
 * @author piotr
 * 
 */

public class SaveModel implements ActionListener {
	private TrafficEditorFrame trafficEditorFrame;

	public SaveModel(TrafficEditorFrame trafficEditorFrame) {
		this.trafficEditorFrame = trafficEditorFrame;
	}

	/*
	 * Method is used to save a state of intersections into a file. It saves an
	 * object of Simulator, which contains all valuable information.
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File selectedFile = fc.getSelectedFile();
				OutputStream file = new FileOutputStream(selectedFile);
				ObjectOutput output = new ObjectOutputStream(file);
				output.writeObject(trafficEditorFrame.getSimulator());
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
