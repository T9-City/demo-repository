package org.main.booking;

import java.time.LocalDateTime;

public class Booking {
    private String customerName;
    private int covers;
    private String phoneNo;
    private LocalDateTime bookingDateTime;
    private Boolean specialBooking;
    private String status;

    public Booking(String customerName, int covers, String phoneNo, LocalDateTime bookingDateTime,
                   Boolean specialBooking, String status) {
        this.customerName = customerName;
        this.covers = covers;
        this.phoneNo = phoneNo;
        this.bookingDateTime = bookingDateTime;
        this.status = status;
        this.specialBooking = specialBooking;
    }

    @Override
    public String toString(){
        return "Booking \n " +
                "Status: " + status +"\n" +
                "Name: " + customerName +"\n" +
                "Phone Number: " + phoneNo +"\n" +
                "Time: " + bookingDateTime +"\n" +
                "Covers: " + covers +"\n";

    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCovers() {
        return covers;
    }
    public void setCovers(int covers) {
        this.covers = covers;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }
    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public Boolean getSpecialBooking() {
        return specialBooking;
    }
    public void setSpecialBooking(Boolean specialBooking) {
        this.specialBooking = specialBooking;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
