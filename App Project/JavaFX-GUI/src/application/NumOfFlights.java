package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NumOfFlights {
	public static int numOfFlights() {
		Connection conn = SQLConnection.getConnection();
		int num = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM [dbo].[Flights]");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				num++;
				
			}
			System.out.println(num);
		} catch (Exception e) {
			System.out.println(e);
		}
		return num;
	}

}
