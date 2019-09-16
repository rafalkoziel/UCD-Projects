
public class Queue<E>extends SinglyLinked<E>  {
	
	public Queue() {
	}
	public void add(E data) {
		addLast(data);
	}
	public E remove() {
		return removeFirst();
	}
	public E element() {
		return getFirst();
	}
	
	public static void main(String[] args) {
		Queue<String> queue = new Queue<String>();
		queue.add("hi");
		queue.add("hey");
		queue.add("hello");
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println(queue.element());
		System.out.println(queue.remove());
	
	}
	

}
