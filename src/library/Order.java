package library;

import java.util.Calendar;

public class Order extends Book {
	
	// Order of library
	// discount is order number
	// name is name of book
	// fileName is name of client
	private Book book;
	private Client client;
	private Calendar issueDate, expiresDate;
	private boolean isMade;
	
	public Order() {
		// TODO Auto-generated constructor stub
		super();
		this.book = new Book();
		this.client = new Client();
		this.issueDate = Calendar.getInstance();
		this.expiresDate = Calendar.getInstance();
	}

	public Order(String name, double discount, String filename, int state, Book book, Client client, Calendar issueDate, Calendar expiresDate, boolean isMade) {
		super(name, discount, filename, state);
		// TODO Auto-generated constructor stub
		this.book = book;
		this.client = client;
		this.issueDate = (Calendar) issueDate.clone();
		this.expiresDate = (Calendar) expiresDate.clone();
		this.isMade = isMade;
	}
	
	@Override
	public void set(Object obj) {
		// TODO Auto-generated method stub
		super.set(obj);
		this.book = ((Order) obj).getBook();
		this.client = ((Order) obj).getClient();
		this.issueDate = (Calendar) ((Order) obj).getIssueDate().clone();
		this.expiresDate = (Calendar) ((Order) obj).getExpiresDate().clone();
		this.isMade = ((Order) obj).getMade();
	}
	
	@Override
	public void firstInit() {
		// TODO Auto-generated method stub
		super.firstInit();
	}
	
	@Override
	public void print(String type) {
		// TODO Auto-generated method stub
		System.out.println("# "+(int) getDiscount()+": Description of "+type+": book "+getName()+", client "+getFileName()+".");
		System.out.print("Ordered to ");
		if (getState() == 0) System.out.println("library.");
		else if (getState() == 1) System.out.println("reading hall.");
		else if (getState() == 2) System.out.println("read outside.");
		else if (getState() == 3) System.out.println("buy.");
		System.out.println("Date of issue: "+dateToString(getIssueDate())+".");
		System.out.println("Date of expires: "+dateToString(getExpiresDate())+".");
		if (getIssueDate().after(Calendar.getInstance()))
			System.out.println("Order is in future.");
		else 
			if (getExpiresDate().after(Calendar.getInstance()))
				if (getMade()) System.out.println("Order is made.");
				else System.out.println("Order is not made.");
			else 
				if (getMade() && (getState() < 3)) System.out.println("Order is expires.");
				else System.out.println("Order is finished.");
	}
	
	@Override
	public void edit(String type, boolean isNew) {
		// TODO Auto-generated method stub
		String name;
		double discount;
		String fileName;
		int state;
		Calendar issueDate = Calendar.getInstance();
		Calendar expiresDate = Calendar.getInstance();
		boolean isMade;
		discount = inputDiscount("order",isNew);
		name = inputName("book",isNew);
		fileName = inputFileName("client", isNew);
		issueDate =	inputDate("issue",getIssueDate(),isNew);
		expiresDate = inputDate("expire",getExpiresDate(),isNew);
		state = inputState(type,isNew);
		isMade = isRepeat("Make order? (y - to make): ");
//		setName(name);
//		setDiscount(discount);
//		setState(state);
//		setFileName(fileName);
//		setIssueDate(issueDate);
//		setExpiresDate(expiresDate);
//		setMade(isMade);
		set(new Order(name,discount,fileName,state,getBook(),getClient(),issueDate,expiresDate,isMade));

	}
	
	@Override
	public boolean menu(String type) {
		// TODO Auto-generated method stub
		return super.menu(type);
	}
	
	@Override
	public double inputDiscount(String type, boolean isNew) {
		// TODO Auto-generated method stub
		double discount;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Current number of "+type+": "+(int) getDiscount()+".");
			System.out.print("Enter new number of "+type+": ");
			discount = makeIntChoise(0,Integer.MAX_VALUE);
			if (discount == Integer.MIN_VALUE) 
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
	public String inputFileName(String type, boolean isNew) {
		String fileName;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Name of "+type+": "+getFileName());
			System.out.print("Enter new name of "+type+" (only letters and .): ");
			fileName = makeNameChoise();
			if (fileName.equals("")) 
				if (!isNew) fileName = getFileName();
				else {
					System.out.println("Wrong name.");
					isMake = false;
				}
		}
		while(!isMake);
		return fileName;
	}
	
	@Override
	public int inputState(String type, boolean isNew) {
		int state;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) {
				System.out.print("Current state of "+type+": ");
				if (getState() == 0) System.out.println("in library.");
				else if (getState() == 1) System.out.println("in reading hall.");
				else if (getState() == 2) System.out.println("is on hands.");
				else if (getState() == 3) System.out.println("is selled.");
			}
			System.out.print("Enter new state (1 - hall, 2 - hands, 3 - selled): ");
			state = makeIntChoise(1,3);
			if (state == Integer.MIN_VALUE) 
				if (!isNew) state = getState();
				else {
					System.out.println("Wrong value.");
					isMake = false;
				}
		}
		while(!isMake);
		return state;
	}
	
	public Calendar inputDate(String type, Calendar date, boolean isNew) {
		String str;
		Calendar newDate = Calendar.getInstance();
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Date of "+type+": "+dateToString(date));
			System.out.print("Enter new date of "+type+" (DD/MM/YYYY): ");
			str = makeDateChoise();
			if (str.equals("")) 
				if (!isNew) newDate = date;
				else {
					System.out.println("Wrong date.");
					isMake = false;
				}
			else
				newDate.set(Integer.parseInt(str.substring(6,10)),Integer.parseInt(str.substring(3,5))-1,Integer.parseInt(str.substring(0,2)));
		}
		while(!isMake);
		return newDate;
	}
	
	// Getters and setters
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
		
	public Calendar getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(Calendar issueDate) {
		this.issueDate = issueDate;
	}
	
	public Calendar getExpiresDate() {
		return expiresDate;
	}
	
	public void setExpiresDate(Calendar expiresDate) {
		this.expiresDate = expiresDate;
	}
	
	public boolean getMade() {
		return isMade;
	}
	
	public void setMade(boolean isMade) {
		this.isMade = isMade;
	}

}
