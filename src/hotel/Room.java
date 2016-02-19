package hotel;

public class Room {
	
	// Room of the hotel
	int roomNumber;
	int roomCapacity;
	int roomLowPrice;
	int roomMediumPrice;
	int roomHighPrice;
	
	Room (int number, int capacity, int lowPrice, int mediumPrice, int highPrice) {
		setRoom(number,capacity,lowPrice,mediumPrice,highPrice);
	}
		
	void printRoom () {
		System.out.println("Room No. "+roomNumber+":");
		System.out.println("Capacity - "+roomCapacity+" person(s).");
		System.out.println("Price in Low Season is "+roomLowPrice+"$ per night.");
		System.out.println("Price in Medium Season is "+roomMediumPrice+"$ per night.");
		System.out.println("Price in High Season is "+roomHighPrice+"$ per night.");
	}
	
	void setRoom (int number, int capacity, int lowPrice, int mediumPrice, int highPrice) {
		roomNumber = number;
		roomCapacity = capacity;
		roomLowPrice = lowPrice;
		roomMediumPrice = mediumPrice;
		roomHighPrice = highPrice;
	}
}
