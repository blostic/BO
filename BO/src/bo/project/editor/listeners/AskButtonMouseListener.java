package bo.project.editor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import bo.project.client.ServerProxy;
import bo.project.client.ViewSimulator;
import bo.project.editor.DrawingArea;
import bo.project.message.Message;
import bo.project.message.MessageType;

public class AskButtonMouseListener implements ActionListener {

	private DrawingArea area;
	
	public AskButtonMouseListener(DrawingArea drawingArea) {
		this.area = drawingArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Message msg = new Message(), res;
		msg.setType(MessageType.SOL_ASK);
		
		int id;
		try {
			id = Integer.parseInt(JOptionPane.showInputDialog(area,"Podaj identyfikator:"));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(area,
					"Nieprawidlowy format identyfikatora",
					"Wysylanie...", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		msg.setId(id);
		
		String hostAddress = JOptionPane.showInputDialog(area,"Podaj adres serwera:", ServerProxy.getHost());
		ServerProxy.setHost(hostAddress);
		
		res = ServerProxy.sendMessage(msg);
		if (res != null) {
			if (res.getType() == MessageType.SOL_INCOMPLETE) {
				JOptionPane.showMessageDialog(area,
					"Rozwiazanie nie jest jeszcze gotowe\n"
					+ "lub podano nieprawidlowe ID",
					"Wysylanie...", JOptionPane.INFORMATION_MESSAGE);
			} else if (res.getType() == MessageType.SOL_RESPONSE) {
				ViewSimulator simulator = area.getSimulator();
				simulator.setGenerators(res.getGenerators());
				simulator.setIntersections(res.getIntersections());
				simulator.setLights(res.getGreenLights(), res.getRedLights());
				
				JOptionPane.showMessageDialog(area,
						"Otrzymano rozwiazanie.",
						"Wysylanie...", JOptionPane.INFORMATION_MESSAGE);
				
				area.getParent().repaint();
			}
		} else {
			JOptionPane.showMessageDialog(area, "Wystapil blad komunikacji z serwerem.",
					"Wysylanie...", JOptionPane.ERROR_MESSAGE);
			
			/*
			 * TODO - only for debug, remove!!!
			 */
			
			double[] reds = {10.0,10.0,10.0,10.0,10.0,10.0,10.0},
					leds = {10.0,10.0,10.0,10.0,10.0,10.0,10.0};
			area.getSimulator().setLights(leds, reds);
		}
		
		
	}

}
