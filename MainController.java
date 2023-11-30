package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

public class MainController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
	//Goes to Main Menu Scene
	public void goToMainMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	//Goes to Login Scene
	public void goToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	//Goes to Register Scene
	public void goToRegister(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("RegisterScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//Goes to Flights Scene
		public void goToFlights(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("FlightsScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
		
	//Closes the window
	public void close(ActionEvent event) {
		stage.close();
	}
	
	
	
	
}
