package lesson8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Test8 {

	static ArrayList arr  = new ArrayList();
	
	static public void print () {
		Iterator itr = arr.iterator();
		while (itr.hasNext()) {

			System.out.print(itr.next()+" ");;

		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int min;
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			arr.add(rand.nextInt(9));
			System.out.print(arr.get(i)+" ");
		}
		System.out.println();
		Iterator itr = arr.iterator();
		while (itr.hasNext()) {

			System.out.print(itr.next()+" ");;

		}
		System.out.println();
		min = (int) arr.get(0);
		for (int i = 0; i < arr.size(); i++) {
			if ((int) arr.get(i) < min)
				min = (int) arr.get(i);
		}
		int count = 0;
		for (int i = 0; i < arr.size(); i++) {
			if ((int) arr.get(i) == min) {
				count++;
				if (count > 2) {
					arr.remove(i--);
				}
			}
		}
		itr = arr.iterator();
		while (itr.hasNext()) {

			System.out.print(itr.next()+" ");;

		}
		System.out.println();
		ArrayList<String> arr2 = new ArrayList<String>();
		arr2.add("dfjhkdf");
		arr2.add("hgfgfh");
		arr2.add("qrttt");
		arr.addAll(arr2);
		print();
		arr.add("Olga");
		print();
		arr.removeAll(arr2);
		print();
	}

}
