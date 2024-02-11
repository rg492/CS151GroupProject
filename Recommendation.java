package application;

import java.time.LocalDate;
import java.util.UUID;

// Represents a single recommendation
public class Recommendation {
	
	// a unique ID
	private String id;
	
	private String studentFirstName;
	private String studentLastName;
	
	private String recommendationText;
	
	private boolean isCompiled;
	
	private String studentGender;
	
	private String schoolName;
	
	private LocalDate date;
	
	private String programApplied;
	
	// first course student ever took with faculty
	private Course firstCourse;
	// semester in which student first took course with faculty
	private String firstCourseSemester;
	// year in which student first took course with faculty
	private String firstCourseYear;
	
	private Course[] courses;
	
	private String[] studentPersonalCharacteristics;
	private String[] studentAcademicCharacteristics;
	
	public Recommendation(boolean isCompiled) {
		this.isCompiled = isCompiled;
		id = "";
		// if compiled, add "compiled" in front of id
		if(isCompiled) {
			id = "compiled_";
		}
		// create unique, randomly generated id for recommendation
		id += UUID.randomUUID().toString();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	// setters and getters
	public String getId() {
		return id;
	}
		
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getProgramApplied() {
		return programApplied;
	}
	public void setProgramApplied(String programApplied) {
		this.programApplied = programApplied;
	}
	
	public Course getFirstCourse() {
		return firstCourse;
	}
	public void setFirstCourse(Course firstCourse) {
		this.firstCourse = firstCourse;
	}
	
	public String getFirstCourseSemester() {
		return firstCourseSemester;
	}
	public void setFirstCourseSemester(String firstCourseSemester) {
		this.firstCourseSemester = firstCourseSemester;
	}
	
	public String getFirstCourseYear() {
		return firstCourseYear;
	}
	public void setFirstCourseYear(String firstCourseYear) {
		this.firstCourseYear = firstCourseYear;
	}
	
	public Course[] getCourses() {
		return courses;
	}
	// return String[] = {courseName_Grade, courseName_Grade...}
	public String[] getCoursesStringArray() {
		String[] arr = new String[courses.length];
		for(int n = 0; n < courses.length; n++) {
			arr[n] = courses[n].getCourseName() + "_";
			arr[n] += courses[n].getCourseGrade();
		}
		return arr;
	}
	public void setCourses(Course[] courses) {
		this.courses = courses;
	}
	
	public String[] getStudentPersonalCharacteristics() {
		return studentPersonalCharacteristics;
	}
	public void setStudentPersonalCharacteristics(String[] studentPersonalCharacteristics) {
		this.studentPersonalCharacteristics = studentPersonalCharacteristics.clone();
	}
	
	public String[] getStudentAcademicCharacteristics() {
		return studentAcademicCharacteristics;
	}
	public void setStudentAcademicCharacteristics(String[] studentAcademicCharacteristics) {
		this.studentAcademicCharacteristics = studentAcademicCharacteristics.clone();
	}
	
	public String getRecommendationText() {
		return recommendationText; 
	}
	public void setRecommendationText(String recommendationText) {
		this.recommendationText = recommendationText;
	}
	
	public boolean getIsCompiled() {
		return isCompiled;
	}
	public void setIsCompiled(boolean isCompiled) {
		this.isCompiled = isCompiled;
	}
}