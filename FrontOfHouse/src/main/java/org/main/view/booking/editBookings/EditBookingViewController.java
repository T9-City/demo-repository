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

/**
 * Controller class for the EditBooking view model
 * Displays the selected booking on the GUI in editable fields
 * User can amend bookings using this view
 */

public class EditBookingViewController extends ViewController {
    /**
     * Fields for all the buttons, lists, text fields, and combo boxes that are displayed in the GUI
     * "@FXML" tags so JavaFX knows that these apply to each element in the FXML file
     */
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

    /**
     * Initializes the controller calling
     * {@link #setUpActions()}
     * and
     * {@link #initTimeComboBoxes()}
     */
    @Override
    public void init() {
        super.init();
        setUpActions();
        initTimeComboBoxes();
    }

    /**
     * Method to add listeners to the buttons confirm, cancel, and delete
     */
    private void setUpActions(){
        confirmBookingButton.setOnAction(e -> {
            onConfirmBooking();
        });
        cancelBookingButton.setOnAction(e -> onCancelBooking());
        deleteBookingButton.setOnAction(e -> onDeleteBooking());
    }

    /**
     * Setter for the Booking object
     * @param booking the booking that is passed into this view so that it can be edited
     */
    public void setBooking(Booking booking) {
        this.currentBooking = booking;
        updateFields();
    }

    /**
     * Populating the text fields and combo boxes with the information from the selected booking
     */
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

    /**
     * Event called on by the listener on the cancel button
     * Closes the window and return to original window
     */
    @FXML
    private void onCancelBooking() {
        ViewHandler.getInstance().openBookingView();
        closeCurrentWindow();
    }

    /**
     * Event called on by the listener on the confirm button
     * Trys to call {@link #GetFromTextFields()} which updates the database
     * Closes the current window and switches back to the original view
     */
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

    /**
     * Event called on by the listener of the delete button
     * Checks if a booking is selected, and has an ID
     * Produces a confirmation window to make sure the user is sure they want to delete the booking
     * @see #showConfirmationDialog(String, String)
     * Trys to call {@link org.main.database.booking.BookingDataAccess#deleteBookingDB(int)}
     * Catchs any SQL exception there may be
     */
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


    /**
     * Method gets all information from text fields, combo boxes and date picker
     * Calls method {@link org.main.database.booking.BookingDataAccess#updateBookingDB(Booking)} to update the booking in the database
     * @throws SQLException if there is an SQL error
     */
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

    /**
     * Sets up the Hours and Minutes combo boxes so that times can be selected
     * hoursComboBox is populated with hours spanning from 16:00 to 24:00
     * minutesComboBox is populated with minutes in increments of 15 minutes
     */
    private void initTimeComboBoxes() {
        for (int i = 0; i < 24; i++) {
            hoursComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minutesComboBox.getItems().add(String.format("%02d", i));
        }
    }

    /**
     * Method to close the current window
     */
    private void closeCurrentWindow() {
        Stage stage = (Stage) confirmBookingButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method to show alerts to the user
     * Errors, warnings, and info is displayed using this
     * @param title the title of the alert
     * @param content the body of the alert
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Type of {@link Alert} used as a conformation dialog
     * @param title the title of the alert
     * @param content the body of the alert
     * @return result the button which the user decides to press
     */
    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

}
