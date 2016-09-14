package a3;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Tick extends AbstractAction {

	private GameWorld gw;

	public Tick(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.tick();

	}

}
