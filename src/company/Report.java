package company;

import java.util.Calendar;

public class Report extends Task {
	
	// Report of the company
	private int percent;
	private int mark;

	public Report() {
		// TODO Auto-generated constructor stub
		percent = 0;
		mark = 0;
	}

	public Report(String name, Calendar date, Calendar end, String executor, int percent, int mark) {
		super(name, date, end, executor);
		// TODO Auto-generated constructor stub
		this.percent = percent;
		this.mark = mark;
	}

	public void set(String name, Calendar date, Calendar end, String executor, int percent, int mark) {
		super.set(name, date, end, executor);
		// TODO Auto-generated constructor stub
		this.percent = percent;
		this.mark = mark;
	}	
	
	public void print() {
		System.out.println("\nReport on task: "+getName()+" from "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR)+" to "+
				MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR)+".");
		System.out.println("Executor: "+getExecutor()+", percentage: "+getPercent()+" %.");
		if (isFinish())
			System.out.println("Result mark is "+getMark()+".");
		else if (isNow())
			System.out.println("Current mark is "+getMark()+".");
		else
			System.out.println("Not started yet.");
	}
	
	public void edit (boolean isNew) {
		Calendar mydate = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		String str;
		int percent, mark;
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
			if (!isNew) System.out.println("Percent of executing: "+getPercent());
			System.out.print("Enter new percent of executing (0 to 100): ");
			percent = makeIntChoise(0,100);
			if (percent == -1) 
				if (!isNew) percent = getPercent();
				else isMake = false;
		}
		while(!isMake);
		do {
			isMake = true;
			if (!isNew) System.out.println("Mark of executing: "+getMark());
			System.out.print("Enter new mark of executing (1 to 12): ");
			mark = makeIntChoise(1,12);
			if (mark == -1) 
				if (!isNew) mark = getMark();
				else isMake = false;
		}
		while(!isMake);
		set(getName(),mydate,myend,getExecutor(),percent,mark);
	}

	// Getters and setters
	public int getPercent() {
		return percent;
	}
	
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	public int getMark() {
		return mark;
	}
	
	public void setMark(int mark) {
		this.mark = mark;
	}
	
}
