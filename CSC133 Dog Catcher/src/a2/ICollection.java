package a2;

public interface ICollection extends Iterable {
	public void add(GameObject newObject);

	public Iterator getIterator();

}