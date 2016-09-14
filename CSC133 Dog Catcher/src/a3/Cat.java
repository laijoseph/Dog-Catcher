package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

class Cat extends Animal {// size attribute indicates length of the sides

	private int dir, speed, numMoves;
	private boolean isKitten = true;
	private Vector<ICollider> crashVector = new Vector<ICollider>();
	private boolean isSelected;

	public Cat(Point p, Color c, int size, int speed, int dir, boolean flag) {
		super(p, c, size, speed, dir, flag);

		this.speed = speed;
		this.dir = dir;
	}

	public void move() {
		numMoves++;
		if (numMoves > 499)// 1 move every 20ms, 1000ms per sec,10 second delay
			isKitten = false;
		Point temp = getP();
		int theta = 90 - dir;
		int deltaX = (int) (Math.cos(theta) * speed);
		int deltaY = (int) (Math.sin(theta) * speed);
		int size = getSize() / 2;

		int newX = (getP().x + deltaX);// setting new x
		int newY = (getP().y + deltaY);// setting new y
		// System.out.println("\nNew cat locations! " + newX + " " + newY);

		if ((newX + size) > 1024)
			newX = 1024 - size;
		else if (newX - size < 0)
			newX = size;

		if ((newY + size) > 1024)
			newX = 1024 - size;
		else if ((newY - size) < 0)
			newY = size;
		// lines above will make sure the triangle will never land outside of
		// the
		// border
		temp.setLocation(newX, newY);
		setP(temp);
		Random rand = new Random();
		boolean nextDir = rand.nextBoolean();//
		boolean turn = rand.nextBoolean();
		if (turn) {
			if (nextDir)
				dir += 1;// new dir
			else
				dir -= 1;
		}
	}

	void printMe() {
		System.out.println("Cat:   loc=" + getP().x + "," + getP().y
				+ " color=[" + getC().getRed() + ", " + +getC().getGreen()
				+ ", " + getC().getBlue() + "] size =" + getSize() + " speed="
				+ speed + " dir=" + dir);
	}

	@Override
	public void draw(Graphics g) {
		int[] xPts = { getP().x, (getP().x - (getSize() / 2)),
				(getP().x + (getSize() / 2)) };
		int[] yPts = { (getP().y + (getSize() / 2)),
				(getP().y - (getSize() / 2)), (getP().y - (getSize() / 2)) };

		g.fillPolygon(xPts, yPts, 3);
		g.setColor(getC());
	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		int thisCenterX = getP().x; // find centers
		int thisCenterY = getP().y;

		int otherCenterX = ((GameObject) otherObject).getP().x;
		int otherCenterY = ((GameObject) otherObject).getP().y;

		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx * dx + dy * dy);

		// find square of sum of radii
		int thisRadius = getSize() / 2;
		int otherRadius = ((GameObject) otherObject).getSize() / 2;

		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius
				* otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		return result;
	}

	public void handleCollision(ICollider otherObject) {
		crashVector.addElement(otherObject);
		// add objects to vector

	}

	public Vector getCrashVector() {
		return crashVector;
	}

	public void crashVectorDel(ICollider collide) {
		crashVector.remove(collide);
	}

	boolean isKitten() {
		return isKitten;
	}

}
