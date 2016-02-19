package provider;

import java.sql.ResultSet;
import java.sql.SQLException;

import menu.SimpleMenu;
import scanner.SimpleScanner;

public class Orders extends Table {
	
	// Orders database connector

	private static Orders instance = new Orders();

	private Orders() {
		// TODO Auto-generated constructor stub
		super();
		setTableName("orders");
		setDatabase(ProviderStats.getInstance());
	}

	public static Orders getInstance() {
		return instance;
	}

	@Override
	public boolean menu() {
		// TODO Auto-generated method stub
		int choise;
		choise = SimpleMenu.getInstance().choiseMenu("<<< Orders table menu >>>\nChoose section (1 - List of orders, 2 - New order, 3 - Edit order, 4 - Delete order, 0 - Exit): ", 0, 4);
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
		int choise;
		ResultSet result = null;
		String user_name, month_name, plan_name;
		int count = 0;
		choise = SimpleMenu.getInstance().choiseMenu("Print list of orders (1 - all, 2 - by user, 3 - by month, 4 - by plan, 0 - Exit): ", 0, 4);
		if (choise == 0) return;
		if (choise == 1) {
			// Select
//			result = getDatabase().executeQuery("select user_name, month_name, plan_name, plan_speed, plan_price from orders, users, months, plans where orders.user_id = users.user_id and orders.month_id = months.month_id and orders.plan_id = plans.plan_id");
			//  Join
			result = getDatabase().executeQuery("select user_name, month_name, plan_name, plan_speed, plan_price from orders join users on orders.user_id = users.user_id join months on orders.month_id = months.month_id join plans on orders.plan_id = plans.plan_id");
			System.out.println("List of all orders:");
			try {
				while (result.next())
					System.out.println("#"+(++count)+" - User: "+result.getString(1)+", Month: "+result.getString(2)+", Plan: "+result.getString(3)+", speed "+result.getInt(4)+" Mbs, price "+result.getDouble(5)+"$ per month.");
				if (count > 0)
					System.out.println("Total number of orders: "+count);
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
//						result = getDatabase().executeQuery("select user_name, month_name, plan_name, plan_speed, plan_price from orders, users, months, plans where orders.user_id = users.user_id and orders.month_id = months.month_id and orders.plan_id = plans.plan_id and users.user_name = \'"+user_name+"\'");
						// Join
						result = getDatabase().executeQuery("select user_name, month_name, plan_name, plan_speed, plan_price from orders join users on (orders.user_id = users.user_id and users.user_name = \'"+user_name+"\') join months on orders.month_id = months.month_id join plans on orders.plan_id = plans.plan_id");
						System.out.println("List of all orders of user " +user_name+":");
						while (result.next())
							System.out.println("#"+(++count)+" - Month: "+result.getString(2)+", Plan: "+result.getString(3)+", speed "+result.getInt(4)+" Mbs, price "+result.getDouble(5)+"$ per month.");
						if (count > 0)
							System.out.println("Total number of orders: "+count);
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
//						result = getDatabase().executeQuery("select user_name, month_name, plan_name, plan_speed, plan_price from orders, users, months, plans where orders.user_id = users.user_id and orders.month_id = months.month_id and orders.plan_id = plans.plan_id and months.month_name = \'"+month_name+"\'");
						// Join
						result = getDatabase().executeQuery("select user_name, month_name, plan_name, plan_speed, plan_price from orders join users on orders.user_id = users.user_id join months on (orders.month_id = months.month_id and months.month_name = \'"+month_name+"\') join plans on orders.plan_id = plans.plan_id");
						System.out.println("List of all orders of month " +month_name+":");
						while (result.next())
							System.out.println("#"+(++count)+" - User: "+result.getString(1)+", Plan: "+result.getString(3)+", speed "+result.getInt(4)+" Mbs, price "+result.getDouble(5)+"$ per month.");
						if (count > 0)
							System.out.println("Total number of orders: "+count);
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
		if (choise == 4) {
			System.out.print("Enter name of plan: ");
			plan_name = SimpleScanner.getInstance().makeStringChoise();
			if (!plan_name.equals("")) {
				result = getDatabase().executeQuery("select count(*) from plans where plan_name = \'"+plan_name+"\'");
				try {
					if ((result.next()) && (result.getInt(1) != 0)) {
						// Select
//						result = getDatabase().executeQuery("select user_name, month_name, plan_name, plan_speed, plan_price from orders, users, months, plans where orders.user_id = users.user_id and orders.month_id = months.month_id and orders.plan_id = plans.plan_id and plans.plan_name = \'"+plan_name+"\'");
						// Join
						result = getDatabase().executeQuery("select user_name, month_name, plan_name, plan_speed, plan_price from orders join users on orders.user_id = users.user_id join months on orders.month_id = months.month_id join plans on (orders.plan_id = plans.plan_id and plans.plan_name = \'"+plan_name+"\')");
						System.out.println("List of all orders of plan " +plan_name+":");
						while (result.next())
							System.out.println("#"+(++count)+" - User: "+result.getString(1)+", Month: "+result.getString(2)+".");
						if (count > 0)
							System.out.println("Total number of orders: "+count);
						else
							System.out.println("List is empty.");
					}
					else {
						System.out.println("Wrong name of plan.");
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
		String user_name, month_name, plan_name;
		System.out.print("Enter name of user of new order: ");
		user_name = SimpleScanner.getInstance().makeStringChoise();
		if (!user_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) != 0)) {
					System.out.print("Enter name of month of new order: ");
					month_name = SimpleScanner.getInstance().makeStringChoise();
					if (!month_name.equals("")) {
						result = getDatabase().executeQuery("select count(*) from months where month_name = \'"+month_name+"\'");
						if ((result.next()) && (result.getInt(1) != 0)) {
							System.out.print("Enter name of plan of new order: ");
							plan_name = SimpleScanner.getInstance().makeStringChoise();
							if (!plan_name.equals("")) {
								result = getDatabase().executeQuery("select count(*) from plans where plan_name = \'"+plan_name+"\'");
								if ((result.next()) && (result.getInt(1) != 0)) {
									result = getDatabase().executeQuery("select count(*) from orders where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\')");
									if ((result.next()) && (result.getInt(1) == 0)) {
										getDatabase().executeUpdate("insert into orders (user_id, month_id, plan_id) values ((select user_id from users where user_name = \'"+user_name+"\'), (select month_id from months where month_name = \'"+month_name+"\'), (select plan_id from plans where plan_name = \'"+plan_name+"\'))");
										result = getDatabase().executeQuery("select order_id from orders where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\') and plan_id = (select plan_id from plans where plan_name = \'"+plan_name+"\')");
										if (result.next())
											Accesses.getInstance().updateOnOrder(result.getInt(1));
									}
									else {
										System.out.println("Order from user "+user_name+" in months "+month_name+" is already exists.");
										if (SimpleScanner.getInstance().isRepeat("Replace with plan "+plan_name+"? (y - to replace): "))
											getDatabase().executeUpdate("update orders set user_id = (select user_id from users where user_name = \'"+user_name+"\'), month_id = (select month_id from months where month_name = \'"+month_name+"\'), plan_id = (select plan_id from plans where plan_name = \'"+plan_name+"\')");
									}
								}
								else
									System.out.println("Wrong name of plan.");
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
		ResultSet result;
		String user_name, month_name, plan_name;
		System.out.print("Enter name of user of edit order: ");
		user_name = SimpleScanner.getInstance().makeStringChoise();
		if (!user_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) != 0)) {
					System.out.print("Enter name of month of edit order: ");
					month_name = SimpleScanner.getInstance().makeStringChoise();
					if (!month_name.equals("")) {
						result = getDatabase().executeQuery("select count(*) from months where month_name = \'"+month_name+"\'");
						if ((result.next()) && (result.getInt(1) != 0)) {
							System.out.print("Enter name of plan of edit order: ");
							plan_name = SimpleScanner.getInstance().makeStringChoise();
							if (!plan_name.equals("")) {
								result = getDatabase().executeQuery("select count(*) from plans where plan_name = \'"+plan_name+"\'");
								if ((result.next()) && (result.getInt(1) != 0)) {
									result = getDatabase().executeQuery("select count(*) from orders where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\')");
									if ((result.next()) && (result.getInt(1) == 0)) {
										System.out.println("Order from user "+user_name+" in months "+month_name+" is not exists.");
										if (SimpleScanner.getInstance().isRepeat("Add new order with plan "+plan_name+"? (y - to add): "))
											getDatabase().executeUpdate("insert into orders (user_id, month_id, plan_id) values ((select user_id from users where user_name = \'"+user_name+"\'), (select month_id from months where month_name = \'"+month_name+"\'), (select plan_id from plans where plan_name = \'"+plan_name+"\'))");
									}
									else {
										getDatabase().executeUpdate("update orders set user_id = (select user_id from users where user_name = \'"+user_name+"\'), month_id = (select month_id from months where month_name = \'"+month_name+"\'), plan_id = (select plan_id from plans where plan_name = \'"+plan_name+"\') where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\')");
									}
								}
								else
									System.out.println("Wrong name of plan.");
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
	public void delete() {
		// TODO Auto-generated method stub
		ResultSet result;
		String user_name, month_name, plan_name;
		System.out.print("Enter name of user of delete order: ");
		user_name = SimpleScanner.getInstance().makeStringChoise();
		if (!user_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from users where user_name = \'"+user_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) != 0)) {
					System.out.print("Enter name of month of delete order: ");
					month_name = SimpleScanner.getInstance().makeStringChoise();
					if (!month_name.equals("")) {
						result = getDatabase().executeQuery("select count(*) from months where month_name = \'"+month_name+"\'");
						if ((result.next()) && (result.getInt(1) != 0)) {
							System.out.print("Enter name of plan of delete order: ");
							plan_name = SimpleScanner.getInstance().makeStringChoise();
							if (!plan_name.equals("")) {
								result = getDatabase().executeQuery("select count(*) from plans where plan_name = \'"+plan_name+"\'");
								if ((result.next()) && (result.getInt(1) != 0)) {
									result = getDatabase().executeQuery("select count(*) from orders where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\') and plan_id = (select plan_id from plans where plan_name = \'"+plan_name+"\')");
									if ((result.next()) && (result.getInt(1) != 0)) {
										getDatabase().executeUpdate("delete from orders where user_id = (select user_id from users where user_name = \'"+user_name+"\') and month_id = (select month_id from months where month_name = \'"+month_name+"\') and plan_id = (select plan_id from plans where plan_name = \'"+plan_name+"\')");
									}
									else {
										System.out.println("Order is not exists.");
									}
								}
								else
									System.out.println("Wrong name of plan.");
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
		while (Orders.getInstance().menu());
	}

}
