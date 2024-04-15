package org.main.view.booking.editBookings;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Optional;

import javafx.stage.Stage;
import org.main.core.ViewHandler;
import org.main.view.ViewController;
import org.main.view.booking.Booking;
import org.main.database.booking.BookingDataAccess;

public class EditBookingViewController extends ViewController {
    @FXML
    private TextField customerFirstNameTextField;
    @FXML
    private TextField customerSurnameTextField;
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
    @FXML
    private Button confirmBookingButton;
    @FXML
    private Button cancelBookingButton;
    @FXML
    private Button deleteBookingButton;

    private Booking currentBooking;

    @Override
    public void init() {
        super.init();
        setUpActions();
        initTimeComboBoxes();
    }

    private void setUpActions(){
        confirmBookingButton.setOnAction(e -> {
            onConfirmBooking();
        });
        cancelBookingButton.setOnAction(e -> onCancelBooking());
        deleteBookingButton.setOnAction(e -> onDeleteBooking());
    }



    public void setBooking(Booking booking) {
        this.currentBooking = booking;
        updateFields();
    }

    private void updateFields() {
        customerFirstNameTextField.setText(currentBooking.getDinerFirstName());
        customerSurnameTextField.setText(currentBooking.getDinerSurname());
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
        ViewHandler.getInstance().openBookingView();
        closeCurrentWindow();
    }

    @FXML
    private void onConfirmBooking() {
        try {
            GetFromTextFields();
            ViewHandler.getInstance().openBookingView();
            closeCurrentWindow();
        } catch (SQLException ex) {
            showAlert("Database Error", "Failed to update booking: " + ex.getMessage());
        } catch (Exception ex) {
            showAlert("Error", "An error occurred: " + ex.getMessage());
        }
    }

    @FXML
    private void onDeleteBooking() {
        if (currentBooking == null || currentBooking.getId() == null) {
            showAlert("Error", "No booking is selected or booking has no valid ID.");
            return;
        }


        boolean confirm = showConfirmationDialog("Confirm Deletion", "Are you sure you want to delete this booking?");
        if (!confirm) {
            return;
        }


        try {
            BookingDataAccess.deleteBookingDB(currentBooking.getId());
            showAlert("Success", "Booking has been successfully deleted.");
            ViewHandler.getInstance().openBookingView();
            closeCurrentWindow();
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to delete booking: " + e.getMessage());
        }
    }



    private void GetFromTextFields() throws SQLException {
        currentBooking.setDinerFirstName(customerFirstNameTextField.getText());
        currentBooking.setDinerSurname(customerSurnameTextField.getText());
        currentBooking.setPhoneNo(phoneNumberTextField.getText());
        currentBooking.setBookingDate(bookingDatePicker.getValue());
        currentBooking.setBookingTime(LocalTime.of(Integer.parseInt(hoursComboBox.getValue()), Integer.parseInt(minutesComboBox.getValue())));
        currentBooking.setCovers(coversComboBox.getValue());
        currentBooking.setSpecialBooking(specialComboBox.isSelected());

        BookingDataAccess.updateBookingDB(currentBooking);
    }

    private void initTimeComboBoxes() {
        for (int i = 0; i < 24; i++) {
            hoursComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minutesComboBox.getItems().add(String.format("%02d", i));
        }
    }

    private void closeCurrentWindow() {
        Stage stage = (Stage) confirmBookingButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

}
