package provider;

import java.sql.ResultSet;
import java.sql.SQLException;

import menu.SimpleMenu;
import scanner.SimpleScanner;

public class Payments extends Table {
	
	// Payments database connector
	
	private static Payments instance = new Payments();

	private Payments() {
		// TODO Auto-generated constructor stub
		super();
		setTableName("payments");
		setDatabase(ProviderStats.getInstance());
	}

	public static Payments getInstance() {
		return instance;
	}

	@Override
	public boolean menu() {
		// TODO Auto-generated method stub
		int choise;
		choise = SimpleMenu.getInstance().choiseMenu("<<< Payments table menu >>>\nChoose section (1 - List of payments, 2 - New payment, 3 - Delete payment, 0 - Exit): ", 0, 3);
		if (choise == 0) return false;
		if (choise == 1) print();
		if (choise == 2) insert();
		if (choise == 3) delete();
		return true;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		int choise;
		ResultSet result = null;
		String user_name, month_name;
		int count = 0;
		choise = SimpleMenu.getInstance().choiseMenu("Print list of payments (1 - all, 2 - by user, 3 - by month, 0 - Exit): ", 0, 3);
		if (choise == 0) return;
		if (choise == 1) {
			// Select
//			result = getDatabase().executeQuery("select user_name, month_name from payments, users, months where payments.user_id = users.user_id and payments.month_id = months.month_id");
			// Join
			result = getDatabase().executeQuery("select user_name, month_name from payments join users on payments.user_id = users.user_id join months on payments.month_id = months.month_id");
			System.out.println("List of all payments:");
			try {
				while (result.next())
					System.out.println("#"+(++count)+" - User: "+result.getString(1)+", Month: "+result.getString(2)+".");
				if (count > 0)
					System.out.println("Total number of payments: "+count);
				else
					System.out.println("List is empty.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("SQLException when print: "+e.getMessage());
			}
		}
		if (choise == 2) {
			System.out.print("Enter name of user: ");
			user_name = SimpleScanner.getInstance().makeStringChoise();
			if (!user_name.equals("")) {
				result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
				try {
					if ((result.next()) && (result.getInt(1) != 0)) {
						// Select
//						result = getDatabase().executeQuery("select user_name, month_name from payments, users, months where payments.user_id = users.user_id and payments.month_id = months.month_id and users.user_name = \'"+user_name+"\'");
						// Join
						result = getDatabase().executeQuery("select user_name, month_name from payments join users on (payments.user_id = users.user_id and users.user_name = \'"+user_name+"\') join months on payments.month_id = months.month_id");
						System.out.println("List of all payments of user " +user_name+":");
						while (result.next())
							System.out.println("#"+(++count)+" - Month: "+result.getString(2)+".");
						if (count > 0)
							System.out.println("Total number of payments: "+count);
						else
							System.out.println("List is empty.");
					}
					else {
						System.out.println("Wrong name of user.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("SQLException when print: "+e.getMessage());
				}
			}
		}
		if (choise == 3) {
			System.out.print("Enter name of month: ");
			month_name = SimpleScanner.getInstance().makeStringChoise();
			if (!month_name.equals("")) {
				result = getDatabase().executeQuery("select count(*) from months where month_name = \'"+month_name+"\'");
				try {
					if ((result.next()) && (result.getInt(1) != 0)) {
						// Select
//						result = getDatabase().executeQuery("select user_name, month_name from payments, users, months where payments.user_id = users.user_id and payments.month_id = months.month_id and months.month_name = \'"+month_name+"\'");
						// Join
						result = getDatabase().executeQuery("select user_name, month_name from payments join users on payments.user_id = users.user_id join months on (payments.month_id = months.month_id and months.month_name = \'"+month_name+"\')");
						System.out.println("List of all payments of month " +month_name+":");
						while (result.next())
							System.out.println("#"+(++count)+" - User: "+result.getString(1)+".");
						if (count > 0)
							System.out.println("Total number of payments: "+count);
						else
							System.out.println("List is empty.");
					}
					else {
						System.out.println("Wrong name of month.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("SQLException when print: "+e.getMessage());
				}
			}
		}
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		ResultSet result;
		String user_name, month_name;
		int payment_id = 0;
		System.out.print("Enter name of user of new payment: ");
		user_name = SimpleScanner.getInstance().makeStringChoise();
		if (!user_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) != 0)) {
					System.out.print("Enter name of month of new payment: ");
					month_name = SimpleScanner.getInstance().makeStringChoise();
					if (!month_name.equals("")) {
						result = getDatabase().executeQuery("select count(*) from months where month_name = \'"+month_name+"\'");
						if ((result.next()) && (result.getInt(1) != 0)) {
							result = getDatabase().executeQuery("select count(*) from payments where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\')");
							if ((result.next()) && (result.getInt(1) == 0)) {
								getDatabase().executeUpdate("insert into payments (user_id, month_id) values ((select user_id from users where user_name = \'"+user_name+"\'), (select month_id from months where month_name = \'"+month_name+"\'))");
								result = getDatabase().executeQuery("select payment_id from payments where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\')");
								if (result.next())
									payment_id = result.getInt(1);
								Accesses.getInstance().updateOnPayment(payment_id);
							}
							else {
								System.out.println("Payment from user "+user_name+" in months "+month_name+" is already exists.");
							}
						}
						else
							System.out.println("Wrong name of month.");
						}
					}
					else
						System.out.println("Wrong name of user.");
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
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		ResultSet result;
		String user_name, month_name;
		System.out.print("Enter name of user of delete payment: ");
		user_name = SimpleScanner.getInstance().makeStringChoise();
		if (!user_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) != 0)) {
					System.out.print("Enter name of month of delete payment: ");
					month_name = SimpleScanner.getInstance().makeStringChoise();
					if (!month_name.equals("")) {
						result = getDatabase().executeQuery("select count(*) from months where month_name = \'"+month_name+"\'");
						if ((result.next()) && (result.getInt(1) != 0)) {
							result = getDatabase().executeQuery("select count(*) from payments where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\')");
							if ((result.next()) && (result.getInt(1) != 0)) {
								getDatabase().executeUpdate("delete from payments where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\')");
								Accesses.getInstance().updateOnPayment(0);
							}
							else {
								System.out.println("Payment is not exists.");
							}
						}
						else
							System.out.println("Wrong name of month.");
					}
				}
				else
					System.out.println("Wrong name of user.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("SQLException when insert: "+e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		while (Payments.getInstance().menu());
	}

}
