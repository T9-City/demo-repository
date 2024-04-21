package org.main.view.order;
/**
 * The Order class represents an order placed that will appear on the .
 * It contains information about the product, quantity, price, and dish ID.
 */
public class Order {
    private String Product;
    private Integer Quantity;
    private Integer Price;

    /**
     * @return the dishID as an Integer for the item being ordered
     */

    public Integer getDishID() {
        return DishID;
    }

    /**
     * @param dishID is passed to set the dishID
     */
    public void setDishID(Integer dishID) {
        DishID = dishID;
    }

    private Integer DishID;
    /**
     * @return The item's name as a String.
     */
    public String getProduct() {
        return Product;
    }
    /**
     * @param product is passed to set the items name for the order.
     */
    public void setProduct(String product) {
        Product = product;
    }

    /**
     * @return the amount of the item ordered as an Integer
     */
    public Integer getQuantity() {
        return Quantity;
    }

    /**
     * @param quantity is passed to set the amount of the item ordered for the odering table
     */
    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    /**
     * @return the total price for the amount of a specific item ordered
     */
    public Integer getPrice() {
        return Price;
    }

    /**
     * @param price is passed to set the total price of the amount ordered for the ordering table
     */

    public void setPrice(Integer price) {
        Price = price;
    }
    /**
     * Constructs a new Order object with the specified product, quantity, price, and dish ID.
     * @param product The name of the item ordered.
     * @param quantity The quantity of the item ordered.
     * @param price The total price of the item that is ordered, which is the item price * quantity amount
     * @param dishID The ID of the dish ordered.
     */

    public Order(String product, Integer quantity, Integer price, Integer dishID) {
        Product = product;
        Quantity = quantity;
        Price = price;
        DishID = dishID;
    }
}
