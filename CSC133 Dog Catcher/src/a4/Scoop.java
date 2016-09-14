package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Scoop extends AbstractAction {

	private GameWorld gw;
	private Game g;

	public Scoop(GameWorld gw, Game g) {
		super("Scoop Swoop");
		this.gw = gw;
		this.g = g;
	}

	public void actionPerformed(ActionEvent e) {
		gw.scoop();
		g.playScoop();
	}
}
