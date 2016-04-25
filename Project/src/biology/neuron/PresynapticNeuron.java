package biology.neuron;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algorithms.RectangleAlgorithm;
import biology.neuron.elements.AutoReceptor;
import biology.neuron.elements.ReuptakePump;
import biology.neuron.elements.Vesicle;
import biology.neuron.handlers.AutoReceptorHandler;

public class PresynapticNeuron extends Neuron implements AutoReceptorHandler, RectangleAlgorithm {

	public PresynapticNeuron(File fs, JFrame frs, int xs, int ys) {

		super(fs, frs, xs, ys);

		try {
			i = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rects = getRects(i, Color.black);

		pump = createPump();
		vesicles = createVesicles();
		autoReceptors = createAutoReceptors();

		shiftRectangles();

	}

	ArrayList<AutoReceptor> createAutoReceptors() {

		ArrayList<AutoReceptor> returnReceptors = presynapticAutoReceptors;

		return returnReceptors;

	}

	ReuptakePump createPump() {

		ReuptakePump returnPump = null;

		return returnPump;

	}

	ArrayList<Vesicle> createVesicles() {

		ArrayList<Vesicle> returnVesicles = new ArrayList<Vesicle>();

		returnVesicles.add(new Vesicle(150, 100, 25, 25, fr));

		return returnVesicles;

	}

}
