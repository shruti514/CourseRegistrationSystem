package org.courseregistration.datatests;

import java.sql.Connection;

//This is a singleton class to return connection object
public class DatabaseConnector {
    private static Connection connection = null;

    public static Connection getDBConnection(){
        if(connection==null){
           //establish connection
        }
        return connection;
    }
}
