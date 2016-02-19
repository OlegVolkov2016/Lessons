package hierarchyhotel;

public class HReservation extends HRoom {
	
	// Reservation of the hotel
	// name is client name.
	// lowPrice is number of nights.
	// highPrice is number of persons.
	// price is total value of reservation.
	// contact is information about reservation and is not used.
	private int room;
	private boolean isAccept;

	public HReservation() {
		// TODO Auto-generated constructor stub
		room = 0;
		isAccept = false;
	}

	public HReservation(int number, int room, String name, int date, int lowPrice, int highPrice, int price, boolean isAccept, String contact) {
		super(number, date, lowPrice, price, highPrice, name, contact);
		// TODO Auto-generated constructor stub
		this.room = room;
		this.isAccept = isAccept;
	}
	
	public void Set(int number, int room, String name, int date, int lowPrice, int highPrice, int price, boolean isAccept, String contact) {
		super.set(number, date, lowPrice, price, highPrice, name, contact);
		this.room = room;
		this.isAccept = isAccept;
	}

	void print () {
		System.out.println("Reservation number "+getNumber()+":");
		System.out.println("Room "+getRoom()+".");
		System.out.println("Client: "+getName()+".");
		System.out.println("From "+toString(getDate())+" to "+toString((getDate()+getLowPrice())%365)+" for "+getHighPrice()+" person(s).");
		System.out.println("Value is "+getPrice()+"$.");
		System.out.println("Accepted:"+getAccept());
	}
		
	// Getters and setters
	public int getRoom() {
		return room;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}
	
	public boolean getAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}
	
}
