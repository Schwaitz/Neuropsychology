package edu.milton.justin.engine.UIElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class StartSimulationButton {

	int x;
	int y;
	int width;
	int height;
	JFrame f;

	public Rectangle rect;
	Image i;

	public StartSimulationButton(int xs, int ys, int widths, int heights,
			JFrame fs) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		f = fs;

		rect = new Rectangle(x, y, width, height);

		try {
			i = ImageIO.read(new File("./resources/images/sim.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void draw(Graphics g) {

		g.drawImage(i, x, y, width, height, f);

	}

}
