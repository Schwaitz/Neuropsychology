package biology.neuron.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import biology.neuron.Neuron;
import misc.GlobalVariables;

public class ActionPotential {

	Neuron n;

	ArrayList<Rectangle> dRects = new ArrayList<Rectangle>();
	ArrayList<Rectangle> rdRects = new ArrayList<Rectangle>();

	public ActionPotential(Neuron ns) {

		n = ns;

		releaseAP();

	}

	public void releaseAP() {

		new Thread(new Runnable() {

			public void run() {

				for (int i = n.y; i < GlobalVariables.WY; i++) {

					for (Rectangle r : n.rects) {

						if (r.y == i) {

							Rectangle temp = new Rectangle(r.x, r.y, r.width, r.height + 5);
							dRects.add(temp);

							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							rdRects.add(temp);

						}

					}

					n.aps.remove(this);

				}
			}

		}).start();

	}

	public void draw(Graphics g) {

		g.setColor(Color.yellow);

		for (Rectangle r : dRects) {

			g.fillRect(r.x, r.y, r.width, r.height);

		}

	}

	public void update() {

		try {
			for (Rectangle r : rdRects) {
				dRects.remove(r);
			}
		} catch (Exception e) {

		}

	}

}
