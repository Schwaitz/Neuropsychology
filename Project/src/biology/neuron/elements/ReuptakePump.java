package biology.neuron.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ReuptakePump {

	int height;
	int width;
	int x;
	int y;

	public Rectangle rect;

	public ReuptakePump(int xs, int ys, int widths, int heights) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;

<<<<<<< HEAD
		rect = new Rectangle(x + 6, y + 6, width - 12, height - 12);
=======
		rect = new Rectangle(x, y, width, height);
>>>>>>> d1099c6b2f314f1866427c906b94c0dafd29a60a

	}

	public void draw(Graphics g) {

		g.setColor(Color.cyan);
		g.fillOval(x, y, width, height);

	}

}
