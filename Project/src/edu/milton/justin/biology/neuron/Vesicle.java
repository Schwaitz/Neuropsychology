package edu.milton.justin.biology.neuron;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Vesicle {

	int x;
	int y;
	int width;
	int height;
	
	
	BufferedImage i;

	ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

	public Vesicle(int xs, int ys, int widths, int heights) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		
		
		
		try {
			i = ImageIO.read(new File("./resources/images/vesicle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
