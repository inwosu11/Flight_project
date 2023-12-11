package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConflictingFlightPopup {
	public static void display(String title) {
		Stage window = new Stage();
		// requires current window to be dealt with
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
			
		Label label = new Label();
		label.setText("This flight conflicts with another flight you've booked.");
		Button close = new Button("Close the window");
		close.setOnAction(e -> window.close());
			
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, close);
		layout.setAlignment(Pos.CENTER);
			
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
			
		}

}
