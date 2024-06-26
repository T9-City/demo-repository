package org.main.view.ViewOrder;

import org.main.database.initialCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewOrderDB {
    /**
     * @return an array list that contains all the tables that have an unpaid order
     * @throws SQLException
     */
    public static ArrayList<Integer> gettableIDs() throws SQLException {
        ArrayList<Integer> tableNums = new ArrayList<>();
        String sql = "SELECT tableNo FROM orders WHERE isPaid = 0 ORDER BY tableNo ASC ";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet values = stm.executeQuery(sql);
            while (values.next()) {
                tableNums.add(Integer.parseInt(values.getObject(1).toString()));
            }
            if(!values.next()){
            return tableNums;
            }
            values.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param tableNo is the table number that is selected from the select order view
     * @return the orderID of the specified table number that has an unpaid order since each table should only have
     * one unpaid order at a time
     * @throws SQLException
     */
    public static Integer getOrderID(Integer tableNo) throws SQLException {
        String sql = "SELECT orderID FROM orders WHERE tableNo = '" + tableNo +"' AND isPaid = 0";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet values = stm.executeQuery(sql);
            while (values.next()) {
                return Integer.parseInt(values.getObject(1).toString());
            }
            values.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets the allergy info for the specified orderID
     * @param orderID is the orderID for the selected order
     * @return
     * @throws SQLException
     */
    public static String getAllergyInfo(Integer orderID) throws SQLException {
        String sql = "SELECT allergy_info FROM orders WHERE orderID = '" + orderID+ "'";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet values = stm.executeQuery(sql);
            while (values.next()) {
                return values.getString("allergy_info");
            }
            values.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param orderID is the orderID from the order selected
     * @return an array list of the dishes in the specfied order
     * @throws SQLException
     */
    public static ArrayList<Integer> getDishID(Integer orderID) throws SQLException {
        ArrayList<Integer> dishIDs = new ArrayList<>();
        String sql = "SELECT dishID FROM dishes WHERE orderID = '" + orderID+ "'";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet values = stm.executeQuery(sql);
            while (values.next()) {
                dishIDs.add(Integer.parseInt(values.getObject(1).toString()));
            }
            if(!values.next()){
                return dishIDs;
            }
            values.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param orderID is the orderID for the selected order
     * @return an array list containg all the quantites of each item linked to the order
     * @throws SQLException
     */
    public static ArrayList<Integer> getQuantity(Integer orderID) throws SQLException {
        ArrayList<Integer> quantities = new ArrayList<>();
        String sql = "SELECT quantity FROM dishes WHERE orderID = '" + orderID+ "'";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet values = stm.executeQuery(sql);
            while (values.next()) {
                quantities.add(Integer.parseInt(values.getObject(1).toString()));
            }
            if(!values.next()){
                return quantities;
            }
            values.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * deletes the dishes of an order for the selected order
     * @param orderID is the orderID for the selected order
     * @throws SQLException
     */
    public static void deleteOrderDishes(Integer orderID) throws SQLException {
        String sql = "DELETE FROM dishes WHERE orderID = '" + orderID+ "'";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delets the order for the selected order
     * @param orderID is the orderID for the selected order
     * @throws SQLException
     */
    public static void deleteOrder(Integer orderID) throws SQLException {
        String sql = "DELETE FROM orders WHERE orderID = '" + orderID+ "'";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * lets you pay for a selected order
     * @param orderID is the orderID for the selected order
     * @throws SQLException
     */
    public static void payOrder(Integer orderID) throws SQLException {
        String sql = "UPDATE orders SET isPaid = 1 WHERE orderID = ?";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,orderID);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
