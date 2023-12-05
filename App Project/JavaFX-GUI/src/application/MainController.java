package application;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import javafx.util.Callback;

public class MainController extends Main{

	
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
	
	Connection conn = SQLConnection.getConnection();
	
	
	

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
			if (UserId != 0) {
				Parent root = FXMLLoader.load(getClass().getResource("FlightsScene.fxml"));
				stage = (Stage) MenuBar.getScene().getWindow();		
				stage.setTitle("Available Flights");
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			else 
				NoAccountPopup.display("Error");
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
			 UserId = 0;
			
			
		}
		
	// Closes the window
		public void close(ActionEvent event) {
		stage = (Stage) MenuBar.getScene().getWindow();
		stage.close();
	}
	
	// Gets User Id
		public int getUserId() {
		    int userID = 0;
		    String query = "SELECT ID FROM [dbo].[CurrentUser]";
		    try (CallableStatement statement = conn.prepareCall(query)) {
		        // Execute the query
		        try (ResultSet resultSet = statement.executeQuery()) {
		            // Check if there is a result
		            if (resultSet.next()) {
		                // Retrieve the ID from the result set
		                userID = resultSet.getInt("ID");
		            }
		        }
		    } catch (Exception e) {
		        System.out.println(e);
		    } finally {
		        System.out.println("getUserID completed: " + userID);
		        return userID;
		    }
		}

		
//--------------------------------End of Menu Bar Items--------------------------------------------
		
//--------------------------------Page Buttons-----------------------------------------------------

			// Find user
				public void searchUsers() {
//					String username = firstNameTextField.getText();
//					
//					
//					
				}
				

							
			// Gets Password
				public void getPassword() {
				    String username = usernameTextField.getText();
				    String securityQuestion = securityTextField.getText();
				 
				    try {
				        Connection conn = SQLConnection.getConnection();
				        String query = "{CALL RetrievePassword (?, ?, ?)}";
				 
				        try (CallableStatement statement = conn.prepareCall(query)) {
				            statement.setString(1, username);
				            statement.setString(2, securityQuestion);
				            statement.registerOutParameter(3, Types.NVARCHAR);
				 
				            statement.execute();
				 
				            String password = statement.getString(3);
				            System.out.println("Retrieved password: " + password);
				            PasswordPopup.display("Password", password);
				        }
				    } catch (Exception e) {
				        e.printStackTrace();
				    } finally {
				        System.out.println("Password retrieval completed");
				        		        
				    }
				}

	
		
}

