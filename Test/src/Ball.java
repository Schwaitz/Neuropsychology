import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

	int x;
	int y;
	int width;
	int height;
	
	Color drawColor = Color.blue;

	int dx;
	int dy;

	Rectangle rect = new Rectangle(0, 0, 0, 0);

	public Ball(int xs, int ys, int widths, int heights) {

		x = xs;
		y = ys;
		width = widths;
		height = heights;

	}

	public void doStuff(Graphics g) {

		x += dx;
		y += dy;
		
		rect = new Rectangle(x, y, width, height);

		g.setColor(drawColor);
		
		g.fillOval(x, y, width, height);

	}
	
	public void handleBounds(){
		
		
		
	}

}
