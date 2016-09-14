package a3;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Heal extends AbstractAction {
	private GameWorld gw;

	public Heal(GameWorld gw) {
		super("Heal");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.heal();

	}

}
