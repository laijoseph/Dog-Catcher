package a2;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

class ScoreView extends JPanel implements Observer {

	private JLabel points, dogsC, catsC, dogsR, catsR, soundS;

	public ScoreView(GameWorld gw) {
		gw.addObserver(this);
		setLayout(new FlowLayout());
		setBorder(new TitledBorder("Score View"));
		points = new JLabel();
		dogsC = new JLabel();
		catsC = new JLabel();
		dogsR = new JLabel();
		catsR = new JLabel();
		soundS = new JLabel();
		add(points);
		add(dogsC);
		add(catsC);
		add(dogsR);
		add(catsR);
		add(soundS);

	}

	public void update(Observable gw, Object arg) {
		System.out.println("update method called");
		points.setText("Points: " + ((GameWorld) gw).getPoints());
		dogsC.setText("Dogs Captured: " + ((GameWorld) gw).getDogCaught());
		catsC.setText("Cats Captured: " + ((GameWorld) gw).getCatCaught());
		dogsR.setText("Dogs Remaining: " + ((GameWorld) gw).getDogCount());
		catsR.setText("Cats Remaining: " + ((GameWorld) gw).getCatCount());
		soundS.setText("Sound: " + ((GameWorld) gw).getSoundStatus());
	}
}
