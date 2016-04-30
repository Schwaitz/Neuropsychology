package engine.handlers;

import java.awt.Rectangle;

import enums.BounceType;

public interface IntersectionHandler {

	default BounceType handleIntersection() {
		return BounceType.FALSE;
	}

	default BounceType handleIntersectionOutside(Rectangle r1, Rectangle r2) {

		
		if (r1.intersects(r2)) {
			int x1 = r1.x;
			int w1 = r1.x + r1.width;
			int y1 = r1.y;
			int h1 = r1.y + r1.height;

			int x2 = r2.x;
			int w2 = r2.x + r2.width;
			int y2 = r2.y;
			int h2 = r2.y + r2.height;

			if (r1.intersects(r2)) {

				if (w1 > x2) {
					return BounceType.RIGHT;
				}

				if (w1 < w2) {
					return BounceType.LEFT;
				}

				if (h1 > y2) {
					return BounceType.TOP;
				}

				if (h1 < h2) {
					return BounceType.BOTTOM;

				}

			}
		}

		return BounceType.FALSE;
	}

	default BounceType handleIntersectionInside(Rectangle r, int x, int y,
			int WX, int WY) {

		if (r.x > x + WX) {
			return BounceType.RIGHT;
		}

		if (r.x < x) {
			return BounceType.LEFT;
		}

		if (r.y > y+ WY) {
			return BounceType.BOTTOM;
		}

		if (r.y < y) {
			return BounceType.TOP;
		}

		return BounceType.FALSE;
	}

	default BounceType handleIntersectionWall(Rectangle r, int WX, int WY) {

		if (r.x > WX) {
			return BounceType.RIGHT;
		}

		if (r.x < 0) {
			return BounceType.LEFT;
		}

		if (r.y > WY) {
			return BounceType.BOTTOM;
		}

		if (r.y < 0) {
			return BounceType.TOP;
		}

		return BounceType.FALSE;
	}

}
