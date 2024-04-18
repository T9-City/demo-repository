package org.main.view.booking.createBookings;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.main.core.ViewHandler;
import org.main.core.ViewModelFactory;
import org.main.view.ViewController;
import org.main.view.booking.Booking;
import org.main.database.booking.BookingDataAccess;
import org.main.view.booking.viewBookings.ViewBookingViewModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Controller class for the CreateBooking view model
 * Displays a GUI in which a user can fill in details of a booking
 * User can create a new booking using this GUI
 */
public class CreateBookingViewController extends ViewController {
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
    private ComboBox<Integer> coversComboBox;
    @FXML
    private CheckBox specialComboBox;
    @FXML
    private DatePicker bookingDatePicker;
    @FXML
    private ComboBox<String> hoursComboBox;
    @FXML
    private ComboBox<String> minutesComboBox;
    @FXML
    private Button confirmBookingButton;
    @FXML
    private Button cancelBookingButton;
    private ViewBookingViewModel bookingViewModel;

    /**
     * Initializes the controller calling
     * {@link #initCoversComboBox()}
     * ,
     * {@link #initTimeComboBoxes()}
     * and
     * {@link #setUpActions()}
     */
    @FXML
    public void init(){
        bookingViewModel = ViewModelFactory.getInstance().getBookingViewModel();

        initCoversComboBox();
        initTimeComboBoxes();
        setUpActions();
    }

    /**
     * Method to refresh the window based on if the user checks the special booking checkbox
     * @param special boolean that states if the booking is a special booking
     *                if True then {@link #initSpecialBooking()}
     *                if False then {@link #initCoversComboBox()}, {@link #initTimeComboBoxes()}
     */
    public void refreshWindow(boolean special) {

        customerFirstNameTextField.clear();
        customerSurnameTextField.clear();
        phoneNumberTextField.clear();
        bookingDatePicker.setValue(null);

        coversComboBox.getSelectionModel().selectFirst();
        hoursComboBox.getSelectionModel().selectFirst();
        minutesComboBox.getSelectionModel().selectFirst();

        if (special) {
            initSpecialBooking();
        }
        else {
            initCoversComboBox();
            initTimeComboBoxes();
        }
    }

    /**
     * Method to create listeners for the buttons confirm and cancel
     * Also creates a listener for the special booking checkbox
     */
    private void setUpActions(){
        confirmBookingButton.setOnAction(e -> {
            try {
                onConfirmBooking();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        cancelBookingButton.setOnAction(e -> onCancelBooking());
        specialComboBox.setOnAction(e -> onSpecialBooking());
    }

    /**
     * Event called by listener when the special booking combo box is selected
     * Method calls {@link #refreshWindow(boolean)} based on the special booking checkbox
     */
    private void onSpecialBooking() {
        if (specialComboBox.isSelected()) {
            refreshWindow(true);
        }
        else {
            refreshWindow(false);
        }
    }

    /**
     * Sets up the covers (Number of guests) combo box for a regular booking
     * Populates the combobox
     * Regular booking < 12 guests
     */
    private void initCoversComboBox() {
        coversComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        coversComboBox.getSelectionModel().selectFirst();
    }

    /**
     * Sets up the covers (Number of guests) combo box for a special booking
     * Populates the combobox
     * Special booking = 12 guests
     */
    private void initSpecialBooking() {
        coversComboBox.getItems().addAll(12);
        coversComboBox.getSelectionModel().selectFirst();
    }

    /**
     * Sets up the Hours and Minutes combo boxes so that times can be selected
     * hoursComboBox is populated with hours spanning from 16:00 to 24:00
     * minutesComboBox is populated with minutes in increments of 15 minutes
     */
    private void initTimeComboBoxes() {
        // Populate hours and minutes
        for (int i = 16; i < 24; i++) {
            hoursComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i = i + 15) {
            minutesComboBox.getItems().add(String.format("%02d", i));
        }

        hoursComboBox.getSelectionModel().selectFirst();
        minutesComboBox.getSelectionModel().selectFirst();
    }

    /**
     * Creates a new booking based on the information the user enters
     * Calls {@link org.main.database.booking.BookingDataAccess#addBookingDB(Booking)} to add booking to database
     * Clears all text fields in preparation for the next time a new booking is made
     * @throws SQLException is there is an SQL error
     */
    private void createBooking() throws SQLException {
        String customerFirstName = customerFirstNameTextField.getText();
        String customerSurname = customerSurnameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        boolean specialBooking = specialComboBox.isSelected();

        int covers = coversComboBox.getValue();


        LocalDate date = bookingDatePicker.getValue();
        LocalTime time = LocalTime.of(Integer.parseInt(hoursComboBox.getValue()), Integer.parseInt(minutesComboBox.getValue()));

        Booking newBooking = new Booking(customerFirstName, customerSurname, phoneNumber, date, covers, time, specialBooking);
        bookingViewModel.addBooking(newBooking);
        BookingDataAccess.addBookingDB(newBooking);

        customerFirstNameTextField.clear();
        customerSurnameTextField.clear();
        phoneNumberTextField.clear();
        bookingDatePicker.setValue(null);
        hoursComboBox.getSelectionModel().selectFirst();
        minutesComboBox.getSelectionModel().selectFirst();
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
        alert.setHeaderText("Information");
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Event called on by listener on confirm button
     * Creates the booking by calling the {@link #createBooking()} method
     * Closes the current view and switches to the original view
     * @throws SQLException if there is an SQL error
     */
    @FXML
    private void onConfirmBooking() throws SQLException {
        createBooking();
        ViewHandler.getInstance().openBookingView();
        closeCurrentWindow();
        refreshWindow(false);
    }

    /**
     * Event called on by listener on cancel button
     * Refreshes the window to set it up for the next use
     * Closes the current window and switches back to the original view
     */
    @FXML
    private void onCancelBooking() {
        ViewHandler.getInstance().openBookingView();
        closeCurrentWindow();
        refreshWindow(false);
    }

    /**
     * Method to close the current window
     */
    private void closeCurrentWindow() {
        Stage stage = (Stage) confirmBookingButton.getScene().getWindow();
        stage.close();
    }

}
