package edu.milton.justin.engine.frames;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import edu.milton.justin.engine.Engine;

public class SimulationFrame extends JFrame implements MouseMotionListener {

	public int WX;
	public int WY;

	public int ballX = 0;
	public int ballY = 0;
	public Rectangle ballRect = new Rectangle(0,0,0,0);
	
	boolean loaded = false;

	public SimulationFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;
		
		this.addMouseMotionListener(this);

		setupFrame();


		
		loaded = true;
	}

	void setupFrame() {

		this.setTitle("Simulation Frame");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		this.setLocation(Engine.mFrame.WX + 100, 0);
		this.setSize(WX, WY);

	}

	public void render(Graphics g) {

		ballRect = new Rectangle(ballX, ballY, 10, 10);

		Image offscreen = this.createImage(this.WX, this.WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, this.WX, this.WY);

		bufferGraphics.drawImage(Engine.b, Engine.synapseShiftX, 0, this);

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
