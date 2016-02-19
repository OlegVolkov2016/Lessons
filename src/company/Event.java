package company;

import java.util.Calendar;

public class Event extends Technology {
	
	// Event of the company
	private Calendar date;
	// Constant array to determine the names of months
	static String[] MONTH_NAMES = {"January","February","March","April","May","June","July","August","September","October","November","December"};

	public Event() {
		// TODO Auto-generated constructor stub
		date = Calendar.getInstance();
	}
	
	public Event(String name, Calendar date) {
		super(name);
		// TODO Auto-generated constructor stub
		this.date = Calendar.getInstance();
		this.date = (Calendar) date.clone();
//		this.date.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE));
	}
	
	public void set(String name, Calendar date) {
		super.set(name);
		// TODO Auto-generated constructor stub
		this.date = (Calendar) date.clone();
//		this.date.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE));
	}
	
	public void print() {
		System.out.println("\nEvent: "+getName()+" on "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR)+".");
	}
	
	public void edit (boolean isNew) {
		Calendar mydate = Calendar.getInstance();
		String str;
		boolean isMake = true;
		do {
			isMake = true;
			if (!isNew) System.out.println("Date of event: "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR));
			System.out.print("Enter new date of event (DD/MM/YYYY):");
			str = makeDateChoise();
			if (str.equals("")) 
				if (!isNew) mydate = getDate();
				else isMake = false;
			else
				mydate.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
		}
		while(!isMake);
		set(getName(),mydate);
	}
	
	// Determine is event on present date
	boolean isNow() {
		Calendar now = Calendar.getInstance();
		return ((getDate().get(Calendar.YEAR) == now.get(Calendar.YEAR)) && (getDate().get(Calendar.MONTH) == now.get(Calendar.MONTH)) && (getDate().get(Calendar.DATE) == now.get(Calendar.DATE)));
	}
	
	// Getters and setters
	public Calendar getDate() {
		Calendar newDate = Calendar.getInstance();
		newDate.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE));
		return newDate;
	}
	
	public void setDate(Calendar date) {
		this.date.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE));
	}
	
}
