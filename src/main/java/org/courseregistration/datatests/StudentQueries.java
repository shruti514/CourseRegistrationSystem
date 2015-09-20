package org.courseregistration.datatests;

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
				&& !"quit".equalsIgnoreCase(input)) {

			switch (input) {
			case "1":
				break;

			}
			if (input.trim().equalsIgnoreCase(exitCode)) {
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
