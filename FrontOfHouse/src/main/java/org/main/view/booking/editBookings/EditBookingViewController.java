package org.main.view.booking.editBookings;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalTime;
import org.main.view.ViewController;
import org.main.view.booking.Booking;

public class EditBookingViewController extends ViewController {
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private DatePicker bookingDatePicker;
    @FXML
    private ComboBox<String> hoursComboBox;
    @FXML
    private ComboBox<String> minutesComboBox;
    @FXML
    private ComboBox<Integer> coversComboBox;
    @FXML
    private CheckBox specialComboBox;

    private Booking currentBooking;

    @Override
    public void init() {
        super.init();

        initTimeComboBoxes();
    }

    public void setBooking(Booking booking) {
        this.currentBooking = booking;
        updateFields();
    }

    private void updateFields() {
        customerNameTextField.setText(currentBooking.getDinerFirstName() + currentBooking.getDinerSurname());
        phoneNumberTextField.setText(currentBooking.getPhoneNo());
        bookingDatePicker.setValue(currentBooking.getBookingDate());

        LocalTime time = currentBooking.getBookingTime();
        hoursComboBox.setValue(String.format("%02d", time.getHour()));
        minutesComboBox.setValue(String.format("%02d", time.getMinute()));

        coversComboBox.setValue(currentBooking.getCovers());
        specialComboBox.setSelected(currentBooking.getSpecialBooking());
    }

    @FXML
    private void onCancelBooking() {
        // Handle cancel action
    }

    @FXML
    private void onConfirmBooking() {
        // Handle confirm action
    }

    private void initTimeComboBoxes() {
        for (int i = 0; i < 24; i++) {
            hoursComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minutesComboBox.getItems().add(String.format("%02d", i));
        }
    }
}
