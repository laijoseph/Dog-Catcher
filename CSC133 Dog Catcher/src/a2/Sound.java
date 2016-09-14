package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class Sound extends AbstractAction {
	private GameWorld gw;

	public Sound(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//
		if (gw.getSoundStatus().equals("ON")) {
			gw.setSoundStatus(false);
		} else {
			gw.setSoundStatus(true);
		}

	}
}