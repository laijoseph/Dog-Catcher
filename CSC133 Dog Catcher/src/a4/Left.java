package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Left extends AbstractAction {

	private GameWorld gw;

	public Left(GameWorld gw) {
		super("Move Net Left");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.left();

	}

}
