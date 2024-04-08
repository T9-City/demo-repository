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

public class BookingViewController extends ViewController {
    @FXML
    private ListView<Booking> bookingListView;
    @FXML
    private Button addBookingButton;
    @FXML
    private Button addSpecialBookingButton;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private ComboBox<Integer> coversComboBox;
    @FXML
    private CheckBox specialComboBox;
    @FXML
    private DatePicker bookingDatePicker;
    @FXML
    private TextField bookingTimeField;

    private BookingViewModel bookingViewModel;

    @Override
    public void init(){
        bookingViewModel = ViewModelFactory.getInstance().getBookingViewModel();
        bookingListView.setItems(bookingViewModel.getBookings());

        initCoversComboBox();
        setUpActions();
    }

    private void setUpActions(){
        addBookingButton.setOnAction(e -> onAddBooking());
        //addSpecialBookingButton.setOnAction(e -> onAddSpecialBooking());
        bookingListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                onBookingSelected(newSelection);
            }
        });
    }

    private void initCoversComboBox() {
        // Gotta check how many a special booking is
        coversComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        coversComboBox.getSelectionModel().selectFirst();

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
        int covers = coversComboBox.getValue();
        createBooking(covers);
    }

//    private void onAddSpecialBooking() {
//        int covers = specialComboBox.getValue();
//        createBooking(covers);
//    }

    private void createBooking(int covers){
        String status = "CONFIRMED";
        String customerName = customerNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        boolean specialBooking = true;

        LocalDateTime dateTime = LocalDateTime.of(bookingDatePicker.getValue(),
                LocalDateTime.parse(bookingTimeField.getText()).toLocalTime());

        Booking newBooking = new Booking(customerName, covers, phoneNumber, dateTime, specialBooking, status);

        bookingViewModel.addBooking(newBooking);

        customerNameTextField.clear();
        phoneNumberTextField.clear();
        bookingDatePicker.setValue(null);
        bookingTimeField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Information");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
