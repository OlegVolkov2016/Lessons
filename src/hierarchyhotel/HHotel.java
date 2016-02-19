package hierarchyhotel;

import java.util.Scanner;

public class HHotel {
	
	// The simplest class to determine simple hotel, constants and basic input & output methods 
	private String name;
	// Constant array to determine the number of days in a month
	static final int[] MONTHDAYS = {31,28,31,30,31,30,31,31,30,31,30,31};
	// Uses one instance of common input scanner
	static Scanner sc = new Scanner(System.in);
	
	public HHotel() {
		// TODO Auto-generated constructor stub
		name = "";
	}
	
	public HHotel(String name) {
		this.name = name;
	}
	
	public void set(String name) {
		setName(name);
	}
	
	@Override
	// To free scanner when finished
	protected void finalize() throws Throwable {
		sc.close();
		super.finalize();
	};
	
	// Getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	// Simple methods of input confirmations, integer values in certain range,
	// names, phones and dates in DD/MM format
	boolean isRepeat(String question) {
		String answer;
		System.out.print(question);
		answer = sc.nextLine();
		answer = answer.toLowerCase().substring(0, 1);
		if (answer.equals("y")) return true;
		else return false;
	}
	
	int makeIntChoise (int from, int to) {
		String enter;
		int choise = -1; 
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			try {
				choise = Integer.parseInt(enter);
				if ((choise >= from) && (choise <= to))
					isMake = true;
				else System.out.println("Wrong value. Enter again.");
			}
			catch (NumberFormatException e) {
				System.out.println("Not a value. Enter again.");
			}
		}
		while (!isMake);
		return choise;
	}
	
	String makeNameChoise () {
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.length() > 0) {
				isMake = true;
				for (int i = 0; i < enter.length(); i++) {
					if (((enter.toUpperCase().charAt(i) < 'A') || (enter.toUpperCase().charAt(i) > 'Z')) &&
							(enter.toUpperCase().charAt(i) != '.') && (enter.toUpperCase().charAt(i) != ' ')) {
						System.out.println("Wrong value. Enter again.");
						isMake = false;
					}
				if (!isMake) break;
				}
			}
			else System.out.println("Wrong value. Enter again.");
		}
		while (!isMake);
		return enter;
	}
	
	String makePhoneChoise () {
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.length() == 9) {
				isMake = true;
				for (int i = 0; i < enter.length(); i++) {
					if ((i == 3) || (i == 6))
						if (enter.toUpperCase().charAt(i) != '-') {
						System.out.println("Wrong value. Enter again.");
						isMake = false;
						}
						else;
					else if ((enter.toUpperCase().charAt(i) < '0') || (enter.toUpperCase().charAt(i) > '9')) {
						System.out.println("Wrong value. Enter again.");
						isMake = false;
					}
					if (!isMake) break;	
				}
			}
			else System.out.println("Wrong value. Enter again.");
		}
		while (!isMake);
		return enter;
	}
	
	String makeDateChoise () {
		String enter;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.length() == 5)
				for (int i = 0; i < enter.length(); i++) {
					isMake = true;
					if (i == 2) 
						if (enter.toUpperCase().charAt(i) != '/')
							isMake = false;
						else;
					else if ((enter.toUpperCase().charAt(i) < '0') || (enter.toUpperCase().charAt(i) > '9'))
						isMake = false;
					if (!isMake) break;
				}
			if (!isMake)
				System.out.println("Wrong value. Enter again.");
		}
		while (!isMake);
		return enter;
	}
	
	// Operations with dates in format DD/MM or integer value of day number in the year
	int toDate (String strDate) {
		int intDate = -1, month, day;
		boolean isRight = false;
		if (strDate.length() == 5) {
			for (int i = 0; i < strDate.length(); i++) {
				isRight = true;
				if (i == 2) 
					if (strDate.toUpperCase().charAt(i) != '/')
						isRight = false;
					else;
				else if ((strDate.toUpperCase().charAt(i) < '0') || (strDate.toUpperCase().charAt(i) > '9'))
					isRight = false;
				if (!isRight) break;
			}
			month = Integer.parseInt(strDate.substring(3,5));
			if ((month >= 1) && (month <= 12)) {
				day = Integer.parseInt(strDate.substring(0,2));
				if ((day >=1) && (day <= MONTHDAYS[month-1])) {
					intDate = 0;
					for (int i = 0; i < month-1; i++) {
						intDate+=MONTHDAYS[i];
					}
					intDate+=day;
				}
			}
		}
		if (isRight) return intDate;
		else return -1;
	}

	String toString(int intDate) {
		String strDate = "";
		int day = intDate;
		boolean isRight = false;
		if ((day >= 1) && (day <= 365)) {
			for (int i = 0; i < 11; i++) {
				if (day > MONTHDAYS[i])
					day-=MONTHDAYS[i];
				else {
					if (day < 10) strDate+='0';
					strDate+=day+"/";
					if (i < 9) strDate+='0';
					strDate+=(i+1)+"";
					isRight = true;
				}
				if (isRight) break;
			}
		}
		return strDate;
	}
	
}
