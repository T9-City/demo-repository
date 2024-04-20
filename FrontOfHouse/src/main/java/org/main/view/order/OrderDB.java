package org.main.view.order;



import org.main.database.initialCon;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDB {


    /**
     * is used to add an orders details to the database
     * @param totalPrice is the total price of the order
     * @param date is the data the order takes place
     * @param allergyInfo is the allergy info specified by the customer
     * @param tableNo is the table number the order came from
     * @throws SQLException
     */
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

    /**
     * is used to add an edited order to the database
     * @param orderID is the orderID of the order being edited
     * @param totalPrice is the updated total price of the edited order
     * @param date is the date of the order
     * @param allergyInfo is the updated allergy info of the edited order
     * @param tableNo is the table number the order came from
     * @throws SQLException
     */
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

    /**
     * adds the order that was selected to the database and makes it so that the order is now paid for
     * @param orderID is the orderID of the order that needs to be paid for
     * @param totalPrice is the total price of the order
     * @param date is the date of the order
     * @param allergyInfo is the allergy info the order had
     * @param tableNo is the table number that ordered
     * @throws SQLException
     */
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

    /**
     * adds the items that are in an order to the dishes table in the database and links it to the order using orderID
     * @param dishID the dishID of the items ordered
     * @param Quantity the amount of an item ordered
     * @param order is the orderID linked to the order
     * @throws SQLException
     */
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


    /**
     * @return returns the orderID of the last order added to the order table in the database so that
     * it can be used for when adding the dishes of that order to the dishes table
     * @throws SQLException
     */
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