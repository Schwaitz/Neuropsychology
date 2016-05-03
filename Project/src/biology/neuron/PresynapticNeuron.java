package biology.neuron;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algorithms.RectangleAlgorithm;
import biology.neuron.elements.ActionPotential;
import biology.neuron.elements.ExocytosisPoint;
import biology.neuron.elements.ReuptakePump;
import biology.neuron.elements.Vesicle;

public class PresynapticNeuron extends Neuron implements RectangleAlgorithm {

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

		shiftRectangles();

		startVesicleThread();

	}

	ReuptakePump createPump() {

		ReuptakePump returnPump = new ReuptakePump(110, 200, 50, 50);

		return returnPump;

	}

	ArrayList<ExocytosisPoint> createExits() {
		ArrayList<ExocytosisPoint> returnExits = new ArrayList<ExocytosisPoint>();

		returnExits.add(new ExocytosisPoint(45 + 71, 285, 60, 60, Color.red));
		returnExits.add(new ExocytosisPoint(145 + 71, 290, 60, 60, Color.red));
		returnExits.add(new ExocytosisPoint(258 + 71, 278, 60, 60, Color.red));

		return returnExits;

	}

	public void releaseVesicle() {

		vesicles.add(new Vesicle((int) (Math.random() * 50 + 135 + 71), 5, 30, 30, fr, this));
		aps.add(new ActionPotential(this));

	}

	void startVesicleThread() {

		new Thread(new Runnable() {

			public void run() {

				while (true) {

					releaseVesicle();

					try {
						Thread.sleep(releaseRate);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}

				}
			}
		}).start();
	}

}
