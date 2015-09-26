package org.courseregistration.dbtests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * This is a singleton class to return connection object
 */
public class DatabaseConnector {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	static String IPADRESS = "localhost";

	static String PORT = "3306";

	static final String DB_URL = "jdbc:mysql://" + IPADRESS + ":" + PORT
			+ "/hibernate_test";

	// Database credentials

	static String USER = "root";

	static String PASS = "";


}
