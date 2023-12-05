package application;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class LoginController {
	
	//User Data
	@FXML private TextField usernameTextField;
	@FXML private TextField passwordTextField;
	
//--------------------------------Start of Menu Bar Items--------------------------------------------
	
	private Stage stage;
	private Scene scene;
	private Parent root;
		
		
		
	@FXML MenuBar MenuBar;
		
	//Goes to Main Menu Scene
		public void goToMainMenu(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();
			stage.setTitle("Main Menu");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		
	}


	//Goes to Login Scene
		public void goToLogin(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();	
			stage.setTitle("Login");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		
	}
	
	//Goes to Register Scene
		public void goToRegister(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();	
			stage.setTitle("Register Account");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	
	//Goes to Flights Scene
		public void goToFlights(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("FlightsScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();		
			stage.setTitle("Available Flights");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	//Goes to User Info Scene
		public void goToUserInfo(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("UserInfoScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();	
			stage.setTitle("Account Information");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	
	//Goes to Booked Flights Scene
		public void goToBookedFlights(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("BookedFlightsScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();	
			stage.setTitle("Your Flights");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		// Deletes User Account
		public void deleteAccount() {
			
			
		}
		
	// Logs out of Account
		public void logout() {
			int userID = 0;
			
			
			
			
		}
		
	// Closes the window
		public void close(ActionEvent event) {
		stage = (Stage) MenuBar.getScene().getWindow();
		stage.close();
	}
		
			
//--------------------------------End of Menu Bar Items--------------------------------------------
			
		// Login method
		public int loginMethod() {
			   String username = usernameTextField.getText();
			   String password = passwordTextField.getText();
			   int userID = 0;
			    
			   try {
			       Connection conn = SQLConnection.getConnection();
			       String query = "{CALL loginMethod2 (?, ?, ?)}";
			       String query2 ="{Call RetrieveID2 (?, ?, ?)}";
			 
			       try (CallableStatement statement = conn.prepareCall(query)) {
			           statement.setString(1, username);
			           statement.setString(2, password);
			           statement.registerOutParameter(3, Types.NVARCHAR);
			 
			           statement.execute();
			 
			           Boolean authenticationResult = statement.getBoolean(3);
			           if (authenticationResult) {
			        	   try(CallableStatement statement2 =conn.prepareCall(query2)) {
			        		   statement2.setString(1, username);
					           statement2.setString(2, password);
					           statement2.registerOutParameter(3, Types.NVARCHAR);
					           
					           statement2.execute();
					           userID = statement2.getInt(3);
			        	   }
			        	   Parent root = FXMLLoader.load(getClass().getResource("FlightsScene.fxml"));
			        	   stage = (Stage) MenuBar.getScene().getWindow();
			        	   stage.setTitle("Available Flights");
			        	   scene = new Scene(root);
			        	   stage.setScene(scene);
			        	   stage.show();
			           }
			           else {
			        	   Parent root = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
			        	   stage = (Stage) MenuBar.getScene().getWindow();
			        	   stage.setTitle("Register Account");
			        	   scene = new Scene(root);
			        	   stage.setScene(scene);
			        	   stage.show();			        	   
			           }			        	   
			        	   
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			    	System.out.println("Login check completed");
			        return userID;
			    }
			}
}
