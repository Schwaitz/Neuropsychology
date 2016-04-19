package edu.milton.justin.engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.milton.justin.biology.neuron.handlers.ReceptorHandler;
import edu.milton.justin.engine.handlers.FrameHandler;
import edu.milton.justin.misc.ValueHolder;

public class Engine implements Runnable, FrameHandler, ReceptorHandler,
		MouseMotionListener {

	Thread mainThread = new Thread(this);

	PrintStream p = new PrintStream(System.out);
	PrintStream e = new PrintStream(System.err);

	boolean running = true;

	Color drawColor = Color.blue;

	Image brain = null;
	Image neuron = null;
	Image cleft = null;

	BufferedImage b;

	ArrayList<Rectangle> irs = new ArrayList<Rectangle>();

	int ballX = WX / 2;
	int ballY = WY / 2 - 200;

	int synapseShiftX = 125;

	Rectangle ballRect = new Rectangle(ballX, ballY, 10, 10);

	public static void main(String args[]) {

		new Engine();
	}

	Engine() {

		setupFrame();

		frame.addMouseMotionListener(this);

		try {

			brain = ImageIO.read(new File("./resources/images/brain.png"));
			neuron = ImageIO.read(new File("./resources/images/neuron.png"));
			cleft = ImageIO.read(new File("./resources/images/cleft.png"));

			b = ImageIO.read(new File("./resources/images/cleft.png"));

		} catch (Exception e) {

			e.printStackTrace();

		}

		getIntersectionRectangles();

		mainThread.start();

	}

	void getIntersectionRectangles() {

		for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {

				if (b.getRGB(x, y) != 16777215 && b.getRGB(x, y) != 0) {
					b.setRGB(x, y, -16777216);
				}

			}
		}

		for (int y = 0; y < b.getHeight(); y++) {

			ArrayList<Integer> indexes = new ArrayList<Integer>();
			int indexNum = 0;
			for (int x = 0; x < b.getWidth(); x++) {

				if (b.getRGB(x, y) == -16777216) {
					indexes.add(x);

					if ((x + 1) < b.getWidth()) {
						if (b.getRGB(x + 1, y) == 0
								|| b.getRGB(x + 1, y) == 16777215) {

							indexNum += 1;
						}
					}

				}

			}

			int count = 0;
			ArrayList<Integer> ends = new ArrayList<Integer>();
			ArrayList<Integer> starts = new ArrayList<Integer>();
			for (Integer i : indexes) {

				if (count == 0) {
					starts.add(i);
				}
				count++;

				if (indexes.indexOf(i) < (indexes.size() - 1)) {

					if (indexes.get(indexes.indexOf(i) + 1) != (i + 1)) {

						ends.add(indexes.get(indexes.indexOf(i)));
						count = 0;

					}
				}

				if (indexes.indexOf(i) == indexes.size() - 1) {

					ends.add(indexes.get(indexes.indexOf(i)));
				}

			}

			ArrayList<ValueHolder> startEnds = new ArrayList<ValueHolder>();

			for (int i = 0; i < ends.size(); i++) {

				startEnds.add(new ValueHolder(starts.get(i), ends.get(i)));

			}

			for (ValueHolder se : startEnds) {
				int dif = se.end - se.start;
				irs.add(new Rectangle(se.start + synapseShiftX, y, dif, 1));
			}

		}
	}

	void render(Graphics g) {

		Image offscreen = canvas.createImage(WX, WY);
		Graphics bufferGraphics = offscreen.getGraphics();
		bufferGraphics.clearRect(0, 0, WX, WY);

		if (getLevelValue() == 1) {

			bufferGraphics.drawImage(brain, 100, 100, 90 * 4, 72 * 4, canvas);

		}
		if (getLevelValue() == 2) {

			int nWidth = 50 * 4;
			int nHeight = 72 * 4;

			bufferGraphics.drawImage(neuron, 50, 50, nWidth, nHeight, canvas);

			bufferGraphics.drawImage(neuron, nWidth, nHeight + 25, nWidth,
					nHeight, canvas);

			Graphics2D g2d = (Graphics2D) bufferGraphics;

			g2d.setStroke(new BasicStroke(3));

			g2d.drawOval(nWidth, nHeight, 50, 50);

			g2d.drawLine(nWidth + 50, nHeight + 25, nWidth + 125, nHeight - 25);

			g2d.setStroke(new BasicStroke(1));

			bufferGraphics.setFont(new Font("Impact", 20, 20));

			bufferGraphics.drawString("Synapse", nWidth + 130, nHeight - 30);

		}

		if (getLevelValue() == 3) {

			bufferGraphics.drawImage(b, synapseShiftX, 0, canvas);

			bufferGraphics.setColor(drawColor);
			bufferGraphics.fillOval(ballX, ballY, 10, 10);

		}

		g.drawImage(offscreen, 0, 0, WX, WY, frame);
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

			frame.requestFocusInWindow();

			ballRect = new Rectangle(ballX, ballY, 10, 10);

			if (isIntersecting()) {
				drawColor = Color.red;
			} else {
				drawColor = Color.blue;
			}

			render(canvas.getGraphics());

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

		ballX = e.getX() - 10;
		ballY = e.getY() - 30;

	}

}
