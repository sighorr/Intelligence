package intelligence;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Program Name: ChestController.java
 * Name: Simon Orr
 * Date: February 2nd, 2021
 * Description: A series of Intelligence based games to hone your intelligence and problem solving skills while earning Coins!
 */

public class ChestController {
	
	@FXML Canvas chestCanvas;
	@FXML Button chestOne;
	@FXML Button chestTwo;
	@FXML Button chestThree;
	@FXML Text statementOne;
	@FXML Text statementTwo;
	@FXML Text statementThree;
	@FXML Text textRule;


	GraphicsContext gc;
	
	public void gameLoop(Stage stage){
//		Image background = new Image("images/background.png");
//		gc = chestCanvas.getGraphicsContext2D();
		chestOne.getStyleClass().remove("button");
		chestTwo.getStyleClass().remove("button");
		chestThree.getStyleClass().remove("button");
		chestOne.getStyleClass().add("chest-button");
		chestTwo.getStyleClass().add("chest-button");
		chestThree.getStyleClass().add("chest-button");

	    //Creating a graphic (image)
		
	    Image img = new Image("images/chest.png");
	    ImageView view = new ImageView(img);
	    ImageView view2 = new ImageView(img);
	    ImageView view3 = new ImageView(img);
	    // set height and preserveratio for all of the chests
	    view.setFitHeight(80);
	    view.setPreserveRatio(true);
	    view2.setFitHeight(80);
	    view2.setPreserveRatio(true);
	    view3.setFitHeight(80);
	    view3.setPreserveRatio(true);
	    // setting the location of the chest
	    chestOne.setTranslateX(200);
	    chestOne.setTranslateY(25);
	    //setting the size of the chest
	    chestOne.setPrefSize(80, 80);
	    // setting the location of the chest
	    chestTwo.setTranslateX(300);
	    chestTwo.setTranslateY(25);
	    // setting the size of the chest
	    chestTwo.setPrefSize(80, 80);
	    // setting the location of the chest
	    chestThree.setTranslateX(400);
	    chestThree.setTranslateY(25);
	    // setting the size of the chest
	    chestThree.setPrefSize(80, 80);
	    
	    // setting a graphic to the button (the chest)
	    chestOne.setGraphic(view);
	    chestTwo.setGraphic(view2);
	    chestThree.setGraphic(view3);

	    //Setting the stage
	    Group root = new Group(chestOne, chestTwo, chestThree, statementOne, statementTwo, statementThree, textRule); // bring in all of the objects
	    Scene scene = new Scene(root, 1600, 900); // make a new scene with all of the objects / elements
	    stage.setTitle("Button Graphics");
	    stage.setScene(scene);
	    stage.show();

	}
	public void buttonClickHandler(ActionEvent evt) throws IOException {
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		try {
			if(clickedButton == chestOne) { // if the right chest was clicked
				EndScene.win = true;
				EndScene.coinsCollected += 20;
				changeEndScene(evt);
			} else if(clickedButton == chestTwo){
				EndScene.win = false;
				changeEndScene(evt);
			} else if(clickedButton == chestThree) {
				EndScene.win = false;
				changeEndScene(evt);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
    
	public void getScene(Stage stage) {
		// TODO Auto-generated method stub
		
	}
	
	private void quit() {
		// close the program
		Platform.exit();
		System.exit(0);
	}
}
