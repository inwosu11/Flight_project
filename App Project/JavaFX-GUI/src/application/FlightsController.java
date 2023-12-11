package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FlightsController extends MainController implements Initializable {
	
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
	
	
	
	// Flight Data
	@FXML
	protected TextField flightIDTextField;
	@FXML protected TextField fromTextField;
	@FXML protected TextField toTextField;
	@FXML protected TextField departureTextField;
	@FXML protected TextField arrivalTextField;
	@FXML protected TextField seatsTextField;
	
	ObservableList<Flights> listM;
	
	int index = -1;
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	@Override
	public void initialize(URL url, ResourceBundle rb){
		
		columnFlight.setCellValueFactory(new PropertyValueFactory<Flights, String>("flightID"));
		columnFrom.setCellValueFactory(new PropertyValueFactory<Flights, String>("from"));
		columnTo.setCellValueFactory(new PropertyValueFactory<Flights, String>("to"));
		columnDeparture.setCellValueFactory(new PropertyValueFactory<Flights, String>("departure"));
		columnArrival.setCellValueFactory(new PropertyValueFactory<Flights, String>("arrival"));
		columnSeatsAvailable.setCellValueFactory(new PropertyValueFactory<Flights, String>("seats"));
		
		listM = FlightsConnect.getDataFlights();
		FlightTable.setItems(listM);		
	}

	    public void FlightIdHandler(ActionEvent event) throws IOException {
	        // Get the selected row
	        Flights selectedFlight = FlightTable.getSelectionModel().getSelectedItem();

	        // Check if a row is selected
	        if (selectedFlight != null) {
	            String flightId = selectedFlight.getFlightID();
	            System.out.println("Selected Flight ID: " + flightId);

	            int userID = getUserId();
	            int flightIdInt = Integer.parseInt(flightId);

	            bookFlightProcedure(userID, flightIdInt);
	    	
	            
	        } else {
	            System.out.println("No row selected");
	        }
	        
	    }
	    

	    private void bookFlightProcedure(int userID, int flightId) throws IOException {
	    	
	        try (Connection conn = SQLConnection.getConnection()) {
	        	
	        	String query = ("{Call BookFlight6 (?, ?, ?)}");
	        	String query2 = ("{Call checkConflicting2 (?, ?, ?)} ");

	        	        try (CallableStatement statement = conn.prepareCall(query)) {
	        	            statement.setInt(1, userID);
	        	            statement.setInt(2, flightId);
	        	            statement.registerOutParameter(3, Types.INTEGER);
	        	            statement.execute();

	        	            try (CallableStatement statement2 = conn.prepareCall(query2)) {
	        	                statement2.setInt(1, userID);
	        	                statement2.setInt(2, flightId);
	        	                statement2.registerOutParameter(3, Types.INTEGER);

	        	                statement2.execute();

	        	                int authenticationResult = statement2.getInt(3);
	        	                System.out.println("Authentication Result: " + authenticationResult);

	        	                if (authenticationResult == 1) {
	        	                    ConflictingFlightPopup.display("Cannot Book");
	        	                    System.out.println("Display reached");
	        	                } else {
	        	                    Parent root = FXMLLoader.load(getClass().getResource("BookedFlightsScene.fxml"));
	        	                    stage = (Stage) MenuBar.getScene().getWindow();
	        	                    stage.setTitle("Your Flights");
	        	                    scene = new Scene(root);
	        	                    stage.setScene(scene);
	        	                    stage.show();
	        	                }
	        	            } catch (Exception e) {
	        	                e.printStackTrace();
	        	            }
	        	        } catch (Exception e) {
	        	            e.printStackTrace();
	        	        }
	        	    } catch (Exception e) {
	        	        e.printStackTrace();
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

	        if (!toTest.isEmpty()) {
	            query += " AND ArrivalCity = ?";
//	            from = fromTest;
	            to = toTest;
	        }
	        
	        if (!departureTest.isEmpty()) {
	            query += " AND DepartureDate = ?";
//	            from = fromTest;
	            departure = departureTest;
	        }
	        
	        if (!arrivalTest.isEmpty()) {
	            query += " AND ArrivalDate = ?";
//	            from = fromTest;
	            arrival = arrivalTest;
	        }
	        
	        if (!seatsTest.isEmpty()) {
	            query += " AND SeatsAvailable = ?";
//	            from = fromTest; 
	            seats = seatsTest;
	        }
	        PreparedStatement statement = conn.prepareStatement(query);

	        // Set parameters based on conditions
	        int parameterIndex = 1;
	        if (!flightIDTest.isEmpty()) {
	            statement.setString(parameterIndex++, flightIDTest);
	        }

	        if (!fromTest.isEmpty()) {
	            statement.setString(parameterIndex++, fromTest);
	        }

	        if (!toTest.isEmpty()) {
	            statement.setString(parameterIndex++, toTest);
	        }
	        
	        if (!departureTest.isEmpty()) {
	            statement.setString(parameterIndex++, departureTest);
	        }
	        
	        if (!arrivalTest.isEmpty()) {
	            statement.setString(parameterIndex++, arrivalTest);
	        }
	        
	        if (!seatsTest.isEmpty()) {
	            statement.setString(parameterIndex++, seatsTest);
	        }

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
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

}
