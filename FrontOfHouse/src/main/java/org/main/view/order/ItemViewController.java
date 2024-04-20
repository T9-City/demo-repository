package org.main.view.order;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.main.persistence.Dish;
import org.main.utils.Views;
import org.main.view.ViewController;
import org.main.view.ViewControllerFactory;
import org.main.view.booking.Booking;

import java.io.IOException;

/**
 * The ItemViewController class controls the interaction between the user and item data within the order view.
 * It handles the display of item details and allows users to add items to an order.
 */
public class ItemViewController  extends ViewController {
    /**
     * Fields for all the buttons, panes, labels and spinners that are displayed in the GUI
     * "@FXML" tags so JavaFX knows that these apply to each element in the FXML file
     */
    @FXML
    private AnchorPane item_Form;
    @FXML
    private Label item_name;
    @FXML
    private Label item_Price;
    @FXML
    private Label item_Availability;
    @FXML
    private Spinner<Integer> item_amountSpnr;
    @FXML
    private Button item_AddBtn;

    private Dish ItemData;

    private SpinnerValueFactory<Integer> spin;
    private int quantity;


    /**
     * Sets the item data and updates the view to display the item's details.
     * @param dishData The data of the dish to be displayed.
     */
    public void setItemData(Dish dishData){
        this.ItemData = dishData;
        setQuantity();
        item_name.setText(ItemData.getName());
        item_Price.setText(Integer.toString(ItemData.getPrice()));
        if(!ItemData.isAvailable()){
            item_Availability.setText("Not Available");
            item_Availability.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Initializes the quantity spinner with values from 0 to 100.
     */
    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        item_amountSpnr.setValueFactory(spin);
    }
    /**
     * when the user clicks the add button on an item this event will happen.
     * if a correct quantity is entered and the dish is available
     * Call{@link org.main.view.order.OrderingViewController#updateTable(String, Integer, Integer, Integer)
     * to add the items detail and quantity to the ordering table}
     * @param event The mouse event triggering the action.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void addToTable(MouseEvent event) throws IOException {
        quantity = item_amountSpnr.getValue();
        if (quantity>0 && ItemData.isAvailable()) {

            OrderingViewController orderView = (OrderingViewController) ViewControllerFactory.getViewController(Views.ORDER);
            orderView.updateTable(ItemData.getName(), quantity, ItemData.getPrice() * quantity,ItemData.getDishID());

        }
    }


}
