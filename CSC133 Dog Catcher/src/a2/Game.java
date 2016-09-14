package a2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

class Game extends JFrame {

	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;

	// GUI fields
	private JPanel controls = new JPanel(new GridLayout(10, 1));
	private JButton[] controlButton;
	private JMenuItem[] menuItems;

	private Scanner scan = new Scanner(System.in);

	public Game() {
		gw = new GameWorld();
		gw.initLayout();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		gw.addObserver(mv);
		gw.addObserver(sv);
		sv.update(gw, null);
		gw.map();

		InputMap imap;
		ActionMap amap;

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu commands = new JMenu("Commands");
		setJMenuBar(menuBar);
		menuBar.add(file);
		menuBar.add(commands);

		// commands go here
		Expand e = new Expand(gw);
		Contract c = new Contract(gw);
		Scoop s = new Scoop(gw);
		Right r = new Right(gw);
		Left l = new Left(gw);
		Up u = new Up(gw);
		Down d = new Down(gw);
		Kitten k = new Kitten(gw);
		Fight f = new Fight(gw);
		Tick t = new Tick(gw);
		Quit q = new Quit(gw);
		Sound snd = new Sound(gw);
		About about = new About(gw);
		MapCall m = new MapCall(gw);
		// commands end here

		KeyStroke scoopKey = KeyStroke.getKeyStroke('s');
		KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
		KeyStroke upKey = KeyStroke.getKeyStroke("UP");
		KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
		KeyStroke quitKey = KeyStroke.getKeyStroke('q');
		KeyStroke mapKey = KeyStroke.getKeyStroke('m');

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
		controlButton[7].setAction(k);
		controlButton[8].setAction(f);
		controlButton[9].setAction(t);

		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		imap = controls.getInputMap(mapName);
		imap.put(scoopKey, "Scoop");
		imap.put(rightKey, "Right");
		imap.put(leftKey, "Left");
		imap.put(upKey, "Up");
		imap.put(downKey, "Down");
		imap.put(quitKey, "Quit");
		imap.put(mapKey, "Map");

		amap = controls.getActionMap();
		amap.put("Scoop", s);
		amap.put("Right", r);
		amap.put("Left", l);
		amap.put("Up", u);
		amap.put("Down", d);
		amap.put("Quit", q);
		amap.put("Map", m);

		// menu items begin----------------------
		menuItems = new JMenuItem[9];
		file.add(menuItems[0] = new JMenuItem("New"));
		file.add(menuItems[1] = new JMenuItem("Save"));
		file.add(menuItems[2] = new JCheckBoxMenuItem("Sound"));
		file.add(menuItems[3] = new JMenuItem("About"));
		file.add(menuItems[4] = new JMenuItem("Quit"));

		commands.add(menuItems[5] = new JMenuItem("Expand Net"));
		commands.add(menuItems[6] = new JMenuItem("Contract Net"));
		commands.add(menuItems[7] = new JMenuItem("New Kitten"));
		commands.add(menuItems[8] = new JMenuItem("Fight"));

		// menuItems[0].setAction();
		// menuItems[1].setAction();
		menuItems[2].setAction(snd);
		menuItems[3].setAction(about);
		menuItems[4].setAction(q);

		menuItems[5].setAction(e);
		menuItems[6].setAction(c);
		menuItems[7].setAction(k);
		menuItems[8].setAction(f);

		menuBar.add(file);
		menuBar.add(commands);
		// menu items end---------------------------------

		// maps

		getContentPane().add(sv, BorderLayout.NORTH);
		controls.setBorder(new TitledBorder("Controls:"));
		getContentPane().add(controls, BorderLayout.WEST);
		getContentPane().add(mv, BorderLayout.CENTER);
		setTitle("Dog Catcher");

		// pack();
		setSize(700, 700);
		requestFocus();
		setVisible(true);
	}
}