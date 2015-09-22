package org.courseregistration.datatests;

import dnl.utils.text.table.TextTable;
import org.courseregistration.model.Course;
import org.courseregistration.model.Section;
import org.courseregistration.repositories.CourseRepository;
import org.courseregistration.repositories.SectionRespository;

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
                case "1": // Search for Course
                    SectionRespository sectionRespository = new SectionRespository();
                    List<Section> searchedCourses = sectionRespository.findCourseBy();

                    String [] columnsToPrint_1 = {"section_id","course_id","course_code"};
                    Object[][] dataToPrint_1 = new Object[searchedCourses.size()][3];
                    for(int i=0;i<searchedCourses.size();i=i+1){
                        Section section = searchedCourses.get(i);
                        dataToPrint_1[i][0] = section.getId();
                        dataToPrint_1[i][1] = section.getCourse().getId();
                        dataToPrint_1[i][2] = section.getCourse().getCode();
                    }

                    this.printTable(columnsToPrint_1,dataToPrint_1);

                    break;
                case "2"://List available courses
                    CourseRepository courseRepository = new CourseRepository();
                    List<Course> courses = courseRepository.findAllCourses();

                    String [] columnsToPrint_2 = {"course_id","course_code","course_name"};
                    Object[][] dataToPrint_2 = new Object[courses.size()][3];
                    for(int i=0;i<courses.size();i=i+1){
                        Course course = courses.get(i);
                        dataToPrint_2[i][0] = course.getId();
                        dataToPrint_2[i][1] = course.getCode();
                        dataToPrint_2[i][2] = course.getName();
                    }

                    this.printTable(columnsToPrint_2,dataToPrint_2);
                    break;
			}

			System.out.println();
			printStudentMenu();
		}

	}

    private void printTable(String[] columnsToPrint, Object[][] dataToPrint){
        TextTable tt = new TextTable(columnsToPrint,dataToPrint);
        tt.printTable();
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
