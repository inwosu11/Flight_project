package application;
	
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;




public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		getConnection();
		System.setProperty("javafx.preloader", SplashScreen.class.getName());
		launch(args);
	}

	//Connects to Database
	public static Connection getConnection() throws Exception{

		try {

			String userName = "database@happiness";
			String userPassword = "Carrot22";
			String cnnString = "jdbc:sqlserver://happiness.database.windows.net:1433;"
			+ "database=projectDB;" + "user=" + userName + ";" 
			+ "password= " + userPassword + ";" 
			+ "encrypt=true;" 
			+ "trustServerCertificate=false;"
			+ "hostNameInCertifate=*.database.windows.net;"
			+ "loginTimeout=30;";
			Connection conn = DriverManager.getConnection (cnnString,userName, userPassword);
			System.out. println ("Connected");
			return conn;

			} catch (Exception e){System.out.println(e);}

		return null;
		}
		
}