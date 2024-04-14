package org.main.view.ViewOrder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.main.core.ViewHandler;
import org.main.core.ViewModelFactory;
import org.main.utils.Views;
import org.main.view.ViewController;
import org.main.view.ViewControllerFactory;
import org.main.view.order.OrderingViewController;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewOrderViewController extends ViewController {

    @FXML
    private Label order_view_table_label;

    @FXML
    private Button back_btn;
    @FXML
    private GridPane ordersGridPane;
    private ViewOrderViewModel viewOrderViewModel;
    private ViewHandler viewHandler;

    public boolean isPayOrder() {
        return payOrder;
    }

    public void setPayOrder(boolean payOrder) {
        this.payOrder = payOrder;
    }

    private boolean payOrder;


    public void init() {
        this.viewOrderViewModel = ViewModelFactory.getInstance().getViewOrderViewModel();
        this.viewHandler = ViewHandler.getInstance();
        OrderingViewController orderView = (OrderingViewController) ViewControllerFactory.getViewController(Views.ORDER);
        if(orderView.isPayOrder()) {
            System.out.println("no");
        }
        try {
            createTableBtns();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void return_to_OrderScreen(ActionEvent event){
        ViewHandler.getInstance().openOrderingView();
    }

    public Label getOrder_view_table_label() {
        return order_view_table_label;
    }

    public void createTableBtns() throws SQLException {
        int row = 0;
        int column = 0;
        ArrayList < Integer> tableNums = new ArrayList<>();
        tableNums = ViewOrderDB.gettableIDs();


        ordersGridPane.getRowConstraints().clear();
        ordersGridPane.getColumnConstraints().clear();

        for (int i = 0; i < tableNums.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("tableBtns.fxml"));
                AnchorPane pane = loader.load();
                tableBtnsController tableBtnsController = loader.getController();
                Integer tableNo = tableNums.get(i);
                tableBtnsController.getTableNoBtn().setText(String.valueOf(tableNo));
                tableBtnsController.setTableNo(tableNums.get(i));
                tableBtnsController.getTableNoBtn().setText("Table "+String.valueOf(tableNo));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                ordersGridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(25));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}