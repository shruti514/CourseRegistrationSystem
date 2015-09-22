package org.courseregistration.datatests;

import dnl.utils.text.table.TextTable;
import org.courseregistration.model.Section;
import org.courseregistration.repositories.CourseRepository;

import java.util.List;
import java.util.Scanner;

/*
 * Can we follow this format for developing actor related function in separate and related classes?
 * Plus we can have functions related to each functionality we are providing
 * with ArrayList or Specific Object as return type.
 * So that it will be easier to call same functions from web UI and from API calls
 */
public class StudentQueries {

	private String exitCode = "5";

	public StudentQueries() {
		// TODO Auto-generated constructor stub
	}

	public void startStudentMenuExecution() {
		System.out.println();
		printStudentMenu();
		Scanner scanner = new Scanner(System.in);
		String input = "";
		while ((input = scanner.nextLine()) != null
				&& input.trim().length() != 0
				&& !exitCode.equals(input)) {

			switch (input) {
                case "1":
                    CourseRepository courseRepository = new CourseRepository();
                    List<Section> searchedCourses = courseRepository.findCourseBy();

                    String [] columnsToPrint = {"section_id","course_id","course_code"};
                    Object[][] dataToPrint = new Object[searchedCourses.size()][3];
                    for(int i=0;i<searchedCourses.size();i=i+1){
                        Section section = searchedCourses.get(i);
                        dataToPrint[i][0] = section.getId();
                        dataToPrint[i][1] = section.getCourse().getId();
                        dataToPrint[i][2] = section.getCourse().getCode();
                    }

                    TextTable tt = new TextTable(columnsToPrint,dataToPrint);
                    tt.printTable();

                    break;
                case "2":

                    break;
			}

			System.out.println();
			printStudentMenu();
		}

	}

	/*
	 * Printing Student related Menu
	 */
	private void printStudentMenu() {
		System.out.println("**** Student Menu ****");
		System.out.println("1. Search for course");
		System.out.println("2. List all available courses");
		System.out.println("3. Register to course");
		System.out.println(exitCode + ". Exit");
		System.out.println();
		System.out.println("Please select your choice: ");
	}
}
