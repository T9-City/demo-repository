package org.main.gateway;

import org.main.view.booking.Booking;

import java.util.ArrayList;
import java.util.Date;

public interface FOHBooking {

    /**
     * Gets all bookings from our database.
     * @return A list of all bookings
     */
    ArrayList<Booking> getAllBookings();

    /**
     * Gets a certain booking based on Name or Phone number
     * @param customerName The {@link String} that is filtered for
     * @return The specific booking that contains the name or Phone number
     */

    Booking getBookingNamed(String customerName);
    /**
     * Gets a certain booking based on Name or Phone number
     * @param phoneNo The {@link String} that is filtered for
     * @return The specific booking that contains the name or Phone number
     */

    Booking getBookingPhone(String phoneNo);

    /**
     * Gets a certain booking based on Name or Phone number
     * @param date The {@link Date} that is filtered for
     * @return The specific booking that contains the name or Phone number
     */

    Booking getBookingDate(Date date);

    /**
     * Add booking to the database
     * @return True if it succeeds
     * @return False if it fails
     */
    boolean addBooking();
    //Booking addBooking();


}
