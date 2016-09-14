package a3;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

class Quit extends AbstractAction {

	private Game g;

	public Quit(Game g) {
		super("Quit");
		this.g = g;
	}

	public void actionPerformed(ActionEvent e) {
		int exit = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to quit?", "Confirm Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exit == JOptionPane.YES_OPTION) {
			g.quit();
		}
		return;
	}

}
