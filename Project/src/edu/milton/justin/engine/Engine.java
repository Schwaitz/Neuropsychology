package edu.milton.justin.engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import edu.milton.justin.engine.handlers.MainFrame;
import edu.milton.justin.engine.handlers.SimulationFrame;

public class Engine implements Runnable, ReceptorHandler, MouseMotionListener,
		ActionListener {

	Thread mainThread = new Thread(this);

	PrintStream p = new PrintStream(System.out);
	PrintStream e = new PrintStream(System.err);

	boolean running = true;

	Color drawColor = Color.blue;

	Image brain = null;
	Image neuron = null;
	Image cleft = null;
	
	
	int mWX = 600;
	int mWY = 700;
	
	int sWX = 600;
	int sWY = 700;

	BufferedImage b;

	ArrayList<Rectangle> irs = IntersectionRectangles
			.getIntersectionRectangles();

	static public MainFrame mFrame;
	static public SimulationFrame sFrame;

	int ballX;
	int ballY;

	int synapseShiftX = 125;

	Rectangle ballRect = new Rectangle(ballX, ballY, 10, 10);

	public static void main(String args[]) {

		new Engine();
	}

	Engine() {

		mFrame = new MainFrame(mWX, mWY);

		// setupSimulationFrame();



		try {

			brain = ImageIO.read(new File("./resources/images/brain.png"));
			neuron = ImageIO.read(new File("./resources/images/neuron.png"));
			cleft = ImageIO.read(new File("./resources/images/cleft.png"));
			b = ImageIO.read(new File("./resources/images/cleft.png"));

			// b = ImageIO.read(new File("./resources/images/test.png"));

		} catch (Exception e) {

			e.printStackTrace();

		}

		mFrame.start.addActionListener(this);
		mFrame.unused1.addActionListener(this);
		mFrame.unused2.addActionListener(this);

		mainThread.start();

	}

	void render(Graphics g) {

		Image offscreen = mFrame.canvas.createImage(mFrame.WX, mFrame.WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, mFrame.WX, mFrame.WY);

		if (mFrame.getLevelValue() == 1) {

			bufferGraphics.drawImage(brain, 100, 100, 90 * 4, 72 * 4,
					mFrame.canvas);

		}
		if (mFrame.getLevelValue() == 2) {

			int nWidth = 50 * 4;
			int nHeight = 72 * 4;

			bufferGraphics.drawImage(neuron, 50, 50, nWidth, nHeight,
					mFrame.canvas);

			bufferGraphics.drawImage(neuron, nWidth, nHeight + 25, nWidth,
					nHeight, mFrame.canvas);

			Graphics2D g2d = (Graphics2D) bufferGraphics;

			g2d.setStroke(new BasicStroke(3));

			g2d.drawOval(nWidth, nHeight, 50, 50);

			g2d.drawLine(nWidth + 50, nHeight + 25, nWidth + 125, nHeight - 25);

			g2d.setStroke(new BasicStroke(1));

			bufferGraphics.setFont(new Font("Impact", 20, 20));

			bufferGraphics.drawString("Synapse", nWidth + 130, nHeight - 30);

		}

		if (mFrame.getLevelValue() == 3) {

			bufferGraphics.setColor(Color.blue);
			bufferGraphics.setFont(new Font("Impact", 50, 50));
			bufferGraphics.drawString("Press the \"Start\" button", mWX/2-250, mWY/2);
			bufferGraphics.drawString("to start the Simulation.", mWX/2-250, mWY/2 + 60);

		}

		g.drawImage(offscreen, 0, 0, mFrame.WX, mFrame.WY, mFrame.canvas);
	}

	void simulationRender(Graphics g) {

		Image offscreen = sFrame.createImage(sFrame.WX, sFrame.WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, sFrame.WX, sFrame.WY);
		
		
		bufferGraphics.drawImage(b, synapseShiftX, 0, mFrame.canvas);
		
		
		bufferGraphics.setColor(drawColor);
		bufferGraphics.fillOval(ballX, ballY, 10, 10);

		g.drawImage(offscreen, 0, 0, sFrame.WX, sFrame.WY, sFrame);

	}

	boolean isIntersecting() {

		for (Rectangle r : irs) {

			if (ballRect.intersects(r)) {

				return true;

			}

		}

		return false;

	}

	@Override
	public void run() {

		while (running == true) {

			ballRect = new Rectangle(ballX, ballY, 10, 10);

			if (isIntersecting()) {
				drawColor = Color.red;
			} else {
				drawColor = Color.blue;
			}

			render(mFrame.canvas.getGraphics());

			if (sFrame != null) {
				simulationRender(sFrame.getGraphics());
			}

			try {
				mainThread.sleep(25);
			} catch (InterruptedException exception) {
				e.println("Error with mainThread");
			}

		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(mFrame.start)) {
			
			ballX = 300;
			ballY = 300;
			
			sFrame = new SimulationFrame(sWX, sWY);
			sFrame.addMouseMotionListener(this);
		}

	}

}
