package com.example.onlineshop;

public class Shoe {
    private String name;
    private int imageResourceId;
    private String[] sizes;
    private double price;

    public Shoe(String name, int imageResourceId, String[] sizes, double price) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.sizes = sizes;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String[] getSizes() {
        return sizes;
    }

    public double getPrice() {
        return price;
    }
}
