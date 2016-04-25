package biology.neurotransmitter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Neurotransmitter {

	Color color;
	int dx = 0;
	int dy = 0;
	int height;
	Rectangle rect;
	String type;
	int width;
	int x;

	int y;

	public Neurotransmitter(String types, int xs, int ys, int widths,
			int heights, Color colors) {

		type = types;

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		color = colors;

		rect = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {

		g.setColor(color);
		g.drawOval(x, y, width, height);

	}

	public void update() {
		x += dx;
		y += dy;

		rect = new Rectangle(x, y, width, height);

	}

}
