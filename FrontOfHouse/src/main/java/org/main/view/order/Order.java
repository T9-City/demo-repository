package org.main.view.order;

public class Order {
    private String Product;
    private Integer Quantity;
    private Integer Price;

    public Integer getDishID() {
        return DishID;
    }

    public void setDishID(Integer dishID) {
        DishID = dishID;
    }

    private Integer DishID;

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Order(String product, Integer quantity, Integer price, Integer dishID) {
        Product = product;
        Quantity = quantity;
        Price = price;
        DishID = dishID;
    }
}
