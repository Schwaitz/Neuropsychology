package biology.neuron.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Receptor {
	public int height;
	public String name;
	public String type;
	public int width;
	public int x;
	public int y;
	public Color color;
	
	public Rectangle rect = new Rectangle(0,0,0,0);
	
	public Receptor(String types, String names) {

		type = types;
		name = names;

	}
	
	
	
	public void draw(Graphics g) {

		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.setFont(new Font("Impact", 10, 10));
		g.drawString(name, x - 10, y + height + 20);
		

	}
	
	public void update() {

		rect = new Rectangle(x,y,width,height);
		
	}


}
