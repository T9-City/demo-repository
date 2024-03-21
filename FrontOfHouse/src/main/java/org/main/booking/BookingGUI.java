package org.main.booking;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BookingGUI extends Application {
    private BookingSystem bookingSystem = new BookingSystem();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Restaurant Booking System");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label nameLabel = new Label("Customer Name:");
        grid.add(nameLabel, 0, 1);

        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 1);

        Label guestsLabel = new Label("Number of Guests:");
        grid.add(guestsLabel, 0, 2);

        TextField guestsTextField = new TextField();
        grid.add(guestsTextField, 1, 2);

        Label phoneLabel = new Label("Phone Number:");
        grid.add(guestsLabel, 0, 2);

        TextField phoneTextField = new TextField();
        grid.add(guestsTextField, 1, 2);

        Label timeLabel = new Label("Booking Time:");
        grid.add(timeLabel, 0, 3);

        TextField timeTextField = new TextField();
        grid.add(timeTextField, 1, 3);

        Button btn = new Button("Book Table");
        grid.add(btn, 1, 4);

        TextArea bookingsArea = new TextArea();
        bookingsArea.setEditable(false);
        grid.add(bookingsArea, 1, 5);

        btn.setOnAction(e -> {
            String name = nameTextField.getText();
            int guests = Integer.parseInt(guestsTextField.getText());
            int phoneNumber = Integer.parseInt(phoneTextField.getText());
            String time = timeTextField.getText();

            bookingSystem.newBooking(name, guests, phoneNumber, time);
            bookingsArea.setText(bookingSystem.getBookingStr());

            // Clear inputs after booking
            nameTextField.clear();
            guestsTextField.clear();
            phoneTextField.clear();
            timeTextField.clear();
        });

        Scene scene = new Scene(grid, 400, 375);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

