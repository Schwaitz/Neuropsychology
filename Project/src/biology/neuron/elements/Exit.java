package biology.neuron.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Exit {

	int x;
	int y;
	int width;
	int height;
	Color color;

	public Rectangle rect;

	public Exit(int xs, int ys, int widths, int heights, Color colors) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		color = colors;

		rect = new Rectangle(x, y, width, height);

	}

	public void draw(Graphics g) {

		g.setColor(color);
		g.fillOval(x, y, width, height);
		g.setColor(Color.cyan);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);

	}

}
