package lesson9;

import java.util.HashMap;
import java.util.Scanner;

public class UserMap implements Authorisation {
	private static UserMap userinst;
	private static Scanner sc;
	private HashMap<String, Integer> users;

	UserMap() {
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
		users = new HashMap<>();
	}
	
	public static UserMap getUserinst() {
		if (userinst == null) userinst = new UserMap();
		return userinst;
	}

	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		if (users.containsKey(user.getName())) return false;
		else users.put(user.getName(),user.getPassword());
		return true;
	}

	@Override
	public boolean checkUser(User user) {
		// TODO Auto-generated method stub
		Integer check;
		if (users.containsKey(user.getName())) {
			check = users.get(user.getName());
			if (users.get(user.getName()) == user.getPassword()) return true;
		}
		return false;
	}

	@Override
	public User getUser(boolean isNew) {
		// TODO Auto-generated method stub
		String name, password;
		User user = new User();
		if (isNew) {
			System.out.print("Input new name: ");
			name = sc.nextLine();
			System.out.print("Input new passowrd: ");
			password = sc.nextLine();
			user.setName(name);
			user.setPassword(password);
		}
		else {
			System.out.print("Input user name: ");
			name = sc.nextLine();
			System.out.print("Input user passowrd: ");
			password = sc.nextLine();
			user.setName(name);
			user.setPassword(password);
		}
		return user;
	}
	
	public void menu() {
		int choise;
		User user;
		do {
			System.out.println("1 - register, 2 - check, 0 - exit");
			choise = sc.nextInt();
			sc.nextLine();
			if (choise == 1) {
				user = getUser(true);
				if (registerUser(user)) System.out.println("Registered");
				else System.out.println("Not registered");
			}
			if (choise == 2) {
				user = getUser(true);
				if (checkUser(user)) System.out.println("Checked");
				else System.out.println("Not checked");
			}
			if (choise == 0) {
				break;
			}
		}
		while (true);
	}
	
	public static void main(String[] args) {
		UserMap um = getUserinst();
		um.menu();
	}
}
