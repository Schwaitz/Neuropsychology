package biology.neurotransmitter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import biology.neuron.elements.Vesicle;

public class Neurotransmitter implements MouseMotionListener{

	Color color;
	public int dx;
	public int dy;
	int height;
	public Rectangle rect;
	String type;
	int width;
	public int x;
	public int y;
	
	
	public int prx;
	public int pry;
	public int prw;
	public int prh;
	
	public Vesicle pointer;
	
	JFrame f;
	
	
	int mouseX = 0;
	int mouseY = 0;

	public Neurotransmitter(String types, int xs, int ys, int widths,
			int heights, Color colors, JFrame fs, Vesicle pointers) {

		type = types;
		x = xs;
		y = ys;
		width = widths;
		height = heights;
		color = colors;
		f = fs;
		pointer = pointers;
		
		prx = pointer.x;
		pry = pointer.y;
		prw = pointer.x + pointer.width;
		prh = pointer.y + pointer.height;
		
		f.addMouseMotionListener(this);

		
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
		

		rect = new Rectangle(x, y, width, height);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

}
