package application;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.util.Date;


public class Flights {
	
	private String flightID;
	private String from;
	private String to;
	private String departure;
	private String arrival;
	private String seats;
	private int buttonValue;
//	private Button button;
	
	public Flights(String flightID, String from, String to, String departure, String arrival, String seats) {
		this.flightID = flightID;
		this.from = from;
		this.to = to;
		this.departure = departure;
		this.arrival = arrival;
		this.seats = seats;
		this.buttonValue = buttonValue;
//		this.button = new Button("Add Flight");
		
	}

	public int getButtonValue() {
		return buttonValue;
	}

	public void setButtonValue(int buttonValue) {
		this.buttonValue = buttonValue;
	}

//	public Button getButton() {
//		return button;
//	}
//
//	public void setButton(Button button) {
//		this.button = button;
//	}

	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

}
	