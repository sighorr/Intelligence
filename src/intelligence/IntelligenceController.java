package intelligence;

import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

/*
 * Program Name: IntelligenceController.java
 * Name: Simon Orr
 * Date: February 2nd, 2021
 * Description: A series of Intelligence based games to hone your intelligence and problem solving skills while earning Coins!
 */

public class IntelligenceController {
	Stage secondaryStage;
    Scene gameScene;
    public Button[] buttonArr = new Button[9];
    public int buttonClicks = 0, round = 1, lives = 3;
    public int[] coinCollection = new int[round];
    public int[] coinCollectionTemp = coinCollection;
    public Button buttonStored;
    public int clicks;
    public boolean clicked = false;
    @FXML Button b1;
    @FXML Button b2;
    @FXML Button b3;
    @FXML Button b4;
    @FXML Button b5;
    @FXML Button b6;
    @FXML Button b7;
    @FXML Button b8;
    @FXML Button b9;
    @FXML Button scoreButton;
    @FXML GridPane intelligenceBoard;
    @FXML Text roundText;
	public void gameLoop() {
		// put all of the buttons from the board in an array
		buttonArr[0] = b1;
		buttonArr[1] = b2;
		buttonArr[2] = b3;
		buttonArr[3] = b4;
		buttonArr[4] = b5;
		buttonArr[5] = b6;
		buttonArr[6] = b7;
		buttonArr[7] = b8;
		buttonArr[8] = b9;
		for(int f = 0; f < round; f++) {
		    coinCollection[f] = (int)(Math.random()*8); // set the box to a random value on the screen
	    	buttonArr[coinCollection[f]].getStyleClass().add("highlight-button");
	    	buttonArr[coinCollection[f]].setText("" + f); // set the order in which the boxes should be pressed
	    	if(clicked) {
		    	buttonArr[coinCollection[f]].getStyleClass().add("button");
				ObservableList<javafx.scene.Node> buttons = intelligenceBoard.getChildren(); // put all of the buttons in a list
				buttons.forEach(btn -> { // go through the list
					// reset the styles of the buttons
					btn.getStyleClass().remove("highlight-button");
					btn.getStyleClass().add("button");
				});
				clicked = false;
	    	}
		}

		if(buttonClicks == round) { // if the amount of boxes clicked is the amount of rounds passed 
		    for(int i = 0; i < round; i++) {
		    	coinCollection[i] = coinCollectionTemp[i]; // bring the entire coinCollection array back into itself
		    }
			round++; // go up a round
			buttonClicks = 0; // set the button clicks to 0 
		    coinCollection = new int[round]; // refresh the array
	    	coinCollectionTemp = coinCollection; // refresh the temp array
		}

	}
    public void buttonClickHandler(ActionEvent evt) throws IOException {
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		playAudio(); // play the audio as a button is clicked
		buttonClicks++;
    	clicked = true;
		buttonStored = clickedButton; // store the clicked button
		if(buttonArr[coinCollection[round-1]] == clickedButton) { // check if the button clicked is the right button
			buttonArr[coinCollection[round-1]].getStyleClass().add("button"); // add the button style (refresh the style)
		} else if (buttonArr[coinCollection[round-1]] != clickedButton){ 
			lives -= 1;
		}
		if(lives == 0) {
			EndScene.coinsCollected += round; // add to the amount of coins collected
			EndScene.win = true; // set the end screen for winning the game
			changeEndScene(evt);
		}
		roundText.setText("Level: " + round + "Lives: " + lives); // display the round and lives at the top of the screen
		gameLoop();
	}
	    
    public void menuClickHandler(ActionEvent evt) {
		MenuItem clickedMenu = (MenuItem) evt.getTarget();
		String menuLabel = clickedMenu.getText();
		
		if ("How To Play".equals(menuLabel)) {
			openHowToWindow();
		} else if("Quit".equals(menuLabel)) {
			quit();
		} 
			
	}
	public void openHowToWindow() {
		try {
			// load the pop up you created
			Pane howTo = (Pane)FXMLLoader.load(getClass().getResource("/intelligence/PopUpPanel.fxml"));
			// create a new scene
			Scene howToScene = new Scene(howTo,250,250);
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
	

    public void changeEndScene(ActionEvent evt) throws IOException {
		try {
			Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow(); 
			stage.setTitle("Coin Collection"); // set the title of the program to monkey game
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScene.fxml")); // change the fxml to monkey game 
			BorderPane root = (BorderPane) loader.load(); // load the border pane
			Scene sceneTwo = new Scene(root, 1600, 900); // start a new scene
			sceneTwo.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); // load the CSS
			EndScene controller = loader.getController(); // load the controller
			stage.setScene(sceneTwo);
			controller.getScene(stage);
			controller.main();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			quit();
		}
    }
	public void closeCurrentWindow(final ActionEvent evt) {
	    final Node source = (Node) evt.getSource(); // initialize a node to close the window
	    final Stage stage = (Stage) source.getScene().getWindow(); // store the stage that is being used
	    stage.close(); // close the stored stage
	}
	public void getScene(Stage stage) {
		// TODO Auto-generated method stub
		
	}
	public void quit() {
		// close the program
		Platform.exit();
		System.exit(0);
	}
	public void music() {
		
	}
	private void playAudio() {
		AudioClip note = new AudioClip(this.getClass().getResource("click.wav").toString());
		
		note.play();
	}
}
