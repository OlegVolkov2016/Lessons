package company;

import java.util.Calendar;

public class Project extends Report {
	
	// Project of the company
	// Project fields
	final int MAXEXECUTORS = 10, MAXREPORTS = 20, MAXTECHNOLOGIES = 10;
	private int executorsAmount;
	private String[] executors;
	private int reportsAmount;
	private Report[] reports;
	private int technologiesAmount;
	private Technology[] technologies;

	public Project() {
		// TODO Auto-generated constructor stub
		executorsAmount = 0;
		executors = new String[MAXEXECUTORS];
		reportsAmount = 0;
		reports = new Report[MAXREPORTS];
		technologiesAmount = 0;
		technologies = new Technology[MAXTECHNOLOGIES];
	}

	public Project(String name, Calendar date, Calendar end, String executor, int percent, int mark, boolean isEmpty) {
		super(name, date, end, executor, percent, mark);
		// TODO Auto-generated constructor stub
		executorsAmount = 0;
		executors = new String[MAXEXECUTORS];
		reportsAmount = 0;
		reports = new Report[MAXREPORTS];
		technologiesAmount = 0;
		technologies = new Technology[MAXTECHNOLOGIES];
		if (!isEmpty)
			firstInit();
	}
	
	public void set(String name, Calendar date, Calendar end, String executor, int percent, int mark, boolean isEmpty) {
		super.set(name, date, end, executor, percent, mark);
		// TODO Auto-generated constructor stub
		executorsAmount = 0;
		executors = new String[MAXEXECUTORS];
		reportsAmount = 0;
		reports = new Report[MAXREPORTS];
		technologiesAmount = 0;
		technologies = new Technology[MAXTECHNOLOGIES];
		if (!isEmpty)
			firstInit();
	}
	
	// First initializing
	public void firstInit() {
		Calendar date = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		executorsAmount = 2;
		executors[0] = "Johnson";
		executors[1] = "Maxwell";
		reportsAmount = 3;
		date.set(2015, 6, 1);
		end.set(2015, 10, 1);
		reports[0] = new Report("Design",date,end,executors[0],10,5);
		date.set(2015, 10, 1);
		end.set(2015, 11, 15);
		reports[1] = new Report("Drawing",date,end,executors[1],15,4);
		date.set(2015, 11, 1);
		end.set(2016, 0, 15);
		reports[2] = new Report("Calculating",date,end,executors[0],0,0);
		technologiesAmount = 3;
		technologies[0] = new Technology("Java");
		technologies[1] = new Technology("Modeling");
		technologies[2] = new Technology("Constructing");
	}
	
	public void print() {
		System.out.println("\nProject: "+getName()+" from "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR)+" to "+
				MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR)+".");
		System.out.println("Director: "+getExecutor());
		System.out.println("Percentage is: "+getPercent()+" %.");
		if (isFinish())
			System.out.println("Result mark is "+getMark()+".");
		else if (isNow())
			System.out.println("Current mark is "+getMark()+".");
		else
			System.out.println("Not started yet.");
//		System.out.println("\n\tExecutors: ");
//		for (int i = 0; i < getExecutorsAmount(); i++)
//			System.out.println(getExecutors()[i]);
//		System.out.println("\n\tReports: ");
//		for (int i = 0; i < getReportsAmount(); i++)
//			getReports()[i].print();
//		System.out.println("\n\tTechnologies: ");
//		for (int i = 0; i < getTechnologiesAmount(); i++)
//			getTechnologies()[i].print();
	}
	
	public void edit (boolean isNew) {
		Calendar mydate = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		String executor, str;
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
			if (!isNew) System.out.println("Director: "+getExecutor());
			System.out.print("Enter new name of director (only letters and .): ");
			executor = makeNameChoise();
			if (executor.equals("")) 
				if (!isNew) executor = getExecutor();
				else isMake = false;
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
		set(getName(),mydate,myend,executor,percent,mark,true);
	}

	// Add report to a project
	public boolean addReport(Report report) {
		if (getReportsAmount() < MAXREPORTS) {
			reports[reportsAmount++] = new Report(report.getName(), report.getDate(), report.getEnd(), report.getExecutor(), report.getPercent(), report.getMark());
			return true;
		}
		else {
			System.out.println("Maximum amount of reports reached.");
			return false;
		}
	}

	// Add technology to a project
	public boolean addTechnology(Technology technology) {
		if (getTechnologiesAmount() < MAXTECHNOLOGIES) {
			technologies[technologiesAmount++] = new Technology(technology.getName());
			return true;
		}
		else {
			System.out.println("Maximum amount of technologies reached.");
			return false;
		}
	}
	
	// Change or delete executor in a reports list of a project
	public void changeExecutor(String executor, String name, Boolean isDelete) {
		for (int i = 0; i < getReportsAmount(); i++)
			if (getReports()[i].getExecutor().equals(executor))
				if (!isDelete) getReports()[i].setExecutor(name);
				else {
					for (int j = i; j < getReportsAmount(); j++)
						reports[j] = reports[j+1];
					reports[getReportsAmount()] = null;
					setReportsAmount(getReportsAmount()-1);		
					}

	}
	
	// Change or delete name of executor in a executors list of a project
	public void changeStringExecutor(String executor, String name, Boolean isDelete) {
		for (int i = 0; i < getExecutorsAmount(); i++)
			if (executors[i].equals(executor))
				if (!isDelete) executors[i] = name;
				else {
					for (int j = i; j < getExecutorsAmount()-1; j++)
						executors[j] = executors[j+1];
					executors[getExecutorsAmount()] = null;
					setExecutorsAmount(getExecutorsAmount()-1);
				}
	}

	// Main menu of a project
	public boolean menu() {
		int choise;
		System.out.println("\n<<<Project "+getName()+" menu>>>");
		System.out.print("Choose section (1 - Available executors, 2 - Technologies, 3 - Reports (tasks), 4 - Project info, 5 - Edit info, 0 - Exit): ");
		choise = makeIntChoise(0,5);
//		if (choise == 1)  while (executorsMenu()); // not used now
		if (choise == 1) printExecutors("executor");
		if (choise == 2) while (technologiesMenu());
		if (choise == 3) while (reportsMenu());
		if (choise == 4) print();
		if (choise == 5) edit(false);
		if (choise == 0) {
			System.out.println("Thank you. Good bye...");
			return false;
		}
		return true;
	}
	
	// Project technologies menu
	public boolean technologiesMenu() {
		int choise, index;
		String name;
		System.out.println("\n<<<Technologies of "+getName()+" menu>>>");
		System.out.print("Choose section (1 - Print technologies, 2 - New technology, 3 - Edit technology, 4 - Delete technology, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			printTechnologies();
		}
		if (choise == 2) {
			editTechnologies(true,-1);
		}
		if (choise == 3) {
			System.out.print("Enter technology name: ");
			name = makeStringChoise();
			index = searchTechnologies(name);
			if (index >= 0) editTechnologies(false,index);
			else System.out.println("Wrong technology name.");
		}
		if (choise == 4) {
			System.out.print("Enter technology name: ");
			name = makeStringChoise();
			index = searchTechnologies(name);
			if (index >= 0) {
				for (int i = index; i < getTechnologiesAmount(); i++)
					technologies[i] = technologies[i+1];
				technologies[technologiesAmount--] = null;
				}
			else System.out.println("Wrong technology name.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of technologies
	public void printTechnologies() {
		System.out.println("Amount of technologies - "+getTechnologiesAmount()+".");
		for (int i = 0; i < getTechnologiesAmount(); i++) {
			getTechnologies()[i].print();
		}
	}
	
	// Input new or edit technology information
	public void editTechnologies(boolean isNew, int index) {
		String name;
		Technology mytechnology;
		boolean isUnique = false;
		if ((isNew) && (getTechnologiesAmount() == MAXTECHNOLOGIES)) {
			System.out.println("Maximum technologies entered.");
			return;
		}
		do {
			if (!isNew) System.out.println("Technology name: "+getTechnologies()[index].getName());
			System.out.print("Enter new name (any symbols): ");
			name = makeStringChoise();
			if (name.equals("")) {
				if (!isNew)
					name = getTechnologies()[index].getName();
				isUnique = true;
			}
			else if ((searchTechnologies(name) < 0) || (searchTechnologies(name) == index)) isUnique = true;
			else System.out.println("Name is already exist.");
		}
		while (!isUnique);
		if (!isNew) {
			getTechnologies()[index].set(name);
		}
		else if (!name.equals("")) {
			mytechnology = new Technology(name);
			addTechnology(mytechnology);
		}
	}
	
	// Search technology by name
	public int searchTechnologies(String name) {
		for (int i = 0; i < getTechnologiesAmount(); i++)
			if (getTechnologies()[i].getName().equals(name)) 
				return i;
		return -1;
	}
	
	// Project executors menu
	public boolean executorsMenu(String type) {
		int choise, index;
		String name = "";
		System.out.println("\n<<<List of "+type+"s of "+getName()+" menu>>>");
		System.out.print("Choose section (1 - Print "+type+"s, 2 - New "+type+", 3 - Edit "+", 4 - Delete "+", 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			printExecutors(type);
		}
		if (choise == 2) {
			editExecutors(type,true,-1);
		}
		if (choise == 3) {
			System.out.print("Enter "+type+" name (only letters and .): ");
			name = makeNameChoise();
			index = searchExecutors(name);
			if (index >= 0) editExecutors(type,false,index);
			else System.out.println("Wrong "+type+" name.");
		}
		if (choise == 4) {
			System.out.print("Enter "+type+" name (only letters and .): ");
			name = makeNameChoise();
			index = searchExecutors(name);
			if (index >= 0) {
				for (int i = index; i < getExecutorsAmount(); i++)
					executors[i] = executors[i+1];
				executors[executorsAmount--] = null;
				}
			else System.out.println("Wrong "+type+" name.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of executors
	public void printExecutors(String type) {
		System.out.println("Amount of "+type+" - "+getExecutorsAmount()+".");
		for (int i = 0; i < getExecutorsAmount(); i++) {
			System.out.println(getExecutors()[i]);
		}
	}
	
	// Input new or edit executor information
	public void editExecutors(String type, boolean isNew, int index) {
		String name;
		boolean isUnique = false;
		if ((!isNew) && (getExecutorsAmount() == MAXEXECUTORS)) {
			System.out.println("Maximum "+type+"s entered.");
			return;
		}
		do {
			if (!isNew) System.out.println("Name of "+type+": "+getExecutors()[index]);
			System.out.print("Enter new name (only letters and .): ");
			name = makeNameChoise();
			if (name.equals("")) {
				if ((!isNew))
					name = getExecutors()[index];
				isUnique = true;
			}
			else if ((searchExecutors(name) < 0) || (searchExecutors(name) == index)) isUnique = true;
			else System.out.println("Name is already exist.");
		}
		while (!isUnique);
		if (!isNew)
			executors[index] = name;
		else if (!name.equals(""))
			executors[executorsAmount++] = name;
	}
	
	// Search executors by name
	public int searchExecutors(String name) {
		for (int i = 0; i < getExecutorsAmount(); i++)
			if (getExecutors()[i].equals(name)) 
				return i;
		return -1;
	}

	// Search executors by name in executors list
	public boolean isExecutorAvailable(String executor) {
		for (int i = 0; i < getExecutorsAmount(); i++)
			if (getExecutors()[i].equals(executor))
				return true;
		return false;
	}
	
	// Project reports menu
	public boolean reportsMenu() {
		int choise, index;
		String name = "";
		System.out.println("\n<<<List of reports of "+getName()+" menu>>>");
		System.out.print("Choose section (1 - Print reports, 2 - New report, 3 - Edit report, 4 - Delete report, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			printReports();
		}
		if (choise == 2) {
			editReports(true,-1);
		}
		if (choise == 3) {
			System.out.print("Enter task name (any symbols): ");
			name = makeStringChoise();
			index = searchReports(name);
			if (index >= 0) editReports(false,index);
			else System.out.println("Wrong task name.");
		}
		if (choise == 4) {
			System.out.print("Enter task name (any symbols): ");
			name = makeStringChoise();
			index = searchReports(name);
			if (index >= 0) {
				for (int i = index; i < getReportsAmount(); i++)
					reports[i] = reports[i+1];
				reports[reportsAmount--] = null;
				}
			else System.out.println("Wrong task name.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of reports
	public void printReports() {
		System.out.println("Amount of tasks - "+getReportsAmount()+".");
		for (int i = 0; i < getReportsAmount(); i++) {
			getReports()[i].print();
		}
	}
	
	// Input new or edit report information
	public void editReports(boolean isNew, int index) {
		String name, executor;
		boolean isUnique = false, isMake = false;
		Report myreport;
		if ((!isNew) && (getReportsAmount() == MAXREPORTS)) {
			System.out.println("Maximum reports entered.");
			return;
		}
		do {
			if (!isNew) System.out.println("Task name: "+getReports()[index].getName());
			System.out.print("Enter new name (any symbols): ");
			name = makeStringChoise();
			if (name.equals("")) {
				if (!isNew) {
					name = getReports()[index].getName();
					isUnique = true;
				}
				else System.out.println("Wrong task name.");
			}
			else if ((searchReports(name) < 0) || (searchReports(name) == index)) isUnique = true;
			else System.out.println("Name is already exist.");
		}
		while (!isUnique);
		do {
			isMake = true;
			if (!isNew) System.out.println("Executor: "+getReports()[index].getExecutor());
			System.out.print("Enter new name of executor (only letters and .): ");
			executor = makeNameChoise();
			if (executor.equals("")) 
				if (!isNew) executor = getReports()[index].getExecutor();
				else isMake = false;
			else
				if (!isExecutorAvailable(executor)) {
					isMake = false;
					System.out.println("Wrong executor's name. Select from list.");
					if (getExecutorsAmount() > 0) printExecutors("executor");
					else {
						System.out.println("No available executors.");
						return;
					}
				}
		}
		while(!isMake);
		if (!isNew) {
			getReports()[index].setName(name);
			getReports()[index].setExecutor(executor);
			getReports()[index].edit(false);
		}
		else if (!name.equals("")) {
			myreport = new Report();
			myreport.setName(name);
			myreport.setExecutor(executor);
			myreport.edit(true);
			addReport(myreport);
		}
	}
	
	
	// Search reports by name
	public int searchReports(String name) {
		for (int i = 0; i < getReportsAmount(); i++)
			if (getReports()[i].getName().equals(name)) 
				return i;
		return -1;
	}
	
	// Getters and setters
	public int getExecutorsAmount() {
		return executorsAmount;
	}
	
	public void setExecutorsAmount(int executorsAmount) {
		this.executorsAmount = executorsAmount;
	}
	
	public String[] getExecutors() {
		return executors;
	}
	
	public void setExecutors(String[] executors) {
		this.executors = executors;
	}
	
	public int getReportsAmount() {
		return reportsAmount;
	}
	
	public void setReportsAmount(int reportsAmount) {
		this.reportsAmount = reportsAmount;
	}
	
	public Report[] getReports() {
		return reports;
	}
	
	public void setReports(Report[] reports) {
		this.reports = reports;
	}
	
	public int getTechnologiesAmount() {
		return technologiesAmount;
	}
	
	public void setTechnologiesAmount(int technologiesAmount) {
		this.technologiesAmount = technologiesAmount;
	}
	
	public Technology[] getTechnologies() {
		return technologies;
	}
	
	public void setTechnologies(Technology[] technologies) {
		this.technologies = technologies;
	}
	
}
