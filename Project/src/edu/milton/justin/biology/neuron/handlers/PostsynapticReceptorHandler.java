package edu.milton.justin.biology.neuron.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.milton.justin.biology.neuron.elements.Receptor;
import edu.milton.justin.enums.ErrorType;

public interface PostsynapticReceptorHandler {

	File list = new File("./resources/files/Neurotransmitter Receptor List.txt");

	ArrayList<Receptor> postsynapticReceptors = readReceptors();

	static ArrayList<Receptor> readReceptors() {

		ArrayList<Receptor> returnReceptors = new ArrayList<Receptor>();
		String line = null;
		String type = null;
		String name = null;

		try {

			FileReader fileReader = new FileReader(list);

			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {

				if (line.charAt(0) == ':') {

					type = line.substring(1, line.length());

				} else if (line.charAt(0) != ':') {
					name = line;
				} else {
					System.out.println(ErrorType.ELSECHECK.getMessage());
				}

				returnReceptors.add(new Receptor(type, name));
			}

			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to find receptor list");
		} catch (IOException ex) {
			System.out.println("Error reading receptor list");
		}

		return returnReceptors;

	}

}
