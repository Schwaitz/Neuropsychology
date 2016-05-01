package engine;

import java.io.PrintStream;

import engine.frames.MainFrame;
import engine.frames.OptionsFrame;
import engine.frames.SimulationFrame;

public class Engine implements Runnable {

	public static MainFrame mFrame;
	public static OptionsFrame oFrame;

	public static SimulationFrame sFrame;

	public static void main(String args[]) {

		new Engine();
	}

	PrintStream e = new PrintStream(System.err);

	Thread mainThread = new Thread(this);

	PrintStream p = new PrintStream(System.out);

	boolean running = true;

	Engine() {

		mFrame = new MainFrame();

		oFrame = new OptionsFrame();

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
