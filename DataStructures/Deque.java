
public class Deque<E> extends SinglyLinked<E> {
	public Deque() {};
	
	public void addHead(E data) {
		addFirst(data);
	}
	public void addTail(E data) {
		addLast(data);
	}
	public E removeHead() {
		return removeFirst();
	}
	public E removeTail() {
		return removeLast();
	}
	public E getHead() {
		return getFirst();
	}
	public E getTail() {
		return getLast();
	}
	
	
	
	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();
		deque.addHead("hi");
		deque.addHead("hey");
		deque.addTail("hello");
		System.out.println(deque);
		System.out.println(deque.size());
		deque.removeTail();
		System.out.println(deque);
		deque.removeHead();
		System.out.println(deque);
		
	}
}
