package biology.neuron;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import algorithms.RectangleAlgorithm;
import biology.neuron.elements.receptor.base.Receptor;
import biology.neuron.handlers.PostsynapticReceptorHandler;

public class PostsynapticNeuron extends Neuron implements PostsynapticReceptorHandler, RectangleAlgorithm {

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

		activeReceptors = activateReceptors();

	}

	ArrayList<Receptor> createReceptors() {
		ArrayList<Receptor> returnReceptors = postsynapticReceptors;

		return returnReceptors;

	}

	ArrayList<Receptor> activateReceptors() {
		ArrayList<Receptor> returnAReceptors = new ArrayList<Receptor>();

		int indexD = 0;

		int base = 30 + 71;
		int incAmount = 0;
		for (Receptor r : this.receptors) {

			if (r.type.equals("DOPAMINE")) {

				r.x = base + 4 * indexD + incAmount * 0;
				r.index = indexD;
				r.y = y - 10;
				r.width = 3;
				r.height = 14;

				r.color = new Color(170, 100, 20);

				r.rect = new Rectangle(r.x, r.y, r.width, r.height);

				returnAReceptors.add(r);

				indexD++;

			}
		}
		int indexGA = 0;
		incAmount = 30;
		for (Receptor r : this.receptors) {

			if (r.type.equals("GABA")) {
				r.x = base + 4 * indexGA + incAmount;
				r.index = indexGA;
				r.y = y - 10;
				r.width = 3;
				r.height = 14;
				r.color = Color.magenta;
				r.rect = new Rectangle(r.x, r.y, r.width, r.height);

				returnAReceptors.add(r);

				indexGA++;

			}
		}
		incAmount = 90;
		int indexS = 0;
		for (Receptor r : this.receptors) {

			if (r.type.equals("SEROTONIN")) {

				r.x = base + 4 * indexS + incAmount;
				r.index = indexS;
				r.y = y - 10;
				r.width = 3;
				r.height = 14;
				r.color = Color.red;
				r.rect = new Rectangle(r.x, r.y, r.width, r.height);

				returnAReceptors.add(r);

				indexS++;

			}
		}
		int indexGL = 0;
		incAmount = 130;
		for (Receptor r : this.receptors) {

			if (r.type.equals("GLUTAMATE")) {

				r.x = base + 4 * indexGL + incAmount;
				r.index = indexGL;
				r.y = y - 10;
				r.width = 3;
				r.height = 14;
				r.color = Color.green;
				r.rect = new Rectangle(r.x, r.y, r.width, r.height);

				returnAReceptors.add(r);

				indexGL++;

			}
		}
		int indexN = 0;
		incAmount = 200;
		for (Receptor r : this.receptors) {

			if (r.type.equals("NOREPINEPHRINE")) {

				r.x = base + 4 * indexN + incAmount;
				r.index = indexN;
				r.y = y - 10;
				r.width = 3;
				r.height = 14;
				r.color = Color.pink;
				r.rect = new Rectangle(r.x, r.y, r.width, r.height);

				returnAReceptors.add(r);

				indexN++;

			}
		}
		int indexA = 0;
		incAmount = 250;
		for (Receptor r : this.receptors) {

			if (r.type.equals("ACETYLCHOLINE")) {

				r.x = base + 4 * indexA + incAmount;
				r.index = indexA;
				r.y = y - 10;
				r.width = 3;
				r.height = 14;
				r.color = new Color(200, 255, 100);
				r.rect = new Rectangle(r.x, r.y, r.width, r.height);

				returnAReceptors.add(r);

				indexA++;

			}
		}

		return returnAReceptors;
	}

}
