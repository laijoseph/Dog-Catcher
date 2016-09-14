package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Up extends AbstractAction {

	private GameWorld gw;

	public Up(GameWorld gw) {
		super("Move Net Up");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.up();

	}

}
