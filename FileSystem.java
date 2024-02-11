package dal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import application.Course;
import application.Faculty;
import application.Recommendation;

// Package data and return to controller
public class FileSystem {
	
	// contains all the information about the faculty
	private static final String facultyFile = "Faculty.txt";
	// contains all recommendations and their attribute values
	private static final String recommendationFile = "recommendationFile.txt";
	// contains text of all recommendations
	// private static final String compiledRecommendationFile = "CompiledRecommendation.txt";
	
	public static void main(String[] args) {
		FileSystem fs = new FileSystem();
		Recommendation[] r = fs.getAllRecommendations();
		for(int n = 0; n < r.length; n++) {
			System.out.println(r[n].getRecommendationText());
		}
	}

	// change faculty's password in facultyFile
	public void changePassword(String newPassword) {

		try {
			Scanner reader = new Scanner(new File(facultyFile));
			String[] info = reader.nextLine().split(";");
			reader.close();

			// 1st field is password
			info[0] = newPassword;

			// override file with new info
			BufferedWriter writer = new BufferedWriter(new FileWriter(facultyFile));
			for (int n = 0; n < info.length; n++) {
				writer.append(info[n]);
				if (n < info.length - 1) {
					writer.append(";");
				}
			}
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// find a match for a password in the "Passwords" file
	public boolean matchPassword(String password) {

		boolean match = false;
		try {
			File file = new File(facultyFile);
			Scanner reader = new Scanner(file);
			// password is the 1st field
			String storedPassword = reader.next();
			// remove trailing comma
			int i = storedPassword.indexOf(";");
			storedPassword = storedPassword.substring(0, i);
			if (storedPassword.compareTo(password) == 0) {
				match = true;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return match;
	}

	// save faculty's information in the facultyFile (use 
	public void saveFaculty(Faculty faculty) {

		try {
			// FileWriter is in override mode
			BufferedWriter writer = new BufferedWriter(new FileWriter(facultyFile));

			// each field separated by a comma
			writer.append(faculty.getPassword());
			writer.append(";" + faculty.getId());
			writer.append(";" + faculty.getLastName());
			writer.append(";" + faculty.getFirstName());
			writer.append(";" + faculty.getTitle());
			writer.append(";" + faculty.getSchoolName());
			writer.append(";" + faculty.getDepartmentName());
			writer.append(";" + faculty.getEmail());
			writer.append(";" + faculty.getPhoneNumber());

			// write lists with each index separated by comma
			writer.append(";" + String.join(",", faculty.getSemesters()));
			writer.append(";" + String.join(",", faculty.getCoursesTaught()));
			writer.append(";" + String.join(",", faculty.getProgramNames()));
			writer.append(";" + String.join(",", faculty.getPersonalCharacteristics()));
			writer.append(";" + String.join(",", faculty.getAcademicCharacteristics()));

			// close the writer
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// package all of faculty's information into Faculty object and return
	public Faculty getFaculty() {

		Faculty faculty = null;

		try {
			Scanner reader = new Scanner(new File(facultyFile));

			// get all the fields
			String[] fields = reader.nextLine().split(";");

			faculty = new Faculty();

			// prepare Faculty object
			faculty.setPassword(fields[0]);
			faculty.setId(fields[1]);
			faculty.setLastName(fields[2]);
			faculty.setFirstName(fields[3]);
			faculty.setTitle(fields[4]);
			faculty.setSchoolName(fields[5]);
			faculty.setDepartmentName(fields[6]);
			faculty.setEmail(fields[7]);
			faculty.setPhoneNumber(fields[8]);
			faculty.setSemesters(fields[9].split(","));
			faculty.setCoursesTaught(fields[10].split(","));
			faculty.setProgramNames(fields[11].split(","));
			faculty.setPersonalCharacteristics(fields[12].split(","));
			faculty.setAcademicCharacteristics(fields[13].split(","));

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return faculty;
	}

	// add new recommendation to file system
	public void addRecommendation(Recommendation recommendation) {
		
		try {
			// FileWriter is in append mode
			BufferedWriter writer = new BufferedWriter(new FileWriter(recommendationFile, true));

			writer.append("\n");
			
			if(recommendation.getIsCompiled()) {
				writer.append(recommendation.getId());
				writer.append(";" + recommendation.getStudentLastName());
				writer.append(";" + recommendation.getRecommendationText());
			}
			else {

				// separate singular fields by a comma
				writer.append(recommendation.getId());
				writer.append(";" + recommendation.getStudentLastName());
				writer.append(";" + recommendation.getStudentFirstName());
				writer.append(";" + recommendation.getStudentGender());
				writer.append(";" + recommendation.getSchoolName());
				writer.append(";" + recommendation.getDate().toString());
				writer.append(";" + recommendation.getProgramApplied());
				writer.append(";" + recommendation.getFirstCourse().getCourseName() + "_"
						+ recommendation.getFirstCourse().getCourseGrade());
				writer.append(";" + recommendation.getFirstCourseSemester());
				writer.append(";" + recommendation.getFirstCourseYear());
	
				// repeated fields with multiple values by a underscore
				writer.append(";" + String.join(",", recommendation.getCoursesStringArray()));
				writer.append(";" + String.join(",", recommendation.getStudentPersonalCharacteristics()));
				writer.append(";" + String.join(",", recommendation.getStudentAcademicCharacteristics()));
			}

			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// update data of a specific recommendation
	public void updateRecommendation(Recommendation recommendation) {
		
		try {
			Scanner reader = new Scanner(new File(recommendationFile));
			String allRecommendations = "";
			
			// skip first line (contains field names)
			allRecommendations += reader.nextLine();
			
			while(reader.hasNextLine()) {
				String[] currRecommendation = reader.nextLine().split(";");
				
				// if id matches
				if(currRecommendation[0].compareTo(recommendation.getId()) == 0) {
					// if compiled
					if(recommendation.getIsCompiled()) {
						currRecommendation[1] = recommendation.getStudentLastName();
						currRecommendation[2] = recommendation.getRecommendationText();
					}
					// if not compiled
					else {
						currRecommendation[1] = recommendation.getStudentLastName();
						currRecommendation[2] = recommendation.getStudentFirstName();
						currRecommendation[3] = recommendation.getStudentGender();
						currRecommendation[4] = recommendation.getSchoolName();
						currRecommendation[5] = recommendation.getDate().toString();
						currRecommendation[6] = recommendation.getProgramApplied();

						currRecommendation[7] = recommendation.getFirstCourse().getCourseName();
						currRecommendation[7] += "_" + recommendation.getFirstCourse().getCourseGrade();

						currRecommendation[8] = recommendation.getFirstCourseSemester();
						currRecommendation[9] = recommendation.getFirstCourseYear();

						currRecommendation[10] = "";
						for (int n = 0; n < recommendation.getCourses().length; n++) {
							currRecommendation[10] += recommendation.getCourses()[n].getCourseName();
							currRecommendation[10] += "_" + recommendation.getCourses()[n].getCourseGrade();
							if (n < recommendation.getCourses().length - 1) {
								currRecommendation[10] += ",";
							}
						}

						currRecommendation[11] = "";
						for (int n = 0; n < recommendation.getStudentPersonalCharacteristics().length; n++) {
							currRecommendation[11] += recommendation.getStudentPersonalCharacteristics()[n];
							if (n < recommendation.getStudentPersonalCharacteristics().length - 1) {
								currRecommendation[11] += ",";
							}
						}

						currRecommendation[12] = "";
						for (int n = 0; n < recommendation.getStudentAcademicCharacteristics().length; n++) {
							currRecommendation[12] += recommendation.getStudentAcademicCharacteristics()[n];
							if (n < recommendation.getStudentAcademicCharacteristics().length - 1) {
								currRecommendation[12] += ",";
							}
						}
					}
				}
				allRecommendations += "\n";
				allRecommendations += String.join(";", currRecommendation);
			}
	
			reader.close();
			
			// write back all recommendations to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter(recommendationFile));
			
			writer.append(allRecommendations);
			
			writer.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public Recommendation[] getAllRecommendations() {
		ArrayList<Recommendation> recommendations = new ArrayList<>();
		
		try {
			Scanner reader = new Scanner(new File(recommendationFile));
			
			// ignore first line (contains field names)
			reader.nextLine();
			
			while(reader.hasNextLine()) {
				String[] currRecommendation = reader.nextLine().split(";");
				
				if(recommendationIsCompiled(currRecommendation[0])) {
					recommendations.add(packageCompiledRecommendation(currRecommendation));
				}
				else {
					// package recommendation in Recommendation object and add to list
					recommendations.add(packageUncompiledRecommendation(currRecommendation));	
				}
			}
			
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// convert ArrayList to array of Recommendations
		return recommendations.toArray(new Recommendation[0]);
	}

	// return recommendation with a particular id
	public Recommendation getRecommendationById(String recommendationId) {

		Recommendation recommendation = null;

		try {
			Scanner reader = new Scanner(new File(recommendationFile));
			String[] currRecommendation;

			// skip first line (it contains field names)
			reader.nextLine();

			while (reader.hasNextLine()) {
				
				currRecommendation = reader.nextLine().split(";");
				if (currRecommendation[0].compareTo(recommendationId) == 0) {
					
					// prepare recommendation object to return
					if(recommendationIsCompiled(currRecommendation[0])) {
						recommendation = packageCompiledRecommendation(currRecommendation);
					}
					else {
						recommendation = packageUncompiledRecommendation(currRecommendation);
					}	
				}
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recommendation;
	}

	// update a recommendations information in the file system
	/* public void updateUncompiledRecommendation(Recommendation recommendation) {
		try {
			// read and store all recommendations
			Scanner reader = new Scanner(new File(uncompiledRecommendationFile));
			String allRecommendations = "";

			// skip first line (contains field names)
			allRecommendations += reader.nextLine();

			while (reader.hasNextLine()) {
				String[] currRecommendation = reader.nextLine().split(";");

				// update recommendation with matching recommendationId
				if (currRecommendation[0].compareTo(recommendation.getId()) == 0) {
					currRecommendation[1] = recommendation.getStudentLastName();
					currRecommendation[2] = recommendation.getStudentFirstName();
					currRecommendation[3] = recommendation.getStudentGender();
					currRecommendation[4] = recommendation.getSchoolName();
					currRecommendation[5] = recommendation.getDate().toString();
					currRecommendation[6] = recommendation.getProgramApplied();

					currRecommendation[7] = recommendation.getFirstCourse().getCourseName();
					currRecommendation[7] += "_" + recommendation.getFirstCourse().getCourseGrade();

					currRecommendation[8] = recommendation.getFirstCourseSemester();
					currRecommendation[9] = recommendation.getFirstCourseYear();

					currRecommendation[10] = "";
					for (int n = 0; n < recommendation.getCourses().length; n++) {
						currRecommendation[10] += recommendation.getCourses()[n].getCourseName();
						currRecommendation[10] += "_" + recommendation.getCourses()[n].getCourseGrade();
						if (n < recommendation.getCourses().length - 1) {
							currRecommendation[10] += ",";
						}
					}

					currRecommendation[11] = "";
					for (int n = 0; n < recommendation.getStudentPersonalCharacteristics().length; n++) {
						currRecommendation[11] += recommendation.getStudentPersonalCharacteristics()[n];
						if (n < recommendation.getStudentPersonalCharacteristics().length - 1) {
							currRecommendation[11] += ",";
						}
					}

					currRecommendation[12] = "";
					for (int n = 0; n < recommendation.getStudentAcademicCharacteristics().length; n++) {
						currRecommendation[12] += recommendation.getStudentAcademicCharacteristics()[n];
						if (n < recommendation.getStudentAcademicCharacteristics().length - 1) {
							currRecommendation[12] += ",";
						}
					}
				}
				allRecommendations += "\n";
				allRecommendations += String.join(";", currRecommendation);
			}
			reader.close();

			// write back all recommendations with updated value
			BufferedWriter writer = new BufferedWriter(new FileWriter(uncompiledRecommendationFile)); // override mode
			writer.append(allRecommendations);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	} */

	
	public void deleteRecommendation(String recommendationId) {

		try {
			Scanner reader = new Scanner(new File(recommendationFile));
			String allRecommendations = "";

			// first line is just field names
			allRecommendations = reader.nextLine();

			while (reader.hasNextLine()) {

				String currRecommendation = reader.nextLine();
				int i = currRecommendation.indexOf(";");
				String currRecommendationId = currRecommendation.substring(0, i);

				// keep all recommendations with different id
				if (currRecommendationId.compareTo(recommendationId) != 0) {
					allRecommendations += "\n";
					allRecommendations += currRecommendation;
				}
			}

			reader.close();

			// write back all recommendations (except deleted one)
			BufferedWriter writer = new BufferedWriter(new FileWriter(recommendationFile)); // override mode
			writer.append(allRecommendations);
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// with help of id, determines if a recommendation is compiled or uncompiled
	private boolean recommendationIsCompiled(String recommendationId) {
		boolean currRecommendationIsCompiled = (recommendationId.split("_")[0].compareTo("compiled") == 0) ? true : false;
		return currRecommendationIsCompiled;
	}

	// helper function to package recommendation into Recommendation object
	private Recommendation packageUncompiledRecommendation(String[] recommendationStr) {
		
		Recommendation recommendation = new Recommendation(false);
		recommendation.setId(recommendationStr[0]);
		recommendation.setStudentLastName(recommendationStr[1]);
		recommendation.setStudentFirstName(recommendationStr[2]);
		recommendation.setStudentGender(recommendationStr[3]);
		recommendation.setSchoolName(recommendationStr[4]);
		recommendation.setDate(LocalDate.parse(recommendationStr[5]));
		recommendation.setProgramApplied(recommendationStr[6]);

		String[] firstCourse = recommendationStr[7].split("_");
		recommendation.setFirstCourse(new Course(firstCourse[0], firstCourse[1]));

		recommendation.setFirstCourseSemester(recommendationStr[8]);
		recommendation.setFirstCourseYear(recommendationStr[9]);

		String[] coursesStr = recommendationStr[10].split(",");
		Course[] courses = new Course[coursesStr.length];
		for (int n = 0; n < coursesStr.length; n++) {
			// [0] = courseName, [1] = courseGrade
			String[] course = coursesStr[n].split("_");
			courses[n] = new Course(course[0], course[1]);
		}
		recommendation.setCourses(courses);

		recommendation.setStudentPersonalCharacteristics(recommendationStr[11].split(","));
		recommendation.setStudentAcademicCharacteristics(recommendationStr[12].split(","));
		
		return recommendation;
	}
	
	// package compiled recommendation as Recommendation object
	private Recommendation packageCompiledRecommendation(String[] recommendationStr) {
		
		Recommendation recommendation = new Recommendation(true);
		recommendation.setId(recommendationStr[0]);
		recommendation.setStudentLastName(recommendationStr[1]);
		recommendation.setRecommendationText(recommendationStr[2]);
		return recommendation;
	}
}
