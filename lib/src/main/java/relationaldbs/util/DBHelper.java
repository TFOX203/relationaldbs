package relationaldbs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

	private final static String dbURL = "jdbc:postgresql://localhost:5432/happylearning";
	private final static String username = "postgres";
	//the password required to connect
	private final static String password = "admin";

	public static Connection getConnection() {
		try {	
			return DriverManager.getConnection(dbURL, username, password);
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return  null;
	}
}
