package biology.neuron;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algorithms.RectangleAlgorithm;
import biology.neuron.elements.ExocytosisPoint;
import biology.neuron.elements.ReuptakePump;
import biology.neuron.elements.Vesicle;
import biology.neuron.elements.receptor.base.AutoReceptor;
import biology.neuron.handlers.AutoReceptorHandler;

public class PresynapticNeuron extends Neuron implements AutoReceptorHandler,
		RectangleAlgorithm {

	public PresynapticNeuron(JFrame frs, int xs, int ys) {

		super(frs, xs, ys);

		try {
			i = ImageIO.read(new File("./resources/images/PresynapticNeuron.png"));
			iDraw = ImageIO.read(new File("./resources/images/PresynapticNeuron_transparent.png"));
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

		returnVesicles.add(new Vesicle(85+71, 150, 30, 30, fr, this));
		returnVesicles.add(new Vesicle(165+71, 150, 30, 30, fr, this));
		returnVesicles.add(new Vesicle(240+71, 150, 30, 30, fr, this));

		return returnVesicles;

	}

	ArrayList<ExocytosisPoint> createExits() {
		ArrayList<ExocytosisPoint> returnExits = new ArrayList<ExocytosisPoint>();

		returnExits.add(new ExocytosisPoint(45 + 71, 285, 60, 60, Color.red));
		returnExits.add(new ExocytosisPoint(145 + 71, 290, 60, 60, Color.red));
		returnExits.add(new ExocytosisPoint(258 + 71, 278, 60, 60, Color.red));

		return returnExits;

	}

}
