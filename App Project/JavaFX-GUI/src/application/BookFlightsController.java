package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookFlightsController extends FlightsController{
	
	@FXML private TableView<Flights> UserFlightsTable;
	@FXML private TableColumn<Flights, String> columnFlight;
	@FXML private TableColumn<Flights, String> columnFrom;
	@FXML private TableColumn<Flights, String> columnTo;
	@FXML private TableColumn<Flights, String> columnDeparture;
	@FXML private TableColumn<Flights, String> columnArrival;
	@FXML private TableColumn<Flights, String> columnSeatsAvailable; 
	
	ObservableList<Flights> listN;
	
	int index = -1;
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	@Override
	public void initialize(URL url, ResourceBundle rb){
		//Sets Column Values
		columnFlight.setCellValueFactory(new PropertyValueFactory<Flights, String>("flightID"));
		columnFrom.setCellValueFactory(new PropertyValueFactory<Flights, String>("from"));
		columnTo.setCellValueFactory(new PropertyValueFactory<Flights, String>("to"));
		columnDeparture.setCellValueFactory(new PropertyValueFactory<Flights, String>("departure"));
		columnArrival.setCellValueFactory(new PropertyValueFactory<Flights, String>("arrival"));
		columnSeatsAvailable.setCellValueFactory(new PropertyValueFactory<Flights, String>("seats"));
		
		listN = FlightsConnect.getBookedFlights();
		UserFlightsTable.setItems(listN);	
	}
	
    public void deleteFlight(ActionEvent event) throws IOException {
        // Get the selected row
        Flights selectedFlight = UserFlightsTable.getSelectionModel().getSelectedItem();

        // Check if a row is selected
        if (selectedFlight != null) {
            String flightId = selectedFlight.getFlightID();
            System.out.println("Selected Flight ID: " + flightId);

            int flightIdInt = Integer.parseInt(flightId);

            deleteFlightProcedure(UserId, flightIdInt);
            UserFlightsTable.setItems(listN);
            UserFlightsTable.refresh();
            
        } else {
            System.out.println("No row selected");
        }
    }
    
    private void deleteFlightProcedure(int userID, int flightIdInt) {
    	try (Connection conn = SQLConnection.getConnection()) {
            // Create a CallableStatement for the stored procedure
            // Prepare the SQL statement
            String sql = 
                "DECLARE @userId INT = ?;" +
                "DECLARE @FlightId INT = ?; " +
                "DELETE FROM Bookings " +
                "WHERE ID = @userId " +
                "and FlightID = @FlightId;" +
                "PRINT 'Booking deleted successfully';" +
                "DECLARE @seats_to_book INT = 1;" +
                
                "UPDATE Flights " +
                "SET SeatsAvailable = CASE " +
                	"WHEN SeatsAvailable + @seats_to_book <= 100 " +
                "THEN SeatsAvailable + @seats_to_book " +
                	"ELSE 100 " +
                	"END " +
                "WHERE FlightID = @FlightId";
                
         
                
   
             try (PreparedStatement statement =  conn.prepareStatement(sql)) {
                // Set the parameters
                statement.setInt(1, userID);
                statement.setInt(2, flightIdInt);
   
                // Execute the SQL statement
                statement.execute();
             }
   
             // Display deleted bookings for testing purposes
            String selectQuery = "SELECT * FROM [dbo].[Bookings]";
             try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
                ResultSet resultSet = selectStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println("Booking ID: " + resultSet.getInt("BookingID"));
                    System.out.println("Customer ID: " + resultSet.getInt("ID"));
                    System.out.println("Flight ID: " + resultSet.getInt("FlightID"));
                    System.out.println("Booking Date: " + resultSet.getTimestamp("BookingDate"));
                    System.out.println("---------------");
                }
             }
   
             // Close the connection
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
     }
    @Override
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
	                       "FROM [dbo].[bookings] " +
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
	        UserFlightsTable.setItems(resultList);
	    }
	}


}
