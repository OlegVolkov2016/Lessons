package lesson8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Group {
	
	public static void print(ArrayList<Student> group) {
		for (int i = 0; i < group.size(); i++) {
			System.out.print(group.get(i).name+" "+group.get(i).course+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Student s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11;
		ArrayList<Student> students = new ArrayList<>(); 
		String str;
		s1 = new Student("A", "AA", 1980, "Eng");
		s2 = new Student("B", "BB", 1981, "Fra");
		s3 = new Student("C", "CC", 1982, "Eng");
		s4 = new Student("D", "DD", 1983, "Det");
		s5 = new Student("E", "EE", 1984, "Fra");
		s6 = new Student("F", "FF", 1985, "Det");
		s7 = new Student("G", "GG", 1986, "Eng");
		s8 = new Student("H", "HH", 1987, "Eng");
		s9 = new Student("I", "II", 1988, "Eng");
		s10 = new Student("J", "JJ", 1989, "Fra");
		s11 = new Student("K", "KK", 1990, "Det");
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		students.add(s5);
		students.add(s6);
		students.add(s7);
		students.add(s8);
		students.add(s9);
		students.add(s10);
		students.add(s11);
		ArrayList<Student>[] groups = new ArrayList[10];
		groups[0] = new ArrayList<Student>();
		ArrayList<Student> group1 = new ArrayList<Student>();
		ArrayList<Student> group2 = new ArrayList<Student>();
		ArrayList<Student> group3 = new ArrayList<Student>();
		ArrayList<Student> group4 = new ArrayList<Student>();
		ArrayList<Student> group5 = new ArrayList<Student>();
		ArrayList<Student> group6 = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++) {
			str = students.get(i).course;
			if (str.equals("Eng")) {
				if ((i % 2) == 0)
					group1.add(students.get(i));
				else group2.add(students.get(i));
			}
			if (str.equals("Fra")) {
				if ((i % 2) == 0)
					group3.add(students.get(i));
				else group4.add(students.get(i));
			}
			if (str.equals("Det")) {
				if ((i % 2) == 0)
					group5.add(students.get(i));
				else group6.add(students.get(i));
			}		
		}
		print(group1);
		print(group2);
		print(group3);
		print(group4);
		print(group5);
		print(group6);
		LinkedList<Student> students2 = new LinkedList<>();
		students2.addAll(0, students);
		Iterator<Student> itr = students2.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next().name+" ");
		}
		System.out.println();
		itr = students2.descendingIterator();
		while (itr.hasNext()) {
			System.out.print(itr.next().name+" ");
		}
		System.out.println();
	}
}
