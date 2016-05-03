package biology.neuron.elements;

import java.awt.Color;
import java.awt.Graphics;

public class ActionPotential {

	int x;
	int y;
	int width;
	int height;
	int dx;
	int dy;

	public ActionPotential(int xs, int ys, int widths, int heights) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;
		dx = 0;
		dy = 3;

	}

	public void draw(Graphics g) {

		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);

	}

	public void update() {
		x += dx;
		y += dy;

	}

}
