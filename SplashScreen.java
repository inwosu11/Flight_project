package application;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreen extends Preloader {

	private final StackPane parent = new StackPane();
	
	private Stage preloaderStage;
	
	@Override
	public void init() throws Exception {
		
		Image image = new Image("SplashScreen.png");
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(500);
		this.parent.getChildren().add(imageView);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.preloaderStage = stage;
		Scene scene = new Scene(parent, 640, 400);
		scene.setFill(Color.TRANSPARENT);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.centerOnScreen();
		stage.show();
		
	}
	
	@Override
	public void handleStateChangeNotification(StateChangeNotification info) {
		if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
			this.preloaderStage.close();
		}
		
	}
	
}
