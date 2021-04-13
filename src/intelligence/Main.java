package intelligence;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;

/*
 * Program Name: Main.java
 * Name: Simon Orr
 * Date: February 2nd, 2021
 * Description: A series of Intelligence based games to hone your intelligence and problem solving skills while earning Coins!
 */


public class Main extends Application {
	@Override
	
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle( "Spark Intelligence" );
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,1600,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
