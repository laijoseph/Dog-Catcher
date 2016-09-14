package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Right extends AbstractAction {

	private GameWorld gw;

	public Right(GameWorld gw) {
		super("Move Net Right");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.right();

	}

}
