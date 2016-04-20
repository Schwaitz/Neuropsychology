package edu.milton.justin.engine.handlers;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import edu.milton.justin.engine.Engine;

public class SimulationFrame extends JFrame {

	public int WX;
	public int WY;

	public SimulationFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;
		
		setupFrame();

	}

	void setupFrame() {

		this.setTitle("Simulation Frame");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.setLocation(Engine.mFrame.WX + 10, 0);
		this.setSize(WX, WY);
	}

}
