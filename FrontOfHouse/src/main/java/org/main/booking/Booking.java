package org.main.booking;

//import java.util.Date;

public class Booking {
    private String customerName;
    private int covers;
    private int phoneNo;

    //Using string for now for simplicity reasons will probaly implement java.util.Date
    private String bookingTime;

    public Booking(String customerName, int covers, int phoneNo, String bookingTime) {
        this.customerName = customerName;
        this.covers = covers;
        this.phoneNo = phoneNo;
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString(){
        return "Booking \n " +
                "Name: " + customerName +"\n" +
                "Phone Number: " + phoneNo +"\n" +
                "Time: " + bookingTime +"\n" +
                "Covers: " + covers +"\n";
    }
}
