
import java.util.*;


public class PriorityQueue<E>{
	private ArrayList<E> list;
	private int size=0;
	private Comparator<E> comparator;
	
	public PriorityQueue() {
		list= new ArrayList<E>(100);
	}
	public PriorityQueue(Comparator<E> comparator) {
		list= new ArrayList<E>(100);
		this.comparator=comparator;
	}
	public void add(E element) {
		list.add(element);
		size++;
		int i=size-1;
		int j=0;
		
		while (i-1 >= 0 && comparator.compare(list.get(i-1), element) < 0){
			j=i;
			Collections.swap(list, i-1,j);
			i--;
		}
	}
	public boolean isEmpty() {
		return size()==0;
	}
	public E min() {
		return list.get(0);
	}
	public E removeMin() {
		E temp=list.get(0);
		int j;
		for(int i=0;i<size-1;i++) {
			j=i+1;
			Collections.swap(list, i,j);
		}
		return temp;
	}
	public int size() {
		return size;
	}
	
	public void print() {
		System.out.println(Arrays.toString(list.toArray()));
	}
	
	
	public static void main(String[] args){
		// Creating Priority queue
		Comparator<Students> comparator = new CompareStudents();
		PriorityQueue <Students> pq = new PriorityQueue <Students>(comparator);
		
		// Invoking a parameterised Student constructor with
		Students student1 = new Students("Nataly Ware", 21, 4.0);
		Students student2 = new Students("Mira Ware", 19, 3.5);
		Students student3 = new Students("Emilie Gibbs", 20, 3.2);
		Students student4 = new Students("Lisa Boone",22, 4.7);
		Students student5 = new Students("Karsyn Terry", 20, 4.8);
		Students student6 = new Students("Jeremy Schwartz", 18, 4.6);
		Students student7 = new Students("Aleah Gaines", 19, 4.1);
		Students student8 = new Students("Arianna Reeves", 20, 3.9);
		Students student9 = new Students("Walker Holloway", 22, 3.8);
		Students student10 = new Students("Adelyn Walter",24, 4.95);
		Students student11 = new Students("Damion Sanders", 25, 3.2);
		Students student12 = new Students("Aimee Quinn", 21, 2.7);
		
		pq.add(student1);
		pq.add(student2);
		pq.add(student3);
		pq.add(student4);
		pq.add(student5);
		pq.add(student6);
		pq.add(student7);
		pq.add(student8);
		pq.add(student9);
		pq.add(student10);
		pq.add(student11);
		pq.add(student12);
		pq.print(); // Printing names of students in priority order
		pq.size();
		System.out.println(pq.min().getGPA());
		pq.removeMin();
		pq.print();
		
	}
}


	
