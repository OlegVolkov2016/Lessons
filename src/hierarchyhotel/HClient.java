package hierarchyhotel;

public class HClient extends HService {
	
	// Client of the hotel
	// price is a discount for client in %.
	private String contact;

	public HClient() {
		// TODO Auto-generated constructor stub
		contact = "";
	}

	public HClient(String name, String contact, int price) {
		super(name, price);
		// TODO Auto-generated constructor stub
		this.contact = contact;
	}

	public void set(String name, String contact, int price) {
		super.set(name, price);
		setContact(contact);
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	void print () {
		System.out.println("Client "+getName()+":");
		System.out.println("Phone: "+getContact()+".");
		System.out.println("Discount: "+getPrice()+"%.");
	}
		
	// Getters and setters
	public String getContact() {
		return contact;
	}
	
}
