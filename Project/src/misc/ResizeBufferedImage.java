package misc;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface ResizeBufferedImage {

	default BufferedImage resize(BufferedImage i, int width, int height) {

		BufferedImage returnImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = returnImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.drawImage(i, 0, 0, width, height, null);
		g.dispose();
		return returnImage;
	}

}
