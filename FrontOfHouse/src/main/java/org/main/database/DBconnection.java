package org.main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t19";
    private static String user = "in2033t19_d"; // Database username
    private static String password = "nZGbM-vLn2U"; // Database password

    private static String userAdmin = "in2033t19_a"; // Database username
    private static String passwordAdmin = "d3zcNs5ket0"; // Database password



//    private static Connection con;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userAdmin, passwordAdmin);
    }


//    public static void main(String[] args) {
////        try {
////            // Opening database connection
////            // Load MySQL JDBC Driver
////            // Not always necessary with newer versions of JDBC
////            try {
////                Class.forName("com.mysql.cj.jdbc.Driver");
////            } catch (ClassNotFoundException e) {
////                e.printStackTrace();
////                return;
////            }
////
////            // Establishing a connection
////            con = DriverManager.getConnection(url, user, password);
////            System.out.println("Connection established successfully!");
////
////            // Database interaction code here -----
////
////            // Database interaction code here ^^^^^
////
////        } catch (SQLException sqlEx) {
////            sqlEx.printStackTrace();
////        } finally {
////            // Closing the connection
////            try { con.close(); } catch(SQLException se) { /*can ignore*/ }
////        }
//    }
}