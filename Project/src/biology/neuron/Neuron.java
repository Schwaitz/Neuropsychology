package biology.neuron;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import biology.neuron.elements.AutoReceptor;
import biology.neuron.elements.Receptor;
import biology.neuron.elements.ReuptakePump;
import biology.neuron.elements.Vesicle;

public class Neuron {
	ArrayList<AutoReceptor> autoReceptors;
	File f;
	JFrame fr;
	public BufferedImage i;

	ReuptakePump pump;
	ArrayList<Receptor> receptors;
	public ArrayList<Rectangle> rects;
	public ArrayList<Vesicle> vesicles;

	int x;

	int y;

	public Neuron(File fs, JFrame frs, int xs, int ys) {

		f = fs;
		fr = frs;
		x = xs;
		y = ys;

		receptors = new ArrayList<Receptor>();
		autoReceptors = new ArrayList<AutoReceptor>();
		pump = null;
		vesicles = new ArrayList<Vesicle>();

	}

	public void draw(Graphics g) {

		g.drawImage(i, x, y, fr);

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

		for (Receptor r : receptors) {
			r.update();
		}

		for (AutoReceptor ar : autoReceptors) {
			ar.update();
		}

	}

}
