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

import java.io.IOException;

public class ItemViewController  extends ViewController {
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


    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        item_amountSpnr.setValueFactory(spin);
    }

    @FXML
    public void addToTable(MouseEvent event) throws IOException {
        quantity = item_amountSpnr.getValue();
        if (quantity>0 && ItemData.isAvailable()) {

            OrderingViewController orderView = (OrderingViewController) ViewControllerFactory.getViewController(Views.ORDER);
            orderView.updateTable(ItemData.getName(), quantity, ItemData.getPrice() * quantity,ItemData.getDishID());

        }
    }


}
