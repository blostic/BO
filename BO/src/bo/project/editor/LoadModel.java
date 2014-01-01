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
import javax.swing.JOptionPane;

import bo.project.logic.Simulator;

public class LoadModel implements ActionListener {

	TrafficEditorFrame trafficEditorFrame;

	public LoadModel(TrafficEditorFrame trafficEditorFrame) {
		this.trafficEditorFrame = trafficEditorFrame;
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
				Simulator simulator = (Simulator) input.readObject();
				this.trafficEditorFrame.setSimulator(simulator);
				JOptionPane.showMessageDialog(trafficEditorFrame.drawingArea,
						"Konfiguracja została pomyślnie wczytana",
						"Wczytywanie...", JOptionPane.PLAIN_MESSAGE);
				input.close();
				this.trafficEditorFrame.drawingArea.getParent().repaint();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
}
