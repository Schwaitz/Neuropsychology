package engine.frames;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import biology.neuron.Neuron;
import biology.neuron.PostsynapticNeuron;
import biology.neuron.PresynapticNeuron;
import biology.neuron.elements.Vesicle;
import biology.neuron.handlers.AutoReceptorHandler;
import biology.neuron.handlers.PostsynapticReceptorHandler;
import engine.handlers.IntersectionHandler;

public class SimulationFrame extends JFrame implements AutoReceptorHandler,
		PostsynapticReceptorHandler, IntersectionHandler {

	boolean loaded = false;

	Neuron post;

	Neuron pre;
	public int WX;

	public int WY;

	public SimulationFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;

		setupFrame();
		setupBiology();

	}

	void setupFrame() {


		this.setTitle("Simulation");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.setSize(WX, WY);

	}

	void setupBiology() {

		File preFile = new File("./resources/images/PresynapticNeuron.png");
		pre = new PresynapticNeuron(preFile, this, 0, 0);

		File postFile = new File("./resources/images/PostsynapticNeuron.png");
		post = new PostsynapticNeuron(postFile, this, 0, 400);

	}
	
	
	
	

	public void render(Graphics g) {

		Image offscreen = this.createImage(this.WX, this.WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, this.WX, this.WY);

		pre.draw(bufferGraphics);
		post.draw(bufferGraphics);

		for (Vesicle v : pre.vesicles) {

			v.draw(bufferGraphics);
		}

		g.drawImage(offscreen, 0, 0, this.WX, this.WY, this);

	}

	public void updateState() {

		checkIntersections();

		handleUpdates();

	}

	void checkIntersections() {

		for (Vesicle v : pre.vesicles) {
			if (handleIntersection(v.rects, pre.rects)) {

				v.dx = -v.dx;
				v.dy = -v.dy;
			}
		}

	}

	void handleUpdates() {

		pre.update();
		post.update();

	}


}
