package biology.neuron.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import biology.neuron.elements.Receptor;

public interface PostsynapticReceptorHandler {

	File list = new File("./resources/files/Receptor List.txt");

	ArrayList<Receptor> postsynapticReceptors = readReceptors();

	static ArrayList<Receptor> readReceptors() {

		ArrayList<Receptor> returnReceptors = new ArrayList<Receptor>();
		String line = null;
		String type = null;

		try {

			FileReader fileReader = new FileReader(list);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				String[] array = line.split(":");

				type = array[0];

				for (int i = 1; i < array.length; i++) {

					returnReceptors.add(new Receptor(type, array[i]));

				}

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
