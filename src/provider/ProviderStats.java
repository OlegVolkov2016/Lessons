package provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import menu.SimpleMenu;

public class ProviderStats implements DatabaseConnector {
	
	// Main database class
	private static ProviderStats instance = new ProviderStats();
	private String databaseName;
	private Connection databaseConnection;
	private Statement databaseStatement;

	private ProviderStats() {
		// TODO Auto-generated constructor stub
		setDatabaseName("providers_stats");
	}

	public static ProviderStats getInstance() {
		return instance;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		disconnect();
	}

	@Override
	public boolean connect() {
		// TODO Auto-generated method stub
		if (getDatabaseName().equals("")) {
			System.out.println("Database name is not defined");
			return false;
		}
		else {
			try {
				databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+getDatabaseName(),"root","");
				databaseStatement = getDatabaseConnection().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("SQLException when connect: "+e.getMessage());
			}
		}
		return true;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		try {
			databaseStatement.close();
			databaseConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("SQLException when disconnect: "+e.getMessage());
		}	
	}
	
	@Override
	public int executeUpdate(String query) {
		// TODO Auto-generated method stub
		try {
			return getDatabaseStatement().executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("SQLEception when executeUpdate: "+e.getMessage());
		}
		return -1;
	}

	@Override
	public ResultSet executeQuery(String query) {
		// TODO Auto-generated method stub
		try {
			return getDatabaseStatement().executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("SQLException when executeQuery: "+e.getMessage());
		}
		return null;
	}
	
	@Override
	public String getDatabaseName() {
		// TODO Auto-generated method stub
		return databaseName;
	}
	
	@Override
	public void setDatabaseName(String databaseName) {
		// TODO Auto-generated method stub
		String oldName = getDatabaseName();
		this.databaseName = databaseName;
		if (!connect()) {
			this.databaseName = oldName;
			connect();
		}
	}
	
	@Override
	public Connection getDatabaseConnection() {
		// TODO Auto-generated method stub
		return databaseConnection;
	}
	
	@Override
	public Statement getDatabaseStatement() {
		// TODO Auto-generated method stub
		return databaseStatement;
	}

	@Override
	public boolean menu() {
		// TODO Auto-generated method stub
		int choise;
		choise = SimpleMenu.getInstance().choiseMenu("<<< Provider Stats database menu >>>\nChoose section (1 - Months menu, 2 - Users menu, 3 - Plans menu, 4 - Orders menu, 5 - Payments menu, 6 - Accesses menu, 0 - Exit): ", 0, 6);
		if (choise == 0) return false;
		if (choise == 1) while (Months.getInstance().menu());
		if (choise == 2) while (Users.getInstance().menu());
		if (choise == 3) while (Plans.getInstance().menu());
		if (choise == 4) while (Orders.getInstance().menu());
		if (choise == 5) while (Payments.getInstance().menu());
		if (choise == 6) while (Accesses.getInstance().menu());
		return true;
	}

	public static void main(String[] args) {
		while (ProviderStats.getInstance().menu());
		System.out.println("Thank you. Good bye.");
	}
	
}
