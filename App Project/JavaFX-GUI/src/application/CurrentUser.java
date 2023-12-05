+-------UML-------+
|    CurrentUser  |
+-----------------+
| - userID: int    |
| - firstName: String |
| - lastName: String |
| - address: String |
| - zip: String     |
| - state: String   |
| - username: String|
| - password: String|
| - email: String   |
| - SSN: String     |
| - securityAnswer: String|
+-----------------+
| + CurrentUser()  |
| + CurrentUser(int, String, String, String, String, String, String, String, String, String, String) |
+-----------------+

package application;

public class CurrentUser extends UserClass {
	
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
	
	public CurrentUser() {
		
	}
	public CurrentUser(int userID, String firstName, String lastName, String address, String zip, String state, String username, String password, String email, String SSN, String securityAnswer) {
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
		
}


