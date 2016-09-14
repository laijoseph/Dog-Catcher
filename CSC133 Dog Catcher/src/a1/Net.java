package a1;

import java.awt.Color;
import java.awt.Point;

class Net extends Catcher {

	private final int inc = 15;// num of pixels to move by per method call

	public Net(Point p, Color c, int size, boolean flag) {
		super(p, c, size, flag);
		super.setSize(size);
	}

	// size attribute indicates length of equal sides of the square
	void setSize(int thisSize) {
		if (thisSize < 50) {
			super.setSize(50);
			// size = 50;
		} else if (thisSize > 500) {
			super.setSize(500);
			// size = 500;
		} else
			super.setSize(thisSize);
		System.out.println("Size is now " + getSize());

	}

	void setC(Color c) {// doesn't allow color change

	}

	@Override
	public void moveDown() {// y value goes up because origin is at top left
		Point temp = new Point(0, 0);
		if ((getP().y + (getSize() / 2) + inc) > 1024)
			temp.setLocation(getP().x, (1024 - (getSize() / 2)));
		else
			temp.setLocation(getP().x, getP().y + inc);// y+inc is how much it
														// moves up
		// per method call
		setP(temp);

	}

	@Override
	public void moveUp() {// y value goes down because origin is at top left
		Point temp = new Point(0, 0);
		if ((getP().y - (getSize() / 2) - inc) < 0)
			temp.setLocation(getP().x, (getSize() / 2));
		else
			temp.setLocation(getP().x, getP().y - inc);// y-inc is how much
														// it'll move
		setP(temp); // down per method call

	}

	@Override
	public void moveLeft() {
		Point temp = new Point(0, 0);
		if ((getP().x - getSize() / 2) - inc < 0)
			temp.setLocation((getSize() / 2), getP().y);
		else
			temp.setLocation(getP().x - inc, getP().y);// x moves to left by inc
														// pixels
		setP(temp);
	}

	@Override
	public void moveRight() {
		Point temp = new Point(0, 0);
		if ((getP().x + getSize() / 2) + inc > 1024)
			temp.setLocation(1024 - (getSize() / 2), getP().y);
		else
			temp.setLocation(getP().x + inc, getP().y);
		setP(temp);

	}

	void printMe() {
		System.out.println("Net:   loc=" + getP().x + "," + getP().y
				+ " color=[" + getC().getRed() + ", " + +getC().getGreen()
				+ ", " + getC().getBlue() + "] size =" + getSize());
	}
}
