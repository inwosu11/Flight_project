package application;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController extends MainController {
	
	//User Data
	@FXML private TextField firstNameTextField;
	@FXML private TextField lastNameTextField;
	@FXML private TextField addressTextField;
	@FXML private TextField zipCodeTextField;
	@FXML private TextField stateTextField;
	@FXML private TextField usernameTextField;
	@FXML private TextField passwordTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField ssnTextField;
	@FXML private TextField securityTextField;
	

	
			private Stage stage;
			private Scene scene;
			private Parent root;
			
			
			
			@FXML MenuBar MenuBar;
			
//--------------------------------Page Buttons-----------------------------------------------------
	
			
		//Create User
			public void createUser() {
				String firstName = firstNameTextField.getText();
				String lastName = lastNameTextField.getText();
				String address = addressTextField.getText();
				String zipCode = zipCodeTextField.getText();
				String state = stateTextField.getText();
				String username = usernameTextField.getText();
				String password = passwordTextField.getText();
				String email = emailTextField.getText();
				String SSN = ssnTextField.getText();
				String security = securityTextField.getText();
				try {
					Connection conn = SQLConnection.getConnection();
					PreparedStatement statement = conn.prepareStatement(
							"insert into [dbo].[users] (FirstName, LastName, Address, "
							+ "Zip, State, Username, Password, Email, SSN, SecurityAnswer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					statement.setString(1, firstName);
					statement.setString(2, lastName);
					statement.setString(3, address);
					statement.setString(4, zipCode);
					statement.setString(5, state);
					statement.setString(6, username);
					statement.setString(7, password);
					statement.setString(8, email);
					statement.setString(9, SSN);
					statement.setString(10, security);
					
					statement.execute();
					
		        	   Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		        	   stage = (Stage) MenuBar.getScene().getWindow();
		        	   stage.setTitle("Login");
		        	   scene = new Scene(root);
		        	   stage.setScene(scene);
		        	   stage.show();
												
					conn.close();

				} catch(Exception e) {System.out.println(e);}
				finally {
					System.out.println("Insert Completed");
					
				}

			}
			
	


}
