package a2;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

class GameWorld extends Observable {
	private Scanner in = new Scanner(System.in);
	private int dogInit, catInit, catCount, dogCount, catCaught, dogCaught,
			clock, points;
	private Dog woof;
	private Cat meow;
	private Net net;
	private MyCollection c;
	private boolean sound;

	String getSoundStatus() {
		if (sound) {
			return ("ON");
		} else {
			return ("OFF");
		}
	}

	void setSoundStatus(boolean soundSetter) {
		sound = soundSetter;
		System.out.println("Sound set " + sound);
		setChanged();
		notifyObservers();
	}

	GameWorld() {
		c = new MyCollection();
	}

	MyCollection getCollection() {
		return c;
	}

	int getCatCount() {
		return catCount;
	}

	int getDogCount() {
		return dogCount;
	}

	int getPoints() {
		return points;
	}

	int getDogCaught() {
		return dogCaught;
	}

	int getCatCaught() {
		return catCaught;
	}

	private void makeDog() {
		woof = new Dog(
				randPoint(),// point
				new Color(222, 184, 135)/* brown or "burlywood" */, randSize(),
				5, randDir(), 0, false);// size, speed, dir, scratches
		c.add(woof);
		dogCount++;
		print("dog made");

		setChanged();
		notifyObservers();
	}

	private void makeCat() {
		meow = new Cat(randPoint(),// point
				Color.yellow,// color
				randSize(),// size
				5,// speed
				randDir()// direction
				, false);
		c.add(meow);
		catCount++;
		print("cat made");

		setChanged();
		notifyObservers();

	}

	private void makeNet() {
		net = new Net(randPoint(), randColor(), 100, false);
		c.add(net);
		print("net made");
		setChanged();
		notifyObservers();
	}

	public void initLayout() {
		System.out
				.println("Input number of dogs, number of cats, then press enter to start the game.  \n");
		dogInit = in.nextInt();
		catInit = in.nextInt();
		makeNet();
		for (int x = 0; x < dogInit; x++) {// creates all the dogs
			makeDog();
		}
		for (int y = 0; y < catInit; y++) {// create all the cats
			makeCat();
		}

	}

	Point randPoint() {

		Point p = new Point(new Point((int) (Math.random() * 900) + 50,
				(int) (Math.random() * 900) + 50));// returns a random point
													// with
													// x and y values ranging
													// from 50-950.
		return p;
	}

	int randSize() {
		return (int) Math.ceil(Math.random()) * 40 + 10;// random size is
														// from 10-50. This will
														// fit the map because
														// radius is at most 50,
														// so it won't be
														// created off map
	}

	Color randColor() {
		int red = (int) (Math.random() * 256);
		int green = (int) (Math.random() * 256);
		int blue = (int) (Math.random() * 256);
		Color col = new Color(red, green, blue);// sets a random color

		Random random = new Random();
		float hue = random.nextFloat();
		float saturation = 0.9f;
		float luminance = 1.0f;
		col = Color.getHSBColor(hue, saturation, luminance);// I found this
															// online, and it
															// setting
															// saturation to .9f
															// and luminance to
															// 1.0f is supposed
															// to give me a
															// bright pastel
															// color.

		return col;
	}

	int randDir() {
		return (int) (Math.random() * 1000) % 360;
	}

	void expand() {
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			GameObject obj = it.getNext();
			if (obj instanceof Net) {
				Net n = (Net) obj;
				n.setSize(n.getSize() + 50);// setSize() has a check to make
											// sure it doesn't get bigger
											// than
											// 500
				print("net expanded");
			}
		}
	}

	void contract() {
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			GameObject obj = it.getNext();
			if (obj instanceof Net) {
				Net n = (Net) obj;
				n.setSize(n.getSize() - 50);// setSize() has a check to make
											// sure it doesn't get smaller than
											// 50
				print("net contracted");
			}
		}
		setChanged();
		notifyObservers();
	}

	void scoop() {
		Iterator it = c.getIterator();
		GameObject obj = null;
		int animalCount = catCount + dogCount;
		int animalIndex = -1;

		int animalEndIndex = (int) (Math.random() * (animalCount));// gives
																	// index of
																	// animal to
																	// be
																	// deleted,
																	// from 0 to
																	// animalcount-1.
		System.out.println("animal count: " + animalCount);

		if (animalCount == 0)
			print("no animals found");// will print error if no animal is found.

		else {
			System.out.println("nth animal picked for scooping: "
					+ (animalEndIndex + 1));

			while ((animalIndex < (animalEndIndex))) {
				obj = it.getNext();
				if (obj instanceof Animal)
					animalIndex++;

			}

			if (obj instanceof Dog) {
				points += (10 - ((Dog) obj).getScratches());
				dogCaught++;
			} else {// has to be a cat if not a dog
				points -= 10;
				catCaught++;
			}
			obj.setFlag(true);// flag for removal

			System.out.print("delete animal   ");
			obj.printMe();
		}
		removeFlagged();// remove from world
		// setChanged();
		/*
		 * notifyObservers(); notify observers are already called in
		 * removeFlagged
		 */
	}

	void removeFlagged() {
		Iterator remover = c.getIterator();
		while (remover.hasNext()) {
			GameObject obj = remover.getNext();
			if (obj.getFlag()) {
				if (obj instanceof Cat) {
					catCount--;
				} else if (obj instanceof Dog) {
					dogCount--;
				}
				remover.del();
				remover = c.getIterator();// sets it as the new iterator because
											// size of collection has changed
				setChanged();
				notifyObservers();
			}

		}
		print("flagged object removed");
		setChanged();
		notifyObservers();
	}

	void right() {
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			GameObject obj = it.getNext();
			if (obj instanceof Net) {
				Net n = (Net) obj;
				n.moveRight();
				print("net moved right");
				System.out.println("Net:   loc=" + n.getP().x + ","
						+ n.getP().y);
			}
		}
		setChanged();
		notifyObservers();
	}

	void left() {
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			GameObject obj = it.getNext();
			if (obj instanceof Net) {
				Net n = (Net) obj;
				n.moveLeft();
				print("net moved left");
				System.out.println("Net:   loc=" + n.getP().x + ","
						+ n.getP().y);
			}
		}
		setChanged();
		notifyObservers();
	}

	void up() {
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			Object obj = it.getNext();
			if (obj instanceof Net) {
				Net n = (Net) obj;
				n.moveUp();
				print("net moved up");
				System.out.println("Net:   loc=" + n.getP().x + ","
						+ n.getP().y);
			}
		}
		setChanged();
		notifyObservers();
	}

	void down() {
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			Object obj = it.getNext();
			if (obj instanceof Net) {
				Net n = (Net) obj;
				n.moveDown();
				print("net moved down");
				System.out.println("Net:   loc=" + n.getP().x + ","
						+ n.getP().y);
			}
		}
		setChanged();
		notifyObservers();
	}

	void kitten() {
		Iterator it = c.getIterator();
		Boolean noCat = true;
		while (it.hasNext()) {
			Object obj = it.getNext();
			if (obj instanceof Cat) {
				noCat = false;// checks for cat. if no cat, print noCat
								// statement
								// below.
			}
		}

		if (noCat)
			System.out.println("No cats found.");
		else
			makeCat();// if "noCat" is false,cats exist. Add a cat.
		setChanged();
		notifyObservers();
	}

	void fight() {
		Iterator it = c.getIterator();
		GameObject obj = null;
		int tempScratches = 0;
		int dogIndex = 0;
		int dogEndIndex = (int) ((Math.random() * (dogCount - 1)) + 1);
		System.out.println("nth dog picked: " + dogEndIndex);
		// System.out.println("dogCount: " + dogCount);
		if (catCount == 0)
			System.out.println("No cats found.");
		else if (dogCount == 0) {
			System.out.println("No dogs to fight.");

		} else {
			while (dogIndex < dogEndIndex) {
				obj = it.getNext();
				if (obj instanceof Dog)
					dogIndex++;

			}

			tempScratches = ((Dog) obj).getScratches();// grabs current
														// scratches
			tempScratches++;// increments current scratches
			((Dog) obj).setScratches(tempScratches);// sets new incremented
													// scratches
			((Dog) obj).setSpeed(0);// parameter doesn't matter as long as it's
									// an int. Dog.setSpeed overrides
									// GameObject.setSpeed because speed has to
									// be 5-scratches. (see "updateSpeed" in
									// dog)
			obj.setC(randColor());// set the dog to a new bright color
		}
		setChanged();
		notifyObservers();
	}

	void tick() {
		clock++;
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			GameObject obj = it.getNext();
			if (obj instanceof Animal) {
				((Animal) obj).move();
				print("animal moved");
			}

		}
		print("clock tocked");
		setChanged();
		notifyObservers();
	}

	void points() {
		print("\nCurrent score: " + points);
		print(dogCaught + " dogs and " + catCaught + " cats captured.");
		print(dogCount + " dogs and " + catCount + " cats left.");
		print("Game time:" + clock);
	}

	void map() {
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			GameObject obj = it.getNext();
			obj.printMe();
		}

	}

	void quit() {
		print("goodybe!");
		System.exit(0);

	}

	void print(String x) {// shortens "System.out.println();"
		System.out.println(x);
	}

}
