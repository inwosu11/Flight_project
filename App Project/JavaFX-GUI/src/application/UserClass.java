+-------------------+
|    UserClass     |
+-------------------+
| - userID: int     |
| - firstName: String |
| - lastName: String  |
| - address: String  |
| - zip: String      |
| - state: String    |
| - username: String |
| - password: String |
| - email: String    |
| - SSN: String      |
| - securityAnswer: String |
+-------------------+
| + UserClass()           |
| + UserClass(userID: int, firstName: String, lastName: String, address: String, zip: String, state: String, username: String, password: String, email: String, SSN: String, securityAnswer: String) |
| + getFirstName(): String |
| + setFirstName(firstName: String): void |
| + getLastName(): String |
| + setLastName(lastName: String): void |
| + getAddress(): String |
| + setAddress(address: String): void |
| + getZip(): String |
| + setZip(zip: String): void |
| + getState(): String |
| + setState(state: String): void |
| + getUsername(): String |
| + setUsername(username: String): void |
| + getPassword(): String |
| + setPassword(password: String): void |
| + getEmail(): String |
| + setEmail(email: String): void |
| + getSSN(): String |
| + setSSN(SSN: String): void |
| + getSecurityAnswer(): String |
| + setSecurityAnswer(securityAnswer: String): void |
| + getUserID(): int |
| + setUserID(userID: int): void |
+-------------------+

package application;

public class UserClass {
	
	private int userID = 0;
	private String firstName = "";
	private String lastName = "";
	private String address = "";
	private String zip = "";
	private String state = "";
	private String username = "";
	private String password = "";
	private String email = "";
	private String SSN = "";
	private String securityAnswer= "";
	
	public UserClass() {
		
	}
	
	public UserClass(int userID, String firstName, String lastName, String address, String zip, String state, String username, String password, String email, String SSN, String securityAnswer) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zip = zip;
		this.state = state;
		this.username = username;
		this.password = password;
		this.email = email;
		this.SSN = SSN;
		this.securityAnswer = securityAnswer;



	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}

}
