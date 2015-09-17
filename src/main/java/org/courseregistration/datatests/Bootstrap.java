package org.courseregistration.datatests;

import java.util.Scanner;

public class Bootstrap {

    public static void main(String[] args) {


        System.out.println("Type: 'Quit' to exit application.");

        System.out.println("1. Query to search for courses taught in Fall-2016");
        System.out.println("Select * from Section s where s.session='Fall-2016'");

        System.out.println("2. Query to enroll Student for course CMPE 272 taught by Prof.xyz in Fall 2015");
        System.out.println("Select * from Section s where s.session='Fall-2016'");

        System.out.println("3. Query to search for courses taught in Fall-2016");
        System.out.println("Select * from Section s where s.session='Fall-2016'");

        System.out.println("4. Query to search for courses taught in Fall-2016");
        System.out.println("Select * from Section s where s.session='Fall-2016'");


        Scanner scanner = new Scanner(System.in);
        String input = "";
            while ((input = scanner.nextLine()) != null && input.length() != 0 && ! "quit".equalsIgnoreCase(input)){
                switch(input){
                    case "1":
                           Queries.executeQuery_1();
                        break;
                    case "2":
                        Queries.executeQuery_1();
                        break;
                    case "3":
                        Queries.executeQuery_1();
                        break;
                    case "4":
                        Queries.executeQuery_1();
                        break;

                }
            }
    }
}
