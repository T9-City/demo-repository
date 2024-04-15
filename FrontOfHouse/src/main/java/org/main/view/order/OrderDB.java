package org.main.view.order;


/**
 * The name of your class may be different, e.g. initCon
 */
import org.main.database.initialCon;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDB {

    /**
     * Example fields for this test
     */
    private static String a = "Pizza";
    private static int b = 123;


    /**
     * Method to add things to a table in the database
     * String sql = INSERT INTO [table_name (name_of_column, etc)] VALUES (?, ?)
     * Where the number of ? is equal to the number of colums specified
     *
     * try open connection using method in initCon (or whatever it is called)
     * PreparedStatement -> con.prepareStatement(Your sql statement String)
     *
     * stm.setString(index of the ? assigned to column name, variable)
     * stm.setInt(index of the ? assigned to column name, variable)
     *
     * Execute the update
     * @throws SQLException
     */
    public static void addToOrder(float totalPrice,Date date, String allergyInfo,Integer tableNo) throws SQLException {

        String sql = "INSERT INTO ordersTest (total_price,order_Date,allergy_info,tableNo) VALUES (?,?, ?,?)";
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

        String sql = "INSERT INTO ordersTest (orderID,total_price,order_Date,allergy_info,tableNo) VALUES (?,?, ?,?,?)";
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

        String sql = "INSERT INTO ordersTest (orderID,total_price,order_Date,allergy_info,tableNo,isPaid) VALUES (?,?, ?,?,?,1)";
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

        String sql = "INSERT INTO dishesTest (dishID,Quantity,orderID) VALUES (?, ?,?)";
        try (Connection conn = initialCon.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, dishID);
            stm.setInt(2, Quantity);
            stm.setInt(3, order);
            stm.executeUpdate();
        }
    }


    /**
     * Method to add things to a table in the database
     * String sql = INSERT INTO [table_name (name_of_column, etc)] VALUES (?, ?)
     * Where the number of ? is equal to the number of colums specified
     *
     * try open connection using method in initCon (or whatever it is called)
     *      PreparedStatement -> con.prepareStatement
     *      ResultSet -> stm.executeQuery(Your sql statement String)
     *
     *      Iterate through all matching items
     *      while (values.next())
     *          String -> values.getString(column_name)
     *          Int -> values.getInt(column_name)
     *          etc.
     *      close ResultSet
     *      close Statement
     *
     * catch SQLException
     *
     * @throws SQLException
     */
    public static Integer getLastOrderID() throws SQLException {
        String sql = "SELECT orderID FROM ordersTest ORDER BY orderID DESC LIMIT 1";
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