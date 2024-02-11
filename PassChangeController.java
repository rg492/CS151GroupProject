package application.controller;

import application.CommonUseObjects;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PassChangeController {

	@FXML PasswordField newPasswordReEntry;
	@FXML PasswordField newPasswordEntry;
	
	// get access to common functionality (like scene switching)
	private CommonUseObjects commonObj = CommonUseObjects.getInstance();
	@FXML Label errorLabel;

	@FXML public void changePassword() {
		
		// if passwords do not match, display error message
		if(newPasswordEntry.getText().toString().compareTo(newPasswordReEntry.getText().toString()) != 0) {
			errorMessage("Password do not match!");
		}
		// if  they enter an empty password field
		else if(newPasswordEntry.getText().length() == 0) {
			errorMessage("Password field cannot be empty!");
		}
		// if they try to use default password as new password, display error message
		else if(newPasswordEntry.getText().compareTo("p") == 0) {
			errorMessage("Default password \"p\" not allowed");
		}
		else {
			// update password in the file system
			commonObj.fs.changePassword(newPasswordEntry.getText().toString());
			// display confirmation message
			errorLabel.setText("Password changed! Navigating to Login Page.");
			errorLabel.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			errorLabel.setTextFill(Color.WHITE);
			CommonUseObjects.delay(2000, () -> commonObj.switchToScene("view/LoginView.fxml"));
		}
	}
	
	private void errorMessage(String msg) {
		errorLabel.setText(msg);
	}

	// switch back to login page
	@FXML public void cancelToLogin() {
		commonObj.switchToScene("view/LoginView.fxml");
	}

}
