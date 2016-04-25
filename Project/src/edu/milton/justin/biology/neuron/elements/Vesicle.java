package edu.milton.justin.biology.neuron.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import edu.milton.justin.algorithms.RectangleAlgorithm;
import edu.milton.justin.misc.ResizeBufferedImage;

public class Vesicle implements RectangleAlgorithm, ResizeBufferedImage {

	int x;
	int y;
	public int dx = 3;
	public int dy = 2;
	int width;
	int height;
	JFrame f;

	int originalWidth;
	int originalHeight;

	public BufferedImage i;

	public ArrayList<Rectangle> rects;

	public Vesicle(int xs, int ys, int widths, int heights, JFrame fs) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		f = fs;

		try {
			i = ImageIO.read(new File("./resources/images/vesicle.png"));
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

	public void update() {
		x += dx;
		y += dy;

		for (Rectangle r : rects) {

			r.x += dx;
			r.y += dy;

		}

	}

	void shiftRectangles() {

		for (Rectangle r : rects) {
			r.x += x;
			r.y += y;

		}

	}

}
