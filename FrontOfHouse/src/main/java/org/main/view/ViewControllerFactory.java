package org.main.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import org.main.utils.Views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ViewControllerFactory {

    private static final Map<Views, ViewController> viewControllers = new HashMap<>();

    public static ViewController getViewController(Views id) {
        ViewController viewController = viewControllers.get(id);

        if (viewController == null) {
            switch (id) {
                case LOGIN:
                    viewController = createNewViewController("login/LoginView.fxml");
                    break;
                case BOOKING:
                    viewController = createNewViewController("booking/BookingView.fxml");

            }
            viewControllers.put(id, viewController);
        }
        viewController.init();
        return viewController;
    }

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

    public static void clearViews() {
        viewControllers.clear();
    }


}
