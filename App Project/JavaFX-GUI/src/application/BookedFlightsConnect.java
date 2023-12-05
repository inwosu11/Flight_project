package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookedFlightsConnect extends FlightsConnect {
//	// Gets Booked flights
		public static ObservableList<Flights> getBookedFlights() {
			Connection conn = SQLConnection.getConnection();
			ObservableList<Flights> list = FXCollections.observableArrayList();
            int userID = UserId;
			try {
				PreparedStatement ps = conn.prepareStatement(
						"SELECT b.[FlightID]," +
						"f.[DepartureCity]," +
						"f.[ArrivalCity]," +
						"f.[DepartureDate]," +
						"f.[ArrivalDate]," +
						"b.[BookingID]," +
						"b.[BookingDate]," +
						"f.[SeatsAvailable]," +
						"FROM [dbo].[Bookings] b " +
						"JOIN [dbo].[Flights] f ON b.[FlightID] = f.[FlightID]" +
						"WHERE b.[ID] = ?;");
				
				ps.setInt(1, UserId);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					String flightID = rs.getString("FlightID");
				    String departureCity = rs.getString("DepartureCity");
				    String arrivalCity = rs.getString("ArrivalCity");
				    String departureTime = rs.getString("DepartureDate");
				    String arrivalTime = rs.getString("ArrivalDate");
				    String seatsAvailable = rs.getString("SeatsAvailable");
				    
				    

					list.add(new Flights(flightID, departureCity, arrivalCity, departureTime, arrivalTime, seatsAvailable));
				}
				System.out.println("List populated");
			} catch (Exception e) {
				System.out.println(e);
			}
			return list; 
		}

}
