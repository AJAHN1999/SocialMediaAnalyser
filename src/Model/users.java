package Model;

public class users {
	private int userid;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private int isVIP;
	
	//constructor
	public users( String username, String firstname, String lastname, String password) {
		this.username = username;
		this.password = password;
		this.firstname= firstname;
		this.lastname= lastname;
		
	}
	
	//constructor
		public users(int userid, String username, String firstname, String lastname, String password) {
			this.userid = userid;
			this.username = username;
			this.password = password;
			this.firstname= firstname;
			this.lastname= lastname;
			
		}
		
		public users(int userid, String username, String firstname, String lastname, String password, int isVIP) {
			this.userid = userid;
			this.username = username;
			this.password = password;
			this.firstname= firstname;
			this.lastname= lastname;
			this.isVIP = isVIP;
			
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(int isVIP) {
		this.isVIP = isVIP;
	}
	
	
}
