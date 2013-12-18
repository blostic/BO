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
				OutputStream file = new FileOutputStream(selectedFile);
				ObjectOutput output = new ObjectOutputStream(file);
				output.writeObject(frame.getSimulator());
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
