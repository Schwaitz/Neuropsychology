package edu.milton.justin.biology.neuron;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import edu.milton.justin.algorithms.RectangleAlgorithm;
import edu.milton.justin.biology.neuron.elements.Receptor;
import edu.milton.justin.biology.neuron.handlers.PostsynapticReceptorHandler;

public class PostsynapticNeuron extends Neuron implements
		PostsynapticReceptorHandler, RectangleAlgorithm {

	public PostsynapticNeuron(File fs, JFrame frs, int xs, int ys) {

		super(fs, frs, xs, ys);

		try {
			i = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rects = getRects(i, Color.black);

		receptors = createReceptors();

		shiftRectangles();

	}

	ArrayList<Receptor> createReceptors() {
		ArrayList<Receptor> returnReceptors = postsynapticReceptors;

		return returnReceptors;

	}

}
