package a3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

class MapView extends JPanel implements Observer {
	GameWorld gw = null;

	public MapView(GameWorld gw) {
		gw = this.gw;
		setBackground(Color.darkGray);
		setBorder(new TitledBorder("Map:"));
		setSize(800, 800);
		setVisible(true);

	}

	@Override
	public void update(Observable o, Object arg) {
		gw = (GameWorld) o;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Iterator iter = gw.getCollection().getIterator();
		while (iter.hasNext()) {
			GameObject temp = iter.getNext();
			if (temp instanceof IDrawable) {
				((IDrawable) temp).draw(g);
			}
		}
	}
}
