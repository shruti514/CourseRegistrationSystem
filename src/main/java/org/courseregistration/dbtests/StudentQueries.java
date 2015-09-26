package org.courseregistration.dbtests;

import dnl.utils.text.table.TextTable;
import org.courseregistration.model.Course;
import org.courseregistration.model.Section;
import org.courseregistration.dao.CourseDAO;
import org.courseregistration.dao.SectionDAO;

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


                    break;
                case "2"://List available courses
                    CourseDAO courseDAO = new CourseDAO(HibernateUtils.getEntityManager());
                    List<Course> courses = courseDAO.findAll();

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
