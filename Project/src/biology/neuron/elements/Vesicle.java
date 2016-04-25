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

public class Vesicle implements RectangleAlgorithm, ResizeBufferedImage {

	public int dx = 3;
	public int dy = 2;
	JFrame f;
	int height;
	public BufferedImage i;
	int originalHeight;
	int originalWidth;

	public ArrayList<Rectangle> rects;
	int width;

	int x;

	int y;

	public Vesicle(int xs, int ys, int widths, int heights, JFrame fs) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		f = fs;

		try {
			i = ImageIO.read(new File("./resources/images/Vesicle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		i = resize(i, width, height);

		rects = getRects(i, Color.black);

		shiftRectangles();

	}

	public void draw(Graphics g) {

		g.drawImage(i, x, y, width, height, f);

	}

	void shiftRectangles() {

		for (Rectangle r : rects) {
			r.x += x;
			r.y += y;

		}

	}

	public void update() {
		x += dx;
		y += dy;

		for (Rectangle r : rects) {

			r.x += dx;
			r.y += dy;

		}

	}

}
