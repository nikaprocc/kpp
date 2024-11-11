package com.labs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int orderNumber;
    private Client client;
    @JsonIgnore
    private transient List<Dish> dishes;

    public Order() {}

    public Order(int orderNumber, Client client, List<Dish> dishes) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.dishes = dishes;
    }

    // Getters and setters
    public int getOrderNumber() { return orderNumber; }
    public void setOrderNumber(int orderNumber) { this.orderNumber = orderNumber; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public List<Dish> getDishes() { return dishes; }
    public void setDishes(List<Dish> dishes) { this.dishes = dishes; }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", client=" + client +
                ", dishes=" + dishes +
                '}';
    }

}