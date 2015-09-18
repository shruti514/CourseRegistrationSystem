package org.courseregistration.datatests;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * This is a singleton class to return connection object
 */
public class DatabaseConnector {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	static final String DB_URL = "jdbc:mysql://localhost:3307/taxi_cab_system";

	// Database credentials

	static final String USER = "root";

	static final String PASS = "root";

	private static Connection connection = null;

	public static Connection getDBConnection() {

		if (connection == null) {
			try {
				Class.forName(JDBC_DRIVER);
				System.out.println("Connecting to database...");
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
