package org.main.booking;


import java.time.LocalDateTime;

public class Booking {
    private String customerName;
    private int covers;
    private String phoneNo;
    private LocalDateTime bookingDateTime;
    private Boolean specialBooking;

    // Confirmed / Cancelled / ETC
    //private String status;

    public Booking(String customerName, int covers, String phoneNo, LocalDateTime bookingDateTime, Boolean specialBooking) {
        this.customerName = customerName;
        this.covers = covers;
        this.phoneNo = phoneNo;
        this.bookingDateTime = bookingDateTime;
        //this.status = status;
        this.specialBooking = specialBooking;
    }

    @Override
    public String toString(){
        return "Booking \n " +
                "Name: " + customerName +"\n" +
                "Phone Number: " + phoneNo +"\n" +
                "Time: " + bookingDateTime +"\n" +
                "Covers: " + covers +"\n";

                //"Status: " + status +"\n";
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCovers() {
        return covers;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public Boolean getSpecialBooking() {
        return specialBooking;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCovers(int covers) {
        this.covers = covers;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public void setSpecialBooking(Boolean specialBooking) {
        this.specialBooking = specialBooking;
    }
}
