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
import biology.neuron.elements.Exit;
import biology.neuron.elements.Receptor;
import biology.neuron.elements.Vesicle;
import biology.neuron.handlers.AutoReceptorHandler;
import biology.neuron.handlers.PostsynapticReceptorHandler;
import biology.neurotransmitter.Neurotransmitter;
import engine.handlers.IntersectionHandler;

public class SimulationFrame extends JFrame implements AutoReceptorHandler,
		PostsynapticReceptorHandler, IntersectionHandler {

	Neuron post;
	Neuron pre;

	public int WX;
	public int WY;

	boolean drawRectangles = false;

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

		this.setLocation(0, 0);

		this.setSize(WX, WY);

	}

	void setupBiology() {

		File preFile = new File("./resources/images/PresynapticNeuron.png");
		pre = new PresynapticNeuron(preFile, this, 0, 0);

		File postFile = new File("./resources/images/PostsynapticNeuron.png");
		post = new PostsynapticNeuron(postFile, this, 0, 400);

		for (int n = 0; n < 10; n++) {
			for (int v = 0; v < 3; v++) {
				nt.add(new Neurotransmitter("Dopamine",
						pre.vesicles.get(v).x + 15, pre.vesicles.get(v).y + 15,
						3, 3, Color.blue, this, pre.vesicles.get(v)));
			}
		}
		int index = 0;

		for (Receptor r : post.receptors) {
			
			
			System.out.println(r.type + " | " + r.name);

			if (r.type.equals("DOPAMINE")) {

				r.x = 45 + 60 * index;
				r.y = 390;
				r.width = 30;
				r.height = 30;
				r.color = Color.green;

				post.activeReceptors.add(r);

				index++;

			}

		}

	}

	public void render(Graphics g) {

		Image offscreen = this.createImage(this.WX, this.WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, this.WX, this.WY);

		bufferGraphics.setColor(Color.white);
		bufferGraphics.fillRect(0, 0, WX, WY);

		pre.draw(bufferGraphics);
		post.draw(bufferGraphics);

		for (Vesicle v : pre.vesicles) {

			v.draw(bufferGraphics);
		}

		for (Exit e : pre.exits) {

			e.draw(bufferGraphics);
		}

		for (Neurotransmitter n : nt) {

			n.draw(bufferGraphics);
		}

		for (Receptor r : post.activeReceptors) {

			r.draw(bufferGraphics);
		}

		bufferGraphics.setColor(Color.cyan);
		
		
		if (drawRectangles == true) {
//			for (Rectangle r : pre.rects) {
//				bufferGraphics.drawRect(r.x, r.y, r.width, r.height);
//
//			}
//
//			for (Vesicle v : pre.vesicles) {
//
//				for (Rectangle r : v.rects) {
//					bufferGraphics.drawRect(r.x, r.y, r.width, r.height);
//				}
//			}
//
//			for (Neurotransmitter n : nt) {
//
//				bufferGraphics.drawRect(n.rect.x, n.rect.y, n.rect.width,
//						n.rect.height);
//			}

		}

		g.drawImage(offscreen, 0, 0, this.WX, this.WY, this);

	}

	public void updateState() {

		checkWallIntersections();
		vesicleIntersections();
		neurotransmitterIntersections();

		handleUpdates();

		handleRemoving();

	}

	void checkWallIntersections() {

	}

	void vesicleIntersections() {

		for (Vesicle v : pre.vesicles) {

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
				}

				for (Exit e : pre.exits) {
					switch (handleIntersectionOutside(r, e.rect)) {

					case TOP:
						v.releaseTransmitter = true;
						System.out.println("e X v");

						break;
					case BOTTOM:
						v.releaseTransmitter = true;
						System.out.println("e X v");

						break;
					case LEFT:
						v.releaseTransmitter = true;
						System.out.println("e X v");

						break;
					case RIGHT:
						v.releaseTransmitter = true;
						System.out.println("e X v");
						break;

					case FALSE:
						// Do nothing
						break;

					}
				}

				for (Vesicle v2 : pre.vesicles) {

					for (Rectangle r2 : v2.rects) {

						if (r != r2) {
							switch (handleIntersectionOutside(r, r2)) {

							case TOP:
								v.dy = -v.dy;
								v2.dy = -v2.dy;
								break;
							case BOTTOM:
								v.dy = -v.dy;
								v2.dy = -v2.dy;

								break;
							case LEFT:

								v.dx = -v.dx;
								v2.dx = -v2.dx;
								break;
							case RIGHT:

								v.dx = -v.dx;
								v2.dx = -v2.dx;

								break;

							case FALSE:
								// Do nothing
								break;

							}
						}
					}
				}
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

	void neurotransmitterIntersections() {

		for (Neurotransmitter n : nt) {

			if (n.pointer.releaseTransmitter == false) {

				if (n.x > n.prw) {
					n.x = n.prw - 1;
					n.dx = -n.dx;
				}
				if (n.x < n.prx) {
					n.x = n.prx + 1;
					n.dx = -n.dx;
				}

				if (n.y > n.prh) {
					n.y = n.prh - 1;
					n.dy = -n.dy;
				}
				if (n.y < n.pry) {
					n.y = n.pry + 1;
					n.dy = -n.dy;
				}

			}

			for (Rectangle r : pre.rects) {

				switch (handleIntersectionOutside(n.rect, r)) {

				case TOP:
					n.dy = -n.dy;
					n.dx = -n.dx;
					break;
				case BOTTOM:
					n.dy = -n.dy;
					n.dx = -n.dx;
					break;
				case LEFT:
					n.dy = -n.dy;
					n.dx = -n.dx;

					break;
				case RIGHT:
					n.dy = -n.dy;
					n.dx = -n.dx;

					break;

				case FALSE:
					// Do nothing
					break;
				}
			}

			for (Rectangle r : post.rects) {

				switch (handleIntersectionOutside(n.rect, r)) {

				case TOP:
					n.dy = -n.dy;
					n.dx = -n.dx;
					break;
				case BOTTOM:
					n.dy = -n.dy;
					n.dx = -n.dx;
					break;
				case LEFT:
					n.dy = -n.dy;
					n.dx = -n.dx;

					break;
				case RIGHT:
					n.dy = -n.dy;
					n.dx = -n.dx;

					break;

				case FALSE:
					// Do nothing
					break;
				}
			}

			switch (handleIntersectionWall(n.rect, WX, WY)) {

			case TOP:
				n.dy = -n.dy;

				break;
			case BOTTOM:
				n.dy = -n.dy;

				break;
			case LEFT:

				n.dx = -n.dx;

				break;
			case RIGHT:

				n.dx = -n.dx;

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
