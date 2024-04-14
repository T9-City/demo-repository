package org.main.view.booking;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {

    private Integer id;
    private String dinerFirstName;
    private String dinerSurname;
    private String phoneNo;
    private LocalDate bookingDate;
    private int covers;
    private LocalTime bookingTime;
    private Boolean specialBooking;

    public Booking(String dinerFirstName, String dinerSurname, String phoneNo, LocalDate bookingDate, int covers, LocalTime bookingTime, Boolean specialBooking) {
        this.dinerFirstName = dinerFirstName;
        this.dinerSurname = dinerSurname;
        this.phoneNo = phoneNo;
        this.bookingDate = bookingDate;
        this.covers = covers;
        this.bookingTime = bookingTime;
        this.specialBooking = specialBooking;
    }

    public Booking(int id, String dinerFirstName, String dinerSurname, String phoneNo, LocalDate bookingDate, int covers, LocalTime bookingTime, boolean specialBooking) {
        this(dinerFirstName, dinerSurname, phoneNo, bookingDate, covers, bookingTime, specialBooking);
        this.id = id;
    }

    @Override
    public String toString(){
        return "Booking \n " +
                "First Name: " + dinerFirstName + "\n" +
                "Surname: " + dinerSurname + "\n" +
                "Phone Number: " + phoneNo + "\n" +
                "Date: " + bookingDate + "\n" +
                "Time: " + bookingTime + "\n" +
                "Covers: " + covers + "\n" +
                "Special: " + (specialBooking ? "Yes" : "No");

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDinerFirstName() {
        return dinerFirstName;
    }

    public void setDinerFirstName(String dinerFirstName) {
        this.dinerFirstName = dinerFirstName;
    }

    public String getDinerSurname() {
        return dinerSurname;
    }

    public void setDinerSurname(String dinerSurname) {
        this.dinerSurname = dinerSurname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }


    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getCovers() {
        return covers;
    }

    public void setCovers(int covers) {
        this.covers = covers;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Boolean getSpecialBooking() {
        return specialBooking;
    }

    public void setSpecialBooking(Boolean specialBooking) {
        this.specialBooking = specialBooking;
    }
}
