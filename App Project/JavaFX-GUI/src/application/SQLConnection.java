+------------------------+
|    SQLConnection       |
+------------------------+
|                        |
+------------------------+
| + getConnection(): Connection |
+------------------------+

package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	
	public static Connection getConnection(){

		try {

			String userName = "database@happiness";
			String userPassword = "Carrot22";
			String cnnString = "jdbc:sqlserver://happiness.database.windows.net:1433;"
			+ "database=projectDB;" + "user=" + userName + ";" 
			+ "password= " + userPassword + ";" 
			+ "encrypt=true;" 
			+ "trustServerCertificate=false;"
			+ "hostNameInCertifate=*.database.windows.net;"
			+ "loginTimeout=30;";
			Connection conn = DriverManager.getConnection (cnnString,userName, userPassword);
			System.out. println ("Connected");
			return conn;

			} catch (Exception e){System.out.println(e);}

		return null;
		}	

}
