package edu.milton.justin.engine;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.milton.justin.biology.neuron.handlers.ReceptorHandler;
import edu.milton.justin.engine.algorithms.IntersectionRectangles;
import edu.milton.justin.engine.frames.MainFrame;
import edu.milton.justin.engine.frames.SimulationFrame;

public class Engine implements Runnable, ReceptorHandler {

	Thread mainThread = new Thread(this);

	PrintStream p = new PrintStream(System.out);
	PrintStream e = new PrintStream(System.err);

	boolean running = true;

	public static Color drawColor = Color.blue;

	public static Image brain = null;
	public static Image neuron = null;
	public static Image cleft = null;
	public static BufferedImage b = null;

	int mWX = 600;
	int mWY = 700;

	public static int sWX = 400;
	public static int sWY = 600;

	ArrayList<Rectangle> irs = IntersectionRectangles
			.getIntersectionRectangles();

	public static MainFrame mFrame;
	public static SimulationFrame sFrame;

	public static int synapseShiftX = 25;

	public static void main(String args[]) {

		new Engine();
	}

	Engine() {

		try {

			brain = ImageIO.read(new File("./resources/images/brain.png"));
			neuron = ImageIO.read(new File("./resources/images/neuron.png"));
			cleft = ImageIO.read(new File("./resources/images/cleft.png"));
			b = ImageIO.read(new File("./resources/images/cleft.png"));

			// b = ImageIO.read(new File("./resources/images/test.png"));

		} catch (Exception e) {

			e.printStackTrace();

		}

		mFrame = new MainFrame(mWX, mWY);

		// setupSimulationFrame();

		mainThread.start();

	}

	boolean isIntersecting() {

		for (Rectangle r : irs) {

			if (sFrame != null) {
				if (sFrame.ballRect.intersects(r)) {

					return true;
				}
			}

		}

		return false;

	}

	@Override
	public void run() {

		while (running == true) {

			if (isIntersecting()) {
				drawColor = Color.red;
			} else {
				drawColor = Color.blue;
			}

			mFrame.render(mFrame.canvas.getGraphics());

			if (sFrame != null) {
				sFrame.render(sFrame.getGraphics());
			}

			try {
				mainThread.sleep(25);
			} catch (InterruptedException exception) {
				e.println("Error with mainThread");
			}

		}
	}

}
