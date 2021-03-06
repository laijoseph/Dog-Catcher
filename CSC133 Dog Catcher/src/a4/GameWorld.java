package a4;

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
				new Color(222, 184, 135)/* brown or "burlywood" */,
				2 * randSize(), 5, randDir(), 5, false);// size, speed, dir,
														// scratches
		c.add(woof);
		dogCount++;
		print("dog made");

		setChanged();
		notifyObservers();
	}

	void makeCat() {
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

	void makeNet() {
		net = new Net(randPoint(), Color.WHITE, 100, false);
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

		Point p = new Point(new Point((int) (Math.random() * 700) + 50,
				(int) (Math.random() * 700) + 50));// returns a random point
													// with
													// x and y values ranging
													// from 50-750.
		return p;
	}

	int randSize() {
		int randomSize = (int) ((Math.ceil(Math.random() * 40)) + 10);
		System.out.println("Size of dog is " + randomSize);
		return randomSize;// random size is
							// from 10-50. This
							// will
							// fit the map
							// because
							// radius is at most
							// 50,
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
		setChanged();
		notifyObservers();
	}

	void heal() {
		Iterator it = c.getIterator();
		while (it.hasNext()) {
			GameObject temp = it.getNext();
			if (temp instanceof Dog) {
				((Dog) temp).setScratches(((Dog) temp).getScratches() - 1);
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
		while (it.hasNext()) {
			GameObject temp = it.getNext();
			if (temp instanceof Net) {
				Iterator iter2 = c.getIterator();
				while (iter2.hasNext()) {
					GameObject otherObj = iter2.getNext();
					if (otherObj instanceof Animal) {
						if (temp.collidesWith(otherObj)) {
							if (otherObj instanceof Dog) {
								points += (10 - ((Dog) otherObj).getScratches());
								dogCaught++;
							} else {// has to be a cat if not a dog
								points -= 10;
								catCaught++;
							}
							otherObj.setFlag(true);// flag for removal
						}
					}
				}
			}
		}
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
		// print("flagged object removed");
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

	int kitten(Cat obj, Cat otherObj, int newCats) {
		if ((!(obj.isKitten()) && (!(otherObj).isKitten()))) {
			newCats++;
		}
		return newCats;
	}

	void fight(Dog d1, Cat c1) {
		int tempScratches = d1.getScratches();// grabs
												// current
		// scratches
		tempScratches++;// increments
						// current scratches
		d1.setScratches(tempScratches);// sets
										// new
										// incremented
										// scratches

		d1.setSpeed(0);
		d1.setC(randColor());
		// d1.setSize(d1.getSize() - 5);
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
			}
		}
		setChanged();
		notifyObservers();
	}

	int getTime() {
		return clock++;
	}

	void points() {
		print("\nCurrent score: " + points);
		print(dogCaught + " dogs and " + catCaught + " cats captured.");
		print(dogCount + " dogs and " + catCount + " cats left.");
		print("Game time:" + clock);
	}

	// void map() {
	// Iterator it = c.getIterator();
	// while (it.hasNext()) {
	// GameObject obj = it.getNext();
	// obj.printMe();
	// }
	//
	// }

	void print(String x) {// shortens "System.out.println();"
		System.out.println(x);
	}
}
