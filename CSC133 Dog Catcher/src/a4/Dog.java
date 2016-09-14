package a4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

class Dog extends Animal implements ISelectable {// getSize()
	// attributes
	// indicates
	// diameter of the
	// circle

	private int scratches, dir, speed;
	private Vector<ICollider> crashVector = new Vector<ICollider>();
	private boolean isSelected = false;

	public Dog(Point p, Color c, int s, int speed, int dir, int scratches,
			boolean flag) {
		super(p, c, s, speed, dir, flag);
		this.scratches = scratches;
		this.dir = dir;
		this.speed = speed;

	}

	int getScratches() {
		return scratches;
	}

	void setScratches(int scratches) {
		if (scratches < 0)
			scratches = 0;
		else if (scratches > 5)
			scratches = 5;
		else
			this.scratches = scratches;
	}

	void setSpeed(int x) {
		updateSpeed();
	}

	private void updateSpeed() {
		this.speed = 5 - this.scratches;
	}

	public void move() {
		Point temp = getP();
		int theta = 90 - dir;
		int deltaX = (int) (Math.cos(theta) * speed);
		int deltaY = (int) (Math.sin(theta) * speed);
		int r = getSize() / 2; // r for radius

		int newX = (getP().x + deltaX);// setting new x
		int newY = (getP().y + deltaY);// setting new y
		// System.out.println("\nNew dog locations! " + newX + " " + newY);

		if ((newX + r) > 1024)
			newX = 1024 - r;
		else if (newX - r < 0)
			newX = r;

		if ((newY + r) > 1024)
			newX = 1024 - r;
		else if ((newY - r) < 0)
			newY = r;
		// lines above will make sure the circle will never land outside of the
		// border
		temp.setLocation(newX, newY);
		setP(temp);
		Random rand = new Random();
		boolean nextDir = rand.nextBoolean();//
		boolean turn = rand.nextBoolean();
		boolean turnReally = rand.nextBoolean();

		if (turn) {
			if (turnReally) {
				if (nextDir)
					dir += 1;// new dir
				else
					dir -= 1;
			}
		}
	}

	void printMe() {
		System.out.println("Dog:   loc=" + getP().x + "," + getP().y
				+ " color=[" + getC().getRed() + ", " + getC().getGreen()
				+ ", " + getC().getBlue() + "] Size = " + getSize() + " speed="
				+ speed + " dir=" + dir + " scratches=" + scratches);
	}

	@Override
	public void draw(Graphics g) {
		if (isSelected())
			g.drawOval((getP().x - (getSize() / 2)),
					(getP().y - (getSize() / 2)), getSize() / 2, getSize() / 2);
		else
			g.fillOval((getP().x - (getSize() / 2)),
					(getP().y - (getSize() / 2)), getSize() / 2, getSize() / 2);
		g.setColor(getC());
	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		int thisCenterX = getP().x + (getSize() / 2); // find centers
		int thisCenterY = getP().y + (getSize() / 2);

		int otherCenterX = ((GameObject) otherObject).getP().x
				+ (((GameObject) otherObject).getSize() / 2);
		int otherCenterY = ((GameObject) otherObject).getP().y
				+ (((GameObject) otherObject).getSize() / 2);

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

	@Override
	public void handleCollision(ICollider otherObject) {
		crashVector.addElement(otherObject);
		setC(randColor());// change color upon collision
		// add objects to vector
	}

	public Vector getCrashVector() {
		return crashVector;
	}

	public void crashVectorDel(ICollider collide) {
		crashVector.remove(collide);
	}

	@Override
	public void setSelected(boolean yesNo) {
		isSelected = yesNo;
		System.out.println("hit? " + yesNo);
	}

	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public boolean contains(Point p) {
		int px = (int) p.getX(); // mouse location
		int py = (int) p.getY();
		int xLoc = getP().x; // shape location
		int yLoc = getP().y;
		if ((px >= (xLoc - (getSize() / 2))) && (px <= xLoc + (getSize() / 2))
				&& (py >= (yLoc - (getSize() / 2)))
				&& (py <= yLoc + (getSize() / 2)))
			return true;
		else
			return false;
	}
}
