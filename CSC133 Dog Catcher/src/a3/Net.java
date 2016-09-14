package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

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
		// System.out.println("Net:   loc=" + getP().x + "," + getP().y
		// + " color=[" + getC().getRed() + ", " + +getC().getGreen()
		// + ", " + getC().getBlue() + "] size =" + getSize());
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(getP().x - (getSize() / 2), getP().y - (getSize() / 2),
				getSize(), getSize());
		g.setColor(Color.WHITE);
		g.drawString("Net", getP().x, getP().y);

	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		// boolean result = false;
		// boolean xOverlap = true;
		// boolean yOverlap = true;

		int R1 = getP().x + (getSize() / 2);
		int L1 = getP().x - (getSize() / 2);
		int T1 = getP().y - (getSize() / 2);
		int B1 = getP().y + (getSize() / 2);

		int R2 = ((GameObject) otherObject).getP().x
				+ (((GameObject) otherObject).getSize() / 2);
		int L2 = ((GameObject) otherObject).getP().x
				- (((GameObject) otherObject).getSize() / 2);
		int T2 = ((GameObject) otherObject).getP().y
				- (((GameObject) otherObject).getSize() / 2);
		int B2 = ((GameObject) otherObject).getP().y
				+ (((GameObject) otherObject).getSize() / 2);

		if (R1 < L2 || L1 > R2 || T1 > B2 || B1 < T2) {
			return false;
		} else {
			return true;
		}

		// if ((R1 > L2) || (L1 < R2))
		// xOverlap = true;
		// if ((T2 > B1) || (T1 < B2))
		// yOverlap = true;
		//
		// if (xOverlap && yOverlap)
		// result = true;

		// return result;

	}

	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<ICollider> getCrashVector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crashVectorDel(ICollider collide) {
		// TODO Auto-generated method stub

	}

}
