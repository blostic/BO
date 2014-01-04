package bo.project.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import bo.project.client.ViewSimulator;
import bo.project.editor.listeners.AddGeneratorButtonMouseListener;
import bo.project.editor.listeners.AddJunctionButtonMouseListener;
import bo.project.editor.listeners.AddRoadButtonMouseListener;
import bo.project.editor.listeners.AskButtonMouseListener;
import bo.project.editor.listeners.ClearButtonActionListener;
import bo.project.editor.listeners.DrawingAreaMouseListener;
import bo.project.editor.listeners.ExitActionListener;
import bo.project.editor.listeners.SendButtonMouseListener;
import bo.project.editor.listeners.SpeedSliderListener;
import bo.project.editor.listeners.StopButtonMouseListener;
import bo.project.logic.Generator;
import bo.project.logic.Intersection;

public class TrafficEditorFrame {

	private BorderLayout layout;
	private JPanel pane;
	private JPanel menuPane;
	private Border border;
	private JFrame frame;
	private LayoutManager layout2;

	public DrawingArea drawingArea;
	private DrawingAreaMouseListener mouseListener;
	private ViewSimulator simulator;
	

	public TrafficEditorFrame() {
		this.simulator = new ViewSimulator(new ArrayList<Intersection>(),
				new ArrayList<Generator>(), 1, this);
		this.createView();
		
	}

	public DrawingArea getDrawingArea() {
		return drawingArea;
	}

	public void setDrawingArea(DrawingArea drawingArea) {
		this.drawingArea = drawingArea;
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
		button.addActionListener(new AddGeneratorButtonMouseListener(mouseListener));
		
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		button = createButton("Dodaj Skrzyzowanie");
		button.addActionListener(new AddJunctionButtonMouseListener(mouseListener));
		
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		button = createButton("Dodaj Droge");
		button.addActionListener(new AddRoadButtonMouseListener(mouseListener));
		
		panel.add(button, cons);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		button = createButton("Zacznij od nowa");
		button.addActionListener(new ClearButtonActionListener(this));
		panel.add(button, cons);

		button = createButton("Zapisz");
		button.addActionListener(new SaveModel(this));
		panel.add(button, cons);

		button = createButton("Wczytaj");
		button.addActionListener(new LoadModel(this));
		panel.add(button, cons);
		
		button = createButton("Wyslij do serwera");
		button.addActionListener(new SendButtonMouseListener(drawingArea));
		panel.add(button, cons);
		
		button = createButton("Sprawdz rozwiazanie");
		button.addActionListener(new AskButtonMouseListener(drawingArea));
		panel.add(button, cons);

		button = createButton("Rozpocznij symulacje");
		button.addActionListener(new SimulatorListener(this));
		panel.add(button, cons);
		
		button = createButton("Zakoncz symulacje");
		button.addActionListener(new StopButtonMouseListener(this));
		panel.add(button, cons);
		
		button = createButton("Wyjdz");
		button.addActionListener(new ExitActionListener());
		panel.add(button, cons);
		
		JLabel sliderLabel = new JLabel("Szybkosc symulacji", JLabel.CENTER);
		panel.add(sliderLabel, cons);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.addChangeListener(new SpeedSliderListener());
		panel.add(slider, cons);
	
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

	public ViewSimulator getSimulator() {
		return simulator;
	}

	public void setSimulator(ViewSimulator simulator) {
		this.simulator = simulator;
		this.drawingArea.setSimulator(simulator);
	}


}
