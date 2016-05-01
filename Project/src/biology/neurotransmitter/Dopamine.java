package biology.neurotransmitter;

import java.awt.Color;

import javax.swing.JFrame;

import biology.neuron.elements.Vesicle;
import biology.neurotransmitter.base.Neurotransmitter;

public class Dopamine extends Neurotransmitter {

	public Dopamine(String types, int xs, int ys, int widths, int heights,
			JFrame fs, Vesicle pointers, String bindReceptorTypes) {
<<<<<<< HEAD
		super(types, xs, ys, widths, heights, new Color(170,100,20), fs, pointers,
=======
		super(types, xs, ys, widths, heights, Color.orange, fs, pointers,
>>>>>>> d1099c6b2f314f1866427c906b94c0dafd29a60a
				bindReceptorTypes);
		// TODO Auto-generated constructor stub
	}

}
