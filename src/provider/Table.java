package provider;

abstract class Table implements TableConnector {
	
	// Abstract Table implements Table Connector 
	
	private String tableName;
	private DatabaseConnector database;
	
	// Getters and setters for all table classes
	
	@Override
	public String getTableName() {
		return tableName;
	}
	
	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Override
	public DatabaseConnector getDatabase() {
		return database;
	}
	
	@Override
	public void setDatabase(DatabaseConnector database) {
		this.database = database;
	}

}
