package a3;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class Contract extends AbstractAction {

	private GameWorld gw;

	public Contract(GameWorld gw) {
		super("Contract Net");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.contract();

	}

}
