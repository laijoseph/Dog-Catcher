package a4;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

abstract class GameObject implements ICollider {
	private Point p;
	private Color c;
	private int size;
	private boolean flagForDel;

	public GameObject(Point p, Color c, int size, boolean flagForDel) {
		this.p = p;
		this.c = c;
		this.size = size;
		this.flagForDel = flagForDel;
	}

	Point getP() {
		return p;
	}

	void setP(Point p) {
		this.p = p;// works for animals, not net. Net uses different move
	}

	Color getC() {
		return c;
	}

	void setC(Color c) {
		this.c = c;
	}

	int getSize() {
		return size;
	}

	void setSize(int size) {
		this.size = size;
	}

	boolean getFlag() {
		return flagForDel;
	}

	void setFlag(boolean flag) {
		this.flagForDel = flag;
	}

	void printMe() {
		System.out.println("Missing print override");
	}

	Color randColor() {
		int red = (int) (Math.random() * 256);
		int green = (int) (Math.random() * 256);
		int blue = (int) (Math.random() * 256);
		Color col = new Color(red, green, blue);// sets a random color

		Random random = new Random();
		float hue = random.nextFloat();
		float saturation = 0.9f;
		float luminance = 1.0f;
		col = Color.getHSBColor(hue, saturation, luminance);// I found this
															// online, and it
															// setting
															// saturation to .9f
															// and luminance to
															// 1.0f is supposed
															// to give me a
															// bright pastel
															// color.

		return col;
	}
}
