package edu.milton.justin.biology.neuron;

import java.util.ArrayList;

public class Neuron {

	int id;
	ArrayList<Receptor> receptors;
	ArrayList<Vesicle> vesicles;
	ReuptakePump pump;


	public Neuron(int ids, ArrayList<Receptor> receptorss,
			ArrayList<Vesicle> vesicless, ReuptakePump pumps) {

		id = ids;
		receptors = receptorss;
		vesicles = vesicless;
		pump = pumps;
		
	}

}
