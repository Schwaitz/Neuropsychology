package edu.milton.justin.biology.neuron;

import edu.milton.justin.biology.neuron.handlers.ReceptorHandler;

public class Receptor implements ReceptorHandler {

	public String type;
	public String name;

	public Receptor(String types, String names) {

		type = types;
		name = names;

	}

}
