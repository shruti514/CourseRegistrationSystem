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

			if (this.getCommandAndSingleParameter())
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

	private boolean getCommandAndSingleParameter() {
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

		if (!this.command.trim().isEmpty())
			// System.out.println(this.command);

			switch (this.command.trim().toLowerCase()) {
			case "list all students":
				StudentDAO dao = new StudentDAO(entityManager);
				List<Student> studentlist = dao.findAll();

				System.out
						.println("\n\tList of Students: \n\t************************************************");
				for (Student student : studentlist) {
					System.out.println(student.toString());
				}
				break;

			case "list all courses":
				List<Course> courselist = courseDAO.findAll();
				System.out
						.println("\n\tList of Courses: \n\t************************************************");
				for (Course course : courselist) {
					System.out.println(course.toString());
				}

				break;

			case "list all professors":
				List<Professor> professorList = professorDAO.findAll();
				System.out
						.println("\n\tList of Professors: \n\t************************************************");
				for (Professor professor : professorList) {
					System.out.println(professor.toString());
				}

				break;

			case "as a student i should be able to search for a course which matches exactly cs-218":
				criteria.clear();
				criteria.put(SearchCriteria.COURSE_CODE_EQUALS, "CS-218");
				searchCoursesByCriteria("No Course found by ID CS-218");
				break;

			case "as a student i should be able to add a course cs-218 conducted by professor larkin":
				Student student = loadStudentJohn();

				criteria.clear();
				criteria.put(SearchCriteria.COURSE_CODE_EQUALS, "CS-218");
				criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_EQUALS,
						"Larkin");
				List<Section> coursesByID = sectionDAO
						.findCourseByCriteria(criteria);

				if (student.getSections().contains(coursesByID.get(0)))
					printProperMessage(
							"Error",
							"John, You have already registered to the course CS-218 taken by Professor Larkin!");
				else {
					student.addSection(coursesByID.get(0));
					printProperMessage("Success",
							"You are Successfully registerred to the course CS-218 by Prof. Larkin");
					System.out.println();

					Student updatedStudent = studentDAO.update(student);

					for (Section section : updatedStudent.getSections()) {
						System.out.println(section.toString());
					}
				}
				break;

			case "as a student i should be able to drop a course cs-218 conducted by professor larkin":
				Student studentJohn = loadStudentJohn();

				criteria.clear();
				criteria.put(SearchCriteria.COURSE_CODE_EQUALS, "CS-218");
				criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_EQUALS,
						"Larkin");
				List<Section> coursesByProfMike = sectionDAO
						.findCourseByCriteria(criteria);

				if (!studentJohn.getSections().contains(
						coursesByProfMike.get(0)))
					printProperMessage("Error",
							"John, You have not registered to the course CS-218 taken by Professor Larkin!");
				else {

					studentJohn.dropSection(coursesByProfMike.get(0));
					printProperMessage("Success",
							"You are successfully dropped out from course: CS-218");
					System.out.println();

					Student updatedJohn = studentDAO.update(studentJohn);

					for (Section section : updatedJohn.getSections()) {
						System.out.println(section.toString());
					}
				}
				break;

			case "list the students from course cs-218 of professor larkin":
				// TODO Shraddha
				Section section = fetchSection("CS-218", "Larkin");
				List<Student> studentsList = section.getStudents();

				if (studentsList.size() > 0) {
					for (Student currStudent : studentsList) {
						System.out.println(currStudent.toString());
					}
				} else {
					printProperMessage("ERROR", section.getCourse().getCode()
							+ " has no students registerred. ");
				}
				break;

			case "list of sections of john":

				Student studentSections = loadStudentJohn();
				List<Section> sectionsList = studentSections.getSections();

				if (sectionsList.size() > 0)
					for (Section course : sectionsList) {
						System.out.println(course.toString());
					}
				else
					printProperMessage("ERROR", studentSections.getFirstName()
							+ " is not registered to any course. ");
				break;

			case "show number of open seats in course cs-218 of professor larkin":
				Section courseSection = fetchSection("CS-218", "Larkin");
				int total_seats = courseSection.getTotalCapacity();
				int enrolled_students = courseSection.getStudents().size();

				System.out.println("Section: " + courseSection.toString());
				printProperMessage("Success", "This course has "
						+ (total_seats - enrolled_students) + " open seats");
				System.out.println();

				break;

			case "search for course by course code":
				criteria.clear();
				criteria.put(SearchCriteria.COURSE_CODE_EQUALS, this.parameter);
				searchCoursesByCriteria("No courses found of this course ID");
				break;

			case "search for courses by professor":
				criteria.clear();
				criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_EQUALS,
						this.parameter);
				searchCoursesByCriteria("No courses found of this professor");
				break;

			case "search for courses by session":
				criteria.clear();
				criteria.put(SearchCriteria.SEMESTER_EQUALS, this.parameter);
				searchCoursesByCriteria("No courses found in this semester");
				break;

			case "search for courses by name":
				criteria.clear();
				criteria.put(SearchCriteria.COURSE_NAME_CONTAINS,
						this.parameter);
				searchCoursesByCriteria("No courses found of this Name");
				break;

			case "search for courses by day of a week":
				criteria.clear();
				criteria.put(SearchCriteria.DAY_OF_WEEK_EQUALS, this.parameter);
				searchCoursesByCriteria("No courses found on this day of week");
				break;

			case "search for courses by department":
				criteria.clear();
				criteria.put(SearchCriteria.DEPARTMENT_EQUALS, this.parameter);
				searchCoursesByCriteria("No courses found in this Department");
				break;

			case "help":
				System.out.println();
				printHelp();
				System.out.println();
				break;

			case "":
				break;

			default:
				printError();
				break;
			}
	}

	private Student loadStudentJohn() {
		return entityManager
				.createQuery(
						"select s from Student s where s.firstName=:firstName",
						Student.class).setParameter("firstName", "john")
				.getSingleResult();

	}

	private Section fetchSection(String courseId, String professor) {
		return entityManager
				.createQuery(
						"select s from Section s where s.course.code=:courseId and s.professor.lastName=:lastname",
						Section.class).setParameter("courseId", courseId)
				.setParameter("lastname", professor).getSingleResult();
	}

	private void searchCoursesByCriteria(String errorMsg) {
		int counter = 1;
		List<Section> coursesByID = sectionDAO.findCourseByCriteria(criteria);
		if (coursesByID.size() > 0)
			for (Section course : coursesByID) {
				System.out.println("\n\t__________________________________"
						+ counter + "___________________________________");
				System.out.println(course.toString());
				counter++;
			}
		else
			printProperMessage("ERROR", errorMsg);
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

	private void printHelp() {

		System.out.println("List all Students");
		System.out.println("List all Courses");
		System.out.println("List all Professors");
		System.out
				.println("As a student I should be able to search for a course which matches exactly CS-218");
		System.out
				.println("As a student I should be able to ADD a course CS-218 conducted by professor Larkin");
		System.out
				.println("As a student I should be able to DROP a course CS-218 conducted by professor Larkin");
		System.out
				.println("List the students from course CS-218 of professor Larkin");
		System.out.println("List of sections of John");
		System.out
				.println("Show number of open seats in course CS-218 of professor Larkin");

		System.out.println("Search for courses by Session=Fall-2015");
		System.out.println("Search for courses by day of a week=Friday");
		System.out.println("Search for courses by professor=Ranjan");
		System.out.println("Search for course by course code=CS-218");
		System.out.println("Search courses by name=Quantitative Analysis");
		System.out.println("Search for courses by department=Computer Science");
	}
}
