package library;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

public class Library extends Client {

	// Main library fields
	// discount is popularity for example
	private int booksAmount, clientsAmount, ordersAmount;
	ArrayList<Book> books;
	ArrayList<Client> clients;
	LinkedList<Order> orders;
	// count is first init count
	private int count = 3;
	
	public Library() {
		// TODO Auto-generated constructor stub
		super();
		books = new ArrayList<Book>();
		clients = new ArrayList<Client>();
		orders = new LinkedList<Order>();
	}

	public Library(String name, double discount, boolean isEmpty) {
		this();
		// TODO Auto-generated constructor stub
		setName(name);
		setDiscount(discount);
		if (!isEmpty)
			firstInit();
	}
	
	@Override
	public void set(Object obj) {
		// TODO Auto-generated method stub
		super.set(obj);
		booksAmount =  ((Library) obj).getBooksAmount();
		clientsAmount = ((Library) obj).getClientsAmount();
		ordersAmount =  ((Library) obj).getOrdersAmount();
		books = ((Library) obj).getBooks();
		clients = ((Library) obj).getClients();
		orders = ((Library) obj).getOrders();
	}
	
	@Override
	public void firstInit() {
		// TODO Auto-generated method stub
		super.firstInit();
		Calendar issueDate = Calendar.getInstance();
		Calendar expiresDate = Calendar.getInstance();
		setClientsAmount(count);
		for (int i = 0; i < getClientsAmount(); i++) {
			clients.add(new Client("Client C"+ (char) ('A'+i),(i+1)*5));
		}
		setBooksAmount(count);
		for (int i = 0; i < getBooksAmount(); i++) {
			books.add(new Book("Book B"+ (char) ('A'+i),(i+1)*5,"Book B"+ (char) ('A'+i)+".txt",i));
		}
		setOrdersAmount(count*3);
		issueDate.set(Calendar.DATE,issueDate.get(Calendar.DATE)+50);
		expiresDate.set(Calendar.DATE,expiresDate.get(Calendar.DATE)+50);
		for (int i = 0; i < getClientsAmount(); i++)
			for (int j = 0; j < getBooksAmount(); j++) {
				issueDate.set(Calendar.DATE,issueDate.get(Calendar.DATE)-20);
				expiresDate.set(Calendar.DATE,expiresDate.get(Calendar.DATE)-14);
				orders.add(new Order(getBooks().get(j).getName(), (i+1)*10+(j+1), getClients().get(i).getName(), j+1, getBooks().get(j), getClients().get(i), issueDate, expiresDate, (j == i)));
		}
		assignBooks("");
	}
	
	@Override
	public void print(String type) {
		// TODO Auto-generated method stub
		System.out.println("Name of "+type+": "+getName()+", popularuty is "+getDiscount()+"%.");
		System.out.println("List of clients: ");
		clients.forEach(client -> {
			System.out.print("# "+clients.indexOf(client)+": "); 
			client.print("client");
		});
//		for (int i = 0; i < getClientsAmount(); i++) {
//			clients.get(i).print("client");
//		}
		System.out.println("List of books: ");
		books.forEach(book -> {
			System.out.print("# "+books.indexOf(book)+": "); 
			book.print("book");
		});
//		for (int i = 0; i < getBooksAmount(); i++) {
//			books.get(i).print("book");
//		}
		System.out.println("List of orders: ");
		orders.forEach(order -> order.print("order"));
//		for (int i = 0; i < getOrdersAmount(); i++) {
//			orders.get(i).print("order");
//		}
	}
	
	@Override
	public void edit(String type, boolean isNew) {
		// TODO Auto-generated method stub
		String name;
		double discount;
		name = inputName(type,isNew);
		discount = inputDiscount(type,isNew);
		setName(name);
		setDiscount(discount);
	}
	
	public double inputDiscount(String type, boolean isNew) {
		// TODO Auto-generated method stub
		double discount;
		boolean isMake;
		do {
			isMake = true;
			if (!isNew) System.out.println("Current popularity of "+type+": "+getDiscount()+"%.");
			System.out.print("Enter new popularity of "+type+" (from 0 to 100): ");
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
		System.out.print("Choose section (1 - Clients menu, 2 - Books menu, 3 - Orders menu, 4 - View info, 5 - Edit info, 0 - Exit): ");
		choise = makeIntChoise(0,5);
		if (choise == 1) while (clientsMenu(type));
		if (choise == 2) while (booksMenu(type));
		if (choise == 3) while (ordersMenu(type));
		if (choise == 4) print(type);
		if (choise == 5) edit(type,false);
		if (choise == 0) {
			System.out.println("Thank you. Good bye...");
			return false;
		}
		return true;
	}
	
	// Library clients menu
	public boolean clientsMenu(String type) {
		int choise, index;
		String name;
		Client client;
		System.out.println("\n<<<Clients menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - List of clients, 2 - New client, 3 - Edit client, 4 - Delete client, 0 - Exit): ");
		choise = makeIntChoise(0,4);
		if (choise == 1) printClients();
		if (choise == 2) {
			client = new Client();
			client.edit("client",true);
			if (searchClients(client.getName()) < 0) {
				clients.add(client);
				setClientsAmount(getClientsAmount()+1);
			}
			else System.out.println("Client is already exist.");
		}
		if (choise == 3) {
			System.out.print("Enter name of client to edit (only letters and .): ");
			name = makeNameChoise();
			if (!name.equals("")) {
				index = searchClients(name);
				if (index >= 0) {
					clients.get(index).edit("client",false);
					if (!clients.get(index).getName().equals(name))
						changeClient(name,clients.get(index).getName(),false);
				}
				else System.out.println("Client is not exist.");
			}
		}
		if (choise == 4) {
			System.out.print("Enter name of client to remove (only letters and .): ");
			name = makeNameChoise();
			if (!name.equals("")) {
				index = searchClients(name);
				if (index >= 0) {
					clients.remove(index);
					setClientsAmount(getClientsAmount()-1);
					changeClient(name,"",true);
				}
				else System.out.println("Client is not exist.");
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;		
	}
	
	// Print list of clients
	public void printClients() {
		System.out.println("List of clients: ");
		Iterator<Client> index = clients.iterator();
		Client client;
		while (index.hasNext()) {
			client = index.next();
			System.out.print("# "+clients.indexOf(client)+": ");
			client.print("client");
		}
//		for (int i = 0; i < getClientsAmount(); i++) {
//			System.out.print("# "+i+" ");
//			clients.get(i).print("");			
//		}
	}
	
	// Search clients by name
	public int searchClients(String name) {
		Iterator<Client> index = clients.iterator();
		Client client;
		while (index.hasNext()) {
			client = index.next();
			if (client.getName().equals(name))
			return clients.indexOf(client);
		}
//		for (int i = 0; i < getClientsAmount(); i++) {
//			if (clients.get(i).getName().equals(name))
//				return i;
//		}
		return -1;
	}
	
	// Change or delete client name from list of orders
	public void changeClient(String name, String client, boolean isDel) {
		orders.forEach(order -> {
			if (order.getFileName().equals(name))
				if (isDel) {
					orders.remove(order);
					setOrdersAmount(getOrdersAmount()-1);
				}
				else order.setFileName(client);
		});
//		for (int i = 0; i < getOrdersAmount(); i++) {
//			if (orders.get(i).getFileName().equals(name))
//				if (isDel) {
//					orders.remove(i);
//					i--;
//					setOrdersAmount(getOrdersAmount()-1);
//				}
//				else orders.get(i).setFileName(client);
//		}
	}
	
	// Library books menu
	public boolean booksMenu(String type) {
		int choise, index, state;
		String name;
		Book book = new Book();
		System.out.println("\n<<<Books menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - List of books, 2 - List by state, 3 - New book, 4 - Edit book, 5 - Delete book, 0 - Exit): ");
		choise = makeIntChoise(0,5);
		if (choise == 1) printBooks(-1);
		if (choise == 2) {
			state = book.inputState("book",true);
			printBooks(state);
		}
		if (choise == 3) {
			book = new Book();
			book.edit("book",true);
			if (searchBooks(book.getName()) < 0) {
				books.add(book);
				setBooksAmount(getBooksAmount()+1);
			}
			else System.out.println("Book is already exist.");
		}
		if (choise == 4) {
			System.out.print("Enter name of book to edit (any symbols): ");
			name = makeStringChoise();
			if (!name.equals("")) {
				index = searchBooks(name);
				if (index >= 0) {
					books.get(index).edit("book",false);
					if (!books.get(index).getName().equals(name))
						changeBook(name,books.get(index).getName(),false);
				}
				else System.out.println("Book is not exist.");
			}
		}
		if (choise == 5) {
			System.out.print("Enter name of book to remove (any symbols): ");
			name = makeStringChoise();
			if (!name.equals("")) {
				index = searchBooks(name);
				if (index >= 0) {
					books.remove(index);
					setBooksAmount(getBooksAmount()-1);
					changeBook(name,"",true);
				}
				else System.out.println("Book is not exist.");
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;		
	}
	
	// Print list of books
	public void printBooks(int state) {
		System.out.println("List of books: ");
		Iterator<Book> index = books.iterator();
		Book book;
		while (index.hasNext()) {
			book = index.next();
			if (!((state >= 0) && (book.getState() != state))) {
				System.out.print("# "+books.indexOf(book)+": ");
				book.print("book");
			}
		}
//		for (int i = 0; i < getBooksAmount(); i++)
//			if (!((state >= 0) && (books.get(i).getState() != state))) {
//			System.out.print("# "+i+" ");
//			books.get(i).print("book");			
//		}
	}
	
	// Search book by name
	public int searchBooks(String name) {
		Iterator<Book> index = books.iterator();
		Book book;
		while (index.hasNext()) {
			book = index.next();
			if (book.getName().equals(name))
			return books.indexOf(book);
		}
//		for (int i = 0; i < getBooksAmount(); i++) {
//			if (books.get(i).getName().equals(name))
//				return i;
//		}
		return -1;
	}
	
	// Change or delete book name from list of orders
	public void changeBook(String name, String book, boolean isDel) {
		orders.forEach(order -> {
			if (order.getName().equals(name))
				if (isDel) {
					orders.remove(order);
					setOrdersAmount(getOrdersAmount()-1);
				}
				else order.setName(book);
		});	
//		for (int i = 0; i < getOrdersAmount(); i++) {
//			if (orders.get(i).getName().equals(name))
//				if (isDel) {
//					orders.remove(i);
//					i--;
//					setOrdersAmount(getOrdersAmount()-1);
//				}
//				else orders.get(i).setName(book);
//		}
	}

	// Update state of all books
	public void assignBooks (String name) {
		// TODO Auto-generated method stub
		int index;
		if (name.equals("")) books.forEach(book -> assignBook(book));
		else {
			index = searchBooks(name);
			if (index >= 0)
				assignBook(books.get(index));
		}	
	}
	
	// Update state of concrete book
	public void assignBook(Book book) {
		Calendar date = Calendar.getInstance();
		boolean isSet = false;
		Iterator<Order> index = orders.iterator();
		Order order;
		book.setState(0);
		while (index.hasNext()) {
			order = index.next();
			if (order.getName().equals(book.getName()) && order.getIssueDate().before(Calendar.getInstance()) && order.getMade())
				if (isSet)
					if (order.getIssueDate().after(date)) {
						date = order.getIssueDate();
						book.setState(order.getState());
					}
					else;								
				else {
					isSet = true;
					date = order.getIssueDate();
					book.setState(order.getState());
				}
		}
	}
	
	// Print list of free books between dates
	public void printFreeBooks(Calendar issueDate, Calendar expiresDate) {
		System.out.println("List of books free between "+dateToString(issueDate)+" and "+dateToString(expiresDate)+":");
		books.forEach(book -> {
			if (isBookFree(books.get(books.indexOf(book)),issueDate,expiresDate)) book.print("book");
		});
	}
	
	// Determine is book free between dates
	public boolean isBookFree(Book book, Calendar issueDate, Calendar expiresDate) {
		Iterator<Order> index = orders.iterator();
		Order order;
		while (index.hasNext()) {
			order = index.next();
			if (order.getName().equals(book.getName()) && 
				((order.getIssueDate().before(issueDate) && order.getExpiresDate().after(issueDate)) ||
				(order.getIssueDate().before(expiresDate) && order.getExpiresDate().after(expiresDate))))
				return false;
		}
		return true;
	}

	// Library orders menu
	public boolean ordersMenu(String type) {
		int choise, index, state;
		double discount;
		String name, fileName;
		Calendar issueDate = Calendar.getInstance();
		Calendar expiresDate = Calendar.getInstance();
		Order order = new Order();
		Book book;
		System.out.println("\n<<<Orders menu of "+type+" "+getName()+">>>");
		System.out.print("Choose section (1 - List of orders, 2 - List by book, 3 - List by client, 4 - List by state, 5 - List of free books on date, 6 - New order, 7 - Edit order, 8 - Delete order, 0 - Exit): ");
		choise = makeIntChoise(0,8);
		if (choise == 1) printOrders("","",-1);
		if (choise == 2) {
			name = order.getBook().inputName("book",true);
			printOrders(name,"",-1);
		}
		if (choise == 3) {
			fileName = order.getClient().inputName("client",true);
			printOrders("",fileName,-1);
		}
		if (choise == 4){
			state = order.inputState("order",true);
			printOrders("","",state);			
		}
		if (choise == 5) {
			issueDate =	order.inputDate("issue",null,true);
			expiresDate = order.inputDate("expire",null,true);
			printFreeBooks(issueDate,expiresDate);
		}
		if (choise == 6) {
			order = new Order();
			order.edit("order",true);
			if (searchOrders(order.getDiscount()) < 0) 
				if (isRightOrder(order)) {
					orders.add(order);
					setOrdersAmount(getOrdersAmount()+1);
				}
				else ;
			else System.out.println("Order is already exist.");
		}
		if (choise == 7) {
			System.out.print("Enter number of order to edit: ");
			discount = makeIntChoise(0,Integer.MAX_VALUE);
			if (discount != Integer.MIN_VALUE) {
				index = searchOrders(discount);
				if (index >= 0) {
					order = new Order();
					order.set(orders.get(index));
					order.edit("order",false);
					if (isRightOrder(order)) orders.set(index,order);
					else ;
				}
				else System.out.println("Order is not exist.");
			}
		}
		if (choise == 8) {
			System.out.print("Enter number of order to remove: ");
			discount = makeIntChoise(0,Integer.MAX_VALUE);
			if (discount != Integer.MIN_VALUE) {
				index = searchOrders(discount);
				if (index >= 0) {
					book = orders.get(index).getBook();
					orders.remove(index);
					assignBook(book);
					setOrdersAmount(getOrdersAmount()-1);
				}
				else System.out.println("Order is not exist.");
			}
		}
		if (choise == 0) {
			return false;
		}
		return true;		
	}
	
	// Print list of orders
	public void printOrders(String name, String fileName, int state) {
		System.out.println("List of orders: ");
		Iterator<Order> index = orders.iterator();
		Order order;
		boolean isPrint;
		while (index.hasNext()) {
			order = index.next();
			isPrint = true;
			if (!name.equals("") && !order.getName().equals(name)) isPrint = false;
			if (!fileName.equals("") && !order.getFileName().equals(fileName)) isPrint = false;
			if ((state >= 0) && (order.getState() != state)) isPrint = false;
			if (isPrint) order.print("order");
		}
//		for (int i = 0; i < getOrdersAmount(); i++) {
//			isPrint = true;
//			if (!name.equals("") && !orders.get(i).getName().equals(name)) isPrint = false;
//			if (!fileName.equals("") && !orders.get(i).getFileName().equals(fileName)) isPrint = false;
//			if ((state >= 0) && (orders.get(i).getState() != state)) isPrint = false;
//			if (isPrint) orders.get(i).print("order");
//		}
	}
	
	
	// Search orders by number
	public int searchOrders(double discount) {
		Iterator<Order> index = orders.iterator();
		Order order;
		while (index.hasNext()) {
			order = index.next();
			if (order.getDiscount() == discount)
			return orders.indexOf(order);
		}
//		for (int i = 0; i < getOrdersAmount(); i++) {
//			if (orders.get(i).getDiscount() == discount)
//				return i;
//		}
		return -1;
	}
	
	// Check if is right order
	public boolean isRightOrder(Order order) {
		Book book;
		Client client;
		int indexBook, indexClient;
		indexBook = searchBooks(order.getName());
		indexClient = searchClients(order.getFileName());
		if (indexBook >= 0) 
			if (indexClient >= 0) {
				book = books.get(indexBook);
				if (isBookFree(book,order.getIssueDate(),order.getExpiresDate())) {
					client = clients.get(indexClient);
					order.setBook(book);
					order.setClient(client);
					return true;
				}
				else  System.out.println("Book is not free.");
			}
			else System.out.println("Client is not exist.");
		else System.out.println("Book is not exist.");
		return false;
	}
	
	
	// Getters and setters
	public int getBooksAmount() {
		return booksAmount;
	}
	
	public void setBooksAmount(int booksAmount) {
		this.booksAmount = booksAmount;
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public int getClientsAmount() {
		return clientsAmount;
	}
	
	public void setClientsAmount(int clientsAmount) {
		this.clientsAmount = clientsAmount;
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}
	
	public int getOrdersAmount() {
		return ordersAmount;
	}
	
	public void setOrdersAmount(int ordersAmount) {
		this.ordersAmount = ordersAmount;
	}
	
	public LinkedList<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(LinkedList<Order> orders) {
		this.orders = orders;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Library library = new Library("Public Library",25,false);
		while (library.menu("library"));
	}

}
