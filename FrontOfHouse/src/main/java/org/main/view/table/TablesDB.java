package org.main.view.table;

import org.main.database.initialCon;

import java.sql.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TablesDB {
    public boolean isTableAvailability(int tableNo) throws SQLException{
        String SqlQuery = "SELECT booked FROM tablesTest WHERE tableNo = ?";
        try (Connection conn = initialCon.getConnection();
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
    public void setTableAvailability(int tableNo, boolean available) throws SQLException{
        String sqlUpdate  = "UPDATE tablesTest SET booked = ? WHERE tableNo = ?";
        try (Connection conn = initialCon.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)){
            pstmt.setInt(1, available?1:0);
            pstmt.setInt(2,tableNo);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Rows affected: " + affectedRows);
        }


    }
}
