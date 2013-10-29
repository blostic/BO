package bo.project.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import bo.project.logic.*;

public class TrafficEditorFrame {

	private BorderLayout layout;
	private JPanel pane;
	private JPanel menuPane;
	private Border border;
	private JFrame frame;
	private LayoutManager layout2;
	
	private JPanel drawingArea;
	private List<IJunction> junctions;
	
	public TrafficEditorFrame()
	{
		junctions = new ArrayList<>();
		this.createView();
		
		junctions.add(new Intersection(0, new ArrayList<Road>(), new ArrayList<Road>(), 0, 0, 5,20 ));
		junctions.add(new Intersection(1, new ArrayList<Road>(), new ArrayList<Road>(), 0,0, 10 ,20 ));
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
		frame.setSize(400, 300);
		drawingArea = new DrawingArea(junctions);
		drawingArea.setSize(200, 200);
		pane.add(drawingArea, BorderLayout.WEST);
		
		createMenu();
		frame.setContentPane(pane);
		
		frame.addWindowListener(new WindowAdapter() {

		      @Override
		      public void windowClosing(WindowEvent e) {
		        System.exit(0);
		      }
		    });
	}

	private void createMenu() {
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
		button = createButton("wyjdz");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		c.gridy = 3;
		menuPane.add(button,c);
		menuPane.setSize(250, 300);
		pane.add(menuPane,BorderLayout.EAST);
	}
	
	private JButton createButton(String text){
		JButton button = new JButton(text);
		button.setSize(200, 50);
		return button;
	}
	
}
