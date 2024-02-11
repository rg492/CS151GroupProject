package application;

// Each instance represents a single course
public class Course {
	
	// name of the course
	private String courseName;
	// grade of the course
	private String courseGrade;
	
	public Course(String courseName, String courseGrade) {
		this.courseName = courseName;
		this.courseGrade = courseGrade;
	}
	
	// setters and getters
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseGrade() {
		return courseGrade;
	}
	public void setCourseGrade(String courseGrade) {
		this.courseGrade = courseGrade;
	}
}
