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

/**
 * @author Mihail Constantin
 * @author Kian Pflitsch
 * @author Muhhamed Siddique
 * @author  Jahir Amir
 * @version 1.0
 * Singleton class that returns an instance of itself in a static manner to classes that might need it.
 * It handles the views and the switching of views
 */
public class ViewHandler {

    private Stage stage;
    private Scene scene;
    private ViewController viewController;
    private static ViewHandler viewHandler;

    private double xOffset;
    private double yOffset;

    /**
     *Custom constructor of ViewHandler. It sets the offset to 0 by default.
     */
    private ViewHandler()
    {
        xOffset = yOffset = 0;
    }

    /**
     * Singleton getInstance() method the returns the instance of this ViewHandler
     * @return this ViewHandler instance.
     */
    public static ViewHandler getInstance()
    {
        if(viewHandler == null) {
            viewHandler = new ViewHandler();
        }
        return viewHandler;
    }

    /**
     * GUI Start method. This method starts the GUI on the login view.
     * This only works if the GUI is started for the first time.
     */
    public void start()
    {
        stage = new Stage();
        if(stage.getScene() == null) stage.initStyle(StageStyle.TRANSPARENT);
        openLoginView();
    }

    /**
     *GUI Method that opens the login view. It also creates the
     * necessary controller if it has never been used before.
     */
    public void openLoginView()
    {
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.LOGIN);
        showView(viewController,null);
    }
    /**
     *GUI Method that opens the table view. It also creates the
     * necessary controller if it has never been used before.
     */
    public void openTableView(){
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.TABLES);
        showView(viewController,null);
    }

    /**
     *GUI Method that opens the booking view. It also creates the
     * necessary controller if it has never been used before.
     */
    public void openBookingView() {
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.SHOW_BOOKINGS);
        showView(viewController,null);
    }

    /**
     *GUI Method that opens the create booking view. It also creates the
     * necessary controller if it has never been used before.
     */
    public void openCreateBookingView() {
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.CREATE_BOOKING);
        showView(viewController,null);
    }

    /**
     *GUI Method that opens the edit booking view. It also creates the
     * necessary controller if it has never been used before.
     * This method also passes the booking object that is edited.
     * @param booking The booking object that needs to be edited
     */
    public void openEditBookingView(Booking booking) {
        if (booking.getId() == null) {
            System.out.println("Warning: Trying to edit a booking without an ID.");
        }
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.EDIT_BOOKINGS);
        EditBookingViewController controller = (EditBookingViewController) ViewControllerFactory.getViewController(Views.EDIT_BOOKINGS);
        controller.setBooking(booking);
        showView(viewController, null);
    }



    /**
     *GUI Method that opens the order view. It also creates the
     * necessary controller if it has never been used before.
     */
    public void openOrderingView(){
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.ORDER);
        showView(viewController, null);
    }
    /**
     *GUI Method that opens the view orders view. It also creates the
     * necessary controller if it has never been used before.
     */
    public void openViewOrders(){
        ViewControllerFactory.clearViews();
        viewController = ViewControllerFactory.getViewController(Views.VIEWORDERS);
        showView(viewController, null);
    }


    /**
     * Method that displays the view; it is used in all of the methods that display
     * specific views in this class.
     * @param viewController The ViewController that is specific to a view
     * @param pane The pane that it needs to be displayed upon
     */
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

    /**
     * Method that minimizes the GUI in the OS application bar
     */
   public void minimize() {
       stage.setIconified(true);
   }

    /**
     * Method that resets the views
     */
   public void resetView() {
        ViewControllerFactory.clearViews();
   }

    /**
     * Method that keeps track of window getting moved around
     * @param root The root where the movement of the window happens
     */
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

    /**
     * Method that closes the view and stops the application
     */
    public void close() {
        stage.close();
    }


}

