package edu.milton.justin.biology.neuron;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import edu.milton.justin.biology.neuron.handlers.PostsynapticReceptorHandler;

public class PostsynapticNeuron extends Neuron implements PostsynapticReceptorHandler{

	public PostsynapticNeuron(File fs, JFrame frs) {

		super(fs, frs);

		try {
			i = ImageIO.read(new File("./resources/images/post.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		receptors = createReceptors();

	}
	
	
	
	ArrayList<Receptor> createReceptors(){
		ArrayList<Receptor> returnReceptors = postsynapticReceptors;
		
		
		return returnReceptors;
		
	}
	



}
