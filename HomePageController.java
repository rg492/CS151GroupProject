package application.controller;

import application.CommonUseObjects;
import application.Recommendation;
import application.RecommendationCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


// Controller for the home page
public class HomePageController {
	
	private Recommendation[] recommendations;
	private CommonUseObjects commonObj = CommonUseObjects.getInstance();
	@FXML ListView<Recommendation> recentRecsList;
	@FXML TextField searchLastName;
	
	public void initialize() {
		recentRecsList.setCellFactory(new RecommendationCellFactory());
		recommendations = commonObj.fs.getAllRecommendations();
		ObservableList<Recommendation> recList = FXCollections.observableArrayList();
		// add in order (most recent to oldest)
		for(int n = recommendations.length - 1; n >= 0; n--) {
			recList.add(recommendations[n]);
		}
		recentRecsList.setItems(recList);
	}

	// when button is clicked to create new recommendation, go to CreateRec scene
	@FXML public void GoToCreateRec() {
		commonObj.switchToScene("view/CreateRec.fxml");
	}

	// when button is clicked to change password, go to ResetPassword scene
	@FXML public void GoToChangePassword() {
		commonObj.switchToScene("view/ResetPasswordHomePage.fxml");
	}
	
	// When logout button is clicked, go out to login page
	@FXML public void Logout() {
		commonObj.switchToScene("view/LoginView.fxml");
	}

	@FXML public void searchRec() {
		if(searchLastName.getText() == null || searchLastName.getText().length() == 0) {
			return;
		}
		ObservableList<Recommendation> recList = FXCollections.observableArrayList();
		for(int n = 0; n < recommendations.length; n++) {
			if(recommendations[n].getStudentLastName().compareTo(searchLastName.getText()) == 0) {
				recList.add(recommendations[n]);
			}
		}
		recentRecsList.getItems().clear();
		recentRecsList.setItems(recList);
	}

	@FXML public void editFacultyData() {
		commonObj.switchToScene("view/EditData.fxml");
	}

	// clear the search (display all recommendations once again)
	@FXML public void clearSearch() {
		ObservableList<Recommendation> recList = FXCollections.observableArrayList();
		for(int n = recommendations.length - 1; n >= 0; n--) {
			recList.add(recommendations[n]);
		}
		recentRecsList.setItems(recList);
		// clear search parameter
		searchLastName.clear();
	}
}
