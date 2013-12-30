package bo.project.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;

import bo.project.logic.Simulator;

public class LoadModel implements ActionListener {

	TrafficEditorFrame frame;

	public LoadModel(TrafficEditorFrame frame) {
		this.frame = frame;
	}

	/*
	 * Method is used to load a state of intersections from a file. It loads an
	 * object of Simulator, which contains all valuable information.
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File selectedFile = fc.getSelectedFile();
				InputStream file = new FileInputStream(selectedFile);
				ObjectInput input = new ObjectInputStream(file);
				this.frame.setSimulator((Simulator) input.readObject());
				System.out.println("wczytalem "+this.frame.getSimulator());
				input.close();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
}
