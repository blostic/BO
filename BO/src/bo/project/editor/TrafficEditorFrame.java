package bo.project.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TrafficEditorFrame {

	private BorderLayout layout;
	private JPanel pane;
	private JPanel menuPane;
	private Border border;
	private JFrame frame;
	private LayoutManager layout2;
	
	public TrafficEditorFrame()
	{
		this.createView();
	}
	
	@SuppressWarnings("deprecation")
	public void show(){
		if(frame != null)
			frame.show();
	}
	
	private void createView(){
		
		layout = new BorderLayout();
		
		pane = new JPanel(layout);
		border = BorderFactory.createLineBorder(Color.green); 
		pane.setBorder(border);
		
		frame = new JFrame("Badania Operacyjne");
		frame.setSize(500, 700);
		
		createMenu();
		frame.setContentPane(pane);
	}

	private void createMenu(){
		layout2 = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 0;
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		menuPane = new JPanel(layout2);
		menuPane.add(createButton("dodaj skrzyzowanie"),c);
		c.gridy = 1;
		JButton button = createButton("zapisz uklad");
		button.addActionListener(new SaveModel());
		menuPane.add(button,c);
		button = createButton("wczytaj uklad");
		button.addActionListener(new LoadModel());
		c.gridy = 2;
		menuPane.add(button,c);
		menuPane.setSize(250, 600);
		pane.add(menuPane,BorderLayout.EAST);
		
	}
	
	private JButton createButton(String text){
		JButton button = new JButton(text);
		button.setSize(200, 50);
		return button;
	}
	
}
