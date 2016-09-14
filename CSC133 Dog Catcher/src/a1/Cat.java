package a1;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

class Cat extends Animal {// size attribute indicates length of the sides

	private int dir, speed;

	public Cat(Point p, Color c, int size, int speed, int dir, boolean flag) {
		super(p, c, size, speed, dir, flag);

		this.speed = speed;
	}

	public void move() {
		int theta = 90 - dir;
		int deltaX = (int) Math.cos(theta) * speed;
		int deltaY = (int) Math.sin(theta) * speed;
		int xDim = getSize() / 2;// width is size. xDim is center to max
									// left/right
		int yDim = (getSize() / 4) * (int) Math.sqrt(3);// height is
														// (1/2x)*sqrt(3).
		// center to top/bottom is
		// that/2.

		int newX = (getP().x + deltaX);// setting new x
		int newY = (getP().y + deltaY);// setting new y
		System.out.println("\nNew cat locations! " + newX + " " + newY);

		if (newX + xDim > 1024)
			newX = 1024 - xDim;
		else if (newX - xDim < 0)
			newX = xDim;

		if (newY + yDim > 1024)
			newX = 1024 - yDim;
		else if (newY - yDim < 0)
			newY = yDim;
		// lines above will make sure the triangle will never land outside of
		// the
		// border
		Point temp = new Point(newX, newY);
		setP(temp);
		Random rand = new Random();
		boolean nextDir = rand.nextBoolean();//
		if (nextDir)
			dir += 15;
		else
			dir -= 15;

	}

	void printMe() {
		System.out.println("Cat:   loc=" + getP().x + "," + getP().y
				+ " color=[" + getC().getRed() + ", " + +getC().getGreen()
				+ ", " + getC().getBlue() + "] size =" + getSize() + " speed="
				+ speed + " dir=" + dir);
	}
}
