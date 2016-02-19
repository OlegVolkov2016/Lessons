package provider;

import java.sql.ResultSet;
import java.sql.SQLException;

import menu.SimpleMenu;
import scanner.SimpleScanner;

public class Plans extends Table {
	
	// Plans database connector
	
	private static Plans instance = new Plans();
	
	private Plans() {
		// TODO Auto-generated constructor stub
		super();
		setTableName("plans");
		setDatabase(ProviderStats.getInstance());
	}
	
	public static Plans getInstance() {
		return instance;
	}

	@Override
	public boolean menu() {
		// TODO Auto-generated method stub
		int choise;
		choise = SimpleMenu.getInstance().choiseMenu("<<< Plans table menu >>>\nChoose section (1 - List of plans, 2 - New plan, 3 - Edit plan, 4 - Delete plan, 0 - Exit): ", 0, 4);
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
		result = getDatabase().executeQuery("select plan_name, plan_speed, plan_price from plans order by plan_speed");
		System.out.println("List of plans:");
		try {
			while (result.next())
				System.out.println("#"+(++count)+" - Plan: "+result.getString(1)+", speed "+result.getInt(2)+" Mbs, price "+result.getDouble(3)+"$ per month.");
			if (count > 0)
				System.out.println("Total number of plans: "+count);
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
		String plan_name;
		int plan_speed;
		double plan_price;
		System.out.print("Enter new name of plan: ");
		plan_name = SimpleScanner.getInstance().makeStringChoise();
		if (!plan_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from plans where plan_name = \'"+plan_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) == 0)) {
				System.out.print("Enter new plan "+plan_name+" speed in Mbs (from 0 to 1000): ");
				plan_speed = SimpleScanner.getInstance().makeIntChoise(0,1000);
				if (plan_speed >= 0) {
					System.out.print("Enter new plan "+plan_name+" price in $ per month (from 0 to 999.99): ");
					plan_price = SimpleScanner.getInstance().makeDoubleChoise(0, 999.99);
					if (plan_price != Double.NaN)
						getDatabase().executeUpdate("insert into plans (plan_name, plan_speed, plan_price) values (\'"+plan_name+"\', "+plan_speed+", "+plan_price+")");
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
		String plan_name, new_plan_name;
		int plan_speed;
		double plan_price;
		System.out.print("Enter name of plan to edit: ");
		plan_name = SimpleScanner.getInstance().makeStringChoise();
		if (!plan_name.equals("")) {
			result = getDatabase().executeQuery("select plan_name, plan_speed, plan_price from plans where plan_name = \'"+plan_name+"\'");
			try {
				if (result.next()) {
					System.out.print("Enter new name of plan: ");
					new_plan_name = SimpleScanner.getInstance().makeStringChoise();
					if (new_plan_name.equals(""))
						new_plan_name = result.getString(1);
					System.out.print("Enter new plan "+plan_name+" speed in Mbs (from 0 to 1000): ");
					plan_speed = SimpleScanner.getInstance().makeIntChoise(0,1000);
					if (plan_speed < 0)
						plan_speed = result.getInt(2);
					System.out.print("Enter new plan "+plan_name+" price in $ per month (from 0 to 999.99): ");
					plan_price = SimpleScanner.getInstance().makeDoubleChoise(0, 999.99);
					if (plan_price == Double.NaN)
						plan_price = result.getDouble(3);
					getDatabase().executeUpdate("update plans set plan_name = \'"+new_plan_name+"\', plan_speed = "+plan_speed+", plan_price = "+plan_price+" where plan_name = \'"+plan_name+"\'");
				}
				else
					System.out.println("Wrong name.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("SQLException: "+e.getMessage());
			}
		}
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		ResultSet result;
		String plan_name;
		System.out.print("Enter name of plan to delete: ");
		plan_name = SimpleScanner.getInstance().makeStringChoise();
		if (!plan_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from plans where plan_name = \'"+plan_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) > 0)) {
					getDatabase().executeUpdate("delete from plans where plan_name = \'"+plan_name+"\'");
				}
				else
					System.out.println("Wrong name.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("SQLException: "+e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		while (Plans.getInstance().menu());
	}

}
