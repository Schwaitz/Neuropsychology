package edu.milton.justin.engine.frames;

import java.awt.BorderLayout;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class MainFrame extends JFrame {

	JPanel options = new JPanel();
	public JButton start = new JButton("Start");
	public JButton unused1 = new JButton("Unused");
	public JButton unused2 = new JButton("Unused");

	public JPanel canvas = new JPanel();

	public JSlider level = new JSlider(1, 3);
	int startLevel = 1;

	Dictionary<Integer, JLabel> levelDictionary = new Hashtable<Integer, JLabel>();

	public int WX;
	public int WY;

	public MainFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;

		setupFrame();

	}

	void setupFrame() {

		this.setTitle("Main Frame");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		setupCanvas();
		setupPanel();

		this.setSize(WX, WY);
	}

	void setupCanvas() {

		this.add(canvas, BorderLayout.CENTER);

	}

	void setupPanel() {

		options.add(start);
		options.add(unused1);
		options.add(unused2);

		setupLevel();

		this.add(options, BorderLayout.SOUTH);

	}

	void setupLevel() {

		level.setValue(startLevel);
		level.setSnapToTicks(true);

		levelDictionary.put(1, new JLabel("Brain"));
		levelDictionary.put(2, new JLabel("Neuron"));
		levelDictionary.put(3, new JLabel("Synapse"));

		level.setLabelTable(levelDictionary);
		level.setPaintLabels(true);

		options.add(level);

	}

	public int getLevelValue() {

		return level.getValue();

	}

}
