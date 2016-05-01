package engine.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import engine.Engine;
import misc.GlobalVariables;

public class OptionsFrame extends JFrame {

	public int WX = 300;
	public int WY = 300;

	public static JRadioButton debugOff;
	public static JRadioButton debugOn;
	public static ButtonGroup debugGroup;

	public static JTextField vesicleRate;

	int seconds = 1000;
	int minutes = seconds * 60;

	public OptionsFrame() {

		setupFrame();
		setupOptionElements();

		this.pack();
	}

	void setupFrame() {

		this.setTitle("Options Menu");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation(GlobalVariables.WX + 10, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

	}

	void setupOptionElements() {

		JPanel rates = new JPanel();

		JLabel pvrLabel = new JLabel("Vesicle Creation Rate: ");
		JLabel svrLabel = new JLabel(" vesicles per minute");

		vesicleRate = new JTextField(3);
		vesicleRate.setEditable(true);
		vesicleRate.setText("15");

		rates.add(pvrLabel);
		rates.add(vesicleRate);
		rates.add(svrLabel);

		this.add(rates, BorderLayout.PAGE_START);

		JPanel debug = new JPanel();

		debug.setLayout(new GridLayout(1, 2));

		debugGroup = new ButtonGroup();

		debugOff = new JRadioButton("Debug Mode: Off");
		debugOn = new JRadioButton("Debug Mode: On");

		debugGroup.add(debugOff);
		debugGroup.add(debugOn);
		debugGroup.setSelected(debugOff.getModel(), true);

		debug.add(debugOff);
		debug.add(debugOn);

		this.add(debug, BorderLayout.PAGE_END);

	}

	void passData() {

		int value = Integer.valueOf(vesicleRate.getText());
		
		int rate = minutes / value;
		
		
		
		Engine.sFrame.pre.releaseRate = rate;
		

		if (debugGroup.isSelected(debugOff.getModel())) {

			Engine.sFrame.drawRectangles = false;
		} else {
			Engine.sFrame.drawRectangles = true;
		}

	}

}
