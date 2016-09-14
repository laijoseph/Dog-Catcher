package a3;

import java.util.Vector;

class MyCollection implements ICollection {

	private Vector<GameObject> objVector;

	public MyCollection() {
		objVector = new Vector<GameObject>();
	}

	@Override
	public void add(GameObject newObject) {
		objVector.addElement(newObject);
	}

	public int size() {
		return objVector.size();
	}

	@Override
	public Iterator getIterator() {
		return new objIterator();
	}

	class objIterator implements Iterator {
		private int index;

		public objIterator() {
			index = -1;
		}

		@Override
		public boolean hasNext() {
			if (objVector.size() <= 0)
				return false;
			else if (index == (objVector.size() - 1))
				return false;
			else
				return true;
		}

		@Override
		public GameObject getNext() {
			index++;
			return objVector.elementAt(index);
		}

		@Override
		public void del() {
			if (index > -1)
				objVector.remove(index);

		}

		public void rm(Object obj) {
			objVector.remove(obj);
		}

	}

	@Override
	public java.util.Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
