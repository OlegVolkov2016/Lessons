package provider;

import java.sql.ResultSet;
import java.sql.SQLException;

import menu.SimpleMenu;
import scanner.SimpleScanner;

public class Months extends Table {
	
	 // Months database connector
	
	private static Months instance = new Months();
	
	private Months() {
		// TODO Auto-generated constructor stub
		super();
		setTableName("months");
		setDatabase(ProviderStats.getInstance());
		ResultSet result;
		result = getDatabase().executeQuery("select count(*) from months");
		try {
			if (result.next() && (result.getInt(1) == 0))
				for (Monthnames i : Monthnames.values()) {
					getDatabase().executeUpdate("insert into months (month_name) values (\'"+i.toString()+"\')");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("SQLException when construct: "+e.getMessage());
		}
	}
	
	public static Months getInstance() {
		return instance;
	}
	
	@Override
	public boolean menu() {
		// TODO Auto-generated method stub
		int choise;
		choise = SimpleMenu.getInstance().choiseMenu("<<< Months table menu >>>\nChoose section (1 - List of months, 2 - Edit month, 0 - Exit): ", 0, 2);
		if (choise == 0) return false;
		if (choise == 1) print();
		if (choise == 2) update();
		return true;
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		ResultSet result;
		int count = 0;
		result = getDatabase().executeQuery("select month_name from months order by month_id");
		System.out.println("List of months:");
		try {
			while (result.next())
				System.out.println("#"+(++count)+" - "+result.getString(1)+", ");
			if (count > 0)
				System.out.println("Total number of months: "+count);
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
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		ResultSet result;
		String month_name, new_month_name;
		System.out.print("Enter name of month to edit: ");
		month_name = SimpleScanner.getInstance().makeStringChoise();
		if (!month_name.equals("")) {
			result = getDatabase().executeQuery("select count(*) from months where month_name = \'"+month_name+"\'");
			try {
				if ((result.next()) && (result.getInt(1) > 0)) {
					System.out.print("Enter new name of month: ");
					new_month_name = SimpleScanner.getInstance().makeStringChoise();
					if (!new_month_name.equals("")) {
						getDatabase().executeUpdate("update months set month_name = \'"+new_month_name+"\' where month_name = \'"+month_name+"\'");
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
		
	}
	
	public static void main(String[] args) {
		while (Months.getInstance().menu());
	}

}
