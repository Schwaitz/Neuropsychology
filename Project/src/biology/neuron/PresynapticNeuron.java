package biology.neuron;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algorithms.RectangleAlgorithm;
import biology.neuron.elements.AutoReceptor;
import biology.neuron.elements.Exit;
import biology.neuron.elements.ReuptakePump;
import biology.neuron.elements.Vesicle;
import biology.neuron.handlers.AutoReceptorHandler;

public class PresynapticNeuron extends Neuron implements AutoReceptorHandler,
		RectangleAlgorithm {

	public PresynapticNeuron(File fs, JFrame frs, int xs, int ys) {

		super(fs, frs, xs, ys);

		try {
			i = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		exits = createExits();

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

		returnVesicles.add(new Vesicle(85, 150, 30, 30, fr));
		returnVesicles.add(new Vesicle(165, 150, 30, 30, fr));
		returnVesicles.add(new Vesicle(240, 150, 30, 30, fr));

		return returnVesicles;

	}

	ArrayList<Exit> createExits() {
		ArrayList<Exit> returnExits = new ArrayList<Exit>();

		returnExits.add(new Exit(45, 285, 60, 60, Color.red));
		returnExits.add(new Exit(145, 290, 60, 60, Color.red));
		returnExits.add(new Exit(258, 278, 60, 60, Color.red));

		return returnExits;

	}

}
