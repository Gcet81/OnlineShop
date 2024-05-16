package com.example.onlineshop;

public class Order {
    private int orderId;
    private String userId;
    private String address;


    public Order() {}

    public Order(int orderId, String userId, String address) {
        this.orderId = orderId;
        this.userId = userId;
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }
}
