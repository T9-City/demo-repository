package org.main.view.booking;

import org.main.database.DBconnection;
import org.main.view.booking.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDataAccess {

    public static Date convertToDate(LocalDate localDate) {
        return (localDate != null) ? Date.valueOf(localDate) : null;
    }

    public static Time convertToTime(LocalTime localTime) {
        return (localTime != null) ? Time.valueOf(localTime) : null;
    }


    public void addBookingDB(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookingTest (diner_first_name, diner_surname, phone_no, date_reserved, covers, time_reserved, special_booking) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, booking.getDinerFirstName());
            stm.setString(2, booking.getDinerSurname());
            stm.setString(3, booking.getPhoneNo());
            stm.setDate(4, convertToDate(booking.getBookingDate()));
            stm.setInt(5, booking.getCovers());
            stm.setTime(6, convertToTime(booking.getBookingTime()));
            stm.setBoolean(7, booking.getSpecialBooking());
            stm.executeUpdate();
        }
    }

    public List<Booking> getAllBookingDB() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookingTest ORDER BY date_reserved ASC, time_reserved ASC ";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String customerFirstName = rs.getString("diner_first_name");
                String customerSurname = rs.getString("diner_Surname");
                String phoneNo = rs.getString("phone_no");
                LocalDate date = rs.getDate("date_reserved").toLocalDate();
                LocalTime time = rs.getTime("time_reserved").toLocalTime();
                int covers = rs.getInt("covers");
                boolean specialBooking = rs.getBoolean("special_booking");

                bookings.add(new Booking(customerFirstName, customerSurname, phoneNo, date, covers, time, specialBooking));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return bookings;
    }

//    public List<Booking> getCertainBookingDB() throws SQLException {
//        List<Booking> bookings = new ArrayList<>();
//        String sql = "SELECT * FROM bookingTest WHERE "; // pass in vars so they can choose how to search
//        try (Connection con = DBconnection.getConnection();
//             PreparedStatement stm = con.prepareStatement(sql);
//             ResultSet rs = stm.executeQuery()) {
//            while (rs.next()) {
//                String customerName = rs.getString("diner_name");
//                String phoneNo = rs.getString("phone_no");
//                LocalDate date = rs.getDate("date_reserved").toLocalDate();
//                LocalTime time = rs.getTime("time_reserved").toLocalTime();
//                int covers = rs.getInt("covers");
//                boolean specialBooking = rs.getBoolean("special_booking");
//
//                bookings.add(new Booking(customerName, phoneNo, date, covers, time, specialBooking));
//            }
//        }
//        catch (SQLException e) {
//            throw e;
//        }
//        return bookings;
//    }

//    public static void addTest() throws SQLException {
//
//        String sql = "INSERT INTO test (food, price) VALUES (?, ?)";
//        try (Connection conn = DBconnection.getConnection();
//             PreparedStatement stm = conn.prepareStatement(sql)) {
//            //stm.setString(1, a);
//            //stm.setInt(2, b);
//            stm.executeUpdate();
//        }
//    }

//    public static void getTest() throws SQLException {
//        String sql = "SELECT booking FROM booking";
//        try (Connection con = DBconnection.getConnection()) {
//            PreparedStatement stm = con.prepareStatement(sql);
//            ResultSet values = stm.executeQuery(sql);
//            while (values.next()) {
//                String f = values.getString("food");
//                System.out.println("Food " + f);
//            }
//            values.close();
//            stm.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


}
