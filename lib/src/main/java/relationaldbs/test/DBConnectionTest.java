 
package relationaldbs.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionTest {
	
	//The direction of the database that we are going to connect
	private final static String dbURL = "jdbc:postgresql://localhost:5432/postgres";
								//"jdbc:postgresql://192.168.1.170:5432/sanmple?ssl=true"
	//The user name used to connect to the database
	private final static String username = "postgres";
	//The password required to connect
	private final static String password = "admin";
	//DriverManager class is used to manage different drivers for relational database
	
	public static void main(String[] args) {
		
		try {
			//Obtain a object of class "Connection"wich represents 
			//a connection between our application and the database
			Connection conn = DriverManager.getConnection(dbURL, username, password);
			System.out.println("The adress of the connection object is " + conn);
			
			createDataBase(conn);
			
			String dropTableSQL = "drop table if exists users";
			PreparedStatement ps = conn.prepareStatement(dropTableSQL);
			ps.executeUpdate();
			ps.close();
						
			//Table creation SQL
			String createTableSQL = "create table if not exists users(id serial, usernamne VARCHAR(255)," + "psw VARCHAR(255)," + "isVIP BOOLEAN," + "balance FLOAT," + "PRIMARY KEY (id)" + ")";
			
			ps = conn.prepareStatement(createTableSQL);
			//ps.execute();
			ps.executeUpdate();
			ps.close();
			
			//Insert SQL
			String insertSQL = "insert into users( name,  password,  balance,  id, email,  phone,  address, role, registerDate) values('sasha', '12343', 3000, 1,'644420201','calle ramadan 23','user','12/05/2021'), ('Alejandro', '123', false, 234.3)";
			
			ps = conn.prepareStatement(insertSQL);
			System.out.println(ps.executeUpdate());
			ps.close();
			
			selectByName(ps, conn, "Manolo");
			selectByName(ps, conn, "Lucas");
			
			
						
			deleteByName(ps, conn, "Panbro");
			deleteByName(ps, conn, "Sansha");
			
			/*
			 * String createTableSQL = 
			 * "create table if not exists users(" 
			 * + "id integer not null," + "usernamne VARCHAR(255)," 
			 * + "psw VARCHAR(255)," 
			 * + "isVIP TINYINT(1)," 
			 * + "balance FLOAT," 
			 * + "PRIMARY KEY (id)" 
			 * + ")";
			 */
						
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
	}
	
	private static void selectByName(PreparedStatement ps, Connection conn, String name) throws SQLException {
		
		//Select psw, isVIP from users where username = 'Manolo';
		String selectSQL = "select * from users where username = " + name;
		
		ps = conn.prepareStatement(selectSQL);
		System.out.println(ps.executeUpdate());
		
		//We use the "next()" to check if we have reached the end of the result set
		//If receive true, then there is more data
		//rs.next();
		//We can use a series of "getXXXX" methods to access each column of each row of data
		try(ResultSet rs = ps.getResultSet()){				
			
			if(rs.next()) {
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("psw"));
				System.out.println(rs.getBoolean("isVIP"));
			}
		}		
					
		ps.close();
		
	}

	private static void deleteByName(PreparedStatement ps, Connection conn, String name) throws SQLException {
		
		//Delete SQL
		String deleteSQL = "DELETE FROM users WHERE username = " + name;
		
		ps = conn.prepareStatement(deleteSQL);
		System.out.println(ps.executeUpdate());
		ps.close();
		
	}

	private static void createDataBase(Connection conn) {
		
		try {
			//A SQL for creating a new database if it not exist
			String dbCreationSQL = "CREATE DATABASE happylearning";
			//This one is for Postgresql
			//CREATE DATABASE happylearning
			PreparedStatement ps = conn.prepareStatement(dbCreationSQL);
			//ps.execute();
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
