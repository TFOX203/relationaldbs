package relationaldbs.dao;

import java.util.List;

import relationaldbs.model.User;

/**
 * the interface that defines the functionalities to interact
 * with the database
 * @author Alexander
 * apr 10, 2026
 */
public interface UserDao {
	/*
	 * insert an user object to database
	 * @param
	 * @return true if inserted successfully, otherwise false
	 */
	 public boolean insert (User user);
	 
	 /*
	  * delete an user by id 
	  * @param
	  * @return if the user is deletes successfully, return true
	  */
	 public boolean delete(Long id); 
		
	 /**
	  * update user data 
	  * @param user
	  */
	 public void update (User user);
	 
		/**
		 * find an user by his id
		 * @param id
		 */
	public User find(Long id);
	
	/**
	 * find an user by his email
	 * @param id
	 * @return
	 */
	public User find( String email);
		
	public User findAll( String email);
	/**
	 * retrieve all users
	 * @return a list of users
	 */
	public List<User> findAll(); 
		
	
	
}
