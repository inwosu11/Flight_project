+---------------------+
|  FlightsController  |
+---------------------+
| - stage: Stage      |
| - scene: Scene      |
| - root: Parent      |
| + MenuBar: MenuBar  |
| + FlightTable: TableView<Flights> |
| + columnFlight: TableColumn<Flights, String> |
| + columnFrom: TableColumn<Flights, String> |
| + columnTo: TableColumn<Flights, String> |
| + columnDeparture: TableColumn<Flights, String> |
| + columnArrival: TableColumn<Flights, String> |
| + columnSeatsAvailable: TableColumn<Flights, String> |
| + flightIDTextField: TextField |
| + fromTextField: TextField |
| + toTextField: TextField |
| + departureTextField: TextField |
| + arrivalTextField: TextField |
| + seatsTextField: TextField |
| - listM: ObservableList<Flights> |
| - index: int        |
| - conn: Connection  |
| - rs: ResultSet     |
| - pst: PreparedStatement |
+---------------------+
| + initialize(url: URL, rb: ResourceBundle): void |
| + FlightIdHandler(event: ActionEvent): void |
| + bookFlightProcedure(userID: int, flightId: int): void |
| + searchCriteria(): void |
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
	        	
	        	String query = ("{Call BookFlight (?, ?)");
	        	try (CallableStatement statement = conn.prepareCall(query)) {
	        		statement.setInt(1, userID);
	        		statement.setInt(2, flightId);
	        		statement.execute();
	        		conn.close();
	        	} catch (Exception e){
	        		
	        	}

//	            String sql = 
//	                "DECLARE @userId INT = ?; " +
//	                "DECLARE @flightId INT = ?; " +
//	                "IF NOT EXISTS (" +
//	                    "SELECT 1 " +
//	                    "FROM Bookings " +
//	                    "WHERE ID = @userId " +
//	                    "    AND FlightID = @flightId " +
//	                ") " +
//	                "BEGIN " +
//	                    "IF NOT EXISTS (" +
//	                        "SELECT 1 " +
//	                        "FROM Bookings AS b " +
//	                        "JOIN Flights AS f ON b.FlightID = f.FlightID " +
//	                        "WHERE b.ID = @userId " +
//	                        "    AND f.DepartureDate = ?" +
//	                        "    AND f.DepartureTime BETWEEN ?" +
//	                    ") " +
//	                    "BEGIN " +
//	                        "IF ((SELECT SeatsAvailable FROM Flights WHERE FlightID = @flightId) > 0) " +
//	                        "BEGIN " +
//	                             "UPDATE Flights " +
//	                             "SET SeatsAvailable = SeatsAvailable - 1 " +
//	                             "WHERE FlightID = @flightId; " +
//	                             "INSERT INTO Bookings (ID, FlightID, BookingDate) " +
//	                             "VALUES (@userId, @flightId, GETDATE()); " +
//	                             "PRINT 'Booking successful'; " +
//	                        "END " +
//	                        "ELSE " +
//	                        "BEGIN " +
//	                             "PRINT 'Sorry, the flight is already full'; " +
//	                        "END; " +
//	                    "END " +
//	                    "ELSE " +
//	                    "BEGIN " +
//	                        "PRINT 'Conflict: You already have a booking for this date and time'; " +
//	                    "END; " +
//	                "END " +
//	                "ELSE " +
//	                "BEGIN " +
//	                    "PRINT 'You have already booked this flight'; " +
//	                "END;";
	   
//	             try (PreparedStatement statement =  conn.prepareStatement(query)) {
//	                // Set the parameters
//	                statement.setInt(1, userID);
//	                statement.setInt(2, flightId);
	   
	                // Execute the SQL statement
//	                statement.execute();
	                
					Parent root = FXMLLoader.load(getClass().getResource("FlightsScene.fxml"));
					stage = (Stage) MenuBar.getScene().getWindow();		
					stage.setTitle("Available Flights");
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
	                
	             
	   
	             // Display all bookings for testing purposes
//	            String selectQuery = "SELECT * FROM [dbo].[Bookings]";
//	             try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
//	                ResultSet resultSet = selectStatement.executeQuery();
//	                while (resultSet.next()) {
//	                    System.out.println("Booking ID: " + resultSet.getInt("BookingID"));
//	                    System.out.println("Customer ID: " + resultSet.getInt("ID"));
//	                    System.out.println("Flight ID: " + resultSet.getInt("FlightID"));
//	                    System.out.println("Booking Date: " + resultSet.getTimestamp("BookingDate"));
//	                    System.out.println("---------------");
//	                }
//	             }
//	   
	             // Close the connection
//	            conn.close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
}

	     
//	

	
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
	            from = fromTest;
	        }
	        
	        if (!departureTest.isEmpty()) {
	            query += " AND DepartureDate = ?";
	            from = fromTest;
	        }
	        
	        if (!arrivalTest.isEmpty()) {
	            query += " AND ArrivalDate = ?";
	            from = fromTest;
	        }
	        
	        if (!seatsTest.isEmpty()) {
	            query += " AND SeatsAvailable = ?";
	            from = fromTest;
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
