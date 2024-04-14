package org.main.view.booking.viewBookings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.main.view.booking.Booking;

public class ViewBookingViewModel {
    private ObservableList<Booking> bookings;

    public ViewBookingViewModel() {
        this.bookings = FXCollections.observableArrayList();
        loadBookings();
    }

    private void loadBookings() {

    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking newBooking) {
    }
}

