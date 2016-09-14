package a2;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

class MapView extends JPanel implements Observer {

	public MapView(GameWorld gw) {
		setBackground(Color.WHITE);
		setBorder(new TitledBorder("Map:"));
		JTextArea text = new JTextArea();
		text.append("This area is for future use...");
		add(text);

		setSize(550, 550);
		setVisible(true);

	}

	@Override
	public void update(Observable o, Object arg) {
	}
}
