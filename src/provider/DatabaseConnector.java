package provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface DatabaseConnector {
	
	// Interface of database operations
	public boolean connect();
	public void disconnect();
	public int executeUpdate(String query);
	public ResultSet executeQuery(String query);
	public String getDatabaseName();
	public void setDatabaseName(String databaseName);
	public Connection getDatabaseConnection();
	public Statement getDatabaseStatement();
	public boolean menu();

}
