package a3;

import java.awt.Graphics;
import java.awt.Point;

public interface ISelectable {
	public void setSelected(boolean yesNo);

	// a way to test whether an object is selected
	public boolean isSelected();

	// a way to determine if a mouse point is “in” an object
	public boolean contains(Point p);

	// a way to “draw” the object that knows about drawing // different ways
	// depending on “isSelected”
	public void draw(Graphics g);
}
