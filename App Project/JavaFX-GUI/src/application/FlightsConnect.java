package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class FlightsConnect {
	Connection conn = null;
	
	// Gets all flights
	public static ObservableList<Flights> getDataFlights() {
		Connection conn = SQLConnection.getConnection();
		ObservableList<Flights> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM [dbo].[Flights]");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String flightID = rs.getString("FlightID");
			    String departureCity = rs.getString("DepartureCity");
			    String arrivalCity = rs.getString("ArrivalCity");
			    String departureTime = rs.getString("DepartureTime");
			    String arrivalTime = rs.getString("ArrivalTime");
			    String seatsAvailable = rs.getString("SeatsAvailable");
			    Button button = new Button("button");
			    int buttonValue = 1;
			    
			    

				list.add(new Flights(flightID, departureCity, arrivalCity, departureTime, arrivalTime, seatsAvailable));
			}
			System.out.println("List populated");
		} catch (Exception e) {
			System.out.println(e);
		}
		return list; 
	}
}
