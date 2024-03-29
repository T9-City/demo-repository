package org.main.booking;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingDetailsViewController {
    private Button convertToSpecialBooking;
    @FXML
    private Button saveButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button cancelBookingButton;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private ComboBox<Integer> coversComboBox;
    @FXML
    private DatePicker bookingDatePicker;
    @FXML
    private TextField bookingTimeField;
    private Booking booking;
    private boolean saveClicked = false;
    private boolean cancelClicked = false;

    public void setBooking(Booking booking) {
        this.booking = booking;

        customerNameTextField.setText(booking.getCustomerName());
        phoneNumberTextField.setText(booking.getPhoneNo());
        coversComboBox.setValue(booking.getCovers());
        bookingDatePicker.setValue(booking.getBookingDateTime().toLocalDate());
        bookingTimeField.setText(booking.getBookingDateTime().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    @FXML
    private void onSave() {
        booking.setCustomerName(customerNameTextField.getText());
        booking.setPhoneNo(phoneNumberTextField.getText());
        booking.setCovers(coversComboBox.getValue());
        booking.setBookingDateTime(LocalDateTime.of(bookingDatePicker.getValue(),
                LocalDateTime.parse(bookingTimeField.getText(), DateTimeFormatter.ofPattern("HH:mm")).toLocalTime()));

        saveClicked = true;
        closeWindow();
    }

    @FXML
    private void onClose(){
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) customerNameTextField.getScene().getWindow();
        stage.close();
    }


    private void onCancelBooking(){
        booking.setStatus("CANCELLED");
        cancelClicked = true;
        closeWindow();
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }
}
