package com.example.onlineshop;

public class BasketItem {
    private Shoe shoe;
    private int quantity;

    public BasketItem(Shoe shoe, int quantity) {
        this.shoe = shoe;
        this.quantity = quantity;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
