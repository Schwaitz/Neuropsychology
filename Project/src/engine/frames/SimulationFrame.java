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
import biology.neuron.elements.ExocytosisPoint;
import biology.neuron.elements.Vesicle;
import biology.neuron.elements.receptor.base.Receptor;
import biology.neuron.handlers.AutoReceptorHandler;
import biology.neuron.handlers.PostsynapticReceptorHandler;
import biology.neurotransmitter.base.Neurotransmitter;
import engine.handlers.IntersectionHandler;

public class SimulationFrame extends JFrame implements AutoReceptorHandler,
		PostsynapticReceptorHandler, IntersectionHandler {

	Neuron post;
	Neuron pre;

	public int WX;
	public int WY;

	int neurotransmitterInVesicle = 30;

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

		pre = new PresynapticNeuron(this, 0 + 71, 0);

		post = new PostsynapticNeuron(this, 0 + 71, 400);

		for (int n = 0; n < neurotransmitterInVesicle; n++) {
			for (int v = 0; v < 3; v++) {
				nt.add(new Neurotransmitter("Dopamine",
						pre.vesicles.get(v).x + 15, pre.vesicles.get(v).y + 15,
						5, 5, new Color(255, 160, 30), this, pre.vesicles
								.get(v), "Dopamine"));
			}
		}
		int index = 0;

		for (Receptor r : post.receptors) {

			if (r.type.equals("DOPAMINE")) {

				r.x = 45 + 60 * index + 71;
				r.y = 390;
				r.width = 30;
				r.height = 14;
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
		for (Neurotransmitter n : nt) {

			n.draw(bufferGraphics);
		}

		for (ExocytosisPoint e : pre.exits) {

			e.draw(bufferGraphics);
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

			for (Neurotransmitter n : nt) {

				bufferGraphics.drawRect(n.rect.x, n.rect.y, n.rect.width,
						n.rect.height);
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
						switch (handleIntersectionOutside(r, prer)) {

						case TOP:

							v.intersectionLock();

							if (randSwitch()) {
								v.dy = -v.dy + (int) (Math.random() * 3);
								if(v.dy == 0){
									v.dy = 3;
								}

							} else {
								v.dy = -v.dy + (int) (Math.random() * -3);
								if(v.dy == 0){
									v.dy = 3;
								}
							}

							if (randSwitch()) {
								v.dx = -v.dx + (int) (Math.random() * 3);
								if(v.dx == 0){
									v.dx = 3;
								}
							} else {
								v.dx = -v.dx + (int) (Math.random() * -3);
								if(v.dx == 0){
									v.dx = 3;
								}
							}

							break;
						case BOTTOM:

							v.intersectionLock();
							if (randSwitch()) {
								v.dy = -v.dy + (int) (Math.random() * 3);
								if(v.dy == 0){
									v.dy = 3;
								}

							} else {
								v.dy = -v.dy + (int) (Math.random() * -3);
								if(v.dy == 0){
									v.dy = 3;
								}
							}

							if (randSwitch()) {
								v.dx = -v.dx + (int) (Math.random() * 3);
								if(v.dx == 0){
									v.dx = 3;
								}
							} else {
								v.dx = -v.dx + (int) (Math.random() * -3);
								if(v.dx == 0){
									v.dx = 3;
								}
							}
							break;
						case LEFT:

							v.intersectionLock();
							if (randSwitch()) {
								v.dy = -v.dy + (int) (Math.random() * 3);
								if(v.dy == 0){
									v.dy = 3;
								}

							} else {
								v.dy = -v.dy + (int) (Math.random() * -3);
								if(v.dy == 0){
									v.dy = 3;
								}
							}

							if (randSwitch()) {
								v.dx = -v.dx + (int) (Math.random() * 3);
								if(v.dx == 0){
									v.dx = 3;
								}
							} else {
								v.dx = -v.dx + (int) (Math.random() * -3);
								if(v.dx == 0){
									v.dx = 3;
								}
							}

							break;
						case RIGHT:

							v.intersectionLock();

							if (randSwitch()) {
								v.dy = -v.dy + (int) (Math.random() * 3);
								if(v.dy == 0){
									v.dy = 3;
								}

							} else {
								v.dy = -v.dy + (int) (Math.random() * -3);
								if(v.dy == 0){
									v.dy = 3;
								}
							}

							if (randSwitch()) {
								v.dx = -v.dx + (int) (Math.random() * 3);
								if(v.dx == 0){
									v.dx = 3;
								}
							} else {
								v.dx = -v.dx + (int) (Math.random() * -3);
								if(v.dx == 0){
									v.dx = 3;
								}
							}

							break;

						case FALSE:
							// Do nothing
							break;
						}
					}
				}

				for (ExocytosisPoint e : pre.exits) {
					switch (handleIntersectionOutside(r, e.rect)) {

					case TOP:
						v.releaseTransmitter = true;
						v.releasePointer = e;

						for (Neurotransmitter n : nt) {

							if (n.pointer.equals(v)) {
								n.x = v.releasePointer.x
										+ (v.releasePointer.width / 2);
								n.y = v.releasePointer.y + 10;
								n.release();

							}

						}

						v.remove();

						break;
					case BOTTOM:
						v.releaseTransmitter = true;
						v.releasePointer = e;

						for (Neurotransmitter n : nt) {

							if (n.pointer.equals(v)) {
								n.x = v.releasePointer.x
										+ (v.releasePointer.width / 2);
								n.y = v.releasePointer.y + 10;
								n.release();

							}

						}
						v.remove();

						break;
					case LEFT:
						v.releaseTransmitter = true;
						v.releasePointer = e;

						for (Neurotransmitter n : nt) {

							if (n.pointer.equals(v)) {
								n.x = v.releasePointer.x
										+ (v.releasePointer.width / 2);
								n.y = v.releasePointer.y + 10;
								n.release();

							}

						}
						v.remove();

						break;
					case RIGHT:
						v.releaseTransmitter = true;
						v.releasePointer = e;

						for (Neurotransmitter n : nt) {

							if (n.pointer.equals(v)) {

								n.x = v.releasePointer.x
										+ (v.releasePointer.width / 2);
								n.y = v.releasePointer.y + 10;
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

				if (n.y > r.y + 20) {

					rnt.add(n);
				}

				switch (handleIntersectionOutside(n.rect, r)) {

				case TOP:
					n.dy = -n.dy;

					break;
				case BOTTOM:
					n.dy = -n.dy;

					break;
				case LEFT:
					n.dy = -n.dy;

					break;
				case RIGHT:
					n.dy = -n.dy;

					break;

				case FALSE:
					// Do nothing
					break;
				}
			}

			for (Receptor r : post.receptors) {

				if (r.neurotransmitterBindType.equals(n.receptorBindType)) {

					switch (handleIntersectionOutside(n.rect, r.rect)) {
					case TOP:
						n.dy = 0;
						n.dx = 0;

						break;
					case BOTTOM:
						n.dy = 0;
						n.dx = 0;

						break;
					case LEFT:
						n.dy = 0;
						n.dx = 0;

						break;
					case RIGHT:
						n.dy = 0;
						n.dx = 0;

						break;

					case FALSE:
						// Do nothing
						break;

					}
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

	boolean randSwitch() {

		int rand = (int) (Math.random() * 2);

		if (rand == 0) {

			return false;

		} else {
			return true;
		}
	}

}
