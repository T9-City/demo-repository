package org.main.view.order;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.main.core.ViewHandler;
import org.main.core.ViewModelFactory;

import org.main.persistence.Dish;
import org.main.utils.Views;
import org.main.view.ViewController;
import org.main.view.ViewControllerFactory;
import org.main.view.ViewOrder.ViewOrderDB;
import org.main.view.ViewOrder.ViewOrderViewController;
import org.main.view.booking.Booking;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
/**
 * The OrderingViewController class manages the order creation process.
 * It allows users to create orders, update the order tables with order information, and manage order details.
 * allows the user to send orders, pay orders
 */

public class OrderingViewController extends ViewController {
    public Button Pay_Button;
    /**
     * Fields for all the buttons, panes, labels and tables that are displayed in the GUI
     * "@FXML" tags so JavaFX knows that these apply to each element in the FXML file
     */
    @FXML
    private Button editingOrders_Btn;
    @FXML
    private AnchorPane menu_form;

    @FXML
    private TableView<Order> menu_tableView;
    @FXML
    private TableColumn<Order, String> menu_col_product;
    @FXML
    private TableColumn<Order, Integer> menu_col_quantity;
    @FXML
    private TableColumn<Order, Integer> menu_col_DishID;
    @FXML
    private TableColumn<Order, Integer> menu_col_price;
    @FXML
    private TextField menu_table_num;
    @FXML
    private Button menu_sendOrder_Btn;
    @FXML
    private Button menu_Remove_Btn;

    @FXML
    private Button logOutButton;


    @FXML
    private TextField menu_allergy_info;
    @FXML
    private Label menu_total;
    @FXML
    private ScrollPane menu_scroll;
    @FXML
    private GridPane menu_grid;
    @FXML
    private OrderingViewModel orderingViewModel;

    private ArrayList<Dish> menu;
    private ViewHandler viewHandler;

    private float total;

    private boolean editingOrder = false;

    private boolean payOrderbool;
    private Integer orderID;

    private Integer payOrderID;


    public Integer getPayOrderID() {
        return payOrderID;
    }

    /**
     * @param payOrderID sets the ID for the order that is being paid
     */
    public void setPayOrderID(Integer payOrderID) {
        this.payOrderID = payOrderID;
    }

    /**
     * Initializes the OrderingViewController by setting up the order
     * view model, view handler, and displaying menu items and setting up the table
     */
    public void init() {
        this.orderingViewModel = ViewModelFactory.getInstance().getOrderViewModel();
        this.viewHandler = ViewHandler.getInstance();
        logOutButton.setOnAction(e -> logOut());
        setMenu_tableView();
        menuDisplayCard();

    }

    /**
     * Method to log out and return to the main login page
     */
    private void logOut() {
        ViewHandler.getInstance().openLoginView();
    }

    public Integer getOrderID() {
        return orderID;
    }

    /**
     * @param orderID order ID is passed to set the orderID for the order selected
     */
    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    /**
     * @param tablenum is passed to set the table number after an order is selected
     */
    public void setMenu_table_num_Text(String tablenum) {
        menu_table_num.setText(tablenum);
    }

    /**
     * @param allergy_info is passed to set the allergy info after an order is selected
     */
    public void setMenu_allergy_info_Text(String allergy_info) {
        menu_allergy_info.setText(allergy_info);
    }
    public boolean isEditingOrder() {
        return editingOrder;
    }

    /**
     * @param editingOrder is used to signify an order is being edited
     */
    public void setEditingOrder(boolean editingOrder) {
        this.editingOrder = editingOrder;
    }

    /**
     * @return the menu for the items
     */
    public ArrayList<Dish> returnMenu() {
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("meat");
        ArrayList<String> allergens = new ArrayList<String>();
        ingredients.add("eggs");

        Dish dish1 = new Dish(1, 10, "burger", "tasty", ingredients, allergens, true);
        Dish dish2 = new Dish(2, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish3 = new Dish(3, 15, "chips", "very tasty", ingredients, allergens, false);
        Dish dish4 = new Dish(4, 15, "Pepsi", "very tasty", ingredients, allergens, true);
        Dish dish5 = new Dish(5, 10, "Wings", "tasty", ingredients, allergens, true);
        Dish dish6 = new Dish(6, 15, "Rice", "very tasty", ingredients, allergens, true);
        Dish dish7 = new Dish(7, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish8 = new Dish(8, 15, "pizza", "very tasty", ingredients, allergens, true);

        Dish dish9 = new Dish(9, 10, "burger", "tasty", ingredients, allergens, true);
        Dish dish10 = new Dish(10, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish11 = new Dish(11, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish12 = new Dish(12, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish13 = new Dish(13, 10, "burger", "tasty", ingredients, allergens, true);
        Dish dish14 = new Dish(14, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish15 = new Dish(15, 15, "pizza", "very tasty", ingredients, allergens, true);
        Dish dish16 = new Dish(16, 15, "pizza", "very tasty", ingredients, allergens, true);

        menu = new ArrayList<>();
        menu.add(dish1);
        menu.add(dish2);
        menu.add(dish3);
        menu.add(dish4);
        menu.add(dish5);
        menu.add(dish6);
        menu.add(dish7);
        menu.add(dish8);
        menu.add(dish9);
        menu.add(dish10);
        menu.add(dish11);
        menu.add(dish12);
        menu.add(dish13);
        menu.add(dish14);
        menu.add(dish15);
        menu.add(dish16);
        return menu;
    }

    /**
     * is used to display all the items and its information onto the ordering panel
     * and will add it in rows
     * Calls{@link org.main.view.order.ItemViewController#setItemData(Dish)} to get the data for each item
     */
    public void menuDisplayCard() {
        menu = returnMenu();

        int row = 0;
        int column = 0;

        menu_grid.getRowConstraints().clear();
        menu_grid.getColumnConstraints().clear();

        for (int i = 0; i < menu.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane pane = loader.load();
                ItemViewController Item = loader.getController();
                Item.setItemData(menu.get(i));
                if (column == 2) {
                    column = 0;
                    row += 1;
                }
                menu_grid.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * set the values for the ordering table
     */
    public void setMenu_tableView() {
        menu_col_product.setCellValueFactory(new PropertyValueFactory<Order, String>("Product"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Price"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Quantity"));
        menu_col_DishID.setCellValueFactory(new PropertyValueFactory<Order,Integer>("DishID"));
    }

    /**
     * is used for the select orders button to go and select an order to edit or pay
     * @param event
     * @throws SQLException
     */
    @FXML
    public void openOrders(ActionEvent event) throws SQLException {
        viewHandler.openViewOrders();
    }

    public boolean isPayOrder() {
        return payOrderbool;
    }

    /**
     * @param payOrder is passed to determine whether or not you are paying for an order
     */
    public void setPayOrder(boolean payOrder) {
        this.payOrderbool = payOrder;
    }

    /**
     * @param event when the X button is pressed the view will close
     */
    @FXML
    public void close(ActionEvent event){
        viewHandler.close();
    }

    /**
     * @param event when the - button is pressed the view will minimise
     */
    @FXML
    public void minimise(ActionEvent event) {
        viewHandler.minimize();
    }

    /**
     * @param event when an order has been selected and the pay button is pressed it will
     * call {@link  org.main.view.order.OrderDB#addPaidOrder(Integer, float, java.sql.Date, String, Integer)}
     * to make the order now paid for in the database
     * @throws SQLException
     */
    @FXML
    public void payOrder(ActionEvent event) throws SQLException {
        if (menu_tableView.getItems().size() > 0) {
            String allergyInfo = menu_allergy_info.getText();
            Integer tableNo = Integer.parseInt(menu_table_num.getText());
            Date date = new Date();
            java.sql.Date currentDate = new java.sql.Date(date.getTime());

            OrderDB.addPaidOrder(orderID, total, currentDate, allergyInfo, tableNo);

            for (int i = 0; i < menu_tableView.getItems().size(); i++) {
                OrderDB.addToDishes(menu_col_DishID.getCellData(i), menu_col_quantity.getCellData(i), orderID);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully paid for an order");
            alert.showAndWait();

            menu_allergy_info.clear();
            menu_table_num.clear();
            menu_total.setText("£0.00");
            menu_tableView.getItems().clear();
            editingOrder = false;

        }
    }

    /**
     * @param event when an item is selected on the ordering view and the remove button is pressed
     * the item will get removed from the table
     */
    @FXML
    public void RemoveItem(ActionEvent event){
        int selectedID = menu_tableView.getSelectionModel().getSelectedIndex();
        menu_tableView.getItems().remove(selectedID);
        calcTotal();
    }

    /**
     * @param event when an items have been added to the ordering table and the send order button is pressed it will call
     * Calls {@link org.main.view.order.OrderDB#addToOrder(float, java.sql.Date, String, Integer)} and
     * Calls{@link org.main.view.order.OrderDB#addToDishes(Integer, Integer, Integer)
     * to add the order details and the dishes of the order to the database}
     * @throws SQLException
     */
    @FXML
    public void sendOrder(ActionEvent event) throws SQLException {
        if (menu_tableView.getItems().size()>0) {
            String allergyInfo = menu_allergy_info.getText();
            Integer tableNo = Integer.parseInt(menu_table_num.getText());
            Date date = new Date();
            java.sql.Date currentDate = new java.sql.Date(date.getTime());

            if(editingOrder) {
                OrderDB.addEditedOrder(orderID, total, currentDate, allergyInfo, tableNo);
            }
            else {
            OrderDB.addToOrder(total,currentDate,allergyInfo,tableNo);
            orderID = OrderDB.getLastOrderID();
            }
        for(int i =0; i <menu_tableView.getItems().size();i++){
            OrderDB.addToDishes(menu_col_DishID.getCellData(i),menu_col_quantity.getCellData(i),orderID);
        }
        menu_allergy_info.clear();
        menu_table_num.clear();
        menu_total.setText("£0.00");
        menu_tableView.getItems().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Ordered!");
            alert.showAndWait();
        }
        editingOrder = false;
    }

    /**
     * is used to add items to the ordering table
     * @param product the name of the item ordered
     * @param quantity the amount of the item ordered
     * @param price the total price for the amount of an item ordered
     * @param dishID the dishID of the item ordered
     * @throws IOException
     */
    public void updateTable(String product, Integer quantity, Integer price,Integer dishID) throws IOException {
            Order order = new Order(product, quantity, price, dishID);
            ObservableList<Order> orders = menu_tableView.getItems();
            orders.add(order);
            menu_tableView.setItems(orders);
            calcTotal();
        }

    /**
     * used to calculate the total price of the order and change its text
     */
    public void calcTotal(){
        total = 0;
        for(int i =0; i <menu_tableView.getItems().size();i++){
            total += menu_col_price.getCellData(i);
        }
            menu_total.setText("£"+ String.valueOf(total));
        }



}
