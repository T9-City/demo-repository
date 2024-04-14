package org.main.core;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.main.utils.Views;
import org.main.view.ViewController;
import org.main.view.ViewControllerFactory;
import org.main.view.booking.Booking;
import org.main.view.booking.editBookings.EditBookingViewController;

public class ViewHandler {

    private Stage stage;
    private Scene scene;
    private ViewController viewController;
    private static ViewHandler viewHandler;

    private double xOffset;
    private double yOffset;

    private ViewHandler()
    {
        xOffset = yOffset = 0;
    }

    public static ViewHandler getInstance()
    {
        if(viewHandler == null) {
            viewHandler = new ViewHandler();
        }
        return viewHandler;
    }

    public void start()
    {
        stage = new Stage();
        if(stage.getScene() == null) stage.initStyle(StageStyle.TRANSPARENT);
        openLoginView();

    }

    public void openLoginView()
    {
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.LOGIN);
        showView(viewController,null);
    }

    public void openBookingView() {
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.SHOW_BOOKINGS);
        showView(viewController,null);
    }

    public void openCreateBookingView() {
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.CREATE_BOOKING);
        showView(viewController,null);
    }

    public void openEditBookingView(Booking booking) {
        if (booking.getId() == null) {
            System.out.println("Warning: Trying to edit a booking without an ID.");
        }
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.EDIT_BOOKINGS);
        EditBookingViewController controller = (EditBookingViewController) ViewControllerFactory.getViewController(Views.EDIT_BOOKINGS);
        controller.setBooking(booking);
        showView(viewController,null);


    }


   private void showView(ViewController viewController, Pane pane)
   {
       Platform.runLater(() ->{
           if(pane == null)
           {
               moveWindowsEvents(viewController.getRoot());
               if(scene == null){
                   scene = new Scene(viewController.getRoot());
               }
               scene.setRoot(viewController.getRoot());

           if (stage == null) {
               stage = new Stage();
       }

           stage.setScene(scene);
           stage.show();
           }else {
               pane.getChildren().clear();
               pane.getChildren().setAll(viewController.getRoot());
           }
       });
   }

   public void minimize() {
       stage.setIconified(true);
   }

   public void resetView() {
        ViewControllerFactory.clearViews();
   }

   private void moveWindowsEvents(Parent root)
   {
       root.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               xOffset = event.getSceneX();
               yOffset = event.getSceneY();
           }
       });
       root.setOnMouseDragged(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               stage.setX(event.getScreenX() - xOffset);
               stage.setY(event.getScreenY() - yOffset);
           }
       });
   }


    public void close() {
        stage.close();
    }
}

