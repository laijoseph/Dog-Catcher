package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

class ZoomIn extends AbstractAction {

	private MapView mv = new MapView(null);

	public ZoomIn(MapView mv) {

		this.mv = mv;
	}

	public void actionPerformed(ActionEvent e) {
		mv.zoomIn();
	}

}
