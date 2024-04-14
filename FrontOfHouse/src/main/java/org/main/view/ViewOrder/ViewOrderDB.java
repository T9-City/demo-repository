package org.main.view.ViewOrder;
import org.main.database.initialCon;
import org.main.persistence.Dish;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ViewOrderDB {
    public static ArrayList<Integer> gettableIDs() throws SQLException {
        ArrayList<Integer> tableNums = new ArrayList<>();
        String sql = "SELECT tableNo FROM ordersTest WHERE isPaid = 0 ORDER BY tableNo ASC ";
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
    public static Integer getOrderID(Integer tableNo) throws SQLException {
        String sql = "SELECT orderID FROM ordersTest WHERE tableNo = '" + tableNo +"' AND isPaid = 0";
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

    public static String getAllergyInfo(Integer orderID) throws SQLException {
        String sql = "SELECT allergy_info FROM ordersTest WHERE orderID = '" + orderID+ "'";
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


    public static ArrayList<Integer> getDishID(Integer orderID) throws SQLException {
        ArrayList<Integer> dishIDs = new ArrayList<>();
        String sql = "SELECT dishID FROM dishesTest WHERE orderID = '" + orderID+ "'";
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

    public static ArrayList<Integer> getQuantity(Integer orderID) throws SQLException {
        ArrayList<Integer> quantities = new ArrayList<>();
        String sql = "SELECT quantity FROM dishesTest WHERE orderID = '" + orderID+ "'";
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

    public static void deleteOrderDishes(Integer orderID) throws SQLException {
        String sql = "DELETE FROM dishesTest WHERE orderID = '" + orderID+ "'";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteOrder(Integer orderID) throws SQLException {
        String sql = "DELETE FROM ordersTest WHERE orderID = '" + orderID+ "'";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void payOrder(Integer orderID,Integer tableNo) throws SQLException {
        String sql = "UPDATE ordersTest SET isPaid = 1 WHERE orderID = ? AND tableNo = ?";
        try (Connection con = initialCon.getConnection()) {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,orderID);
            stm.setInt(2,tableNo);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
