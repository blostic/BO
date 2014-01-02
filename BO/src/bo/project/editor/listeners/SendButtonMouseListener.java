package bo.project.editor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import bo.project.client.ServerProxy;
import bo.project.client.ViewSimulator;
import bo.project.editor.DrawingArea;
import bo.project.message.Message;
import bo.project.message.MessageType;

public class SendButtonMouseListener implements ActionListener {

	private DrawingArea area;
	
	public SendButtonMouseListener(DrawingArea drawingArea) {
		this.area = drawingArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Message msg = new Message(), res;
		msg.setType(MessageType.SOL_REQUEST);

		ViewSimulator simulator = area.getSimulator();
		msg.setTrafficData(simulator.getIntersections(), simulator.getGenerators(),1);
		
		String hostAddress = JOptionPane.showInputDialog(area,"Podaj adres serwera:", ServerProxy.getHost());
		ServerProxy.setHost(hostAddress);
		
		res = ServerProxy.sendMessage(msg);
		if (res != null) {
			JOptionPane.showMessageDialog(area,
					"Pomyslnie dodano zadanie do kolejki serwera. ID = " + res.getId(),
					"Wysylanie...", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(area, "Wystapil blad komunikacji z serwerem.",
					"Wysylanie...", JOptionPane.ERROR_MESSAGE);
		}
	}

}
