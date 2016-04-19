package edu.milton.justin.engine.handlers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public interface FrameHandler {

	static JFrame frame = new JFrame("Project");

	static int WX = 800;
	static int WY = 600;

	default void setupFrame() {

		frame.setEnabled(true);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(WX, WY);
	}

	default void render(Graphics g) {

		Image offscreen = frame.createImage(WX, WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, WX, WY);

		bufferGraphics.setColor(Color.black);
		bufferGraphics.fillRect(100, 100, 100, 100);

		g.drawImage(offscreen, 0, 0, WX, WY, frame);
	}

	default void paintToFrame() {

		render(frame.getGraphics());

	}

}
