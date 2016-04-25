package edu.milton.justin.biology.neuron;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Neuron {
	File f;
	JFrame fr;
	
	ArrayList<Receptor> receptors;
	ArrayList<AutoReceptor> autoReceptors;
	ReuptakePump pump;
	ArrayList<Vesicle> vesicles;
	
	
	public BufferedImage i;

	ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

	public Neuron(File fs, JFrame frs) {

		f = fs;
		fr = frs;

	}
	
	
	public void draw(Graphics g){
		
		g.drawImage(i, 0, 0, fr);
		
	}

}
