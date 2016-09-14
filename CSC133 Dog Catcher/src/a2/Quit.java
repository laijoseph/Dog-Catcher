package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

class Quit extends AbstractAction {

	private GameWorld gw;

	public Quit(GameWorld gw) {
		super("Quit");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		int exit = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to quit?", "Confirm Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exit == JOptionPane.YES_OPTION) {
			gw.quit();
		}
		return;
	}

}
