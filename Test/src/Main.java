import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends JFrame implements Runnable, MouseMotionListener {

	Thread thread;

	BufferedImage pre;
	BufferedImage post;

	int mx;
	int my;

	// ArrayList<Rectangle> irs;

	boolean lock = false;

	public static void main(String args[]) {

		new Main();

	}

	Main() {

		this.addMouseMotionListener(this);

		this.setVisible(true);
		this.setEnabled(true);
		this.setSize(355, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {

			pre = ImageIO.read(new File("./pre.png"));
			post = ImageIO.read(new File("./post.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		thread = new Thread(this);
		thread.start();

	}

	void render(Graphics g) {

		Image offscreen = createImage(this.getWidth(), this.getHeight());
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());

		bufferGraphics.drawImage(pre, 0, 0, this);
		bufferGraphics.drawImage(post, 0, 400, this);

		try {
			Color RGB = new Color(pre.getRGB(mx, my));
			System.out.println("Pre: " + "| R: " + RGB.getRed() + " | G: "
					+ RGB.getGreen() + " | B: " + RGB.getBlue() + " | A: "
					+ RGB.getAlpha());
		} catch (ArrayIndexOutOfBoundsException e) {
			// idgaf
		}

		try {
			Color RGB = new Color(post.getRGB(mx, my));
			System.out.println("Post: " + "| R: " + RGB.getRed() + " | G: "
					+ RGB.getGreen() + " | B: " + RGB.getBlue() + " | A: "
					+ RGB.getAlpha());
		} catch (ArrayIndexOutOfBoundsException e) {
			// idgaf
		}

		g.drawImage(offscreen, 0, 0, this);
	}

	boolean isIntersecting() {

		// for (Rectangle r : irs) {
		//
		// if (ball.rect.intersects(r)) {
		//
		// return true;
		//
		// }
		//
		// }

		return false;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// irs = IntersectionRectangles.getIntersectionRectangles();

		while (true) {

			render(this.getGraphics());

			try {
				thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		mx = e.getX();
		my = e.getY();

	}

}
