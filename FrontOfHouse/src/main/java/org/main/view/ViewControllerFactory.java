package org.main.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import org.main.utils.Views;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mihail Constantin
 * @version 1.0
 * ViewControllerFactory is a factory for controller classes for the GUI
 * It creates them once and stores them in a map for when their use is needed.
 */
public class ViewControllerFactory {

    private static final Map<Views, ViewController> viewControllers = new HashMap<>();

    /**
     * Static method that returns a specific Controller depending on the enum it is provided with
     * @param id Enum that specifies which Controller is needed to the method
     * @return The Controller class that was specified by the Enum
     */
    public static ViewController getViewController(Views id) {
        ViewController viewController = viewControllers.get(id);

        if (viewController == null) {
            switch (id) {
                case LOGIN -> viewController = createNewViewController("/org/main/view/login/LoginView.fxml");
                case ORDER -> viewController = createNewViewController("/org/main/view/order/OrderScreen.fxml");
                case VIEWORDERS -> viewController = createNewViewController("/org/main/view/ViewOrder/ViewOrder.fxml");
                case CREATE_BOOKING -> viewController = createNewViewController("/org/main/view/booking/createBookings/CreateBooking.fxml");
                case SHOW_BOOKINGS -> viewController = createNewViewController("/org/main/view/booking/viewBookings/viewBooking.fxml");
                case EDIT_BOOKINGS -> viewController = createNewViewController("/org/main/view/booking/editBookings/EditBooking.fxml");
                case BOOKING -> viewController = createNewViewController("/org/main/view/booking/BookingView.fxml");
                case TABLES -> viewController = createNewViewController("/org/main/view/table/TableView.fxml");
            }
                viewControllers.put(id, viewController);
            }
            viewController.init();
            return viewController;
        }

    /**
     * Private method used by the static method that creates the new ViewController based
     * on the path provided to the FXML file for the specific Controller.
     * @param path String path that is provided in the getViewController method
     * @return a ViewController instance that was available at the path.
     * @throws IOException if there is nothing present at the location
     */
    private static ViewController createNewViewController(String path) {
        ViewController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewControllerFactory.class.getResource(path));
            Region root = loader.load();
            controller = loader.getController();
            controller.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }


    /**
     * Method that clears the map from any values.
     */
        public static void clearViews () {
            viewControllers.clear();
        }

    }
