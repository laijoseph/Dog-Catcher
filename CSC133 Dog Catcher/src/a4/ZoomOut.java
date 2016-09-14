package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class ZoomOut extends AbstractAction {

	private MapView mv = null;

	public ZoomOut(MapView mv) {

		this.mv = mv;
	}

	public void actionPerformed(ActionEvent e) {
		mv.zoomOut();
	}

}
