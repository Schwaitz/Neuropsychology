package biology.neurotransmitter;

import java.awt.Color;

import javax.swing.JFrame;

import biology.neuron.elements.Vesicle;
import biology.neurotransmitter.base.Neurotransmitter;

public class GABA extends Neurotransmitter {

	public GABA(String types, int xs, int ys, int widths, int heights,
			JFrame fs, Vesicle pointers, String bindReceptorTypes) {
		super(types, xs, ys, widths, heights, Color.magenta, fs, pointers,
				bindReceptorTypes);
		// TODO Auto-generated constructor stub
	}

	

}
