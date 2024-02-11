package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;


public class CourseCellFactory implements Callback<ListView<Course>, ListCell<Course>> {
	
	private List<Course> selectedCourses = new ArrayList<>();
	
	public Course[] getSelectedCourses() {
		return selectedCourses.toArray(new Course[0]);
	}
	
	public void addSelectedCourse(Course course) {
		selectedCourses.add(course);
	}
	
	public String getCourseGrade(String courseName) {
		for(int n = 0; n < selectedCourses.size(); n++) {
			if(selectedCourses.get(n).getCourseName().compareTo(courseName) == 0) {
				return selectedCourses.get(n).getCourseGrade();
			}
		}
		return null;
	}
	
	@Override
	public ListCell<Course> call(ListView<Course> param) {
		return new ListCell<Course>() {
			
			private AnchorPane aPane = new AnchorPane();
			private Label courseNameLbl = new Label();
			private ComboBox<String> courseGrade = new ComboBox<>();
			public CheckBox checkbox = new CheckBox();
			
			@Override
			protected void updateItem(Course course, boolean empty) {
				super.updateItem(course, empty);
				if(empty || course == null) {
					setText(null);
					setGraphic(null);
				}
				else {
					courseNameLbl.setText(course.getCourseName());
					
					courseGrade.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					courseGrade.setPromptText("Grade");
					courseGrade.setItems(FXCollections.observableArrayList(new String[] {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-"}));
					
					String grade = null;
					// select course if it is in the selectedCourses list
					if((grade = getCourseGrade(course.getCourseName())) != null) {
						checkbox.setSelected(true);
						courseGrade.getSelectionModel().select(grade);
					}
					
					checkbox.setOnAction(event -> {
						// if selected, add course
						if(checkbox.isSelected()) {
							selectedCourses.add(course);
						}
						// if unselected, remove course
						else {
							selectedCourses.remove(course);
						}
					});
					
					courseGrade.setOnAction(event -> {
						course.setCourseGrade(courseGrade.getSelectionModel().getSelectedItem());
					});
					
					aPane.setPrefHeight(50);
					aPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					aPane.getChildren().removeAll(courseNameLbl, courseGrade, checkbox);
					aPane.getChildren().addAll(courseNameLbl, courseGrade, checkbox);
					
					AnchorPane.setTopAnchor(courseNameLbl, 0.0);
					AnchorPane.setLeftAnchor(courseNameLbl, 0.0);
					
					AnchorPane.setBottomAnchor(courseGrade, -5.0);
					AnchorPane.setLeftAnchor(courseGrade, -10.0);
					
					AnchorPane.setTopAnchor(checkbox, 15.0);
					AnchorPane.setRightAnchor(checkbox, 85.0);
					
					setText(null);
					setGraphic(aPane);
				}
			}
		};
	}
}
