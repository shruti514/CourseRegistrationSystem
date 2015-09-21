package org.courseregistration.datatests;

import java.util.Scanner;

public class Bootstrap {

	private static String exitCode = "5";

	public static void main(String[] args) {

		System.out.println();
		printMenu();
		Scanner scanner = new Scanner(System.in);
		String input = "";
		while ((input = scanner.nextLine()) != null
				&& input.trim().length() != 0
				&& !"quit".equalsIgnoreCase(input)) {

			switch (input) {
			case "1":
				System.out.println();
				StudentQueries student = new StudentQueries();
				student.startStudentMenuExecution();
				System.out.println("Executing Student Queries");
				break;

			case "2":
				Queries.executeQuery_1();
				break;

			case "3":
				Queries.executeQuery_1();
				break;

			case "4":
				DatabaseConnector dbConnector = new DatabaseConnector();
				dbConnector.getAllTables();
				break;

			case "5":
				break;

			}
			if (input.trim().equalsIgnoreCase(exitCode)) {
				break;
			}

			System.out.println();
			printMenu();
		}
	}

	private static void printMenu() {
		System.out.println("**** Main Menu ****");
		System.out.println("1. Student Menu");
		System.out.println("2. Professor Menu");
		System.out.println("3. Admin Menu");
		System.out.println("4. Database Menu");
		System.out.println(exitCode + ". Exit");
		System.out.println();
		System.out.println("Please select your choice: ");
	}

}
