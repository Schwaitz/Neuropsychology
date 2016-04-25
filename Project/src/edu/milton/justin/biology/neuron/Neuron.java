package edu.milton.justin.biology.neuron;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.milton.justin.biology.neuron.elements.AutoReceptor;
import edu.milton.justin.biology.neuron.elements.Receptor;
import edu.milton.justin.biology.neuron.elements.ReuptakePump;
import edu.milton.justin.biology.neuron.elements.Vesicle;

public class Neuron {
	File f;
	JFrame fr;
	int x;
	int y;

	ArrayList<Receptor> receptors;
	ArrayList<AutoReceptor> autoReceptors;
	ReuptakePump pump;
	public ArrayList<Vesicle> vesicles;

	public BufferedImage i;

	public ArrayList<Rectangle> rects;

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
