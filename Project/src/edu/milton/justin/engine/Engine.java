package edu.milton.justin.engine;

import java.io.PrintStream;

import edu.milton.justin.biology.handlers.ReceptorHandler;
import edu.milton.justin.biology.neuron.Receptor;
import edu.milton.justin.engine.handlers.FrameHandler;

public class Engine implements Runnable, FrameHandler, ReceptorHandler {

	Thread mainThread = new Thread(this);

	PrintStream p = new PrintStream(System.out);
	PrintStream e = new PrintStream(System.err);

	boolean running = true;

	public static void main(String args[]) {

		new Engine();
	}

	Engine() {

		setupFrame();

		mainThread.start();
		
		
		for(Receptor r : receptors){
			
			p.println(r.type + " | " + r.name);
		}
		
	}

	@Override
	public void run() {

		while (running == true) {

			paintToFrame();

			try {
				mainThread.sleep(25);
			} catch (InterruptedException exception) {
				e.println("Error with mainThread");
			}
		}

	}

}
