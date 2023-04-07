package application.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class MainController {

	@FXML PasswordField password;

	private String pass = password.getText();

	private String defaultPass = "p";

	@FXML TextField firstName;

	@FXML TextField lastName;

	@FXML ChoiceBox gender;

	@FXML TextField schoolName;

	@FXML ChoiceBox programRecFor;

	@FXML ChoiceBox firstSemesterTaken;

	@FXML DatePicker date;

	@FXML ChoiceBox course1;

	@FXML ChoiceBox course2;

	@FXML ChoiceBox course3;

	@FXML TextField yearTaken;

	@FXML TextField letterGrade1;

	@FXML TextField letterGrade2;

	@FXML TextField letterGrade3;

	@FXML public void login() {
		//check
		if(pass == defaultPass) {
			switchToChangePass();
		}
		else if(!verifyPassword(pass)){//still working on this
			/**Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Wrong Password");
			alert.setContentText("You have entered the wrong password or ");
			ButtonType btnOk = new ButtonType("Ok", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(btnOk);
			alert.show();
			**/
		}
		else {
			//try {
				//changeScenes("createRec.fxml");
			//} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
		}
	}

	private void switchToChangePass() {
		// TODO Auto-generated method stub
		
	}

	private boolean verifyPassword(String pass2) {
		// TODO Auto-generated method stub
		return false;
	}

	@FXML public void signUp(
			
			) {}
	
}
