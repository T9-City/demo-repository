package org.main.booking;

public class testingBooking {
    public static void main(String[] args) {
        BookingSystem testing = new BookingSystem();
        testing.newBooking("Kian Pflitsch", 2, 7789, "18:00");
        testing.newBooking("Olivia Veasey", 4, 1234, "20:00");

        testing.printBookings();
    }
}
