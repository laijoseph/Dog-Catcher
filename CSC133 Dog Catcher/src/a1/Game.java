package a1;

import java.util.Scanner;

class Game {
	private GameWorld gw;
	private Scanner scan = new Scanner(System.in);

	public Game() {
		gw = new GameWorld();
		gw.initLayout();
		play();
	}

	void play() {
		while (true)
			getCommand();
	}

	private void getCommand() {
		System.out.print("Input command: ");
		switch (scan.next().charAt(0)) {
		case 'e': {
			gw.expand();
			break;
		}
		case 'c': {
			gw.contract();
			break;
		}
		case 's': {
			gw.scoop();
			break;
		}
		case 'r': {
			gw.right();
			break;
		}
		case 'l': {
			gw.left();
			break;
		}
		case 'u': {
			gw.up();
			break;
		}
		case 'd': {
			gw.down();
			break;
		}
		case 'k': {
			gw.kitten();
			break;
		}
		case 'f': {
			gw.fight();
			break;
		}
		case 't': {
			gw.tick();
			break;
		}
		case 'p': {
			gw.points();
			break;
		}
		case 'm': {
			gw.map();
			break;
		}
		case 'q': {
			gw.quit();
			break;
		}
		default: {
			System.out.println("Invalid command, please try again: ");
			break;
		}
		}
	}

}
