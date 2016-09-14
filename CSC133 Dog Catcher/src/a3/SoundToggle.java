package a3;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class SoundToggle extends AbstractAction {
	private Game g;

	public SoundToggle(Game g) {
		super("Sound");
		this.g = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//
		g.toggleSound();
	}
}