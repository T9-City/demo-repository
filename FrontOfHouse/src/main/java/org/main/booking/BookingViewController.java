package org.main.booking;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.main.core.ViewModelFactory;
import org.main.view.ViewController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingViewController extends ViewController {
    @FXML
    private ListView<Booking> bookingListView;
    @FXML
    private Button addBookingButton;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private CheckBox specialBookingCheckBox;
    @FXML
    private ComboBox<Integer> coversTextField;
    @FXML
    private DatePicker bookingDatePicker;
    @FXML
    private TextField bookingTimeField;



    private BookingViewModel bookingViewModel;

    @Override
    public void init(){
        bookingViewModel = ViewModelFactory.getInstance().getBookingViewModel();
        bookingListView.setItems(bookingViewModel.getBookings());

        setUpActions();
    }

    private void setUpActions(){
        addBookingButton.setOnAction(e -> onAddBooking());
        bookingListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                onBookingSelected(newSelection);
            }
        });
    }

    private void onBookingSelected(Booking booking){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingDetails.fxml"));
            Parent detailedView = loader.load();

            BookingDetailsViewController controller = loader.getController();
            controller.setBooking(booking);

            Stage windowStage = new Stage();
            windowStage.setTitle("Modify booking");
            windowStage.initModality(Modality.WINDOW_MODAL);
            windowStage.initOwner(bookingListView.getScene().getWindow());
            windowStage.setScene(new Scene(detailedView));
            windowStage.showAndWait();

            if (controller.isSaveClicked()){
                //This only refreshes the view
                bookingListView.refresh();

                //Need to implement a method where it updates the Database
            }
        }
        catch (IOException e){
            e.printStackTrace();
            showAlert("Error", "Booking details failed to open.");
        }
    }


    private void onAddBooking(){
        String customerName = customerNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        //--- Right now before I implement the combobox and checkbox ---
        //ComboBox covers = null;
        int covers = 1;

        Boolean specialBooking = false;
        //CheckBox specialBooking = null;
        //^^^ Right now before I implement the combobox and checkbox ^^^

        LocalDateTime dateTime = LocalDateTime.of(bookingDatePicker.getValue(),
                LocalDateTime.parse(bookingTimeField.getText()).toLocalTime());

        //bookingTimeField.setText(booking.getBookingDateTime().format(DateTimeFormatter.ofPattern("HH:mm")));

        //String status = null;

        Booking newBooking = new Booking(customerName, covers, phoneNumber, dateTime, specialBooking);

        customerNameTextField.clear();
        phoneNumberTextField.clear();
        bookingDatePicker.setValue(null);
        bookingTimeField.clear();

        //Need to add covers and special booking later
    }

    private void onCancelBooking(){
        Booking selectedBooking = bookingListView.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            bookingViewModel.cancelBooking(selectedBooking.getPhoneNo());
        }
        else {
            showAlert("No booking selected", "Select a booking to cancel.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Information");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
