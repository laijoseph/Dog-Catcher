package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Kitten extends AbstractAction {

	private GameWorld gw;

	public Kitten(GameWorld gw) {
		super("Create Kitten");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.kitten();

	}

}
