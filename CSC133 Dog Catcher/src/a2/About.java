package a2;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class About extends AbstractAction {
	private GameWorld gw;

	public About(GameWorld gw) {
		super("About");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane
				.showMessageDialog(
						null,
						"By Joseph Lai \nNo dogs or cats were harmed in the process of catching.",
						"About", JOptionPane.INFORMATION_MESSAGE);
	}
}