package a1;

import java.awt.Color;
import java.awt.Point;

abstract class Animal extends GameObject implements IMovable {

	private int speed;
	private int direction;

	public Animal(Point p, Color c, int s, int speed, int d, boolean flag) {
		super(p, c, s, flag);
		this.speed = speed;
		this.direction = d;

	}

	void setSpeed(int newSpeed) {
		if (newSpeed > 5)
			this.speed = 5;
		else if (newSpeed < 0)
			this.speed = 0;
		else
			speed = newSpeed;
	}

	int getSpeed() {
		return speed;
	}

	int getDirection() {
		return direction;
	}

	void setDirection(int direction) {
		this.direction = direction % 360;
	}

	void setSize(int size) {// overrides setSize because Animals cannot change
							// size
	}
}
