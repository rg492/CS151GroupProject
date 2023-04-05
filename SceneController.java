package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToCreate() throws IOException {
		HBox root = (HBox)FXMLLoader.load(getClass().getResource("createRec.fxml"));
	}
	
	

}
