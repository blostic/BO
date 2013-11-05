package bo.project.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	
	public TrafficEditorFrame()
	{
		junctions = new ArrayList<>();
		roads = new ArrayList<>();
		
		Road sampleRoad = new Road("1",30, 5);
		roads.add(sampleRoad);
		junctions.add(new Intersection(0, roads, new ArrayList<Road>(), 0, 0, 7,20 ));
		junctions.add(new Intersection(1, new ArrayList<Road>(), roads, 0,0, 50 ,40 ));
		
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
		frame.setSize(400, 300);
		drawingArea = new DrawingArea(junctions,roads);
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

	private JPanel createOverallManagementPanel(){
		JPanel panel = new JPanel();
		
		BoxLayout boxLayout = new BoxLayout(panel,  BoxLayout.PAGE_AXIS);
		JButton button = createButton("Zacznij od nowa");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		
		button = createButton("Zapisz");
		button.addActionListener(new SaveModel());
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		
		button  = createButton("Wczytaj");
		button.addActionListener(new LoadModel());
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		
		button = createButton("wyjdz");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel.add(button);
		//boxLayout.setSize(200,200);		
		panel.add(Box.createRigidArea(new Dimension(10, 10)));
		panel.setLayout(boxLayout);
		
		return panel;
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
		menuPane.setSize(250, 300);
		JPanel panel = createOverallManagementPanel();
		c.gridy = 1;
		menuPane.add(panel,c);
		pane.add(menuPane,BorderLayout.EAST);
	}
	
	private JButton createButton(String text){
		JButton button = new JButton(text);
		button.setSize(200, 20);
		return button;
	}
	
}
