package engine.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import biology.neuron.Neuron;
import biology.neuron.PostsynapticNeuron;
import biology.neuron.PresynapticNeuron;
import biology.neuron.elements.Vesicle;
import biology.neuron.handlers.AutoReceptorHandler;
import biology.neuron.handlers.PostsynapticReceptorHandler;
import biology.neurotransmitter.Neurotransmitter;
import engine.handlers.IntersectionHandler;
import enums.BounceType;

public class SimulationFrame extends JFrame implements AutoReceptorHandler,
		PostsynapticReceptorHandler, IntersectionHandler {

	Neuron post;
	Neuron pre;

	public int WX;
	public int WY;

	ArrayList<Neurotransmitter> nt = new ArrayList<Neurotransmitter>();
	ArrayList<Neurotransmitter> rnt = new ArrayList<Neurotransmitter>();

	public SimulationFrame(int WXs, int WYs) {
		WX = WXs;
		WY = WYs;

		setupFrame();
		setupBiology();

	}

	void setupFrame() {

		this.setTitle("Simulation");
		this.setEnabled(true);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocation(600, 0);

		this.setSize(WX, WY);

	}

	void setupBiology() {

		File preFile = new File("./resources/images/PresynapticNeuron.png");
		pre = new PresynapticNeuron(preFile, this, 0, 0);

		File postFile = new File("./resources/images/PostsynapticNeuron.png");
		post = new PostsynapticNeuron(postFile, this, 0, 400);

		for (int n = 0; n < 20; n++) {
			for (int v = 0; v < 3; v++) {
				nt.add(new Neurotransmitter("Dopamine",
						pre.vesicles.get(v).x + 10, pre.vesicles.get(v).y + 10,
						3, 3, Color.blue, this, pre.vesicles.get(v)));
			}
		}

	}

	public void render(Graphics g) {

		Image offscreen = this.createImage(this.WX, this.WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, this.WX, this.WY);

		pre.draw(bufferGraphics);
		post.draw(bufferGraphics);

		for (Vesicle v : pre.vesicles) {

			v.draw(bufferGraphics);
		}

		for (Neurotransmitter n : nt) {

			n.draw(bufferGraphics);
		}

		g.drawImage(offscreen, 0, 0, this.WX, this.WY, this);

	}

	public void updateState() {

		checkIntersections();

		handleUpdates();

		handleRemoving();

	}

	void checkIntersections() {

		for (Vesicle v : pre.vesicles) {

			for (Vesicle v2 : pre.vesicles) {

				if (v != v2) {
					switch (handleIntersectionOutside(v.rect, v2.rect)) {

					case TOP:
						v.dy = -v.dy;
						break;
					case BOTTOM:
						v.dy = -v.dy;

						break;
					case LEFT:

						v.dx = -v.dx;

						break;
					case RIGHT:

						v.dx = -v.dx;

						break;

					case FALSE:
						// Do nothing
						break;

					}
				}

			}

			for (Rectangle r : v.rects) {
				for (Rectangle prer : pre.rects) {

					switch (handleIntersectionOutside(r, prer)) {

					case TOP:
						v.dy = -v.dy;
						v.dx = -v.dx;
						break;
					case BOTTOM:
						v.dy = -v.dy;
						v.dx = -v.dx;
						break;
					case LEFT:
						v.dy = -v.dy;
						v.dx = -v.dx;

						break;
					case RIGHT:
						v.dy = -v.dy;
						v.dx = -v.dx;

						break;

					case FALSE:
						// Do nothing
						break;

					}

					switch (handleIntersectionWall(v.rect, WX, WY)) {

					case TOP:
						v.dy = -v.dy;

						break;
					case BOTTOM:
						v.dy = -v.dy;

						break;
					case LEFT:

						v.dx = -v.dx;

						break;
					case RIGHT:

						v.dx = -v.dx;

						break;

					case FALSE:
						// Do nothing
						break;

					}

				}
			}

			for (Neurotransmitter n : nt) {

				switch (handleIntersectionInside(n.rect, v.x + 5, v.y + 5,
						v.width - 10, v.height - 10)) {

				case TOP:
					n.dy = -n.dy;
					n.y += 5;
					break;
				case BOTTOM:
					n.dy = -n.dy;
					n.y -= 5;
					break;
				case LEFT:
					n.dx = -n.dx;
					n.x += 5;
					break;
				case RIGHT:
					n.dx = -n.dx;
					n.x -= 5;
					break;

				case FALSE:
					// Do nothing
					break;

				}

			}

		}

		for (Neurotransmitter n : nt) {
			
			
			
			
			
			
			

			switch (handleIntersectionWall(n.rect, WX, WY)) {

			case TOP:
				n.dy = -n.dy;
				n.y += 5;
				break;
			case BOTTOM:
				n.dy = -n.dy;
				n.y -= 5;
				break;
			case LEFT:
				n.dx = -n.dx;
				n.x += 5;
				break;
			case RIGHT:
				n.dx = -n.dx;
				n.x -= 5;
				break;

			case FALSE:
				// Do nothing
				break;

			}
		}

	}

	void handleUpdates() {

		pre.update();
		post.update();

		for (Neurotransmitter n : nt) {
			n.update();
		}

	}

	void handleRemoving() {

		for (Neurotransmitter rn : rnt) {

			nt.remove(rn);
		}

	}

}
