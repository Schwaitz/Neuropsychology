package engine;

import java.io.PrintStream;

import engine.frames.MainFrame;
import engine.frames.SimulationFrame;

public class Engine implements Runnable {

	public static MainFrame mFrame;

	public static SimulationFrame sFrame;
	public static void main(String args[]) {

		new Engine();
	}

	PrintStream e = new PrintStream(System.err);


	Thread mainThread = new Thread(this);
	
	int mWX = 355 + (71 * 2);
	int mWY = 600;
	PrintStream p = new PrintStream(System.out);

	boolean running = true;

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
				sFrame.updateState();
			}

			try {
				Thread.sleep(25);
			} catch (InterruptedException exception) {
				e.println("Error with mainThread");
			}

		}
	}

}
