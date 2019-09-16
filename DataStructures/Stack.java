public class Stack<E> extends SinglyLinked<E> {
	private int size=0;
	public Stack() {
	}
	public void push(E node) {
		addLast(node);
		size++;
	}
	public E pop() {
		size--;
		return removeLast();
	}
	public E peek() {
		return getLast();
	}
	public boolean isEmpty() {
		return size==0;
	}

	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("hello");
		stack.push("hi");
		stack.push("hey");
		stack.push("test");
		System.out.println(stack);
		System.out.println(stack.size());
		stack.pop();
		System.out.println(stack);
		System.out.print(stack.peek());
	}
	
}
