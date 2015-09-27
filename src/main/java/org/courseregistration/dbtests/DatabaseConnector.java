package org.courseregistration.dbtests;

/*
 * This is a singleton class to return connection object
 */
public class DatabaseConnector {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	static String IPADRESS = "localhost";

	static String DBNAME = "hibernate_test";
	// static String DBNAME = "student_registration_database";

	static String PORT = "3306";

	static final String DB_URL = "jdbc:mysql://" + IPADRESS + ":" + PORT + "/"
			+ DBNAME;

	// Database credentials

	static String USER = "root";

	static String PASS = "";

}
