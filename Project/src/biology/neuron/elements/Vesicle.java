package biology.neuron.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algorithms.RectangleAlgorithm;
import biology.neuron.Neuron;
import biology.neurotransmitter.Acetylcholine;
import biology.neurotransmitter.Dopamine;
import biology.neurotransmitter.GABA;
import biology.neurotransmitter.Glutamate;
import biology.neurotransmitter.Norepinephrine;
import biology.neurotransmitter.Serotonin;
import biology.neurotransmitter.base.Neurotransmitter;
import engine.frames.SimulationFrame;
import misc.ResizeBufferedImage;

public class Vesicle implements RectangleAlgorithm, ResizeBufferedImage {

	public int dx = 0;
	public int dy = 0;
	JFrame f;

	public BufferedImage i;
	public BufferedImage iDraw;

	public Rectangle rect;
	public ArrayList<Rectangle> rects;
	public int width;
	public int height;
	public int x;

	public int speedLimit = 6;

	int ntAmount = 30;

	public boolean xLock = false;

	Neuron pointer;

	public int y;

	public ArrayList<Neurotransmitter> NTContainer = new ArrayList<Neurotransmitter>();

	public Neurotransmitter ntType;

	public boolean releaseTransmitter = false;

	public Vesicle(int xs, int ys, int widths, int heights, JFrame fs, Neuron pointers) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		f = fs;
		pointer = pointers;

		dx = (int) (Math.random() * 4 - 2);

		if (dx == 0) {
			int rand = (int) (Math.random() * 2);

			if (rand == 0) {
				dx = 1;
			} else {
				dx = -1;
			}

		}

		dy = (int) (Math.random() * 2 + 4);

		try {
			i = ImageIO.read(new File("./resources/images/Vesicle.png"));
			iDraw = ImageIO.read(new File("./resources/images/Vesicle_transparent.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		i = resize(i, width, height);

		rects = getRects(i, Color.black);

		rect = new Rectangle(x, y, width, height);

		NTContainer = fill();

		shiftRectangles();

	}

	public void draw(Graphics g) {

		g.drawImage(iDraw, x, y, width, height, f);

		int charWidth = 15;
		int charHeight = 15;
		Font f = new Font("Impact", charWidth, charHeight);
		g.setFont(f);
		g.setColor(ntType.color);
		Graphics2D g2d = (Graphics2D) g;

		FontRenderContext frc = g2d.getFontRenderContext();
		TextLayout layout = new TextLayout(ntType.type, f, frc);
		Rectangle2D bounds = layout.getBounds();

		int fWidth = (int) Math.round(bounds.getWidth());
		int fHeight = (int) Math.round(bounds.getHeight());

		g.drawString(ntType.type, x + (width / 2) - (fWidth / 2), y + height + fHeight + 4);

	}

	void shiftRectangles() {

		for (Rectangle r : rects) {
			r.x += x;
			r.y += y;

		}

	}

	public void shiftRectangles(int amountX, int amountY) {

		for (Rectangle r : rects) {
			r.x += amountX;
			r.y += amountY;

		}

	}

	public void update() {
		x += dx;
		y += dy;

		rect = new Rectangle(x, y, width, height);

		for (Rectangle r : rects) {

			r.x += dx;
			r.y += dy;

		}

		if (dx >= speedLimit) {
			dx = speedLimit - 1;
		}

		if (dx <= -speedLimit) {
			dx = -speedLimit + 1;
		}

		if (dy >= speedLimit) {
			dy = speedLimit - 1;
		}

		if (dy <= -speedLimit) {
			dy = -speedLimit + 1;
		}

	}

	public void remove() {

		// this.rects.clear();
		pointer.rVesicles.add(this);

	}

	public void intersectionLock() {

		xLock = true;

		new Thread(new Runnable() {

			int count = 0;

			public void run() {

				while (count < 30) {

					count++;

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				xLock = false;

			}

		}).start();

	}

	ArrayList<Neurotransmitter> fill() {
		ArrayList<Neurotransmitter> returnNT = new ArrayList<Neurotransmitter>();

		int rand = (int) (Math.random() * 6);

		if (rand == 0) {
			for (int i = 0; i < ntAmount; i++) {

				Neurotransmitter temp = new Dopamine("Dopamine", x + (width / 2), y + (height / 2), 5, 5, f, this,
						"Dopamine");
				SimulationFrame.nt.add(temp);
				ntType = temp;
			}
		}

		if (rand == 1) {
			for (int i = 0; i < ntAmount; i++) {
				Neurotransmitter temp = new Serotonin("Serotonin", x + (width / 2), y + (height / 2), 5, 5, f, this,
						"Serotonin");
				SimulationFrame.nt.add(temp);
				ntType = temp;
			}
		}

		if (rand == 2) {
			for (int i = 0; i < ntAmount; i++) {
				Neurotransmitter temp = new Acetylcholine("Acetylcholine", x + (width / 2), y + (height / 2), 5, 5, f,
						this, "Acetylcholine");
				SimulationFrame.nt.add(temp);
				ntType = temp;
			}
		}

		if (rand == 3) {
			for (int i = 0; i < ntAmount; i++) {
				Neurotransmitter temp = new GABA("GABA", x + (width / 2), y + (height / 2), 5, 5, f, this, "GABA");
				SimulationFrame.nt.add(temp);
				ntType = temp;
			}
		}

		if (rand == 4) {
			for (int i = 0; i < ntAmount; i++) {
				Neurotransmitter temp = new Glutamate("Glutamate", x + (width / 2), y + (height / 2), 5, 5, f, this,
						"Glutamate");
				SimulationFrame.nt.add(temp);
				ntType = temp;

			}
		}
		if (rand == 5) {
			for (int i = 0; i < ntAmount; i++) {
				Neurotransmitter temp = new Norepinephrine("Norepinephrine", x + (width / 2), y + (height / 2), 5, 5, f,
						this, "Norepinephrine");
				SimulationFrame.nt.add(temp);
				ntType = temp;

			}
		}

		return returnNT;

	}

}
