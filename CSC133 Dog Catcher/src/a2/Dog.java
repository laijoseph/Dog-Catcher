package a2;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

class Dog extends Animal {// getSize() attributes indicates diameter of the
							// circle

	private int scratches, dir, speed;

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
		System.out.println("\nNew dog locations! " + newX + " " + newY);

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
		if (nextDir)
			dir += 15;
		else
			dir -= 15;

	}

	void printMe() {
		System.out.println("Dog:   loc=" + getP().x + "," + getP().y
				+ " color=[" + getC().getRed() + ", " + getC().getGreen()
				+ ", " + getC().getBlue() + "] Size = " + getSize() + " speed="
				+ speed + " dir=" + dir + " scratches=" + scratches);
	}
}
