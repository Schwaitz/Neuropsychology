package biology.neuron.elements.receptor.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import biology.neurotransmitter.base.Neurotransmitter;
import misc.GlobalVariables;

public class Receptor {
	public int height;
	public String name;
	public String type;
	public int width;
	public int x;
	public int y;
	public Color color;

	public boolean mouseOver = false;

	public int index;

	public ArrayList<Neurotransmitter> boundNT = new ArrayList<Neurotransmitter>();

	public String neurotransmitterBindType;

	public Rectangle rect = new Rectangle(0, 0, 0, 0);

	public Receptor(String types, String names, String neurotransmitterBindTypes) {

		type = types;
		name = names;
		neurotransmitterBindType = neurotransmitterBindTypes;

	}

	public void draw(Graphics g) {

		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.setFont(new Font("Impact", 15, 15));

		if (mouseOver == true) {
			g.drawString(name, GlobalVariables.WX / 2 - 150, y + height + 30);
		}

	}

	public void update() {

		rect = new Rectangle(x, y, width, height);

	}

	public void startReleaseTimer(Neurotransmitter n) {

		new Thread(new Runnable() {
			public void run() {
				int count = 0;
				while (count < 100) {

					count++;
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				n.released = true;
				n.dy = (int) (Math.random() * -4 - 2);
				n.dx = (int) (Math.random() * 8 - 4);
			}
		}).start();

	}

}
