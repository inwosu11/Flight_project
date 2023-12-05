+-----------------------+
|    SplashScreen       |
+-----------------------+
| - parent: StackPane   |
| - splash: Stage       |
| - scene: Scene        |
+-----------------------+
| + init(): void        |
| + start(stage: Stage): void |
+-----------------------+
package application;


import javafx.animation.PauseTransition;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashScreen extends Preloader {

	private final StackPane parent = new StackPane();
	
	private Stage splash;
	private Scene scene;
	
	@Override
	public void init() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("SplashScene.fxml"));
		scene = new Scene(root);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.splash = stage;
		
		splash.setScene(scene);
		splash.initStyle(StageStyle.UNDECORATED);
		splash.show();
		PauseTransition pause = new PauseTransition(Duration.seconds(3.0));
		
		pause.setOnFinished(event -> {
			System.out.println("BEFORE_START");
			splash.hide();
		});
		pause.play();}
		
		
		
}
	

	

