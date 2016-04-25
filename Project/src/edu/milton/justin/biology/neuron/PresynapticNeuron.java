package edu.milton.justin.biology.neuron;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import edu.milton.justin.biology.neuron.handlers.AutoReceptorHandler;

public class PresynapticNeuron extends Neuron implements AutoReceptorHandler {

	public PresynapticNeuron(File fs, JFrame frs) {

		super(fs, frs);

		try {
			i = ImageIO.read(new File("./resources/images/pre.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pump = createPump();
		vesicles = createVesicles();
		autoReceptors = createAutoReceptors();

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

		ArrayList<Vesicle> returnVesicles = null;

		return returnVesicles;

	}

}
