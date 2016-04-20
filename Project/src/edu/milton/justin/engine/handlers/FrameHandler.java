package edu.milton.justin.engine.handlers;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public interface FrameHandler {

	JFrame frame = new JFrame("Project");

	JPanel options = new JPanel();
	JButton start = new JButton("Start");
	JButton unused1 = new JButton("Unused");
	JButton unused2 = new JButton("Unused");

	JPanel canvas = new JPanel();

	JSlider level = new JSlider(1, 3);
	int startLevel = 1;

	Dictionary<Integer, JLabel> levelDictionary = new Hashtable<Integer, JLabel>();

	int WX = 600;
	int WY = 600;

	default void setupFrame() {

		frame.setEnabled(true);
		frame.setVisible(true);
		 frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		setupCanvas();
		setupPanel();

		frame.setSize(WX, WY);
	}




	default void setupCanvas() {

		frame.add(canvas, BorderLayout.CENTER);

	}

	default void setupPanel() {


		
		options.add(start);
		options.add(unused1);
		options.add(unused2);

		setupLevel();

		frame.add(options, BorderLayout.SOUTH);

	}

	default void setupLevel() {

		level.setValue(startLevel);
		level.setSnapToTicks(true);

		levelDictionary.put(1, new JLabel("Brain"));
		levelDictionary.put(2, new JLabel("Neuron"));
		levelDictionary.put(3, new JLabel("Synapse"));

		level.setLabelTable(levelDictionary);
		level.setPaintLabels(true);

		options.add(level);

	}

	default int getLevelValue() {

		return level.getValue();

	}

}
