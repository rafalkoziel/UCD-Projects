import java.lang.reflect.Array;

public class QueueArray<E>{
	
	private E[] array;
	private int size=0;
	
	public QueueArray(Class <E> element ,int length){
		array = (E[]) Array.newInstance(element, length);
	}
	public void add(E data) {
		array[size]=data;
		size++;
	}
	
	public E remove() {
		E temp=array[0];
		for(int i=0;i<size;i++) {
			array[i]=array[i+1];
		}
		size--;
		
		return(temp);
	}
	public E peek() {
		return (array[0]);
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
		QueueArray<String> queue = new QueueArray<>(String.class,10);
		queue.add("Hey");
		queue.add("Hi");
		queue.add("Hello");
		queue.add("Test");
		queue.add("Test");
		System.out.println(queue);
		System.out.println(queue.size());
		System.out.println("Remove: "+queue.remove());
		System.out.println(queue);
		
	}
}


