package company;

import java.util.Calendar;

public class Executor extends Project {
	
	// Executor of the company
	// date - birthday
	// begin - start working
	// end - end working
	// executor - director
	// percentage - mark of director
	// mark - mark of clients
	private Calendar begin;	
	// Executor fields
	final int MAXPROJECTS = 10;
	private int projectsAmount;
	private String[] projects;
	
	public Executor() {
		// TODO Auto-generated constructor stub
		begin = Calendar.getInstance();
		projectsAmount = 0;
		projects = new String[MAXPROJECTS];
	}

	public Executor(String name, Calendar date, Calendar begin, Calendar end, String executor, int percent, int mark,
			boolean isEmpty) {
		super(name, date, end, executor, percent, mark, true);
		// TODO Auto-generated constructor stub
		this.begin = Calendar.getInstance();
		this.begin.set(begin.get(Calendar.YEAR),begin.get(Calendar.MONTH),begin.get(Calendar.DATE));
		projectsAmount = 0;
		projects = new String[MAXPROJECTS];
		if (!isEmpty)
			firstInit();
	}
	
	public void set(String name, Calendar date, Calendar begin, Calendar end, String executor, int percent, int mark,
			boolean isEmpty) {
		super.set(name, date, end, executor, percent, mark, true);
		// TODO Auto-generated constructor stub
		this.begin = Calendar.getInstance();
		this.begin.set(begin.get(Calendar.YEAR),begin.get(Calendar.MONTH),begin.get(Calendar.DATE));
		if (!isEmpty)
			firstInit();
	}
	
	// First initializing
	public void firstInit() {
		super.firstInit();
		projectsAmount = 2;
		projects[0] = "Car design";
		projects[1] = "Speed analyzing";
	}
		
	public void print() {
		System.out.println("\nExecutor: "+getName());
		System.out.println("Date of birth: "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR));
		System.out.println("Date of start working: "+MONTH_NAMES[getBegin().get(Calendar.MONTH)]+", "+getBegin().get(Calendar.DATE)+" "+getBegin().get(Calendar.YEAR));
		System.out.println("Date of end working: "+MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR));
		System.out.println("Director: "+getExecutor());
		System.out.println("Mark of director: "+getPercent());
		System.out.println("Mark of clients: "+getMark());
//		System.out.println("\n\tWorking on projects:");
//		for (int i = 0; i < getProjectsAmount(); i++) {
//			System.out.println(getProjects()[i]);
//		}
//		System.out.println("\n\tColleagues: ");
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
		Calendar mybegin = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		String executor, str;
		int percent, mark;
		boolean isMake = true;
		do {
			isMake = true;
			if (!isNew) System.out.println("Date of birth: "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR));
			System.out.print("Enter new date of birth (DD/MM/YYYY):");
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
			if (!isNew) System.out.println("Date of start working: "+MONTH_NAMES[getBegin().get(Calendar.MONTH)]+", "+getBegin().get(Calendar.DATE)+" "+getBegin().get(Calendar.YEAR));
			System.out.print("Enter new date of start working (DD/MM/YYYY):");
			str = makeDateChoise();
			if (str.equals("")) 
				if (!isNew) mybegin = getBegin();
				else isMake = false;
			else
				mybegin.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
		}
		while(!isMake);
		do {
			isMake = true;
			if (!isNew) System.out.println("Date of end working: "+MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR));
			System.out.print("Enter new date of end working (DD/MM/YYYY):");
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
			if (!isNew) System.out.println("Mark of director: "+getPercent());
			System.out.print("Enter new mark of director (1 to 12): ");
			percent = makeIntChoise(1,12);
			if (percent == -1) 
				if (!isNew) percent = getPercent();
				else isMake = false;
		}
		while(!isMake);
		do {
			isMake = true;
			if (!isNew) System.out.println("Mark of clients: "+getMark());
			System.out.print("Enter new mark of clients (1 to 12): ");
			mark = makeIntChoise(1,12);
			if (mark == -1) 
				if (!isNew) mark = getMark();
				else isMake = false;
		}
		while(!isMake);
		set(getName(),mydate,mybegin,myend,executor,percent,mark,true);
	}
	
	// Change or delete executor in a reports list of a project
	public void changeExecutor(String executor, String name, Boolean isDelete) {
		super.changeExecutor(executor,name,isDelete);
	}
	
	// Main menu of executor
	public boolean menu() {
		int choise;
		System.out.println("\n<<<Executor "+getName()+" menu>>>");
		System.out.print("Choose section (1 - List of Projects, 2 - List of reports, 3 - Technologies, 4 - Colleagues, 5 - Executor info, 6 - Edit info, 0 - Exit): ");
		choise = makeIntChoise(0,6);
//		if (choise == 1) while (projectsMenu("project")); //not used now
		if (choise == 1) printProjects("project");
		if (choise == 2) printReports();
		if (choise == 3) while (technologiesMenu());
		if (choise == 4) while (executorsMenu("colleague"));
		if (choise == 5) print();
		if (choise == 6) edit(false);
		if (choise == 0) {
			System.out.println("Thank you. Good bye...");
			return false;
		}
		return true;
	}
	
	// Executor projects menu
	public boolean projectsMenu(String type) {
		int choise, index;
		String name = "";
		System.out.println("\n<<<List of "+type+"s of "+getName()+" menu>>>");
		System.out.print("Choose section (1 - Print "+type+"s, 2 - New "+type+", 3 - Edit "+", 4 - Delete "+", 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) {
			printProjects(type);
		}
		if (choise == 2) {
			editProjects(type,true,-1);
		}
		if (choise == 3) {
			System.out.print("Enter "+type+" name (only letters and .): ");
			name = makeNameChoise();
			index = searchProjects(name);
			if (index >= 0) editProjects(type,false,index);
			else System.out.println("Wrong "+type+" name.");
		}
		if (choise == 4) {
			System.out.print("Enter "+type+" name (only letters and .): ");
			name = makeNameChoise();
			index = searchProjects(name);
			if (index >= 0) {
				for (int i = index; i < getProjectsAmount(); i++)
					projects[i] = projects[i+1];
				projects[projectsAmount--] = null;
				}
			else System.out.println("Wrong "+type+" name.");
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of projects
	public void printProjects(String type) {
		System.out.println("Amount of "+type+" - "+getProjectsAmount()+".");
		for (int i = 0; i < getProjectsAmount(); i++) {
			System.out.println(getProjects()[i]);
		}
	}
	
	// Input new or edit project information
	public void editProjects(String type, boolean isNew, int index) {
		String name;
		boolean isUnique = false;
		if ((!isNew) && (getProjectsAmount() == MAXPROJECTS)) {
			System.out.println("Maximum "+type+"s entered.");
			return;
		}
		do {
			if (!isNew) System.out.println("Name of "+type+": "+getProjects()[index]);
			System.out.print("Enter new name (only letters and .): ");
			name = makeNameChoise();
			if (name.equals("")) {
				if ((!isNew))
					name = getProjects()[index];
				isUnique = true;
			}
			else if ((searchProjects(name) < 0) || (searchProjects(name) == index)) isUnique = true;
			else System.out.println("Name is already exist.");
		}
		while (!isUnique);
		if (!isNew)
			projects[index] = name;
		else if (!name.equals(""))
			projects[projectsAmount++] = name;
	}
	
	// Search project by name
	public int searchProjects(String name) {
		for (int i = 0; i < getProjectsAmount(); i++)
			if (getProjects()[i].equals(name)) 
				return i;
		return -1;
	}
	
	// Getters and setters
	public Calendar getBegin() {
		Calendar newBegin = Calendar.getInstance();
		newBegin.set(begin.get(Calendar.YEAR),begin.get(Calendar.MONTH),begin.get(Calendar.DATE));
		return newBegin;
	}
	
	public void setBegin(Calendar end) {
		this.begin.set(begin.get(Calendar.YEAR),begin.get(Calendar.MONTH),begin.get(Calendar.DATE));
	}
	
	public int getProjectsAmount() {
		return projectsAmount;
	}
	
	public void setProjectsAmount(int projectsAmount) {
		this.projectsAmount = projectsAmount;
	}
	
	public String[] getProjects() {
		return projects;
	}
	
	public void setProjects(String[] projects) {
		this.projects = projects;
	}
	
}
