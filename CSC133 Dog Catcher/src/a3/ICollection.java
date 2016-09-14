package a3;

public interface ICollection extends Iterable {
	public void add(GameObject newObject);

	public Iterator getIterator();

}