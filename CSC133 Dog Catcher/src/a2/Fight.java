package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Fight extends AbstractAction {

	private GameWorld gw;

	public Fight(GameWorld gw) {
		super("Fight");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.fight();

	}

}
