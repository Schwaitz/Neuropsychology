package biology.neuron.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import misc.ResizeBufferedImage;
import algorithms.RectangleAlgorithm;
import biology.neuron.Neuron;

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

	public boolean xLock = false;

	public ExocytosisPoint releasePointer = null;
	Neuron pointer;

	public int y;

	public boolean releaseTransmitter = false;

	public Vesicle(int xs, int ys, int widths, int heights, JFrame fs,
			Neuron pointers) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		f = fs;
		pointer = pointers;

		dx = (int) (Math.random() * 2 + 2);

		int rand = (int) (Math.random() * 2);
		if (rand == 1) {
			dx = -dx;
		}

		dy = (int) (Math.random() * 2 + 2);
		rand = (int) (Math.random() * 2);
		if (rand == 1) {
			dy = -dy;
		}

		try {
			i = ImageIO.read(new File("./resources/images/Vesicle.png"));
			iDraw = ImageIO.read(new File(
					"./resources/images/Vesicle_transparent.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		i = resize(i, width, height);

		rects = getRects(i, Color.black);

		rect = new Rectangle(x, y, width, height);

		shiftRectangles();

	}

	public void draw(Graphics g) {

		g.drawImage(iDraw, x, y, width, height, f);

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

}
