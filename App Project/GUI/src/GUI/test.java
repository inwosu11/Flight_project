package GUI;

import application.Flights;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test {
	
	Button mainButton;
	Button loginButton;
	Button registerButton;
	Button closeButton;
	Stage window;
	Scene mainScene, loginScene;
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		TableView<Flights> table;
		
		//From Column
		TableColumn<Flights, String> fromColumn = new TableColumn<>("From");
		fromColumn.setMinWidth(200);
		fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
		
		//To Column
		TableColumn<Flights, String> toColumn = new TableColumn<>("To");
		toColumn.setMinWidth(200);
		toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
		
		//FlightID Column
		TableColumn<Flights, String> flightIDColumn = new TableColumn<>("Flight ID");
		flightIDColumn.setMinWidth(200);
		flightIDColumn.setCellValueFactory(new PropertyValueFactory<>("flightID"));
		
		//Start Time Column
		TableColumn<Flights, String> startTimeColumn = new TableColumn<>("Start Time");
		startTimeColumn.setMinWidth(200);
		startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		
		//End Time Column
		TableColumn<Flights, String> endTimeColumn = new TableColumn<>("End Time");
		endTimeColumn.setMinWidth(200);
		endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		//Seats Column
		TableColumn<Flights, String> seatsColumn = new TableColumn<>("Seats Available");
		seatsColumn.setMinWidth(200);
		seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
		
		table = new TableView<>();
		table.setItems(getFlights());
		table.getColumns().addAll(fromColumn, toColumn, flightIDColumn, startTimeColumn, endTimeColumn, seatsColumn);
		
		//Main Menu button
		mainButton = new Button();
		mainButton.setText("Main Menu");
		mainButton.setOnAction(e -> window.setScene(mainScene));
		
		//Login Button
		loginButton = new Button();
		loginButton.setText("Login");
		loginButton.setOnAction(e -> {
			window.setScene(loginScene);
			boolean result = ConfirmBox.display("Error", "Test");
		});
		
		//Close Button
		closeButton = new Button();
		closeButton.setText("Close");
		closeButton.setOnAction(e -> closeProgram());
		
		
		//Main Menu Layout
		VBox mainLayout = new VBox(20);
		mainLayout.getChildren().addAll(loginButton, registerButton, closeButton);
		mainScene = new Scene(mainLayout, 200, 200);
		
		//Login Screen Layout
		VBox loginLayout = new VBox(20);
		loginLayout.getChildren().addAll(table);
		
		Label userLabel = new Label("Username: ");
		
		
		
		
		
		// Set the main scene
		window.setScene(mainScene);
		window.setTitle("Main Menu");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
			
		});
		primaryStage.show();
		
	}
	
	private void closeProgram() {
		Boolean answer = ConfirmBox.display("Close", "Are you sure you want to close");
		if(answer) {
			window.close();
		}
	}
	
	public ObservableList<Flights> getFlights() {
		ObservableList<Flights> flights = FXCollections.observableArrayList();
		flights.add(new Flights());
		return flights;
	}


}
