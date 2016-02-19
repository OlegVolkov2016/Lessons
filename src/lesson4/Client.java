package lesson4;

public class Client {
	
	String clientName;
	String clientPhone;
	int clientDiscount;
	
	Client (String name, String phone, int discount) {
		setClient(name,phone,discount);
	}
	
	void printClient () {
		System.out.println("Client "+clientName+":");
		System.out.println("Phone: "+clientPhone+".");
		System.out.println("Discount: "+clientDiscount+"%.");
	}

	void setClient (String name, String phone, int discount) {
		clientName = name;
		clientPhone = phone;
		clientDiscount = discount;
	}
}
