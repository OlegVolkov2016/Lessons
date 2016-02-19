package lesson4;

public class Order {
	
	int orderNumber, orderDate;
	String serviceName, clientName;
	
	Order (int index, int date, String name, String client) {
		setOrder(index,date,name,client);
	}
	
	void printOrder () {
		System.out.println("Order number "+orderNumber+":");
		System.out.println("Date - "+Reservation.resToString(orderDate)+", service - "+serviceName+", client - "+clientName);
	}

	void setOrder (int index, int date, String name, String client) {
		orderNumber = index;
		orderDate = date;
		serviceName = name;
		clientName = client;
	}
	
}
