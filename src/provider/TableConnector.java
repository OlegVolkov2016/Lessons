package provider;

public interface TableConnector {
	
	// Interface of table operations
	public String getTableName();
	public void setTableName(String tableName);
	public DatabaseConnector getDatabase();
	public void setDatabase(DatabaseConnector database);
	public boolean menu();
	public void print();
	public void insert();
	public void update();
	public void delete();
}
