package org.main.view.booking.createBookings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.main.view.booking.Booking;


public class CreateBookingViewModel {
    private ObservableList <Booking> bookings;

    public CreateBookingViewModel(){
        bookings = FXCollections.observableArrayList();
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }



}

