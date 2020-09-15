package il.co.ilrd.GenericLinkedList;

public interface FailFastIterator<T> {

	public boolean hasNext();
	
	public T next();
	
	public T getData();
}
