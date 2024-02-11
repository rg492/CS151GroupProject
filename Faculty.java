package application;

import java.util.UUID;

// Contains all information about the faculty
public class Faculty {
	
	private String id;
	
	private String password;

	private String lastName;
	private String firstName;
	private String title;
	
	private String schoolName;
	private String departmentName;
	
	private String email;
	private String phoneNumber;
	
	private String[] semesters;
	private String[] coursesTaught;
	private String[] programNames;
	private String[] personalCharacteristics;
	private String[] academicCharacteristics;
	
	public Faculty() {}
	
	// constructor
	public Faculty(String password) {
		// create a unique id for user
		id = UUID.randomUUID().toString();
		this.password = password;
	}
	
	// setters and getters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String[] getSemesters() {
		return semesters;
	}
	public void setSemesters(String[] semesters) {
		this.semesters = semesters.clone();
	}
	
	public String[] getCoursesTaught() {
		return coursesTaught;
	}
	public void setCoursesTaught(String[] coursesTaught) {
		this.coursesTaught = coursesTaught.clone();
	}
	
	public String[] getProgramNames() {
		return programNames;
	}
	public void setProgramNames(String[] programNames) {
		this.programNames = programNames.clone();
	}
	
	public String[] getPersonalCharacteristics() {
		return personalCharacteristics;
	}
	public void setPersonalCharacteristics(String[] personalCharacteristics) {
		this.personalCharacteristics = personalCharacteristics.clone();
	}
	
	public String[] getAcademicCharacteristics() {
		return academicCharacteristics;
	}
	public void setAcademicCharacteristics(String[] academicCharacteristics) {
		this.academicCharacteristics = academicCharacteristics.clone();
	}
}