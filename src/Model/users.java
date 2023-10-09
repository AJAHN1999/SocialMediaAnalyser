package Model;

public class users {
	private String UserId;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	//constructor
	public users(String userId, String username, String password, String firstname, String lastname) {
		this.UserId = userId;
		this.username = username;
		this.password = password;
		this.firstname= firstname;
		this.lastname= lastname;
		
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
}
