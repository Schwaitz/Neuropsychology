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

	public int dx = 0;
	public int dy = 0;
	JFrame f;

	public BufferedImage i;

	public Rectangle rect;
	public ArrayList<Rectangle> rects;
	public int width;
	public int height;
	public int x;

	public int y;

	public Vesicle(int xs, int ys, int widths, int heights, JFrame fs) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		f = fs;
		
		
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

		g.drawImage(i, x, y, width, height, f);
		g.setColor(Color.red);
	//	g.drawRect(rect.x, rect.y, rect.width, rect.height);

	}

	void shiftRectangles() {

		for (Rectangle r : rects) {
			r.x += x;
			r.y += y;

		}

	}
	
	public void shiftRectangles(int amountX, int amountY){
		
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

}
