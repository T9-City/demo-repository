package org.main.view.booking.viewBookings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.main.view.booking.Booking;

/**
 * ViewBookingViewModel
 * Redundant class as all actions are completed in view controller
 *
 * View model class for displaying the bookings
 */

public class ViewBookingViewModel {
    private ObservableList<Booking> bookings;

    /**
     * Constructor to construct the ViewBookingViewModel and initialise the list of all the bookings
     */
    public ViewBookingViewModel() {
        this.bookings = FXCollections.observableArrayList();
        loadBookings();
    }

    /**
     * Method to get the bookings from the database
     * Not used, method is completed in ViewBookingViewController
     * @see ViewBookingViewController
     */
    private void loadBookings() {

    }
    /**
     * Method to get the list of bookings
     * Not used, method is completed in ViewBookingViewController
     * @return bookings observable list of bookings
     * @see ViewBookingViewController
     */
    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Method to add bookings to the database
     * Not used, method is completed in ViewBookingViewController
     * @see ViewBookingViewController
     */
    public void addBooking(Booking newBooking) {
    }
}

