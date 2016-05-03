package biology.neuron;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import biology.neuron.elements.ActionPotential;
import biology.neuron.elements.ExocytosisPoint;
import biology.neuron.elements.ReuptakePump;
import biology.neuron.elements.Vesicle;
import biology.neuron.elements.receptor.base.Receptor;
import engine.Engine;
import misc.GlobalVariables;

public class Neuron {

	JFrame fr;
	public BufferedImage i;
	public BufferedImage iDraw;

	public ReuptakePump pump;
	public ArrayList<Receptor> receptors;
	public ArrayList<Rectangle> rects;
	public ArrayList<Vesicle> vesicles;
	public ArrayList<Vesicle> rVesicles;

	public ArrayList<ExocytosisPoint> exits;
	public ArrayList<ActionPotential> aps;

	public ArrayList<Receptor> activeReceptors;

	int x;

	int y;

	public static int releaseRate = 5000;

	public Neuron(JFrame frs, int xs, int ys) {

		fr = frs;
		x = xs;
		y = ys;

		exits = new ArrayList<ExocytosisPoint>();
		aps = new ArrayList<ActionPotential>();
		activeReceptors = new ArrayList<Receptor>();
		receptors = new ArrayList<Receptor>();
		pump = new ReuptakePump(0, 0, 0, 0);
		vesicles = new ArrayList<Vesicle>();
		rVesicles = new ArrayList<Vesicle>();

	}

	public void draw(Graphics g) {

		g.drawImage(iDraw, x, y, fr);

		try {
			for (Vesicle v : vesicles) {
				v.draw(g);
			}

			for (Receptor ar : activeReceptors) {
				ar.draw(g);
			}

			for (ExocytosisPoint ep : exits) {
				ep.draw(g);
			}

			for (ActionPotential ap : aps) {

				ap.draw(g);
			}

			pump.draw(g);
		} catch (Exception e) {

		}

	}

	void shiftRectangles() {

		for (Rectangle r : rects) {
			r.x += x;
			r.y += y;
		}

	}

	public void update() {

		for (Vesicle v : vesicles) {
			v.update();
		}

		for (Vesicle v : rVesicles) {
			v.rects.clear();
			vesicles.remove(v);

		}

		try {
			for (Point p : Engine.sFrame.rPoint1pre) {
				Engine.sFrame.point1pre.remove(p);

			}

			for (Point p : Engine.sFrame.rPoint2pre) {
				Engine.sFrame.point2pre.remove(p);

			}

			for (Point p : Engine.sFrame.rPoint1post) {
				Engine.sFrame.point1post.remove(p);

			}

			for (Point p : Engine.sFrame.rPoint2post) {
				Engine.sFrame.point2post.remove(p);

			}
		} catch (Exception e) {

		}

		for (ActionPotential ap : aps) {

			ap.update();
		}

	}

	public void releaseAPPost() {

		new Thread(new Runnable() {

			Point temp1;
			Point temp2;

			ArrayList<Integer> indexes = new ArrayList<Integer>();

			public void run() {

				for (int i = y; i < GlobalVariables.WY; i++) {

					for (Rectangle r : rects) {
						if (r.y == i) {
							indexes.add(rects.indexOf(r));

						}

					}

					if (indexes.size() <= 0) {

					} else if (indexes.size() == 1) {

						int index1 = indexes.get(0);
						int index2 = indexes.get(0);

						temp1 = new Point(rects.get(index1).x, rects.get(index1).y);
						temp2 = new Point(rects.get(index2).x + rects.get(index2).width, rects.get(index2).y);

						Engine.sFrame.point1post.add(temp1);
						Engine.sFrame.point2post.add(temp2);

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Engine.sFrame.rPoint1post.add(temp1);
						Engine.sFrame.rPoint2post.add(temp2);

						indexes.clear();
					} else if (indexes.size() >= 2) {
						int index1 = indexes.get(0);
						int index2 = indexes.get(1);

						temp1 = new Point(rects.get(index1).x, rects.get(index1).y);
						temp2 = new Point(rects.get(index2).x + rects.get(index2).width, rects.get(index2).y);

						Engine.sFrame.point1post.add(temp1);
						Engine.sFrame.point2post.add(temp2);

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Engine.sFrame.rPoint1post.add(temp1);
						Engine.sFrame.rPoint2post.add(temp2);

						indexes.clear();
					}

				}

			}

		}).start();

	}

	public void releaseAPPre() {

		new Thread(new Runnable() {

			Point temp1;
			Point temp2;

			ArrayList<Integer> indexes = new ArrayList<Integer>();

			public void run() {

				for (int i = y; i < GlobalVariables.WY; i++) {

					for (Rectangle r : rects) {
						if (r.y == i) {
							indexes.add(rects.indexOf(r));

						}

					}

					if (indexes.size() <= 0) {

					}

					else if (indexes.size() == 1) {
						int index1 = indexes.get(0);
						int index2 = indexes.get(0);

						temp1 = new Point(rects.get(index1).x, rects.get(index1).y);
						temp2 = new Point(rects.get(index2).x + rects.get(index2).width, rects.get(index2).y);

						Engine.sFrame.point1pre.add(temp1);
						Engine.sFrame.point2pre.add(temp2);

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Engine.sFrame.rPoint1pre.add(temp1);
						Engine.sFrame.rPoint2pre.add(temp2);

						indexes.clear();
					} else if (indexes.size() >= 2) {
						int index1 = indexes.get(0);
						int index2 = indexes.get(1);

						temp1 = new Point(rects.get(index1).x, rects.get(index1).y);
						temp2 = new Point(rects.get(index2).x + rects.get(index2).width, rects.get(index2).y);

						Engine.sFrame.point1pre.add(temp1);
						Engine.sFrame.point2pre.add(temp2);

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						Engine.sFrame.rPoint1pre.add(temp1);
						Engine.sFrame.rPoint2pre.add(temp2);

						indexes.clear();
					}

				}

			}

		}).start();

	}

}
