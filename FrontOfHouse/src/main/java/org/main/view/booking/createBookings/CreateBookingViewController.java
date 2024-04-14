package org.main.view.booking.createBookings;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.main.core.ViewHandler;
import org.main.core.ViewModelFactory;
import org.main.view.ViewController;
import org.main.view.booking.Booking;
import org.main.view.booking.BookingDataAccess;
import org.main.view.booking.viewBookings.ViewBookingViewModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class CreateBookingViewController extends ViewController {
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


    @FXML
    public void init(){
        bookingViewModel = ViewModelFactory.getInstance().getBookingViewModel();

        initCoversComboBox();
        //initSpecialBooking();
        initTimeComboBoxes();
        setUpActions();
    }

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

    private void onSpecialBooking() {
        if (specialComboBox.isSelected()) {
            refreshWindow(true);
        }
        else {
            refreshWindow(false);
        }
    }

    private void initCoversComboBox() {
        coversComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        coversComboBox.getSelectionModel().selectFirst();
    }

    private void initSpecialBooking() {
        coversComboBox.getItems().addAll(12);
        coversComboBox.getSelectionModel().selectFirst();
    }

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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Information");
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void onConfirmBooking() throws SQLException {
        createBooking();
        ViewHandler.getInstance().openBookingView();
        closeCurrentWindow();
        refreshWindow(false);
    }

    @FXML
    private void onCancelBooking() {
        ViewHandler.getInstance().openBookingView();
        closeCurrentWindow();
        refreshWindow(false);
    }

    private void closeCurrentWindow() {
        Stage stage = (Stage) confirmBookingButton.getScene().getWindow();
        stage.close();
    }

}
