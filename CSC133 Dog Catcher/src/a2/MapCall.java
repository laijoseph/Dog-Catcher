package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class MapCall extends AbstractAction {

	private GameWorld gw;

	public MapCall(GameWorld gw) {
		super("Map called");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.map();

	}

}
