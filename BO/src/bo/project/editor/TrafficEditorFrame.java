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
	private ArrayList<Junction> closures;
	private ArrayList<Road> roads;
	
	public TrafficEditorFrame()
	{
		closures = new ArrayList<>();
		roads = new ArrayList<>();
		int [] array = new int[10];

		Road firstRoad = new Road("road_x",10,20);
		Road secondRoad = new Road("road_a",10,20);
		Road thirdRoad = new Road("road_c",10,20);
		// (int ID, ArrayList<Road> entryRoads,
		//ArrayList<Road> awayRoads, double greenLightTime, double redLightTime,
		//int xCoordinate, int yCoordinate) 
		
		ArrayList<Road> out3 = new ArrayList();
		ArrayList<Road> in3 = new ArrayList();
		out3.add(thirdRoad);
		in3.add(secondRoad);
		
		ArrayList<Road> out2 = new ArrayList();
		ArrayList<Road> in2 = new ArrayList();
		out2.add(secondRoad);
		in2.add(firstRoad);
		
		ArrayList<Road> out1 = new ArrayList();
		ArrayList<Road> in1 = new ArrayList();
		out1.add(firstRoad);
		in1.add(thirdRoad);
		
		Junction start = new Intersection(2, in2, out2, 1.0f, 2.0f,100,100);
		Junction ends = new Intersection(3, in3, out3, 2.0f, 4.0f, 200,150);
		
		//(int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y){
		Junction generator = new Generator(1, in1, out1,50,100);


		closures.add(start);
		closures.add(ends);
		closures.add(generator);
		roads.add(firstRoad);
		roads.add(secondRoad);
		roads.add(thirdRoad);
		
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
		drawingArea = new DrawingArea(closures,roads,frame.WIDTH,frame.HEIGHT);
		drawingArea.setSize(200, 200);
		pane.add(drawingArea, BorderLayout.WEST);
		
		drawingArea.addMouseListener(new DrawingAreaMouseListener((DrawingArea) drawingArea));
		
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
