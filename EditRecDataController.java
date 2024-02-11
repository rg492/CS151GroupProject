package application.controller;

import application.CheckboxCellFactory;
import application.CommonUseObjects;
import application.Course;
import application.CourseCellFactory;
import application.Faculty;
import application.Recommendation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


// Controller for Recommendation view
public class EditRecDataController {
	
	// text field for first name of student
	@FXML TextField stuFirstName;

	// text field for last name of student
	@FXML TextField stuLastName;
	
	// text box for school name
	@FXML TextField schoolName;

	// drop down for gender selection
	@FXML ComboBox<String> gender;

	// drop down for program selection
	@FXML ComboBox<String> programRecFor;

	// date picker for choosing date of creation for recommendation
	@FXML DatePicker date;

	// drop down for semester (in which first course was taken by student) selection
	@FXML ComboBox<String> firstSemesterTaken;
	
	// text field for the year in which first course was take by student with this faculty
	@FXML TextField firstCourseYearTaken;
	
	// text field for the grade of the first course ever taken with faculty
	@FXML TextField firstCourseGrade;

	// drop down for selection of first course taken with faculty
	@FXML ComboBox<String> firstCourseTaken;
	
	@FXML ListView<Course> otherCoursesDisplayList;
	
	// list view for displaying list of personal characteristic selected chosen by faculty for this recommendation
	@FXML ListView<String> pCharDisplayList;
	
	// list view for displaying list of academic characteristic selected chosen by faculty for this recommendation
	@FXML ListView<String> aCharDisplayList;
	
	
	// Singleton (for shared functionality across controllers)
	private CommonUseObjects commonObj = CommonUseObjects.getInstance();
	
	private Recommendation recommendation;
	private Faculty f;
	
	// for customized list views
	CourseCellFactory courseCellFactory = new CourseCellFactory();
	CheckboxCellFactory pCharCellFactory = new CheckboxCellFactory();
	CheckboxCellFactory aCharCellFactory = new CheckboxCellFactory();
	
	// Bring data (from files) and into the view
	public void initialize() {
		// fetch faculty data
		f = commonObj.fs.getFaculty();
		// fetch recommendation from the file system
		recommendation = commonObj.fs.getRecommendationById(commonObj.getSelectedRecommendationId());
		
		// set first and last name
		stuFirstName.setText(recommendation.getStudentFirstName());
		stuLastName.setText(recommendation.getStudentLastName());
		
		// set the date
		date.setValue(recommendation.getDate());
		
		// set school name
		schoolName.setText(recommendation.getSchoolName());
		
		// fill drop down
		gender.setItems(FXCollections.observableArrayList("Male","Female"));
		// select the appropriate gender
		gender.getSelectionModel().select(recommendation.getStudentGender());
		
		// fill programs drop down with faculty data
		programRecFor.setItems(FXCollections.observableArrayList(f.getProgramNames()));
		// select program in the recommendation
		programRecFor.getSelectionModel().select(recommendation.getProgramApplied());
		
		firstSemesterTaken.setItems(FXCollections.observableArrayList(f.getSemesters()));
		firstSemesterTaken.getSelectionModel().select(recommendation.getFirstCourseSemester());
		
		firstCourseTaken.setItems(FXCollections.observableArrayList(f.getCoursesTaught()));
		firstCourseTaken.getSelectionModel().select(recommendation.getFirstCourse().getCourseName());
		
		firstCourseYearTaken.setText(recommendation.getFirstCourseYear());
		
		// set first course grade
		firstCourseGrade.setText(recommendation.getFirstCourse().getCourseGrade());
		
		// mark check boxes of the courses that are in the recommendation...
		for(int n = 0; n < recommendation.getCourses().length; n++) {
			courseCellFactory.addSelectedCourse(recommendation.getCourses()[n]);
		}
		// setCellFactory to customize list view
		otherCoursesDisplayList.setCellFactory(courseCellFactory);
		ObservableList<Course> otherCoursesList = FXCollections.observableArrayList();
		for(int n = 0; n < f.getCoursesTaught().length; n++) {
			otherCoursesList.add(new Course(f.getCoursesTaught()[n], ""));
		}
		otherCoursesDisplayList.setItems(otherCoursesList);
		
		// mark check boxes of characteristics that are in the recommendation
		for(int n = 0; n < recommendation.getStudentPersonalCharacteristics().length; n++) {
			pCharCellFactory.addSelectedItem(recommendation.getStudentPersonalCharacteristics()[n]);
		}
		// display all characteristics
		pCharDisplayList.setCellFactory(pCharCellFactory);
		pCharDisplayList.setItems(FXCollections.observableArrayList(f.getPersonalCharacteristics()));
		
		// mark check boxes of characteristics that are in the recommendation
		for(int n = 0; n < recommendation.getStudentAcademicCharacteristics().length; n++) {
			aCharCellFactory.addSelectedItem(recommendation.getStudentAcademicCharacteristics()[n]);
		}
		aCharDisplayList.setCellFactory(aCharCellFactory);
		aCharDisplayList.setItems(FXCollections.observableArrayList(f.getAcademicCharacteristics()));
	}

	// method to compile the recommendation (NOT IMPLEMENTED YET)
	@FXML public void saveAndCompile() {
		
		// create new Recommendation with the information entered
		recommendation.setStudentLastName(stuLastName.getText());
		recommendation.setStudentFirstName(stuFirstName.getText());
		recommendation.setStudentGender(gender.getSelectionModel().getSelectedItem());
		recommendation.setSchoolName(schoolName.getText());
		recommendation.setDate(date.getValue());
		recommendation.setProgramApplied(programRecFor.getSelectionModel().getSelectedItem());
		recommendation.setFirstCourse(new Course(firstCourseTaken.getSelectionModel().getSelectedItem(), firstCourseGrade.getText()));
		recommendation.setFirstCourseSemester(firstSemesterTaken.getSelectionModel().getSelectedItem());
		
		// get list of selected courses
		recommendation.setCourses(courseCellFactory.getSelectedCourses());
		recommendation.setStudentPersonalCharacteristics(pCharCellFactory.getSelectedItems());
		recommendation.setStudentAcademicCharacteristics(aCharCellFactory.getSelectedItems());
		
		// add new recommendation to the file system
		commonObj.fs.updateRecommendation(recommendation);
		
		// set selected recommendation id (so Compile Rec knows which rec to display)
		commonObj.setSelectedRecommendationId(recommendation.getId());
		
		// switch scene
		commonObj.switchToScene("view/CompileRec.fxml");
	}
	
	@FXML public void saveRec() {
		// create new Recommendation with the information entered
		recommendation.setStudentLastName(stuLastName.getText());
		recommendation.setStudentFirstName(stuFirstName.getText());
		recommendation.setStudentGender(gender.getSelectionModel().getSelectedItem());
		recommendation.setSchoolName(schoolName.getText());
		recommendation.setDate(date.getValue());
		recommendation.setProgramApplied(programRecFor.getSelectionModel().getSelectedItem());
		recommendation.setFirstCourse(new Course(firstCourseTaken.getSelectionModel().getSelectedItem(), firstCourseGrade.getText()));
		recommendation.setFirstCourseSemester(firstSemesterTaken.getSelectionModel().getSelectedItem());
		recommendation.setFirstCourseYear(firstCourseYearTaken.getText());
		
		// get list of selected courses
		recommendation.setCourses(courseCellFactory.getSelectedCourses());
		recommendation.setStudentPersonalCharacteristics(pCharCellFactory.getSelectedItems());
		recommendation.setStudentAcademicCharacteristics(aCharCellFactory.getSelectedItems());
		
		// add new recommendation to the file system
		commonObj.fs.updateRecommendation(recommendation);
		
		commonObj.switchToScene("view/HomePage.fxml");
	}

	// cancel creation of new recommendation (return back to home page)
	@FXML public void cancel() {
		commonObj.switchToScene("view/HomePage.fxml");
	}
	
	@FXML public void delete() {
		commonObj.fs.deleteRecommendation(recommendation.getId());
		commonObj.switchToScene("view/HomePage.fxml");
	}

	// to prevent some "null" error when mouse is dragged over the list views
	@FXML public void otherCoursesMouseDragged() {}
	@FXML public void aCharMouseDragged() {}
	@FXML public void pCharMouseDragged() {}
}
