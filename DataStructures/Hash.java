import java.util.*;
import java.io.*;
public class Hash {
		public static int polyHashCode(String s, int a) {	
			int result = 0;
				    for (int i=0; i<s.length(); i++) {
				     result = (a*result) + s.charAt(i);
				 }
				 return result;
				 
		}
		public static int cyclicShift( String s) {
			int h = 0;
			 for(int i = 0; i < s.length(); ++i) {
			 h = (h << 7) | (h >>> 25);
			 h += (int) s.charAt(i);
			 }
			 return h; 
		}
		public static int javaHashCode(String s) {
			int hash=0;
			int skip= Math.max(1, s.length()/8);
			for(int i=0;i<s.length();i+=skip) {
				hash=(hash*37)+s.charAt(i);
			}
			return hash;
		}
	 public static void main(String[] args) throws IOException {
		 	
		 	int collision=0;
		    
		 	File file = new File("src/words.txt");
		    
		 	ArrayList<String> list= new ArrayList();
		    
		 	Scanner scan = new Scanner(file); 
		 
		 	while (scan.hasNext()) {
		      list.add(scan.next());
		    }
		 	HashMap<String, Integer> map=new HashMap<>();
		 	for(String temp:list) {
		 		if(!map.containsKey(temp)) {
		 			map.put(temp, 1);
		 		}
		 	}
		 	HashMap<Integer, Integer> map2=new HashMap<>();
		    scan.close();
		    for(HashMap.Entry<String, Integer> temp:map.entrySet()) {
		    	 if(!map2.containsKey(cyclicShift(temp.getKey()))) {
		    		 map2.put(cyclicShift(temp.getKey()),1);
		    	 }
		    	 else {
		    		 collision++;
		    	 }
		    }
		    System.out.println("Cyclic Shift: "+collision);
		    collision=0; 
		    map2.clear();
		    for(HashMap.Entry<String, Integer> temp:map.entrySet()) {
		    	 if(!map2.containsKey(polyHashCode(temp.getKey(),17))) {
		    		 map2.put(polyHashCode(temp.getKey(),17),1);
		    	 }
		    	 else {
		    		 collision++;
		    	 }
		  }
		    System.out.println("Polynomial Hash Code(x=17): "+collision);
		    collision=0; 
		    map2.clear();
		    for(HashMap.Entry<String, Integer> temp:map.entrySet()) {
		    	 if(!map2.containsKey(javaHashCode(temp.getKey()))) {
		    		 map2.put(javaHashCode(temp.getKey()),1);
		    	 }
		    	 else {
		    		 collision++;
		    	 }
		  }
		    System.out.println("Java Hash Function: "+collision);
	 }
	 
}
	 
