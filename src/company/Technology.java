package company;

import java.util.Scanner;

public class Technology {
	
	// The simplest class to determine simple technology, constants and basic input & output methods
	// Technology of the company
	private String name;
	// Constant array to determine the number of days in a month
	static final int[] MONTHDAYS = {31,28,31,30,31,30,31,31,30,31,30,31};
	// Uses one instance of common input scanner
	static Scanner sc = new Scanner(System.in);
	
	public Technology() {
		// TODO Auto-generated constructor stub
		name = "";
	}

	public Technology(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	@Override
	// To free scanner when finished
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		sc.close();
		super.finalize();
	}
	
	public void set(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public void print() {
		System.out.println("Technology: "+getName());
	}
	
	public void edit(boolean isNew) {
		// Empty method
	}
	
	// Getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Simple methods of input confirmations, integer values in certain range, double values in certain range,
	// names consisting of letters and dots, any strings, and dates in DD/MM/YYYY format
	public boolean isRepeat(String question) {
		String answer;
		System.out.print(question);
		answer = sc.nextLine();
		answer = answer.toLowerCase().substring(0, 1);
		if (answer.equals("y")) return true;
		else return false;
	}
	
	public int makeIntChoise (int from, int to) {
		String enter;
		int choise = -1; 
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.equals("")) {
				choise = -1;
				isMake = true;
			}
			else {
				try {
					choise = Integer.parseInt(enter);
					if ((choise >= from) && (choise <= to))
						isMake = true;
					else System.out.print("Wrong value. Enter again: ");
				}
				catch (NumberFormatException e) {
					System.out.print("Not a value. Enter again: ");
				}
			}
		}
		while (!isMake);
		return choise;
	}
	
	public String makeNameChoise () {
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.equals("")) isMake = true;
			else if (enter.length() > 0) {
				isMake = true;
				for (int i = 0; i < enter.length(); i++) {
					if (((enter.toUpperCase().charAt(i) < 'A') || (enter.toUpperCase().charAt(i) > 'Z')) &&
							(enter.toUpperCase().charAt(i) != '.') && (enter.toUpperCase().charAt(i) != ' ')) {
						System.out.print("Wrong value. Enter again: ");
						isMake = false;
					}
				if (!isMake) break;
				}
			}
		}
		while (!isMake);
		return enter;
	}
	
	public String makeStringChoise () {
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			isMake = true;
		}
		while (!isMake);
		return enter;
	}

	public String makeDateChoise () {
		int day, month;
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.length() == 0) {
				isMake = true;
				break;
			}
			else if (enter.length() == 10)
				for (int i = 0; i < enter.length(); i++) {
					isMake = true;
					if ((i == 2) || (i == 5))
						if (enter.toUpperCase().charAt(i) != '/')
							isMake = false;
						else;
					else if ((enter.toUpperCase().charAt(i) < '0') || (enter.toUpperCase().charAt(i) > '9'))
						isMake = false;
					if (!isMake) break;
				}
			day = Integer.parseInt(enter.substring(0,2));
			month = Integer.parseInt(enter.substring(3,5))-1;
			if ((month < 0) || (month > 11)) isMake = false;
			else if ((day < 1) || (day >  MONTHDAYS[month])) isMake = false;
			if (!isMake)
				System.out.print("Wrong value. Enter again: ");
		}
		while (!isMake);
		return enter;
	}

}
