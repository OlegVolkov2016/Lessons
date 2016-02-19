package hierarchyhotel;

public class HService extends HHotel {
	
	// Service of the hotel
	private int price;
	
	public HService() {
		// TODO Auto-generated constructor stub
		price = 0;
	}
	
	public HService(String name, int price) {
		super(name);
		// TODO Auto-generated constructor stub
		this.price = price;
	}
	
	public void set(String name, int price) {
		super.set(name);
		setPrice(price);
	}
	
	void print () {
		System.out.println("Service name "+getName()+":");
		System.out.println("Price - "+getPrice()+"$.");
	}
	
	// Getters and setters
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
}
