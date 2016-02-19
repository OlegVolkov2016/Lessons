package lesson15;

public class Students implements java.io.Serializable {
	String name, address;
	public void mainCheck() {
		System.out.println("name "+name+", address "+address);
	}
}
