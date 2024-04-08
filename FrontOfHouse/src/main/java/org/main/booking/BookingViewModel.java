package org.main.booking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BookingViewModel {
    private ObservableList <Booking> bookings;

    public BookingViewModel(){
        bookings = FXCollections.observableArrayList();
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public void cancelBooking(String phoneNo){
        bookings.removeIf(b -> b.getPhoneNo().equals(phoneNo));
    }


}

