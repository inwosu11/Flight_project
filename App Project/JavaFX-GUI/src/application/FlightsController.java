package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Types;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FlightsController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML MenuBar MenuBar;
	
	//Flight Table
	@FXML private TableView<Flights> FlightTable;
	@FXML private TableColumn<Flights, String> columnFlight;
	@FXML private TableColumn<Flights, String> columnFrom;
	@FXML private TableColumn<Flights, String> columnTo;
	@FXML private TableColumn<Flights, String> columnDeparture;
	@FXML private TableColumn<Flights, String> columnArrival;
	@FXML private TableColumn<Flights, String> columnSeatsAvailable;
	@FXML private TableColumn<Flights, Button> actionColumn;
	
	
	
	// Flight Data
	@FXML private TextField flightIDTextField;
	@FXML private TextField fromTextField;
	@FXML private TextField toTextField;
	@FXML private TextField departureTextField;
	@FXML private TextField arrivalTextField;
	@FXML private TextField seatsTextField;
	
	ObservableList<Flights> listM;
	
	int index = -1;
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	LoginController userId = new LoginController();



	@Override
	public void initialize(URL url, ResourceBundle rb){
		
		columnFlight.setCellValueFactory(new PropertyValueFactory<Flights, String>("flightID"));
		columnFrom.setCellValueFactory(new PropertyValueFactory<Flights, String>("from"));
		columnTo.setCellValueFactory(new PropertyValueFactory<Flights, String>("to"));
		columnDeparture.setCellValueFactory(new PropertyValueFactory<Flights, String>("departure"));
		columnArrival.setCellValueFactory(new PropertyValueFactory<Flights, String>("arrival"));
		columnSeatsAvailable.setCellValueFactory(new PropertyValueFactory<Flights, String>("seats"));
		actionColumn.setCellValueFactory(new PropertyValueFactory<Flights, Button>("button"));
		
		listM = FlightsConnect.getDataFlights();
		FlightTable.setItems(listM);

		
		
//		for (int i = 0; i < button.length; i++) {
//	        button[i] = new Button("Button " + i);
//	        int buttonIndex = i;
//	        button[i].setOnAction(event -> handleButtonAction(event, buttonIndex));
//	    }
//		
//	}
//		private void handleButtonAction(ActionEvent event, int buttonIndex) {
//		    System.out.println("Button " + buttonIndex + " clicked!");
		}
	

//        public void FlightIdHandler(ActionEvent event) {
//            // Get the selected row
//            Flights selectedFlight = FlightTable.getSelectionModel().getSelectedItem();
//
//            // Check if a row is selected
//            if (selectedFlight != null) {
//                String flightId = selectedFlight.getFlightID();
//                System.out.println("Selected Flight ID: " + flightId);
//                // Do something with the flight ID
//            } else {
//                System.out.println("No row selected");
//            }
//        }
  


	    public void FlightIdHandler(ActionEvent event) {
	        // Get the selected row
	        Flights selectedFlight = FlightTable.getSelectionModel().getSelectedItem();

	        // Check if a row is selected
	        if (selectedFlight != null) {
	            String flightId = selectedFlight.getFlightID();
	            System.out.println("Selected Flight ID: " + flightId);

	            int userID = userId.loginMethod();
	            int flightIdInt = Integer.parseInt(flightId);

	            bookFlightProcedure(userID, flightIdInt);
	            
	        } else {
	            System.out.println("No row selected");
	        }
	    }

	    private void bookFlightProcedure(int userID, int flightId) {
	    	
	        try (Connection conn = SQLConnection.getConnection()) {
	            // Create a CallableStatement for the stored procedure
	            try (CallableStatement cs = conn.prepareCall("{call BookFlight(?, ?)}")) {
	            	userID = userId.loginMethod();
	                cs.setInt(2, flightId);

	                // Execute the stored procedure
	                cs.execute();

	                // Check the output parameter (if any)
	                // For example, you can retrieve an output parameter like this:
	                // int outputValue = cs.getInt(3); // assuming the output parameter is at index 3
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }
	

	
	// Search Criteria
	public void searchCriteria() {
	    ObservableList<Flights> resultList = FXCollections.observableArrayList();

	    String flightIDTest = flightIDTextField.getText();
	    String fromTest = fromTextField.getText();
	    String toTest = toTextField.getText();
	    String departureTest = departureTextField.getText();
	    String arrivalTest = arrivalTextField.getText();
	    String seatsTest = seatsTextField.getText();
	    String flightID = "*";
	    String from = "*";
	    String to = "*";
	    String departure = "*";
	    String arrival = "*";
	    String seats = "*";

	    try {
	        Connection conn = SQLConnection.getConnection();

	        // Construct the SQL query based on conditions
	        String query = "SELECT FlightID, DepartureCity, ArrivalCity, DepartureDate, ArrivalDate, SeatsAvailable " +
	                       "FROM [dbo].[Flights] " +
	                       "WHERE 1=1 ";

	        if (!flightIDTest.isEmpty()) {
	            query += " AND FlightID = ?";
	            flightID = flightIDTest;
	        }

	        if (!fromTest.isEmpty()) {
	            query += " AND DepartureCity = ?";
	            from = fromTest;
	        }

	        // Repeat the same pattern for other conditions

	        PreparedStatement statement = conn.prepareStatement(query);

	        // Set parameters based on conditions
	        int parameterIndex = 1;
	        if (!flightIDTest.isEmpty()) {
	            statement.setString(parameterIndex++, flightIDTest);
	        }

	        if (!fromTest.isEmpty()) {
	            statement.setString(parameterIndex++, fromTest);
	        }

	        // Repeat the same pattern for other conditions

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            // Create a Flights object for each row in the result set
	            Flights flight = new Flights(
	                    resultSet.getString("FlightID"),
	                    resultSet.getString("DepartureCity"),
	                    resultSet.getString("ArrivalCity"),
	                    resultSet.getString("DepartureDate"),
	                    resultSet.getString("ArrivalDate"),
	                    resultSet.getString("SeatsAvailable")
	            );
	            resultList.add(flight);
	        }

	        resultSet.close();
	        statement.close();
	        conn.close();

	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        System.out.println("Test");
	        FlightTable.setItems(resultList);
	    }
	}

			
	
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
		LoginController userID = new LoginController();
		if (userID.loginMethod() != 0) {
		Parent root = FXMLLoader.load(getClass().getResource("FlightsScene.fxml"));
		stage = (Stage) MenuBar.getScene().getWindow();		
		stage.setTitle("Available Flights");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
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
		
		
		
	}
	
// Closes the window
	public void close(ActionEvent event) {
	stage = (Stage) MenuBar.getScene().getWindow();
	stage.close();
}



}
