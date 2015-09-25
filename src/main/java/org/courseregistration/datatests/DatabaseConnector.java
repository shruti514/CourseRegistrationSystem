package org.courseregistration.datatests;

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

	static String PORT = "3307";

	static final String DB_URL = "jdbc:mysql://" + IPADRESS + ":" + PORT
			+ "/student_registration_database";

	// Database credentials

	static String USER = "root";

	static String PASS = "root";

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

	public boolean getAllTables() {
		try {
			getDBConnection();

			PreparedStatement statement = connection
					.prepareStatement("show tables");
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				String name = set.getString(1);
				System.out.println("Table: " + name);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public static void getUserAndPWD() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String thisLine = "";

			System.out.println("Enter user to access database: ");
			thisLine = br.readLine().trim();
			if (thisLine != null)
				USER = thisLine;

			System.out.println("Enter password to access database: ");
			thisLine = br.readLine().trim();
			if (thisLine != null)
				PASS = thisLine;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getIPOfServer() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("Enter IP address of Server: ");
			String line = "";
			if ((line = br.readLine()) != null)
				IPADRESS = line.trim();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void getServerPort() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("Enter IP address of Server: ");
			String line = "";
			if ((line = br.readLine()) != null)
				PORT = line.trim();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
