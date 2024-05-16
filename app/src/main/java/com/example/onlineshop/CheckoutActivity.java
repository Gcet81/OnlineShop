package com.example.onlineshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CheckoutActivity extends AppCompatActivity {

    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        addressEditText = findViewById(R.id.addressEditText);
        Button confirmOrderButton = findViewById(R.id.confirmOrderButton);

        confirmOrderButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString().trim();
            if (!address.isEmpty()) {
                int orderId = generateOrderId();
                showConfirmationDialog(orderId, address);
            } else {
                Toast.makeText(this, "Please enter a delivery address.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int generateOrderId() {
        // Simulate order ID generation
        Random random = new Random();
        return 100000 + random.nextInt(900000);  // Generate a random six-digit order ID
    }

    private void showConfirmationDialog(int orderId, String address) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Confirmed");
        builder.setMessage("Your order has been placed successfully!\nOrder ID: " + orderId +
                "\nIt will be delivered to: " + address +
                "\nPayment is due upon delivery.");
        builder.setPositiveButton("OK", (dialog, which) -> {
            saveOrder(orderId);
            navigateToUserPanel();
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveOrder(int orderId) {
        SharedPreferences prefs = getSharedPreferences("UserOrders", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String existingOrders = prefs.getString("orders", "");
        String updatedOrders = existingOrders + "Order ID: " + orderId + "\n";
        editor.putString("orders", updatedOrders);
        editor.apply();
    }

    private void navigateToUserPanel() {
        Intent intent = new Intent(this, UserPanelActivity.class);
        startActivity(intent);
    }
}
