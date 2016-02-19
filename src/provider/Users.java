package provider;

import java.sql.ResultSet;
import java.sql.SQLException;

import menu.SimpleMenu;
import scanner.SimpleScanner;

public class Users extends Table {
	
	// Users database connector
	
	private static Users instance = new Users();
	
	private Users() {
		// TODO Auto-generated constructor stub
		super();
		setTableName("users");
		setDatabase(ProviderStats.getInstance());
	}
	
	public static Users getInstance() {
		return instance;
	}

	@Override
	public boolean menu() {
		// TODO Auto-generated method stub
		int choise;
		choise = SimpleMenu.getInstance().choiseMenu("<<< Users table menu >>>\nChoose section (1 - List of users, 2 - New user, 3 - Edit user, 4 - Delete user, 0 - Exit): ", 0, 4);
		if (choise == 0) return false;
		if (choise == 1) print();
		if (choise == 2) insert();
		if (choise == 3) update();
		if (choise == 4) delete();
		return true;
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		ResultSet result;
		int count = 0;
		result = getDatabase().executeQuery("select user_name from users order by user_name");
		System.out.println("List of users:");
		try {
			while (result.next())
				System.out.println("#"+(++count)+" - "+result.getString(1)+", ");
			if (count > 0)
				System.out.println("Total number of users: "+count);
			else
				System.out.println("List is empty.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("SQLException when print: "+e.getMessage());
		}
	}
	
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		ResultSet result;
		String user_name;
		String pass_str;
		int user_password;
		System.out.print("Enter new name of user: ");
		user_name = SimpleScanner.getInstance().makeStringChoise();
		if (!user_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) == 0)) {
					System.out.print("Enter new password of user "+user_name+": ");
					pass_str = SimpleScanner.getInstance().makeStringChoise();
					if (!pass_str.equals("")) {
						user_password = pass_str.hashCode();
						getDatabase().executeUpdate("insert into users (user_name, user_password) values (\'"+user_name+"\', "+user_password+")");
					}
				}
				else
					System.out.println("Name is already exists.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("SQLException when insert: "+e.getMessage());
			}
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		ResultSet result;
		String user_name, new_user_name;
		String pass_str;
		int user_password;
		System.out.print("Enter name of user to edit: ");
		user_name = SimpleScanner.getInstance().makeStringChoise();
		if (!user_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) > 0)) {
					System.out.print("Enter new name of user: ");
					new_user_name = SimpleScanner.getInstance().makeStringChoise();
					if (!new_user_name.equals("")) {
						System.out.print("Enter new password of user "+new_user_name+": ");
						pass_str = SimpleScanner.getInstance().makeStringChoise();
						if (!pass_str.equals("")) {
							user_password = pass_str.hashCode();
							getDatabase().executeUpdate("update users set user_name = \'"+new_user_name+"\', user_password = "+user_password+" where user_name = \'"+user_name+"\'");
						}
						else
							getDatabase().executeUpdate("update users set user_name = \'"+new_user_name+"\' where user_name = \'"+user_name+"\'");
					}
				}
				else
					System.out.println("Wrong name.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("SQLException when update: "+e.getMessage());
			}
		}
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		ResultSet result;
		String user_name;
		System.out.print("Enter name of user to delete: ");
		user_name = SimpleScanner.getInstance().makeStringChoise();
		if (!user_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) > 0)) {
					getDatabase().executeUpdate("delete from users where user_name = \'"+user_name+"\'");
				}
				else
					System.out.println("Wrong name.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("SQLException when delete: "+e.getMessage());
			}
		}
	}

	
	public static void main(String[] args) {
		while (Users.getInstance().menu());
	}

}
