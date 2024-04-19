package org.main.view.table;

import org.main.database.DBconnection;
import org.main.database.initialCon;

import java.sql.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class TablesDB {
/**
 * Checks if a specific table is available for booking.
 * this queries the database to check the booking status of the table
 * If the "booked" column is 0, the table is available(return true).
 * Else, the method returns false, indicating the table is booked.
 *
 * @param tableNo The number of the table to check availability for.
 * @return boolean This returns treu if table is available,
 * else it returns false if it's booked.
 * @throws  SQLException If there is a Database access error.*/
    public boolean isTableAvailability(int tableNo) throws SQLException{
        String SqlQuery = "SELECT booked FROM tables WHERE tableNo = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SqlQuery)) {
            pstmt.setInt(1, tableNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("booked") == 0;
                }
            }
        }
        return false;
    }
/**
 * Updates the availability status of a specific table in the database.
 * the method uses an SQL statement to update the "booked" status of the table.
 * it uses a prepared statement(pstmt) to prevent SQL injection
 * and to hadnle the insertion of the parameters.
 * The method executes the update and prints the number of affected rows to confirm it works
 *
 * @param tableNo the number of the table to update
 * @[param available the new availablility status to be set, true for available and false for unavailable/booked.
 * @throws SQLException If a database access error happens to occur, or the database connection is close while this runs.*/
    public void setTableAvailability(int tableNo, boolean available) throws SQLException{
        String sqlUpdate  = "UPDATE tables SET booked = ? WHERE tableNo = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)){
            pstmt.setInt(1, available?1:0);
            pstmt.setInt(2,tableNo);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Rows affected: " + affectedRows);
        }


    }
}
