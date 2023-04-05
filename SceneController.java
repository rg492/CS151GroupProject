package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML PasswordField passEnter;
	
	private String defaultPass = "p";
	
	public void login(String passEnter) {
		
		
		
		if(passEnter != defaultPass) {
			
		}
		else if(!verifyPassword(passEnter)){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Wrong Password");
			alert.setContentText("You have entered the wrong password");
			ButtonType btnOk = new ButtonType("Ok", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(btnOk);
			alert.show();
		}
		else {
			switchToCreate();
		}
	}
	
	public void signUp() {
		
	}
	
	public void createRec() {
		
	}
	
	public void deleteRec() {
		
	}
	
	public void switchToCreate() throws IOException {
		HBox root = (HBox)FXMLLoader.load(getClass().getResource("createRec.fxml"));
		//stage = (Stage)((Node)evet.get)
		//still working on switching scenes for this
	}
	
	

}
