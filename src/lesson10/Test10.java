package lesson10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test10 {
	public static void main(String[] args) {
	try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
		Statement statement =  con.createStatement();
		String  query = "select count(*) from books";
		query = "select books_name, books_author from books";
		ResultSet resultSet  = statement.executeQuery(query);
//		while (resultSet.next()) {
//			
//			int count =resultSet.getInt(1);
//			System.out.println(count);
//		}
while (resultSet.next()) {
			
			String autor =resultSet.getString(2);
			System.out.println(autor);
		}
query = "insert into books (books_name, books_author) value ('Books_3','Author_3')";
int count = statement.executeUpdate(query);
query = "select books_name, books_author from books";
resultSet  = statement.executeQuery(query);
while (resultSet.next()) {
	
	String autor =resultSet.getString(2);
	System.out.println(autor);
}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
	//resultSet.close();
		//stm = 
		// conn = 
	}
	
}

}
