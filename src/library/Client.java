package library;

import java.util.Calendar;
import java.util.Scanner;

// The simplest class to determine simple client and basic input & output methods 

public class Client implements Template {
	
	// Client of library
	private String name;
	private double discount;
	// Uses one instance of common input scanner
	static Scanner sc = new Scanner(System.in);
	
	public Client() {
		// TODO Auto-generated constructor stub
		name = "";
	}
	
	public Client(String name, double discount) {
		this.name = name;
		this.discount = discount;		
	}
	
	@Override
	// To free scanner when finished
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		sc.close();
		super.finalize();
	}
	
	@Override
	public void set(Object obj) {
		// TODO Auto-generated method stub
		this.name = ((Client) obj).getName();
		this.discount = ((Client) obj).getDiscount();		
	}
	
	@Override
	public void firstInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print(String type) {
		// TODO Auto-generated method stub
		System.out.println("Name of "+type+": "+getName()+", discount: "+getDiscount()+"%.");
	}
	
	@Override
	public void edit(String type, boolean isNew) {
		// TODO Auto-generated method stub
		String name;
		double discount;
		name = inputName(type,isNew);
		discount = inputDiscount(type,isNew);
//		setName(name);
//		setDiscount(discount);
		set(new Client(name,discount));
	}
	
	public String inputName(String type, boolean isNew) {
		String name;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Name of "+type+": "+getName());
			System.out.print("Enter new name of "+type+" (only letters and .): ");
			name = makeNameChoise();
			if (name.equals("")) 
				if (!isNew) name = getName();
				else {
					System.out.println("Wrong name.");
					isMake = false;
				}
		}
		while(!isMake);
		return name;
	}
	
	public double inputDiscount(String type, boolean isNew) {
		double discount;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Current discount: "+getDiscount()+"%.");
			System.out.print("Enter new discount in % (from 0 to 100): ");
			discount = makeDoubleChoise(0,100);
			if (Double.isNaN(discount)) 
				if (!isNew) discount = getDiscount();
				else {
					System.out.println("Wrong value.");
					isMake = false;
				}
		}
		while(!isMake);
		return discount;
	}
	
	@Override
	public boolean menu(String type) {
		// TODO Auto-generated method stub
		int choise;
		System.out.println("\n<<<Menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - View info, 2 - Edit info, 0 - Exit): ");
		choise = makeIntChoise(0,2);
		if (choise == 1) print(type);
		if (choise == 2) edit(type,false);
		if (choise == 0) return false;
		return true;
	}
	
	// Getters and setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	// Simple methods of input confirmations, integer values in certain range, double values in certain range,
	// names consisting of letters and dots, any strings, and dates in DD/MM/YYYY format
	public boolean isRepeat(String question) {
		String answer;
		System.out.print(question);
		answer = sc.nextLine();
		if (answer.equals("")) return false;
		answer = answer.toLowerCase().substring(0, 1);
		if (answer.equals("y")) return true;
		else return false;
	}
	
	public int makeIntChoise (int from, int to) {
		String enter;
		int choise = Integer.MIN_VALUE;
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.equals("")) {
				choise = Integer.MIN_VALUE;
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
	
	public double makeDoubleChoise (double from, double to) {
		String enter;
		double choise = Double.NaN; 
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			if (enter.equals("")) {
				choise = Double.NaN;
				isMake = true;
			}
			else {
				try {
					choise = Double.parseDouble(enter);
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
			else if ((day < 1) || (day > Monthdays.values()[month].getCount())) isMake = false;
			if (!isMake)
				System.out.print("Wrong value. Enter again: ");
		}
		while (!isMake);
		return enter;
	}
	
	public String dateToString(Calendar date) {
		return Monthdays.values()[date.get(Calendar.MONTH)]+", "+date.get(Calendar.DATE)+" "+date.get(Calendar.YEAR);
	}

}
