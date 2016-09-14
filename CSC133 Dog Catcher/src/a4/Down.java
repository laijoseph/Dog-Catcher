package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Down extends AbstractAction {

	private GameWorld gw;

	public Down(GameWorld gw) {
		super("Move Net Down");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.down();

	}

}
