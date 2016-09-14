package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Up extends AbstractAction {

	private GameWorld gw = new GameWorld();

	public Up(GameWorld gw) {
		super("Move Net Up");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.up();
	}

}
