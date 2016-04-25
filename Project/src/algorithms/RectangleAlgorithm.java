package algorithms;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import misc.ValueHolder;

public interface RectangleAlgorithm {

	default ArrayList<Rectangle> getRects(BufferedImage b, Color c) {

		ArrayList<Rectangle> returnList = new ArrayList<Rectangle>();

		for (int y = 0; y < b.getHeight(); y++) {

			ArrayList<Integer> indexes = new ArrayList<Integer>();
			int indexNum = 0;
			for (int x = 0; x < b.getWidth(); x++) {

				Color rgb = new Color(b.getRGB(x, y));

				int red = rgb.getRed();
				int green = rgb.getGreen();
				int blue = rgb.getBlue();
				int alpha = rgb.getAlpha();

				if (red == c.getRed() && green == c.getGreen()
						&& blue == c.getBlue()) {
					indexes.add(x);

					if ((x + 1) < b.getWidth()) {
						if (red == 0 && green == 0 && blue == 0) {

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
				returnList.add(new Rectangle(se.start, y, dif, 1));
			}

		}

		return returnList;
	}

}
