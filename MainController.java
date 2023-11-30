package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

public class MainController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML MenuBar MenuBar;
	
	

	
	
	
	//Goes to Main Menu Scene
	@FXML
	public void goToMainMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		stage = (Stage) MenuBar.getScene().getWindow();
		//stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	//Goes to Login Scene
	public void goToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		stage = (Stage) MenuBar.getScene().getWindow();		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	//Goes to Register Scene
	public void goToRegister(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
		stage = (Stage) MenuBar.getScene().getWindow();		
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Goes to Flights Scene
		public void goToFlights(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("FlightsScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();		
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	//Goes to User Info Scene
		public void goToUserInfo(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("UserInfoScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();		
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	
	//Goes to Booked Flights Scene
		public void goToBookedFlights(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("BookedFlightsScene.fxml"));
			stage = (Stage) MenuBar.getScene().getWindow();		
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
	//Create User
		public void createUser() {
			
			
		}
		
		
	//Find user
		public void searchUsers() {
			
			
		}
		
	//Asks security question
		public void forgotPassword() {
			
			
		}
		
	//Deletes User Account
		public void deleteAccount() {
			
			
		}
		
	//Logs out of Account
		public void logout() {
			
			
		}
		
	//Closes the window
	public void close(ActionEvent event) {
		stage = (Stage) MenuBar.getScene().getWindow();
		stage.close();
	}
	
	
	
	
}
