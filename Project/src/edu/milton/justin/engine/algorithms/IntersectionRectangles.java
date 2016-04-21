package edu.milton.justin.engine.algorithms;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.milton.justin.engine.Engine;
import edu.milton.justin.misc.ValueHolder;

public class IntersectionRectangles {

	static BufferedImage b = null;

	public static ArrayList<Rectangle> getIntersectionRectangles() {
		ArrayList<Rectangle> returnList = new ArrayList<Rectangle>();

		try {
			b = ImageIO.read(new File("./resources/images/cleft.png"));

			// b = ImageIO.read(new File("./resources/images/test.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {

				if (b.getRGB(x, y) != 16777215 && b.getRGB(x, y) != 0) {
					b.setRGB(x, y, -16777216);
				}

			}
		}

		for (int y = 0; y < b.getHeight(); y++) {

			ArrayList<Integer> indexes = new ArrayList<Integer>();
			int indexNum = 0;
			for (int x = 0; x < b.getWidth(); x++) {

				if (b.getRGB(x, y) == -16777216) {
					indexes.add(x);

					if ((x + 1) < b.getWidth()) {
						if (b.getRGB(x + 1, y) == 0
								|| b.getRGB(x + 1, y) == 16777215) {

							indexNum += 1;
						}
					}

				}

			}

			int count = 0;
			ArrayList<Integer> ends = new ArrayList<Integer>();
			ArrayList<Integer> starts = new ArrayList<Integer>();
			for (Integer i : indexes) {

				if (count == 0) {
					starts.add(i);
				}
				count++;

				if (indexes.indexOf(i) < (indexes.size() - 1)) {

					if (indexes.get(indexes.indexOf(i) + 1) != (i + 1)) {

						ends.add(indexes.get(indexes.indexOf(i)));
						count = 0;

					}
				}

				if (indexes.indexOf(i) == indexes.size() - 1) {

					ends.add(indexes.get(indexes.indexOf(i)));
				}

			}

			ArrayList<ValueHolder> startEnds = new ArrayList<ValueHolder>();
			

			for (int i = 0; i < ends.size(); i++) {

				startEnds.add(new ValueHolder(starts.get(i), ends.get(i)));

			}

			for (ValueHolder se : startEnds) {
				int dif = se.end - se.start;
				returnList.add(new Rectangle(se.start + Engine.synapseShiftX, y, dif, 1));
			}

		}
		

		return returnList;
	}

}