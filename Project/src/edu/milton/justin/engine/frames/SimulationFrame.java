package edu.milton.justin.engine.frames;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.milton.justin.biology.neuron.Neuron;
import edu.milton.justin.biology.neuron.PostsynapticNeuron;
import edu.milton.justin.biology.neuron.PresynapticNeuron;
import edu.milton.justin.biology.neuron.elements.Vesicle;
import edu.milton.justin.biology.neuron.handlers.AutoReceptorHandler;
import edu.milton.justin.biology.neuron.handlers.PostsynapticReceptorHandler;
import edu.milton.justin.engine.handlers.IntersectionHandler;

public class SimulationFrame extends JFrame implements AutoReceptorHandler,
		PostsynapticReceptorHandler, IntersectionHandler, MouseMotionListener {

	public int WX;
	public int WY;

	int mouseX = 0;
	int mouseY = 0;

	Neuron pre;
	Neuron post;

	boolean loaded = false;

	public SimulationFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;

		setupFrame();
		setupBiology();

		loaded = true;
	}

	void setupFrame() {

		this.addMouseMotionListener(this);

		this.setTitle("Simulation Frame");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.setSize(WX, WY);

	}

	void setupBiology() {

		File preFile = new File("./resources/images/pre.png");
		pre = new PresynapticNeuron(preFile, this, 0, 0);

		File postFile = new File("./resources/images/post.png");
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

	boolean intersecting(ArrayList<Rectangle> rects) {

		for (Rectangle r : rects) {

			if (r.contains(mouseX, mouseY)) {

				return true;
			}
		}

		return false;
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		mouseX = e.getX();
		mouseY = e.getY();

	}

}
