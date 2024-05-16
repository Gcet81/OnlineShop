package com.example.onlineshop; // Make sure to replace this with your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 5000; // Duration in milliseconds (5000ms = 5s)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure the layout name matches your XML file name

        // Initialize the TextView by finding it by its ID
        TextView welcomeTextView = findViewById(R.id.welcomeText);
        welcomeTextView.setText("Welcome to Shoe Shop!");

        // Using a Handler to delay the transition to LoginActivity
        new Handler().postDelayed(() -> {
            // Create an Intent that will start the LoginActivity.
            Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(mainIntent);
            MainActivity.this.finish(); // This call is used to finish the current activity
        }, SPLASH_DISPLAY_LENGTH);
    }
}
