package a1;

import java.awt.Color;
import java.awt.Point;

abstract class GameObject {
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
}
