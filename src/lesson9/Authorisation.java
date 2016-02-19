package lesson9;

public interface Authorisation {
	
	public boolean registerUser (User user);
	public boolean checkUser (User user);
	public User getUser(boolean isNew);

}
