package com.example.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BasketActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BasketAdapter adapter;
    private Button checkoutButton;
    private TextView totalAmountTextView; // TextView for displaying the total price

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recyclerView = findViewById(R.id.basketRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BasketAdapter(BasketManager.getInstance().getBasketItems());
        recyclerView.setAdapter(adapter);

        totalAmountTextView = findViewById(R.id.totalAmountTextView); // Initialize the TextView
        updateTotalAmount(); // Calculate and display the total

        checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(BasketActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }

    private void updateTotalAmount() {
        double total = 0;
        for (BasketItem item : BasketManager.getInstance().getBasketItems()) {
            total += item.getShoe().getPrice() * item.getQuantity();
        }
        totalAmountTextView.setText(String.format("Total: %.2f OMR", total));
    }
}
