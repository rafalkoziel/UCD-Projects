import java.lang.reflect.Array;

public class StackArray<E> {
	
	private E[] array;
	private int size=0;
	
	public StackArray(Class <E> element ,int length){
		array = (E[]) Array.newInstance(element, length);
	}
	public int push(E data) {
		for(int i=0;i<=size;i++) {
			if(array[i]==null) {
				array[i]=data;
				size++;
				return 1;
			}
		}
		return 0;
	}
	
	public E pop() {
		E temp=array[size-1];
		array[size-1]=null;
		size--;
		return(temp);
	}
	public E peek() {
		return (array[size-1]);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public String toString() {
		String str="";
		for(int i=0;i<size;i++) {
			str=str+array[i];
			if(i!=size-1) {
				str=str+"-->";
			}
		}
		return str;
	}
	public static void main(String[] args) {
		StackArray<String> stack = new StackArray<>(String.class,10);
		stack.push("Hey");
		stack.push("Hi");
		stack.push("Hello");
		stack.push("Test");
		System.out.println(stack);
		System.out.println(stack.size());
		System.out.println("Remove: "+stack.pop());
		System.out.println(stack);
		
	}
}
