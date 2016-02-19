package company;

import java.util.Calendar;

public class Action extends Event {
	
	// Action of the company
	private Calendar end;

	public Action() {
		// TODO Auto-generated constructor stub
		end = Calendar.getInstance();
	}

	public Action(String name, Calendar date, Calendar end) {
		super(name, date);
		// TODO Auto-generated constructor stub
		this.end = Calendar.getInstance();
		this.end.set(end.get(Calendar.YEAR),end.get(Calendar.MONTH),end.get(Calendar.DATE));
	}
	
	public void set(String name, Calendar date, Calendar end) {
		super.set(name, date);
		// TODO Auto-generated constructor stub
		this.end.set(end.get(Calendar.YEAR),end.get(Calendar.MONTH),end.get(Calendar.DATE));
	}
	
	public void print() {
		System.out.println("\nAction: "+getName()+" from "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR)+" to "+
				MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR)+".");
	}
	
	public void edit (boolean isNew) {
		Calendar mydate = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		String str;
		boolean isMake = true;
		do {
			isMake = true;
			if (!isNew) System.out.println("Date of start: "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR));
			System.out.print("Enter new date of start (DD/MM/YYYY):");
			str = makeDateChoise();
			if (str.equals("")) 
				if (!isNew) mydate = getDate();
				else isMake = false;
			else
				mydate.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
		}
		while(!isMake);
		do {
			isMake = true;
			if (!isNew) System.out.println("Date of end: "+MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR));
			System.out.print("Enter new date of end (DD/MM/YYYY):");
			str = makeDateChoise();
			if (str.equals("")) 
				if (!isNew) myend = getEnd();
				else isMake = false;
			else
				myend.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
		}
		while(!isMake);
		set(getName(),mydate,myend);
	}

	// Determine is action continues on present date
	boolean isNow() {
		Calendar now = Calendar.getInstance();
		if ((getDate().get(Calendar.YEAR) < now.get(Calendar.YEAR)) || ((getDate().get(Calendar.YEAR) == now.get(Calendar.YEAR)) && (getDate().get(Calendar.MONTH) < now.get(Calendar.MONTH))) ||
			((getDate().get(Calendar.YEAR) == now.get(Calendar.YEAR)) && (getDate().get(Calendar.MONTH) == now.get(Calendar.MONTH)) && (getDate().get(Calendar.DATE) <= now.get(Calendar.DATE))))
			if ((getEnd().get(Calendar.YEAR) > now.get(Calendar.YEAR)) || ((getEnd().get(Calendar.YEAR) == now.get(Calendar.YEAR)) && (getEnd().get(Calendar.MONTH) > now.get(Calendar.MONTH))) ||
					((getEnd().get(Calendar.YEAR) == now.get(Calendar.YEAR)) && (getEnd().get(Calendar.MONTH) == now.get(Calendar.MONTH)) && (getEnd().get(Calendar.DATE) >= now.get(Calendar.DATE))))
				return true;
		return false;
	}

	// Determine is action finished on present date
	boolean isFinish() {
		Calendar now = Calendar.getInstance();
		if ((getEnd().get(Calendar.YEAR) < now.get(Calendar.YEAR)) || ((getEnd().get(Calendar.YEAR) == now.get(Calendar.YEAR)) && (getEnd().get(Calendar.MONTH) < now.get(Calendar.MONTH))) ||
			((getEnd().get(Calendar.YEAR) == now.get(Calendar.YEAR)) && (getEnd().get(Calendar.MONTH) == now.get(Calendar.MONTH)) && (getEnd().get(Calendar.DATE) < now.get(Calendar.DATE))))
			return true;
		return false;
	}
	
	// Getters and setters
	public Calendar getEnd() {
		Calendar newEnd = Calendar.getInstance();
		newEnd.set(end.get(Calendar.YEAR),end.get(Calendar.MONTH),end.get(Calendar.DATE));
		return newEnd;
	}
	
	public void setEnd(Calendar end) {
		this.end.set(end.get(Calendar.YEAR),end.get(Calendar.MONTH),end.get(Calendar.DATE));
	}

}
