package org.main.view.ViewOrder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.main.core.ViewHandler;
import org.main.persistence.Dish;
import org.main.utils.Views;
import org.main.view.ViewController;
import org.main.view.ViewControllerFactory;
import org.main.view.order.OrderingViewController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class tableBtnsController extends ViewController {


    @FXML
    private Button tableNoBtn;

    private Integer tableNo;
    private Integer orderID;


    @FXML
    public void getTableOrder(ActionEvent event) throws SQLException, IOException {
       // System.out.println(ViewOrderDB.getOrderID(tableNo));
        ViewHandler.getInstance().openOrderingView();
        OrderingViewController orderView = (OrderingViewController) ViewControllerFactory.getViewController(Views.ORDER);
        ViewOrderViewController viewOrderViewController = (ViewOrderViewController) ViewControllerFactory.getViewController(Views.VIEWORDERS);

        orderID = ViewOrderDB.getOrderID(tableNo);
        orderView.setPayOrderID(orderID);
        if(viewOrderViewController.isPayOrderboolean()) {
            System.out.println("hehjfhjaefjh");
            ViewOrderDB.payOrder(orderID);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully paid for an order");
            alert.showAndWait();

            orderView.setPayOrder(false);
        }
        else {
            ArrayList<Integer> dishIds = ViewOrderDB.getDishID(orderID);
            ArrayList<Integer> quantites = ViewOrderDB.getQuantity(orderID);
            ArrayList<Dish> menu = orderView.returnMenu();
            for (int i = 0; i < dishIds.size(); i++) {
                for (int x = 0; x < menu.size(); x++) {
                    if (menu.get(x).getDishID() == dishIds.get(i)) {
                        String dishName = menu.get(x).getName();
                        Integer dishPrice = menu.get(x).getPrice();
                        orderView.updateTable(dishName, quantites.get(i), dishPrice * quantites.get(i), dishIds.get(i));
                    }
                }
            }
            orderView.setMenu_table_num_Text(Integer.toString(tableNo));
            orderView.setMenu_allergy_info_Text(ViewOrderDB.getAllergyInfo(orderID));
            ViewOrderDB.deleteOrderDishes(orderID);
            ViewOrderDB.deleteOrder(orderID);
            orderView.setEditingOrder(true);
            orderView.setOrderID(orderID);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("You may now edit and resend an order, or pay for the order");
            alert.showAndWait();
        }

    }

    public void payTable() throws SQLException {
        ViewHandler.getInstance().openOrderingView();
        OrderingViewController orderView = (OrderingViewController) ViewControllerFactory.getViewController(Views.ORDER);
        orderID = ViewOrderDB.getOrderID(tableNo);
        System.out.println("hehjfhjaefjh");
        ViewOrderDB.payOrder(orderID);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("You have successfully paid for an order");
        alert.showAndWait();

        orderView.setPayOrder(false);
    }

    public Integer getTableNo() {
        return tableNo;
    }

    public void setTableNo(Integer tableNo) {
        this.tableNo = tableNo;
    }

    public Button getTableNoBtn() {
        return tableNoBtn;
    }
}
