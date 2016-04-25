package edu.milton.justin.biology.neuron.handlers;

import java.io.File;
import java.util.ArrayList;

import edu.milton.justin.biology.neuron.AutoReceptor;

public interface AutoReceptorHandler {

	File list = new File("./resources/files/Neurotransmitter Receptor List.txt");

	ArrayList<AutoReceptor> presynapticAutoReceptors = createAutoReceptors();

	static ArrayList<AutoReceptor> createAutoReceptors() {
		
		
		ArrayList<AutoReceptor> returnAutoReceptors = null;

		return returnAutoReceptors;

	}

}
