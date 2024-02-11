package application.controller;

import java.time.LocalDate;

import application.CommonUseObjects;
import application.Course;
import application.Faculty;
import application.Recommendation;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CompileRecController {
	
	@FXML TextField lastName;
	@FXML TextArea textArea;
	
	private CommonUseObjects commonObj = CommonUseObjects.getInstance();

	private Recommendation recommendation;
	
	public void initialize() {
		
		recommendation = commonObj.fs.getRecommendationById(commonObj.getSelectedRecommendationId());
		String recommendationText = "";
		
		// if recommendation is not compiled, compile it and display
		if(!recommendation.getIsCompiled()) {
			// get the recommendation that is to be displayed
			recommendationText = compileNewRec(recommendation);
		}
		else {
			// add in the appropriate newlines
			recommendationText = recommendation.getRecommendationText().replace("\\n", "\n");
		}
		
		textArea.setText(recommendationText);
		lastName.setText(recommendation.getStudentLastName());
	}

	@FXML public void save() {
		
		Recommendation recommendationToSave = new Recommendation(true);
		recommendationToSave.setStudentLastName(lastName.getText());
		// replace all newlines of text by token "\n"
		recommendationToSave.setRecommendationText(textArea.getText().replace("\n", "\\n"));
		
		// new recommendation? Call add(). Otherwise, call update()
		if(!recommendation.getIsCompiled()) {
			commonObj.fs.addRecommendation(recommendationToSave);
		}
		else {
			// use same id
			recommendationToSave.setId(commonObj.getSelectedRecommendationId());
			commonObj.fs.updateRecommendation(recommendationToSave);
		}
		// remove recommendation selection
		commonObj.setSelectedRecommendationId("");
		
		// go to homepage
		commonObj.switchToScene("view/HomePage.fxml");
	}

	@FXML public void delete() {
		// delete this recommendation from the file system
		commonObj.fs.deleteRecommendation(commonObj.getSelectedRecommendationId());
		// remove recommendation selection
		commonObj.setSelectedRecommendationId("");
		// return to home page
		commonObj.switchToScene("view/HomePage.fxml");
	}

	@FXML public void Cancel() {
		
		// remove recommendation selection
		commonObj.setSelectedRecommendationId("");
		// back to homepage
		commonObj.switchToScene("view/HomePage.fxml");
	}

	public String compileNewRec(Recommendation recommendation) {
		LocalDate date = LocalDate.now();
		String firstName = recommendation.getStudentFirstName();
		// determine pronoun
		String pronoun = (recommendation.getStudentGender().compareTo("Male") == 0) ? "He" : "She";
		Course[] otherCourses = recommendation.getCourses();
		String[] personalCharacteristics = recommendation.getStudentPersonalCharacteristics();
		String[] academicCharacteristics = recommendation.getStudentAcademicCharacteristics();
		
		Faculty faculty = commonObj.fs.getFaculty();
		String professorFullName = faculty.getFirstName() + " " + faculty.getLastName();
		
		String recStr = "";
		recStr += "Letter of Recommendation";
		recStr += "\n\n\n";
		recStr += "For: " + firstName + " " + recommendation.getStudentLastName();
		recStr += "\n\nDate: " + date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear();
		recStr += "\n\nTo: Graduate Admissions Committee";
		recStr += "\n\nI am writing this letter to recommend my former student " + recommendation.getStudentFirstName() + " " + recommendation.getStudentLastName()
			   + " who is applying for the " + recommendation.getProgramApplied() + " in your school.";
		recStr += "\n\nI met " + firstName + " in " + recommendation.getFirstCourseSemester()
			   + " when " + pronoun.toLowerCase() + " enrolled in my " + recommendation.getFirstCourse().getCourseName() + " course";
		recStr += "\n\n" + firstName + " earned " + recommendation.getFirstCourse().getCourseGrade() + " from this tough course"
			   + ", and this shows how knowledgeable and hard worker " + pronoun.toLowerCase() + " is";
		
		if(otherCourses != null) {
			recStr += "\n\n" + pronoun + " also earned ";
			for(int n = 0; n < otherCourses.length; n++) {
				// last course
				if(n == otherCourses.length - 1) {
					if(n == 1) {
						// only 2 courses (no comma needed before "and"
						recStr += " and ";
					}
					else if(n >= 2) {
						recStr += ", and ";
					}
				}
				// not the last course
				else {
					// if not the first course
					if(n != 0) {
						recStr += ", ";
					}
				}
				recStr += "\"" + otherCourses[n].getCourseGrade() + "\"";
				recStr += " from my \"" + otherCourses[n].getCourseName() + "\"";
			}
			recStr += ".";
		}
		
		if(academicCharacteristics != null) {
			recStr += "\n\n" + firstName + " ";
			for(int n = 0; n < academicCharacteristics.length; n++) {
				// last characteristic
				if(n == academicCharacteristics.length - 1) {
					if(n == 1) {
						// only 2 characteristics (no comma needed before "and"
						recStr += " and ";
					}
					else if(n >= 2) {
						recStr += ", and ";
					}
				}
				// not the last characteristic
				else {
					// if not the first characteristic
					if(n != 0) {
						recStr += ", ";
					}
				}
				recStr += academicCharacteristics[n];
			}
			recStr += ".";
		}
		
		if(personalCharacteristics != null) {
			recStr += "\n\n" + pronoun + " was always ";
			for(int n = 0; n < personalCharacteristics.length; n++) {
				// last characteristic
				if(n == personalCharacteristics.length - 1) {
					if(n == 1) {
						// only 2 characteristics (no comma needed before "and"
						recStr += " and ";
					}
					else if(n >= 2) {
						recStr += ", and ";
					}
				}
				// not the last characteristic
				else {
					// if not the first characteristic
					if(n != 0) {
						recStr += ", ";
					}
				}
				recStr += personalCharacteristics[n];
			}
			recStr += ".";
		}
		
		recStr += "\n\nFurthermore, I noticed from the term project result, " + pronoun.toLowerCase() + " developed leadership, time management, and problem-solving skills."
				+ " " + pronoun.toLowerCase() + " worked effectively with the team members and delegated tasks appropriately. They were able to deliver a successful project in a timely fashion.";
		recStr += "\n\nI believe that " + firstName +  " has the capacity to excel at higher education program and this is my pleasure to highly recommend him.";
		recStr += "\n\nPlease do not hesitate to contact me with further questions.";
		recStr += "\n\n\nVery Respectfully,";
		recStr += "\n\n" + professorFullName;
		recStr += "\n\n" + faculty.getTitle();
		recStr += "\n" + faculty.getSchoolName() + ", " + faculty.getDepartmentName();
		recStr += "\n" + faculty.getEmail();
		recStr += "\n" + faculty.getPhoneNumber();
		
		return recStr;
	}
}

