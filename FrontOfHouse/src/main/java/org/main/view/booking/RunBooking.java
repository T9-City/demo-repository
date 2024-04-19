package org.main.view.booking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.sql.SQLException;

/**
 * Simple class to test my booking system before AppicationStart was finilized
 * Uses the class {@link Application} from JavaFX to launch the application
 */

public class RunBooking extends Application {

    /**
     * Start method to initialize the JavaFX application
     * Loads the initial booking view from the FXML file
     * Catches any exceptions that may arise from incorrect JavaFX configuration or resource paths
     * @param primaryStage the primary stage for the JavaFX application
     */
    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/view/booking/createBookings/CreateBooking.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Booking System");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to launch the application
     * launch method is from the Application method
     * @param args the command-line arguemnts
     * @throws SQLException if there is an issue with the database
     */
    public static void main(String[] args) throws SQLException {

        launch(args);

    }
}
