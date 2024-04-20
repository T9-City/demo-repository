package org.main.view.order;


import org.main.database.initialCon;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDB {



    public static void addToOrder(float totalPrice,Date date, String allergyInfo,Integer tableNo) throws SQLException {

        String sql = "INSERT INTO orders (total_price,order_Date,allergy_info,tableNo) VALUES (?,?, ?,?)";
        try (Connection conn = initialCon.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setFloat(1, totalPrice);
            stm.setDate(2,date);
            stm.setString(3,allergyInfo);
            stm.setInt(4, tableNo);
            stm.executeUpdate();
        }
    }
    public static void addEditedOrder(Integer orderID, float totalPrice,Date date, String allergyInfo,Integer tableNo) throws SQLException {

        String sql = "INSERT INTO orders (orderID,total_price,order_Date,allergy_info,tableNo) VALUES (?,?, ?,?,?)";
        try (Connection conn = initialCon.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1,orderID);
            stm.setFloat(2, totalPrice);
            stm.setDate(3,date);
            stm.setString(4,allergyInfo);
            stm.setInt(5, tableNo);
            stm.executeUpdate();
        }
    }
    public static void addPaidOrder(Integer orderID, float totalPrice,Date date, String allergyInfo,Integer tableNo) throws SQLException {

        String sql = "INSERT INTO orders (orderID,total_price,order_Date,allergy_info,tableNo,isPaid) VALUES (?,?, ?,?,?,1)";
        try (Connection conn = initialCon.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1,orderID);
            stm.setFloat(2, totalPrice);
            stm.setDate(3,date);
            stm.setString(4,allergyInfo);
            stm.setInt(5, tableNo);
            stm.executeUpdate();
        }
    }

    public static void addToDishes(Integer dishID,Integer Quantity,Integer order) throws SQLException {

        String sql = "INSERT INTO dishes (dishID,Quantity,orderID) VALUES (?, ?,?)";
        try (Connection conn = initialCon.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, dishID);
            stm.setInt(2, Quantity);
            stm.setInt(3, order);
            stm.executeUpdate();
        }
    }



    public static Integer getLastOrderID() throws SQLException {
        String sql = "SELECT orderID FROM orders ORDER BY orderID DESC LIMIT 1";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet values = stm.executeQuery(sql);
            while (values.next()) {
                Integer orderID = values.getInt("orderID");
                return orderID;
            }
            values.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}