/**
 * 
 */
package relationaldbs.model;

/*
 *
 * @author Alexander
 * 8 abr 2026
 */
public class User {
	
	//fields
	private String name;
	
	private String password;
	
	//The money that the user has in his account
	private double balance;
	
	private long id;
	
	//methods
	public long getId() {
		return id;
	}
	
	public void setId() {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//constructors
	public User(long id, String name, String password, double balance) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.balance = balance;
	}
	
}
