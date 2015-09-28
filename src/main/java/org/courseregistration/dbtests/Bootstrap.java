package org.courseregistration.dbtests;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.courseregistration.dao.StudentDAO;
import org.courseregistration.model.Student;

public class Bootstrap {

	private static String exitCode = "quit";
	protected static EntityManager emf;

	public static void main(String[] args) {
		// If error comment below line
		HibernateUtils.initEntityManager();

		// This is just for now till we dont have data
		DataGenerator.generateData();

		String input = "";
		System.out.println("***********************************************");
		System.out.println("Welcome to Student Course Registartion System!");
		System.out.println("***********************************************");
		printMenu();
		Scanner scanner = new Scanner(System.in);
		while ((input = scanner.nextLine()) != null
				&& input.trim().length() != 0
				&& !exitCode.equalsIgnoreCase(input)) {
			System.out.println(input);
			switch (input.toLowerCase()) {
			case "search":
				StudentDAO dao = new StudentDAO(
						HibernateUtils.getEntityManager());
				List<Student> list = dao.findAll();

				System.out.println("List of Students: " + list);
				break;
			case "2":

				break;

			case "3":
				;
				break;

			case "exit":
				break;
			}
			if (exitCode.equals(input)) {
				System.out.println("Signing off.");
				break;
			}

			System.out.println();
			printMenu();
		}

		// If error comment below line
		HibernateUtils.closeEntityManager();
	}

	private static void printMenu() {

		System.out.print("[write quit to exit]# ");

		// System.out.println("**** Main Menu ****");
		// System.out.println("1. Student Menu");
		// System.out.println("2. Professor Menu");
		// System.out.println("3. Admin Menu");
		// System.out.println("4. Database Menu");
		// System.out.println(exitCode + ". Exit");
		// System.out.println();
		// System.out.println("Please select your choice: ");
	}

}
