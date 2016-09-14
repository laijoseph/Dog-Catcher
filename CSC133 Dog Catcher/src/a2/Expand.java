package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Expand extends AbstractAction {

	private GameWorld gw;

	public Expand(GameWorld gw) {
		super("Expand Net");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.expand();

	}

}
