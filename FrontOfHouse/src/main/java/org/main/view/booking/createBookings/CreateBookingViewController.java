package org.main.view.booking.createBookings;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.main.core.ViewModelFactory;
import org.main.view.ViewController;
import org.main.view.booking.Booking;

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
    private CreateBookingViewModel bookingViewModel;


    @FXML
    public void init(){
        //bookingViewModel = ViewModelFactory.getInstance().getBookingViewModel();

        initCoversComboBox();
        initTimeComboBoxes();
        setUpActions();
    }

    private void setUpActions(){
        confirmBookingButton.setOnAction(e -> onAddBooking());
        cancelBookingButton.setOnAction(e -> onCancelBooking());
    }

    private void initCoversComboBox() {
        coversComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        coversComboBox.getSelectionModel().selectFirst();
    }

    private void initTimeComboBoxes() {
        // Populate hours and minutes
        for (int i = 0; i < 24; i++) {
            hoursComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minutesComboBox.getItems().add(String.format("%02d", i));
        }

        hoursComboBox.getSelectionModel().selectFirst();
        minutesComboBox.getSelectionModel().selectFirst();
    }


    private void onAddBooking(){
        int covers = coversComboBox.getValue();
        createBooking(covers);
    }

    private void createBooking(int covers) {
        String customerFirstName = customerFirstNameTextField.getText();
        String customerSurname = customerSurnameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        boolean specialBooking = specialComboBox.isSelected();

        LocalDate date = bookingDatePicker.getValue();
        LocalTime time = LocalTime.of(Integer.parseInt(hoursComboBox.getValue()), Integer.parseInt(minutesComboBox.getValue()));

        Booking newBooking = new Booking(customerFirstName, customerSurname, phoneNumber, date, covers, time, specialBooking);
        bookingViewModel.addBooking(newBooking);

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
    private void onConfirmBooking() {
        int covers = coversComboBox.getValue();
        createBooking(covers);
        closeCurrentWindow();
    }

    @FXML
    private void onCancelBooking() {
        closeCurrentWindow();
    }

    private void closeCurrentWindow() {
        Stage stage = (Stage) confirmBookingButton.getScene().getWindow();
        stage.close();
    }

}
