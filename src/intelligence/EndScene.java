package intelligence;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/*
 * Program Name: EndScene.java
 * Name: Simon Orr
 * Date: February 2nd, 2021
 * Description: A series of Intelligence based games to hone your intelligence and problem solving skills while earning Coins!
 */

public class EndScene {
	@FXML Button endButton;
	@FXML TextArea textAreaEndScene;
	public static int coinsCollected = 0;
	public static boolean win = false; 
	public void main() {
		if(win) {
			textAreaEndScene.setText("Congratulations, you won! You collected: " + coinsCollected + " coins!");
			playAudio(); // play the coin audio when the user wins
		} else {
			textAreaEndScene.setText("Nice Try, you lost. Good Luck next time!");
		}
	}
	public void buttonHandlerEndScene(ActionEvent evt) {
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		if(buttonLabel.equals("Quit")) {
			Platform.exit();
			System.exit(0);
		} else if(buttonLabel.equals("Restart")) {
			try {
				Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow(); 
				stage.setTitle( "Spark Intelligence" );
				FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScene.fxml"));
				BorderPane root = (BorderPane)loader.load();
				Scene scene = new Scene(root,1600,900);
				// show stage / scene
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
				StartController.count = 1;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void getScene(Stage stage) {
		// TODO Auto-generated method stub
		
	}
	private void playAudio() {
		AudioClip note = new AudioClip(this.getClass().getResource("coins.wav").toString()); // initialize the audio
		note.play(); // play the audio
	}
}
