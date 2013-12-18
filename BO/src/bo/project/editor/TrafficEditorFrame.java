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
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import bo.project.editor.DrawingAreaMouseListener.DrawingAreaListenerCommand;
import bo.project.logic.*;

public class TrafficEditorFrame {

	private BorderLayout layout;
	private JPanel pane;
	private JPanel menuPane;
	private Border border;
	private JFrame frame;
	private LayoutManager layout2;
	
	private JPanel drawingArea;
	private DrawingAreaMouseListener mouseListener;
	private ArrayList<Junction> closures;
	private ArrayList<Road> roads;
	private ArrayList<Intersection> intersections;
	private ArrayList<Generator> generators;
	//private Simulator simulator;
	
	public TrafficEditorFrame()
	{
		closures = new ArrayList<>();
		roads = new ArrayList<>();
		Road firstRoad = new Road("road_x",10,20);
		Road secondRoad = new Road("road_a",10,20);
		Road thirdRoad = new Road("road_c",10,20);
		Vehicle auto = new Vehicle();
		auto.setX(50);
		auto.setY(100);
		firstRoad.addVehicle(auto);
		
		ArrayList<Road> out3 = new ArrayList<Road>();
		ArrayList<Road> in3 = new ArrayList<Road>();
		out3.add(thirdRoad);
		in3.add(secondRoad);
		
		ArrayList<Road> out2 = new ArrayList<Road>();
		ArrayList<Road> in2 = new ArrayList<Road>();
		out2.add(secondRoad);
		in2.add(firstRoad);
		
		ArrayList<Road> out1 = new ArrayList<Road>();
		ArrayList<Road> in1 = new ArrayList<Road>();
		out1.add(firstRoad);
		in1.add(thirdRoad);
		
		intersections = new ArrayList<Intersection>();
		generators = new ArrayList<Generator>();
		Junction start = new Intersection(2, in2, out2, 1.0f, 2.0f,100,100);
		Junction ends = new Intersection(3, in3, out3, 2.0f, 4.0f, 200,150);
		intersections.add((Intersection) start);
		intersections.add((Intersection) ends);
		//(int ID, ArrayList<Road> entryRoads, ArrayList<Road> awayRoads, int x, int y){
		Junction generator = new Generator(1, in1, out1,50,100);
		generators.add((Generator) generator);
		
		closures.add(start);
		closures.add(ends);
		closures.add(generator);
		roads.add(firstRoad);
		roads.add(secondRoad);
		roads.add(thirdRoad);
		this.createView();
		//double [] lights = new double[intersections.size()];
		//simulator = new Simulator(intersections,generators,10,1000);
		
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
		frame.setSize(600, 400);
		//frame.setBackground(Color.cyan);
		drawingArea = new DrawingArea(closures,ImageObserver.WIDTH,ImageObserver.HEIGHT);
		drawingArea.setSize(300, 300);
		pane.add(drawingArea, BorderLayout.WEST);
		
		mouseListener = new DrawingAreaMouseListener((DrawingArea)drawingArea);
		pane.addMouseListener(mouseListener);
		pane.addMouseMotionListener(mouseListener);
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
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.gridx = 0;
		
		panel.setMinimumSize(new Dimension(100, 500));
		JButton button;
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		button = createButton("Dodaj Generator");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mouseListener.command != DrawingAreaListenerCommand.GENERATOR)
					mouseListener.command = DrawingAreaListenerCommand.GENERATOR;
				else
					mouseListener.command = DrawingAreaListenerCommand.NONE;
			}
		});
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		button = createButton("Dodaj Skrzyzowanie");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mouseListener.command != DrawingAreaListenerCommand.INTERSECTION)
					mouseListener.command = DrawingAreaListenerCommand.INTERSECTION;
				else
					mouseListener.command = DrawingAreaListenerCommand.NONE;
			}
		});
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		button = createButton("Dodaj Droge");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mouseListener.command != DrawingAreaListenerCommand.ROAD)
					mouseListener.command = DrawingAreaListenerCommand.ROAD;
				else
					mouseListener.command = DrawingAreaListenerCommand.NONE;
			}
		});
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		button = createButton("Zacznij od nowa");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(button, cons);
		
		button = createButton("Zapisz");
		button.addActionListener(new SaveModel());
		panel.add(button, cons);
		
		button  = createButton("Wczytaj");
		button.addActionListener(new LoadModel());
		panel.add(button, cons);
		
		button = createButton("Wyjdz");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel.add(button, cons);
		//boxLayout.setSize(200,200);
		
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
//		menuPane.add(createButton("Dodaj generator"), c);
//		menuPane.add(createButton("Dodaj skrzyzowanie"),c);
		
		menuPane.setSize(250, 300);
		menuPane.setBackground(Color.lightGray);
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
	
	public void addNewGenerator(int x, int y){		//Zrobic przeszukiwanie okolicy dla poczatkow i koncow drog
		
	}
	
}
