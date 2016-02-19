package provider;

import java.sql.ResultSet;
import java.sql.SQLException;

import menu.SimpleMenu;
import scanner.SimpleScanner;

public class Accesses extends Table {
	
	// Accesses database connector
	
	private static Accesses instance = new Accesses();

	private Accesses() {
		// TODO Auto-generated constructor stub
		super();
		setTableName("accesses");
		setDatabase(ProviderStats.getInstance());
	}

	public static Accesses getInstance() {
		return instance;
	}
	
	@Override
	public boolean menu() {
		// TODO Auto-generated method stub
		int choise;
		choise = SimpleMenu.getInstance().choiseMenu("<<< Accesses table menu >>>\nChoose section (1 - List of accesses, 2 - Update accesses, 0 - Exit): ", 0, 2);
		if (choise == 0) return false;
		if (choise == 1) print();
		if (choise == 2) update();
		return true;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		int choise;
		ResultSet result = null;
		String user_name, month_name;
		int count = 0;
		choise = SimpleMenu.getInstance().choiseMenu("Print list of acesses (1 - all, 2 - by user, 3 - by month, 0 - Exit): ", 0, 3);
		if (choise == 0) return;
		if (choise == 1) {
			// Select
//			result = getDatabase().executeQuery("select user_name, month_name, access_state from accesses, orders, users, months where accesses.order_id = orders.order_id and orders.user_id = users.user_id and orders.month_id = months.month_id");
			// Join
			result = getDatabase().executeQuery("select user_name, month_name, access_state from accesses join orders on accesses.order_id = orders.order_id join users on orders.user_id = users.user_id join months on orders.month_id = months.month_id");
			System.out.println("List of all payments:");
			try {
				while (result.next())
					System.out.println("#"+(++count)+" - User: "+result.getString(1)+", Month: "+result.getString(2)+", Access: "+result.getBoolean(3)+".");
				if (count > 0)
					System.out.println("Total number of accesses: "+count);
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
//						result = getDatabase().executeQuery("select user_name, month_name, access_state from accesses, orders, users, months where accesses.order_id = orders.order_id and orders.user_id = users.user_id and orders.month_id = months.month_id and users.user_name = \'"+user_name+"\'");
						// Join
						result = getDatabase().executeQuery("select user_name, month_name, access_state from accesses join orders on accesses.order_id = orders.order_id join users on (orders.user_id = users.user_id and users.user_name = \'"+user_name+"\') join months on orders.month_id = months.month_id");
						System.out.println("List of all accesses of user " +user_name+":");
						while (result.next())
							System.out.println("#"+(++count)+" - Month: "+result.getString(2)+", Access: "+result.getBoolean(3)+".");
						if (count > 0)
							System.out.println("Total number of accesses: "+count);
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
//						result = getDatabase().executeQuery("select user_name, month_name, access_state from accesses, orders, users, months where accesses.order_id = orders.order_id and orders.user_id = users.user_id and orders.month_id = months.month_id and months.month_name = \'"+month_name+"\'");
						// Join
						result = getDatabase().executeQuery("select user_name, month_name, access_state from accesses join orders on accesses.order_id = orders.order_id join users on orders.user_id = users.user_id join months on (orders.month_id = months.month_id and months.month_name = \'"+month_name+"\')");
						System.out.println("List of all accesses of month " +month_name+":");
						while (result.next())
							System.out.println("#"+(++count)+" - User: "+result.getString(1)+", Access: "+result.getBoolean(3)+".");
						if (count > 0)
							System.out.println("Total number of accesses: "+count);
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
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		updateOnOrder(0);
	}
	
	public void updateOnOrder(int order_id) {
		if (order_id > 0) {
			getDatabase().executeUpdate("insert into accesses (order_id) values ("+order_id+")");
			// Select
//			getDatabase().executeUpdate("update accesses set access_state = (exists (select accesses.order_id, orders.user_id, orders.month_id from payments, orders where accesses.order_id = orders.order_id and payments.user_id = orders.user_id and payments.month_id = orders.month_id)) where accesses.order_id = "+order_id);
			// Join
			getDatabase().executeUpdate("update accesses set access_state = (exists (select orders.order_id from orders join payments on (payments.user_id = orders.user_id and payments.month_id = orders.month_id) where accesses.order_id = orders.order_id)) where accesses.order_id = "+order_id);
		}
		else {
			getDatabase().executeUpdate("delete from accesses");
			getDatabase().executeUpdate("insert into accesses (order_id) select order_id from orders");
			// Select
//			getDatabase().executeUpdate("update accesses set access_state = (exists (select orders.order_id, orders.user_id, orders.month_id from orders, payments where accesses.order_id = orders.order_id and payments.user_id = orders.user_id and payments.month_id = orders.month_id))");
			// Join
//			getDatabase().executeUpdate("update accesses set access_state = (exists (select orders.order_id from orders join payments on (payments.user_id = orders.user_id and payments.month_id = orders.month_id) where accesses.order_id = orders.order_id))");
			updateOnPayment(0);
		}
	}
	
	public void updateOnPayment(int payment_id) {
		if (payment_id > 0) {
			// Select
//			getDatabase().executeUpdate("update accesses set access_state = (exists (select accesses.order_id, orders.user_id, orders.month_id from payments, orders where accesses.order_id = orders.order_id and payments.user_id = orders.user_id and payments.month_id = orders.month_id)) where order_id in (select orders.order_id from orders, payments where payments.user_id = orders.user_id and payments.month_id = orders.month_id and payments.payment_id = "+payment_id+")");
			// Join
			getDatabase().executeUpdate("update accesses set access_state = (exists (select orders.order_id from orders join payments on (payments.user_id = orders.user_id and payments.month_id = orders.month_id) where accesses.order_id = orders.order_id and payments.user_id = orders.user_id and payments.month_id = orders.month_id)) where order_id in (select orders.order_id from orders join payments on (payments.user_id = orders.user_id and payments.month_id = orders.month_id and payments.payment_id = "+payment_id+"))");
		}
		else {
			// Select
//			getDatabase().executeUpdate("update accesses set access_state = (exists (select accesses.order_id, orders.user_id, orders.month_id from payments, orders where accesses.order_id = orders.order_id and payments.user_id = orders.user_id and payments.month_id = orders.month_id))");
			// Join
			getDatabase().executeUpdate("update accesses set access_state = (exists (select orders.order_id from orders join payments on (payments.user_id = orders.user_id and payments.month_id = orders.month_id) where accesses.order_id = orders.order_id))");
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		while (Accesses.getInstance().menu());
	}

}
