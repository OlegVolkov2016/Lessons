package company;

import java.util.Calendar;

public class Company extends Executor {
	
	// Main company fields
	// date - birthday
	// begin - 
	// end - 
	// executor - director
	// percentage - mark of director
	// mark - mark of clients
	// reports - empty
	private Project[] projects;
	private Executor[] executors;

	public Company() {
		// TODO Auto-generated constructor stub
		projects = new Project[MAXPROJECTS];
		executors = new Executor[MAXEXECUTORS];
	}

	public Company(String name, Calendar date, Calendar begin, Calendar end, String executor, int percent, int mark,
			boolean isEmpty) {
		super(name, date, begin, end, executor, percent, mark, true);
		// TODO Auto-generated constructor stub
		projects = new Project[MAXPROJECTS];
		executors = new Executor[MAXEXECUTORS];
		if (!isEmpty)
			firstInit();
	}
	
	public void set(String name, Calendar date, Calendar begin, Calendar end, String executor, int percent, int mark,
			boolean isEmpty) {
		super.set(name, date, begin, end, executor, percent, mark, true);
		// TODO Auto-generated constructor stub
		projects = new Project[MAXPROJECTS];
		executors = new Executor[MAXEXECUTORS];
		if (!isEmpty)
			firstInit();
	}
	
	// First initializing
	public void firstInit() {
		super.firstInit();
		setProjectsAmount(2);
		Calendar mydate = Calendar.getInstance();
		Calendar mybegin = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		mydate.set(2015, 3, 1);
		myend.set(2016, 2, 31);
		projects[0] = new Project("Car design",mydate,myend,"Administrator",10,2,false);
		mydate.set(2015, 8, 10);
		myend.set(2016, 0, 10);
		projects[1] = new Project("Speed analyzing",mydate,myend,"Administrator",0,1,true);
		setExecutorsAmount(2);
		mydate.set(1990, 2, 20);
		mybegin.set(2014, 0, 15);
		myend.set(2016, 2, 31);
		executors[0] = new Executor("Johnson",mydate,mybegin,myend,"Maxwell",5,6,false);
		mydate.set(1994, 4, 20);
		mybegin.set(2014, 3, 15);
		myend.set(2016, 0, 31);
		executors[1] = new Executor("Maxwell",mydate,mybegin,myend,"Administrator",2,3,true);
	}
	
	public void print() {
		System.out.println("\nCompany: "+getName());
		System.out.println("Date of creation: "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR));
		System.out.println("Date of start working: "+MONTH_NAMES[getBegin().get(Calendar.MONTH)]+", "+getBegin().get(Calendar.DATE)+" "+getBegin().get(Calendar.YEAR));
		System.out.println("Date of end working: "+MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR));
		System.out.println("Director: "+getExecutor());
		System.out.println("Mark of director: "+getPercent());
		System.out.println("Mark of clients: "+getMark());
//		System.out.println("\n\tCompany projects:");
//		for (int i = 0; i < getProjectsAmount(); i++)
//			getAProjects()[i].print();
//		System.out.println("\n\tExecutors: ");
//		for (int i = 0; i < getExecutorsAmount(); i++)
//			getAExecutors()[i].print();
//		System.out.println("\n\tReports: ");
//		for (int i = 0; i < getReportsAmount(); i++)
//			getReports()[i].print();
//		System.out.println("\n\tTechnologies: ");
//		for (int i = 0; i < getTechnologiesAmount(); i++)
//			getTechnologies()[i].print();
	}
	
	public void edit() {
		Calendar mydate = Calendar.getInstance();
		Calendar mybegin = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		String name, executor, str;
		int percent, mark;
		boolean isMake = true;
		do {
			System.out.println("Company name: "+getName());
			System.out.print("Enter new name: ");
			name = makeStringChoise();
			if (name.equals("")) name = getName();
			System.out.println("Date of creation: "+MONTH_NAMES[getDate().get(Calendar.MONTH)]+", "+getDate().get(Calendar.DATE)+" "+getDate().get(Calendar.YEAR));
			System.out.print("Enter new date (DD/MM/YYYY):");
			str = makeDateChoise();
			if (str.equals("")) mydate = getDate();
			else
				mydate.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
			System.out.println("Date of start working: "+MONTH_NAMES[getBegin().get(Calendar.MONTH)]+", "+getBegin().get(Calendar.DATE)+" "+getBegin().get(Calendar.YEAR));
			System.out.print("Enter new date (DD/MM/YYYY):");
			str = makeDateChoise();
			if (str.equals("")) mybegin = getBegin();
			else
				mybegin.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
			System.out.println("Date of end working: "+MONTH_NAMES[getEnd().get(Calendar.MONTH)]+", "+getEnd().get(Calendar.DATE)+" "+getEnd().get(Calendar.YEAR));
			System.out.print("Enter new date (DD/MM/YYYY):");
			str = makeDateChoise();
			if (str.equals("")) myend = getEnd();
			else
				myend.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
			System.out.println("Director: "+getExecutor());
			System.out.print("Enter new name (only letters and .): ");
			executor = makeNameChoise();
			if (executor.equals("")) executor = getExecutor();
			System.out.println("Mark of director: "+getPercent());
			System.out.print("Enter new mark (1 to 12): ");
			percent = makeIntChoise(1,12);
			if (percent == -1) percent = getPercent();
			System.out.println("Mark of clients: "+getMark());
			System.out.print("Enter new mark (1 to 12): ");
			mark = makeIntChoise(1,12);
			if (mark == -1) mark = getMark();
			set(name,mydate,mybegin,myend,executor,percent,mark,true);
		}
		while (!isMake);
	}
	
	
	// Main menu
	public boolean menu() {
		int choise;
		System.out.println("\n<<<Company "+getName()+" menu>>>");
		System.out.print("Choose section (1 - Executors, 2 - Projects, 3 - List of reports, 4 - Events, 5 - Technologies, 6 - Company info, 7 - Edit info, 0 - Exit): ");
		choise = makeIntChoise(0,7);
		if (choise == 1) while (executorsMenu());
		if (choise == 2) while (projectsMenu());
		if (choise == 3) for (int i = 0; i < getExecutorsAmount(); i++) {
			System.out.println("\nReports of executor "+getAExecutors()[i].getName()+":");
			getAExecutors()[i].printReports();
		}
		if (choise == 4) while (eventsMenu());
		if (choise == 5) while (technologiesMenu());
		if (choise == 6) print();
		if (choise == 7) edit();
		if (choise == 0) {
			System.out.println("Thank you. Good bye...");
			return false;
		}
		return true;
	}
	
	// Company executors menu
	public boolean executorsMenu() {
		int choise, index;
		String name = "";
		System.out.println("\n<<<Executors of "+getName()+" menu>>>");
		System.out.print("Choose section (1 - Print executors, 2 - New executor, 3 - Edit executor, 4 - Delete executor, 5 - Executor menu, 0 - Exit): ");
		choise = makeIntChoise(0,5);
		if (choise == 1) {
			printExecutors();
		}
		if (choise == 2) {
			editExecutors(true,-1);
		}
		if (choise == 3) {
			System.out.print("Enter executor name (only letters and .): ");
			name = makeNameChoise();
			index = searchExecutors(name);
			if (index >= 0) editExecutors(false,index);
			else System.out.println("Wrong executor name.");
		}
		if (choise == 4) {
			System.out.print("Enter executor name (only letters and .): ");
			name = makeNameChoise();
			index = searchExecutors(name);
			if (index >= 0) {
				for (int i = index; i < getExecutorsAmount(); i++)
					executors[i] = executors[i+1];
				executors[getExecutorsAmount()] = null;
				setExecutorsAmount(getExecutorsAmount()-1);
				changeExecutor(name,"",true);
				}
			else System.out.println("Wrong executor name.");
		}
		if (choise == 5) {
			System.out.print("Enter executor name (only letters and .): ");
			name = makeNameChoise();
			index = searchExecutors(name);
			if (index >= 0) {
				rebuildExecutors(index);
				while (getAExecutors()[index].menu());
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of executors
	public void printExecutors() {
		System.out.println("Amount of executors - "+getExecutorsAmount()+".");
		for (int i = 0; i < getExecutorsAmount(); i++) {
			getAExecutors()[i].print();
		}
	}
	
	// Input new or edit executor information
	public void editExecutors(boolean isNew, int index) {
		String name, executor;
		boolean isUnique = false;
		if ((!isNew) && (getExecutorsAmount() == MAXEXECUTORS)) {
			System.out.println("Maximum executors entered.");
			return;
		}
		do {
			if (!isNew) {
				executor = getAExecutors()[index].getName();
				System.out.println("Executor name: "+executor);
			}
			else executor = "";
			System.out.print("Enter new name (only letters and .): ");
			name = makeNameChoise();
			if (name.equals("")) {
				if ((!isNew))
					name = getAExecutors()[index].getName();
				isUnique = true;
			}
			else if ((searchExecutors(name) < 0) || (searchExecutors(name) == index)) isUnique = true;
			else System.out.println("Name is already exist.");
		}
		while (!isUnique);
		if ((!isNew) & !name.equals(executor)) {
			changeExecutor(executor,name,false);
		}
		if (!isNew) {
			getAExecutors()[index].setName(name);
			rebuildExecutors(index);
			while(getAExecutors()[index].menu());
		}
		else if (!name.equals("")) {
			getAExecutors()[getExecutorsAmount()] = new Executor();
			getAExecutors()[getExecutorsAmount()].setName(name);
			setExecutorsAmount(getExecutorsAmount()+1);
			getAExecutors()[getExecutorsAmount()-1].edit(true);
		}
	}
	
	// Search executor by name
	public int searchExecutors(String name) {
		for (int i = 0; i < getExecutorsAmount(); i++)
			if (getAExecutors()[i].getName().equals(name)) 
				return i;
		return -1;
	}
	
	// Change or delete executor in all lists 
	public void changeExecutor(String executor, String name, Boolean isDelete) {
		// Reports list of company
		super.changeExecutor(executor,name,isDelete);
		for (int i = 0; i < getExecutorsAmount(); i++)
			// Reports list of executor
			getAExecutors()[i].changeExecutor(executor,name,isDelete);
		for (int i = 0; i < getProjectsAmount(); i++) {
			// Reports list of a project
			getAProjects()[i].changeExecutor(executor,name,isDelete);
			// Executors list of a project
			getAProjects()[i].changeStringExecutor(executor,name,isDelete);
		}
	}
	
	// Rebuild list of executor project and reports to update executor information
	public void rebuildExecutors(int index) {
		int projectsAmount = 0, reportsAmount = 0;
		String[] projects;
		Report[] reports;
		projects = new String[MAXPROJECTS];
		reports = new Report[MAXREPORTS];
		boolean isFind;
		for (int i = 0; i < getProjectsAmount(); i++) {
			isFind = false;
			for (int j = 0; j < getAProjects()[i].getReportsAmount(); j++) {
				if (getAProjects()[i].getReports()[j].getExecutor().equals(getAExecutors()[index].getName())) {
					isFind = true;
					if (reportsAmount < MAXREPORTS)
						reports[reportsAmount++] = getAProjects()[i].getReports()[j];
				}
			}
			if ((isFind) && (projectsAmount < MAXPROJECTS))
				projects[projectsAmount++] = getAProjects()[i].getName();
		}
		executors[index].setProjectsAmount(projectsAmount);
		executors[index].setProjects(projects);
		executors[index].setReportsAmount(reportsAmount);
		executors[index].setReports(reports);
	}
	
	
	// Company projects menu
	public boolean projectsMenu() {
		int choise, index;
		String name = "";
		System.out.println("\n<<<Projects of "+getName()+" menu>>>");
		System.out.print("Choose section (1 - Print projects, 2 - New project, 3 - Edit project, 4 - Delete project, 5 - Project menu, 0 - Exit): ");
		choise = makeIntChoise(0,5);
		if (choise == 1) {
			printProjects();
		}
		if (choise == 2) {
			editProjects(true,-1);
		}
		if (choise == 3) {
			System.out.print("Enter project name (only letters and .): ");
			name = makeNameChoise();
			index = searchProjects(name);
			if (index >= 0) editProjects(false,index);
			else System.out.println("Wrong project name.");
		}
		if (choise == 4) {
			System.out.print("Enter project name (only letters and .): ");
			name = makeNameChoise();
			index = searchProjects(name);
			if (index >= 0) {
				for (int i = index; i < getProjectsAmount(); i++)
					projects[i] = projects[i+1];
				projects[getProjectsAmount()] = null;
				setProjectsAmount(getProjectsAmount()-1);
				}
			else System.out.println("Wrong project name.");
		}
		if (choise == 5) {
			System.out.print("Enter project name (only letters and .): ");
			name = makeNameChoise();
			index = searchProjects(name);
			if (index >= 0) {
				rebuildProjects(index);
				while (getAProjects()[index].menu());
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of projects
	public void printProjects() {
		System.out.println("Amount of projects - "+getProjectsAmount()+".");
		for (int i = 0; i < getProjectsAmount(); i++) {
			getAProjects()[i].print();
		}
	}
	
	// Input new or edit project information
	public void editProjects(boolean isNew, int index) {
		String name;
		boolean isUnique = false;
		if ((!isNew) && (getProjectsAmount() == MAXPROJECTS)) {
			System.out.println("Maximum projects entered.");
			return;
		}
		do {
			if (!isNew) System.out.println("Project name: "+getAProjects()[index].getName());
			System.out.print("Enter new name (only letters and .): ");
			name = makeNameChoise();
			if (name.equals("")) {
				if ((!isNew))
					name = getAProjects()[index].getName();
				isUnique = true;
			}
			else if ((searchProjects(name) < 0) || (searchProjects(name) == index)) isUnique = true;
			else System.out.println("Name is already exist.");
		}
		while (!isUnique);
		if (!isNew) {
			getAProjects()[index].setName(name);
			rebuildProjects(index);
			while(getAProjects()[index].menu());
		}
		else if (!name.equals("")) {
			getAProjects()[getProjectsAmount()] = new Project();
			getAProjects()[getProjectsAmount()].setName(name);
			setProjectsAmount(getProjectsAmount()+1);
			getAProjects()[getProjectsAmount()-1].edit(true);
		}
	}
	
	// Search project by name
	public int searchProjects(String name) {
		for (int i = 0; i < getProjectsAmount(); i++)
			if (getAProjects()[i].getName().equals(name)) 
				return i;
		return -1;
	}
	
	// Rebuild list of project available executors to update project information
	public void rebuildProjects(int index) {
		int executorsAmount = 0;
		String[] executors;
		executors = new String[MAXEXECUTORS];
		boolean isFind;
		for (int i = 0; i < getExecutorsAmount(); i++) {
			isFind = false;
			for (int j = 0; j < getAProjects()[index].getTechnologiesAmount(); j++) {
				if ((getAExecutors()[i].searchTechnologies(getAProjects()[index].getTechnologies()[j].getName())) >= 0) {
					isFind = true;
					break;
				}
			}
			if ((isFind) && (executorsAmount < MAXEXECUTORS))
				executors[executorsAmount++] = getAExecutors()[i].getName();
		}
		getAProjects()[index].setExecutorsAmount(executorsAmount);
		getAProjects()[index].setExecutors(executors);
	}

	// Company events menu
	public boolean eventsMenu() {
		int choise;
		System.out.println("\n<<<Company "+getName()+" events menu>>>");
		System.out.print("Choose section (1 - Executors events, 2 - Projects events, 0 - Exit): ");
		choise = makeIntChoise(0,2);
		if (choise == 1) executorsEvents();
		if (choise == 2) projectsEvents();
		if (choise == 0) {
			return false;
		}
		return true;
	}
	
	// Print list of executors events between dates
	public void executorsEvents() {
		Calendar mybegin = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		String str;
		boolean isMake;
		do {
			isMake = true;
			System.out.print("Enter begin date (DD/MM/YYYY): ");
			str = makeDateChoise();
			if (str.equals("")) mybegin = getBegin();
			else
				mybegin.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
			System.out.print("Enter end date (DD/MM/YYYY): ");
			str = makeDateChoise();
			if (str.equals("")) myend = getEnd();
			else
				myend.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
			if (!myend.after(mybegin)) {
				System.out.println("Wrong end date. Try again.");
				isMake = false;
			}
		}
		while (!isMake);
		for (int i = 0; i < getExecutorsAmount(); i++) {
			System.out.println("\nEvents of executor "+getAExecutors()[i].getName());
			if ((getAExecutors()[i].getDate().after(mybegin)) && (getAExecutors()[i].getDate().before(myend)))
				System.out.println("Birthday on "+MONTH_NAMES[getAExecutors()[i].getDate().get(Calendar.MONTH)]+", "+getAExecutors()[i].getDate().get(Calendar.DATE)+" "+getAExecutors()[i].getDate().get(Calendar.YEAR));
			if ((getAExecutors()[i].getBegin().after(mybegin)) && (getAExecutors()[i].getBegin().before(myend)))
				System.out.println("Start working on "+MONTH_NAMES[getAExecutors()[i].getBegin().get(Calendar.MONTH)]+", "+getAExecutors()[i].getBegin().get(Calendar.DATE)+" "+getAExecutors()[i].getBegin().get(Calendar.YEAR));
			if ((getAExecutors()[i].getEnd().after(mybegin)) && (getAExecutors()[i].getEnd().before(myend)))
				System.out.println("End working on "+MONTH_NAMES[getAExecutors()[i].getEnd().get(Calendar.MONTH)]+", "+getAExecutors()[i].getEnd().get(Calendar.DATE)+" "+getAExecutors()[i].getEnd().get(Calendar.YEAR));
		}
	}
	
	// Print list of projects events between dates
	public void projectsEvents() {
		Calendar mybegin = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		String str;
		boolean isMake;
		do {
			isMake = true;
			System.out.print("Enter begin date (DD/MM/YYYY): ");
			str = makeDateChoise();
			if (str.equals("")) mybegin = getBegin();
			else
				mybegin.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
			System.out.print("Enter end date (DD/MM/YYYY): ");
			str = makeDateChoise();
			if (str.equals("")) myend = getEnd();
			else
				myend.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
			if (!myend.after(mybegin)) {
				System.out.println("Wrong end date. Try again.");
				isMake = false;
			}
		}
		while (!isMake);
		for (int i = 0; i < getProjectsAmount(); i++) {
			System.out.println("\nEvents of project "+getAProjects()[i].getName());
			if ((getAProjects()[i].getDate().after(mybegin)) && (getAProjects()[i].getDate().before(myend)))
				System.out.println("Start on "+MONTH_NAMES[getAProjects()[i].getDate().get(Calendar.MONTH)]+", "+getAProjects()[i].getDate().get(Calendar.DATE)+" "+getAProjects()[i].getDate().get(Calendar.YEAR));
			if ((getAProjects()[i].getEnd().after(mybegin)) && (getAProjects()[i].getEnd().before(myend)))
				System.out.println("End on "+MONTH_NAMES[getAProjects()[i].getEnd().get(Calendar.MONTH)]+", "+getAProjects()[i].getEnd().get(Calendar.DATE)+" "+getAProjects()[i].getEnd().get(Calendar.YEAR));
			for (int j = 0; j < getAProjects()[i].getReportsAmount(); j++) {
				System.out.println("Events of task "+getAProjects()[i].getReports()[j].getName());
				if ((getAProjects()[i].getReports()[j].getDate().after(mybegin)) && (getAProjects()[i].getReports()[j].getDate().before(myend)))
					System.out.println("Start on "+MONTH_NAMES[getAProjects()[i].getReports()[j].getDate().get(Calendar.MONTH)]+", "+getAProjects()[i].getReports()[j].getDate().get(Calendar.DATE)+" "+getAProjects()[i].getReports()[j].getDate().get(Calendar.YEAR));
				if ((getAProjects()[i].getEnd().after(mybegin)) && (getAProjects()[i].getEnd().before(myend)))
					System.out.println("End on "+MONTH_NAMES[getAProjects()[i].getReports()[j].getEnd().get(Calendar.MONTH)]+", "+getAProjects()[i].getReports()[j].getEnd().get(Calendar.DATE)+" "+getAProjects()[i].getReports()[j].getEnd().get(Calendar.YEAR));
			}
		}
	}
	
	// Getters and setters
	public Project[] getAProjects() {
		return projects;
	}
	
	public void setAProjects(Project[] projects) {
		this.projects = projects;
	}
	
	public Executor[] getAExecutors() {
		return executors;
	}
	
	public void setAExecutors(Executor[] executors) {
		this.executors = executors;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		Calendar mydate = Calendar.getInstance();
		Calendar mybegin = Calendar.getInstance();
		Calendar myend = Calendar.getInstance();
		mydate.set(1990, 2, 20);
		mybegin.set(2012, 0, 15);
		myend.set(2016, 2, 31);
		Company myCompany = new Company("Robinson Computing",mydate,mybegin,myend,"Director",12,11,false);
		System.out.println("Welcome to the company "+myCompany.getName()+"!\n");
		while (myCompany.menu());
	}

}
