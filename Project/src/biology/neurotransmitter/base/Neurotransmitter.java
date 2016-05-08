package biology.neurotransmitter.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;

import biology.neuron.elements.Vesicle;
import biology.neuron.elements.receptor.base.Receptor;

public class Neurotransmitter {

	public Color color;
	public int dx;
	public int dy;
	int height;
	public Rectangle rect;
	public String type;
	int width;
	public int x;
	public int y;

	public int speedLimit = 8;

	public boolean xLock = false;
	public boolean released = false;
	public boolean passed = false;

	public int prx;
	public int pry;
	public int prw;
	public int prh;

	public Vesicle pointer;

	public String receptorBindType;
	public Receptor bindReceptor;

	JFrame f;

	public Neurotransmitter(String types, int xs, int ys, int widths, int heights, Color colors, JFrame fs,
			Vesicle pointers, String receptorBindTypes) {

		type = types;
		x = xs;
		y = ys;
		width = widths;
		height = heights;
		color = colors;
		f = fs;
		pointer = pointers;
		receptorBindType = receptorBindTypes;

		prx = pointer.x;
		pry = pointer.y;
		prw = pointer.x + pointer.width;
		prh = pointer.y + pointer.height;

		dx = (int) (Math.random() * 2 + 1);

		int rand = (int) (Math.random() * 2);
		if (rand == 1) {
			dx = -dx;
		}

		dy = (int) (Math.random() * 2 + 1);
		rand = (int) (Math.random() * 2);
		if (rand == 1) {
			dy = -dy;
		}

		rect = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {

		g.setColor(color);

		g.fillOval(x, y, width, height);

	}

	public void update() {
		x += dx;
		y += dy;

		prx = pointer.x + 5;
		pry = pointer.y + 5;
		prw = pointer.x + pointer.width - 10;
		prh = pointer.y + pointer.height - 10;

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

		rect = new Rectangle(x, y, width, height);

	}

	public void release() {

		dy = (int) (Math.random() * 4 - 1);
		dx = (int) (Math.random() * 8 - 4);

		int switchRandX = (int) (Math.random() * 2);

		int randX = 0;
		if (switchRandX == 0) {
			randX = (int) (Math.random() * 20 + 5);
		} else {
			randX = (int) (Math.random() * -20 + -5);
		}

		x += randX;

		y += (int) (Math.random() * 6);

		if (dy == 0) {
			dy = (int) (Math.random() * 3 + 1);
		}
		if (dx == 0) {
			dx = (int) (Math.random() * 3 + 1);
		}

	}

	public void unlockPassed() {

		new Thread(new Runnable() {

			public void run() {

				int count = 0;

				while (count < 100) {

					count++;

					try {
						Thread.sleep(6);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				passed = true;
			}
		}).start();
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
