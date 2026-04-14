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
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String role; // admin / cliente
	
	private String registerDate;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setId(long id) {
		this.id = id;
	}

	
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

	public User(String name, String password, double balance, long id, String email, String phone, String address,
			String role, String registerDate) {
		//constructors
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.registerDate = registerDate;
	}
	
	

	
}
