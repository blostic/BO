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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import bo.project.editor.DrawingAreaMouseListener.DrawingAreaListenerCommand;
import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Road;
import bo.project.logic.Simulator;
import bo.project.logic.Vehicle;

public class TrafficEditorFrame {

	private BorderLayout layout;
	private JPanel pane;
	private JPanel menuPane;
	private Border border;
	private JFrame frame;
	private LayoutManager layout2;

	private JPanel drawingArea;
	private DrawingAreaMouseListener mouseListener;
	private Simulator simulator;

	public TrafficEditorFrame() {
		this.simulator = new Simulator(new ArrayList<Intersection>(),
				new ArrayList<Generator>(), 1, 100);

		Vehicle auto = new Vehicle();
		auto.setX(50);
		auto.setY(100);
		
		Intersection start = new Intersection(new ArrayList<Road>(), new ArrayList<Road>(), 1.0f, 2.0f, 100, 100);
		Intersection end = new Intersection(new ArrayList<Road>(), new ArrayList<Road>(), 2.0f, 4.0f, 200, 150);
		this.simulator.getIntersections().add((Intersection) start);
		this.simulator.getIntersections().add((Intersection) end);
		
		Generator generator = new Generator(new ArrayList<Road>(), new ArrayList<Road>(), 50, 100);
		this.simulator.getGenerators().add(generator);
		this.simulator.getGenerators().add(generator);
		this.simulator.getIntersections().add(start);
		this.simulator.getIntersections().add(end);

		this.createView();
		
	}

	@SuppressWarnings("deprecation")
	public void show() {
		if (frame != null)
			frame.show();
	}

	private void createView() {

		layout = new BorderLayout();

		pane = new JPanel(layout);
		border = BorderFactory.createLineBorder(Color.green);
		pane.setBorder(border);

		frame = new JFrame("Badania Operacyjne");
		frame.setSize(600, 400);
		// frame.setBackground(Color.cyan);
		drawingArea = new DrawingArea(this.simulator, ImageObserver.WIDTH,
				ImageObserver.HEIGHT);
		drawingArea.setSize(300, 300);
		pane.add(drawingArea, BorderLayout.WEST);

		mouseListener = new DrawingAreaMouseListener((DrawingArea) drawingArea);
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

	private JPanel createOverallManagementPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.gridx = 0;

		panel.setMinimumSize(new Dimension(100, 500));
		JButton button;
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		button = createButton("Dodaj Generator");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mouseListener.command != DrawingAreaListenerCommand.GENERATOR)
					mouseListener.command = DrawingAreaListenerCommand.GENERATOR;
				else
					mouseListener.command = DrawingAreaListenerCommand.NONE;
			}
		});
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		button = createButton("Dodaj Skrzyzowanie");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mouseListener.command != DrawingAreaListenerCommand.INTERSECTION)
					mouseListener.command = DrawingAreaListenerCommand.INTERSECTION;
				else
					mouseListener.command = DrawingAreaListenerCommand.NONE;
			}
		});
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		button = createButton("Dodaj Droge");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mouseListener.command != DrawingAreaListenerCommand.ROAD)
					mouseListener.command = DrawingAreaListenerCommand.ROAD;
				else
					mouseListener.command = DrawingAreaListenerCommand.NONE;
			}
		});
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		button = createButton("Zacznij od nowa");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		panel.add(button, cons);

		button = createButton("Zapisz");
		button.addActionListener(new SaveModel(this));
		panel.add(button, cons);

		button = createButton("Wczytaj");
		button.addActionListener(new LoadModel(this));
		panel.add(button, cons);

		button = createButton("Wyjdz");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel.add(button, cons);
		// boxLayout.setSize(200,200);

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
		// menuPane.add(createButton("Dodaj generator"), c);
		// menuPane.add(createButton("Dodaj skrzyzowanie"),c);

		menuPane.setSize(250, 300);
		menuPane.setBackground(Color.lightGray);
		JPanel panel = createOverallManagementPanel();
		c.gridy = 1;
		menuPane.add(panel, c);
		pane.add(menuPane, BorderLayout.EAST);
	}

	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setSize(200, 20);
		return button;
	}

	public void addNewGenerator(int x, int y) { // Zrobic przeszukiwanie okolicy
												// dla poczatkow i koncow drog

	}

	public Simulator getSimulator() {
		return simulator;
	}

	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
	}

}
