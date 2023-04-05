package application;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class Login{
	
	@FXML PasswordField passEnter;
	
	private String defaultPass = "p";
	
	public void login(String passEnter) {
		
		
		
		if(passEnter != defaultPass) {
			
		}
		else if(!verifyPassword(passEnter)){
			
		}
		else {
			switchToCreate();
		}
	}
	
	//part of DBconect class
	private boolean verifyPassword(String pass) {
		//veryify if true
		return true;
	}

}
