package org.courseregistration.datatests;


import dnl.utils.text.table.TextTable;


import java.sql.Connection;

public class Queries {

    public static void executeQuery_1(){
        Connection con = DatabaseConnector.getDBConnection();

       String[] columnNames = { "Comlumn1", "Column2", "Column3" };
       Object[][] dataArray = {{"data1","data2","data3"},{"data4","data5","data6"},{"data7","data8","data9"},};

        TextTable tt = new TextTable(columnNames,dataArray);
        tt.printTable();
    }
}
