package org.main.view.booking.viewBookings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.main.core.ViewHandler;
import org.main.view.ViewController;
import org.main.view.booking.Booking;
import org.main.view.booking.BookingDataAccess;

import java.time.format.DateTimeFormatter;

public class ViewBookingViewController extends ViewController {
    @FXML
    private ListView<Booking> bookingListView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label coversLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label specialLabel;
    @FXML
    private Button newBookingButton;
    @FXML
    private Button editBookingButton;
    @FXML
    private Button closeButton;
    private BookingDataAccess bookingDataAccess;

    private Booking booking;

    public ViewBookingViewController() {
        bookingDataAccess = new BookingDataAccess();
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
        nameLabel.setText(booking.getDinerFirstName() + " " + booking.getDinerSurname());
        phoneLabel.setText(booking.getPhoneNo());
        dateLabel.setText(booking.getBookingDate().toString());
        coversLabel.setText(Integer.toString(booking.getCovers()));
        //timeLabel.setText(booking.getBookingTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        timeLabel.setText(booking.getBookingTime().toString());
        specialLabel.setText(booking.getSpecialBooking() ? "Yes" : "No");
    }

    @Override
    public void init() {
        super.init();
        initBookingsList();
        closeButton.setOnAction(event -> closeWindow());

        bookingListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setBooking(newSelection);
            }
        });
    }

    private void closeWindow() {
        ((Stage) closeButton.getScene().getWindow()).close();
    }

//    public void initBookingsList() {
//        try {
//            ObservableList<Booking> bookings = FXCollections.observableArrayList(bookingDataAccess.getAllBookingDB());
//            bookingListView.setItems(bookings);
//        }
//        catch (Exception e) {
//            showAlert("Database Error", "Error retrieving bookings from the database: " + e);
//        }
//    }

    public void initBookingsList() {
        try {
            ObservableList<Booking> bookings = FXCollections.observableArrayList(bookingDataAccess.getAllBookingDB());

            // Set a cell factory to display only the name and time of each booking
            bookingListView.setCellFactory(lv -> new ListCell<Booking>() {
                @Override
                protected void updateItem(Booking booking, boolean empty) {
                    super.updateItem(booking, empty);
                    if (empty || booking == null) {
                        setText(null);
                    } else {
                        // Assuming that you have getter methods in Booking class
                        String displayText = String.format("%s %s\n%s\n%s",
                                booking.getDinerFirstName(),
                                booking.getDinerSurname(),
                                booking.getBookingDate(),
//                                booking.getBookingTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                                booking.getBookingTime());
                        setText(displayText);
                    }
                }
            });

            bookingListView.setItems(bookings);
        } catch (Exception e) {
            showAlert("Database Error", "Error retrieving bookings from the database: " + e.getMessage());
        }
    }


    @FXML
    public void onCreateNewBooking() {
        ViewHandler.getInstance().openCreateBookingView();
    }

    @FXML
    public void onEditBooking() {
        if (bookingListView.getSelectionModel().getSelectedItem() != null) {
            ViewHandler.getInstance().openEditBookingView();
        } else {
            showAlert("Select Booking", "Please select a booking to edit.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
