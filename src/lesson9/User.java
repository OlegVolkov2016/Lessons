package lesson9;

public class User {
	private String name;
	private int password;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password =  password.hashCode();
	}
	
	}
