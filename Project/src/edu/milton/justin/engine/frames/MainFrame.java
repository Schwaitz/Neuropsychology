package edu.milton.justin.engine.frames;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import edu.milton.justin.engine.Engine;

public class MainFrame extends JFrame implements MouseListener {

	JPanel options = new JPanel();

	public JPanel canvas = new JPanel();

	public JSlider level = new JSlider(1, 3);
	int startLevel = 1;

	int cWX;
	int cWY;

	Rectangle startRect;

	Dictionary<Integer, JLabel> levelDictionary = new Hashtable<Integer, JLabel>();

	public int WX;
	public int WY;
	
	boolean loaded = false;

	public MainFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;
		this.addMouseListener(this);
		setupFrame();
		
		loaded = true;

	}

	void setupFrame() {

		this.setTitle("Main Frame");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		setupCanvas();
		setupPanel();

		this.setSize(WX, WY);
	}

	void setupCanvas() {

		this.add(canvas, BorderLayout.CENTER);

	}

	void setupPanel() {

		setupLevel();

		this.add(options, BorderLayout.SOUTH);

	}

	void setupLevel() {

		level.setValue(startLevel);
		level.setSnapToTicks(true);

		levelDictionary.put(1, new JLabel("Brain"));
		levelDictionary.put(2, new JLabel("Neuron"));
		levelDictionary.put(3, new JLabel("Synapse"));

		level.setLabelTable(levelDictionary);
		level.setPaintLabels(true);

		options.add(level);

	}

	public int getLevelValue() {

		return level.getValue();

	}

	public void render(Graphics g) {

		cWX = canvas.getWidth();
		cWY = canvas.getHeight();
		startRect = new Rectangle(cWX / 2 - 150, cWY / 2 - 75, 300, 150);

		if (cWX != 0 && cWY != 0) {

			Image offscreen = canvas.createImage(cWX, cWY);
			Graphics bufferGraphics = offscreen.getGraphics();

			bufferGraphics.clearRect(0, 0, cWX, cWY);

			if (this.getLevelValue() == 1) {

				bufferGraphics.drawImage(Engine.brain, 100, 100, 90 * 4,
						72 * 4, canvas);

			}

			if (this.getLevelValue() == 2) {

				int nWidth = 50 * 4;
				int nHeight = 72 * 4;

				bufferGraphics.drawImage(Engine.neuron, 50, 50, nWidth,
						nHeight, canvas);

				bufferGraphics.drawImage(Engine.neuron, nWidth, nHeight + 25,
						nWidth, nHeight, canvas);

				Graphics2D g2d = (Graphics2D) bufferGraphics;

				g2d.setStroke(new BasicStroke(3));

				g2d.drawOval(nWidth, nHeight, 50, 50);

				g2d.drawLine(nWidth + 50, nHeight + 25, nWidth + 125,
						nHeight - 25);

				g2d.setStroke(new BasicStroke(1));

				bufferGraphics.setFont(new Font("Impact", 20, 20));

				bufferGraphics
						.drawString("Synapse", nWidth + 130, nHeight - 30);

			}
			if (this.getLevelValue() == 3) {

				bufferGraphics.setColor(Color.blue);
				bufferGraphics.setFont(new Font("Impact", 50, 50));
				bufferGraphics.drawString("Press the \"Start\" button",
						cWX / 2 - 250, cWY / 2 - 200);
				bufferGraphics.drawString("to start the Simulation.",
						cWX / 2 - 250, cWY / 2 + 60 - 200);

				bufferGraphics.setColor(Color.blue);
				bufferGraphics.fillRect(cWX / 2 - 150, cWY / 2 - 75, 300, 150);

				Graphics2D g2d = (Graphics2D) bufferGraphics;

				g2d.setStroke(new BasicStroke(5));

				bufferGraphics.setColor(Color.black);
				g2d.drawRect(cWX / 2 - 150, cWY / 2 - 75, 300, 150);
				g2d.setStroke(new BasicStroke(1));

				bufferGraphics.setColor(Color.white);
				bufferGraphics.drawString("Start", cWX / 2 - 45, cWY / 2 + 15);

			}

			g.drawImage(offscreen, 0, 0, cWX, cWY, canvas);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		if (startRect.contains(e.getPoint())) {

			if (Engine.sFrame == null) {
				Engine.sFrame = new SimulationFrame(Engine.sWX, Engine.sWY);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
}
