package engine.frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

import misc.GlobalVariables;
import misc.RandomSwitch;
import biology.neuron.Neuron;
import biology.neuron.PostsynapticNeuron;
import biology.neuron.PresynapticNeuron;
import biology.neuron.elements.ExocytosisPoint;
import biology.neuron.elements.Vesicle;
import biology.neuron.elements.receptor.base.Receptor;
import biology.neuron.handlers.PostsynapticReceptorHandler;
import biology.neurotransmitter.base.Neurotransmitter;
import engine.handlers.IntersectionHandler;

public class SimulationFrame extends JFrame implements
		PostsynapticReceptorHandler, IntersectionHandler, RandomSwitch {

	Neuron post;
	Neuron pre;

	public int WX = GlobalVariables.WX;
	public int WY = GlobalVariables.WY;

	boolean drawRectangles = false;

	public static ArrayList<Neurotransmitter> nt = new ArrayList<Neurotransmitter>();
	public static ArrayList<Neurotransmitter> rnt = new ArrayList<Neurotransmitter>();

	public SimulationFrame() {

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

		pre = new PresynapticNeuron(this, 0 + 71, 0);

		post = new PostsynapticNeuron(this, 0 + 71, WY - 197);

	}

	public void render(Graphics g) {

		Image offscreen = this.createImage(this.WX, this.WY);
		Graphics bufferGraphics = offscreen.getGraphics();

		bufferGraphics.clearRect(0, 0, this.WX, this.WY);

		// bufferGraphics.setColor(Color.white);
		// bufferGraphics.fillRect(0, 0, WX, WY);

		for (Receptor r : post.activeReceptors) {

			r.draw(bufferGraphics);
		}

		pre.draw(bufferGraphics);

		post.draw(bufferGraphics);

		for (Vesicle v : pre.vesicles) {

			v.draw(bufferGraphics);
		}

		for (ExocytosisPoint e : pre.exits) {

			e.draw(bufferGraphics);
		}

		for (Neurotransmitter n : nt) {

			n.draw(bufferGraphics);
		}

		if (drawRectangles == true) {

			bufferGraphics.setColor(Color.cyan);

			for (Rectangle r : pre.rects) {
				bufferGraphics.drawRect(r.x, r.y, r.width, r.height);

			}

			for (Rectangle r : post.rects) {
				bufferGraphics.drawRect(r.x, r.y, r.width, r.height);

			}

			for (Receptor re : post.receptors) {

				if (re.type.equals("DOPAMINE")) {

					bufferGraphics.drawRect(re.rect.x, re.rect.y,
							re.rect.width, re.rect.height);

				}

			}

			for (Vesicle v : pre.vesicles) {

				for (Rectangle r : v.rects) {
					bufferGraphics.drawRect(r.x, r.y, r.width, r.height);
				}
			}

			bufferGraphics.setColor(Color.black);

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

					if (v.xLock == false) {
						switch (handleIntersection(r, prer)) {

						case TRUE:

							v.intersectionLock();

							if (randSwitch()) {
								v.dy = -v.dy + (int) (Math.random() * 2 + 1);

							} else {
								v.dy = -v.dy + (int) (Math.random() * -2 - 1);

							}

							if (randSwitch()) {
								v.dx = -v.dx + (int) (Math.random() * 2 + 1);

							} else {
								v.dx = -v.dx + (int) (Math.random() * -2 - 1);

							}

							break;

						case FALSE:
							// Do nothing
							break;
						}
					}
				}

				for (ExocytosisPoint e : pre.exits) {
					switch (handleIntersection(r, e.rect)) {

					case TRUE:
						v.releaseTransmitter = true;

						for (Neurotransmitter n : nt) {

							if (n.pointer == v) {

								n.x = e.x + (e.width / 2);
								n.y = e.y + e.height - 15;
								n.release();
							}

						}

						v.remove();

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
				if (n.xLock == false) {
					switch (handleIntersection(n.rect, r)) {

					case TRUE:

						if (n.passed == false) {
							if (r.y < 290) {

								n.dy = -n.dy;
								n.dx = -n.dx;

							} else {
								n.unlockPassed();
							}

						} else {

							n.intersectionLock();
							if (randSwitch()) {
								n.dy = -n.dy + (int) (Math.random() * 2 + 1);

							} else {
								n.dy = -n.dy + (int) (Math.random() * -2 - 1);

							}

							if (randSwitch()) {
								n.dx = -n.dx + (int) (Math.random() * 2 + 1);

							} else {
								n.dx = -n.dx + (int) (Math.random() * -2 - 1);

							}
						}

					case FALSE:
						// Do nothing
						break;
					}
				}
			}

			for (Rectangle r : post.rects) {

				switch (handleIntersection(n.rect, r)) {

				case TRUE:
					n.dy = -n.dy;
					n.dx = -n.dx;

					break;
				case FALSE:
					// Do nothing
					break;
				}
			}

			switch (handleIntersection(pre.pump.rect, n.rect)) {
			case TRUE:
				if (n.passed == true) {
					rnt.add(n);
				}

				break;
			case FALSE:
				// Do nothing
				break;
			}

			for (Receptor r : post.receptors) {

				if (r.neurotransmitterBindType.equals(n.receptorBindType)) {

					if (n.released == false) {
						switch (handleIntersection(n.rect, r.rect)) {
						case TRUE:
							n.dy = 0;
							n.dx = 0;
							n.y = r.y - 5;
							r.boundNT.add(n);
							r.startReleaseTimer(n);

							break;

						case FALSE:
							// Do nothing
							break;
						}
					}
				}
			}

			switch (handleIntersectionWall(n.rect, WX, WY)) {

			case TOP:
				rnt.add(n);

				break;
			case BOTTOM:
				rnt.add(n);

				break;
			case LEFT:

				rnt.add(n);

				break;
			case RIGHT:

				rnt.add(n);

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
