package org.main.booking;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private List<Booking> bookings;

    public BookingSystem() {
        bookings = new ArrayList<>();
    }

    public boolean newBooking(String customerName, int covers, int phoneNo, String bookingTime){
        try {
            Booking newBooking = new Booking(customerName, covers, phoneNo, bookingTime);
            bookings.add(newBooking);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void printBookings() {
        for (Booking booking : bookings){
            System.out.println(booking);
        }
    }

    public String getBookingStr() {
        StringBuilder sb = new StringBuilder();
        for (Booking booking : bookings) {
            sb.append(booking.toString()).append("\n");
        }
        return sb.toString();
    }
}

