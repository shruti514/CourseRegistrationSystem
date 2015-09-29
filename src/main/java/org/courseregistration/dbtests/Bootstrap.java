package org.courseregistration.dbtests;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.courseregistration.dao.CourseDAO;
import org.courseregistration.dao.SearchCriteria;
import org.courseregistration.dao.SectionDAO;
import org.courseregistration.dao.StudentDAO;
import org.courseregistration.model.Course;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;

import com.google.common.collect.Maps;

public class Bootstrap {

	private static String exitCode = "quit";
	protected static EntityManager emf;

	private EntityManager entityManager;
	private Map<SearchCriteria, String> criteria;
	private SectionDAO sectionDAO;

	private String input = "";
	private String command;
	private String parameter;

	public Bootstrap() {
		entityManager = HibernateUtils.getEntityManager();
		criteria = Maps.newHashMap();
		sectionDAO = new SectionDAO(entityManager);
	}

	public static void main(String[] args) {
		// If error comment below line
		HibernateUtils.initEntityManager();

		// This is just for now till we don't have data
		// DataGenerator.generateData();

		Bootstrap program = new Bootstrap();
		program.printWelcomeMessage();
		program.printCommandEntry();

		program.getInputQueryFromUser();

		// If error comment below line
		HibernateUtils.closeEntityManager();
	}

	private void getInputQueryFromUser() {
		Scanner scanner = new Scanner(System.in);
		while ((this.input = scanner.nextLine()) != null
				&& !exitCode.equalsIgnoreCase(this.input)) {

			if (this.getArgumentsAndCommand())
				this.executeCommand();
			else {
				printError();
			}

			if (exitCode.equals(this.input)) {
				System.out.println("Signing off.");
				break;
			}

			this.printCommandEntry();
		}
	}

	private boolean getArgumentsAndCommand() {
		try {
			String temp = this.input.toLowerCase().trim();
			if (temp.contains("=")) {
				String args[] = temp.split("=");
				if (args.length == 2) {
					this.parameter = args[1];
				}
				this.command = args[0];
			} else {
				this.command = temp;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void executeCommand() {

		if (!this.command.isEmpty())
			System.out.println(this.command);

		switch (this.command.toLowerCase()) {
		case "list all students":
			StudentDAO dao = new StudentDAO(entityManager);
			List<Student> studentlist = dao.findAll();

			System.out
					.println("List of Students: \n ________________________________");
			for (Student student : studentlist) {
				System.out.println(student.toString());
			}
			break;

		case "list all courses":
			CourseDAO courseDao = new CourseDAO(entityManager);
			List<Course> courselist = courseDao.findAll();
			System.out
					.println("List of Courses: \n ________________________________");
			for (Course course : courselist) {
				System.out.println(course.toString());
			}

			break;

		case "as a student i should be able to search for a course which matches exactly cs-218":
			criteria.clear();
			criteria.put(SearchCriteria.COURSE_CODE_EQUALS, "CS-218");
			List<Section> courseByCriteria = sectionDAO
					.findCourseByCriteria(criteria);

			for (Section course : courseByCriteria) {
				System.out.println(course.toString());
			}
			break;
		case "":
			break;
		default:

			printError();
			break;
		}
	}

	private void printError() {
		System.out.println("ERROR: Invalid Command [" + this.input + "]");
	}

	private void printCommandEntry() {
		System.out.print("[write quit to exit]# ");
	}

	private void printWelcomeMessage() {
		System.out.println("***********************************************");
		System.out.println("Welcome to Student Course Registartion System!");
		System.out.println("***********************************************");
	}
}
