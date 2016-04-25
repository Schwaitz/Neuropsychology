package edu.milton.justin.biology.neurotransmitter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Neurotransmitter {

	String type;
	int x;
	int y;
	int width;
	int height;
	Color color;
	
	Rectangle rect;

	public Neurotransmitter(String types, int xs, int ys, int widths,
			int heights, Color colors) {

		type = types;

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		color = colors;
		
		
		rect = new Rectangle(x,y,width,height);
	}
	
	public void draw(Graphics g){
		
		g.setColor(color);
		g.drawOval(x,y,width,height);
		
	}
	
	public void update(){
		
		
	}
	

}
