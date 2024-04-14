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

    public static Booking addBookingDB(Booking booking) throws SQLException {

        if (booking.getId() != null) {
            updateBookingDB(booking);
            return booking;
        }

        String sql = "INSERT INTO bookingTest (diner_first_name, diner_surname, phone_no, date_reserved, covers, time_reserved, special_booking) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, booking.getDinerFirstName());
            stm.setString(2, booking.getDinerSurname());
            stm.setString(3, booking.getPhoneNo());
            stm.setDate(4, convertToDate(booking.getBookingDate()));
            stm.setInt(5, booking.getCovers());
            stm.setTime(6, convertToTime(booking.getBookingTime()));
            stm.setBoolean(7, booking.getSpecialBooking());
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setId(generatedKeys.getInt(1));  // Set the auto-generated ID back to the booking object
                    System.out.println(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating booking failed, no ID obtained.");
                }
            }
        }
        return booking; // Return the booking object, now including the ID
    }


    public static void updateBookingDB(Booking booking) throws SQLException {
        if (booking.getId() == null) {
            throw new IllegalStateException("Booking ID is null, cannot update the database.");
        }

        String sql = "UPDATE bookingTest SET diner_first_name = ?, diner_surname = ?, phone_no = ?, date_reserved = ?, covers = ?, time_reserved = ?, special_booking = ? WHERE booking_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, booking.getDinerFirstName());
            stm.setString(2, booking.getDinerSurname());
            stm.setString(3, booking.getPhoneNo());
            stm.setDate(4, BookingDataAccess.convertToDate(booking.getBookingDate()));
            stm.setInt(5, booking.getCovers());
            stm.setTime(6, BookingDataAccess.convertToTime(booking.getBookingTime()));
            stm.setBoolean(7, booking.getSpecialBooking());
            stm.setInt(8, booking.getId());  // Set the ID for the WHERE clause

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
                int id = rs.getInt("booking_id");
                String customerFirstName = rs.getString("diner_first_name");
                String customerSurname = rs.getString("diner_Surname");
                String phoneNo = rs.getString("phone_no");
                LocalDate date = rs.getDate("date_reserved").toLocalDate();
                LocalTime time = rs.getTime("time_reserved").toLocalTime();
                int covers = rs.getInt("covers");
                boolean specialBooking = rs.getBoolean("special_booking");

                bookings.add(new Booking(id, customerFirstName, customerSurname, phoneNo, date, covers, time, specialBooking));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        return bookings;
    }

    public static void deleteBookingDB(int bookingId) throws SQLException {
        String sql = "DELETE FROM bookingTest WHERE booking_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, bookingId);
            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting booking failed, no rows affected.");
            }
        }
    }



}
