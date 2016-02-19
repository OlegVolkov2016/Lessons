package company;

import java.util.Calendar;

public class Task extends Action {
	
	// Task of the company
	private String executor;

	public Task() {
		// TODO Auto-generated constructor stub
		executor = "";
	}

	public Task(String name, Calendar date, Calendar end, String executor) {
		super(name, date, end);
		// TODO Auto-generated constructor stub
		this.executor = executor;
	}
	
	public void set(String name, Calendar date, Calendar end, String executor) {
		super.set(name, date, end);
		// TODO Auto-generated constructor stub
		this.executor = executor;
	}
	
	public void print() {
		System.out.println("\nTask: "+getName()+" from "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR)+" to "+
				MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR)+".");
		System.out.println("Executor: "+getExecutor());
		if (isFinish())
			System.out.println("Finished.");
		else if (isNow())
			System.out.println("Continues.");
		else
			System.out.println("Not started yet.");
	}
	
	public void edit (boolean isNew) {
		Calendar mydate = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		String executor, str;
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
		do {
			isMake = true;
			if (!isNew) System.out.println("Executor: "+getExecutor());
			System.out.print("Enter new name of executor (only letters and .): ");
			executor = makeNameChoise();
			if (executor.equals("")) 
				if (!isNew) executor = getExecutor();
				else isMake = false;
		}
		while(!isMake);
		set(getName(),mydate,myend,executor);
	}
	
	// Getters and setters
	public String getExecutor() {
		return executor;
	}
	
	public void setExecutor(String executor) {
		this.executor = executor;
	}

}
