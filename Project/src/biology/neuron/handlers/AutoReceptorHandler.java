package biology.neuron.handlers;

import java.io.File;
import java.util.ArrayList;

import biology.neuron.elements.receptor.base.AutoReceptor;

public interface AutoReceptorHandler {

	File list = new File("./resources/files/Receptor List.txt");

	ArrayList<AutoReceptor> presynapticAutoReceptors = createAutoReceptors();

	static ArrayList<AutoReceptor> createAutoReceptors() {
		
		
		ArrayList<AutoReceptor> returnAutoReceptors = new ArrayList<AutoReceptor>();

		return returnAutoReceptors;

	}

}
