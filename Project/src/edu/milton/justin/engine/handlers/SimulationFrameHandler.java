package edu.milton.justin.engine.handlers;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;

public interface SimulationFrameHandler {

	JFrame simulationFrame = new JFrame("Simulation");;

	int sWX = 400;
	int sWY = 600;

	default void setupSimulationFrame() {

		simulationFrame.setEnabled(true);
		simulationFrame.setVisible(true);
		simulationFrame.setResizable(false);
		simulationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		simulationFrame.setLocation(sWX + 10, 0);
		simulationFrame.setSize(sWX, sWY);
	}

}
