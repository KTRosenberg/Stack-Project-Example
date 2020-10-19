package draw_example.ds;

public class ArrayStack<E> implements IStack<E> {
	
	Object[] array;
	int cursor;
	
	
	public static int INITIAL_SIZE_DEFAULT = 16;
	
	
	ArrayStack() {
		this.array = new Object[ArrayStack.INITIAL_SIZE_DEFAULT];
		this.cursor = 0;
	}
	
	ArrayStack(int initialSize) {
		this.array = new Object[initialSize];
		this.cursor = 0;
	}

	private void resizeTo(int newSize) {
		Object[] newArray = new Object[newSize];
		
		System.arraycopy(
			this.array, 0, 
			newArray, 0, 
			this.array.length
		);
		
		this.array = newArray;		
	}
	
	@Override
	public void push(E el) {
		
		if (cursor >= array.length) {
			this.resizeTo(this.array.length * 2);
		}
		
		this.array[this.cursor] = el;
		this.cursor += 1;
	}

	@Override
	public E pop() {
		
		if (this.cursor <= 0) {
			throw new IndexOutOfBoundsException(
				"ERROR: stack is empty"
			);
		}
		
		@SuppressWarnings("unchecked")
		E el = (E)this.array[this.cursor - 1];
		this.cursor -= 1;
		
		return el;
	}

	@Override
	public E top() {
		if (this.cursor <= 0) {
			throw new IndexOutOfBoundsException(
				"ERROR: stack is empty"
			);
		}	
		
		@SuppressWarnings("unchecked")
		E el = (E)this.array[this.cursor - 1];
		
		return el;
	}

	@Override
	public boolean isEmpty() {
		return (this.cursor == 0);
	}

	@Override
	public int size() {
		return this.cursor;
	}

}
