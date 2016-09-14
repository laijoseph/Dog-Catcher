package a1;

import java.awt.Color;
import java.awt.Point;

abstract class Catcher extends GameObject implements IGuided {

	public Catcher(Point p, Color c, int size, boolean flag) {
		super(p, c, size, flag);

	}

}
