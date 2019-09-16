public class DoublyLinked<E> {
	
	public class Node<T> {	
		private E data;
		private Node<E> next;
		private Node<E> previous;
		

		public Node() {
			this.data=null;
			this.next=null;
			this.previous=null;
		}
		public Node(E data, Node<E> previous, Node<E> next) {
			this.data=data;
			this.next=next;
			this.previous=previous;
		}
		
		public Node<E> getNext() {
			return next;
		}

		public void setNext( Node<E> next) {
			this.next=next;
		}
		public Node<E> getPrevious() {
			return previous;
		}

		public void setPrevious( Node<E> previous) {
			this.previous=previous;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data=data;
		}


	}
	private Node<E> header=null;
	private Node<E> tailer= null;
	private int size=0;
	
	public DoublyLinked() {
		header= new Node<>(null,null,null);
		tailer=new Node<>(null,header,null);
		header.setNext(tailer);
	}
	public void addFirst(E data) {
		Node <E> add= new Node<E>();
		add.setData(data);
		if(header.getNext().getData()==null) {
			add.setNext(tailer);
			add.setPrevious(header);
			header.setNext(add);
			tailer.setPrevious(add);
			//System.out.println(header.getNext().getData());
		}
		else {
			add.setNext(header.getNext());
			Node<E> temp=header.getNext();
			temp.setPrevious(add);
			header.setNext(add);
		//	System.out.println(tailer.getPrevious().getPrevious().getData());
			
		}
		size++;
	}
	public void addLast(E data) {
		Node <E> add= new Node<E>();
		add.setData(data);
		if(tailer.getPrevious().getData()==null) {
			add.setPrevious(header);
			add.setNext(tailer);
			header.setNext(add);
			tailer.setPrevious(add);
			//System.out.println(tailer.getPrevious().getData());
			
		}
		else {
			add.setPrevious(tailer.getPrevious());
			Node<E> temp=tailer.getPrevious();
			temp.setNext(add);
			tailer.setPrevious(add);
			//System.out.println(header.getNext().getNext().getData());
		}
		size++;
	}
	public void add(int i, E data) {
		Node <E> add= new Node<E>();
		Node <E> prv=header;
		Node <E> current= new Node<E>();
		add.setData(data);
		if(i>size || i<0) {
			System.out.println("Element you are adding is out of bounds");
		}
		else if(i==1) {
			addFirst(data);
		}
		else if(i==size()) {
			addLast(data);
		}
		else{
			for(int j=0;j<i-1;j++) {
				prv=prv.getNext();
			}
			current=prv.getNext();
			prv.setNext(add);
			add.setPrevious(prv);
			current.setPrevious(add);
			add.setNext(current);
		}
		size++;
	}
	
	public E get(int i) {
		Node <E>temp=header;
		if(size<i) {
			return null;
		}
		else {
			for(int j=0;j<i;j++)
			{
				temp=temp.getNext();
			}
		}
		return temp.getData();
	}
	public E getFirst() {
		return header.getNext().getData();
	}
	public E getLast() {
		return tailer.getPrevious().getData();
	}
	
	public E remove(int i) {
		Node <E> remove= new Node<E>();
		Node <E> prv=header;
		Node <E> nxt= new Node<E>();
		if(i>size || i<0) {
			System.out.println("Element you are removing is out of bounds");
		}
		else if(i==1) {
			removeFirst();
		}
		else if(i==size()) {
			removeLast();
		}
		else {
			for(int j=0;j<i-1;j++) {
				prv=prv.getNext();
			}
			remove=prv.getNext();
			nxt=prv.getNext().getNext();
			prv.setNext(nxt);
			nxt.setPrevious(prv);
		}
		size--;
		return remove.getData();
	}
	public E removeFirst() {
		Node<E> remove=header.getNext();
		if(size==0) {
			System.out.println("double list is empty");
		}
		else {
			Node<E> temp= header.getNext().getNext();
			temp.setPrevious(header);
			header.setNext(temp);
		}
		size--;
		return remove.getData();
	}
	public E removeLast() {
		Node<E> remove=tailer.getPrevious();
		if(size==0) {
			System.out.println("double list is empty");
			return null;
		}
		else {
			Node<E> temp= tailer.getPrevious().getPrevious();
			temp.setNext(tailer);
			tailer.setPrevious(temp);
		}
		size--;
		return remove.getData();
	}
	public void traverseForward() {
		Node<E>temp=header;
		while(temp.getNext()!=null) {
			temp=temp.getNext();
			System.out.print(temp.getData());
			if(temp.getNext()!=null) {
				System.out.print("-->");
			}
		}
		System.out.println();
	}
	public void traverseBackward() {
		Node<E> temp=tailer;
		while(temp.getPrevious()!=null) {
			temp=temp.getPrevious();
			System.out.print(temp.getData());
			if(temp.getPrevious()!=null) {
				System.out.print("-->");
			}
		}
		System.out.println();
	}
	public int size() {
		return size;
	}
	public String toString(){
		Node<E> temp=header;
		String str="";
		while(temp.getNext()!=tailer.getPrevious()) {
			temp=temp.getNext();
			str=str+temp.getData();
			if(temp.getNext()!=null) {
				str=str+"<-->";
			}
		}
		str=str+temp.getNext().getData();
		return str;
	}
	
	public static void main(String[] args) {
		DoublyLinked<String> list = new DoublyLinked<String>();
		list.addLast("hello");
		list.addLast("hi");
		list.addFirst("hey");
		list.add(2, "test");
		System.out.println("Traversing forward: ");list.traverseForward();
		System.out.println("Traversing backward: ");list.traverseBackward();
		System.out.println("Whole list"+list);
		System.out.println("size is "+list.size());
		System.out.println("Get element as position 2: "+list.get(2));
		System.out.println("Remove at position 2");
		list.remove(2);
		System.out.println(list);
		System.out.println("size is "+list.size());
		list.removeFirst();
		System.out.println("Remove at 1st position ");
		System.out.println(list);
		System.out.println("size is "+list.size());
		
		
	}
	
}
