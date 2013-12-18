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

public class SaveModel implements ActionListener {
	private TrafficEditorFrame frame;
	public SaveModel(TrafficEditorFrame frame) {
		this.frame = frame;
	}
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
				input.close();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

}
