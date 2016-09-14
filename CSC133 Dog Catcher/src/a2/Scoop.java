package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Scoop extends AbstractAction {

	private GameWorld gw;

	public Scoop(GameWorld gw) {
		super("Scoop Swoop");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.scoop();
		gw.map();

	}

}
