
public class CircularLinked<E> {
	public class Node<T> {
		private E data;
		private Node<E> next;

		public Node() {
			this.data=null;
			this.next=null;
		}
		
		public Node<E> getNext() {
			return next;
		}

		public void setNext( Node<E> next) {
			this.next=next;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data=data;
		}


	}
	
	public CircularLinked(){
		head=null;
	}
	private Node<E> head;
	private Node<E> tail;
	private int size=0;
	
	public void add(E data) {
		Node<E> add=new Node<E>();
		add.setData(data);
		add.setNext(tail);
		if(head==null) {
			head=add;
			tail=add;
		}
		else {
			head.setNext(add);
			head=add;
		}
		
		size++;
	}
	public Node<E> remove(){
		Node<E>remove=tail;
		tail=remove.getNext();
		head.setNext(tail);
		size--;
		return remove;
	}
	public int size() {
		return size;
	}
	public String toString() {
		String str="";
		int i=0;
		Node<E>temp=head;
		while(i<=1) {
			if(temp==head) {
				i++;
			}
			str=str+temp.getData();
			temp=temp.getNext();
			if(i<=1) {
				str=str+"-->";
			}
		}
		return str;
	}
	public static void main(String[] args) {
		CircularLinked<String> list = new CircularLinked<String>();
		list.add("Hey");
		list.add("Hi");
		list.add("Hello");
		list.add("test");
		list.add("works");
		System.out.println(list);
		System.out.println(list.size());
		list.remove();//Delete element that was added as first one
		System.out.println(list);
		System.out.println(list.size());
	}

}
