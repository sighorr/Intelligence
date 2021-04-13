package intelligence;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Program Name: CoinDistributionController.java
 * Name: Simon Orr
 * Date: February 2nd, 2021
 * Description: A series of Intelligence based games to hone your intelligence and problem solving skills while earning Coins!
 */

public class CoinDistributionController {
	@FXML Button chestButton;
	@FXML Button b0;
	@FXML Button b1;
	@FXML Button b2;
	@FXML Button b3;
	@FXML Button submitButton;
	@FXML GridPane submitGrid;
	@FXML Text zeroText;
	@FXML Text oneText;
	@FXML Text twoText;
	@FXML Text threeText;

	public int buttonSelected;
	public int coinsAmount = 0;
	
	public void getScene(Stage stage) {
		// TODO Auto-generated method stub
		
	}

	public void gameLoop(Stage stage) {
		// TODO Auto-generated method stub
		
	}
	public void buttonClickHandler(ActionEvent evt) throws IOException {
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		if(b0 == clickedButton) {
			ObservableList<javafx.scene.Node> buttons = submitGrid.getChildren(); // put all of the buttons in a list
			buttons.forEach(btn -> { // go through the list
				// reset the styles of the buttons
				btn.getStyleClass().remove("selected-button");
				btn.getStyleClass().add("button");
			});
			b0.getStyleClass().add("selected-button");
			buttonSelected = 0;
			System.out.println(buttonSelected);
		} else if (b1 == clickedButton){
			ObservableList<javafx.scene.Node> buttons = submitGrid.getChildren(); // put all of the buttons in a list
			buttons.forEach(btn -> { // go through the list
				// reset the styles of the buttons
				btn.getStyleClass().remove("selected-button");
				btn.getStyleClass().add("button");
			});
			b1.getStyleClass().add("selected-button");
			buttonSelected = 1;
		} else if (b2 == clickedButton){
			ObservableList<javafx.scene.Node> buttons = submitGrid.getChildren(); // put all of the buttons in a list
			buttons.forEach(btn -> { // go through the list
				// reset the styles of the buttons
				btn.getStyleClass().remove("selected-button");
				btn.getStyleClass().add("button");
			});
			b2.getStyleClass().add("selected-button");
			buttonSelected = 2;
		} else if (b3 == clickedButton){
			ObservableList<javafx.scene.Node> buttons = submitGrid.getChildren(); // put all of the buttons in a list
			buttons.forEach(btn -> { // go through the list
				// reset the styles of the buttons
				btn.getStyleClass().remove("selected-button");
				btn.getStyleClass().add("button");
			});
			b3.getStyleClass().add("selected-button");
			buttonSelected = 3;
		}
		if(clickedButton == submitButton) {
			if(buttonSelected == coinsAmount) { // check if user got the right answer
				EndScene.win = true;
				EndScene.coinsCollected += 20;
				changeEndScene(evt);
			} else {
				EndScene.win = false;
				changeEndScene(evt); 
			}
		}
		
	}
    public void changeEndScene(ActionEvent evt) throws IOException {
		try {
			Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow(); 
			stage.setTitle("Memory Game"); // set the title of the program to monkey game
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

	private void quit() {
		// close the program
		Platform.exit();
		System.exit(0);
	}
}
