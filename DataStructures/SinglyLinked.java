public class SinglyLinked<E> {
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
	
	private Node<E> head;
	private int size=0;

	public SinglyLinked() {
		head =null;
	}
	
	public void add(int i,E data) {
		Node<E> add_node = new Node<E>();
		add_node.setData(data);
		Node<E> temp= head;
		int j=0;
		if(i==0) {
			addFirst(data);
		}
		else if(i==1) {
			temp=head.getNext();
			//System.out.print("hello");
			head.setNext(add_node);
			add_node.setNext(temp);
			size++;
		}
		else {
			while(j<i-1){
				temp=temp.getNext();
				j++;
			}
			add_node.setNext(temp.getNext());
			temp.setNext(add_node);
			size++;
		}
		
	}

	public void addLast(E data) {
		int i=size;
		int j=0;
		Node<E> add_node = new Node<E>();
		add_node.setData(data);
		Node<E> temp = head;
		if(i==0) {
			addFirst(data);
		}
		else if(i==1) {
			add(1,data);
		}
		else {
			while(j<i-1) {
				temp=temp.getNext();
				j++;
			}
			temp.setNext(add_node);
			size++;
		}
		
	}

	public void addFirst(E data) {
		Node<E> add_node = new Node<E>();
		add_node.setData(data);
		if(head==null) {
			head=add_node;
		}
		else {
			add_node.setNext(head);
			head=add_node;
		}
		size++;
	}

	public E remove(int i) {
		Node<E> temp = head;
		int j=0;
		if(i==0) {
			removeFirst();
		}
		else if(i==1) {
			temp=head.getNext().getNext();
			head.setNext(temp);
			size--;
		}
		else {
			while(j<i-1) {
			temp=temp.getNext();
			j++;
			}
			temp.setNext(temp.getNext().getNext());
			size--;
		}
		return temp.getNext().getData();
			
		
		
	}
	public E removeFirst(){
		Node<E> temp = head;
		head=head.getNext();
		size--;
		return temp.getData();
	}
	public E removeLast(){
		Node<E> temp = head;
		Node<E> remove = null;
		while(temp.getNext().getNext()!=null) {
			temp=temp.getNext();
		}
		remove=temp.getNext();
		temp.setNext(null);
		size--;
		return remove.getData();
	}
	public E get(int i){
		Node<E> temp = head;
		int j=0;
		if(i==0) {
			getFirst();
		}
		else{
			while(j<i) {
				temp=temp.getNext();
				j++;
			}
			
		}
		return temp.getData();
	}
	public E getFirst(){
		return head.getData();

	}
	public E getLast(){
		Node<E> temp = head;
		while(temp.getNext()!=null) {
			temp=temp.getNext();
		}
		return temp.getData();
	}
	public String toString() {
		Node<E> temp=head;
		String str="";
		while(temp!=null) {
			str=str+temp.getData();
			temp=temp.getNext();
			if(temp!=null) {
				str+=" --> ";
			}
		}
		return str;
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {
		SinglyLinked<String> list = new SinglyLinked<String>();
		list.addFirst("Test");
		list.addLast("teesst");
		list.addLast("hello");
		System.out.println(list);
		list.add(1,"check");
		System.out.println(list);
		list.remove(1);
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
		list.removeFirst();
		System.out.println(list);
	}
}
