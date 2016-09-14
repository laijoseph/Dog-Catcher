package a3;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class PlayPause extends AbstractAction {
	private Game g;

	public PlayPause(Game g) {

		this.g = g;

	}

	public void actionPerformed(ActionEvent e) {
		g.togglePlayPause();

	}
}
