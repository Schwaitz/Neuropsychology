package engine.frames;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import engine.Engine;
import engine.UIElements.StartSimulationButton;
import enums.ErrorMessageType;

public class MainFrame extends JFrame implements MouseListener {

	boolean loading = true;
	StartSimulationButton sim;
	BufferedImage background;

	public int WX;
	public int WY;

	public MainFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;
		setupFrame();

		try {
			background = ImageIO.read(new File(
					"./resources/images/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void setupFrame() {

		this.addMouseListener(this);
		this.setTitle("Simulator Menu");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int simFactor = 5;

		sim = new StartSimulationButton((WX / 2) - ((60 * simFactor) / 2),
				(WY / 2) - ((30 * simFactor) / 2) + 150, 60 * simFactor,
				30 * simFactor, this);

		this.setSize(WX, WY);
	}

	public void render(Graphics g) {

		try {
			Image offscreen = this.createImage(WX, WY);
			Graphics bufferGraphics = offscreen.getGraphics();

			bufferGraphics.clearRect(0, 0, WX, WY);
			bufferGraphics.drawImage(background, 0, 0, this);

			if (loading == true) {

				bufferGraphics.setFont(new Font("Impact", 40, 40));
				bufferGraphics.drawString("Loading...", WX / 2 - 50,
						WY / 2 - 75);
				bufferGraphics.drawRect(20, WX / 2 - (30 / 2), WX - 40, 30);

				loading = false;

			}

			else if (loading == false) {
				bufferGraphics.drawImage(background, 0, 0, this);
				sim.draw(bufferGraphics);

			}

			else {
				System.out.println(ErrorMessageType.ELSECHECK.getMessage());
			}

			g.drawImage(offscreen, 0, 0, WX, WY, this);

		} catch (Exception e) {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		if (sim.rect.contains(e.getPoint())) {

			Engine.sFrame = new SimulationFrame(355, 600);

			this.setVisible(false);
			this.setEnabled(false);
			this.dispose();
			Engine.mFrame = null;

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
