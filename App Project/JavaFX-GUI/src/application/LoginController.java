+-------------------+
|  LoginController  |
+-------------------+
| - stage: Stage    |
| - scene: Scene    |
| - root: Parent    |
| + MenuBar: MenuBar |
| + usernameTextField: TextField |
| + passwordTextField: TextField |
| - user: CurrentUser |
+-------------------+
| + loginMethod(): void |
| + forgotPassword(event: ActionEvent): void |

package application;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class LoginController extends MainController {
	
	//User Data
	@FXML private TextField usernameTextField;
	@FXML private TextField passwordTextField;
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
		
	@FXML MenuBar MenuBar;
	
	CurrentUser user = new CurrentUser();

			
		// Login method
		public void loginMethod() {
			
			   String usernameInput = usernameTextField.getText();
			   String passwordInput = passwordTextField.getText();
			   int userID = 0;
			   String lastName = "";
			   String firstName = "";
			   String address = "";
			   String zip = "";
			   String state = "";
			   String username = "";
			   String password = "";
			   String email = "";
			   String ssn = "";
			   String securityAnswer = "";
			   
			    
			   try {
			       Connection conn = SQLConnection.getConnection();
			       String query = "{CALL loginMethod2 (?, ?, ?)}";
			       String query2 ="{Call RetrieveUserInfo2 (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			       String query3 ="{Call setCurrentUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			       String query4 ="{Call removeCurrentUser()}";
			 
			       try (CallableStatement statement = conn.prepareCall(query)) {
			    	   
			           statement.setString(1, usernameInput);
			           statement.setString(2, passwordInput);
			           statement.registerOutParameter(3, Types.NVARCHAR);
			 
			           statement.execute();
			 
			           Boolean authenticationResult = statement.getBoolean(3);
			           if (authenticationResult) {
			        	   try(CallableStatement statement4 =conn.prepareCall(query4)){
			        		   statement4.execute();
			        	   } catch (Exception e) {
						        e.printStackTrace();} 
			        	   finally {
						        	System.out.println("Table Dropped");
						        }
			        	   try(CallableStatement statement2 =conn.prepareCall(query2)) {
			        		   statement2.setString(1, usernameInput);
					           statement2.setString(2, passwordInput);
					           statement2.registerOutParameter(3, Types.NVARCHAR);
					           statement2.registerOutParameter(4, Types.NVARCHAR);
					           statement2.registerOutParameter(5, Types.NVARCHAR);
					           statement2.registerOutParameter(6, Types.NVARCHAR);
					           statement2.registerOutParameter(7, Types.NVARCHAR);
					           statement2.registerOutParameter(8, Types.NVARCHAR);
					           statement2.registerOutParameter(9, Types.NVARCHAR);
					           statement2.registerOutParameter(10, Types.NVARCHAR);
					           statement2.registerOutParameter(11, Types.NVARCHAR);
					           statement2.registerOutParameter(12, Types.NVARCHAR);
					           statement2.registerOutParameter(13, Types.NVARCHAR);
					           
					           
					           statement2.execute();
					           userID = statement2.getInt(3);
					           lastName = statement2.getString(4);
					           firstName = statement2.getString(5);
					           address = statement2.getString(6);
					           zip = statement2.getString(7);
					           state = statement2.getString(8);
					           username = statement2.getString(9);
					           password = statement2.getString(10);
					           email = statement2.getString(11);
					           ssn = statement2.getString(12);
					           securityAnswer = statement2.getString(13);
			        	   }

				           
				         try(CallableStatement statement3 =conn.prepareCall(query3)) {
				        	 statement3.setString(1, usernameInput);
				        	 statement3.registerOutParameter(2, Types.NVARCHAR);
					         statement3.registerOutParameter(3, Types.NVARCHAR);
					         statement3.registerOutParameter(4, Types.NVARCHAR);
					         statement3.registerOutParameter(5, Types.NVARCHAR);
					         statement3.registerOutParameter(6, Types.NVARCHAR);
					         statement3.registerOutParameter(7, Types.NVARCHAR);
					         statement3.registerOutParameter(8, Types.NVARCHAR);
					         statement3.registerOutParameter(9, Types.NVARCHAR);
					         statement3.registerOutParameter(10, Types.NVARCHAR);
					         statement3.registerOutParameter(11, Types.NVARCHAR);
					         statement3.registerOutParameter(12, Types.NVARCHAR);
					           
					          
					           
			        	   
					         statement3.execute();
					         
					         UserId = statement3.getInt(2); 				         } catch (Exception e) {
						        e.printStackTrace();}
				         finally {System.out.println("Statement 3 executed");}
			                
					           
			        	   
			        	   Parent root = FXMLLoader.load(getClass().getResource("FlightsScene.fxml"));
			        	   stage = (Stage) MenuBar.getScene().getWindow();
			        	   stage.setTitle("Available Flights");
			        	   scene = new Scene(root);
			        	   stage.setScene(scene);
			        	   stage.show();}
			        	   
			       
			       		else {
			        	   Parent root = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
			        	   stage = (Stage) MenuBar.getScene().getWindow();
			        	   stage.setTitle("Register Account");
			        	   scene = new Scene(root);
			        	   stage.setScene(scene);
			        	   stage.show();			        	   
			           }			        	   
			        	   
			        
			    } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			    	System.out.println("Login check completed");}
			       } finally {
			    	   System.out.println("final test");
			       }
			   
			    
			}  
	
		
		// Asks security question
		public void forgotPassword(ActionEvent event) throws IOException {
			Stage security = new Stage();
			security.initModality(Modality.APPLICATION_MODAL);
			security.setTitle("Forgot Password");
			
			Parent root = FXMLLoader.load(getClass().getResource("SecurityBoxScene.fxml"));
			scene = new Scene(root);
			security.setScene(scene);
			security.showAndWait();
		}
		
}
