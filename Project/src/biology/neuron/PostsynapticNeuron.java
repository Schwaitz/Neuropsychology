package biology.neuron;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algorithms.RectangleAlgorithm;
import biology.neuron.elements.Receptor;
import biology.neuron.handlers.PostsynapticReceptorHandler;

public class PostsynapticNeuron extends Neuron implements
		PostsynapticReceptorHandler, RectangleAlgorithm {

	public PostsynapticNeuron(JFrame frs, int xs, int ys) {

		super(frs, xs, ys);

		try {
			i = ImageIO.read(new File("./resources/images/PostsynapticNeuron.png"));
			iDraw = ImageIO.read(new File("./resources/images/PostsynapticNeuron_transparent.png"));
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
