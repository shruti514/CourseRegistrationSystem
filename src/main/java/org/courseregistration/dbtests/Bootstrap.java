package org.courseregistration.dbtests;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.courseregistration.dao.CourseDAO;
import org.courseregistration.dao.ProfessorDAO;
import org.courseregistration.dao.SearchCriteria;
import org.courseregistration.dao.SectionDAO;
import org.courseregistration.dao.StudentDAO;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;

import com.google.common.collect.Maps;

public class Bootstrap {

	private static String exitCode = "quit";
	protected static EntityManager emf;

	private EntityManager entityManager;
	private Map<SearchCriteria, String> criteria;
	private SectionDAO sectionDAO;
	private CourseDAO courseDAO;
	private ProfessorDAO professorDAO;
	private StudentDAO studentDAO;

	private String input = "";
	private String command;
	private String parameter;

	public Bootstrap() {
		entityManager = HibernateUtils.getEntityManager();
		criteria = Maps.newHashMap();
		sectionDAO = new SectionDAO(entityManager);
		courseDAO = new CourseDAO(entityManager);
		professorDAO = new ProfessorDAO(entityManager);
		studentDAO = new StudentDAO(entityManager);
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
			// CourseDAO courseDao = new CourseDAO(entityManager);
			List<Course> courselist = courseDAO.findAll();
			System.out
					.println("List of Courses: \n ________________________________");
			for (Course course : courselist) {
				System.out.println(course.toString());
			}

			break;

		case "list all professors":
			List<Professor> professorList = professorDAO.findAll();
			System.out
					.println("List of Courses: \n ________________________________");
			for (Professor professor : professorList) {
				System.out.println(professor.toString());
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

		case "search for course by courseid":
			criteria.clear();
			criteria.put(SearchCriteria.COURSE_CODE_EQUALS, this.parameter);
			List<Section> coursesByID = sectionDAO
					.findCourseByCriteria(criteria);
			if (coursesByID.size() > 0)
				for (Section course : coursesByID) {
					System.out.println(course.toString());
				}
			else
				printProperMessage("ERROR", "No courses found of this type");
			break;

		case "search for courses by professor":
			criteria.clear();
			criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_EQUALS,
					this.parameter);
			List<Section> coursesByProf = sectionDAO
					.findCourseByCriteria(criteria);
			if (coursesByProf.size() > 0)
				for (Section course : coursesByProf) {
					System.out.println(course.toString());
				}
			else
				printProperMessage("ERROR",
						"No courses found of this professor");
			break;

		case "search for courses by session":
			criteria.clear();
			criteria.put(SearchCriteria.SEMESTER_EQUALS, this.parameter);
			List<Section> coursesBySem = sectionDAO
					.findCourseByCriteria(criteria);
			if (coursesBySem.size() > 0)
				for (Section course : coursesBySem) {
					System.out.println(course.toString());
				}
			else
				printProperMessage("ERROR", "No courses found in this semester");
			break;

		case "search for courses by name":
			criteria.clear();
			criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, this.parameter);
			List<Section> coursesByName = sectionDAO
					.findCourseByCriteria(criteria);
			if (coursesByName.size() > 0)
				for (Section course : coursesByName) {
					System.out.println(course.toString());
				}
			else
				printProperMessage("ERROR", "No courses found of this Name");
			break;

		case "search for courses by day of a week":
			criteria.clear();
			criteria.put(SearchCriteria.DAY_OF_WEEK_EQUALS, this.parameter);
			List<Section> coursesOnADay = sectionDAO
					.findCourseByCriteria(criteria);
			if (coursesOnADay.size() > 0)
				for (Section course : coursesOnADay) {
					System.out.println(course.toString());
				}
			else
				printProperMessage("ERROR",
						"No courses found on this day of week");
			break;

		case "search for courses by department":
			criteria.clear();
			criteria.put(SearchCriteria.DEPARTMENT_EQUALS, this.parameter);
			List<Section> coursesByDeptName = sectionDAO
					.findCourseByCriteria(criteria);
			if (coursesByDeptName.size() > 0)
				for (Section course : coursesByDeptName) {
					System.out.println(course.toString());
				}
			else
				printProperMessage("ERROR",
						"No courses found in this Department");
			break;

		case "list of students from courseid":
			criteria.clear();
			criteria.put(SearchCriteria.COURSE_CODE_EQUALS, this.parameter);

			List<Section> studentsByCourse = sectionDAO
					.findCourseByCriteria(criteria);

			if (studentsByCourse.size() > 0)
				for (Section course : studentsByCourse) {
					System.out.println(course.toString());
				}
			else
				printProperMessage("ERROR",
						"No courses found in this Department");
			break;
		case "":
			break;

		default:
			printError();
			break;
		}
	}

	private void printProperMessage(String tag, String message) {
		System.out.println(tag + ": " + message);
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
