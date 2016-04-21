import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends JFrame implements Runnable, KeyListener {

	Thread thread;

	BufferedImage b;

	Ball ball;

	static int shift = 125;

	ArrayList<Rectangle> irs;

	boolean lock = false;

	public static void main(String args[]) {

		new Main();

	}

	Main() {

		this.setVisible(true);
		this.setEnabled(true);
		this.setSize(600, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			b = ImageIO.read(new File("./cleft.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ball = new Ball(300, 200, 10, 10);

		this.addKeyListener(this);

		thread = new Thread(this);
		thread.start();

	}

	void render(Graphics g) {

		Image offscreen = createImage(this.getWidth(), this.getHeight());
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());

		ball.doStuff(bufferGraphics);

		bufferGraphics.drawImage(b, shift, 0, this);

		g.drawImage(offscreen, 0, 0, this);
	}

	boolean isIntersecting() {

		for (Rectangle r : irs) {

			if (ball.rect.intersects(r)) {

				return true;

			}

		}

		return false;

	}

	public BufferedImage snapshot() {
		BufferedImage returnImage = (BufferedImage) createImage(getWidth(),
				getHeight());
		return returnImage;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		irs = IntersectionRectangles.getIntersectionRectangles();

		while (true) {

			render(this.getGraphics());

			if (isIntersecting()) {

				ball.drawColor = Color.red;
			} else {
				ball.drawColor = Color.blue;
			}

			try {
				thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {

		case KeyEvent.VK_A:
			ball.dx = -5;
			break;

		case KeyEvent.VK_D:
			ball.dx = 5;
			break;

		case KeyEvent.VK_W:
			ball.dy = -5;
			break;

		case KeyEvent.VK_S:
			ball.dy = 5;
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {

		case KeyEvent.VK_A:
			ball.dx = 0;
			break;

		case KeyEvent.VK_D:
			ball.dx = 0;
			break;

		case KeyEvent.VK_W:
			ball.dy = 0;
			break;

		case KeyEvent.VK_S:
			ball.dy = 0;
			break;

		}

	}

}
