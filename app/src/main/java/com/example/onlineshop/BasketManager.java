package com.example.onlineshop;

import java.util.ArrayList;
import java.util.List;

public class BasketManager {
    private static BasketManager instance;
    private List<BasketItem> basketItems;

    // Private constructor prevents instantiation from other classes
    private BasketManager() {
        basketItems = new ArrayList<>();
    }

    // Singleton pattern implementation
    public static synchronized BasketManager getInstance() {
        if (instance == null) {
            instance = new BasketManager();
        }
        return instance;
    }

    // Get all items in the basket
    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    // Add an item to the basket
    public void addItemToBasket(Shoe shoe, int quantity) {
        // Check if the Shoe already exists in the basket
        for (BasketItem item : basketItems) {
            if (item.getShoe().equals(shoe)) {
                // If exists, just update the quantity
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // If not exists, add as a new item
        basketItems.add(new BasketItem(shoe, quantity));
    }

    // Optional: Method to clear basket
    public void clearBasket() {
        basketItems.clear();
    }
}
