package org.main.view.booking.viewBookings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.main.core.ViewHandler;
import org.main.view.ViewController;
import org.main.view.booking.Booking;
import org.main.database.booking.BookingDataAccess;

/**
 * Controller class for the ViewBooking view model
 * Displays a list of bookings
 * User can navigate to different views via this GUI
 */

public class ViewBookingViewController extends ViewController {

    /**
     * Fields for all the buttons, lists, text fields, and combo boxes that are displayed in the GUI
     * "@FXML" tags so JavaFX knows that these apply to each element in the FXML file
     */
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
    @FXML
    private Button assignTable;
    @FXML
    private Button logOutButton;
    private BookingDataAccess bookingDataAccess;

    private Booking booking;


    /**
     * Constructor to initialize the BookingDataAccess object
     */
    public ViewBookingViewController() {
        bookingDataAccess = new BookingDataAccess();
    }

    /**
     * Method to display the currently selected booking in the GUI
     * Updates the labes for each booking that is selected
     * @param booking the booking that is displayed
     */
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

    /**
     * Sets up the view and the actions in the controller:
     * Booking List
     * Close Button
     * Assign Table Button
     * Booking Selection
     */
    @Override
    public void init() {
        super.init();
        initBookingsList();
        closeButton.setOnAction(event -> closeWindow());
        assignTable.setOnAction(e -> openTableView());
        logOutButton.setOnAction(e -> logOut());

        bookingListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setBooking(newSelection);
            }
        });
    }

    /**
     * Opens the table view
     * @see org.main.view.table.TableViewController
     */
    private void openTableView() {
        ViewHandler.getInstance().openTableView();
    }

    /**
     * Method to log out and return to the main login page
     */
    private void logOut() {
        ViewHandler.getInstance().openLoginView();
        closeWindow();
    }

    /**
     * Closes the window
     */
    private void closeWindow() {
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    /**
     * Initialises the list of bookings
     * Gets all bookings from the database
     * @see BookingDataAccess#getAllBookingDB()
     *
     */
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
                                // booking.getBookingTime().format(DateTimeFormatter.ofPattern("HH:mm")));
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

    /**
     * Event called using a listener when New Booking button is pressed
     * Opens the create booking view
     */
    @FXML
    public void onCreateNewBooking() {
        ViewHandler.getInstance().openCreateBookingView();
    }

    /**
     * Event called using a listener when Edit Booking button is pressed
     * Opens the edit booking view
     * Makes sure a booking is selected before switching windows
     */
    @FXML
    public void onEditBooking() {
        Booking selectedBooking = bookingListView.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            ViewHandler.getInstance().openEditBookingView(selectedBooking);
        } else {
            showAlert("Select Booking", "Please select a booking to edit.");
        }
    }

    /**
     * Method to refresh the window if a change is made locally or on the database
     */
    public void refreshBookingsList() {
        initBookingsList();
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

}
