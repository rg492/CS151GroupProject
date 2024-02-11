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

// Reset password from home page
public class HomePageResetController {

	@FXML PasswordField newPasswordReEntry;
	@FXML PasswordField newPasswordEntry;
	@FXML PasswordField OldPasswordEntry;
	@FXML Label notifLbl;
	
	// get access to common functionality (like scene switching)
	private CommonUseObjects obj = CommonUseObjects.getInstance();
	
	

	@FXML public void changePassword() {
		
		// if passwords do not match, display error message
		if(obj.fs.matchPassword(OldPasswordEntry.getText().toString()) && newPasswordEntry.getText().toString().compareTo(newPasswordReEntry.getText().toString()) != 0) {
			notifLbl.setTextFill(Color.RED);
			notifLbl.setText("One of the passwords you entered do not match.\nPlease try again.");
			
		}
		// if  they enter an empty password field
		else if(newPasswordEntry.getText().length() == 0) {
			notifLbl.setTextFill(Color.RED);
			notifLbl.setText("Password must be at least 1 character long.\nPlease try again.");
		}
		// if they try to use default password as new password, display error message
		else if(newPasswordEntry.getText().compareTo("p") == 0) {
			notifLbl.setTextFill(Color.RED);
			notifLbl.setText("Default password not allowed.\nPlease enter a different password");
		}
		else {
			// update password in the file system
			obj.fs.changePassword(newPasswordEntry.getText().toString());//come back to this and see if we can change label and wait
			// display confirmation message
			notifLbl.setTextFill(Color.GREEN);
			// display confirmation message
			setErrorLabel(Color.LIGHTGREEN, "Your password was changed successfully! Navigating back to home page...");

			// delay of 2 seconds before navigating away
			CommonUseObjects.delay(2000, () -> obj.switchToScene("view/HomePage.fxml"));
		}
	}
	

	@FXML public void cancel() {
		obj.switchToScene("view/HomePage.fxml");
	}
	
	// helper function
		private void setErrorLabel(Color backgroundColor, String message) {
			notifLbl.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
			notifLbl.setText(message);
		}

}
