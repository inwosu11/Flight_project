package GUI;

import javafx.application.Application;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class mainMenu extends Application {
	
	Stage window;
	BorderPane layout;
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Main Menu");
		
		Menu userMenu = new Menu("Account");
		
		userMenu.getItems().add(new MenuItem("Flights..."));
		
		userMenu.getItems().add(new MenuItem("Logout"));
		
		Menu mainMenu = new Menu("Main");
		
		mainMenu.getItems().add(new MenuItem("Main Menu"));
		mainMenu.getItems().add(new MenuItem("Register"));
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(mainMenu, userMenu);
		
		layout = new BorderPane();
		layout.setTop(menuBar);
		
		Scene scene = new Scene(layout, 400, 300);
		window.setScene(scene);
		window.show();
	}
}
