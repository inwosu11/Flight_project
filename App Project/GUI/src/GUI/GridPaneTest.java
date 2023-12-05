package GUI;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class GridPaneTest extends Application{
	
	Stage window;
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Grid Test");
		
		GridPane loginLayout = new GridPane();
		loginLayout.setPadding(new Insets(10, 10, 10, 10));
		loginLayout.setVgap(8);
		loginLayout.setHgap(10);
		
		//Name label
		Label nameLabel = new Label("Username: ");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		//Name input
		TextField nameInput = new TextField();
		GridPane.setConstraints(nameInput, 1, 0);
		
		//Pass label
		Label passLabel = new Label("Password: ");
		GridPane.setConstraints(passLabel, 0, 1);
		
		//Pass input
		TextField passInput = new TextField();
		passInput.setPromptText("password");
		GridPane.setConstraints(passInput, 1, 1);
		
		Button loginButton = new Button("Log In");
		loginButton.setOnAction(e -> isInt(nameInput, nameInput.getText()));
		GridPane.setConstraints(loginButton, 1, 2);
		
		loginLayout.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);
		
		Scene scene = new Scene(loginLayout, 300, 200);
		window.setScene(scene);
		
		window.show();
		
	}
	
	private boolean isInt(TextField input, String message) {
		try {
			int age = Integer.parseInt(input.getText());
			System.out.println("User is: " + age);
			return true;
		} catch(NumberFormatException e) {
			System.out.println("Error: " + message + " is not a number"):
				return false;
			
		}
		
		
	}

}
