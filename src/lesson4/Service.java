package lesson4;

public class Service {
	
	String serviceName;
	int servicePrice;
	
	Service (String name, int price) {
		setService(name,price);
	}
	
	void printService () {
		System.out.println("Service name "+serviceName+":");
		System.out.println("Price - "+servicePrice+"$.");
	}

	void setService (String name, int price) {
		serviceName = name;
		servicePrice = price;
	}
}
