package engine.handlers;

import java.awt.Rectangle;
import java.util.ArrayList;

public interface IntersectionHandler {

	default boolean handleIntersection() {
		return false;
	}

	default boolean handleIntersection(ArrayList<Rectangle> r1s,
			ArrayList<Rectangle> r2s) {

		for (Rectangle r1 : r1s) {
			for (Rectangle r2 : r2s) {

				if (r1.intersects(r2)) {

					return true;
				}
			}

		}

		return false;
	}

	default boolean handleIntersection(ArrayList<Rectangle> r1s, Rectangle r2) {

		for (Rectangle r : r1s) {

			if (r2.intersects(r)) {
				return true;
			}
		}

		return false;
	}

	default boolean handleIntersection(Rectangle r1, ArrayList<Rectangle> r2s) {

		for (Rectangle r : r2s) {

			if (r1.intersects(r)) {
				return true;
			}
		}

		return false;
	}

	default boolean handleIntersection(Rectangle r1, Rectangle r2) {

		if (r1.intersects(r2)) {
			return true;
		}

		return false;
	}

}
