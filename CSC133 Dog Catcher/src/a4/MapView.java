package a4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

class MapView extends JPanel implements Observer {
	GameWorld gw = null;
	AffineTransform worldToND, ndToScreen, theVTM;
	private int winWidth, winHeight, winLeft, winBottom;

	public MapView(GameWorld gw) {
		gw = this.gw;
		setBackground(Color.darkGray);
		setBorder(new TitledBorder("Map:"));
		setSize(800, 800);
		setVisible(true);
		winWidth = 1024;
		winHeight = 1024;

	}

	@Override
	public void update(Observable o, Object arg) {
		gw = (GameWorld) o;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		// update the Viewing Transformation Matrix
		worldToND = buildWorldToNDXform(1024, 1024, 1024, 1024);
		ndToScreen = buildNDToScreenXform(this.getWidth(), this.getHeight());

		Iterator iter = gw.getCollection().getIterator();
		while (iter.hasNext()) {
			GameObject temp = iter.getNext();
			if (temp instanceof IDrawable) {
				((IDrawable) temp).draw(g);
			}
		}
	}

	private AffineTransform buildWorldToNDXform(int winWidth, int winHeight,
			int winLeft, int winBottom) {
		AffineTransform at = new AffineTransform();
		at.scale(1 / winWidth, 1 / winHeight);
		at.translate(-winLeft, -winBottom);
		return at;
	}

	private AffineTransform buildNDToScreenXform(int width, int height) {
		AffineTransform at = new AffineTransform();
		at.translate(0, height);
		at.scale(width, -height);
		return at;
	}

	void zoomIn() {
		double h = winHeight - winBottom;
		double w = winWidth - winLeft;
		winLeft += (w * 0.05);
		winWidth -= (w * 0.05);
		winHeight -= (h * 0.05);
		winBottom += (h * 0.05);
		repaint();
	}

	void zoomOut() {
		double h = winHeight - winBottom;
		double w = winWidth - winLeft;
		winLeft -= (w * 0.05);
		winWidth += (w * 0.05);
		winHeight += (h * 0.05);
		winBottom -= (h * 0.05);
		repaint();
	}
}
