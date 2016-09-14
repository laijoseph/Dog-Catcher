package a4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

class Game extends JFrame implements ActionListener, MouseListener {

	private GameWorld gw = new GameWorld();
	private MapView mv;
	private ScoreView sv;

	// GUI fields
	private JPanel controls = new JPanel(new GridLayout(10, 1));
	private JButton[] controlButton;
	private JMenuItem[] menuItems;
	private final int DELAY_IN_MSEC = 20;
	private Timer myTimer = new Timer(DELAY_IN_MSEC, this);
	private boolean sound = true;
	private Game g = this;

	// commands go here
	Expand e = new Expand(gw);
	Contract c = new Contract(gw);
	Scoop s = new Scoop(gw, g);
	Right r = new Right(gw);
	Left l = new Left(gw);
	Up u = new Up(gw);
	Down d = new Down(gw);
	Tick t = new Tick(gw);
	Quit q = new Quit(g);
	PlayPause pp = new PlayPause(g);
	SoundToggle snd = new SoundToggle(this);
	About about = new About(gw);
	Heal h = new Heal(gw);

	ZoomIn zi;
	ZoomOut zo;

	// sound vars

	private String soundDir = "." + File.separator + "sounds" + File.separator;

	private String bgmSoundFile = "bgm.wav";
	private String scratchSoundFile = "scratch.wav";
	private String kitenSoundFile = "kitten.wav";
	private String scoopSoundFile = "scoop.wav";

	private String bgmSoundPath = soundDir + bgmSoundFile;
	private String scratchSoundPath = soundDir + scratchSoundFile;
	private String kittenSoundPath = soundDir + kitenSoundFile;
	private String scoopSoundPath = soundDir + scoopSoundFile;

	private Sound bgm = new Sound(bgmSoundPath);
	private Sound scoop = new Sound(scoopSoundPath);
	private Sound scratch = new Sound(scratchSoundPath);
	private Sound kitten = new Sound(kittenSoundPath);

	public Game() {
		gw.initLayout();
		bgm.loop();
		mv = new MapView(gw);

		zi = new ZoomIn(mv);
		zo = new ZoomOut(mv);

		addMouseListener(g);
		sv = new ScoreView(gw);
		gw.addObserver(mv);
		gw.addObserver(sv);
		sv.update(gw, this);
		mv.update(gw, null);
		myTimer.start();

		InputMap imap;
		ActionMap amap;

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu commands = new JMenu("Commands");
		setJMenuBar(menuBar);
		menuBar.add(file);
		menuBar.add(commands);

		// commands end here

		KeyStroke scoopKey = KeyStroke.getKeyStroke('s');
		KeyStroke expandKey = KeyStroke.getKeyStroke('e');
		KeyStroke contractKey = KeyStroke.getKeyStroke('c');
		KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
		KeyStroke upKey = KeyStroke.getKeyStroke("UP");
		KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
		KeyStroke quitKey = KeyStroke.getKeyStroke('q');
		KeyStroke playPauseKey = KeyStroke.getKeyStroke('p');
		KeyStroke zoomInKey = KeyStroke.getKeyStroke('+');
		KeyStroke zoomOutKey = KeyStroke.getKeyStroke('-');

		controlButton = new JButton[10];// initializing buttons
		for (int i = 0; i < controlButton.length; i++) {
			controlButton[i] = new JButton();
			controlButton[i].getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
					"none");
		}
		// adding buttons to control panel
		for (int j = 0; j < controlButton.length; j++)
			controls.add(controlButton[j]);

		controlButton[0].setAction(e);
		controlButton[1].setAction(c);
		controlButton[2].setAction(s);
		controlButton[3].setAction(r);
		controlButton[4].setAction(l);
		controlButton[5].setAction(u);
		controlButton[6].setAction(d);
		controlButton[7].setAction(h);
		h.setEnabled(false);
		controlButton[9].setAction(pp);
		controlButton[9].setText("Pause");

		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		imap = controls.getInputMap(mapName);
		imap.put(scoopKey, "Scoop");
		imap.put(rightKey, "Right");
		imap.put(leftKey, "Left");
		imap.put(upKey, "Up");
		imap.put(downKey, "Down");
		imap.put(quitKey, "Quit");
		imap.put(playPauseKey, "PlayPause");
		imap.put(expandKey, "Expand");
		imap.put(contractKey, "Contract");
		imap.put(zoomInKey, "ZoomIn");
		imap.put(zoomOutKey, "ZoomOut");

		amap = controls.getActionMap();
		amap.put("Scoop", s);
		amap.put("Right", r);
		amap.put("Left", l);
		amap.put("Up", u);
		amap.put("Down", d);
		amap.put("Quit", q);
		amap.put("PlayPause", pp);
		amap.put("Expand", e);
		amap.put("Contract", c);
		amap.put("ZoomIn", zi);
		amap.put("ZoomOut", zo);

		// menu items begin----------------------
		menuItems = new JMenuItem[9];
		file.add(menuItems[0] = new JMenuItem("New"));
		file.add(menuItems[1] = new JMenuItem("Save"));
		file.add(menuItems[2] = new JCheckBoxMenuItem("Sound", true));
		file.add(menuItems[3] = new JMenuItem("About"));
		file.add(menuItems[4] = new JMenuItem("Quit"));

		commands.add(menuItems[5] = new JMenuItem("Expand Net"));
		commands.add(menuItems[6] = new JMenuItem("Contract Net"));

		// menuItems[0].setAction();
		// menuItems[1].setAction();
		menuItems[2].setAction(snd);
		menuItems[3].setAction(about);
		menuItems[4].setAction(q);

		menuItems[5].setAction(e);
		menuItems[6].setAction(c);

		menuBar.add(file);
		menuBar.add(commands);
		// menu items end---------------------------------

		// maps

		getContentPane().add(sv, BorderLayout.NORTH);
		controls.setBorder(new TitledBorder("Controls:"));
		getContentPane().add(controls, BorderLayout.WEST);
		getContentPane().add(mv, BorderLayout.CENTER);
		setTitle("Dog Catcher");

		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		// requestFocus();
		setVisible(true);
	}

	String getSoundStatus() {
		if (sound) {
			return ("ON");
		} else {
			return ("OFF");
		}
	}

	void togglePlayPause() {
		if (myTimer.isRunning()) {
			myTimer.stop();
			controlButton[9].setText("Pause");
			c.setEnabled(false);
			e.setEnabled(false);
			u.setEnabled(false);
			d.setEnabled(false);
			l.setEnabled(false);
			r.setEnabled(false);
			s.setEnabled(false);
			h.setEnabled(true);

		} else {
			myTimer.start();

			controlButton[9].setText("Play");
			c.setEnabled(true);
			e.setEnabled(true);
			u.setEnabled(true);
			d.setEnabled(true);
			l.setEnabled(true);
			r.setEnabled(true);
			s.setEnabled(true);
			h.setEnabled(false);

		}

	}

	void toggleSound() {
		if (sound) {
			sound = false;
			bgm.stop();
		} else {
			sound = true;
			bgm.loop();
		}
	}

	public void actionPerformed(ActionEvent e) {
		gw.tick();
		gw.removeFlagged();

		Iterator iter = gw.getCollection().getIterator();
		int newCats = 0;
		while (iter.hasNext()) {
			GameObject curObj = iter.getNext();
			if (curObj instanceof Animal) {
				Iterator iter2 = gw.getCollection().getIterator();
				while (iter2.hasNext()) {
					GameObject otherObj = iter2.getNext();
					if (otherObj instanceof Animal) {
						if (otherObj != curObj) {// make sure it's not the SAME
													// object
							// check for collision
							if (curObj.collidesWith(otherObj)) {
								if (!(curObj.getCrashVector()
										.contains(otherObj))) {// isn't
																// registered
																// object

									curObj.getCrashVector()
											.addElement(otherObj);
									System.out.print("new collision!");
									if ((curObj instanceof Dog)
											&& (otherObj instanceof Cat)) {
										gw.fight((Dog) curObj, (Cat) otherObj);
										if (sound)
											scratch.play();

									} else if ((curObj instanceof Cat)
											&& (otherObj instanceof Cat)) {
										newCats = gw.kitten((Cat) curObj,
												(Cat) otherObj, newCats);
									}
								}
							}
						}

					} else {// no overlap
						if ((curObj.getCrashVector().contains(otherObj))) {
							((ICollider) curObj).crashVectorDel(otherObj);
						}
					}
				}
			}
		}

		for (int i = 0; i < newCats; i++)
			if (gw.getCatCount() < 30) {
				gw.makeCat();
				if (sound)
					kitten.play();
			}

		if (gw.getDogCount() <= 0) {
			myTimer.stop();
			JOptionPane.showMessageDialog(null, "Score: " + gw.getPoints()
					+ "\n Time in seconds: " + (gw.getTime() / 50),
					"Game Over", JOptionPane.PLAIN_MESSAGE);
		}
		gw.notifyObservers();
	}

	void playScoop() {
		scoop.play();
	}

	void quit() {
		System.out.print("goodybe!");
		System.exit(0);

	}

	void disableCommands() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		p.setLocation((p.x - 142), (p.y - 82));

		Iterator it = gw.getCollection().getIterator();
		while (it.hasNext()) {
			GameObject temp = it.getNext();
			if (temp instanceof Dog) {
				if (((ISelectable) temp).contains(p)) {
					((ISelectable) temp).setSelected(true);
					System.out.println("object selected");
				} else {
					if (e.isControlDown()) {
						// empty body so don't deselect if ctrl is held down
					} else
						((ISelectable) temp).setSelected(false);
				}
				System.out.println("mouse loc: " + p);
				System.out.println("dog loc: " + temp.getP());
			}
		}

		mv.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}