package hierarchyhotel;

public class HOrder extends HClient {
	
	// Order of the hotel
	// contact is client name of order.
	private int number, date;

	public HOrder() {
		// TODO Auto-generated constructor stub
		number = 0;
		date = 0;
	}

	public HOrder(int number, int date, String name, String contact, int price) {
		super(name, contact, price);
		// TODO Auto-generated constructor stub
		this.number = number;
		this.date = date;
	}
	
	public void set(int number, int date, String name, String contact, int price) {
		super.set(name, contact, price);
		this.number = number;
		this.date = date;
	}

	void print () {
		System.out.println("Order number "+getNumber()+":");
		System.out.println("Date - "+toString(getDate())+", service - "+getName()+", client - "+getContact()+", price - "+getPrice()+"$.");
	}
	
	// Getters and setters
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getDate() {
		return date;
	}
	
	public void setDate(int date) {
		this.date = date;
	}	
	
}
