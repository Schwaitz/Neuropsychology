package biology.neuron.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ExocytosisPoint {

	public int x;
	public int y;
	public int width;
	public int height;
	Color color;

	public Rectangle rect;

	public ExocytosisPoint(int xs, int ys, int widths, int heights, Color colors) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		color = colors;

		rect = new Rectangle(x + 10, y + 10, width - 20, height - 20);

	}

	public void draw(Graphics g) {

		g.setColor(color);
		g.fillOval(x, y, width, height);

	}

}
