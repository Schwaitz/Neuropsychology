package edu.milton.justin.engine;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.PrintStream;

import edu.milton.justin.engine.frames.MainFrame;
import edu.milton.justin.engine.frames.SimulationFrame;

public class Engine implements Runnable {

	Thread mainThread = new Thread(this);

	PrintStream p = new PrintStream(System.out);
	PrintStream e = new PrintStream(System.err);

	boolean running = true;

	public static Color drawColor = Color.blue;

	int mWX = 355;
	int mWY = 600;

	public static MainFrame mFrame;
	public static SimulationFrame sFrame;

	public static void main(String args[]) {

		new Engine();
	}

	Engine() {

		mFrame = new MainFrame(mWX, mWY);

		mainThread.start();

	}

	@SuppressWarnings("all")
	@Override
	public void run() {

		while (running == true) {

			if (mFrame != null) {
				mFrame.render(mFrame.getGraphics());
			}

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
