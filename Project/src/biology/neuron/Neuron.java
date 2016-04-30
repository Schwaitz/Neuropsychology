package biology.neuron;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import biology.neuron.elements.ExocytosisPoint;
import biology.neuron.elements.ReuptakePump;
import biology.neuron.elements.Vesicle;
import biology.neuron.elements.receptor.base.AutoReceptor;
import biology.neuron.elements.receptor.base.Receptor;

public class Neuron {

	JFrame fr;
	public BufferedImage i;
	public BufferedImage iDraw;

	public ReuptakePump pump;
	public ArrayList<Receptor> receptors;
	public ArrayList<AutoReceptor> autoReceptors;
	public ArrayList<Rectangle> rects;
	public ArrayList<Vesicle> vesicles;
	public ArrayList<Vesicle> rVesicles;

	public ArrayList<ExocytosisPoint> exits;

	public ArrayList<Receptor> activeReceptors;

	int x;

	int y;

	public Neuron(JFrame frs, int xs, int ys) {


		fr = frs;
		x = xs;
		y = ys;

		exits = new ArrayList<ExocytosisPoint>();
		activeReceptors = new ArrayList<Receptor>();
		receptors = new ArrayList<Receptor>();
		autoReceptors = new ArrayList<AutoReceptor>();
		pump = null;
		vesicles = new ArrayList<Vesicle>();
		rVesicles = new ArrayList<Vesicle>();

	}

	public void draw(Graphics g) {

		g.drawImage(iDraw, x, y, fr);

		for (Receptor r : receptors) {

			r.draw(g);
		}

		for (AutoReceptor ar : autoReceptors) {

			ar.draw(g);
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

		for (Receptor r : receptors) {
			r.update();
		}

		for (AutoReceptor ar : autoReceptors) {
			ar.update();
		}

	}

}
