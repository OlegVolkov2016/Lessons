package lesson9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class Test99 {

	static HashSet<String> hs;
	
	public static void main(String[] args) {
		hs = new HashSet<String>();
		hs.add("D");
		hs.add("E");
		hs.add("C");
		hs.add("E");
		hs.add("C");
		hs.add("D");
		Iterator itr = hs.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next()+" ");
		}
		TreeSet<String> ts = new TreeSet<String>();
		ts.add("D");
		ts.add("E");
		ts.add("C");
		ts.add("E");
		ts.add("C");
		ts.add("D");
		Iterator itr1 = ts.iterator();
		while (itr1.hasNext()) {
			System.out.println(itr1.next()+" ");
		}
		
	}
}
