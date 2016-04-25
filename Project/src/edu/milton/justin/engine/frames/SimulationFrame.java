package edu.milton.justin.engine.frames;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JFrame;

import edu.milton.justin.biology.neuron.Neuron;
import edu.milton.justin.biology.neuron.PostsynapticNeuron;
import edu.milton.justin.biology.neuron.PresynapticNeuron;
import edu.milton.justin.biology.neuron.handlers.AutoReceptorHandler;
import edu.milton.justin.biology.neuron.handlers.PostsynapticReceptorHandler;
import edu.milton.justin.engine.Engine;

public class SimulationFrame extends JFrame implements MouseMotionListener,
		AutoReceptorHandler, PostsynapticReceptorHandler {

	public int WX;
	public int WY;

	public int ballX = 0;
	public int ballY = 0;
	public Rectangle ballRect = new Rectangle(0, 0, 0, 0);

	Neuron pre;
	Neuron post;

	boolean loaded = false;

	public SimulationFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;

		this.addMouseMotionListener(this);

		setupFrame();
		setupBiology();

		loaded = true;
	}

	void setupFrame() {

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
		pre = new PresynapticNeuron(preFile, this);
		
		File postFile = new File("./resources/images/post.png");
		post = new PostsynapticNeuron(postFile, this);

	}

	public void render(Graphics g) {

		ballRect = new Rectangle(ballX, ballY, 10, 10);

		Image offscreen = this.createImage(this.WX, this.WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, this.WX, this.WY);

		pre.draw(bufferGraphics);
		post.draw(bufferGraphics);

		bufferGraphics.setColor(Engine.drawColor);
		bufferGraphics.fillOval(ballX, ballY, 10, 10);

		g.drawImage(offscreen, 0, 0, this.WX, this.WY, this);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		ballX = e.getX() - 6;
		ballY = e.getY() - 10;

	}

}
