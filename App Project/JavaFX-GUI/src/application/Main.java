package application;
	
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	
	public static void main(String[] args) throws Exception {
		System.setProperty("javafx.preloader", SplashScreen.class.getName());
		launch(args);
	}
	
	static MainController main = new MainController();;
	// gets current user
	static int UserId = main.getUserId();
		
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
			Scene loader = new Scene(root);
			primaryStage.setScene(loader);
			primaryStage.hide();
			
			PauseTransition pause = new PauseTransition(Duration.seconds(3.0));
			
			pause.setOnFinished(event -> {
				primaryStage.show();
			});
			pause.play();
			primaryStage.show();


			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}