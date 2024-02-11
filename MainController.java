package application.controller;

import application.CommonUseObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


/**
 * @author raulg
 *
 */
public class MainController {

	@FXML PasswordField password;

	public String defaultP = "p";
		
	private CommonUseObjects obj = CommonUseObjects.getInstance();


	@FXML Label errorLabel;

		
		/*
		 * fxml method that handles  when the login button is pressed and checks 
		 */
	@FXML public void login() {
		
		String pass = password.getText().toString();
		
		if(pass == null) {
			errorLabel.setText("Password field cannot be empty!");
		}
		//check to see if first login
		else if (pass.compareTo(defaultP) == 0 && obj.fs.matchPassword(pass)) {
			obj.switchToScene("view/ResetPasswordLogin.fxml");
		}
		
		//if pass matches we redirect to homepage
		else if(verifyPassword(pass)){
			obj.switchToScene("view/HomePage.fxml");//changes scene to create recommendation screen
		}
		//password doesnt exist so show alert
		else {
			errorLabel.setText("Incorrect Password!");
		}
		
	}
	
	/**
	 * Method that verifies password so its not null it matches an entry and isnt the defaultP
	 * @param pass2
	 * @return boolean
	 */
	private boolean verifyPassword(String pass2) {
		if (obj.fs.matchPassword(pass2) && pass2.compareTo(defaultP) != 0) {
			return true;
		}
		else
		return false;
	}



	
	public void initialize() {
		
		
	}

	
	
	
}
