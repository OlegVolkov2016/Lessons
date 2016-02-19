package hierarchyhotel;

public class HRoom extends HOrder {
	
	// Room of the hotel
	// date is room capacity.
	// price is mediumPrice.
	// name and contact are descriptions of room and not used now.
	private int lowPrice, highPrice;

	public HRoom() {
		// TODO Auto-generated constructor stub
		lowPrice = 0;
		highPrice = 0;
	}

	public HRoom(int number, int date, int lowPrice, int price, int highPrice, String name, String contact) {
		super(number, date, name, contact, price);
		// TODO Auto-generated constructor stub
		this.lowPrice = lowPrice;
		this.highPrice = highPrice;
	}
	
	public void set(int number, int date, int lowPrice, int price, int highPrice, String name, String contact) {
		super.set(number, date, name, contact, price);
		this.lowPrice = lowPrice;
		this.highPrice = highPrice;
	}

	void print () {
		System.out.println("Room No. "+getNumber()+":");
		System.out.println("Capacity - "+getDate()+" person(s).");
		System.out.println("Price in Low Season is "+getLowPrice()+"$ per night.");
		System.out.println("Price in Medium Season is "+getPrice()+"$ per night.");
		System.out.println("Price in High Season is "+getHighPrice()+"$ per night.");
	}

	// Getters and setters
	public int getLowPrice() {
		return lowPrice;
	}
	
	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}
	
	public int getHighPrice() {
		return highPrice;
	}
	
	public void setHighPrice(int highPrice) {
		this.highPrice = highPrice;
	}
	
}
