package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.CommonUseObjects;
import application.Faculty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;


public class EditDataController {
	
	//fxml references to views
	@FXML TextField facultyFirstName;
	@FXML TextField facultyLastName;
	@FXML TextField title;
	@FXML TextField schoolName;
	@FXML TextField departmentName;
	@FXML TextField email;
	@FXML TextField phoneNumber;
	
	@FXML TextField enteredCourse;
	@FXML TextField enteredSemester;
	@FXML TextField enteredProgram;
	@FXML TextField enteredPChar;
	@FXML TextField enteredAChar;
	
	@FXML ListView<String> coursesListView;
	@FXML ListView<String> pCharListView;
	@FXML ListView<String> semestersListView;
	@FXML ListView<String> aCharListView;
	@FXML ListView<String> programsListView;
	
	private Faculty f = new Faculty();
	
	//common objects 
	private CommonUseObjects commonObj = CommonUseObjects.getInstance();
	
	
	//might have to add a copy check method here
	@FXML public void Cancel() {
		commonObj.switchToScene("view/HomePage.fxml");
	}

	@FXML public void saveData() {
		// add faculty data to a Faculty object
		f.setLastName(facultyLastName.getText());
		f.setFirstName(facultyFirstName.getText());
		f.setTitle(schoolName.getText());
		f.setSchoolName(schoolName.getText());
		f.setDepartmentName(departmentName.getText());
		f.setEmail(email.getText());
		f.setPhoneNumber(phoneNumber.getText());
		f.setSemesters(semestersListView.getItems().toArray(new String[0]));
		f.setCoursesTaught(coursesListView.getItems().toArray(new String[0]));
		f.setProgramNames(programsListView.getItems().toArray(new String[0]));
		f.setPersonalCharacteristics(pCharListView.getItems().toArray(new String[0]));
		f.setAcademicCharacteristics(aCharListView.getItems().toArray(new String[0]));
		
		// save faculty to the file system
		commonObj.fs.saveFaculty(f);

		commonObj.switchToScene("view/HomePage.fxml");
	}

	public void initialize() {
		f = commonObj.fs.getFaculty();
		facultyFirstName.setText(f.getFirstName());
		facultyLastName.setText(f.getLastName());
		title.setText(f.getTitle());
		schoolName.setText(f.getSchoolName());
		departmentName.setText(f.getDepartmentName());
		email.setText(f.getEmail());
		phoneNumber.setText(f.getPhoneNumber());
		
		coursesListView.setItems(FXCollections.observableArrayList(f.getCoursesTaught()));
		semestersListView.setItems(FXCollections.observableArrayList(f.getSemesters()));
		programsListView.setItems(FXCollections.observableArrayList(f.getProgramNames()));
		pCharListView.setItems(FXCollections.observableArrayList(f.getPersonalCharacteristics()));
		aCharListView.setItems(FXCollections.observableArrayList(f.getAcademicCharacteristics()));
	}

	// add enteredCourse to coursesListView
	@FXML public void addCourse() {
		// if empty field, don't add it
		if(enteredCourse == null || enteredCourse.getText().length() == 0) {
			return;
		}
		coursesListView.getItems().add(enteredCourse.getText());
		// scroll to the added item
		coursesListView.scrollTo(enteredCourse.getText());
		// clear text field
		enteredCourse.clear();
	}

	// add enteredPChar to pCharListView
	@FXML public void addPersonalChar() {
		// if empty field, don't add it
		if(enteredPChar == null || enteredPChar.getText().length() == 0) {
			return;
		}
		pCharListView.getItems().add(enteredPChar.getText());
		// scroll to the added item
		pCharListView.scrollTo(enteredPChar.getText());
		// clear text field
		enteredPChar.clear();
	}

	// add enteredAChar to aCharListView
	@FXML public void addAcademicChar() {
		// if empty field, don't add it
		if(enteredAChar == null || enteredAChar.getText().length() == 0) {
			return;
		}
		aCharListView.getItems().add(enteredAChar.getText());
		// scroll to the added item
		aCharListView.scrollTo(enteredAChar.getText());
		// clear text field
		enteredAChar.clear();
	}

	// add enteredProgram to programListView
	@FXML public void addProgram() {
		// if empty field, don't add it
		if(enteredProgram == null || enteredProgram.getText().length() == 0) {
			return;
		}
		programsListView.getItems().add(enteredProgram.getText());
		// scroll to the added item
		programsListView.scrollTo(enteredProgram.getText());
		// clear text field
		enteredProgram.clear();
	}
	
	// add enteredSemester to semestersListView
	@FXML public void addSemester() {
		// if empty field, don't add it
		if(enteredSemester == null || enteredSemester.getText().length() == 0) {
			return;
		}
		semestersListView.getItems().add(enteredSemester.getText());
		// scroll to the added item
		semestersListView.scrollTo(enteredSemester.getText());
		// clear text field
		enteredSemester.clear();
	}

	// remove selected course
	@FXML public void removeCourse() {
		coursesListView.getItems().remove(coursesListView.getSelectionModel().getSelectedItem());
		coursesListView.getSelectionModel().clearSelection();
	}

	// remove selected personal char
	@FXML public void removePChar() {
		pCharListView.getItems().remove(pCharListView.getSelectionModel().getSelectedItem());
		pCharListView.getSelectionModel().clearSelection();
	}

	// remove selected semester
	@FXML public void removeSemester() {
		semestersListView.getItems().remove(semestersListView.getSelectionModel().getSelectedItem());
		semestersListView.getSelectionModel().clearSelection();
	}

	// remove selected academic char
	@FXML public void removeAChar() {
		aCharListView.getItems().remove(aCharListView.getSelectionModel().getSelectedItem());
		aCharListView.getSelectionModel().clearSelection();
	}

	// remove selected program
	@FXML public void removeProgram() {
		programsListView.getItems().remove(programsListView.getSelectionModel().getSelectedItem());
		programsListView.getSelectionModel().clearSelection();
	}
}
