package biology.neuron;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import biology.neuron.elements.ActionPotential;
import biology.neuron.elements.ExocytosisPoint;
import biology.neuron.elements.ReuptakePump;
import biology.neuron.elements.Vesicle;
import biology.neuron.elements.receptor.base.Receptor;

public class Neuron {

	JFrame fr;
	public BufferedImage i;
	public BufferedImage iDraw;

	public ReuptakePump pump;
	public ArrayList<Receptor> receptors;
	public ArrayList<Rectangle> rects;
	public ArrayList<Vesicle> vesicles;
	public ArrayList<Vesicle> rVesicles;

	public ArrayList<ExocytosisPoint> exits;
	public ArrayList<ActionPotential> aps;

	public ArrayList<Receptor> activeReceptors;

	public int x;

	public int y;

	public static int releaseRate = 5000;

	public Neuron(JFrame frs, int xs, int ys) {

		fr = frs;
		x = xs;
		y = ys;

		exits = new ArrayList<ExocytosisPoint>();
		aps = new ArrayList<ActionPotential>();
		activeReceptors = new ArrayList<Receptor>();
		receptors = new ArrayList<Receptor>();
		pump = new ReuptakePump(0, 0, 0, 0);
		vesicles = new ArrayList<Vesicle>();
		rVesicles = new ArrayList<Vesicle>();

	}

	public void draw(Graphics g) {

		g.drawImage(iDraw, x, y, fr);

		try {
			for (Vesicle v : vesicles) {
				v.draw(g);
			}

			for (Receptor ar : activeReceptors) {
				ar.draw(g);
			}

			for (ExocytosisPoint ep : exits) {
				ep.draw(g);
			}

			for (ActionPotential ap : aps) {

				ap.draw(g);
			}

			pump.draw(g);
		} catch (Exception e) {

		}

	}

	void shiftRectangles() {

		for (Rectangle r : rects) {
			r.x += x;
			r.y += y;
		}

	}

	public void update() {

		for (Vesicle v : vesicles) {
			v.update();
		}

		for (Vesicle v : rVesicles) {
			v.rects.clear();
			vesicles.remove(v);

		}

		
		for(ActionPotential ap : aps){
			
			ap.update();
		}

	}

}
