package com.example.onlineshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserPanelActivity extends AppCompatActivity {

    private TextView tvUserEmail;
    private TextView tvOrderDetails;
    private Button homeButton;  // Add this line to define the button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvOrderDetails = findViewById(R.id.tvOrderDetails);
        homeButton = findViewById(R.id.homeButton);  // Initialize the button

        // Set user email from Firebase Auth
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            tvUserEmail.setText("Email: " + user.getEmail());
        } else {
            tvUserEmail.setText("No user logged in");
        }

        // Load and display order history from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserOrders", MODE_PRIVATE);
        String orderHistory = prefs.getString("orders", "No orders made yet.");
        tvOrderDetails.setText(orderHistory);

        // Set the click listener for the home button
        homeButton.setOnClickListener(v -> {
            // Create an Intent to start HomePageActivity
            Intent intent = new Intent(UserPanelActivity.this, HomePageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
