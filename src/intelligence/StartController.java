package intelligence;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;  

import javafx.application.Application;  
import javafx.scene.Group;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView;  
/*
 * Program Name: StartController.java
 * Name: Simon Orr
 * Date: February 2nd, 2021
 * Description: A series of Intelligence based games to hone your intelligence and problem solving skills while earning Coins!
 */

public class StartController {
	// change the scene to the game
	public static int count = 0;
	public Stage secondaryStage;

	public void changeSceneButtonHandler(ActionEvent evt) throws IOException { 
		// play the audio if it hasn't been played before
        if(count == 0) {
        	playAudio(); 
            count++;
        }
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		if(buttonLabel.equals("Coin Collection")) {
			try {
				// initialize the stage
				Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow(); 
				stage.setTitle("Coin Collection"); // set the title of the program to monkey game
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Intelligence.fxml")); // change the fxml to monkey game 
				BorderPane root = (BorderPane) loader.load(); // load the border pane
				Scene sceneTwo = new Scene(root, 1600, 900); // start a new scene
				sceneTwo.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // load the CSS
				IntelligenceController controller = loader.getController(); // load the controller
				// show the scene / stage
				stage.setScene(sceneTwo);
				controller.getScene(stage);
				controller.gameLoop();
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (buttonLabel.equals("Chest Search")){
			try {
				// initialize the stage
				Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow(); 
				stage.setTitle("Chest Search"); // set the title of the program to monkey game
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Chest.fxml")); // change the fxml to monkey game 
				BorderPane root = (BorderPane) loader.load(); // load the border pane
				Scene sceneTwo = new Scene(root, 1600, 900); // start a new scene
				sceneTwo.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // load the CSS
				ChestController controller = loader.getController(); // load the controller
				// show the scene
				stage.setScene(sceneTwo);
				controller.getScene(stage);
				controller.gameLoop(stage);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(buttonLabel.equals("Coin Distribution")) {
			try {
				// initialize the stage
				Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow(); 
				stage.setTitle("Coin Distribution"); // set the title of the program to monkey game
				FXMLLoader loader = new FXMLLoader(getClass().getResource("CoinDistribution.fxml")); // change the fxml to monkey game 
				BorderPane root = (BorderPane) loader.load(); // load the border pane
				Scene sceneTwo = new Scene(root, 1600, 900); // start a new scene
				sceneTwo.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // load the CSS
				CoinDistributionController controller = loader.getController(); // load the controller
				// show the scene / stage
				stage.setScene(sceneTwo);
				controller.getScene(stage);
				controller.gameLoop(stage);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public void menuClickHandler(ActionEvent evt) {
		MenuItem clickedMenu = (MenuItem) evt.getTarget();
		String menuLabel = clickedMenu.getText();
		
		if ("How To Play".equals(menuLabel)) {
			openHowToWindow();
		} else if("Quit".equals(menuLabel)) {
			// close the program
			Platform.exit();
			System.exit(0);
		} 
			
	}
	public void openHowToWindow() {
		try {
			// load the pop up you created
			Pane howTo = (Pane)FXMLLoader.load(getClass().getResource("/intelligence/PopUpPanel.fxml"));
			// create a new scene
			Scene howToScene = new Scene(howTo,500,500);
			// add css to the new scene		
			howToScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//create new stage to put scene in
			secondaryStage = new Stage(); // make a new stage
			secondaryStage.setScene(howToScene); // set the scene to the scene made earlier
			secondaryStage.setResizable(false); // make the window non resizable 
			secondaryStage.showAndWait();
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
	public void closeCurrentWindow(final ActionEvent evt) {
	    final Node source = (Node) evt.getSource(); // initialize a node to close the window
	    final Stage stage = (Stage) source.getScene().getWindow(); // store the stage that is being used
	    stage.close(); // close the stored stage
	}
	private void playAudio() {
		AudioClip note = new AudioClip(this.getClass().getResource("theme.mp3").toString()); // load / initialize the audio clip
		
		note.play(); // play the audio clip 
	}

}
