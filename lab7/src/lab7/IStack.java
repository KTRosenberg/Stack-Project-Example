package lab7;

public interface IStack<E> {
	
	public void push(E el);
	
	public E pop();
	
	public E top();
	
	public boolean isEmpty();
	
	public int size();
	
	//public void clear();
}
