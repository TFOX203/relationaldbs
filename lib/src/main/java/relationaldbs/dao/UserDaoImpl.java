package relationaldbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import relationaldbs.model.User;

/**
 * @author Alexander
 */
public class UserDaoImpl implements UserDao{
	
	//The direction of the database that we are going to connect
		private final static String dbURL = "jdbc:postgresql://localhost:5432/happylearning";
									//"jdbc:postgresql://192.168.1.170:5432/sanmple?ssl=true"
		//The user name used to connect to the database
		private final static String username = "postgres";
		//The password required to connect
		private final static String password = "admin";
		//DriverManager class is used to manage different drivers for relational database
		String dropTableSQL = "drop table if exists users";
		String createTableSQL = "create table if not exists users(" + "id integer not null," + "usernamne VARCHAR(255)," + "psw VARCHAR(255)," + "isVIP TINYINT(1)," + "balance FLOAT," + "PRIMARY KEY (id)" + ")";
	
	

	@Override
	public boolean insert(User user) {
		//the ingredients that we need to do task
		//insert sql
		String insertSQL = "insert into users (name,password,balance,email,phone,address,role,registerDate), (?, ?, ?, ?, ?, ? ,? ,? )";
		// TODO Auto-generated method stub
		//create an object of connection to establish a network connection with the database used in our program
		try (Connection conn = 
		 	DriverManager.getConnection(dbURL, username, password);
				/**
				 * create an object of PreparedStatment which allows
				 * us to preapre, send and execute sqls
				 */
				PreparedStatement ps = 
					conn.prepareStatement(insertSQL);) {
			conn.close();
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setDouble(3, user.getBalance());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getRole());
			ps.setString(8, user.getRegisterDate());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User find(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findAll(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
